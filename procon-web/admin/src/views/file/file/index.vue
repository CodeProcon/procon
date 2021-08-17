<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="文件名称" prop="fileOldName">
        <el-input
          v-model="queryParams.fileOldName"
          placeholder="请输入文件名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>


      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['file:file:add']"
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['file:file:edit']"
        >修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['file:file:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['file:file:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="fileList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="序号" align="center" prop="id"/>
      <el-table-column label="文件名" align="center" prop="fileOldName"/>
      <el-table-column label="文件保存方式" align="center">
        <template slot-scope="scope">
          <template v-if="scope.row.fileType === 0">
            <span>本地</span>
          </template>
          <template v-if="scope.row.status === 1">
            <span>阿里oss</span>
          </template>
        </template>
      </el-table-column>
      <el-table-column label="文件扩展名" align="center" prop="fileExpandedName"/>
      <el-table-column label="文件大小(MB)" align="center">
        <template slot-scope="scope">
          <span>{{(scope.row.fileSize/(1024*1024)).toFixed(2)}}</span>
        </template>
      </el-table-column>

      <el-table-column label="状态" align="center">
        <template slot-scope="scope">
          <template v-if="scope.row.status === 1">
            <span>正常</span>
          </template>
          <template v-if="scope.row.status === 2">
            <span>停用</span>
          </template>
        </template>
      </el-table-column>

      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['file:file:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['file:file:remove']"
          >删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改文件对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item lable="上传文件">
          <FileUpload ref="fileUpload"></FileUpload>
        </el-form-item>
        <el-form-item label="保存方式" prop="fileType">
          <el-select v-model="form.fileType" placeholder="请选择文件保存方式(0:本地 1:阿里OSS)">
            <el-option label="请选择字典生成" value=""/>
          </el-select>
        </el-form-item>
        <el-form-item label="文件分类" prop="fileSortId">
          <el-input v-model="form.fileSortId" placeholder="请输入文件分类uid"/>
        </el-form-item>

        <el-form-item label="状态">
          <el-radio-group v-model="form.status">
            <el-radio label="1">请选择字典生成</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {listFile, getFile, delFile, addFile, updateFile} from "@/api/file/file";
import FileUpload from "@/components/FileUpload"
export default {
  name: "File",
  components: {
    FileUpload
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 文件表格数据
      fileList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        fileOldName: null,
        fileName: null,
        fileType: null,
        fileUrl: null,
        fileExpandedName: null,
        fileSize: null,
        fileSortId: null,
        adminId: null,
        userId: null,
        status: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        // fileType: [
        //   {required: true, message: "文件保存方式(0:本地 1:阿里OSS)不能为空", trigger: "change"}
        // ],
        // status: [
        //   {required: true, message: "状态不能为空", trigger: "blur"}
        // ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询文件列表 */
    getList() {
      this.loading = true;
      listFile(this.queryParams).then(response => {
        this.fileList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        fileOldName: null,
        fileName: null,
        fileType: null,
        fileUrl: null,
        fileExpandedName: null,
        fileSize: null,
        fileSortId: null,
        status: "1"
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加文件";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getFile(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改文件";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateFile(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addFile(this.form).then(response => {
              debugger
              console.log(this.$refs.fileUpload)
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除文件编号为"' + ids + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return delFile(ids);
      }).then(() => {
        this.getList();
        this.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('file/file/export', {
        ...this.queryParams
      }, `file_file.xlsx`)
    }
  }
};
</script>

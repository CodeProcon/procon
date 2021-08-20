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
          <template v-if="scope.row.fileType === 1">
            <span>阿里oss</span>
          </template>
        </template>
      </el-table-column>
      <el-table-column label="文件扩展名" align="center" prop="fileExpandedName"/>
      <el-table-column label="文件大小(MB)" align="center">
        <template slot-scope="scope">
          <span>{{ (scope.row.fileSize / (1024 * 1024)).toFixed(2) }}</span>
        </template>
      </el-table-column>

      <el-table-column label="状态" align="center">
        <template slot-scope="scope">
          <template v-if="scope.row.status === 0">
            <span>正常</span>
          </template>
          <template v-if="scope.row.status === 1">
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

        <div class="imgBody" v-if="form.fileUrl">
          <el-form-item label="文件">
            <div v-if="picArray.indexOf(form.fileExpandedName) > -1">
              <img alt="logo"  v-bind:src="form.fileUrl" style="display:inline; width: 320px;height: 180px;">
            </div>
            <div v-else-if="videoArray.indexOf(form.fileExpandedName) > -1">
              <video width="320" height="180" controls>
                <source src="form.fileUrl" type="video/mp4" />
              </video>
            </div>

          </el-form-item>

        </div>
        <div v-else>
          <el-form-item label="上传">
            <el-upload
              action=""
              :http-request="submitUpload"
              :file-list="uploadFileList"
              :limit = "1"
              :before-remove="beforeRemove"
              :before-upload="beforeFileUpload">
              <el-button size="small" type="primary" icon="el-icon-upload">点击上传</el-button>
            </el-upload>
          </el-form-item>
        </div>

        <el-form-item label="文件分类" prop="fileSortId">
          <el-select v-model="form.fileSortId" placeholder="请选择">
            <el-option
              v-for="item in sortList"
              :key="item.id.toString()"
              :label="item.sortName"
              :value="item.id.toString()">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="状态">
          <el-select v-model="form.status">
            <el-option v-for="dict in statusOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue">
            </el-option>
          </el-select>
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
import {delFile, getFile, listFile, saveFile, updateFile} from "@/api/file/file";
import FileUpload from "@/components/FileUpload"
import {listSort} from "@/api/file/sort";
import {fileUpload} from "@/api/blog/content";

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
      // 文件分类表格数据
      sortList: [],
      // 文件表格数据
      fileList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      querySortParams: {
        pageNum: 1,
        pageSize: 100,
      },
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        fileName: null,
        fileType: null,
        fileSortId: null,
        status: null,
      },
      statusOptions: [],
      // 表单参数
      form: {
        id: null,
        fileName: null,
        fileUrl: null,
        fileType: 1,
        fileSortId: null,
        status: null,
      },
      uploadFileList: [],
      fileUrl:"",
      //图片数组
      picArray:["jpg","png","jpeg","gif","bmp"],
      videoArray:["mp4","avi","mov","flv","rmvb"],
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
    this.getDicts("sys_normal_disable").then(response => {
      this.statusOptions = response.data;
    });
    this.getSortList();
    this.getList();
  },
  methods: {
    /** 查询文件分类列表*/
    getSortList() {
      this.loading = true;
      listSort(this.querySortParams).then(response => {
        this.sortList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
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
        fileOldName:null,
        fileSize:null,
        fileExpandedName:null,
        fileName: null,
        fileUrl: null,
        fileType: 1,
        fileSortId: null,
        status: null,
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
        debugger
        this.form = response;
        this.form.status = response.status.toString();
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
            saveFile(this.form).then(response => {
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
    },

    /**
     * 文件上传前校验
     **/
    beforeFileUpload(file){
      let isRightSize = file.size / 1024 / 1024 < 200
      if (!isRightSize) {
        this.$message.error('文件大小超过 200MB')
      }
      return isRightSize
    },
    beforeRemove(file, uploadFileList) {
      return this.$confirm(`确定移除 ${ file.name }？`);
    },
    //文件上传
    submitUpload(content){
      let _this = this
      const formData = new FormData()
      formData.append('upload', content.file)
      formData.append('uploadType', '1')
      formData.append('notSave',"true")
      fileUpload(formData).then(response => {
        debugger
        _this.fileUrl = response.data.fileUrl;
        _this.form.fileUrl = response.data.fileUrl;
        _this.form.fileName = response.data.fileName;
        _this.form.fileOldName = response.data.fileOldName;
        _this.form.fileSize = response.data.fileSize;
        _this.form.fileExpandedName = response.data.fileExpandedName;

      });
    },
    /** 删除图片*/
    deletePhoto: function() {
      this.form.fileUrl = null;
    },



  }
};
</script>

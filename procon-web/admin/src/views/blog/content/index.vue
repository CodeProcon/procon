<template>
  <div class="app-container">
    <!--查询条件模块-->
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <!--博客标题查询条件-->
      <el-input
        clearable
        class="filter-item"
        style="width: 150px; margin: 0 10px 0 0;"
        v-model="queryParams.title"
        placeholder="请输入博客标题"
        @keyup.enter.native="handleQuery">
      </el-input>
      <!--博客分类查询条件-->
      <el-select
        v-model="queryParams.blogSortId"
        filterable
        clearable
        placeholder="请选择分类名"
        @keyup.enter.native="handleQuery"
        style="margin: 0px 10px 0px 10px; width: 160px"
        :loading="loading">
          <el-option
            v-for="item in sortOptions"
            :key="item.id"
            :label="item.sortName"
            :value="item.id">
          </el-option>
        </el-select>
      <!--博客标签查询条件-->
      <el-select
        v-model="queryParams.tagId"
        filterable
        clearable
        placeholder="请选择标签"
        @keyup.enter.native="handleQuery"
        style="margin: 0px 10px 0px 10px; width: 160px"
        :loading="loading">
        <el-option
          v-for="item in tagOptions"
          :key="item.id"
          :label="item.content"
          :value="item.id">
        </el-option>
      </el-select>
      <!--推荐等级查询条件-->
      <el-select
        v-model="queryParams.level"
        filterable
        clearable
        placeholder="请选择推荐等级"
        @keyup.enter.native="handleQuery"
        style="margin: 0px 10px 0px 10px; width: 160px"
        :loading="loading">
        <el-option
          v-for="item in blogLevelDictList"
          :key="item.id"
          :label="item.dictLabel"
          :value="item.dictValue">
        </el-option>
      </el-select>
      <!--是否发布查询条件-->
      <el-select
        v-model="queryParams.isPublish"
        filterable
        clearable
        placeholder="是否发布"
        @keyup.enter.native="handleQuery"
        style="margin: 0px 10px 0px 10px; width: 110px"
        :loading="loading">
        <el-option
          v-for="item in blogPublishDictList"
          :key="item.id"
          :label="item.dictLabel"
          :value="item.dictValue">
        </el-option>
      </el-select>
      <!--是否原创查询条件-->
      <el-select
        v-model="queryParams.isOriginal"
        filterable
        clearable
        placeholder="是否原创"
        @keyup.enter.native="handleQuery"
        style="margin: 0px 10px 0px 10px; width: 110px"
        :loading="loading">
        <el-option
          v-for="item in blogOriginalDictList"
          :key="item.id"
          :label="item.dictLabel"
          :value="item.dictValue">
        </el-option>
      </el-select>

      <el-form-item>
        <el-button style="margin:0 10px 0 10px" type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    <!--新增 修改 删除 导出-->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['blog:content:add']">新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['blog:content:edit']">修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['blog:content:remove']">删除
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!--数据表格列表模块-->
    <el-table v-loading="loading" :data="contentList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" width="60" align="center">
        <template slot-scope="scope">
          <span>{{scope.$index + 1}}</span>
        </template>
      </el-table-column>

      <el-table-column label="标题图" width="160" align="center">
        <template slot-scope="scope">
          <img v-if="scope.row.photoList" :src="scope.row.photoList[0]" style="width: 130px;height: 70px;" alt="标题图">
        </template>
      </el-table-column>

      <el-table-column label="标题" width="160" align="center">
        <template slot-scope="scope">
          <span @click="onClick(scope.row)" style="cursor:pointer;">{{ scope.row.title }}</span>
        </template>
      </el-table-column>

      <el-table-column label="作者" width="100" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.author }}</span>
        </template>
      </el-table-column>

      <el-table-column label="是否原创" width="100" align="center">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.isOriginal===1" type="success">原创</el-tag>
          <el-tag v-if="scope.row.isOriginal===0" type="info">转载</el-tag>
        </template>
      </el-table-column>

      <el-table-column label="分类" width="100" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.blogSortList[0].sortName }}</span>
        </template>
      </el-table-column>

      <el-table-column label="标签" width="200" align="center" >
        <template slot-scope="scope">
          <template>
            <el-tag
              style="margin-left: 3px"
              type="warning"
              v-if="item"
              :key="index"
              v-for="(item, index) in scope.row.tagList"
            >{{item.content}}</el-tag>
          </template>
        </template>
      </el-table-column>

      <el-table-column label="推荐等级" width="100" align="center">
        <template slot-scope="scope">
          <el-tag v-for="item in blogLevelDictList" :key="item.id" v-if="scope.row.level == item.dictValue" :type="item.listClass">{{item.dictLabel}}</el-tag>
        </template>
      </el-table-column>

      <el-table-column label="点击数" width="70" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.clickCount }}</span>
        </template>
      </el-table-column>

      <el-table-column label="开启评论" width="100" align="center">
        <template slot-scope="scope">
          <template>
            <el-tag v-for="item in openDictList" :key="item.id" :type="item.listClass" v-if="scope.row.startComment == item.dictValue">{{item.dictLabel}}</el-tag>
          </template>
        </template>
      </el-table-column>

      <el-table-column label="发布状态" width="100" align="center">
        <template slot-scope="scope">
          <template>
            <el-tag v-for="item in blogPublishDictList" :key="item.Id" :type="item.listClass" v-if="scope.row.isPublish == item.dictValue">{{item.dictLabel}}</el-tag>
          </template>
        </template>
      </el-table-column>

      <el-table-column label="创建时间" width="160" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.createTime }}</span>
        </template>
      </el-table-column>

      <el-table-column label="操作" fixed="right" min-width="150">
        <template slot-scope="scope">
          <el-button @click="handleUpdate(scope.row)" type="primary" size="small" v-hasPermi="['blog:content:edit']">编辑</el-button>
          <el-button @click="handleDelete(scope.row)" type="danger" size="small" v-hasPermi="['blog:content:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!--分页-->
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改博客对话框 -->
    <el-dialog :title="title" :visible.sync="dialogFormVisible" :before-close="closeDialog" fullscreen>
      <el-form :model="form" :rules="rules" ref="form">
        <el-form-item label="标题图" :label-width="formLabelWidth">
          <div class="imgBody" v-if="form.photoList.length > 0">
            <em class="el-icon-error inputClass" v-show="icon" @click="deletePhoto()" @mouseover="icon = true"></em>
            <img @mouseover="icon = true" @mouseout="icon = false" v-bind:src="form.photoList[0]" style="display:inline; width: 195px;height: 105px;">
          </div>
          <!--<div v-else >-->
          <!--  <el-upload-->
          <!--    class="avatar-uploader"-->
          <!--    action=""-->
          <!--    accept=".jpg, .jpeg, .png"-->
          <!--    :before-upload="beforeAvatarUpload"-->
          <!--    :http-request='submitUpload'-->
          <!--    :show-file-list = "false">-->
          <!--      <img v-if="imageUrl" :src="imageUrl" class="avatar">-->
          <!--      <em v-else class="el-icon-plus avatar-uploader-icon"></em>-->
          <!--    </el-upload>-->
          <!--</div>-->
          <div v-else class="uploadImgBody" @click="checkPhoto">
            <em class="el-icon-plus avatar-uploader-icon"></em>
          </div>
        </el-form-item>

        <el-form-item label="标题" :label-width="formLabelWidth" prop="title">
          <el-input  v-model="form.title" auto-complete="off" @input="contentChange" style = "width:80%"></el-input>
        </el-form-item>

        <el-form-item label="简介" :label-width="formLabelWidth">
          <el-input type="textarea" :rows="4"  v-model="form.summary" auto-complete="off" @input="contentChange" style = "width:80%"></el-input>
        </el-form-item>

        <el-row :gutter="20">
          <el-col :span="8">
            <el-form-item label="分类" :label-width="formLabelWidth" prop="blogSortId">
              <el-select
                v-model="form.blogSortId"
                size="small"
                placeholder="请选择"
                style="width:250px"
              >
                <el-option
                  v-for="item in blogSortData"
                  :key="item.id"
                  :label="item.sortName"
                  :value="item.id"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="是否发布" :label-width="lineLabelWidth" prop="isPublish">
              <el-radio-group v-model="form.isPublish" size="small">
                <el-radio v-for="item in blogPublishDictList" :key="item.id" :label="item.dictValue" border size="medium">{{item.dictLabel}}</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>


          <el-col :span="8">
            <el-form-item label="推荐等级" :label-width="lineLabelWidth" prop="level">
              <el-select v-model="form.level" size="small" placeholder="请选择" style="width:210px">
                <el-option
                  v-for="item in blogLevelDictList"
                  :key="item.id"
                  :label="item.dictLabel"
                  :value="parseInt(item.dictValue)"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="8">
            <el-form-item label="标签" :label-width="formLabelWidth">
              <el-select
                v-model="tagValue"
                multiple
                size="small"
                placeholder="请选择"
                style="width:250px"
                filterable
              >
                <el-option
                  v-for="item in tagData"
                  :key="item.id"
                  :label="item.content"
                  :value="item.id"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="是否原创" :label-width="formLabelWidth" prop="isOriginal">
              <el-radio-group v-model="form.isOriginal" size="small">
                <el-radio v-for="item in blogOriginalDictList" :key="item.id" :label="item.dictValue" border size="medium">{{item.dictLabel}}</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="网站评论" :label-width="formLabelWidth" prop="startComment">
              <el-radio-group v-model="form.startComment" size="small">
                <el-radio v-for="item in openDictList" :key="item.id"  :label="item.dictValue" border size="medium">{{item.dictLabel}}</el-radio>
              </el-radio-group>

            </el-form-item>
          </el-col>
        </el-row>

        <el-form-item label="作者" :label-width="formLabelWidth"  prop="author">
          <el-input v-model="form.author" auto-complete="off" style="width: 80%;"></el-input>
        </el-form-item>

        <el-form-item label="文章出处" :label-width="formLabelWidth" v-if="form.isOriginal==0">
          <el-input v-model="form.articlesPart" auto-complete="off" style="width: 80%"></el-input>
        </el-form-item>

        <el-form-item label="内容" :label-width="formLabelWidth" prop="content" class="ckCustomer">
          <ckeditor :editor="editor" :value="editorData" v-model="editorData" :content="form.content"></ckeditor>
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer" style="margin-right:60%">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitForm">确 定</el-button>
      </div>
    </el-dialog>

    <CheckPhoto
      v-if="!isFirstPhotoVisible"
      @choose_data="getChooseData"
      @cancelModel="cancelModel"
      :photoVisible="photoVisible"
      :photos="photoList"
      :files="fileIds"
      :limit="1"
    ></CheckPhoto>
  </div>
</template>

<script>
import {listBlog, addBlog, getContent, fileUpload, updateContent,delContent} from "@/api/blog/content";
import {getDataByTypeList} from "@/api/system/dict/data"
import {blogSortList} from "@/api/blog/sort"
import {blogTagList} from "@/api/blog/tag"
import CheckPhoto from "@/components/CheckPhoto"
import ClassicEditor  from  "@/components/CKEditor/core/ckeditor"
import CKEditor from "@ckeditor/ckeditor5-vue";

export default {
  name: "Content",

  /** ckeditor*/
  components: {
    CheckPhoto,
    // 编辑器组件的局部注册方式
    ckeditor: CKEditor.component
  },
  props: {
    value: {
      type: String,
      default: ''
    },
    placeholder: {
      type: String,
      default: '请输入内容'
    }
  },
  data() {
    return {
      editor: ClassicEditor,
      editorData:'',
      editorConfig: {
        placeholder: this.placeholder,
      },
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
      // 博客表格数据
      contentList: [],
      // 弹出层标题
      title: "增加博客",
      dialogFormVisible: false, //控制弹出框
      subjectVisible: false, // 是否显示专题
      isFirstSubjectVisible: true, // 专题选择器是否首次显示【用于懒加载】
      formLabelWidth: "120px",
      lineLabelWidth: "120px", //一行的间隔数
      maxLineLabelWidth: "100px",
      isEditForm: false,
      photoVisible: false, //控制图片选择器的显示
      isFirstPhotoVisible: true, // 图片选择器是否首次显示【用于懒加载】
      photoList: [],
      fileIds: "",
      icon: false, //控制删除图标的显示
      interval: null, //定义触发器
      // 是否显示弹出层
      open: false,

      tagData: [], //标签数据
      tagOptions:[],
      tagValue: [], //保存选中标签id(编辑时)
      blogSortData: [],
      sortOptions:[],
      blogOriginalDictList: [], //存储区域字典
      blogPublishDictList: [], //是否字典
      blogLevelDictList: [], //博客推荐等级字典
      openDictList: [], // 是否启动字典
      blogOriginalDefault: null, //博客原创默认值
      blogLevelDefault: 0, //博客等级默认值
      blogPublishDefault: null, //博客发布默认值
      openDefault: 0, // 是否开启评论默认值
      imageUrl:"",
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        title: null,
        summary: null,
        content: null,
        tagId: null,
        clickCount: null,
        collectCount: null,
        status: null,
        isOriginal: null,
        author: null,
        articlesPart: null,
        blogSortId: null,
        level: null,
        isPublish: null,
        sort: null,
        startComment: null
      },
      // 表单参数
      form: {
        id: null,
        /** 文件id */
        fileId: null,
        /** 图片列表 */
        photoList:[],
        /** 标题 */
        title: null,
        /** 简介 */
        summary: null,
        /** 分类id */
        blogSortId: null,
        /** 是否发布 */
        isPublish: null,
        /** 推荐等级 */
        level:null,
        /** 标签数据 */
        tagData:null,
        /** 标签显示值 */
        tagValue:null,
        /** 标签id */
        tagId: null,
        /** 博客内容 */
        content: null,
        /** 是否原创 */
        isOriginal: null,
        /** 是否开启评论 */
        startComment: null,
        /** 作者 */
        author: null,
        /** 文章出处*/
        articlesPart: null, //文章出处
        /** 点击次数 */
        clickCount: 0,
        status:0,
      },
      // 表单校验
      rules: {
        status: [
          { required: true, message: "状态不能为空", trigger: "blur" }
        ],
        sort: [
          { required: true, message: "排序字段不能为空", trigger: "blur" }
        ],
        startComment: [
          { required: true, message: "是否开启评论(0:否 1:是)不能为空", trigger: "blur" }
        ]
      }
    };
  },


  created() {
    this.getDictList();
    this.blogSortList();
    this.tagList();
    this.getList();
  },
  methods: {
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        fileId: null,
        photoList:[],
        title: null,
        summary: null,
        blogSortId: null,
        isPublish: null,
        level:null,
        tagData:null,
        tagValue:null,
        tagId: null,
        content: null,
        isOriginal: null,
        startComment: null,
        author: null,
        articlesPart: null,
        clickCount: 0,
        status:0,
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
    /** 多选框选中数据 */
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd: function() {
      this.title = "增加博客"
      let that = this;
      //重置表单数据
      that.editorData = "请输入内容"
      that.reset();

      that.tagValue = []
      that.fileId = null
      that.photoList = []
      that.dialogFormVisible = true;
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getContent(id).then(response => {
        this.form = response.data;
        let tagArr = response.data.tagList;
        this.tagValue = [];
        tagArr.forEach((item,index,array)=>{
          this.tagValue.push(item.id)
        })
        this.editorData = this.form.content;
        this.form.startComment = this.form.startComment+'';
        this.dialogFormVisible = true;
        this.title = "修改博客";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          //获取CKEditor中的内容
          this.form.content = this.editorData;
          this.form.tagId = this.tagValue.join(",");
          this.form.fileId = this.fileId;
          this.form.status = 0;
          if (this.form.id != null) {
            updateContent(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.dialogFormVisible = false;
              this.getList();
            });
          } else {
            addBlog(this.form).then(response => {
              this.msgSuccess("新增成功");
              this.dialogFormVisible = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除博客编号为"' + ids + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function() {
        return delContent(ids);
      }).then(() => {
        this.getList();
        this.msgSuccess("删除成功");
      })
    },
    /** 查询博客标签列表 */
    tagList: function() {
      const tagParams = {};
      tagParams.pageSize = 100;
      tagParams.currentPage = 1;
      blogTagList(tagParams).then(response => {
        this.tagData = response.rows;
        this.tagOptions = response.rows;
      });
    },
    /** 查询博客分类列表 */
    blogSortList: function() {
      const blogSortParams = {};
      blogSortParams.pageSize = 100;
      blogSortParams.currentPage = 1;
      blogSortList(blogSortParams).then(response => {
        this.blogSortData = response.rows;
        this.sortOptions = response.rows;
      });
    },
    /** 查询字典列表 */
    getDictList:function (){
      const dictTypeList =  ['sys_recommend_level', 'sys_original_status', 'sys_publish_status', 'sys_normal_disable'];
      getDataByTypeList(dictTypeList).then(response => {
        const dictMap = response.data;
        this.blogOriginalDictList = dictMap.sys_original_status
        this.blogPublishDictList = dictMap.sys_publish_status
        this.blogLevelDictList = dictMap.sys_recommend_level
        this.openDictList = dictMap.sys_normal_disable
      })
    },
    /** 查询博客列表 */
    getList() {
      this.loading = true;
      listBlog(this.queryParams).then(response => {
        this.contentList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    /** 跳转到该博客详情 */
    onClick: function(row) {

    },

    /** 关闭窗口 */
    closeDialog(done) {
      if(this.isChange) {
        this.$confirm("是否关闭博客编辑窗口", "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        })
          .then(() => {
            // 清空触发器
            clearInterval(this.interval);
            this.isChange = false;
            this.changeCount = 0
            done();
          })
          .catch(() => {
            this.$commonUtil.message.info("已取消")
          });
      } else {
        // 清空触发器
        clearInterval(this.interval);
        this.isChange = false;
        this.changeCount = 0
        done();
      }
    },
    /** 删除图片*/
    deletePhoto: function() {
      this.form.photoList = null;
      this.form.fileId = "";
    },
    checkPhoto() {
      this.photoList = [];
      this.fileIds = "";
      this.photoVisible = true;
      this.isFirstPhotoVisible = false
    },
    // 内容改变，触发监听
    contentChange: function() {
      let that = this;
      if(this.changeCount > 0) {
        that.isChange = true;
        //存放到cookie中，时间10天
        //获取CKEditor中的内容
        that.form.tagId = that.tagValue.join(",");
        //Cookies.set("form", JSON.stringify(that.form),{expires: 240 * 60 * 60 * 60 * 1000 })
      }
      this.changeCount = this.changeCount + 1;
    },

    // 将图片上传到服务器，返回地址替换到md中
    imgAdd(pos, $file) {
      let data = new FormData();
      data.append('upload', $file);
      data.append('uploadType',"1");
      //访问后台服务器方法
      fileUpload(data).then(response => {
        let url = response.data;
        this.$refs.md.$img2Url(pos, url)
      });
    },

    beforeAvatarUpload(file) {
      // const isJPG = file.type === 'image/jpeg';
      // const isPNG = file.type === 'image/png';
      // const isLt2M = file.size / 1024 / 1024 < 4;
      //
      // if (!isJPG || !isPNG) {
      //   this.$message.error('上传头像图片只能是 JPG或PNG 格式!');
      // }
      // if (!isLt2M) {
      //   this.$message.error('上传头像图片大小不能超过 4MB!');
      // }
      // return isJPG && isLt2M;
    },

    // 上传文件
   submitUpload(content) {
     const formData = new FormData()
     formData.append('upload', content.file)
     formData.append('uploadType', '1')
     fileUpload(formData).then(response => {
       this.imageUrl = response.data.fileUrl;
       this.fileId = response.data.id;
     });
   },
    getChooseData(data) {
      this.photoVisible = false;
      this.photoList = data.photoList;
      this.fileIds = data.fileIds;
      let fileId = this.fileIds.replace(",", "");
      if (this.photoList.length >= 1) {
        this.form.fileId = fileId;
        this.form.photoList = this.photoList;
      }
    },
    //关闭模态框
    cancelModel() {
      this.photoVisible = false;
    },
    getToJson(parse){

      parse = parse.replace(/&/g,'","').replace(/=/g,'":"');
      parse= '{"'+parse+'"}';
      return parse;
    }
  }
};
</script>

<style scoped>
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  margin: 0 0 0 10px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width:  195px;
  height: 105px;
  line-height: 105px;
  text-align: center;
}
.imgBody {
  width:  195px;
  height: 105px;
  border: solid 2px #ffffff;
  float: left;
  position: relative;
}
.uploadImgBody {
  margin-left: 5px;
  width:  195px;
  height: 105px;
  border: dashed 1px #c0c0c0;
  float: left;
  position: relative;
}
.uploadImgBody :hover {
  border: dashed 1px #00ccff;
}
.inputClass {
  position: absolute;
}
.el-dialog__body {
  padding-top: 10px;
  padding-bottom: 0px;
}
.el-dialog {
  min-height: 400px;
}
.el-upload__tip {
  margin-top: 10px;
  margin-left: 10px;
  color: #3e999f;


}

.upload-demo {
  margin-top: 50px;
}
.tipBox {
  margin-bottom: 30px;
}
.tip {
  font-size: 14px;
  font-weight: bold;
  color: 	#808080;
}
.tipItem {
  line-height: 22px;
  color: 	#A9A9A9;
}
.ckCustomer{
  width: 81%;
}

.avatar {
  width: 178px;
  height: 178px;
  display: block;
}

</style>

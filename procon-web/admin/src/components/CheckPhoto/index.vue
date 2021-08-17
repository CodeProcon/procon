<template>
  <div id="body-loading" class="app-container calendar-list-container">
    <el-dialog title="请选择图片" :visible.sync="dialogVisible" fullscreen :before-close="before_close">
      <div class="filter-container" style="margin: 10px 0 10px 0;">
        <el-input
          clearable
          class="filter-item"
          style="width: 200px;"
          v-model="keyword"
          @change="handleFind"
          placeholder="请输入分类名称"
        ></el-input>
        <el-button class="filter-item" type="primary" icon="el-icon-search" @click="handleFind" >查找</el-button>
        <el-button class="filter-item" type="primary" @click="handleRest" icon="el-icon-refresh">重置</el-button>
      </div>

      <el-tabs
        v-model="activeName"
        type="border-card"
        tab-position="left"
        style="height: 600px; width: 100%;"
        @tab-click="clickTab"
      >
        <el-tab-pane
          style="height: 570px; width: 100%; overflow:auto;"
          v-for="(pictureSort, index) in  pictureSorts"
          :key="index"
        >
          <div class="sortItem" slot="label" style="float:left">
            <em class="el-icon-picture"></em>
            {{submitText(pictureSort.name)}}
          </div>
          <div style="clear:both;"></div>
          <div>
            <img
              alt=" "
              v-if="pictureSort.pictures"
              v-for="picture in pictureSort.pictures"
              :key="picture.fileId"
              class="showPicture"
              @click="checkLogoConfirm(picture.fileId,picture.fileUrl)"
              :src="picture.fileUrl"
            />
          </div>
          <div class="addPicture" v-if="pictureSort.total - (pictureSort.pageSize*pictureSort.pageNum) < 0" @click="toPictureManager(pictureSort.pictureSortId)">
            <span>+</span>
          </div>

          <el-pagination
            class="pagination"
            @current-change="handleCurrentChange"
            :current-page.sync="pictureSort.pageNum"
            :page-size="pictureSort.pageSize"
            small
            layout="prev, pager, next"
            :total="pictureSort.total"
          ></el-pagination>

        </el-tab-pane>
      </el-tabs>
      <span slot="footer" class="dialog-footer">
        <div class="ChooseBody" :key="index" v-for="(picture, index) in form.photoList">
          <em @click="deletePhoto(index)" class="el-icon-error inputClass" v-show="icon"></em>
          <img style="width: 100%;height: 100%;" :src="picture" alt=" ">
        </div>
        <el-button @click="cancel">取 消</el-button>
        <el-button type="primary" @click="commit">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import {getPictureSortList, getPictureSortById} from "@/api/blog/pictureSort";
import { getPictureList } from "@/api/blog/picture";
import Vue from "vue";
import { Loading } from "element-ui";

export default {
  props: ["photoVisible", "photos", "files", "limit"],
  created() {
    this.dialogVisible = this.photoVisible;
    this.form.photoList = this.photos;
    this.form.fileIds = this.files;

    let loadingInstance = Loading.service({
      target: "#body-loading",
      text: "加载中~"
    });

    //加载数据
    let that = this;
    //先加载分类
    if (!that.havePictureSorts) {
      let params = {};
      params.pageSize = 500
      params.pageNum = 1;
      params.isShow = 1;
      getPictureSortList(params).then(function(response) {
          debugger
          let pictureSorts = response.rows;
          that.pictureSorts = pictureSorts;
          //默认初始化第一个
          if (pictureSorts.length > 0) {
            let pictureSortId = pictureSorts[0].id;
            that.currentPictureSortId= pictureSorts[0].id;
            let name = pictureSorts[0].name;
            let requestParams = {};
            requestParams.pictureSortId = pictureSortId;
            requestParams.pageSize = 24;
            requestParams.pageNum = 1;
            getPictureList(requestParams).then(picListResponse => {
                let newObject = {
                  pictureSortId: pictureSortId,
                  name: name,
                  pictures: picListResponse.rows,
                  pageSize: requestParams.pageSize,
                  pageNum: requestParams.pageNum ,
                  total: picListResponse.total
                };
                Vue.set(that.pictureSorts, 0, newObject);

            });
          }

        loadingInstance.close();
      }).catch(function () {
        loadingInstance.close();
      });
    }
  },

  data() {
    return {
      dialogVisible: this.photoVisible,
      sortList: [],
      havePictureSorts: false, //是否加载完图片分类
      pictureSorts: [], //分类列表
      icon: true,
      activeName: "0",
      limitCount: this.limit,
      newPictureSort: [],
      keyword: "",
      currentPictureSortId: null, //当前图片分类id
      form: {
        photoList: [],
        fileIds: []
      }
    };
  },
  watch: {
    photoVisible: function() {
      this.dialogVisible = this.photoVisible;
    },
    photos: function() {
      this.form.photoList = this.photos;
    },
    files: function() {
      this.form.fileIds = this.files;
    },
    limit: function() {
      if (this.limit) {
        this.limitCount = this.limit;
      }
    }
  },
  methods: {
    handleCurrentChange: function(val) {
      let that = this;
      let pictureSortId = this.currentPictureSortId;
      let pictureSortParams = {};
      pictureSortParams.id = pictureSortId
      getPictureSortById(pictureSortParams).then(sortResponse => {
          let pictureSort = sortResponse.data;
          let params = {}
          params.pictureSortId = pictureSortId
          params.pageNum = val
          params.pageSize = 24
          getPictureList(params).then(function(response) {
              let newObject = {
                pictureSortId: pictureSortId,
                name: pictureSort.name,
                pictures: response.rows,
                pageSize: params.pageSize,
                pageNum: params.pageNum,
                total: response.total
              };
              Vue.set(that.pictureSorts, that.activeName, newObject);
          });
      });
    },
    handleRest: function() {
      let loadingInstance = Loading.service({
        target: "#body-loading",
        text: "加载中~"
      });
      this.activeName = "0";
      let that = this;
      let params = {};
      params.pageNum = 1;
      params.pageSize = 500;
      params.isShow = 1;
      getPictureSortList(params).then(function(response) {
          let pictureSorts = response.rows;
          that.pictureSorts = pictureSorts;
          loadingInstance.close();
          //默认初始化第一个
          if (pictureSorts.length > 0) {
            let pictureSortId = pictureSorts[0].id;
            that.currentPictureSortId = pictureSortId; //当前pictureSortId
            let name = pictureSorts[0].name;
            let requestParams = {};
            requestParams.pictureSortId = pictureSortId
            requestParams.pageSize = 24
            requestParams.pageNum = 1;
            getPictureList(requestParams).then(picListResponse => {
                let newObject = {
                  pictureSortId: pictureSortId,
                  name: name,
                  pictures: picListResponse.rows,
                  pageSize: requestParams.size,
                  pageNum: requestParams.pageNum,
                  total: picListResponse.total
                };
                Vue.set(that.pictureSorts, 0, newObject);
            });
          }

      });
    },
    handleFind: function() {
      let that = this;
      if (this.keyword === "") {
        this.handleRest()
        return;
      }
      let params = {};
      params.pageSize = 500
      params.pageNum = 1;
      params.isShow = 1;
      params.keyword = this.keyword
      getPictureSortList(params).then(function(response) {
          debugger
          let pictureSorts = response.rows;
          that.pictureSorts = pictureSorts;
          if (pictureSorts.length <= 0) {
            that.$message({
              type: "error",
              message: "没有搜索到任何信息！"
            });
          }
          let pictureSortId = pictureSorts[0].id;
          let name = pictureSorts[0].name;
          let pictureParams = {};
          pictureParams.pictureSortId = pictureSortId
          pictureParams.pageSize = 24
          pictureParams.pageNum = 1;
          getPictureList(pictureParams).then(function(picListResponse) {
              let newObject = {
                pictureSortId: pictureSortId,
                name: name,
                pictures: picListResponse.rows,
                pageSize: pictureParams.pageSize,
                pageNum: pictureParams.pageNum,
                total: picListResponse.total
              };
              Vue.set(that.pictureSorts, 0, newObject);

          });

      });
    },
    clickTab(e) {
      let that = this;
      let index = this.activeName;
      let pictureSortId = this.pictureSorts[index].id === undefined ? this.pictureSorts[index].pictureSortId : this.pictureSorts[index].id;
      this.currentPictureSortId = pictureSortId;
      let name = this.pictureSorts[index].name;
      let params = {};
      params.pageNum = 1;
      params.pictureSortId = pictureSortId;
      params.pageSize = 24;
      getPictureList(params).then(function(response) {
          if (response.rows.length > 0) {
            let newObject = {
              pictureSortId: pictureSortId,
              name: name,
              pictures: response.rows,
              pageSize: params.pageSize,
              pageNum: params.pageNum,
              total: response.total
            };
            Vue.set(that.pictureSorts, index, newObject);
          }
      });
    },
    submitText: function(str) {
      let result;
      if (str.length > 6) {
        result = str.substring(0, 6) + "...";
      } else {
        result = str;
      }
      return result;
    },
    before_close(done) {
      //取消时，开始状态
      this.form.photoList = []
      this.form.fileIds = ""
      this.$emit("cancelModel", "");
      done();
    },
    cancel() {
      //取消时，还原成开始状态
      this.form.photoList = []
      this.form.fileIds = ""
      this.$emit("cancelModel", "");
    },
    handleClick(tab, event) {
      console.log(tab, event);
    },
    commit(photoList, fileIds) {
      let data = {
        photoList: this.form.photoList,
        fileIds: this.form.fileIds
      };
      this.$emit("choose_data", data);
    },
    //点击选中图片
    checkLogoConfirm: function(fileId, fileUrl) {
      if (this.limitCount !== undefined) {
        if (this.form.photoList.length >= this.limitCount) {
          this.$message({
            message: "最多只能选择" + this.limitCount + "张图片！",
            type: "error"
          });
          return;
        }
      }
      console.log("fileIds的内容", this.form.fileIds);
      if (this.form.fileIds != null) {
        if (this.form.fileIds.indexOf(fileId) !== -1) {
          this.$message({
            message: "该图片已存在列表中！",
            type: "warning"
          });
          return;
        }
      }

      this.form.photoList.push(fileUrl);
      this.$forceUpdate();
      this.form.fileIds = this.form.fileIds + "," + fileId;
      this.$message({
        message: "添加成功",
        type: "success"
      });
    },
    deletePhoto(index) {
      let ids = this.form.fileIds;
      ids = ids
        .split(",")
        .join(" ")
        .trim();
      let array = ids.split(" ");
      this.form.photoList.splice(index, 1);
      let newStr = "";
      let tag = -1;
      this.$message({
        message: "删除成功",
        type: "success"
      });
      for (let a = 0; a < array.length - 1; a++) {
        tag++;
        if (array[a] == null || array[a] === "" || tag === index) continue;
        if (a === 0) {
          newStr = array[a];
        } else {
          newStr = newStr + "," + array[a];
        }
      }
      this.form.fileIds = newStr;
      console.log(this.form.fileIds, "修改后");
    },
    toPictureManager: function(pictureSortId) {
      this.$confirm("是否跳转到图片管理进行上传？", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          /**
           * 跳转到图片上传
           */
          this.$router.push({
            path: "/picture/picture",
            query: { pictureSortId: pictureSortId }
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消跳转"
          });
        });
      console.log("选择图片", pictureSortId);
    }
  }
};
</script>
<style>
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 150px;
  min-height: 400px;
}
.sortItem {
  height: 38px;
  font-size: 16px;
}
.showPicture {
  width: 195px;
  height: 105px;
  float: left;
  margin-left: 10px;
  margin-top: 30px;
  border: solid 1px #c7aeae;
}
.ChooseBody {
  width: 195px;
  height: 105px;
  float: left;
  margin-left: 10px;
  margin-top: 20px;
  border: solid 1px #c7aeae;
}
.inputClass {
  position: absolute;
}
.addPicture {
  width: 195px;
  height: 105px;
  float: left;
  margin-left: 10px;
  margin-top: 30px;
  border: solid 1px #c7aeae;
  line-height: 105px;
  text-align: center;
  cursor: pointer;
}

.addPicture span {
  font-size: 30px;
  color: #97a8be;
  height: 60px;
  margin: 0 auto;
}

.pagination {
  position: absolute;
  bottom: 5%;
  left: 38%;
}
</style>

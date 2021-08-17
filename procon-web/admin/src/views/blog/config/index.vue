<template>
  <div class="app-container">

    <el-tabs type="border-card">
      <el-tab-pane>
        <span slot="label">
          <em class="el-icon-date"></em> 网站信息
        </span>

        <el-form style="margin-left: 20px;" label-position="left" :model="form" label-width="80px" ref="from">
          <el-form-item label="LOGO">
            <div class="imgBody" v-if="form.photoList">
              <em class="el-icon-error inputClass" v-show="icon" @click="deletePhoto()" @mouseover="icon = true"></em>
              <img alt="logo" @mouseover="icon = true" @mouseout="icon = false" v-bind:src="form.photoList[0]" style="display:inline; width: 195px;height: 105px;">
            </div>
            <div v-else >
              <el-upload
                class="avatar-uploader"
                action=""
                accept=".jpg, .jpeg, .png"
                :before-upload="beforeAvatarUpload"
                :http-request='submitUpload'
                :show-file-list = "false">
                <img alt="logo" v-if="imageUrl" :src="imageUrl" class="avatar">
                <em v-else class="el-icon-plus avatar-uploader-icon"></em>
              </el-upload>
            </div>
          </el-form-item>

          <el-row :gutter="24">
            <el-col :span="10">
              <el-form-item label="网站名称" prop="oldPwd">
                <el-input v-model="form.name" style="width: 400px"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="10">
              <el-form-item label="标题" prop="newPwd1">
                <el-input v-model="form.title" style="width: 400px"></el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row :gutter="24">
            <el-col :span="10">
              <el-form-item label="关键字" prop="newPwd2">
                <el-input v-model="form.keyword" style="width: 400px"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="10">
              <el-form-item label="描述" prop="newPwd1">
                <el-input v-model="form.summary" style="width: 400px"></el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row :gutter="24">
            <el-col :span="10">
              <el-form-item label="作者" prop="newPwd2">
                <el-input v-model="form.author" style="width: 400px"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="10">
              <el-form-item label="备案号" prop="newPwd2">
                <el-input v-model="form.recordNum" style="width: 400px"></el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row :gutter="24">
            <el-col :span="10">
              <el-form-item label="登录方式">
                <el-checkbox-group v-model="form.loginTypeList">
                  <el-checkbox label="1" style="margin-left: 10px">账号密码</el-checkbox>
                  <el-checkbox label="2" style="margin-left: 10px">码云</el-checkbox>
                  <el-checkbox label="3" style="margin-left: 10px">Github</el-checkbox>
                  <el-checkbox label="4" style="margin-left: 10px">QQ</el-checkbox>
                </el-checkbox-group>
              </el-form-item>
            </el-col>
          </el-row>

          <el-form-item>
            <el-button type="primary" @click="submitForm()" >保 存</el-button>
          </el-form-item>
        </el-form>
      </el-tab-pane>


      <el-tab-pane>
        <span slot="label">
          <em class="el-icon-date"></em> 评论&打赏
        </span>

        <el-form
          style="margin-left: 20px;"
          label-position="left"
          :model="form"
          label-width="90px"
          ref="from"
        >

          <el-row :gutter="24">
            <el-col :span="4">
              <el-form-item label="阿里支付">
                <el-upload
                  class="avatar-uploader"
                  action=""
                  accept=".jpg, .jpeg, .png"
                  :before-upload="beforeAvatarUpload"
                  :http-request='submitAliPay'
                  :show-file-list = "false">
                  <img alt="支付宝二维码" v-if="form.aliPayPhoto" :src="form.aliPayPhoto" class="avatar">
                  <em v-else class="el-icon-plus avatar-uploader-icon"></em>
                </el-upload>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row :gutter="24">
            <el-col :span="4">
              <el-form-item label="微信支付">
                <el-upload
                  class="avatar-uploader"
                  action=""
                  accept=".jpg, .jpeg, .png"
                  :before-upload="beforeAvatarUpload"
                  :http-request='submitWeChat'
                  :show-file-list = "false">
                  <img alt="微信二维码" v-if="form.weChatPayPhoto" :src="form.weChatPayPhoto" class="avatar">
                  <em v-else class="el-icon-plus avatar-uploader-icon"></em>
                </el-upload>
              </el-form-item>
            </el-col>
          </el-row>


          <el-row :gutter="24">
            <el-col :span="8">
              <el-form-item label="网站评论">
                <el-radio v-for="item in openDictList" :key="item.id" v-model="form.startComment" :label="item.dictValue" border size="medium">{{item.dictLabel}}</el-radio>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row :gutter="24">
            <el-col :span="8">
              <el-form-item label="网站打赏">
                <el-radio v-for="item in openDictList" :key="item.id" v-model="form.status" :label="item.dictValue" border size="medium">{{item.dictLabel}}</el-radio>
              </el-form-item>
            </el-col>
          </el-row>


          <el-form-item>
            <el-button type="primary" @click="submitForm()" >保 存</el-button>
          </el-form-item>
        </el-form>

      </el-tab-pane>

      <el-tab-pane >
        <span slot="label">
          <em class="el-icon-date">关注我</em>
        </span>

        <el-form
          style="margin-left: 20px;"
          label-position="left"
          :model="form"
          label-width="80px"
          :rules="rules"
          ref="form"
        >
          <el-checkbox-group v-model="form.showList">

            <el-form-item label="邮箱" prop="email">
              <el-input v-model="form.email" style="width: 400px"></el-input>
              <el-checkbox label="1" style="margin-left: 10px">在首页显示</el-checkbox>
            </el-form-item>

            <el-form-item label="QQ号" prop="qqNumber">
              <el-input v-model="form.qqNumber" style="width: 400px"></el-input>
              <el-checkbox label="2" style="margin-left: 10px">在首页显示</el-checkbox>
            </el-form-item>

            <el-form-item label="github" prop="github">
              <el-input v-model="form.github" style="width: 400px"></el-input>
              <el-checkbox label="4" style="margin-left: 10px">在首页显示</el-checkbox>
            </el-form-item>

            <el-form-item label="Gitee" prop="gitee">
              <el-input v-model="form.gitee" style="width: 400px"></el-input>
              <el-checkbox label="5" style="margin-left: 10px">在首页显示</el-checkbox>
            </el-form-item>

            <el-form-item label="微信" prop="weChat">
              <el-input v-model="form.weChat" style="width: 400px"></el-input>
              <el-checkbox label="6" style="margin-left: 10px">在首页显示</el-checkbox>
            </el-form-item>

          </el-checkbox-group>

          <el-form-item>
            <el-button type="primary" @click="submitForm()">保 存</el-button>
          </el-form-item>

        </el-form>
      </el-tab-pane>

    </el-tabs>

  </div>
</template>

<script>
import {getConfigList,updateConfig} from "@/api/blog/config"
import {getDataByTypeList} from "@/api/system/dict/data"
import {fileUpload} from "@/api/blog/content";
export default {
  name: "Config",
  data(){
    return {
      form: {
        name: "",
        title: "",
        keyword: "",
        summary: "",
        author: "",
        logo: "",
        recordNum: "",
        startComment: "1",
        aliPay: "",
        weixinPay: "",
        aliPayPhoto: "",
        weChatPayPhoto: "",
        showList: [],
        loginTypeList: [],
        status: "1"
      },
      loadingInstance: null, // loading对象
      fileList: [],
      photoVisible: false, //控制图片选择器的显示
      photoList: [],
      fileIds: "",
      icon: false, //控制删除图标的显示
      otherData: {},
      openDictList: [], //字典
      imageUrl:"",
      indexLogo:"",
      rules: {
        qqNumber: [
          {pattern:  /[1-9]([0-9]{5,11})/, message: '请输入正确的QQ号码'},
        ],
        qqGroup: [
          {pattern:  /-?[1-9]\d*/, message: '请输入正确的QQ群'},
        ],
        gitee: [
          {pattern: /^((https|http|ftp|rtsp|mms)?:\/\/)[^\s]+/, message: '请输入正确的Gitee地址'},
        ],
        github: [
          {pattern: /^((https|http|ftp|rtsp|mms)?:\/\/)[^\s]+/, message: '请输入正确的Github地址'},
        ],
        email: [
          {pattern: /\w[-\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\.)+[A-Za-z]{2,14}/, message: '请输入正确的邮箱'},
        ]
      },

    }
  },
  created() {
    // 获取配置
    this.getWebConfigFun();
    // 查询字典
    this.getDictList();
    //其它数据
    this.otherData = {
      source: "picture",
      userId: "1",
      adminUid: "1",
      projectName: "blog",
      sortName: "admin"
    };
  },
  methods:{
    /** 查询字典列表 */
    getDictList(){
      const dictTypeList =  ['sys_normal_disable'];
      getDataByTypeList(dictTypeList).then(response => {
        const dictMap = response.data;
        this.openDictList = dictMap.sys_normal_disable
      })
    },
    /** 查询网站配置 */
    getWebConfigFun(){
      getConfigList().then(response => {
        let data = response.rows[0];
        if (data.showList) {
          let showList = JSON.parse(data.showList)
          let loginTypeList = JSON.parse(data.loginTypeList)
          data.showList = showList;
          data.loginTypeList = loginTypeList;

          this.form = data;
        } else {
            data.showList = []
            this.form = data;
        }
          this.fileIds = this.form.logo;
          this.photoList = this.form.photoList;
          this.form.status = this.form.status + "";
      });
    },
    //关闭模态框
    cancelModel() {
      this.photoVisible = false;
    },
    /** 提交 */
    submitForm: function() {

      let form = this.form;

      form.showList = JSON.stringify(this.form.showList)
      form.loginTypeList = JSON.stringify(this.form.loginTypeList)
      debugger
      form.logo = this.indexLogo
      updateConfig(form).then(response => {
        this.msgSuccess("修改成功");
        this.getWebConfigFun();
      });
    },
    /** 删除图片*/
    deletePhoto: function() {
      this.form.photoList = null;
      this.form.logo  = "";
    },

    beforeAvatarUpload(file) {

    },
    // 上传文件
    submitUpload(content) {
      let _this = this
      const formData = new FormData()
      formData.append('upload', content.file)
      formData.append('uploadType', '1')
      fileUpload(formData).then(response => {
        console.log(this.form)
        _this.imageUrl = response.data.fileUrl;
        _this.form.logo = response.data.id;
        _this.indexLogo = response.data.id;
        debugger
      });
    },
    submitWeChat(content) {
      const formData = new FormData()
      formData.append('upload', content.file)
      formData.append('uploadType', '1')
      console.log(this.form)
      fileUpload(formData).then(response => {
        this.form.weChatPayPhoto = response.data.fileUrl;
        this.form.weixinPay = response.data.id;
        console.log(this.form)
      });
    },
    submitAliPay(content) {
      const formData = new FormData()
      formData.append('upload', content.file)
      formData.append('uploadType', '1')
      fileUpload(formData).then(response => {
        this.form.aliPayPhoto = response.data.fileUrl;
        debugger
        this.form.aliPay = response.data.id;

      });
    },
  },



}
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

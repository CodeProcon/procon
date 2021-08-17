<template>
  <div>
    <div v-for="item in comments" :key="item.id">
      <div class="commentList">
        <span class="left p1">
          <img v-if="item.user" :src="item.user.avatar ? item.user.avatar:'https://typora-photo.oss-cn-shenzhen.aliyuncs.com/%E5%A4%B4%E5%83%8F/g.jpg'" onerror="onerror=null;src='https://typora-photo.oss-cn-shenzhen.aliyuncs.com/%E5%A4%B4%E5%83%8F/g.jpg'" />
          <img v-else src="https://typora-photo.oss-cn-shenzhen.aliyuncs.com/%E5%A4%B4%E5%83%8F/g.jpg" />
        </span>

        <span class="right p1">
          <div class="rightTop" v-if="item.user">
            <el-link class="userName" :underline="false">{{item.user.nickName}}</el-link>
            <span class="timeAgo" v-if="item.createTime">{{timeAgo(item.createTime)}}</span>
            <span class="timeAgo" v-else>刚刚</span>
          </div>

          <div class="rightCenter" v-html="$xss(item.content, options)"></div>

          <div class="rightBottom">
            <el-link class="b1" :underline="false" @click="replyTo(item)">回复</el-link>
            <el-link class="b1" :underline="false" @click="report(item)">举报</el-link>
            <el-link class="b1" v-if="$store.state.user.isLogin && $store.state.user.userInfo.id === item.userId" :underline="false" @click="delComment(item)">删除</el-link>
          </div>

          <div class="rightCommentList">
            <CommentBox class="comment" :userInfo="userInfo" :toInfo="toInfo" :id="item.id" :commentInfo="commentInfo"
                        @submit-box="submitBox" @cancel-box="cancelBox"></CommentBox>

            <CommentList class="commentStyle" :id="'commentStyle:' + item.id" :comments="item.replyList" :commentInfo="commentInfo"></CommentList>
          </div>
        </span>
      </div>
    </div>
  </div>

</template>

<script>

  import {mapMutations} from 'vuex';
  import CommentBox from "../CommentBox";
  import {timeAgo} from "@/utils/webUtils"
  import {addComment, deleteComment, getCommentList, reportComment} from "@/api/blog/comment";
  import {getListByDictTypeList}  from "@/api/system/dict"
  export default {
    name: "CommentList",
    props: ['comments', 'userInfos', 'commentInfo'],
    data() {
      return {
        // xss白名单配置
        options : {
          whiteList: {
            a: ['href', 'title', 'target'],
            span: ['class']
          }
        },
        tagStatue: true,
        submitting: false,
        value: '',
        toInfo: {
          id: "",
          commentId: ""
        },
        userInfo: {},
        userTagDictList: [], // 用户标签字典
      };
    },
    created() {
      this.getDictList()
    },
    components: {
      CommentBox
    },
    mounted() {

    },
    compute: {},
    methods: {
      ...mapMutations(['setCommentList', 'setUserTag']),
      /**
       * 字典查询
       */
      getDictList: function () {
        if(this.$store.state.app.userTagDictList !== undefined && this.$store.state.app.userTagDictList.length > 0) {
          this.userTagDictList = this.$store.state.app.userTagDictList
          return;
        }
        let dictTypeList =  ['sys_user_tag']
        getListByDictTypeList(dictTypeList).then(response => {
          let dictMap = response.data;
          this.userTagDictList = dictMap.sys_user_tag.list
          this.setUserTag(dictMap.sys_user_tag.list)
        });
      },
      replyTo: function (item) {
        if(!this.validLogin()) {
          this.$notify.error({
            title: '错误',
            message: "登录后才能回复评论哦~",
            offset: 100
          });
          return
        }
        let userId = item.userId;
        let commentId = item.id;
        let lists = document.getElementsByClassName("comment");
        for (let i = 0; i < lists.length; i++) {
          lists[i].style.display = 'none';
        }
        document.getElementById(commentId).style.display = 'block';
        this.toInfo.commentId = commentId
        this.toInfo.id = userId
      },
      /**  提交评论*/
      submitBox(e) {
        console.log("添加内容", e)
        let params = {};
        params.userId = e.userId;
        params.content = e.content;
        params.blogId = e.blogId;
        params.toId = e.toCommentId;
        params.touserId = e.touserId;
        params.source = e.source
        addComment(params).then(response => {
          let commentData = response.data
          document.getElementById(commentData.toId).style.display = 'none'
          let comments = this.$store.state.app.commentList;
          commentData.user = this.userInfo;
          // 设置回复为空
          commentData.replyList = [];
          commentData.user = this.$store.state.user.userInfo
          this.updateCommentList(comments, commentData.toId, commentData)
          this.$store.commit("setCommentList", comments);
            this.$notify({
                title: '成功',
                message: "评论成功",
                type: 'success',
                offset: 100
            });
          }
        );
      },
      /**  查询评论*/
      getCommentList: function () {
        let params = {};
        params.pageNum = 1;
        params.pageSize = 10;
        getCommentList(params).then(response => {
            this.comments = response.data;
        });
      },
      /**  取消评论*/
      cancelBox(toCommentId) {
        document.getElementById(toCommentId).style.display = 'none'
      },

      tagAll: function (item) {
        this.tagStatue = !this.tagStatue;
        let lists = document.getElementsByClassName("commentStyle");
        for (let i = 0; i < lists.length; i++) {
          lists[i].style.display = 'block';
        }
        if (this.tagStatue) {
          document.getElementById('commentStyle:' + item.id).style.display = 'block';
        } else {
          document.getElementById(item.id).style.display = 'none';
        }
      },
      /**  举报评论*/
      report: function (item) {
        if(!this.validLogin()) {
          this.$notify.error({
            title: '错误',
            message: "登录后才能举报评论哦~",
            offset: 100
          });
          return
        }
        let userId = this.$store.state.user.userInfo.id
        if(userId === item.userId) {
          this.$notify.error({
            title: '错误',
            message: "不能举报自己的评论哦~",
            offset: 100
          });
          return;
        }

        let params = {};
        params.id = item.id;
        params.userId = userId
        reportComment(params).then(response => {
          this.$notify({
            title: '成功',
            message: response.data,
            type: 'success',
            offset: 100
          });
        });
      },
      /**  删除评论*/
      delComment: function (item) {
        if(!this.validLogin()) {
          this.$notify.error({
            title: '错误',
            message: "登录后才能删除评论哦~",
            offset: 100
          });
          return
        }

        this.$confirm("此操作将把本评论和子评论删除, 是否继续?", "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        })
          .then(() => {
            let params = {};
            params.id = item.id;
            params.userId = this.$store.state.user.userInfo.id
            deleteComment(params).then(response => {
              if (response.code == this.$ECode.SUCCESS) {
                this.$notify({
                  title: '成功',
                  message: "删除成功",
                  type: 'success',
                  offset: 100
                });

              } else {
                this.$notify.error({
                  title: '错误',
                  message: "删除失败",
                  offset: 100
                });
              }
              let comments = this.$store.state.app.commentList;
              this.deleteCommentList(comments, params.id, null)
              this.$store.commit("setCommentList", comments);
              this.$emit("deleteComment", "")
            });

          })
          .catch(() => {
            this.$message({
              type: "info",
              message: "已取消删除"
            });
          });


      },
      /**  校验是否登录*/
      validLogin() {
        let userInfo = this.$store.state.user.userInfo
        if(userInfo.userName === undefined) {
          return false;
        } else {
          return true;
        }
      },
      /**
       * dateTimeStamp是一个时间毫秒，注意时间戳是秒的形式，在这个毫秒的基础上除以1000，就是十位数的时间戳。13位数的都是时间毫秒。
       * @param dateTimeStamp
       * @returns {string}
       */
      timeAgo(dateTimeStamp) {
        return timeAgo(dateTimeStamp)
      },
      updateCommentList(commentList, id, targetComment) {
        if (commentList === undefined || commentList.length <= 0) {
          return;
        }
        for (let item of commentList) {
          if (item.id === id) {
            let menu = item.replyList;
            menu.push(targetComment);
          } else {
            this.updateCommentList(item.replyList, id, targetComment);
          }
        }
      },
      deleteCommentList(commentList, id, parentList) {
        if (commentList === undefined || commentList.length <= 0) {
          return;
        }
        for (let item of commentList) {
          if (item.id === id) {
            commentList.splice(commentList.indexOf(item), 1);
          } else {
            this.deleteCommentList(item.replyList, id, item);
          }
        }
      },
    },
  };
</script>

<style scoped>
  .commentStyle {
    display: block;
    margin-top: 10px;
    margin-left: 10px;
    border-left: 1px dashed SlateGray;
  }
  .comment {
    display: none;
  }
  .commentList {
    width: 100%;
    margin: 0 auto;
  }
  .commentList .p1 {
    float: left;
  }
  .commentList .left {
    display: inline-block;
    width: 5%;
    height: 100%;
  }
  .commentList .left img {
    margin: 0 auto;
    width: 100%;
    border-radius: 50%;
  }
  .commentList .right {
    display: inline-block;
    width: 93%;
    margin-left: 5px;
  }
  .commentList .rightTop {
    height: 30px;
    margin-top: 2px;
  }
  .commentList .rightTop .userName {
    color: #303133;
    margin-left: 10px;
    font-size: 16px;
    font-weight: bold;
  }
  .commentList .rightTop .timeAgo {
    color: #909399;
    margin-left: 10px;
    font-size: 15px;
  }
  .commentList .rightCenter {
    margin-left: 20px;
    min-height: 50px;
    margin-top: 15px;
  }
  .commentList .rightBottom {
    margin-left: 10px;
    height: 30px;
  }
  .commentList .rightBottom el-link {

  }
  .commentList .rightBottom .b1 {
    margin-left: 10px;
  }
</style>

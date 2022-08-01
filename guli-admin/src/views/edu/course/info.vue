<template>
  <div class="app-container">
    <h2 style="text-align: center">填写课程基本信息</h2>
    <el-steps
      :active="1"
      process-status="wait"
      align-center
      style="margin-bottom: 40px"
    >
      <el-step title="填写课程基本信息" />
      <el-step title="创建课程大纲" />
      <el-step title="最终发布" />
    </el-steps>
    <el-form label-width="120px">
      <el-form-item label="课程标题">
        <el-input
          v-model="courseInfo.title"
          placeholder=" 示例：机器学习项目课：从基础到搭建项目视频课程。专业名称注意大小写"
        />
      </el-form-item>
      <!-- 一级分类 -->
      <el-form-item label="课程分类">
        <el-select
          v-model="courseInfo.subjectParentId"
          placeholder="一级分类"
          @change="subjectLevelOneChange"
        >
          <el-option
            v-for="subject in subjectOneList"
            :key="subject.id"
            :label="subject.title"
            :value="subject.id"
          />
        </el-select>
        <el-select v-model="courseInfo.subjectId" placeholder="二级分类">
          <el-option
            v-for="subject in subjectTwoList"
            :key="subject.id"
            :label="subject.title"
            :value="subject.id"
          />
        </el-select>
      </el-form-item>
      <!-- 课程讲师 -->
      <el-form-item label="课程讲师">
        <el-select v-model="courseInfo.teacherId" placeholder="请选择">
          <el-option
            v-for="teacher in teacherList"
            :key="teacher.id"
            :label="teacher.name"
            :value="teacher.id"
          />
        </el-select>
      </el-form-item>

      <el-form-item label="总课时">
        <el-input-number
          :min="0"
          v-model="courseInfo.lessonNum"
          controls-position="right"
          placeholder="请填写课程的总课时数"
        />
      </el-form-item>
      <!-- 课程简介 -->
      <!-- 课程简介-->
      <el-form-item label="课程简介">
        <tinymce :height="300" v-model="courseInfo.description" />
      </el-form-item>
      <!-- 课程封面-->
      <el-form-item label="课程封面">
        <el-upload
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
          :before-upload="beforeAvatarUpload"
          :action="BASE_API + '/eduoss/fileoss'"
          class="avatar-uploader"
        >
          <img :src="courseInfo.cover" width="400px" height="200px" />
        </el-upload>
      </el-form-item>
      <el-form-item label="课程价格">
        <el-input-number
          :min="0"
          v-model="courseInfo.price"
          controls-position="right"
          placeholder="免费课程设置为0元"
        />元
      </el-form-item>
      <el-form-item>
        <el-button
          :disabled="saveBtnDisabled"
          type="primary"
          @click="saveOrUpdate"
          >保存并下一步</el-button
        >
      </el-form-item>
    </el-form>
  </div>
</template> 
 <style scoped>
.tinymce-container {
  line-height: 29px;
}
</style>
<script>
import course from "@/api/edu/course";
import subject from "@/api/edu/subject";
import Tinymce from "@/components/Tinymce"; //引入组件
export default {
  //声明组件
  components: {
    Tinymce,
  },
  data() {
    return {
      saveBtnDisabled: false, // 保存按钮是否禁用
      courseInfo: {
        title: "",
        subjectId: "",
        subjectParentId: "",
        teacherId: "",
        lessonNum: 0,
        description: "",
        cover: "/static/999.jpg",
        price: 0,
      },
      teacherList: [], //封装讲师
      subjectOneList: [], //一级分类
      subjectTwoList: [], //二级分类
      BASE_API: process.env.BASE_API,
      courseId:'',
    };
  },
  created() {
    //获取路由id值
    if(this.$route.params && this.$route.params.id){
      this.courseId = this.$route.params.id
      //调用根据id查询的方法
      this.getInfo()

    }else{
      //初始化所有讲师
      this.getListTeacher();
      //初始化一级分类
      this.getOneSubject();

    }
    
  },
  watch:{
    //监听
    $route(to,from){
      this.init()
    }
  },
  methods: {
    init(){
      if (this.$route.params && this.$route.params.subjectId) {
        //从路径获取id值
        const id = this.$route.params.subjectId;
        this.getInfo(id);
      } else {
        //清空表单
        this.courseInfo = {};
      }
    },
    //根据课程id查询信息
    getInfo(){
      course.getCourseInfo(this.courseId).then(response=>{
        this.courseInfo = response.data.courseInfoVo
        //1.查询出所有的分类
        subject.getSubjectList()
        .then(response =>{
          //获取所有一节分类
          this.subjectOneList = response.data.list
          //把所有一级分类数组进行遍历，比较当前courseInfo里面一级分类id和所有的一级分类id是否相同
          for(var i = 0;i<this.subjectOneList.length;i++){
            //获取每个一级分类
            var oneSubject = this.subjectOneList[i]
            //比较当前courseInfo里面一级分类id和所有的yijifenleiid
            if(this.courseInfo.subjectParentId == oneSubject.id){
              //获取一级分类中的所有二级分类
              this.subjectTwoList = oneSubject.children
            }
          }
        })
        //初始化所有讲师
        this.getListTeacher()
      })
    },
    //上传之前调用的方法
    beforeAvatarUpload(file) {
      const isJPG = file.type === "image/jpeg";
      const isLt2M = file.size / 1024 / 1024 < 2;
      if (!isJPG) {
        this.$message.error("上传头像图片只能是 JPG 格式!");
      }
      if (!isLt2M) {
        this.$message.error("上传头像图片大小不能超过 2MB!");
      }
      return isJPG && isLt2M;
    },
    //上传封面成功调用的方法
    handleAvatarSuccess(response, file) {
      console.log(response); // 上传响应
      console.log(URL.createObjectURL(file.raw)); // base64编码
      this.courseInfo.cover = response.data.url;
    },

    //当我们点击某个一级分类会触发change显示对应二级分类
    subjectLevelOneChange(value) {
      //value为一级分类的id值
      //遍历所有分类包含一级二级
      for (var i = 0; i < this.subjectOneList.length; i++) {
        //每个一级分类
        var oneSubject = this.subjectOneList[i];
        //判断：所有的一级分类id和点击的一级分类id是否一样
        if (value === oneSubject.id) {
          //从一级分类里面获取所有的二级分类
          this.subjectTwoList = oneSubject.children;
          this.courseInfo.subjectId = "";
        }
      }
    },
    addCourse(){
      course.addCourseInfo(this.courseInfo)
      .then(response =>{
        this.$message({
          type:'success',
          message:'添加课程信息成功!'
        });
          this.$router.push({
          path: "/course/chapter/" + response.data.courseId,
        });
      })
    },
    updateCourse(){
      course.updateCourseInfo(this.courseInfo)
        .then(response =>{
           this.$message({
          type:'success',
          message:'修改课程信息成功!'
        });
          this.$router.push({
          path: "/course/chapter/"+this.courseId,
        });
        })
    },
    saveOrUpdate() {
      //判断添加还是修改
      if(!this.courseInfo.id){
        this.addCourse()
      }else{
        this.updateCourse()
      }
    },
    getListTeacher() {
      course.getListTeacher().then((response) => {
        this.teacherList = response.data.items;
        // console.log(this.teacherList)
      });
    },
    //查询所有一级分类
    getOneSubject() {
      subject.getSubjectList().then((response) => {
        this.subjectOneList = response.data.list;
      });
    },
  },
};
</script>

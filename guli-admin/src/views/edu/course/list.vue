<template>
  <div class="app-container">
    课程列表
    <!--查询表单-->
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item>
        <el-input v-model="courseQuery.name" placeholder="课程名称" />
      </el-form-item>
      <el-form-item>
        <el-select
          v-model="courseQuery.status"
          clearable
          placeholder="课程状态"
        >
          <el-option :value="Normal" label="已发布" />
          <el-option :value="Draft" label="未发布" />
        </el-select>
      </el-form-item>
      <el-button type="primary" icon="el-icon-search" @click="getList()"
        >查询</el-button
      >
      <el-button type="default" @click="resetData()">清空</el-button>
    </el-form>
    <el-table
      v-loading="listLoading"
      :data="list"
      element-loading-text="数据加载中"
      border
      fit
      highlight-current-row
    >
      <el-table-column label="序号" width="70" align="center">
        <template slot-scope="scope">
          {{ (current - 1) * limit + scope.$index + 1 }}
        </template>
      </el-table-column>
      <el-table-column prop="title" label="课程名称" width="80" />
      <el-table-column label="课程状态" width="80">
        <template slot-scope="scope">
          {{ scope.row.status === 'Normal' ? "已发布" : "未发布" }}
        </template>
      </el-table-column>
      <el-table-column prop="lessnum" label="课时数" />
      <el-table-column prop="gmtCreate" label="添加时间" width="160" />
      <el-table-column prop="viewCount" label="浏览数量" width="60" />
      <el-table-column label="操作" width="200" align="center">
        <template slot-scope="scope">
          <router-link :to="'/teacher/edit/' + scope.row.id">
            <el-button type="primary" size="mini" icon="el-icon-edit"
              >编辑课程基本信息</el-button
            >
          </router-link>
            <router-link :to="'/teacher/edit/' + scope.row.id">
            <el-button type="primary" size="mini" icon="el-icon-edit"
              >编辑课程大纲</el-button
            >
              <router-link :to="'/teacher/edit/' + scope.row.id">
            <el-button type="primary" size="mini" icon="el-icon-edit"
              >课程删除</el-button
            >
          </router-link>
          </router-link>
          <el-button
            type="danger"
            size="mini"
            icon="el-icon-delete"
            @click="removeDataById(scope.row.id)"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
   
  </div>
</template>
<script>
//引入course.js文件
import course from "@/api/edu/course";
export default {
  //核心代码位置
  // data:{

  // },
  data() {
    //定义页面中的属性和初始值
    return {
      list: null, //查询之后接口返回的集合
      current: 1,
      limit: 5,
      courseQuery: {},
      total: 0,
    };
  },
  created() {
    //页面渲染之前执行，调用methods定义的方法
    //调用
    this.getList();
  },
  methods: {
    //创建具体的方法，调用teacher.js定义的方法
    //讲师列表的方法
    getList() {
      course
        .getListCourse()
        .then((response) => {
          //response接口返回的数据
          // console.log(response)
          this.list = response.data.list;
        //   console.log(this.list);
        })
       
    },
    resetData() {
      //清空表单输入项的数据
      this.courseQuery = {};
      //查询所有讲师数据
      this.getList();
    },
    
  },
};
</script>
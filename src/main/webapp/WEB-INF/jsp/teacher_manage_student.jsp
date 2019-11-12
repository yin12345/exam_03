<%@ page language="java" import="java.util.*"
		 pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>上机考试系统</title>
	<link rel="stylesheet" href="../static/css/index1.css">

	<style>
		.dropbtn {

			color: white;

			border: none;
			cursor: pointer;
		}

		.dropdown {
			position: relative;
			display: inline-block;
		}

		.dropdown-content {
			display: none;
			position: absolute;
			right: 0;
			background-color: #f9f9f9;
			min-width: 100px;
			box-shadow: 0px 3px 5px 0px rgba(0,0,0,0.2);
		}

		.dropdown-content a {
			color: black;
			height:35px;
			text-decoration: none;
			display: block;
		}

		.dropdown-content a:hover {background-color: #f1f1f1}

		.dropdown:hover .dropdown-content {
			display: block;
		}

		.dropdown:hover .dropbtn {

		}
	</style>
</head>
<body>

<!-- 顶部条 -->
<div class="box">
	<div class="cen clear">
		<div class="lefta" style="font-size:20px;color:#eee;display:block;float:left">
			<li style="float:left">
				上级考试系统  <span class="shu">|</span>
			</li>
			<a href="main">首页</a>  <span class="shu">|</span>
			<a href="before">考前操作</a>  <span class="shu">|</span>
			<div class="dropdown">
				<a class="dropbtn">考中管理</a>
				<div class="dropdown-content" style="left:0;">
					<a href="teacher_manage_summary">考试概况</a>
					<a href="teacher_manage_notify">学生信息</a>
					<a href="teacher_manage_unlock">解除绑定</a>
					<a href="teacher_manage_unlock">通过管理</a>
				</div>
			</div><span class="shu">|</span>
			<a href="after">考后操作</a>

		</div>
		<div class="righta">


			<a href="login">修改口令</a> <span class="shu">|</span> <a
				href="login">退出</a> <span class="shu">|</span>

			<a>登录人：${sessionScope.name}</a>


		</div>
	</div>
	<!-- 文字条 -->
	<div class="box1 clear">
		<div class="mi" style="font-size:40px">
			<img src="../static/img/mi.jpg">
			上机考试管理
		</div>


	</div>

	<form action="teacher_student_insert" class="exam-form" enctype="multipart/form-data" method="post" style="padding:20px;background-color:#EEE;margin:0 0 20px">
	 <h3>添加单个学生</h3>
	 <input name="sno" placeholder="学号*" size="20" type="text">
	 <input name="sname" placeholder="姓名*" size="20" type="text">
	 <input name="sclass" placeholder="班级*" size="20" type="text">
	 <input name="eid" value="1" type="hidden">
	 <input class="btn" value="添加" type="submit">
	</form>
	
	<form action="teacher_student_insert" class="exam-form" enctype="multipart/form-data" method="post" style="padding:20px;background-color:#EEE;margin:0 0 20px">
	 <h3>查找学生信息</h3>
	 <input name="sno" placeholder="学号*" size="20" type="text">
	 <input name="sname" placeholder="姓名*" size="20" type="text">
	 <input name="sclass" placeholder="班级*" size="20" type="text">
	 <input class="btn" value="添加" type="submit">
	</form>
	
	<div style="padding:20px;background-color:#EEE;margin:0 0 20px;">
	<h3>查找结果</h3>
	<table class="table" border="1" style="width:100%;padding:20px;">
		 <thead>
		  <tr>
		   <th>学号</th>
		   <th>姓名</th>
		   <th>班级</th>
		   <th>ip地址</th>
		  </tr>
		 </thead>
		 <tboby>
		  <tr>
		   
		 </tboby>
		</table>
	</div>
	<div style="hight:50%">
		<br>
	</div>

</div>
</body>

</html>
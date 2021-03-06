<%@ page language="java" import="java.util.*"
		 pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>上机考试系统</title>
	<link rel="stylesheet" href="../static/css/index1.css">
	<link rel="stylesheet" href="../static/plugin/layui/css/layui.css"  media="all">
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
				<div class="dropdown-content" style="left:0;z-index: 999">
					<a href="teacher_manage_summary">考试概况</a>
					<a href="teacher_manage_notify">学生信息</a>
					<a href="teacher_manage_unlock">解除绑定</a>
					<a href="teacher_manage_student">通过管理</a>
				</div>
			</div><span class="shu">|</span>
			<a href="after">考后操作</a>

		</div>
		<div class="righta">

			<a href="fix1">修改口令</a> <span class="shu">|</span>
			 <a href="index">退出</a> <span class="shu">|</span>

			<a>登录人：${sessionScope.name}</a>


		</div>
	</div>
</div>
	<!-- 文字条 -->

		


<div style="padding: 20px; background-color: #F2F2F2;">
	<div class="layui-row layui-col-space15">

		<div class="layui-col-md12">
			<div class="layui-card" style="height: 70px;">
				<div class="layui-card-header">

					<img src="../static/img/mi.jpg"  style="float: left">
					<h1 style="font-size:40px;float: left; margin:10px 0 0 0">上机考试管理</h1>
					<br/>

				</div>

			</div>
		</div>
		<div style="margin:20px 0 0 0">
			<p class="exam-info" style="padding:10px;background-color:#555;color:#FFF;border-radius:6px;font-size:14px;margin:20px 0 0 0">
				完成学生名单的导入和修改后，
				<a class="btn" href="teacher_exam_modify1" style="display:inline-block;padding:4px 14px;line-height:20px;color:#333333;background-color:#f5f5f5;text-decoration:none">
					<i class="icon-edit"></i>
					返回编辑
				</a>
			</p>
		</div>


		<div class="layui-col-md6" style="width: 100%;">
			<div class="layui-card" >
				<div class="layui-card-header"><strong style="font-size:20px">导入学生</strong></div>
				<div class="layui-card-body">
					<form action="addstudent" class="exam-form"  method="post" style="padding:20px;background-color:#EEE;margin:0 0 20px">
						<h3>添加单个学生</h3>

						<input name="stu_id" placeholder="学号*" size="20" type="text">
						<input name="stu_name" placeholder="姓名*" size="20" type="text">
						<input name="stu_class" placeholder="班级*" size="20" type="text">
						<input name="stu_exam"  value="${sessionScope.eid}"  type="hidden">
						<input class="btn" value="添加" type="submit">
					</form>

					<div class="container-fluid">
						<div class="row-fluid" style="">

							<form action="setsize" method="post" style="padding:20px;float:left;background-color:#EEE;margin:0 0 20px">
								分页大小
								<input name="size" value="${sessionScope.size}" placeholder="30" type="text">
								<input value="设置" type="submit">
							</form>


							<form action="setpage" method="post" style="padding:20px;background-color:#EEE;margin:0 0 20px;float:right">
								<a href="setpage?page=0" >第一页</a>
								<a href="setpage?page=${sessionScope.page-1}" >上一页</a>
								<input name="page" placeholder="0/${sessionScope.totalPages+1}" type="text">
								<input type="submit" value="确认"></input>
								<a href="setpage?page=${sessionScope.page+1}" >下一页</a>
								<a href="setpage?page=${sessionScope.totalPages}" >最后一页</a>

							</form>
							<table class="table" border="1" style="width:100%;padding:20px;background-color:#EEE;margin:0 0 20px;">
								<thead>
								<tr>
									<th>学号</th>
									<th>姓名</th>
									<th>班级</th>
									<th>操作</th>
								</tr>
								</thead>
								<tbody>
								<c:forEach items="${requestScope.students}" var="student">
									<tr>
										<th>${student.stu_id}</th>
										<th>${student.stu_name}</th>
										<th>${student.stu_class}</th>

										<th>
											<span  style="background-color:blue;"  class="label label-info"><a href="delstudent?stu_id=${student.stu_id}" style="color: white;text-decoration:none;">删除</a></span>
										</th>
									</tr>
								</c:forEach>
								</tbody>
							</table>
							<form action="setpage" method="post" style="padding:20px;background-color:#EEE;margin:0 0 20px;float:right">
								<a href="setpage?page=0" >第一页</a>
								<a href="setpage?page=${sessionScope.page-1}" >上一页</a>
								<input name="page" placeholder="0/0" type="text">
								<input type="submit" value="确认"></input>
								<a href="setpage?page=${sessionScope.page+1}" >下一页</a>
								<a href="setpage?page=${sessionScope.totalPages}" >最后一页</a>

							</form>
							<br/>
							<br/>
							<br/>
							<form class="exam-from" action="uploadExcel"  method="post" enctype="multipart/form-data" style="padding:20px;background-color:#EEE;margin:0 0 20px">
								<h3>批量导入学生名单</h3>
								<input name="file" type="file">
								<input class="btn" style="background-color:#006dcc;color:#eee" value="导入" type="submit">
							</form>

						</div>


					</div>

				</div>


				</div>
			</div>
		</div>
	</div>
</div>
	
	

</body>

</html>
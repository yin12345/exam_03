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
					<h1 style="font-size:40px;float: left">上机考试管理</h1>

				</div>

			</div>
		</div>


		<div class="layui-col-md6" style="width: 100%;">
			<div class="layui-card" >
				<div class="layui-card-header"><strong style="font-size:20px">解绑操作</strong></div>
				<div class="layui-card-body">

					<form action="search" class="exam-form" enctype="multipart/form-data" method="post" style="padding:20px;background-color:#EEE;margin:0 0 20px">
						<h3>按学生查找已登录信息</h3>
						<input name="stu_id" placeholder="学号*" size="20" type="text">
						<input name="stu_name" placeholder="姓名*" size="20" type="text">
						<input name="stu_class" placeholder="班级*" size="20" type="text">
						<input class="btn" value="查找" type="submit">
					</form>

					<form action="searchip" class="exam-form" enctype="multipart/form-data" method="post" style="padding:20px;background-color:#EEE;margin:0 0 20px">
						<h3>按ip地址查找已登录信息</h3>
						<input name="stu_ip" placeholder="ip地址" size="20" type="text">
						<input class="btn" value="查找" type="submit">
					</form>
					<c:if test="${sessionScope.search!=null}">

						<table class="table" border="1" style="width:100%; margin:0px 20px 0px 20px; ">
							<thead>
							<tr>
								<th>学号</th>
								<th>姓名</th>
								<th>班级</th>
								<th>ip地址</th>
								<th>操作</th>

							</tr>
							</thead>
							<tbody>
							<th>${sessionScope.search.stu_id}</th>
							<th>${sessionScope.search.stu_name}</th>
							<th>${sessionScope.search.stu_class}</th>
							<th>${sessionScope.search.stu_ip}</th>
							<th>
								<c:if test="${sessionScope.search.stu_ip!=null}">
									<form class="exam-from" action="unlock?stu_id=${sessionScope.search.stu_id}&stu_name=${sessionScope.search.stu_name}&stu_class=${sessionScope.search.stu_class}"  method="post" style="display: inline">
										<input class="btn" style="color:black" value="解锁" type="submit">
									</form>
								</c:if>
								<c:if test="${sessionScope.search.stu_ip==null}">
									<form class="exam-from" action="#"  method="post" style="display: inline">
										<input class="btn" style="color:black" value="已解锁" type="submit" disabled="disabled">
									</form>
								</c:if>
							</th>
							</tbody>
						</table>

					</c:if>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>
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
				<div class="layui-card-header"><strong style="font-size:20px">通知信息</strong></div>
				<div class="layui-card-body">

					<form action="addmessage" class="exam-form" enctype="multipart/form-data" method="post" style="padding:20px;background-color:#EEE;margin:0 0 20px">
						<h3>新增通知信息</h3>
						<input name="message" placeholder="通知消息内容"  type="text">
						<input class="btn" value="添加" type="submit">
					</form>
					<div style="padding:20px;background-color:#EEE;margin:0 0 20px;">
						<h3>新增通知信息</h3>
						<table class="table" border="1" style="width:100%;padding:20px;">
							<thead>
							<tr>
								<td style="float:left">通知内容</td>
								<td>操作</td>
							</tr>
							</thead>
							<tboby>
								<c:forEach items="${sessionScope.messages}" var="message">
									<tr>
										<td>${message}</td>
										<td>
											<a href="delmessage?message=${message}">
												删除
											</a>
										</td>
									</tr>

								</c:forEach>

							</tboby>
						</table>
					</div>


				</div>
			</div>
		</div>
	</div>
</div>

</body>

</html>
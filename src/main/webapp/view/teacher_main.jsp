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
				<div class="dropdown-content" style="left:0; z-index: 999">
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

		<div class="layui-col-md6" style="width: 33%;">
			<div class="layui-card" style="height: 300px;">
				<div class="layui-card-header"><strong style="font-size:20px">考前操作</strong></div>
				<div class="layui-card-body">
					<ul>
						<li>1.可以新建考试：指定考试名称、开始时间等     </li>
						<li>2.可以编辑自己创建的、未开始的考试，除了修改考试信息，还可以：    </li>
						<ul>
							<li>上传试卷</li>
							<li>学生名单导入</li>
							<li>开启考试</li>
						</ul>

					</ul>

				</div>
			</div>
		</div>

		<div class="layui-col-md6" style="width: 33%;">
			<div class="layui-card" style="height: 300px;">
				<div class="layui-card-header"><strong style="font-size:20px">考中管理</strong></div>
				<div class="layui-card-body">
					<ul>
						<li>可以查看考试情况   </li>
						<li>可以管理学生信息，手工添加个别学生信息   </li>
						<li>可以解除学生登录锁定</li>
						<li>可以添加或删除通知信息</li>
					</ul>
				</div>
			</div>
		</div>

		<div class="layui-col-md6" style="width: 33%;" >
			<div class="layui-card" style="height: 300px;">
				<div class="layui-card-header"><strong style="font-size:20px">考后操作</strong></div>
				<div class="layui-card-body">
					<ul>
						<li>主考教师（考试创建者）可以结束考试</li>
						<li>主考教师可以打包下载学生提交文件</li>
						<li>如果管理员设置，主考教师可以清除和删除考试</li>
					</ul>
				</div>
			</div>
		</div>

	</div>
</div>


</body>

</html>
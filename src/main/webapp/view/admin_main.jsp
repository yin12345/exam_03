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
			<a href="admin_main">首页</a>  <span class="shu">|</span>
			<a href="admin_manage_jsp">教师管理</a>  <span class="shu">|</span>
			
				<a href="admin_clear_jsp">考试清理</a>
				
			<span class="shu">|</span>
			<a href="admin_conf_jsp">系统配置</a>

		</div>
		<div class="righta">


			<a href="admin_fix">修改口令</a> <span class="shu">|</span> <a
				href="admin_login">退出</a> <span class="shu">|</span>

			<a>登录人：${sessionScope.name}</a>


		</div>
	</div>
</div>
<!-- 文字条 -->
<div class="box1 clear">
	<div class="mi" style="font-size:40px">
		<img src="../static/img/mi.jpg">
		上机考试管理
	</div>


</div>


<!-- 小米明星单品 -->

<div style="padding-right:20px; padding-left:20px">
	<div style="width:100%;margin-left:0; float:left; display:block; min-height:30px; margin-left:2.564%;">
		<div style="width:31.9%;float:left;bord:1">
			<strong style="font-size:20px">教师管理</strong>
			<br>
			<br>
			<ul>
				<li>1.可以对教师用户进行增删改查操作    </li>
				<li>2.可以指定特定教师作为系统管理员    </li>
				

			</ul>
		</div>

		<div style="width:30.9%;float:left;margin-left:2.56%">
			<strong style="font-size:20px">考试清理</strong>
			<br>
			<br>
			<ul>
				<li>清除考试的相关数据，包括 : </li>
				<ul>
				  <li>1.数据库中的学生信息</li>
				    <li>2.登录ip地址绑定信息</li> 
				      <li>3.提交信息</li>
				        <li>4.文件系统中的提交文件等</li>
				</ul>
			
			</ul>
		</div>

		<div style="width:31.9%;float:left;margin-left:2.56%">
			<strong style="font-size:20px">系统配置</strong>
			<br>
			<br>
			<ul>
				<li>设置一些全局性的系统选项，包括</li>
				<ul>
				  <li>1.后台任务的时间周期</li>
				<li>2.分页查询时的每页记录数</li>
				<li>3.手动开启考试的时间阈值</li>
				<li>4.学生上传文件字节数的有效范围</li>
				<li>5.可以指定是否允许主考教师清理和删除考试</li>
				</ul>
			</ul>
		</div>

	</div>
</div>



</body>

</html>
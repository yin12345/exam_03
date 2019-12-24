<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	box-shadow: 0px 3px 5px 0px rgba(0, 0, 0, 0.2);
}

.dropdown-content a {
	color: black;
	height: 35px;
	text-decoration: none;
	display: block;
}

.dropdown-content a:hover {
	background-color: #f1f1f1
}

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
			<div class="lefta"
				style="font-size: 20px; color: #eee; display: block; float: left">
				<li style="float: left">上级考试系统 <span class="shu">|</span>
				</li> <a href="admin_main">首页</a> <span class="shu">|</span> <a
					href="admin_manage_jsp">教师管理</a> <span class="shu">|</span> <a
					href="admin_clear_jsp">考试清理</a> <span class="shu">|</span> <a
					href="admin_conf_jsp">系统配置</a>

			</div>
			<div class="righta">


				<a href="admin_fix">修改口令</a> <span class="shu">|</span> <a href="login">退出</a>
				<span class="shu">|</span> <a>登录人：${sessionScope.name}</a>


			</div>
		</div>
	</div>
	<!-- 文字条 -->
	<div class="box1 clear">
		<div class="mi" style="font-size: 40px">
			<img src="../static/img/mi.jpg"> 上机考试管理
		</div>
		
	</div>
<form action="admin_revise_reaction" method="post" class="form-horizontal"
			style="padding: 20px; background-color: #EEE">
			<h3>教师工号</h3>
			<input name="id" value="<%=request.getParameter("id") %>" type="text" />
			<h3>教师姓名</h3>
			<input name="name" type="text"
				value="<%=request.getParameter("name")%>" />
			<h3>密码</h3>
			<input name="pass" placeholder="新密码" type="text" />如不需要修改密码请留空<br>
			 <input
				type="checkbox"
				onclick="javascript:document.getElementById('checkbox').value=this.checked;" />

			<input type="hidden" name="admin" value="false" id="checkbox" />管理员<br>
			<input type="submit" value="确认提交">
		</form>


</body>
</html>
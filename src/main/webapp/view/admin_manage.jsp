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
<div class="container">
<form action="admin_add_teacher" method="post" class="form-horizontal" style="padding:20px;background-color:#EEE;">
  <h3>添加教师</h3>
  <input type="text"name="id" placeholder="教师工号*"size="20">
  <input type="text"name="name" placeholder="教师姓名*"size="20">
  <input type="text"name="pass" placeholder="教师密码*"size="20">
   <input
				type="checkbox"
				onclick="javascript:document.getElementById('checkbox').value=this.checked;" />

			<input type="hidden" name="admin" value="false" id="checkbox" />管理员&nbsp;
  <input type="submit"name="action" class="btn" value="添加">
  
</form>
<table class="table" border="1" style="width:100%; margin:0px 20px 0px 20px; ">
		<thead>
		<tr>
			<th class="span3">教师工号</th>
			<th class="span3">教师姓名</th>
			<th>是否是管理员</th>
			
		</tr>
		</thead>
		<tbody>
		 <c:forEach items="${sessionScope.teachers}" var="teacher">
			 <tr>
				 <th>${teacher.id}</th>
				 <th>${teacher.name}</th>
				 <th>${teacher.admin}</th>
				  
				 
		<th><span   class="label label-info"><a href="admin_revise_teacher_jsp?id=${teacher.id}&&name=${teacher.name}&&pass=${teacher.pass}" style="color: red;">修改</a></span>
			<span   class="label label-info"><a href="admin_delete_teacher?id=${teacher.id}" style="color: red;">删除</a></span>	
				 </th>
			 </tr>
		 </c:forEach>
		</tbody>
	</table>
	<form action="admin_teacher_setpage" class="center" method="post" style="padding:20px;background-color:#EEE;margin:0 0 20px;float:right">
	    <a href="admin_teacher_setpage?page=0" >第一页</a>
	    <a href="admin_teacher_setpage?page=${sessionScope.current_page-1}" >上一页</a>
	    <input name="page" placeholder="0/0" type="text">
	    <input type="submit" value="确认"></input>
	    <a href="admin_teacher_setpage?page=${sessionScope.current_page+1}" >下一页</a>
	    <a href="admin_teacher_setpage?page=-2" >最后一页</a>
	   
	   </form>
</div>
<script type="text/javascript" src="../static/js/jquery-1.8.3.min.js" charset="UTF-8"></script>
<script type="text/javascript" src="../static/js/bootstrap.min.js"></script>
</body>
</html>
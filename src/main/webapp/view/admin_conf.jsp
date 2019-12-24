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

<form action="admin_conf_reaction" method="post" class="form-horizontal" style="padding:20px;background-color:#EEE">
	<h3>后台任务时间周期，指定扫描考试的间隔时间，单位为分钟</h3>

	<input name="dutycycle"  type="text" value="${applicationScope.dutycycle }">
	<h3>分页查询时的每页记录数</h3>
	<input name="pagecount"  type="text" value ="${applicationScope.pagecount }">
	<h3>手动开启考试的时间阈值,指定手工开起考试的最大提前量，单位为分钟</h3>
	<input name="time"  type="text" value ="${applicationScope.time }" >
	<h3>学生上传文件字节数的最大有效范围，高于此值发出警告</h3>
	<input name="high"  type="text" value="${applicationScope.high }">
	<h3>是否允许主考教师清理和删除考试</h3>
	<h3>当前设置为：
		<c:if test="${applicationScope.allowed==true }">允许</c:if>
		<c:if test="${applicationScope.allowed==false }">不允许</c:if>
	</h3>
	<input type="checkbox"
		   onclick="javascript:document.getElementById('checkbox').value=this.checked;" />

	<input type="hidden" name="allowed" value="false" id="checkbox" /><br>
	<input type="submit" value="确认提交">
	</form>

</body>
</html>
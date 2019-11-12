<%@ page import="java.util.Date" %>
<%@ page language="java"
		 pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>上机考试系统</title>
<link rel="stylesheet" href="../static/css/index1.css">
	<link href="../static/css/bootstrap.min.css" rel="stylesheet" media="screen">
	<link href="../static/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">



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
					<a href="teacher_manage_unlock" >解除绑定</a>
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
</div>
<!-- 文字条 -->
<div class="box1 clear">
	<div class="mi" style="font-size:40px">
		<img src="../static/img/mi.jpg">
		上机考试管理
	</div>


</div>

<div class="container">
	<form action="addexam" method="post" class="form-horizontal" style="padding:20px;background-color:#EEE;">

			<h3>添加考试</h3>
			<input name="ename" placeholder="考试名称*" size="20" type="text">
				<div class="controls input-append date form_datetime" style="display:inline-block;margin:0px 0px 0px 0px" data-date="2019-11-5T05:25:07Z" data-date-format="yyyy-mm-dd hh:ii" data-link-field="dtp_input1">
					<input name="starttime" size="16" type="text" placeholder="考试时间*" value="" readonly style="background-color:#FFFFFF ">
					<span class="add-on"><i class="icon-remove"></i></span>
					<span class="add-on"><i class="icon-th"></i></span>
				</div>
		<div  style="display:inline-block">
				<input type="hidden" id="dtp_input1" value="" /><br/>

			<input name="autostart" value="1" type="checkbox" style="display:inline-block">
			自动开始
			<input class="btn" value="添加" type="submit">
		</div>

	</form>
	<table class="table" border="1" style="width:100%; margin:0px 20px 0px 20px; ">
		<thead>
		<tr>
			<th class="span3">考试名称</th>
			<th class="span3">考试时间</th>
			<th>创建人</th>
			<th>上传试卷</th>
			<th>自动开始</th>
			<th>进行中</th>
			<th>已结束</th>
			<th>已归档</th>
			<th>已清理</th>

		</tr>
		</thead>
		<tbody>
		 <c:forEach items="${sessionScope.exams}" var="exam">
			 <tr>
				 <th>${exam.ename}</th>
				 <th>${exam.starttime}</th>
				 <th>${exam.tname}</th>
				 <th>${exam.exampaper}</th>
				 <th>${exam.autostart}</th>
				 <th>${exam.runing}</th>
				 <th>${exam.finished}</th>
				 <th>${exam.archived}</th>
				 <th>${exam.cleaned}</th>
				 <th>
					 <c:if test="${exam.finished==0}">
						<span   class="label label-info"><a href="teacher_exam_modify?eid=${exam.eid}&ename=${exam.ename}&starttime=${exam.starttime}&autostart=${exam.autostart}" style="color: white;">修改</a></span>
					 </c:if>
				 </th
			 </tr>
		 </c:forEach>
		</tbody>
	</table>

	<div style="hight:50%">
		<br>
	</div>

</div>

<script type="text/javascript" src="../static/js/jquery-1.8.3.min.js" charset="UTF-8"></script>
<script type="text/javascript" src="../static/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../static/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="../static/js/bootstrap-datetimepicker.fr.js" charset="UTF-8"></script>
<script type="text/javascript">
    $('.form_datetime').datetimepicker({
        language:  'zn',
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        forceParse: 0,
        showMeridian: 1
    });


</script>
</body>

</html>
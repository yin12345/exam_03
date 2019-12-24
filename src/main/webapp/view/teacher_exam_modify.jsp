<%@ page language="java" import="java.util.*"
		 pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>上机考试系统</title>
	<link rel="stylesheet" href="../static/css/index1.css">
	<link href="../static/css/bootstrap.min.css" rel="stylesheet" media="screen">
	<link href="../static/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
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
				<div class="dropdown-content" style="left:0; z-index: 999" >
					<a href="teacher_manage_summary">考试概况</a>
					<a href="teacher_manage_notify">学生信息</a>
					<a href="teacher_manage_unlock">解除绑定</a>
					<a href="teacher_manage_student">通知管理</a>
				</div>
			</div><span class="shu">|</span>
			<a href="after">考后操作</a>

		</div>
		<div class="righta">

			<a href="fix1">修改口令</a> <span class="shu">|</span>
			</span> <a href="index">退出</a> <span class="shu">|</span>

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
				<div class="layui-card-header"><strong style="font-size:20px">修改考试</strong></div>
				<div class="layui-card-body">
					<form action="updateexam" method="post" class="form-horizontal" style="padding:20px;background-color:#EEE">
						<h3>编辑考试信息</h3>
						<input name="eid"  value="${sessionScope.exam.eid}" type="hidden">
						考试名称：
						<input name="ename"  value="${sessionScope.exam.ename}" type="text" >
						考试时间：
						<div class="controls input-append date form_datetime" style="display:inline-block;margin:0px 0px 0px 0px" data-date="2019-11-5T05:25:07Z" data-date-format="yyyy-mm-dd hh:ii" data-link-field="dtp_input1">
							<input name="starttime" size="16" type="text" placeholder="考试时间*" value="<fmt:formatDate value="${sessionScope.exam.starttime}" pattern="yyyy-MM-dd HH:mm" />" readonly style="background-color:#FFFFFF ">
							<span class="add-on"><i class="icon-remove"></i></span>
							<span class="add-on"><i class="icon-th"></i></span>
						</div>
						<input type="hidden" id="dtp_input1" value="" />

						<div style="left: auto">

							<label class="checkbox">
								<c:if test="${sessionScope.exam.autostart==1}">
									<input name="autostart" value="1" type="checkbox" checked="true">
								</c:if>
								<c:if test="${sessionScope.exam.autostart==0}">
									<input name="autostart" value="1" type="checkbox" >
								</c:if>
								自动开始
							</label>

						</div>
						<div style="left: auto">

							<label class="checkbox">
								<input class="btn" style="background-color:#006dcc;color:black" name="action" value="修改" type="submit">
							</label>

						</div>

					</form>

					<form class="exam-from" action="teacher_exam_upload" enctype="multipart/form-data" method="post" style="padding:20px;background-color:#EEE;margin:20px 0 0 0">
						<h3>上传试卷</h3>
						${sessionScope.exam.exampaper}
						<c:if test="${not empty sessionScope.exam.exampaper}">
							<p class="exam-info" style="padding:10px;background-color:#555;color:#FFF;border-radius:6px;font-size:14px;margin:0 0 10px">
								上传成功，再次上传可替换文件
								<a class="btn" href="download?filename=${sessionScope.exam.exampaper}" style="display:inline-block;padding:4px 14px;line-height:20px;color:#333333;background-color:#f5f5f5;text-decoration:none">
									<i class="icon-edit"></i>
									下载查看
								</a>
							</p>
						</c:if>
						<input name="eid" value="${sessionScope.exam.eid}" type="hidden">
						<input name="paper" type="file">
						<input class="btn" style="background-color:#006dcc;color:black" value="上传" type="submit">
					</form>
					<form class="exam-from" action="teacher_student"  method="post" style="padding:20px;background-color:#EEE;margin:20px 0 0 0">
						<h3>导入学生名单</h3>
						<p>目前设定参加此次考试的学生人数：${sessionScope.studentNum}</p>
						<input name="eid" value="${sessionScope.exam.eid}" type="hidden">
						<input class="btn" style="background-color:#006dcc;color:black" value="继续导入" type="submit">
					</form>

					<form class="exam-from" action="startexam?eid=${sessionScope.exam.eid}&started=1"  method="post" style="padding:20px;background-color:#EEE;margin:20px 0 0 0">
						<h3>开启考试</h3>
						<c:if test="${ empty sessionScope.exam.exampaper}">
							<p style="color:#c09853">尚未上传试卷</p>
						</c:if>
						<c:if test="${ sessionScope.studentNum==0}">
							<p style="color:#c09853">尚未导入学生</p>
						</c:if>
						<c:choose>
							<c:when test="${sessionScope.studentNum==0 || empty sessionScope.exam.exampaper || sessionScope.exam.started==1}">
								<button class="btn" style="background-color:#006dcc;color:black" type="button" disabled="disabled">
									<i class="icon-off"></i>
									开启
								</button>
							</c:when>
							<c:otherwise>
								<button class="btn" style="background-color:#006dcc;color:black" type="submit" >
									<i class="icon-off"></i>
									开启
								</button>
							</c:otherwise>
						</c:choose>

						<c:if test="${sessionScope.exam.started==1}">
							<p class="exam-info" style="padding:10px;background-color:#555;color:#FFF;border-radius:6px;font-size:14px;margin:0 0 10px">
								考试已开启
							</p>
						</c:if>

						<c:if test="${sessionScope.exam.dateout==1}">
							<p class="exam-info" style="padding:10px;background-color:#555;color:#FFF;border-radius:6px;font-size:14px;margin:0 0 10px">
								考试时间已过，请修改考试时间
							</p>
						</c:if>

					</form>


				</div>
			</div>
		</div>
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
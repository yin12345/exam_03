
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: AlphaGo
  Date: 2019/11/22
  Time: 18:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>上机考试系统</title>
    <link rel="stylesheet" href="../static/css/index1.css">
    <script type="text/javascript">
        window.onload = function () {
            var features='<%=(Integer)session.getAttribute("features")%>';
            if(features!=1){
                document.form1.submit();
            }
        }
    </script>
    <script type="text/javascript" src="../static/js/validate.js"></script>
    <script type="text/javascript">
        function validate(){


            var zhanghao = document.getElementById("Zhanghao");
            var mima = document.getElementById("Mima");

            if(is_empty(zhanghao.value)){
                alert("用户名不允许为空");
                return false;
            }else
            {

            }

            if(is_empty(mima.value)){
                alert("密码不允许为空");
                return false;
            }

            //加*号以外的字符 返回true
            if(is_illegal_char_reg(zhanghao.value)){
                alert("不能输入除*号 以外的特殊字符");
                return false;
            }


        }

    </script>

    <link rel="stylesheet" href="../static/css/index1.css">
    <link rel="stylesheet" type="text/css" href="../static/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="../static/css/student_loginCSS.css">
</head>
<body>

<%
    String examname = (String) session.getAttribute("examname");
%>
<!-- 顶部条 -->
<div class="box">
    <div class="cen clear">
        <div class="lefta" style="font-size:20px;color:#eee;display:block;float:left">
            <li style="float:left">
                上级考试系统  <span class="shu">|</span>
            </li>
            <a href="on_load_action">学生登录</a>  <span class="shu">|</span>
            <a href="index">教师登录</a>  <span class="shu">|</span>
            <a href="admin_login">管理员登录</a>

        </div>
    </div>
</div>

<div id="login">
    <h1>教师登陆</h1>
    <form action="login" method="post" onsubmit="return validate()">
        <input  type="text" required="required" placeholder="账户" name="name"/>
        <input  type="text" required="required" placeholder="密码" name="pass"/>
        <button class="but" type="submit">登录</button>
    </form>
</div>

</body>
</html>

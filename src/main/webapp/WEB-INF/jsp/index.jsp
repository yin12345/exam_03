<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录</title>
    <link rel="stylesheet" href="../static/css/index2.css">
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

</head>
<body>

<!-- 内容 -->
<div class="middle">
    <a href="" class="aaa"></a>
    <div class="deng clear">
        <div class="denglu">
            <div class="first">
                <a href="javascript:;" class="center">教师登录</a>

            </div>
            <div class="erweima">
                <div class="second">
                    <div class="mima">
                        <form action="login" method="post" onsubmit="return validate()">
                            <input type="text" id="Zhanghao" name="name" placeholder="账户"
                                   class="zh">
                            <input type="password" id="Mima" name="pass"
                                   placeholder="密码" class="mm">
                            <input type="image"
                                   src="../static/img/dl.jpg" class="tp">


                        </form>
                    </div>

                </div>
            </div>

        </div>
    </div>
</div>





</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: ThinKPad
  Date: 2017/6/18
  Time: 13:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/reset.css" />
    <link rel="stylesheet" type="text/css"  href="${pageContext.request.contextPath}/static/css/login.css" />
</head>
<body>
<div class="page">
    <div class="loginwarrp">
        <div class="logo">管理员登陆</div>
        <div class="login_form">
            <form id="Login" name="Login" method="post"  action="${pageContext.request.contextPath}/user/Login.do">
                <li class="login-item">
                    <span>用户名：</span>
                    <input type="text" name="username" class="login_input">
                </li>
                <li class="login-item">
                    <span>密　码：</span>
                    <input type="password" name="password" class="login_input">
                </li>
                <li class="login-item verify">
                    <span>验证码：</span>
                    <input type="text" id="code_input" value="" class="login_input verify_input"/>
                    <div id="v_container" border="0" style="width: 200px"></div>
                </li>
                <%--<img src="static/images/verify.PNG" border="0" class="verifyimg" />--%>
                <div class="clearfix"></div>
                <li class="login-sub">
                    <input type="button" id="Submit" name="Submit" value="登录" />
                </li> </form>
                <li class="login-sub">
                    <a href="${pageContext.request.contextPath}/user/Register.do"><input type="button" value="注册" /></a>
                </li>

        </div>
    </div>
</div>
</body>
<script src="${pageContext.request.contextPath}/static/js/gVerify.js"></script>
<script>
    var verifyCode = new GVerify('v_container');
    document.getElementById('Submit').onclick = function(){
        var loginForm = document.getElementById('Login');
        var go=document.getElementById('Submit');
        go.onclick = function(){
            var res = verifyCode.validate(document.getElementById("code_input").value);
            if(res){
                loginForm.submit();
            }else{
                alert("验证码错误");
            }
        }
    }
</script>
</html>

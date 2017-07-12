<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2017/7/7
  Time: 16:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.4.4.min.js"></script>
<body>
<script type="text/javascript">
    function sendJson() {
        var res = document.getElementById("res").value;

        /*请求json响应json*/
        $.ajax({
            type: "post",
            url: "${pageContext.request.contextPath }/tran/tran.do",
            contentType: "application/json;charset=utf-8",
            data:{"res":res},
            success:function (data) {
                alert("成功");
            }
        });
    }
</script>
    <input type="text" name="res" id="res">
    <input type="submit" value="diyizhong">
    <input type="button" value="提交" onclick="sendJson()">
<h2>${res}</h2>



</body>
</html>

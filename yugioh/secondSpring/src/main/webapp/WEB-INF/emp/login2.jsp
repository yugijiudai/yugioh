<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/2/9
  Time: 20:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form method="post" action="http://localhost:8080/se/emp/register.do">
        <input name="token" value="${token}"/>
        <input name="url" value="${url}"/>
        <input name="name"/>
        <input type="submit" value="注册">
    </form>
<script>
    var tip = '${repeat}';
    if (tip) {
        alert(tip);
    }
</script>
</body>
</html>

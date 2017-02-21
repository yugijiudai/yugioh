<%@ page import="com.yugi.util.TokenUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
<script>

</script>
</head>
<body>

${pageContext.session.id},${requestScope['javax.servlet.forward.request_uri']}${tokenTime},${tokenValue}
    <form id="form" method="post" action="http://localhost:8080/se/emp/register.do">
        <input name="token" value="<%=TokenUtil.getTokenValue(request)%>">
        <%--<input name="token" value="${pageContext.session.id},${requestScope['javax.servlet.forward.request_uri']}${tokenTime},${tokenValue}">--%>
        <input name="name"/>
        <input type="submit"  value="注册">
    </form>
<script>
    var tip = '${repeat}';
    if (tip) {
        alert(tip);
    }

</script>
</body>
</html>

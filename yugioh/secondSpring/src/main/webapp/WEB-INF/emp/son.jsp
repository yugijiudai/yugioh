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
  <title></title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
</head>
<body>
<script type="text/javascript">
function mo() {
//    可直接访问父级页面控件
//    window.parent.opener.document.getElementById("A431").value=4;
    if (navigator.userAgent.indexOf("Chrome") > 0) {
//    传递值 访问父级页面的函数(此方法只能用在调用window.open)
        window.opener.test();
    }
    myReturnValue({
        name: '龟爷',
        id: 1
    });
    //关闭子窗口
    window.close();
}

</script>
<input type="button" id="btn" value="sss" onclick="mo();"/>
</body>
</html>

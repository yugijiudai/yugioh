<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
哈哈哈
<img src="${pageContext.request.contextPath}/dogge.gif?v=123"><br>
<script src="${pageContext.request.contextPath}/js/jquery-1.11.1.js?v=123"></script>
<script>
    (function () {
        if (window.localStorage) {
            var key = localStorage.getItem("key");
            if (key) {
                console.log(key);
//                var b = eval(key);
                var $_ = new Function('return ' + key)();
                console.log($_("img"));
            }
            else {
                console.log($);
                localStorage.setItem("key", $);
            }
        }
        $.ajax({
            type: "GET",
            <%--url: "${pageContext.request.contextPath}/js/jquery-1.11.1.js",--%>
            url: "${pageContext.request.contextPath}/js/test.js",
            dataType: "script",
            cache: false,
            ifModified: true,
            success: function (data) {
                var bb = new Function('return ' + data)();
                bb.abc();
            }
        });

        var saveDataAry = [];
        var data1 = {"id": "2", "name": "gr", "dept": {"deptno": 4343}};
        var data2 = {"id": "5", "name": "sfe"};
        saveDataAry.push(data1);
        saveDataAry.push(data2);
        var emp = JSON.stringify(saveDataAry);
        console.log(emp);
        $.ajax({
            type: "POST",
            url: "/second/emp/first.do",
            dataType: "json",
            contentType: "application/json;charset=utf-8",
            data: emp,
            success: function (data) {
            }
        });


    })();
</script>
<form action="${pageContext.request.contextPath}/emp/third.do" method="post">
    <input name="emp[0].id">
    <input name="emp[0].name">
    <input name="emp[1].id">
    <input name="emp[1].name">
    <input name="emp[1].dept.deptno">
    <%--initBinder绑定成emem来传参数--%>
    <input name="emem.id">
    <input name="emem.name">
    <input name="emem.dept.deptno">
    <%--<input name="id">--%>
    <%--<input name="name">--%>
    <%--<input name="dept.deptno">--%>
    选择日期：<input value="2016-04-01" name="birthday"/>
    <input type="submit" value="提交">
</form>


<form action="${pageContext.request.contextPath}/emp/third.do" method="post">
    <input name="id">
    <input name="name">
    <input name="dept.deptno">
    <input type="submit" value="提交">
</form>


<a id="date" class="textLink" href="${pageContext.request.contextPath}/emp/date.do?value=2016:04:01">Date</a>
</body>
</html>

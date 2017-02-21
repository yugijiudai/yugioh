<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="<%=request.getContextPath()%>/dwr/engine.js"></script>
    <script src="<%=request.getContextPath()%>/dwr/util.js"></script>
    <script src="<%=request.getContextPath()%>/dwr/interface/helloService.js"></script>
</head>
<body>
<script>
    function dw() {
//        helloService.load(function (data) {
//            alert(data);
//        });
//        helloService.sayHello("ffff", function (data) {
//            alert(data);
//        });


        /**
         * 使用下面的方法就不用导入对应的js
         */
        dwr.engine._execute('dwr', 'userDao', 'sayHello', ['wtf!', function (data) {
            alert(data);
        }]);

    }


</script>
<input type="button" value="sub" onclick="dw();">
</body>
</html>

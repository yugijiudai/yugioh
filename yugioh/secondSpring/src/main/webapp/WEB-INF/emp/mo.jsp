<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title></title>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
</head>
<script>
function openWin(url) {
    window.open(url, "", "width=760, height=640, top=0, left=0, toolbar=no, menubar=no, scrollbars=no, location=yes, resizable=no, status=no");
}
function chromeMo() {
    myShowModalDialog("son.do", 500, 300, function (v) {
//        console.log(v);
        alert(v);
    });
}

function test() {
    alert('呵呵');
}


</script>
<body>
<input type="button" value="你妹的模态窗" onclick="openWin('son.do');"/>
<input type="button" value="你妹的模态窗3" onclick="chromeMo();"/>
<input id="A431"/>

</body>
</html>

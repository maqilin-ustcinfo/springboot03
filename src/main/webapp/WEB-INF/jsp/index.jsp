<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<h2>Hello World!</h2>
    ${value} web-inf/jsp/index.jsp
    <div>
        <a href="${pageContext.request.contextPath }/file/gotoUpload">进入文件上传下载页面</a>
    </div>
</body>
</html>


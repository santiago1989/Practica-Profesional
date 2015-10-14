<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="comon/head.jsp"/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common/form.css">
<title>Login</title>
</head>
<body>
	<jsp:include page="comon/menu.jsp"/>
	<jsp:include page="comon/alert.jsp"/>
	<div class="head">
		<!-- <img class="bannerHead" alt="fondo" src="${pageContext.request.contextPath}/images/images.jpg"> -->
	</div>
	<div class="parent">
		<form action="${pageContext.request.contextPath}/login.htm" method="post">
			<div class="child">
				<div class="cell">
					<span>User:</span>
					<input name="user" type="text" />
				</div>
				<div class="cell">
					<span>Password:</span>
					<input name="password" type="password"/>
				</div>
				<div class="cell">
					<input value="Login" type="submit"/>
				</div>
				<div class="cell">
					<span class="error">${errorMessage}</span>
				</div>
			</div>
		</form>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- CSS -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common/common.css">
<!-- MESSI POPUP PLUGIN CSS -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/messi/messi.css">
<!-- MENU CSS -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/responsive-menu/responsive-menu.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/responsive-menu/styles.css">

<link rel="stylesheet" type="text/css" href="https://google-code-prettify.googlecode.com/svn/loader/prettify.css"></head>
<!-- JQUERY -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.11.3.js"></script>
<!-- MESSI POPUP PLUGIN JS-->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/messi/messi.js"></script>
<!-- DOCUMENT READY CUSTOM TO POPUP MANIPULATE -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/script/alert.js"></script>
<!-- MENU  JS -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/responsive-menu/responsive-menu.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/responsive-menu/modernizr/modernizr-custom.js"></script>
<!-- HTML 5 DIV -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/html5shiv/html5shiv.js"></script>
<!-- Load modernizr or html5shiv -->
<script src="//cdn.jsdelivr.net/modernizr/2.8.3/modernizr.min.js" type="text/javascript"></script>
<!-- LOAD MENU JS-->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/script/menu.js"></script>
<script type="text/javascript">
$( document ).ready(function() {
	showPopup();
	loadMenu();
});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
</head>
<body id="body">
	<jsp:include page="comon/menu.jsp"/>
	<jsp:include page="comon/alert.jsp"/>
</body>
</html>
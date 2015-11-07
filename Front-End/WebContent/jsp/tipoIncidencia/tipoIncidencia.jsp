<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.gestor.entidades.TipoIncidencia"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<jsp:include page="../comon/head.jsp"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common/form.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery/jquery-ui.min.css">
<title>Tipo de incidencia</title>
<script type="text/javascript">
$( document ).ready(function() {
	$("#cancelButton").click(function(){
		var href = $("#backLink").attr("href");
		window.location.href = href;
	});
	$("#backButton").click(function(){
		var href = $("#backLink").attr("href");
		window.location.href = href;
	});
});
</script>
</head>
<body>
	<jsp:include page="../comon/menu.jsp"/>
	<jsp:include page="../comon/alert.jsp"/>
		<c:choose>
			<c:when test="${update != true}">
				<form action="${pageContext.request.contextPath}/saveEntity.htm" method="post">
			</c:when>
			<c:otherwise>
				<form action="${pageContext.request.contextPath}/updateEntity.htm" method="post">
			</c:otherwise>
		</c:choose>
		<input name="entityName" type="hidden" value="<%=TipoIncidencia.class.getName()%>"/>
		<div id="frame" class="parent">
			<c:if test="${read || update}">
				<div>
					<!-- Código -->
					<span>ID</span>
					<input type="text" value="${bean.id}" disabled="disabled"/>
					<input type="hidden" name="id" value="${bean.id}"/>
				</div>
			</c:if>
			<div>
				<!-- Nombre -->
				<span>Nombre</span>
				<input type="text" name="nombre" value="${bean.nombre}"/>
			</div>
			<div id="footer">
				<c:if test="${read == null || read == false}">
					<input value="Aceptar" type="submit">
					<input id="cancelButton" value="Cancelar" type="button">
				</c:if>
				<c:if test="${read == true }">
					<input id="backButton" type="button" value="Volver"/>
				</c:if>
				<a id="backLink" style="display: none;" href="${pageContext.request.contextPath}/searchEntity.htm?entityName=<%=TipoIncidencia.class.getName()%>"></a>
			</div>
		</div>	
	</form>
</body>
</html>

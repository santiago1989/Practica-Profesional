<%@page import="com.gestor.entidades.Incidencia"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../comon/head.jsp"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common/form.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery/jquery-ui.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Incidencia</title>
<!-- JQUERY -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-ui.min.js"></script>
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/jquery/dcalendar.picker.js"></script> --%>
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
		<input name="entityName" type="hidden" value="<%=Incidencia.class.getName()%>"/>
		<div id="frame" class="parent" style="width: 340px;margin-left:-205px">
			<c:if test="${update == true || read == true}">
				<input name="update" type="hidden" value="${update}"/>
				<input name="read" type="hidden" value="${read}"/>
				<div>
					<!-- Número de incidencia -->
					<span>N&uacute;mero de incidencia</span>
					<input type="text" name="numero" disabled="disabled" value="${bean.numero}"/>
					<input type="hidden" name="numero" value="${bean.numero}"/>
				</div>
			</c:if>
			<div>
				<c:if test="${update == true || read == true}">
					<span>Creado por:</span>
					<input type="text" value="${bean.owner.nombre}" disabled="disabled"/>
				</c:if>
				<!-- Owner -->
				<input type="hidden" name="owner" value="${user.legajo}"/>
			</div>
			<div>
				<!-- Owner -->
				<span>Asignada a:</span>
				<c:choose>
					<c:when test="${read}">
						<select name="responsable" disabled="disabled">
					</c:when>
					<c:otherwise>
						<select name="responsable">
					</c:otherwise>
				</c:choose>
					<option value="">Seleccionar Opci&oacute;n</option>
					<c:forEach var="item" items="${collectionsBean.responsables}">
						<c:choose>
							<c:when test="${fn:contains(bean.responsable.legajo,item.legajo)}">
								<option value="${item.legajo}" selected="selected">${item.apellido},${item.nombre}</option>
							</c:when>
							<c:otherwise>
								<option value="${item.legajo}">${item.apellido},${item.nombre}</option>
							</c:otherwise>
						</c:choose>	
					</c:forEach>
				</select>
			</div>
			<div>
				<!-- Titulo -->
				<span>Titulo</span>
				<input type="text" name="titulo" value="${bean.titulo}"/>
			</div>
			<div>
				<!-- Tipo Incidencia -->
				<span>Tipo Incidencia</span>
				<c:choose>
					<c:when test="${read}">
						<select name="tipoIncidencia" disabled="disabled">
					</c:when>
					<c:otherwise>
						<select name="tipoIncidencia">
					</c:otherwise>
				</c:choose>
					<option value="">Seleccionar Opci&oacute;n</option>
					<c:forEach var="item" items="${collectionsBean.tiposIncidencia}">
						<c:choose>
							<c:when test="${fn:contains(bean.tipoIncidencia.id,item.id)}">
								<option value="${item.id}" selected="selected">${item.nombre}</option>
							</c:when>
							<c:otherwise>
								<option value="${item.id}">${item.nombre}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
			</div>
			<div>
				<!--Prioridad incidencia-->
				<span>Prioridad Incidencia</span>
				<c:choose>
					<c:when test="${read}">
						<select name="prioridad" disabled="disabled">
					</c:when>
					<c:otherwise>
						<select name="prioridad">
					</c:otherwise>
				</c:choose>
					<option value="">Seleccionar Opci&oacute;n</option>
					<c:forEach var="item" items="${collectionsBean.prioridadIncidencia}">
						<c:choose>
							<c:when test="${fn:contains(bean.prioridad.id,item.id)}">
								<option value="${item.id}" selected="selected">${item.nombre}</option>
							</c:when>
							<c:otherwise>
								<option value="${item.id}">${item.nombre}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
			</div>
			<div>
				<span>Detalle:</span>
				<textarea name="detalle" rows="3" cols="19" style="overflow: scroll;">${bean.detalle}</textarea>
			</div>
			<div>
				<span>Estado:</span>
				<c:choose>
					<c:when test="${read}">
						<select name="estado" disabled="disabled">
					</c:when>
					<c:otherwise>
						<select name="estado">
					</c:otherwise>
				</c:choose>
					<option value="">Seleccionar Opci&oacute;n</option>
					<c:forEach var="item" items="${collectionsBean.estadosIncidencia}">
						<c:choose>
							<c:when test="${fn:contains(bean.estado.id,item.id)}">
								<option value="${item.id}" selected="selected">${item.nombre}</option>
							</c:when>
							<c:otherwise>
								<option value="${item.id}">${item.nombre}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
			</div>
			<div id="footer">
				<c:if test="${read == null || read == false}">
					<input value="Aceptar" type="submit">
					<input id="cancelButton" value="Cancelar" type="button">
				</c:if>
				<c:if test="${read == true }">
					<input id="backButton" type="button" value="Volver"/>
				</c:if>
				<a id="backLink" style="display: none;" href="${pageContext.request.contextPath}/searchEntity.htm?entityName=<%=Incidencia.class.getName()%>"></a>
			</div>
		</div>
	</form>
</body>
</html>

<%@page import="com.gestor.entidades.Incidencia"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	$("#fechaC").datepicker({dateFormat: 'dd/mm/yy'});
	$("#fechaM").datepicker({dateFormat: 'dd/mm/yy'});
});
</script>
</head>
<body>
	<jsp:include page="../comon/menu.jsp"/>
	<jsp:include page="../comon/alert.jsp"/>
	<form action="${pageContext.request.contextPath}/saveEntity.htm" method="post">
		<input name="entityName" type="hidden" value="<%=Incidencia.class.getSimpleName()%>"/>
		<div id="frame" class="parent" style="width: 340px;margin-left:-205px">
			<div style="display: none;">
				<!-- Número de incidencia -->
				<span>N&uacute;mero de incidencia</span>
				<input type="text" name="numero" disabled="disabled"/>
			</div>
			<div style="display: none;">
				<!-- Owner -->
				<input type="hidden" name="owner" value="${user.legajo}"/>
			</div>
			<div>
				<!-- Owner -->
				<span>Asignada a:</span>
				<select name="responsable">
					<option value="">Seleccionar Opci&oacute;n</option>
					<c:forEach var="item" items="${collectionsBean.responsables}">
						<option value="${item.legajo}">${item.apellido},${item.nombre}</option>
					</c:forEach>
				</select>
			</div>
			<div>
				<!-- FechaCreación -->
				<span>Fecha de creaci&oacute;n </span>
				<input id="fechaC" type="text" name="fechaCreacion"/>
			</div>
			<div>
				<!-- FechaModificación -->
				<span>&Uacute;ltima modificaci&oacute;n </span>
				<input id="fechaM" type="text" name="fechaModificacion"/>
			</div>			
			<div>
				<!-- Titulo -->
				<span>Titulo</span>
				<input type="text" name="titulo"/>
			</div>
			<div>
				<!-- Tipo Incidencia -->
				<span>Tipo Incidencia</span>
				<select name="tipoIncidencia">
					<option value="">Seleccionar Opci&oacute;n</option>
					<c:forEach var="item" items="${collectionsBean.tiposIncidencia}">
						<option value="${item.id}">${item.nombre}</option>
					</c:forEach>
				</select>
			</div>
			<div>
				<!--Prioridad incidencia-->
				<span>Prioridad Incidencia</span>
				<select name="prioridad">
					<option value="">Seleccionar Opci&oacute;n</option>
					<c:forEach var="item" items="${collectionsBean.prioridadIncidencia}">
						<option value="${item.id}">${item.nombre}</option>
					</c:forEach>
				</select>
			</div>
			<div>
				<span>Detalle:</span>
				<textarea name="detalle" rows="3" cols="19" style="overflow: scroll;"></textarea>
			</div>
			<div>
				<span>Estado:</span>
				<select name="estado">
					<option value="">Seleccionar Opci&oacute;n</option>
					<c:forEach var="item" items="${collectionsBean.estadosIncidencia}">
						<option value="${item.id}">${item.nombre}</option>
					</c:forEach>
				</select>
			</div>
			<div id="footer">
				<input value="Aceptar" type="submit">
				<input id="cancelButton" value="Cancelar" type="button">
				<a id="backLink" style="display: none;" href="${pageContext.request.contextPath}/searchEntity.htm?entityName=Incidencia"></a>
			</div>
		</div>
	</form>
</body>
</html>

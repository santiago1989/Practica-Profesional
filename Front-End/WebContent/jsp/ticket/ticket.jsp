<%@page import="com.gestor.entidades.Incidencia"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Incidencia</title>
<!-- JQUERY -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.11.3.js"></script>
<script type="text/javascript">
$( document ).ready(function() {
	$("#cancelButton").click(function(){
		var href = $("#backLink").attr("href");
		window.location.href = href;
	});
});
</script>
</head>
<body>
	<form action="${pageContext.request.contextPath}/saveEntity.htm" method="post">
		<input name="entityName" type="hidden" value="<%=Incidencia.class.getSimpleName()%>"/>
		<div id="frame">
			<div>
				<!-- Número de incidencia -->
				<span>N&uacute;mero de incidencia</span>
				<input type="text" name="numero"/>
			</div>
			<div>
				<!-- Owner -->
				<span>Creada por </span>
				<input type="text" name="owner"/>
			</div>
			<div>
				<!-- FechaCreación -->
				<span>Fecha de creaci&oacute;n </span>
				<input type="text" name="fechaCreacion"/>
			</div>
			<div>
				<!-- FechaModificación -->
				<span>Ãšltima modificaci&oacute;n </span>
				<input type="text" name="fechaModificacion"/>
			</div>			
			<div>
				<!-- Titulo -->
				<span>Titulo</span>
				<input type="text" name="tituloIncidencia"/>
			</div>
			<div>
				<!-- Tipo Incidencia -->
				<span>Tipo Incidencia</span>
				<select name="tipoIncidencia">
					<option value="I">Insumos</option>
					<option value="M">Mantenimiento</option>
					<option value="A">AplicaciÃ³n</option>
				</select>
			</div>
			<div>
				<!--Prioridad incidencia-->
				<span>Prioridad Incidencia</span>
				<select name="prioridadIncidencia">
					<option value="A">Alta</option>
					<option value="M">Media</option>
					<option value="B">Baja</option>
				</select>
			</div>
			<div>
				<span>Detalle:</span>
				<textarea id="detail" rows="10" cols="5"></textarea>			
			</div>
			<div>
				<span>Estado:</span>
				<select>
					<option value="">Seleccione OpciÃ³n</option>
					<option value="A">Abierta</option>
					<option value="AS">Asignada</option>
					<option value="R">Resulta</option>
					<option value="C">Cerrada</option>
				</select>
			</div>
		</div>
		<div id="footer">
			<input value="Aceptar" type="submit">
			<input id="cancelButton" value="Cancelar" type="button">
			<a id="backLink" style="display: none;" href="${pageContext.request.contextPath}/searchEntity.htm?entityName=Incidencia"></a>
		</div>
	</form>
</body>
</html>

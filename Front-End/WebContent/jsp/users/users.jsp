<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.gestor.web.seguridad.Usuario"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../comon/head.jsp"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common/form.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Alta de usuario</title>
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
	<jsp:include page="../comon/menu.jsp"/>
	<jsp:include page="../comon/alert.jsp"/>
	<form action="${pageContext.request.contextPath}/saveEntity.htm" method="post">
		<input name="entityName" type="hidden" value="<%=Usuario.class.getName()%>"/>
		<div id="frame" class="parent" style="width: 340px;margin-left:-205px">
			<div  style="display: none;">
				<!-- Número de Legajo -->
				<span>N&uacute;mero de legajo</span>
				<input type="text" name="legajo"/>
			</div>
			<div>
				<!-- Nombre -->
				<span>Nombre</span>
				<input type="text" name="nombre"/>
			</div>
			<div>
				<!-- Apellido -->
				<span>Apellido</span>
				<input type="text" name="apellido"/>
			</div>
			<div>
				<!-- Contraseña -->
				<span>Contrase&ntilde;a</span>
				<input type="password" name="contrasena"/>
			</div>
			<div>
				<!-- DNI -->
				<span>DNI</span>
				<input type="text" name="dni"/>
			</div>
			<div>
				<!-- Telefono -->
				<span>Tel&eacute;fono</span>
				<input type="text" name="telefono"/>
			</div>
			<div style="display: none;">
				<!-- Estado -->
				<span>Activo</span>
				<input type="checkbox" name="estado"/>
			</div>
			<div>
				<!-- Email -->
				<span>Email</span>
				<input type="text" name="correo"/>
			</div>
			<div>
				<span>Rol:</span>
				<select name="rol">
					<option value="">Seleccione Opci&oacute;n</option>
					<c:forEach var="item" items="${collectionsBean.roles}">
						<option value="${item.code}">${item.nombre}</option>
					</c:forEach>
				</select>
			</div>
		<div id="footer">
			<input value="Aceptar" type="submit">
			<input id="cancelButton" value="Cancelar" type="button">
			<a id="backLink" style="display: none;" href="${pageContext.request.contextPath}/searchEntity.htm?entityName=Incidencia"></a>
		</div>
	</form>
</body>
</html>

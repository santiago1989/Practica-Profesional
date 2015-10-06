<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.gestor.web.seguridad.Usuario"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Alta de usuario</title>
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
		<input name="entityName" type="hidden" value="<%=Usuario.class.getSimpleName()%>"/>
		<div id="frame">
			<div>
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
				<input type="password" name="contraseÃ±a"/>
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
			<div>
				<!-- Estado -->
				<span>Activo</span>
				<input type="checkbox" name="estado"/>
			</div>
			<div>
				<!-- Email -->
				<span>Email</span>
				<input type="text" name="email"/>
			</div>
			<div>
				<span>Rol:</span>
				<select name="rol">
					<option value="">Seleccione Opci&oacute;n</option>
					<option value="A">Administrativo</option>
					<option value="R">Responsable</option>
					<option value="S">Superusuario</option>
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

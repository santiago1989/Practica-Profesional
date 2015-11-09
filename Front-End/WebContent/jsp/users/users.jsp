<%@page import="com.gestor.web.dto.UsuarioCollectionsBean"%>
<%@page import="com.gestor.web.seguridad.Rol"%>
<%@page import="com.gestor.entidades.Incidencia"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.gestor.web.seguridad.Usuario"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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

		<input name="entityName" type="hidden" value="<%=Usuario.class.getName()%>"/>
		<div id="frame" class="parent" style="width: 340px;margin-left:-205px">
			<c:if test="${update == true || read == true}">
				<input name="update" type="hidden" value="${update}"/>
				<input name="read" type="hidden" value="${read}"/>
				<div>
					<!-- Número de Legajo -->
					<span>N&uacute;mero de legajo</span>
					<input type="text" name="legajo" disabled="disabled" value="${bean.legajo}"/>
					<input type="hidden" name="legajo" value="${bean.legajo}"/>
				</div>
			</c:if>
			<div>
				<!-- Nombre -->
				<span>Nombre</span>
				<c:choose>
					<c:when test="${read == true}">
						<input type="text" name="nombre" value="${bean.nombre}" disabled="disabled"/>
					</c:when>
					<c:otherwise>
						<input type="text" name="nombre" value="${bean.nombre}"/>
					</c:otherwise>
				</c:choose>
			</div>
			<div>
				<!-- Apellido -->
				<span>Apellido</span>
				<c:choose>
					<c:when test="${read == true}">
						<input type="text" name="apellido" value="${bean.apellido}" disabled="disabled"/>
					</c:when>
					<c:otherwise>
						<input type="text" name="apellido" value="${bean.apellido}"/>
					</c:otherwise>
				</c:choose>
			</div>
			<c:choose>
				<c:when test="${(update != true) && (read != true)}">
					<div>
						<!-- Contraseña -->
						<span>Contrase&ntilde;a</span>
						<input type="password" name="contrasena" value="${bean.contrasena}"/>
					</div>
					<div>
						<!-- Contraseña Confirmacion -->
						<span>Confirmar Contrase&ntilde;a</span>
						<input type="password" name="contrasenaconfirmacion" value="${bean.contrasena}"/>
					</div>
				</c:when>
				<c:otherwise>
					<input type="hidden" name="contrasena" value="${bean.contrasena}"/>
				</c:otherwise>	
			</c:choose>
			<c:if test="">
			</c:if>
			<div>
				<!-- DNI -->
				<span>DNI</span>
				<c:choose>
					<c:when test="${read == true}">
						<input type="text" name="dni" value="${bean.dni}" disabled="disabled"/>
					</c:when>
					<c:otherwise>
						<input type="text" name="dni" value="${bean.dni}"/>
					</c:otherwise>
				</c:choose>
			</div>
			<div>
				<!-- Telefono -->
				<span>Tel&eacute;fono</span>
				<c:choose>
					<c:when test="${read == true}">
						<input type="text" name="telefono" value="${bean.telefono}" disabled="disabled"/>
					</c:when>
					<c:otherwise>
						<input type="text" name="telefono" value="${bean.telefono}"/>
					</c:otherwise>
				</c:choose>
			</div>
			<div>
				<!-- Email -->
				<span>Email</span>
				<c:choose>
					<c:when test="${read == true}">
						<input type="text" name="correo" value="${bean.correo}" disabled="disabled"/>
					</c:when>
					<c:otherwise>
						<input type="text" name="correo" value="${bean.correo}"/>
					</c:otherwise>
				</c:choose>
			</div>
			<div>
				<span>Rol:</span>
				<c:forEach var="item" items="${collectionUsuario.roles}">
					<c:choose>
						<c:when test="${fn:contains(bean.roles,item.nombre)}">
							<c:choose>
								<c:when test="${read}">
									<input name="roles" type="checkbox" value="${item.code}" checked="checked" disabled="disabled">${item.nombre}</input>	
								</c:when>
								<c:otherwise>
									<input name="roles" type="checkbox" value="${item.code}" checked="checked">${item.nombre}</input>																		
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
							<c:choose>
								<c:when test="${read}">
									<input name="roles" type="checkbox" value="${item.code}" disabled="disabled">${item.nombre}</input>
								</c:when>
								<c:otherwise>
									<input name="roles" type="checkbox" value="${item.code}">${item.nombre}</input>
								</c:otherwise>
							</c:choose>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</div>
		<div id="footer">
			<c:if test="${read == null || read == false}">
				<input value="Aceptar" type="submit">
				<input id="cancelButton" value="Cancelar" type="button">
			</c:if>
			<c:if test="${read == true }">
				<input id="backButton" type="button" value="Volver"/>
			</c:if>
			<a id="backLink" style="display: none;" href="${pageContext.request.contextPath}/searchEntity.htm?entityName=<%=Usuario.class.getName()%>"></a>
		</div>
	</form>
</body>
</html>

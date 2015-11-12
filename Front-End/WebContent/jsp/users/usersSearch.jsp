<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/tld/jmesa.tld" prefix="jmesa" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.gestor.web.seguridad.Usuario"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jmesa/jmesa.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jmesa/jquery.jmesa.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jmesa.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common/form.css">
<jsp:include page="../comon/head.jsp"/>
<title>B&uacute;squeda de Usuarios</title>
<script type="text/javascript">
function onInvokeAction(id, action){
  setExportToLimit(id, '');
  createHiddenInputFieldsForLimitAndSubmit(id);
}
function onInvokeExportAction(id,action) {
	var clazName = $('#clazName').attr('value');
    location.href = '${pageContext.request.contextPath}/descargarReporte.htm?clazName='+clazName;
}
</script>
</head>
<body>
	<jsp:include page="../comon/menu.jsp"/>
	<jsp:include page="../comon/alert.jsp"/>
	<form action="${pageContext.request.contextPath}/getUsersResults.htm" method="post">
		<input type="hidden" id="clazName" value="<%=Usuario.class.getSimpleName()%>"/>
		<div class="parent" style="display: inline-block;">
			<div>
				<span>Legajo</span>
				<input name="legajo" type="text" value="${resultsUsuario.criteria.legajo}"/>
			</div>
			<div>
				<span>Nombre</span>
				<input name="nombre" type="text" value="${resultsUsuario.criteria.nombre}"/>
			</div>
			<div>
				<span>Apellido</span>
				<input name="apellido" type="text" value="${resultsUsuario.criteria.apellido}"/>
			</div>
			<div>
				<span>Rol:</span>
				<select name="rol">
					<option value="">Seleccione Opci&oacute;n</option>
					<c:forEach var="item" items="${collectionUsuario.roles}">
						<option value="${item.code}">${item.nombre}</option>
					</c:forEach>
				</select>
			</div>
			<div>
				<input type="submit" value="Buscar"/>
				<input type="button" value="Limpiar" onclick="clean()">
			</div>
			<div style="position: relative;left: -82%">
			    <c:if test="${not empty resultsUsuario.results}">
			       <jmesa:tableModel id="jmesaTag" items="${resultsUsuario.results}" toolbar="com.gestor.web.jmesa.CustomToolBar"
			       stateAttr="restore" var="bean" > 
			           <jmesa:htmlTable captionKey="presidents.caption" width="600px" >		 
			                 <jmesa:htmlRow>	 
			                     <jmesa:htmlColumn property="legajo" title="Legajo"/> 
			                     <jmesa:htmlColumn property="apellido" title="Apellido"/> 
			                     <jmesa:htmlColumn property="nombre" filterEditor="org.jmesa.view.html.editor.DroplistFilterEditor"/>
			                     <jmesa:htmlColumn property="rolesDescription" title="Roles"/>
			                     <jmesa:htmlColumn title="Acciones">
			                         <a href="${pageContext.request.contextPath}/readOrUpdateUsuario.htm?entityId=${bean.legajo}&read=true">
			                         	<img title="Consultar" alt="Consultar" src="${pageContext.request.contextPath}/images/buttons/icono-estado.jpg">
			                      	 </a>
			                         <a href="${pageContext.request.contextPath}/readOrUpdateUsuario.htm?entityId=${bean.legajo}&update=true">
			                         	<img title="Editar" alt="Editar" src="${pageContext.request.contextPath}/images/buttons/iglu-editar.png">
			                         </a> 
			                         <a href="#" onclick="showAlert('Esta seguro que desea eliminar el usuario?','${pageContext.request.contextPath}/removeUser.htm?entityId=${bean.legajo}')">
			                         	<img title="Eliminar" alt="Eliminar" src="${pageContext.request.contextPath}/images/buttons/remove.png">
			                         </a>
			                     </jmesa:htmlColumn>
			                 </jmesa:htmlRow> 
			             </jmesa:htmlTable>  
			       </jmesa:tableModel>
			    </c:if>
			</div>
		</div>
	</form>
</body>
</html>
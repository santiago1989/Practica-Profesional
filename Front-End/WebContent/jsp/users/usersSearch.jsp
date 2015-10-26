<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/tld/jmesa.tld" prefix="jmesa" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jmesa.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jmesa.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common/form.css">
<jsp:include page="../comon/head.jsp"/>
<title>B&uacute;squeda de Usuarios</title>
</head>
<body>
	<jsp:include page="../comon/menu.jsp"/>
	<jsp:include page="../comon/alert.jsp"/>
	<form action="${pageContext.request.contextPath}/getUsersResults.htm" method="post">
		<div class="parent" style="display: inline-block;">
			<div>
				<span>Legajo</span>
				<input name="legajo" type="text" value="${searchBean.legajo}"/>
			</div>
			<div>
				<span>Nombre</span>
				<input name="nombre" type="text" value="${searchBean.nombre}"/>
			</div>
			<div>
				<span>Apellido</span>
				<input name="apellido" type="text" value="${searchBean.apellido}"/>
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
			<div>
				<input type="submit" value="Buscar"/>
			</div>
			<div style="position: relative;left: -82%">
			    <c:if test="${not empty collection}">
				     <jmesa:tableModel id="jmesaTag" items="${collection}" exportTypes="csv,excel" 
				       stateAttr="restore" var="bean"> 
				           <jmesa:htmlTable captionKey="presidents.caption" width="600px">		 
				                 <jmesa:htmlRow>	 
				                     <jmesa:htmlColumn property="legajo" title="Last Name"/> 
				                     <jmesa:htmlColumn property="apellido" title="Last Name"/> 
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
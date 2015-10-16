<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/tld/jmesa.tld" prefix="jmesa" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jmesa.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jmesa/jmesa.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common/form.css">
<jsp:include page="../comon/head.jsp"/>
<title>Gestión de Tickets</title>
</head>
<body>
<body id="body">
	<jsp:include page="../comon/menu.jsp"/>
	<jsp:include page="../comon/alert.jsp"/>
	<form action="${pageContext.request.contextPath}/getTicketsResults.htm" method="post">
		<div class="parent" style="display: inline-block;">			
			<div>
				<span>N&uacute;mero</span>
				<input name="numero" type="text" value="${searchBean.legajo}"/>
			</div>
			<div>
				<span>Autor</span>
				<select name="owner">
					<option value="">Seleccionar Opci&oacute;n</option>
					<c:forEach var="item" items="${collectionsBean.owners}">
						<option value="">Seleccionar Opci&oacute;n</option>
						<option value="${item.legajo}" selected="${searchBean.owner == item.legajo? 'selected' : ''}">${item.apellido},${item.nombre}</option>
					</c:forEach>
				</select>
			</div>
			<div>
				<span>Responsable</span>
				<select name="responsable">
					<option value="">Seleccionar Opci&oacute;n</option>
					<c:forEach var="item" items="${collectionsBean.responsables}">
						<option value="${item.legajo}" selected="${searchBean.owner == item.legajo? 'selected' : ''}">${item.apellido},${item.nombre}</option>
					</c:forEach>
				</select>
			</div>
			<div>
				<span>Tipo de incidencia</span>
				<select name="tipo">
					<option value="">Seleccionar Opci&oacute;n</option>
					<c:forEach var="item" items="${collectionsBean.tiposIncidencia}">
						<option value="${item.id}" selected="${searchBean.tipo == item.id? 'selected' : ''}">${item.nombre}</option>
					</c:forEach>
				</select>
			</div>
			<div>
				<span>Prioridad de incidencia</span>
				<select name="prioridad">
					<option value="">Seleccionar Opci&oacute;n</option>
					<c:forEach var="item" items="${collectionsBean.prioridadIncidencia}">
						<option value="${item.id}" selected="${searchBean.prioridad == item.id? 'selected' : ''}">${item.nombre}</option>
					</c:forEach>
				</select>
			</div>
			<div>
				<span>Estado de incidencia</span>
				<select name="estado">
					<option value="">Seleccionar Opci&oacute;n</option>
					<c:forEach var="item" items="${collectionsBean.estadosIncidencia}">
						<option value="${item.id}" selected="${searchBean.estado == item.id? 'selected' : ''}">${item.nombre}</option>
					</c:forEach>
				</select>
			</div>
			
			<div>
				<input type="submit" value="Buscar"/>
			</div>
			<div>
			    <c:if test="${not empty collection}">
				     <jmesa:tableModel id="jmesaTag" items="${collection}" exportTypes="csv,excel" 
				       stateAttr="restore" var="bean"> 
				           <jmesa:htmlTable captionKey="presidents.caption" width="600px">		 
				                 <jmesa:htmlRow>	 
				                     <jmesa:htmlColumn property="legajo" title="Last Name"/> 
				                     <jmesa:htmlColumn property="apellido" title="Last Name"/> 
				                     <jmesa:htmlColumn property="nombre" filterEditor="org.jmesa.view.html.editor.DroplistFilterEditor"/>
				                     <jmesa:htmlColumn title="Acciones">
				                         <a href="http://www.whitehouse.gov/history/presidents/">Editar</a> 
				                     </jmesa:htmlColumn>
				                 </jmesa:htmlRow> 
				             </jmesa:htmlTable>  
				       </jmesa:tableModel>
			    </c:if>
			</div>
		</div>
	</form>
</body>
</body>
</html>
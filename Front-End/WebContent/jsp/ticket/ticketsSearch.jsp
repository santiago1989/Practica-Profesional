<%@page import="com.gestor.entidades.Incidencia"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/tld/jmesa.tld" prefix="jmesa" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jmesa.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common/form.css">
<jsp:include page="../comon/head.jsp"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery/jquery-ui.min.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-ui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jmesa/jmesa.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jmesa/jquery.jmesa.js"></script>
<title>Gestión de Tickets</title>
<script type="text/javascript">
$( document ).ready(function() {
	$("#fechaC").datepicker({dateFormat: 'dd/mm/yy'});
	$("#fechaM").datepicker({dateFormat: 'dd/mm/yy'});
});
</script>
<script type="text/javascript">
function onInvokeAction(id, action){
  setExportToLimit(id, '');
  createHiddenInputFieldsForLimitAndSubmit(id);
}
function onInvokeExportAction(id) {
	var parameterString = createParameterStringForLimit(id);
	var clazName = $('#clazName').attr('value');
    location.href = '${pageContext.request.contextPath}/descargarReporte.htm?clazName='+clazName+'&exportType='+parameterString;
}
</script>
</head>
<body>
<body id="body">
	<jsp:include page="../comon/menu.jsp"/>
	<jsp:include page="../comon/alert.jsp"/>
	<form action="${pageContext.request.contextPath}/getTicketsResults.htm" method="post">
		<input type="hidden" id="clazName" value="<%=Incidencia.class.getSimpleName()%>"/>
		<div class="parent" style="display: inline-block;width: 310px;">
			<div>
				<span>N&uacute;mero</span>
				<input name="numero" type="text" value="${resultsIncidencia.criteria.numero}"/>
			</div>
			<div>
				<span>Autor</span>
				<select name="owner">
					<option value="">Seleccionar Opci&oacute;n</option>
					<c:forEach var="item" items="${collectionIncidencia.owners}">
						<c:choose>
							<c:when test="${resultsIncidencia.criteria.owner == item.legajo}">
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
				<span>Responsable</span>
				<select name="responsable">
					<option value="">Seleccionar Opci&oacute;n</option>
					<c:forEach var="item" items="${collectionIncidencia.responsables}">
						<c:choose>
							<c:when test="${resultsIncidencia.criteria.responsable == item.legajo}">
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
				<span>Tipo incidencia</span>
				<select name="tipo">
					<option value="">Seleccionar Opci&oacute;n</option>
					<c:forEach var="item" items="${collectionIncidencia.tiposIncidencia}">
						<c:choose>
							<c:when test="${resultsIncidencia.criteria.tipo == item.id}">
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
				<span>Prioridad de incidencia</span>
				<select name="prioridad">
					<option value="">Seleccionar Opci&oacute;n</option>
					<c:forEach var="item" items="${collectionIncidencia.prioridadIncidencia}">
						<c:choose>
							<c:when test="${resultsIncidencia.criteria.prioridad == item.id}">
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
				<span>Estado de incidencia</span>
				<select name="estado">
					<option value="">Seleccionar Opci&oacute;n</option>
					<c:forEach var="item" items="${collectionIncidencia.estadosIncidencia}">
						<c:choose>
							<c:when test="${resultsIncidencia.criteria.estado == item.id}">
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
				<!-- FechaCreación -->
				<span>Fecha de creaci&oacute;n </span>
				<input id="fechaC" type="text" name="fechaCreacion" value="${resultsIncidencia.criteria.fechaCreacion}"/>
			</div>
			<div>
				<!-- FechaModificación -->
				<span>&Uacute;ltima modificaci&oacute;n </span>
				<input id="fechaM" type="text" name="fechaModificacion" value="${resultsIncidencia.criteria.fechaModificacion}"/>
			</div>
			<div>
				<input type="submit" value="Buscar"/>
				<input type="button" value="Limpiar" onclick="clean()">
			</div>
			<div  style="position: relative;left: -82%">
			    <c:if test="${not empty resultsIncidencia.results}">
				     <jmesa:tableModel id="jmesaTag" items="${resultsIncidencia.results}" toolbar="com.gestor.web.jmesa.CustomToolBar" 
				       stateAttr="restore" var="bean"> 
				           <jmesa:htmlTable captionKey="presidents.caption" width="600px">		 
				                 <jmesa:htmlRow>
				                     <jmesa:htmlColumn property="numero" title="ID"/> 
				                     <jmesa:htmlColumn property="titulo" title="Titulo"/>
				                     <jmesa:htmlColumn property="owner.nombre" title="Autor"/>
				                     <jmesa:htmlColumn property="responsable.nombre" title="Responsable"/>
				                     <jmesa:htmlColumn property="tipoIncidencia.nombre" title="Tipo"/>
				                     <jmesa:htmlColumn property="prioridad.nombre" title="Prioridad"/>
				                     <jmesa:htmlColumn property="estado.nombre" title="Estado"/>
				                     <jmesa:htmlColumn title="Acciones">
   				                         <a href="${pageContext.request.contextPath}/readOrUpdateTicket.htm?entityId=${bean.numero}&read=true">
   				                         	<img title="Consultar" alt="Consultar" src="${pageContext.request.contextPath}/images/buttons/icono-estado.jpg">
   				                      	 </a>
				                         <a href="${pageContext.request.contextPath}/readOrUpdateTicket.htm?entityId=${bean.numero}&update=true">
				                         	<img title="Editar" alt="Editar" src="${pageContext.request.contextPath}/images/buttons/iglu-editar.png">
				                         </a> 
<%-- 				                         <a href="#" onclick="showAlert('Esta seguro que desea eliminar el usuario?','${pageContext.request.contextPath}/removeTicket.htm?entityId=${bean.numero}')"> --%>
<%-- 				                         	<img title="Eliminar" alt="Eliminar" src="${pageContext.request.contextPath}/images/buttons/remove.png"> --%>
<!-- 				                         </a> -->
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
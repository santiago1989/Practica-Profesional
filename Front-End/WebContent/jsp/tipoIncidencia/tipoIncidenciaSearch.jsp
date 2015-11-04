<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/tld/jmesa.tld" prefix="jmesa" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jmesa.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jmesa.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common/form.css">
<jsp:include page="../comon/head.jsp"/>
<title>B&uacute;squeda de Tipo de Incidencia</title>
</head>
<body>
	<jsp:include page="../comon/menu.jsp"/>
	<jsp:include page="../comon/alert.jsp"/>
	<form action="${pageContext.request.contextPath}/getTicketsTypeResults.htm" method="post">
		<div>
			<div>
				<span>N&uacute;mero:</span>
				<input name="code" type="text"/>
			</div>
			<div>
				<span>Descripci&oacute;</span>
				<input name="description" type="text"/>
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
				                     <jmesa:htmlColumn property="id" title="ID"/> 
				                     <jmesa:htmlColumn property="nombre" title="Nombre"/>
				                     <jmesa:htmlColumn title="Acciones">
   				                         <a href="${pageContext.request.contextPath}/readOrUpdateTicketType.htm?entityId=${bean.id}&read=true">
   				                         	<img title="Consultar" alt="Consultar" src="${pageContext.request.contextPath}/images/buttons/icono-estado.jpg">
   				                      	 </a>
				                         <a href="${pageContext.request.contextPath}/readOrUpdateTicketType.htm?entityId=${bean.id}&update=true">
				                         	<img title="Editar" alt="Editar" src="${pageContext.request.contextPath}/images/buttons/iglu-editar.png">
				                         </a> 
				                         <a href="#" onclick="showAlert('Esta seguro que desea eliminar el Tipo Incidencia?','${pageContext.request.contextPath}/removeUser.htm?entityId=${bean.id}')">
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
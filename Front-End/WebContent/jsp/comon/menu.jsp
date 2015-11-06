<%@page import="com.gestor.web.seguridad.Usuario"%>
<%@page import="com.gestor.entidades.Incidencia"%>
<%@page import="com.gestor.entidades.TipoIncidencia"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div class="wrapper">
    <div class="brand">
        <p><img class="bannerHead" alt="fondo" src="${pageContext.request.contextPath}/images/LogoSAR.jpg" style="width: 80px; height: 80px; "></p>
    </div>
    <c:choose>
	    <c:when test="${user != null}">
		    <!-- START Responsive Menu HTML -->
		    <div class="rm-container">
		        <a class="rm-toggle rm-button rm-nojs" href="#">Menu</a>
		
		        <nav class="rm-nav rm-nojs rm-lighten">
		            <ul>
		               	<li><a href="${pageContext.request.contextPath}">Inicio</a></li>
		            	<c:if test="${fn:contains(user.rolesValues,administrativo) || fn:contains(user.rolesValues,responsable)}"><li><a href="${pageContext.request.contextPath}/getTicketsResults.htm?owner=${user.legajo}">Mis Tickets</a></li></c:if>
		            	<c:if test="${fn:contains(user.rolesValues,administrativo) || fn:contains(user.rolesValues,responsable)}"><li><a href="${pageContext.request.contextPath}/searchEntity.htm?entityName=<%=Incidencia.class.getName()%>">Gestión Tickets</a></li></c:if>
		                <c:if test="${fn:contains(user.rolesValues,administrativo)}"><li><a href="${pageContext.request.contextPath}/newEntity.htm?entityName=<%=Incidencia.class.getName()%>">Nuevo Ticket</a></li></c:if>
						<c:if test="${fn:contains(user.rolesValues,administrativo)}">
							<li><a href="#">Tipos de Tickets</a>
  		                      <ul>
			                     <li><a href="${pageContext.request.contextPath}/newEntity.htm?entityName=<%=TipoIncidencia.class.getName()%>">Nuevo Tipo de Ticket</a></li>
			                     <li><a href="${pageContext.request.contextPath}/searchEntity.htm?entityName=<%=TipoIncidencia.class.getName()%>">Administración Tipo de Ticket</a></li>
		                      </ul>
		             	    </li>
		                </c:if>
		                <c:if test="${fn:contains(user.rolesValues,superusuario)}">
			                <li><a href="#">Usuarios</a>
			                    <ul>
			                        <li><a href="${pageContext.request.contextPath}/newEntity.htm?entityName=<%=Usuario.class.getName()%>">Nuevo Usuario</a></li>
			                        <li><a href="${pageContext.request.contextPath}/searchEntity.htm?entityName=<%=Usuario.class.getName()%>">Gestión</a></li>
			                    </ul>
			                </li>
		                </c:if>
		                <li><a href="${pageContext.request.contextPath}/logout.htm">Logout</a>		                
		                </li>
		            </ul>
		        </nav>
		    </div>
	    </c:when>
	    <c:otherwise>
		    <div style='color: White; text-align: center; background-color: Black; width: 1008px; height: 80px; font-family: "Roboto Condensed", Sans-Serif; font-weight: 400; font-size: 24px; letter-spacing: 1px; vertical-align: middle'>
				SISTEMA DE ADMINISTRACION DE RECLAMOS
		    </div>
	    </c:otherwise>
	</c:choose>    
</div>
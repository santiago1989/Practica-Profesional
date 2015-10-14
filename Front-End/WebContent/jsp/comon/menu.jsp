<%@page import="com.gestor.web.seguridad.Usuario"%>
<%@page import="com.gestor.entidades.Incidencia"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<div class="wrapper">
    <div class="brand">
        <p><a href="#" class="logo">RM</a></p>
    </div>
    <!-- START Responsive Menu HTML -->
    <div class="rm-container">
        <a class="rm-toggle rm-button rm-nojs" href="#">Menu</a>

        <nav class="rm-nav rm-nojs rm-lighten">
            <ul>
               	<li><a href="${pageContext.request.contextPath}">Inicio</a></li>
            	<li><a href="#">Mis Tickets</a></li>
            	<li><a href="${pageContext.request.contextPath}/searchEntity.htm?entityName=<%=Incidencia.class.getSimpleName()%>">Gestión Tickets</a></li>
                <li><a href="${pageContext.request.contextPath}/newEntity.htm?entityName=<%=Incidencia.class.getSimpleName()%>">Nuevo Ticket</a></li>
				<li><a href="#">Tipos de Tickets</a>
                     <ul>
                          <li><a href="#">Nuevo Tipo de Ticket</a></li>
                          <li><a href="#">Administración Tipo de Ticket</a></li>
                      </ul>
                </li>
                <li><a href="#">Usuarios</a>
                    <ul>
                        <li><a href="${pageContext.request.contextPath}/newEntity.htm?entityName=<%=Usuario.class.getSimpleName()%>">Nuevo Usuario</a></li>
                        <li><a href="${pageContext.request.contextPath}/searchEntity.htm?entityName=<%=Usuario.class.getName()%>">Gestión</a></li>
                    </ul>
                </li>
                <li><a href="${pageContext.request.contextPath}/logout.htm">Logout</a>
                </li>
            </ul>
        </nav>
    </div>
</div>
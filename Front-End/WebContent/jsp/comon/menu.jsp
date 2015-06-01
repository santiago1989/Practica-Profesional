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
                <li><a href="#">Incidencias</a>
                    <ul>
                        <li><a href="#">Nueva Incidencia</a></li>
                        <li><a href="#">Administración</a>
                        	<ul>
                        		<li><a href="#">Mis Incidencias</a></li>
                        		<li><a href="#">Buscar</a></li>
                        	</ul>
                        </li>
                        <li><a href="#">Tipo de incidencias</a>
                            <ul>
                                <li><a href="#">Nuevo Tipo de Incidencia</a></li>
                                <li><a href="#">Administración Tipo de incidencia</a></li>
                            </ul>
                        </li>
                    </ul>
                </li>
                <li><a href="#">Usuarios</a>
                    <ul>
                        <li><a href="#">Nuevo Usuario</a></li>
                        <li><a href="#">Administración</a></li>
                    </ul>
                </li>
                <li><a href="${pageContext.request.contextPath}/logout.htm">Logout</a>
                </li>
            </ul>
        </nav>
    </div>
</div>
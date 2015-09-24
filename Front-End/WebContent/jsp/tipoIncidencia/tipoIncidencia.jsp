<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Tipo de incidencia</title>
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
		<input name="entityName" type="hidden" value="<%=tipoIncidencia.class.getSimpleName()%>"/>
		<div id="frame">
			<div>
				<!-- Código -->
				<span>ID</span>
				<input type="int" name="ID"/>
			</div>
			<div>
				<!-- Nombre -->
				<span>Nombre</span>
				<input type="text" name="nombre"/>
			</div>
		</div>	
		<div id="footer">
			<input value="Aceptar" type="submit">
			<input id="cancelButton" value="Cancelar" type="button">
			<a id="backLink" style="display: none;" href="${pageContext.request.contextPath}/searchEntity.htm?entityName=Incidencia"></a>
		</div>
	</form>
</body>
</html>

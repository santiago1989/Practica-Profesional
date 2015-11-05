<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${update == true && bean != null}">
	<div>
		<form action="${pageContext.request.contextPath}/adjuntar.htm" method="post" enctype="multipart/form-data">
			<input name="filePath" type="file"/>
			<input type="submit" value="Subir"/>
		</form>
	</div>
	<c:forEach var="item" items="${bean.adjuntos}">
		<div>
			<a href="${pageContext.request.contextPath}/${item.url}">
				${item.fileName}
			</a>
		</div>
	</c:forEach>
</c:if>
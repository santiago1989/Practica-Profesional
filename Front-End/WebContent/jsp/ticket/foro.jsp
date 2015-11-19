<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:if test="${update == true && bean != null}">
	<section id="accordion">
		<div>
<!-- 			<input type="checkbox" id="check-1" /> -->
<!-- 			<label for="check-1">Comentarios</label> -->
			<article>
				<form action="${pageContext.request.contextPath}/comentar.htm" method="post">
					<input type="hidden" name="incidencia" value="${bean.id}" />
					<input type="hidden" name="usuario" value="${user.legajo}" />
					<c:forEach var="item" items="${bean.notas}">
					<div style="color: white;">
						<div>
							<span><fmt:formatDate pattern="HH:mm:ss dd/MM/yyyy" value="${item.fechaPublicacion}"/></span>
						</div>
						<div>
							<span>${item.usuario.nombre} </span>
							<span>dice:</span>
						</div>
						<div>
							<textarea rows="4" cols="10" disabled="disabled">${item.detalle}</textarea>
							<c:if test="${user.legajo  == item.usuario.legajo}">
								<a href="${pageContext.request.contextPath}/eliminarComentario.htm?nota=${item.id}">Remove</a>
							</c:if>
						</div>
						<br>
					</div>
					</c:forEach>
					<div>
						<textarea rows="4" cols="10" name="comentario" spellcheck="true" draggable="true">
						</textarea>
					</div>
					<input value="Post" type="submit"/>
				</form>
			</article>
		</div>
	</section>
</c:if>
<div id="passChange" style="display: none;" class="parent">
	<form action="${pageContext.request.contextPath}/changePassword.htm">
		<input name="viewFrom" type="hidden" value="${viewfrom}"/>
		<input name="legajo" type="hidden" value="${user.legajo}"/>
		<div class="child">
			<div>
				Anterior Contrase&ntilde;a:
				<input type="password" name="oldPass" />
			</div>			
			<div>
				Contrase&ntilde;a Nueva:
				<input type="password" name="newPass" />
			</div>
			<div>
				Confirmar Contrase&ntilde;a:
				<input type="password" name="newPassConf" />
			</div>
			<div>
				<input type="submit" value="Cambiar" />
				<input type="button" value="Cancelar" onclick="cerrarDiv()" />
			</div>
		</div>
	</form>
</div>

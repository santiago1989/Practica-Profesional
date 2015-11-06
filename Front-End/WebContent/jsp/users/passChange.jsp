<div id="passChange" style="display: none"> 
	<form action="${pageContext.request.contextPath}/changePassword.htm">
		<input name="legajo" type="hidden" value="${bean.legajo}"/>
		<div>
			<div>
				Anterior Contrase&ntilde;a:
			</div>
			<div>
				<input type="password" name="oldPass" />
			</div>			
		</div>			
		<div>
			<div>
				Contrase&ntilde;a Nueva:
			</div>
			<div>
				<input type="password" name="newPass" />
			</div>			
		</div>
		<div>
			<div>
				Confirmar Contrase&ntilde;a:
			</div>
			<div>
				<input type="password" name="newPassConf" />
			</div>			
		</div>
		<div>
			<input type="submit" value="Cambiar" />
			<input type="button" value="Cancelar" onclick="cerrarDiv()" />
		</div>
	</form>
</div>

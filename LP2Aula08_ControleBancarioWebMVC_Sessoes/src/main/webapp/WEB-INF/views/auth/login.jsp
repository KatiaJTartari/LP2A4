<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<div class="row">
	<div class="col-xl-12">
		<form method="POST" action="${pageContext.request.contextPath}/Login">
			<div class="form-group">
				<label for="txtEmail" class="form-label">E-mail:</label> 
				<input type="email" class="form-control" name="txtEmail" required>
			</div>
			<div class="form-group">
				<label for="pwdSenha" class="form-label">Senha:</label> 
				<input type="password" class="form-control" name="pwdSenha" required>
			</div>
			<div class="form-group">
				<input type="submit" class="btn btn-primary" name="btnEnviar" value="Entrar">
			</div>
		</form>
	</div>
</div> 








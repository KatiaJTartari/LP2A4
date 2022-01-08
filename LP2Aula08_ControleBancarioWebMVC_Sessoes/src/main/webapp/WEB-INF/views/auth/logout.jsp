<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<div class="row">
	<div class="col-xl-12">
		<p>Tem certeza que deseja sair?</p>
	</div>
</div>

<div class="row">
	<div class="col-xl-12">
		<form method="POST" action="${pageContext.request.contextPath}/logout">
			<div class="form-group">
				<input type="submit" class="btn btn-danger" name="btnEnviar" value="Sair">
			</div>
		</form>
	</div>
</div>





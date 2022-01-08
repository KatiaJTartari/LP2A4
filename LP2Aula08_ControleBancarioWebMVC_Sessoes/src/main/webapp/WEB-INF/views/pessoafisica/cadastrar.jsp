<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<form method="POST"
	  action="${pageContext.request.contextPath}/pessoafisica/cadastrar">
	<div class ="mb-3 row"> 
		<label for="numCpf" class="form-label">CPF:</label>
		<input type="num" name="numCpf" required class="form-control">
	</div>
	
	<div class ="mb-3 row"> 
		<label for="txtNome" class="form-label">Nome:</label>
		<input type="text" name="txtNome" required class="form-control">
	</div>
	
	<div class ="mb-3 row"> 
		<label for="datNascto" class="form-label">Data de Nascimento:</label>
		<input type="date" name="datNascto" class="form-control">
	</div>
	
	<div class ="mb-3 row"> 
		<label for="txtEndereco" class="form-label">Endereço</label>
		<input type="text" name="txtEndereco" class="form-control">
	</div>
	
	<div class ="mb-3 row"> 
		<label for="numCep" class="form-label">CEP:</label>
		<input type="number" name="numCep" class="form-control">
	</div>
	
	<div class ="mb-3 row"> 
		<label for="txtTelefone" class="form-label">Telefone:</label>
		<input type="text" name="txtTelefone" class="form-control">
	</div>
	
	<div class ="mb-3 row"> 
		<label for="numRenda" class="form-label">Renda (R$):</label>
		<input type="number" name="numRenda" step=".01" class="form-control">
	</div>
	
	<div class ="mb-3 row"> 
		<label for="selSituacao" class="form-label">Situação</label>
		<select name="selSituacao" disabled class="form-select">
			<option value="">- Selecione -</option>
			<option value="0">Inativo</option>
			<option value="1" selected>Ativo</option>
		</select>
	</div>
	
	<div class ="mb-3 row"> 
		<label for="txtEmail" class="form-label">E-mail:</label>
		<input type="email" name="txtEmail" required class="form-control" placeholder="name@example.com">
	</div>
		
	<div class ="mb-3 row"> 
		<label for="txtSenha" class="form-label">Senha:</label>
		<input type="password" name="pwdSenha" required class="form-control">
	</div>
	
	<div class ="mb-3 row"> 
		<input type="submit" value="Enviar" class="form-control btn btn-primary">
	</div>
</form>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="modelo.PessoaFisica"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   
<%
PessoaFisica pessoaFisica = (PessoaFisica) request.getAttribute("pessoaFisica");
%>

<form method="POST"
	  action="${pageContext.request.contextPath}/pessoafisica/editar">
	<div class ="mb-3 row">
		<label for="numCpf" class="form-label">CPF:</label>
		<input type="num" name="numCpf" required class="form-control" value="<%=pessoaFisica.getCpf()%>">
		<input type="hidden" name="numId" value="<%=pessoaFisica.getId()%>">
	</div>
	
	<div class ="mb-3 row">
		<label for="txtNome" class="form-label">Nome:</label>
		<input type="text" name="txtNome" required class="form-control" value="<%=pessoaFisica.getNome()%>">
	</div>
	
	<div class ="mb-3 row">
		<%
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String datNasctoValue = "";
			
			if(pessoaFisica.getNascto() != null)
			{
				datNasctoValue = dateFormat.format(pessoaFisica.getNascto());
			}
		%>
		
		<label for="datNascto" class="form-label">Data de Nascimento:</label>
		<input type="date" name="datNascto" class="form-control" value="<%=datNasctoValue%>"> <!-- yyyy-MM-dd -->
	</div>
	
	<div class ="mb-3 row">
		<label for="txtEndereco" class="form-label">Endereço</label>
		<input type="text" name="txtEndereco" class="form-control" 
		value="<%=(pessoaFisica.getEndereco() != null ? pessoaFisica.getEndereco() : "")%>">
	</div>
	
	<div class ="mb-3 row">
		<label for="numCep" class="form-label">CEP:</label>
		<input type="number" name="numCep" class="form-control" value="<%=pessoaFisica.getCep()%>">
	</div>
	
	<div class ="mb-3 row">
		<label for="txtTelefone" class="form-label">Telefone:</label>
		<input type="text" name="txtTelefone" class="form-control" 
		value="<%=(pessoaFisica.getTelefone() != null ? pessoaFisica.getTelefone() : "")%>">
	</div>
	
	<div class ="mb-3 row">
		<label for="numRenda" class="form-label">Renda (R$):</label>
		<input type="number" name="numRenda" class="form-control" step=".01" value="<%=pessoaFisica.getRenda()%>">
	</div>
	
	<div class ="mb-3 row">
		<%
		String statusAtivo = "", statusInativo = "";
		
		if(pessoaFisica.getSituacao() == 1)
			statusAtivo = "selected";
		else
			statusInativo = "selected";
		%>
		
		<label for="selSituacao" class="form-label">Situação</label>
		<select name="selSituacao" class="form-select" required>
			<option value="">- Selecione -</option>
			<option value="0" <%=statusInativo%>>Inativo</option>
			<option value="1" <%=statusAtivo%>>Ativo</option>
		</select>
	</div>
	
	<div class ="mb-3 row"> 
		<label for="txtEmail" class="form-label">E-mail:</label>
		<input type="email" name="txtEmail" required class="form-control" value="<%=pessoaFisica.getEmail()%>">
	</div>
		
	<div class ="mb-3 row"> 
		<label for="txtSenha" class="form-label">E-mail:</label>
		<input type="password" name="pwdSenha" required class="form-control" value="<%=pessoaFisica.getSenha()%>">
	</div>
	
	<div class ="mb-3 row">
		<input type="submit" value="Enviar" class="form-control btn btn-primary">
	</div>
</form>
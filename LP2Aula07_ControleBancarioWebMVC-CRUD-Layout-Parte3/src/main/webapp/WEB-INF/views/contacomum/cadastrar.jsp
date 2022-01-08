<%@page import="modelo.PessoaFisica"%>
<%@page import="java.util.Collection"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%
Collection<PessoaFisica> pessoasFisicasAtivas =
	(Collection<PessoaFisica>) request.getAttribute("pessoasFisicasAtivas");
%>

<form method="POST"
	  action="${pageContext.request.contextPath}/contacomum/cadastrar">
	<p>
		<label for="selTitulares">Titulares: </label>
		<select name="selTitulares" multiple required>
			<%
			for(PessoaFisica pf : pessoasFisicasAtivas)
			{
				out.print("<option value=\"");
				
				out.print(pf.getId());
				
				out.print("\">"); // <option value="XX">
				
				out.print(pf.getNome());
				
				out.print("</option>");
			}
			%>
		</select>
	</p>
	<p>
		<label for="selSituacao">Situação: </label>
		<select name="selSituacao" required>
			<option value="" selected>- Selecione -</option>
			<option value="0">Inativa</option>
			<option value="1">Ativa</option>
		</select>
	</p>
	<p>
		<label for="pwdSenha">Senha: </label>
		<input type="password" name="pwdSenha" required>
	</p>
	<p>
		<label for="numSaldo">Saldo inicial: R$</label>
		<input type="number" name="numSaldo" step="0.01">
	</p>
	<p>
		<input type="submit" value="Enviar">
	</p>
</form>
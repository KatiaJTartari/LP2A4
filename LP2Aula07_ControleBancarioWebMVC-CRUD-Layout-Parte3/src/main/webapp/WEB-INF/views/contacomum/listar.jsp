<%@page import="java.text.NumberFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="modelo.ContaComum"%>
<%@page import="java.util.Collection"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%
Collection<ContaComum> contasComuns = 
	(Collection<ContaComum>) request.getAttribute("contasComuns");
%>

<nav>
	<a href="${pageContext.request.contextPath}/contacomum/cadastrar">
		Cadastrar nova conta comum
	</a>
</nav>
 
<table>
	<thead>
		<th>Número</th>
		<th>Data de Abertura</th>
		<th>Data de Fechamento</th>
		<th>Situação</th>
		<th>Saldo</th>
		<th></th>
	</thead>
	<tbody>
		<%
		for(ContaComum cc : contasComuns)
		{
			out.write("<tr>");
			
			out.write("<td>" + cc.getNumero() + "</td>");
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			
			out.write("<td>" + dateFormat.format(cc.getAbertura()) + "</td>");
			
			if(cc.getFechamento() != null)
			{
				out.write("<td>" + dateFormat.format(cc.getFechamento()) + "</td>");
			}
			else
			{
				out.write("<td></td>");
			}
			
			out.write("<td>" + 
					(cc.getSituacao() == 1 ? "Ativa" : "Inativa") +
					"</td>" );
			
			NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
			
			out.write("<td>" + currencyFormat.format(cc.getSaldo()) + "</td>");
			
			out.write("<td>");
			
			out.write("<a href=\"" + request.getContextPath() +
					"/contacomum/editar?numero=" + cc.getNumero() + "\">editar</a> ");
			
			out.write("<a href=\"" + request.getContextPath() +
					"/contacomum/excluir?numero=" + cc.getNumero() + "\">excluir</a>");
			
			out.write("</td>");
			
			out.write("</tr>");
		}
		%>
	</tbody>
</table>
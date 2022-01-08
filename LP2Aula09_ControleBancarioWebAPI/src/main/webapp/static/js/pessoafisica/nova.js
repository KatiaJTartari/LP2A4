/**
 * 
 */

$(document).ready(function(){
	var resourceURI ="http://localhost:8080/LP2Aula09_ControleBancarioWebAPI/webapi/pessoafisica";
	
	function sucessCase(data) {
		console.log("Pessoa física cadastrada com sucesso.");
		alert("Pessoa física cadastrada com sucesso.");
		window.location = "todas.html";
	}
	
	$("#novaPessoaFisicaForm").submit(function(e) {
		
		e.preventDefault();
			
		$.ajax(
			{
				type: "POST",
			    url: resourceURI,
                contentType: "application/json; charset=ISO-8859-1",
			    dataType: "json",
			    async: false,
			    data: {
				    "cep": parseInt($("input[name$=numCep]").val()),
                    "email": $("input[name$=emlEmail]").val(),
                    "endereco": $("input[name$=txtEndereco]").val(),
                    "nome": $("input[name$=txtNome]").val(),
                    "renda": parseFloat($("input[name$=numRenda]").val()),
                    "senha": $("input[name$=pwdSenha]").val(),
                    "situacao": parseInt($("select[name$=selSituacao]").val()),
                    "telefone": $("input[name$=txtTelefone]").val(),
				    "cpf": parseInt($("input[name$=numCpf]").val()),
                    "nascto": $("input[name$=datNascto]").val(),
			 },
			 success: sucessCase
		    }
	     );
	
	});
});
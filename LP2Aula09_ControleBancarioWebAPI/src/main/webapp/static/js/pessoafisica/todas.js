/**
 * 
 */

$(document).ready(function(){
	
	var resourceURI ="http://localhost:8080/LP2Aula09_ControleBancarioWebAPI/pessoafisica";
	
	function processData(data) {
		
		var tableBody = $("#tblPessoasFisicas tbody"); 
		
		$.each(data, function(index, value) {
			tableBody.append(
				"<tr>" +
				    "<th scope=\"row\">" + value.id + "</th>" +
                    "<td>" + value.cpf + "</td>" +
                    "<td>" + value.nome + "</td>" +
                    "<td>" + value.email + "</td>" +
                    "<td>" + value.telefone + "</td>" + 
                    "<td>" + value.endereco + "</td>" +
                    "<td>" + value.cep + "</td>" +
                    "<td>" + value.nascto + "</td>" +
                    "<td>" + value.renda + "</td>" +
                    "<td>" + value.situacao + "</td>" +
				 "</tr>");
		});
	}
	
	$.ajax(
		{
			type: "GET",
			url: resourceURI,
			dataType: "json",
			async: false,
			success: processData
		  }
	);
	
});
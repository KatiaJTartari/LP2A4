import java.util.ArrayList;
import java.util.Date;

import modelo.ContaComum;
import modelo.PessoaFisica;
import modelo.repositorio.ContaComumDAO;
import modelo.repositorio.PessoaFisicaDAO;

public class Main {

	public static void main(String[] args) {
		
		try
		{
			/* EXEMPLO DE EXECUÇÃO 1 */
			
			PessoaFisica p1 = new PessoaFisica();
			
			p1.setNome("Maria da Silva");
			p1.setEndereco("Av. Brasil, 1500, Sao Paulo - SP");
			p1.setCep(99999999);
			p1.setTelefone("(99) 9999-9999");
			p1.setRenda(5750.75f);
			p1.setCpf(99999999999l);
			p1.setNascto(new Date(1978-1900,5,12)); // ver: https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Date.html#%3Cinit%3E(int,int,int)
			
			PessoaFisica p2 = new PessoaFisica();
			
			p2.setNome("Jose dos Santos");
			p2.setEndereco("Rua 9 de Julho, 240, Sao Paulo - SP");
			p2.setCep(88888888);
			p2.setTelefone("(88) 8888-8888");
			p2.setRenda(4525.5f);
			p2.setCpf(88888888888l);
			p2.setNascto(new Date(1990-1900,10,1));
			
			PessoaFisicaDAO pfDAO = new PessoaFisicaDAO(); // Faz abertura da conexão
			
			pfDAO.criarPessoaFisica(p1); // Executa a operação no BD
			
			System.out.println("ID da pessoa física inserida: " + p1.getId());
			
			pfDAO.criarPessoaFisica(p2);
			
			System.out.println("ID da pessoa física inserida: " + p2.getId());
			
			pfDAO.fecharConexao(); // Fecha a conexão
						
			/* EXEMPLO DE EXECUÇÃO 2 */
			
			PessoaFisicaDAO pfDAO2 = new PessoaFisicaDAO();
			
			ArrayList<PessoaFisica> pessoasFisicas = pfDAO2.recuperarPessoasFisicas();
			
			//pessoasFisicas.forEach(pf -> System.out.println("Nome: " + pf.getNome()));
			
			for (PessoaFisica pf : pessoasFisicas)
			{
				System.out.println();
				System.out.println("ID: " + pf.getId());
				System.out.println("Nome: " + pf.getNome());
				System.out.println("Endereço: " + pf.getEndereco());
				System.out.println("CEP: " + pf.getCep());
				System.out.println("Telefone: " + pf.getTelefone());
				System.out.println("Situação: " + 					
						(pf.getSituacao() == 1 ? "Ativo" : "Inativo")
				);
				System.out.println();
			}
			
			pfDAO2.fecharConexao();
			
			/* EXEMPLO DE EXECUÇÃO 3 */
			
			ContaComum cc1 = ContaComum.abrirConta();
			
			cc1.setSenha(123456);
			
			cc1.getTitulares().add(p1);
			cc1.getTitulares().add(p2);
			
			ContaComumDAO ccDAO = new ContaComumDAO();
			
			ccDAO.criarContaComum(cc1);
			
			System.out.println("Conta comum n. " + cc1.getNumero() + " criada.");
			
			ccDAO.incluirTitulares(cc1);
			
			ccDAO.fecharConexao();
		}
		catch (Exception e)
		{
			System.out.println("Erro ao executar: " + e.getMessage());
			e.printStackTrace();
		}
	}
}


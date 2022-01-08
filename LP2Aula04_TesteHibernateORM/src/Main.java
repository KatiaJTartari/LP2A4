import java.util.Collection;
import java.util.Date;

import modelo.ContaComum;
import modelo.PessoaFisica;
import modelo.repositorio.ContaComumRepositorio;
import modelo.repositorio.PersistenceConfig;
import modelo.repositorio.PessoaFisicaRepositorio;
import modelo.repositorio.Repositorio;

public class Main
{
	public static void main(String[] args) 
	{
		
		//Primeiro executa esse:
		/*
		PessoaFisica pf1 = new PessoaFisica();
		
		pf1.setNome("Maria da Silva");
		pf1.setEndereco("Av. Brasil, 1500, Sao Paulo - SP");
		pf1.setCep(99999999);
		pf1.setTelefone("(99) 9999-9999");
		pf1.setRenda(5500.25f);
		pf1.setCpf(99999999999l);
		pf1.setNascto(new Date(1985-1900,5,25));
		
		Repositorio<PessoaFisica> repPF = new Repositorio<PessoaFisica>();
		
		repPF.criar(pf1);
		
		
		ContaComum cc1 = ContaComum.abrirConta();
		
		cc1.setSenha(123456);
		
		Repositorio<ContaComum> repCC = new Repositorio<ContaComum>();
		
		repCC.criar(cc1);		
		
		pf1.getContas().add(cc1);
		
		repPF.atualizar(pf1); 
	    */
		
		
		//Depois executa esse:
		/*
     	PessoaFisicaRepositorio repPF2 = new PessoaFisicaRepositorio();
		
		Collection<PessoaFisica> pessoasFisicasCadastradas =
				repPF2.recuperarPessoasFisicas();
		
		for (PessoaFisica pf : pessoasFisicasCadastradas) {
			System.out.println();
			
			System.out.println("ID: " + pf.getId());
			System.out.println("Nome: " + pf.getNome());
			System.out.println("Endereco: " + pf.getEndereco());
			System.out.println("CEP: " + pf.getCep());
			System.out.println("Telefone: " + pf.getTelefone());
			System.out.println("Renda: " + pf.getRenda());
			System.out.println("Situacao: " + 
					(pf.getSituacao() == 1 ? "Ativo" : "Inativo"));
			
			System.out.println("Contas relacionadas:");
			
			for (ContaComum cc : pf.getContas()) {
				System.out.println("\tConta Numero: " + cc.getNumero() +
						" | Saldo: " + cc.getSaldo());
			}
			
			System.out.println();
		}
		*/
		
		PersistenceConfig.closeEntityManager();
	}

}
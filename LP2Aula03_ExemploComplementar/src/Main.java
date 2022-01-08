import java.time.*;
import modelo.*;
import modelo.repositorio.*;

/**
 * Opção para se trabalhar com banco de dados PostgreSQL on-line:
 * https://www.elephantsql.com/
 **/
public class Main
{
	public static void main(String[] args)
	{
    // Instancia objetos repositorios
		ContaComumDAO ccDao = new ContaComumDAO();
		MovimentoDAO mvDao = new MovimentoDAO();
		
		// Numero da conta a ser persistida
		long numeroContaExemplo = 77777777;
		
		// Cria e persite um objeto cc1 do tipo ContaComum com saldo 0.0
		ContaComum cc1 = new ContaComum();
		cc1.setNumeroConta(numeroContaExemplo);
		cc1.setAberturaConta(LocalDate.now());
		cc1.setFechamentoConta(null);
		cc1.setSituacaoConta(1);
		cc1.setSenhaConta(1234);
		cc1.setSaldoConta(0.0);
		
		ccDao.criarContaComum(cc1);
		
		// Cria e persite um movimento m1 da conta cc1 - Depósito de 1000
		Movimento m1 = new Movimento();
		
		m1.setContaMovimento(cc1);
		m1.registrarMovimento(1, 1000.0); // 1 = Deposito
		
    if(m1.getIdMovimento() > 0)
    {
		  System.out.println("MV ID " + m1.getIdMovimento() + ": Depósito de " + m1.getValorMovimento() + " realizado!\n");
    }
		
		// Cria e persite um movimento m2 da conta cc1 - Saque de 250
		Movimento m2 = new Movimento();
		
		m2.setContaMovimento(cc1);
		m2.registrarMovimento(2, 250.0); // 2 = Saque
		
    if(m2.getIdMovimento() > 0)
    {
		  System.out.println("MV ID " + m2.getIdMovimento() + ": Saque de " + m2.getValorMovimento() + " realizado!\n");
    }
		
		// Exemplo de movimento m3 inválido para cc1 neste momento - Saque de 1500 (saldo insuficiente)
		Movimento m3 = new Movimento();
		
		m3.setContaMovimento(cc1);
		m3.registrarMovimento(2, 1500.0); // 2 = Saque
		
		System.out.println("");
		
		// Cria e persite um movimento m4 da conta cc1 - Depósito de 2000
		Movimento m4 = new Movimento();
		
		m4.setContaMovimento(cc1);
		m4.registrarMovimento(1, 2000.0); // 1 = Deposito
		
    if(m4.getIdMovimento() > 0)
    {
		  System.out.println("MV ID " + m4.getIdMovimento() + ": Depósito de " + m4.getValorMovimento() + " realizado!\n");
    }
		
		// Agora m3 pode ser realizado/persistido para a conta cc1 - Saque de 1500
		m3.registrarMovimento(2, 1500.0); // 2 = Saque
		
    if(m3.getIdMovimento() > 0)
    {
		  System.out.println("MV ID " + m3.getIdMovimento() + ": Saque de " + m3.getValorMovimento() + " realizado!\n");
    }
		
		// Recupera a conta persistida do banco de dados e seus movimentos
		ContaComum contaRecuperadaBD = ccDao.obterContaComumPorNumeroConta(numeroContaExemplo);
		
		if (contaRecuperadaBD != null)
		{
			System.out.println("\n\nConta recuperada: numero = " + contaRecuperadaBD.getNumeroConta() + ", saldo = "
					+ contaRecuperadaBD.getSaldoConta());
			
			contaRecuperadaBD.setMovimentosConta(mvDao.obterMovimentosPorNumeroConta(numeroContaExemplo));
			
			for (Movimento mv : contaRecuperadaBD.getMovimentosConta())
			{
				System.out.println("MV ID " + mv.getIdMovimento() + ", tipo = " + mv.getTipoMovimento() + ", valor = "
						+ mv.getValorMovimento());
			}
		}
		
		// Fecha as conexões utilizadas
		ccDao.fecharConexao();
		mvDao.fecharConexao();
		
		Pessoa p1 = new Pessoa();
		
		p1.setNomePessoa("José da Silva");
		p1.setEnderecoPessoa("Av. Brasil, 2017");
		p1.setCepPessoa(90860510);
		p1.setTelefonePessoa("(55) 3527-7263");
		p1.setRendaPessoa(5000.0);
		
		PessoaDAO pDao = new PessoaDAO();
		
		p1.setIdPessoa(pDao.criarPessoa(p1));
		
    if(p1.getIdPessoa() > 0)
    {
		  System.out.println("\nId da pessoa inserida: " + p1.getIdPessoa());
    }

		pDao.fecharConexao();
	}
}
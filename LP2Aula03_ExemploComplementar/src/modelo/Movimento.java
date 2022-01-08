package modelo;

import java.time.*;

import modelo.repositorio.*;

public class Movimento
{
	private int idMovimento;
	private int tipoMovimento;
	private LocalDateTime dataHoraMovimento;
	private double valorMovimento;
	private ContaComum contaMovimento;
	
	public int getIdMovimento()
	{
		return idMovimento;
	}
	
	public void setIdMovimento(int id)
	{
		this.idMovimento = id;
	}
	
	public int getTipoMovimento()
	{
		return tipoMovimento;
	}
	
	public void setTipoMovimento(int tipoMovimento)
	{
		this.tipoMovimento = tipoMovimento;
	}
	
	public LocalDateTime getDataHoraMovimento()
	{
		return dataHoraMovimento;
	}
	
	public void setDataHoraMovimento(LocalDateTime dataHoraMovimento)
	{
		this.dataHoraMovimento = dataHoraMovimento;
	}
	
	public double getValorMovimento()
	{
		return valorMovimento;
	}
	
	public void setValorMovimento(double valorMovimento)
	{
		this.valorMovimento = valorMovimento;
	}
	
	public ContaComum getContaMovimento()
	{
		return contaMovimento;
	}
	
	public void setContaMovimento(ContaComum contaMovimento)
	{
		this.contaMovimento = contaMovimento;
	}
	
	// Método interno auxiliar.
	// Optei por implementá-lo para que o método registrarMovimento não ficasse tão extenso.
	private boolean efetivarMovimento()
	{
		boolean resultado = false; // true=Sucesso e false=Falha
		
		if (this.contaMovimento != null)
		{
			ContaComumDAO ccDAO = new ContaComumDAO();
			
			// Garante que eu tenho em this.contaMovimento os dados mais atuais da conta
			this.contaMovimento = ccDAO.obterContaComumPorNumeroConta(this.contaMovimento.getNumeroConta());
			
			if (this.contaMovimento != null) // Se deu certo a consulta anterior
			{
				if (this.tipoMovimento == 1) // Depósito
				{
					this.contaMovimento.setSaldoConta(this.contaMovimento.getSaldoConta() + this.valorMovimento);
					ccDAO.atualizarContaComum(this.contaMovimento);
					resultado = true;
					
					System.out.println("Depósito efetuado com sucesso!");
				} else if (this.tipoMovimento == 2) // Saque
				{
					if (this.contaMovimento.getSaldoConta() >= this.valorMovimento)
					{
						// Só é possível sacar se existir saldo suficiente
						this.contaMovimento.setSaldoConta(this.contaMovimento.getSaldoConta() - this.valorMovimento);
						ccDAO.atualizarContaComum(this.contaMovimento);
						resultado = true;
						
						System.out.println("Saque efetuado com sucesso!");
					} else
					{
						System.out.println("Saque não pôde ser efetivado. Sem saldo!");
					}
				}
			}
			
			ccDAO.fecharConexao();
		}
		
		return resultado;
	}
	
	public int registrarMovimento(int tipoMovimento, double valorMovimento)
	{
		int resultado = 0; // 1=Sucesso e 0=Fracasso
		
		if (this.contaMovimento != null) // Somente registra se existir conta vinculada
		{
			this.tipoMovimento = tipoMovimento;
			this.dataHoraMovimento = LocalDateTime.now();
			this.valorMovimento = valorMovimento;
			
			if (this.efetivarMovimento()) // Somente registra se o movimento for efetivado na conta
			{
				MovimentoDAO mDAO = new MovimentoDAO();
				
				this.idMovimento = mDAO.criarMovimento(this); // Persiste este objeto, registrando o movimento no BD.
				
				if (this.idMovimento > 0) // Sucesso na inserção
				{
					resultado = 1;
				}
				
				mDAO.fecharConexao();
			}
		}
		
		return resultado;
	}
}
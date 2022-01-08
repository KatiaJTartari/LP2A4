package modelo;

import java.time.LocalDateTime;

import modelo.repositorio.ContaComumDAO;
import modelo.repositorio.MovimentoDAO;

public class Movimento {
	private int idMov;
	private LocalDateTime dataHora;
	private double valor;
    private ContaComum contaMov;
    private int tipoMov;
	
	public int getIdMov() {
		return idMov;
	}
	
	public void setIdMov(int idMov) {
		this.idMov = idMov;
	}
	
	public LocalDateTime getDataHora() {
		return dataHora;
	}
	
	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}
	
	public double getValor() {
		return valor;
	}
	
	public void setValor(double valor) {
		this.valor = valor;
	}
	
	public ContaComum getContaMov(){
		return contaMov;
	}
	
	public void setContaMov(ContaComum contaMov){
		this.contaMov = contaMov;
	}
	
	public int getTipoMov(){
		return tipoMov;
	}
	
	public void setTipoMov(int tipoMov)
	{
		this.tipoMov = tipoMov;
	}
	
	// Método interno auxiliar.
	// Foi implementado para que o método registrarMovimento não ficasse tão extenso.
	private boolean efetivarMovimento()
	{
		boolean resultado = false; // true=Sucesso e false=Falha
		
		if (this.contaMov != null)
		{
			ContaComumDAO ccDAO = new ContaComumDAO();
			
			// Garante que eu tenho em this.contaMovimento os dados mais atuais da conta
			//this.contaMov = ccDAO.recuperarContaComumPorNumero(this.contaMov.getNumero());
			this.contaMov = ccDAO.obterContaComumPorNumeroConta(this.contaMov.getNumero());
			
			if (this.contaMov != null) // Se deu certo a consulta anterior
			{
				if (this.tipoMov == 1) // Depósito
				{
					this.contaMov.setSaldo(this.contaMov.getSaldo() + this.valor);
					//ccDAO.atualizarContaComum(this.contaMov);
					ccDAO.editarContaComum(this.contaMov);
					resultado = true;
					
					System.out.println("Depósito efetuado com sucesso!");
				} else if (this.tipoMov == 2) // Saque
				{
					if (this.contaMov.getSaldo() >= this.valor)
					{
						// Só é possível sacar se existir saldo suficiente
						this.contaMov.setSaldo(this.contaMov.getSaldo() - this.valor);
						//ccDAO.atualizarContaComum(this.contaMov);
						ccDAO.editarContaComum(this.contaMov);
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
	
	public int registrarMovimento(int tipoMov, double valor)
	{
		int resultado = 0; // 1=Sucesso e 0=Fracasso
		
		if (this.contaMov != null) // Somente registra se existir conta vinculada
		{
			this.tipoMov = tipoMov;
			this.dataHora = LocalDateTime.now();
			this.valor = valor;
			
			if (this.efetivarMovimento()) // Somente registra se o movimento for efetivado na conta
			{
				MovimentoDAO mDAO = new MovimentoDAO();
				
				this.idMov = mDAO.criarMovimento(this); // Persiste este objeto, registrando o movimento no BD.
				
				if (this.idMov > 0) // Sucesso na inserção
				{
					resultado = 1;
				}
				
				mDAO.fecharConexao();
			}
		}
		return resultado;
	}
}

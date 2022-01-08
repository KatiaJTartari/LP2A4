package exemploComplementar;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PessoaFisica extends Pessoa implements Funcionario {
	private long cpf;
	private String telefoneCelular;
	private LocalDate nascimento;
	private String naturalidade;
	private boolean valeTransporte;
	private double valorValeTransporte;
	private boolean valeAlimentacao;
	private double valorValeAlimentacao;

	/**
	 * Método construtor da classe.
	 */
	public PessoaFisica(String nome, String telefoneFixo, double rendaBruta, String login, String senha, long cpf,
			LocalDate nascimento, String naturalidade) {
		super(nome, telefoneFixo, rendaBruta, login, senha); // Chamada ao construtor da superclasse.
		this.cpf = cpf;
		this.nascimento = nascimento;
		this.naturalidade = naturalidade;
	}

	/* Métodos Getters e Setters */

	public long getCpf() {
		return this.cpf;
	}

	public void setCpf(long cpf) {
		this.cpf = cpf;
	}

	public String getTelefoneCelular() {
		return this.telefoneCelular;
	}

	public void setTelefoneCelular(String telefoneCelular) {
		this.telefoneCelular = telefoneCelular;
	}

	public LocalDate getNascimento() {
		return this.nascimento;
	}

	public void setNascimento(LocalDate nascimento) {
		this.nascimento = nascimento;
	}

	public String getNaturalidade() {
		return this.naturalidade;
	}

	public void setNaturalidade(String naturalidade) {
		this.naturalidade = naturalidade;
	}

	public boolean getValeTransporte() {
		return this.valeTransporte;
	}

	public void setValeTransporte(boolean valeTransporte) {
		this.valeTransporte = valeTransporte;
	}

	public double getValorValeTransporte() {
		return this.valorValeTransporte;
	}

	public void setValorValeTransporte(double valorValeTransporte) {
		this.valorValeTransporte = valorValeTransporte;
	}

	public boolean getValeAlimentacao() {
		return this.valeAlimentacao;
	}

	public void setValeAlimentacao(boolean valeAlimentacao) {
		this.valeAlimentacao = valeAlimentacao;
	}

	public double getValorValeAlimentacao() {
		return this.valorValeAlimentacao;
	}

	public void setValorValeAlimentacao(double valorValeAlimentacao) {
		this.valorValeAlimentacao = valorValeAlimentacao;
	}

	/* Implementação da Interface Funcionario */

	public double calcularRendaLiquida() {
		if (this.valeTransporte) {
			return super.rendaBruta - (0.08 * super.rendaBruta);
		}
		return super.rendaBruta;
	}
}
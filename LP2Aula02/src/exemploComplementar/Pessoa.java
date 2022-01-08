package exemploComplementar;

import java.util.Collection;
import java.util.ArrayList;
import java.time.LocalDate;

public abstract class Pessoa implements Cliente, Fornecedor, Usuario {
	protected String nome;
	protected String telefoneFixo;
	protected double rendaBruta;
	protected String login;
	protected String senha;
	protected Collection<Acesso> acessos;
	protected Collection<Endereco> enderecos;

	/**
	 * Método construtor da classe.
	 */
	public Pessoa(String nome, String telefoneFixo, double rendaBruta, String login, String senha) {
		this.nome = nome;
		this.telefoneFixo = telefoneFixo;
		this.rendaBruta = rendaBruta;
		this.login = login;
		this.senha = senha;

		// Instancia coleções de objetos.
		this.acessos = new ArrayList<Acesso>();
		this.enderecos = new ArrayList<Endereco>();
	}

	/* Métodos Getters e Setters */

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefoneFixo() {
		return this.telefoneFixo;
	}

	public void setTelefoneFixo(String telefoneFixo) {
		this.telefoneFixo = telefoneFixo;
	}

	public double getRendaBruta() {
		return this.rendaBruta;
	}

	public void setRendaBruta(double rendaBruta) {
		this.rendaBruta = rendaBruta;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Collection<Acesso> getAcessos() {
		return this.acessos;
	}

	public Collection<Endereco> getEnderecos() {
		return this.enderecos;
	}

	/* Implementação da Interface Cliente */

	public String obterPedidosDoPeriodo(LocalDate inicio, LocalDate fim) {
		String relatorioPedidos = "";
		// Lógica para recuperar os pedidos do cliente no período especificado.
		return relatorioPedidos;
	}

	/* Implementação da Interface Fornecedor */

	public String emitirFaturaDoPeriodo(LocalDate inicio, LocalDate fim) {
		String fatura = "";
		// Lógica para recuperar os itens de fatura a serem pagos ao Fornecedor no
		// período especificado.
		return fatura;
	}

	/* Implementação da Interface Usuario */

	public String obterUltimoAcesso() {
		String dadosUltimoAcesso = "";
		// Lógica para recuperar os dados do último acesso da pessoa.
		return dadosUltimoAcesso;
	}

	public String obterHistoricoAcessos() {
		String dadosHistoricosAcessos = "";
		// Lógica para recuperar os dados dos acessos da pessoa ao sistema.
		return dadosHistoricosAcessos;
	}
}
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
	 * M�todo construtor da classe.
	 */
	public Pessoa(String nome, String telefoneFixo, double rendaBruta, String login, String senha) {
		this.nome = nome;
		this.telefoneFixo = telefoneFixo;
		this.rendaBruta = rendaBruta;
		this.login = login;
		this.senha = senha;

		// Instancia cole��es de objetos.
		this.acessos = new ArrayList<Acesso>();
		this.enderecos = new ArrayList<Endereco>();
	}

	/* M�todos Getters e Setters */

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

	/* Implementa��o da Interface Cliente */

	public String obterPedidosDoPeriodo(LocalDate inicio, LocalDate fim) {
		String relatorioPedidos = "";
		// L�gica para recuperar os pedidos do cliente no per�odo especificado.
		return relatorioPedidos;
	}

	/* Implementa��o da Interface Fornecedor */

	public String emitirFaturaDoPeriodo(LocalDate inicio, LocalDate fim) {
		String fatura = "";
		// L�gica para recuperar os itens de fatura a serem pagos ao Fornecedor no
		// per�odo especificado.
		return fatura;
	}

	/* Implementa��o da Interface Usuario */

	public String obterUltimoAcesso() {
		String dadosUltimoAcesso = "";
		// L�gica para recuperar os dados do �ltimo acesso da pessoa.
		return dadosUltimoAcesso;
	}

	public String obterHistoricoAcessos() {
		String dadosHistoricosAcessos = "";
		// L�gica para recuperar os dados dos acessos da pessoa ao sistema.
		return dadosHistoricosAcessos;
	}
}
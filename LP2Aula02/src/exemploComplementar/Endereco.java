package exemploComplementar;

public class Endereco {

	private TipoLogradouro tipo;
	private String logradouro;
	private int numero;
	private String bairro;
	private String cidade;
	private String estado;
	private String pais;
	private long cep;
	private boolean principal;
	private Pessoa pessoa;

	/**
	 * Método construtor da classe.
	 */
	public Endereco(Pessoa pessoa, TipoLogradouro tipo, String logradouro, int numero, String bairro, String cidade,
			String estado, String pais, long cep, boolean principal) {
		this.pessoa = pessoa;
		this.tipo = tipo;
		this.logradouro = logradouro;
		this.numero = numero;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.pais = pais;
		this.cep = cep;
		this.principal = principal;
	}

	/* Métodos Getters e Setters */

	public TipoLogradouro getTipo() {
		return this.tipo;
	}

	public void setTipo(TipoLogradouro tipo) {
		this.tipo = tipo;
	}

	public String getLogradouro() {
		return this.logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public int getNumero() {
		return this.numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return this.bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return this.cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getPais() {
		return this.pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public long getCep() {
		return this.cep;
	}

	public void setCep(long cep) {
		this.cep = cep;
	}

	public boolean getPrincipal() {
		return this.principal;
	}

	public void setPrincipal(boolean principal) {
		this.principal = principal;
	}

	public Pessoa getPessoa() {
		return this.pessoa;
	}
}

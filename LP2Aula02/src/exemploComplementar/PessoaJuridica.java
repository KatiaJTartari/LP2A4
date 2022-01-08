package exemploComplementar;

public class PessoaJuridica extends Pessoa {
	  private long cnpj;
	  private String nomeFantasia;

	  /**
	   * Método construtor da classe.
	   */
	  public PessoaJuridica(String nome, String telefoneFixo, double rendaBruta, String login, String senha, long cnpj, String nomeFantasia) {
	    super(nome, telefoneFixo, rendaBruta, login, senha); // Chamada ao construtor da superclasse.
	    this.cnpj = cnpj;
	    this.nomeFantasia = nomeFantasia;
	  }

	  /* Métodos Getters e Setters */

	  public long getCnpj() {
	    return this.cnpj;
	  }

	  public void setCnpj(long cnjp) {
	    this.cnpj = cnpj;
	  }

	  public String getNomeFantasia() {
	    return this.nomeFantasia;
	  }

	  public void setNomeFantasia(String nomeFantasia) {
	    this.nomeFantasia = nomeFantasia;
	  }
	}

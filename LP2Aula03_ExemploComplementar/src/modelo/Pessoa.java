package modelo;

public class Pessoa
{
	protected int idPessoa;
	protected String nomePessoa;
	protected String enderecoPessoa;
	protected long cepPessoa;
	protected String telefonePessoa;
	protected double rendaPessoa;
	protected int situacaoPessoa;
	
	public Pessoa()
	{
		this.situacaoPessoa = 1;
	}
	
	/* GETTERS AND SETTERS */

	public int getIdPessoa()
	{
		return idPessoa;
	}

	public void setIdPessoa(int idPessoa)
	{
		this.idPessoa = idPessoa;
	}

	public String getNomePessoa()
	{
		return nomePessoa;
	}

	public void setNomePessoa(String nomePessoa)
	{
		this.nomePessoa = nomePessoa;
	}

	public String getEnderecoPessoa()
	{
		return enderecoPessoa;
	}

	public void setEnderecoPessoa(String enderecoPessoa)
	{
		this.enderecoPessoa = enderecoPessoa;
	}

	public long getCepPessoa()
	{
		return cepPessoa;
	}

	public void setCepPessoa(long cepPessoa)
	{
		this.cepPessoa = cepPessoa;
	}

	public String getTelefonePessoa()
	{
		return telefonePessoa;
	}

	public void setTelefonePessoa(String telefonePessoa)
	{
		this.telefonePessoa = telefonePessoa;
	}

	public double getRendaPessoa()
	{
		return rendaPessoa;
	}

	public void setRendaPessoa(double rendaPessoa)
	{
		this.rendaPessoa = rendaPessoa;
	}

	public int getSituacaoPessoa()
	{
		return situacaoPessoa;
	}

	public void setSituacaoPessoa(int situacaoPessoa)
	{
		this.situacaoPessoa = situacaoPessoa;
	}
}
package modelo;

import java.util.Date;

public class PessoaFisica extends Pessoa {

	private long cpf;
	private Date nascto;
	
	public long getCpf()
	{
		return cpf;
	}
	
	public void setCpf(long cpf)
	{
		this.cpf = cpf;
	}
	
	public Date getNascto()
	{
		return nascto;
	}
	
	public void setNascto(Date nasto)
	{
		this.nascto = nasto;
	}
}

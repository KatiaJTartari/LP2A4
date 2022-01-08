package modelo;

import java.sql.Date;

public class ContaEspecial extends ContaComum{
	private double limite;
		
	public ContaEspecial(long numero, Date abertura) {
	}

	public double getLimite() {
		return limite;
	}

	public void setLimite(double limite) {
		this.limite = limite;
	}
}

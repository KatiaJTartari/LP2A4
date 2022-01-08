package modelo;

import java.util.Date;

public class ContaPoupanca extends ContaComum{
	private Date aniversario;

	public ContaPoupanca(long long1, java.sql.Date date) {
		
	}

	public Date getAniversario() {
		return aniversario;
	}

	public void setAniversario(Date aniversario) {
		this.aniversario = aniversario;
	}
}

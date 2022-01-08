package modelo;

import java.time.*;
import java.util.*;

public class ContaComum
{
	// Nivel de visibilidade alterado de private para protected
	//   a fim de atender ao diagrama apresentado na aula.
	protected long numeroConta;
	protected LocalDate aberturaConta;
	protected LocalDate fechamentoConta;
	protected int situacaoConta;
	protected int senhaConta;
	protected double saldoConta;
	protected ArrayList<Movimento> movimentosConta;
	
	public ContaComum()
	{
		movimentosConta = new ArrayList<Movimento>();
	}
	
	public long getNumeroConta()
	{
		return numeroConta;
	}
	
	public void setNumeroConta(long numeroConta)
	{
		this.numeroConta = numeroConta;
	}
	
	public LocalDate getAberturaConta()
	{
		return aberturaConta;
	}
	
	public void setAberturaConta(LocalDate aberturaConta)
	{
		this.aberturaConta = aberturaConta;
	}
	
	public LocalDate getFechamentoConta()
	{
		return fechamentoConta;
	}
	
	public void setFechamentoConta(LocalDate fechamentoConta)
	{
		this.fechamentoConta = fechamentoConta;
	}
	
	public int getSituacaoConta()
	{
		return situacaoConta;
	}
	
	public void setSituacaoConta(int situacaoConta)
	{
		this.situacaoConta = situacaoConta;
	}
	
	public int getSenhaConta()
	{
		return senhaConta;
	}
	
	public void setSenhaConta(int senhaConta)
	{
		this.senhaConta = senhaConta;
	}
	
	public double getSaldoConta()
	{
		return saldoConta;
	}
	
	public void setSaldoConta(double saldoConta)
	{
		this.saldoConta = saldoConta;
	}

	public ArrayList<Movimento> getMovimentosConta()
	{
		return movimentosConta;
	}

	public void setMovimentosConta(ArrayList<Movimento> movimentosConta)
	{
		this.movimentosConta = movimentosConta;
	}
}
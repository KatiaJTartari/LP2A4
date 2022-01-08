package modelo.repositorio;

import java.sql.*;
import modelo.*;

public class ContaComumDAO extends FabricaConexao
{
	public boolean criarContaComum(ContaComum contaComum)
	{
		boolean resultado = true;
		
		try
		{
			String stmtSql = "insert into contascomuns (numeroconta, aberturaconta, fechamentoconta, " +
				"situacaoconta, senhaconta, saldoconta) values (?, ?, ?, ?, ?, ?)";
			
			PreparedStatement pStmt = conexao.prepareStatement(stmtSql);
			pStmt.setLong(1, contaComum.getNumeroConta());
			pStmt.setObject(2, contaComum.getAberturaConta());
			pStmt.setObject(3, contaComum.getFechamentoConta());
			pStmt.setInt(4, contaComum.getSituacaoConta());
			pStmt.setInt(5, contaComum.getSenhaConta());
			pStmt.setDouble(6, contaComum.getSaldoConta());
			
			pStmt.execute();
		}
		catch (Exception e)
		{
			System.out.println("Erro ao tentar cadastrar a conta comum! " +
					e.getMessage());
			resultado = false;
		}
		
		return resultado;
	}
	
	public int atualizarContaComum(ContaComum contaComum)
	{
		int resultado = 0;
		
		try
		{
			String stmtSql = "update contascomuns set aberturaconta = ?, fechamentoconta = ?, " +
				"situacaoconta = ?, senhaconta = ?, saldoconta = ? where numeroconta = ? ";
			
			PreparedStatement pStmt = conexao.prepareStatement(stmtSql);
			
			pStmt.setObject(1, contaComum.getAberturaConta());
			pStmt.setObject(2, contaComum.getFechamentoConta());
			pStmt.setInt(3, contaComum.getSituacaoConta());
			pStmt.setInt(4, contaComum.getSenhaConta());
			pStmt.setDouble(5, contaComum.getSaldoConta());
			pStmt.setLong(6, contaComum.getNumeroConta());
			
			resultado = pStmt.executeUpdate();
		}
		catch (Exception e)
		{
			System.out.println("Erro ao tentar atualizar a conta comum! " +
					e.getMessage());
		}
		
		return resultado;
	}
	
	public ContaComum obterContaComumPorNumeroConta(long numeroConta)
	{
		ContaComum resultado = null;
		
		try
		{
			String stmtSql = "select numeroconta, aberturaconta, fechamentoconta, " +
				"situacaoconta, senhaconta, saldoconta from contascomuns where numeroconta = ?";
			
			PreparedStatement pStmt = conexao.prepareStatement(stmtSql);
			
			pStmt.setLong(1, numeroConta);
			
			ResultSet rs = pStmt.executeQuery();
			
			if(rs.next())
			{
				resultado = new ContaComum();
				
				resultado.setNumeroConta(rs.getLong("numeroconta"));
				resultado.setAberturaConta(rs.getDate("aberturaconta").toLocalDate());
				if(rs.getDate("fechamentoconta") != null)
				{
					resultado.setFechamentoConta(rs.getDate("fechamentoconta").toLocalDate());
				}
				resultado.setSituacaoConta(rs.getInt("situacaoconta"));
				resultado.setSenhaConta(rs.getInt("senhaconta"));
				resultado.setSaldoConta(rs.getDouble("saldoconta"));
			}
		}
		catch (Exception e)
		{
			System.out.println("Erro ao tentar atualizar a conta comum! " +
					e.getMessage());
			resultado = null;
		}
		
		return resultado;
	}
}

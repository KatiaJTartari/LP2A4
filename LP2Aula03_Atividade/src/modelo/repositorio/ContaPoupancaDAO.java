package modelo.repositorio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.ContaPoupanca;

public class ContaPoupancaDAO extends ContaComumDAO{

	public void  criarContaPoupanca(ContaPoupanca contaPoupanca) throws SQLException{
		try
		{
			String stmt = "insert into contasespeciais " + "(numero, aniversario) " + "values (?, ?)";

			PreparedStatement pStmt = super.conn.prepareStatement(stmt);
			
			pStmt.setLong(1, contaPoupanca.getNumero());
			pStmt.setDate(2, new java.sql.Date(contaPoupanca.getAniversario().getTime()));
		}catch (Exception e) {
			System.out.println("Erro ao tentar inserir uma nova conta poupança! " + e.getMessage());
		}
}
	
	public ArrayList<ContaPoupanca> recuperarContasPoupancas() {
		ArrayList<ContaPoupanca> resultado = null;

		try {
			String stmt = "select numero, abertura, fechamento, situacao, senha, saldo "
					+ "limite" + "from contaspoupancas order by abertura asc";

			PreparedStatement pStmt = super.conn.prepareStatement(stmt);
			ResultSet rs = pStmt.executeQuery();
			resultado = new ArrayList<ContaPoupanca>();

			while (rs.next()) {
				ContaPoupanca cp = new ContaPoupanca(rs.getLong("numero"), rs.getDate("abertura"));
				cp.setFechamento(rs.getDate("fechamento"));
				cp.setSituacao(rs.getByte("situacao"));
				cp.setSenha(rs.getInt("senha"));
				cp.setSaldo(rs.getDouble("saldo"));
				cp.setAniversario(rs.getDate("aniversario"));
				resultado.add(cp);
			}
		} catch (Exception e) {
			System.out.println("Erro ao tentar recuperar as contas poupanças cadastradas! " + e.getMessage());
			resultado = null;
		}

		return resultado;
	}
	
	public ContaPoupanca obterContaPoupancaPorNumeroConta(long numero)
	{
		ContaPoupanca resultado = null;
		
		try
		{
			String stmtSql = "select numerocp, aberturacp, fechamentocp, " +
				"situacaocp, senhace, saldocp, aniversariocp from contaspoupancas where numerocp = ?";
			
			PreparedStatement pStmt = conn.prepareStatement(stmtSql);
			
			pStmt.setLong(1, numero);
			
			ResultSet rs = pStmt.executeQuery();
			
			if(rs.next())
			{
				resultado = new ContaPoupanca(rs.getLong("numero"), rs.getDate("abertura"));
				
				if(rs.getDate("fechamentoconta") != null)
				{
					resultado.setFechamento(rs.getDate("fechamento"));
				}
				resultado.setSituacao(rs.getByte("situacao"));
				resultado.setSenha(rs.getInt("senha"));
				resultado.setSaldo(rs.getDouble("saldo"));
				resultado.setAniversario(rs.getDate("aniversario"));
			}
		}
		catch (Exception e)
		{
			System.out.println("Erro ao tentar recuperar a conta poupança por número " + numero + e.getMessage());
			resultado = null;
		}
		
		return resultado;
	}
	
	public int editarContaPoupanca(ContaPoupanca contaPoupanca)
	{
		int resultado = 0;
		
		try {
			String stmt = "update contaspoupancas " +
		                  "set numero = ?,  abertura = ?, fechamento = ?, situacao = ?, senha = ?, saldo = ? " +
					      "aniversario = ? where numero = ?";
			
			PreparedStatement pStmt = super.conn.prepareStatement(stmt);
			
			pStmt.setLong(1, contaPoupanca.getNumero());
			pStmt.setTimestamp(2, new java.sql.Timestamp(contaPoupanca.getAbertura().getTime()));
			pStmt.setTimestamp(3, new java.sql.Timestamp(contaPoupanca.getFechamento().getTime()));
			pStmt.setByte(4, contaPoupanca.getSituacao());
			pStmt.setInt(5, contaPoupanca.getSenha());
			pStmt.setDouble(6, contaPoupanca.getSaldo());
			pStmt.setTimestamp(7, new java.sql.Timestamp(contaPoupanca.getAniversario().getTime()));
						
			resultado = pStmt.executeUpdate();
		} 
		catch (Exception e) {
			System.out.println("Erro ao tentar atualizar os dados da conta poupança! " + e.getMessage());
		}
		
		return resultado;
	}
	
	public int removerContaPoupanca(long numero)
	{
		int resultado = 0;
		
		try {
			String stmt = "delete from contaspoupancas where numero = ?";
			
			PreparedStatement pStmt = super.conn.prepareStatement(stmt);
			
			pStmt.setLong(1, numero);
			
			resultado = pStmt.executeUpdate();
		}
		catch (Exception e) {
			System.out.println("Erro ao tentar remover os dados da conta poupança! " + e.getMessage());
		}
		
		return resultado;
	}
}
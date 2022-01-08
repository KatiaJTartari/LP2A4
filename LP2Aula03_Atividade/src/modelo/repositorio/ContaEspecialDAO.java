package modelo.repositorio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.ContaEspecial;

public class ContaEspecialDAO extends ContaComumDAO {
	public void  criarContaEspecial(ContaEspecial contaEspecial) throws SQLException{
		try
		{
			String stmt = "insert into contasespeciais " + "(numero, limite) " + "values (?, ?)";

			PreparedStatement pStmt = super.conn.prepareStatement(stmt);
			
			pStmt.setLong(1, contaEspecial.getNumero());
			pStmt.setDouble(2, contaEspecial.getLimite());
		}catch (Exception e) {
			System.out.println("Erro ao tentar inserir uma nova conta especial! " + e.getMessage());
		}
}
	
	public ArrayList<ContaEspecial> recuperarContasEspeciais() {
		ArrayList<ContaEspecial> resultado = null;

		try {
			String stmt = "select numero, abertura, fechamento, situacao, senha, saldo "
					+ "limite" + "from contasespeciais order by abertura asc";

			PreparedStatement pStmt = super.conn.prepareStatement(stmt);
			ResultSet rs = pStmt.executeQuery();
			resultado = new ArrayList<ContaEspecial>();

			while (rs.next()) {
				ContaEspecial ce = new ContaEspecial(rs.getLong("numero"), rs.getDate("abertura"));
				ce.setFechamento(rs.getDate("fechamento"));
				ce.setSituacao(rs.getByte("situacao"));
				ce.setSenha(rs.getInt("senha"));
				ce.setSaldo(rs.getDouble("saldo"));
				ce.setLimite(rs.getDouble("limite"));
				resultado.add(ce);
			}
		} catch (Exception e) {
			System.out.println("Erro ao tentar recuperar as contas especiais cadastradas! " + e.getMessage());
			resultado = null;
		}

		return resultado;
	}
	
	public ContaEspecial obterContaEspecialPorNumeroConta(long numero)
	{
		ContaEspecial resultado = null;
		
		try
		{
			String stmtSql = "select numeroce, aberturace, fechamentoce, " +
				"situacaoce, senhace, saldoce, limitece from contasespeciais where numeroce = ?";
			
			PreparedStatement pStmt = conn.prepareStatement(stmtSql);
			
			pStmt.setLong(1, numero);
			
			ResultSet rs = pStmt.executeQuery();
			
			if(rs.next())
			{
				resultado = new ContaEspecial(rs.getLong("numero"), rs.getDate("abertura"));
				
				if(rs.getDate("fechamentoconta") != null)
				{
					resultado.setFechamento(rs.getDate("fechamento"));
				}
				resultado.setSituacao(rs.getByte("situacao"));
				resultado.setSenha(rs.getInt("senha"));
				resultado.setSaldo(rs.getDouble("saldo"));
				resultado.setLimite(rs.getDouble("limite"));
			}
		}
		catch (Exception e)
		{
			System.out.println("Erro ao tentar recuperar a conta especial por número! " + numero + e.getMessage());
			resultado = null;
		}
		
		return resultado;
	}
	
	public int editarContaEspecial(ContaEspecial contaEspecial)
	{
		int resultado = 0;
		
		try {
			String stmt = "update contasespeciais " +
		                  "set numero = ?,  abertura = ?, fechamento = ?, situacao = ?, senha = ?, saldo = ? " +
					      "limite = ? where numero = ?";
			
			PreparedStatement pStmt = super.conn.prepareStatement(stmt);
			
			pStmt.setLong(1, contaEspecial.getNumero());
			pStmt.setTimestamp(2, new java.sql.Timestamp(contaEspecial.getAbertura().getTime()));
			pStmt.setTimestamp(3, new java.sql.Timestamp(contaEspecial.getFechamento().getTime()));
			pStmt.setByte(4, contaEspecial.getSituacao());
			pStmt.setInt(5, contaEspecial.getSenha());
			pStmt.setDouble(6, contaEspecial.getSaldo());
			pStmt.setDouble(7, contaEspecial.getLimite());
			
			resultado = pStmt.executeUpdate();
		} 
		catch (Exception e) {
			System.out.println("Erro ao tentar atualizar os dados da conta especial! " + e.getMessage());
		}
		
		return resultado;
	}
	
	public int removerContaEspecial(long numero)
	{
		int resultado = 0;
		
		try {
			String stmt = "delete from contasespeciais where numero = ?";
			
			PreparedStatement pStmt = super.conn.prepareStatement(stmt);
			
			pStmt.setLong(1, numero);
			
			resultado = pStmt.executeUpdate();
		}
		catch (Exception e) {
			System.out.println("Erro ao tentar remover os dados da conta especial! " + e.getMessage());
		}
		
		return resultado;
	}
}

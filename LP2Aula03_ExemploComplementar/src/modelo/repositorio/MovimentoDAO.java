package modelo.repositorio;

import java.sql.*;
import java.util.*;

import modelo.*;

public class MovimentoDAO extends FabricaConexao
{
	public int criarMovimento(Movimento movimento)
	{
		int id = 0;
		
		try
		{
			String stmtSql = "insert into movimentos (tipo, datahora, valor, numerocontacomum) " +
				"values (?, ?, ?, ?) returning id";
			
			PreparedStatement pStmt = conexao.prepareStatement(stmtSql);
			pStmt.setInt(1, movimento.getTipoMovimento());
			pStmt.setObject(2, movimento.getDataHoraMovimento());
			pStmt.setDouble(3, movimento.getValorMovimento());
			pStmt.setLong(4, movimento.getContaMovimento().getNumeroConta());
			
			ResultSet rs = pStmt.executeQuery();
			
			if(rs.next())
			{
				id = rs.getInt("id");
			}
		}
		catch (Exception e)
		{
			System.out.println("Erro ao tentar cadastrar o movimento! " +
					e.getMessage());
		}
		
		return id;
	}
	
	
	public ArrayList<Movimento> obterMovimentosPorNumeroConta(long numeroConta)
	{
		ArrayList<Movimento> resultado = null;
		
		try
		{
			String stmtSql = "select id, tipo, datahora, valor, numerocontacomum " +
				"from movimentos where numerocontacomum = ?";
			
			PreparedStatement pStmt = conexao.prepareStatement(stmtSql);
			
			pStmt.setLong(1, numeroConta);
			
			ResultSet rs = pStmt.executeQuery();
			
			resultado = new ArrayList<Movimento>();		
			
			while(rs.next())
			{
				Movimento m = new Movimento();
				
				m.setIdMovimento(rs.getInt("id"));
				m.setTipoMovimento(rs.getInt("tipo"));
				m.setDataHoraMovimento(rs.getTimestamp("datahora").toLocalDateTime());
				m.setValorMovimento(rs.getDouble("valor"));
				
				ContaComum cc = new ContaComum();
				cc.setNumeroConta(rs.getLong("numerocontacomum"));
				
				m.setContaMovimento(cc);
				
				resultado.add(m);
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
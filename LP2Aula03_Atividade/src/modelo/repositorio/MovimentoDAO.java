package modelo.repositorio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import modelo.ContaComum;
import modelo.Movimento;

public class MovimentoDAO extends FabricaConexao{
	
	public int criarMovimento(Movimento movimento)
	{
		int id = 0;
		
		try
		{
			String stmt = "insert into movimentos (tipoMov, datahora, valor, numerocontacomum) " +
				"values (?, ?, ?, ?) returning id";
			
			PreparedStatement pStmt = conn.prepareStatement(stmt);
			pStmt.setInt(1, movimento.getTipoMov());
			pStmt.setObject(2, movimento.getDataHora());
			pStmt.setDouble(3, movimento.getValor());
			pStmt.setLong(4, movimento.getContaMov().getNumero());
			
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
		
	public ArrayList<Movimento> obterMovimentosPorNumeroConta(long numero)
	{
		ArrayList<Movimento> resultado = null;
		
		try
		{
			String stmt = "select id, tipo, datahora, valor, numerocontacomum " +
				"from movimentos where numerocontacomum = ?";
			
			PreparedStatement pStmt = conn.prepareStatement(stmt);
			
			pStmt.setLong(1, numero);
			
			ResultSet rs = pStmt.executeQuery();
			
			resultado = new ArrayList<Movimento>();		
			
			while(rs.next())
			{
				Movimento m = new Movimento();
				
				m.setIdMov(rs.getInt("id"));
				m.setTipoMov(rs.getInt("tipo"));
				m.setDataHora(rs.getTimestamp("datahora").toLocalDateTime());
				m.setValor(rs.getDouble("valor"));
				
				ContaComum cc = new ContaComum(rs.getLong("numero"), rs.getDate("abertura"));
								
				m.setContaMov(cc);
				
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

package modelo.repositorio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;

import modelo.ContaComum;
import modelo.Pessoa;

public class ContaComumDAO extends FabricaConexao {

	public void criarContaComum(ContaComum contaComum) {
		try {
			String stmt = "insert into contascomuns " + "(numero, abertura, fechamento, situacao, senha, saldo) "
					+ "values (?, ?, ?, ?, ?, ?)";

			PreparedStatement pStmt = super.conn.prepareStatement(stmt);

			pStmt.setLong(1, contaComum.getNumero());
			pStmt.setTimestamp(2, new java.sql.Timestamp(contaComum.getAbertura().getTime()));

			if (contaComum.getFechamento() != null) {
				pStmt.setTimestamp(3, new java.sql.Timestamp(contaComum.getFechamento().getTime()));
			} else {
				pStmt.setNull(3, Types.TIMESTAMP);
			}

			pStmt.setByte(4, contaComum.getSituacao());
			pStmt.setInt(5, contaComum.getSenha());
			pStmt.setDouble(6, contaComum.getSaldo());

			pStmt.execute();
		} catch (Exception e) {
			System.out.println("Erro ao tentar inserir uma nova conta comum! " + e.getMessage());
		}
	}

	// recuperarContasComuns
	public ArrayList<ContaComum> recuperarContasComuns() {
		ArrayList<ContaComum> resultado = null;

		try {
			String stmt = "select numero, abertura, fechamento, situacao, senha, saldo "
					+ "from contascomuns order by abertura asc";

			PreparedStatement pStmt = super.conn.prepareStatement(stmt);
			ResultSet rs = pStmt.executeQuery();
			resultado = new ArrayList<ContaComum>();

			while (rs.next()) {
				ContaComum cc = new ContaComum(rs.getLong("numero"), rs.getDate("abertura"));
				cc.setFechamento(rs.getDate("fechamento"));
				cc.setSituacao(rs.getByte("situacao"));
				cc.setSenha(rs.getInt("senha"));
				cc.setSaldo(rs.getDouble("saldo"));
				resultado.add(cc);
			}
		} catch (Exception e) {
			System.out.println("Erro ao tentar recuperar as contas comuns cadastradas! " + e.getMessage());
			resultado = null;
		}

		return resultado;
	}

	// recuperarContaComumPorNumero
	public void recuperarContaComumPorNumero(long numero, ContaComum resultado) {
		try {
			String stmt = "select numero, abertura, fechamento, situacao, senha, saldo "
					+ "from contascomuns where numero = ?";

			PreparedStatement pStmt = super.conn.prepareStatement(stmt);

			pStmt.setLong(1, numero);

			ResultSet rs = pStmt.executeQuery();

			if (rs.next()) {
				resultado = new ContaComum(rs.getLong("numero"), rs.getDate("abertura"));
				resultado.setFechamento(rs.getDate("fechamento"));
				resultado.setSituacao(rs.getByte("situacao"));
				resultado.setSenha(rs.getInt("senha"));
				resultado.setSaldo(rs.getDouble("saldo"));
			}
		} catch (Exception e) {
			System.out.println("Erro ao tentar recuperar a conta comum por número " + numero + ". " + e.getMessage());
		}
	}
	// ou	
	// recuperarContaComumPorNumero
/*	public ContaComum recuperarContaComumPorNumero(long numero) {
		ContaComum resultado = null;

		try {
			if (resultado.getNumero() > 0) {
				String stmt = "select numero, abertura, fechamento, situacao, senha, saldo "
						+ "from contascomuns where numero = ?";

				PreparedStatement pStmt = super.conn.prepareStatement(stmt);

				pStmt.setLong(1, numero);

				ResultSet rs = pStmt.executeQuery();

				if (rs.next()) {
					resultado = new ContaComum(rs.getLong("numero"), rs.getDate("abertura"));
					resultado.setFechamento(rs.getDate("fechamento"));
					resultado.setSituacao(rs.getByte("situacao"));
					resultado.setSenha(rs.getInt("senha"));
					resultado.setSaldo(rs.getDouble("saldo"));
				}
			}
		} catch (Exception e) {
			System.out.println("Erro ao tentar recuperar a pessoa física por número " + numero + ". " + e.getMessage());

			resultado = null;
		}

		return resultado;
	}    */

	// editarContaComum
	public int editarContaComum(ContaComum contaComum)
	{
		int resultado = 0;
		
		// implementar
		try {
			String stmt = "update contascomuns " +
		                  "set numero = ?,  abertura = ?, fechamento = ?, situacao = ?, senha = ?, saldo = ? " +
					      "where numero = ?";
			
			PreparedStatement pStmt = super.conn.prepareStatement(stmt);
			
			pStmt.setLong(1, contaComum.getNumero());
			pStmt.setTimestamp(2, new java.sql.Timestamp(contaComum.getAbertura().getTime()));
			pStmt.setTimestamp(3, new java.sql.Timestamp(contaComum.getFechamento().getTime()));
			pStmt.setByte(4, contaComum.getSituacao());
			pStmt.setInt(5, contaComum.getSenha());
			pStmt.setDouble(6, contaComum.getSaldo());
						
			resultado = pStmt.executeUpdate();
		} 
		catch (Exception e) {
			System.out.println("Erro ao tentar atualizar os dados da conta comum! " + e.getMessage());
		}
		
		return resultado;
	}
	
	// removerContaComum
	public int removerContaComum(long numero)
	{
		int resultado = 0;
		
		// implementar
		try {
			String stmt = "delete from contascomuns where numero = ?";
			
			PreparedStatement pStmt = super.conn.prepareStatement(stmt);
			
			pStmt.setLong(1, numero);
			
			resultado = pStmt.executeUpdate();
		}
		catch (Exception e) {
			System.out.println("Erro ao tentar atualizar os dados da conta comum! " + e.getMessage());
		}
		
		return resultado;
	}
	
	public void incluirTitulares(ContaComum contaComum) {
		try {
			if (contaComum.getTitulares() != null) {
				for (Pessoa p : contaComum.getTitulares()) {
					incluirTitular(contaComum.getNumero(), p.getId());
				}
			}
		} catch (Exception e) {
			System.out.println("Erro ao tentar associar as pessoas à conta comum! " + e.getMessage());
		}
	}

	protected void incluirTitular(long numeroContaComum, int idPessoa) {
		try {
			String stmt = "insert into pessoas_contascomuns " + "(idpessoa, idcontacomum) " + "values (?, ?)";

			PreparedStatement pStmt = super.conn.prepareStatement(stmt);

			pStmt.setInt(1, idPessoa);
			pStmt.setLong(2, numeroContaComum);

			pStmt.execute();
		} catch (Exception e) {
			System.out.println("Erro ao tentar associar pessoa à conta comum! " + e.getMessage());
		}
	}
	
	// removerTitular
	public int removerTitular(long numero, int id)
	{
		int resultado = 0;
		
		// implementar
		try {
			String stmt = "delete from pessoas where id = ? and numero = ?";
			
			PreparedStatement pStmt = super.conn.prepareStatement(stmt);
			
			pStmt.setInt(1, id);
			pStmt.setLong(2, numero);
			
			resultado = pStmt.executeUpdate();
		}
		catch (Exception e) {
			System.out.println("Erro ao tentar remover titular! " + e.getMessage());
		}
		
		return resultado;
	}
	
}

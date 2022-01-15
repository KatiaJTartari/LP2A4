package modelo.repositorio;

import java.sql.PreparedStatement;
import java.sql.Types;

import modelo.ContaComum;
import modelo.Pessoa;

public class ContaComumDAO extends FabricaConexao {

	public void criarContaComum(ContaComum contaComum)
	{		
		try
		{
			String stmt = "insert into contascomuns " +
					"(numero, abertura, fechamento, situacao, senha, saldo) " +
					"values (?, ?, ?, ?, ?, ?)";
			
			PreparedStatement pStmt = super.conn.prepareStatement(stmt);
			
			pStmt.setLong(1, contaComum.getNumero());
			pStmt.setTimestamp(2,
					new java.sql.Timestamp(contaComum.getAbertura().getTime()));
			
			if(contaComum.getFechamento() != null)
			{
				pStmt.setTimestamp(3,
					new java.sql.Timestamp(contaComum.getFechamento().getTime()));
			}
			else
			{
				pStmt.setNull(3, Types.TIMESTAMP);
			}
			
			pStmt.setByte(4, contaComum.getSituacao());
			pStmt.setInt(5, contaComum.getSenha());
			pStmt.setDouble(6, contaComum.getSaldo());
			
			pStmt.execute();
		}
		catch (Exception e)
		{
			System.out.println("Erro ao tentar inserir uma nova conta comum! " +
					e.getMessage());
		}
	}
	
	// recuperarContasComuns
	
	// recuperarContaComumPorNumero
	
	// editarContaComum
	
	// removerContaComum
	
	public void incluirTitulares(ContaComum contaComum)
	{
		try
		{
			if(contaComum.getTitulares() != null)
			{
				for (Pessoa p : contaComum.getTitulares()) {
					incluirTitular(contaComum.getNumero(), p.getId());
				}
			}
		}
		catch (Exception e)
		{
			System.out.println("Erro ao tentar associar as pessoas à conta comum! " +
					e.getMessage());
		}
	}
	
	protected void incluirTitular(long numeroContaComum, int idPessoa)
	{
		try
		{
			String stmt = "insert into pessoas_contascomuns " +
					"(idpessoa, idcontacomum) " +
					"values (?, ?)";
			
			PreparedStatement pStmt = super.conn.prepareStatement(stmt);
			
			pStmt.setInt(1, idPessoa);
			pStmt.setLong(2, numeroContaComum);
			
			pStmt.execute();
		}
		catch (Exception e)
		{
			System.out.println("Erro ao tentar associar pessoa e conta comum! " +
					e.getMessage());
		}
	}
	
	// removerTitular
}


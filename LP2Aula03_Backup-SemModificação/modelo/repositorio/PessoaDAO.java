package modelo.repositorio;

import java.sql.*;
import modelo.Pessoa;

public abstract class PessoaDAO extends FabricaConexao
{
	protected int criarPessoa(Pessoa pessoa)
	{		
		int id = 0;
		
		try
		{
			String stmt = "insert into pessoas (nome, endereco, cep, telefone, " +
					"renda, situacao) values (?, ?, ?, ?, ?, ?) returning id";
			
			PreparedStatement pStmt = super.conn.prepareStatement(stmt);
			
			pStmt.setString(1, pessoa.getNome());
			pStmt.setString(2, pessoa.getEndereco());
			pStmt.setLong(3, pessoa.getCep());
			pStmt.setString(4, pessoa.getTelefone());
			pStmt.setFloat(5, pessoa.getRenda());
			pStmt.setShort(6, pessoa.getSituacao());
			
			ResultSet rs = pStmt.executeQuery();
			
			if(rs.next())
			{
				id = rs.getInt(1);
			}
			
		}
		catch (Exception e)
		{
			System.out.println("Erro ao tentar inserir uma nova pessoa! " +
					e.getMessage());
		}
		
		return id;
	}
	
	public void recuperarPessoaPorId(int id, Pessoa resultado)
	{
		try
		{
			String stmt = "select id, nome, endereco, cep, telefone, renda, " +
					 "situacao from pessoas where id = ?";
			
			PreparedStatement pStmt = super.conn.prepareStatement(stmt);
			
			pStmt.setInt(1, id);
			
			ResultSet rs = pStmt.executeQuery();
			
			if(rs.next())
			{
				resultado.setId(rs.getInt("id"));
				resultado.setNome(rs.getString("nome"));
				resultado.setEndereco(rs.getString("endereco"));
				resultado.setCep(rs.getLong("cep"));
				resultado.setTelefone(rs.getString("telefone"));
				resultado.setRenda(rs.getFloat("renda"));
				resultado.setSituacao(rs.getByte("situacao"));
			}
		}
		catch (Exception e)
		{
			System.out.println("Erro ao tentar recuperar a pessoa com ID " +
					id + ". " + e.getMessage());
		}
	}
	
	public int editarPessoa(Pessoa pessoa)
	{
		int resultado = 0;
		
		// implementar
		
		return resultado;
	}
	
	public int removerPessoa(int id)
	{
		int resultado = 0;
		
		// implementar
		
		return resultado;
	}
	
	// incluirConta
	
	// removerConta
}
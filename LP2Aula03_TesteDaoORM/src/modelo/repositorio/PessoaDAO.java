package modelo.repositorio;

import java.sql.*;
import java.util.ArrayList;

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
	
	// Incluí o método recuperarPessoas do slide 55
	public ArrayList<Pessoa> recuperarPessoas() {
		ArrayList<Pessoa> resultado = null;
		
		try
		{
			String stmt = "select id, nome, endereco, cep, telefone, renda, " +
					      "situacao from pessoas order by nome asc";
			
			PreparedStatement pStmt = super.conn.prepareStatement(stmt);
			ResultSet rs = pStmt.executeQuery();
			resultado = new ArrayList<Pessoa>();
			
			while(rs.next()) {
				Pessoa p = new Pessoa();
				p.setId(rs.getInt("id"));
				p.setNome(rs.getString("nome"));
				p.setEndereco(rs.getString("endereco"));
				p.setCep(rs.getLong("cep"));
				p.setTelefone(rs.getString("telefone"));
				p.setRenda(rs.getFloat("renda"));
				p.setSituacao(rs.getByte("situacao"));
				resultado.add(p);
			}
		}
		catch (Exception e)
		{
			System.out.println("Erro ao tentar recuperar as pessoas cadastradas! " + e.getMessage());
			resultado = null;
		}
		
		return resultado;			
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
		try {
			String stmt = "update pessoas " +
		                  "set nome = ?,  endereco = ?, cep = ?, telefone = ?, renda = ?, situacao = ? " +
					      "where id = ?";
			
			PreparedStatement pStmt = super.conn.prepareStatement(stmt);
			
			pStmt.setString(1, pessoa.getNome());
			pStmt.setString(2, pessoa.getEndereco());
			pStmt.setLong(3, pessoa.getCep());
			pStmt.setString(4, pessoa.getTelefone());
			pStmt.setFloat(5, pessoa.getRenda());
			pStmt.setShort(6, pessoa.getSituacao());
			pStmt.setInt(7, pessoa.getId());
			
			resultado = pStmt.executeUpdate();
		} 
		catch (Exception e) {
			System.out.println("Erro ao tentar atualizar os dados da pessoa! " + e.getMessage());
		}
		
		return resultado;
	}
	
	public int removerPessoa(int id)
	{
		int resultado = 0;
		
		// implementar
		try {
			String stmt = "delete from pessoas where id = ?";
			
			PreparedStatement pStmt = super.conn.prepareStatement(stmt);
			
			pStmt.setInt(1, id);
			
			resultado = pStmt.executeUpdate();
		}
		catch (Exception e) {
			System.out.println("Erro ao tentar remover a pessoa! " + e.getMessage());
		}
		
		return resultado;
	}
		
	// incluirConta
	protected void incluirConta(long numeroContaComum, int idPessoa) {
		try {
			String stmt = "insert into contas " + "(idpessoa, idcontacomum) " + "values (?, ?)";

			PreparedStatement pStmt = super.conn.prepareStatement(stmt);

			pStmt.setInt(1, idPessoa);
			pStmt.setLong(2, numeroContaComum);

			pStmt.execute();
		} catch (Exception e) {
			System.out.println("Erro ao tentar incluir conta à pessoa! " + e.getMessage());
		}
	}
	
	// removerConta
	public int removerConta(long numero)
	{
		int resultado = 0;
		
		// implementar
		try {
			String stmt = "delete from contas where numero = ?";
			
			PreparedStatement pStmt = super.conn.prepareStatement(stmt);
						
			pStmt.setLong(1, numero);
			
			resultado = pStmt.executeUpdate();
		}
		catch (Exception e) {
			System.out.println("Erro ao tentar remover a conta! " + e.getMessage());
		}
		
		return resultado;
	}
	
}
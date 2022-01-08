package modelo.repositorio;

import java.sql.*;
import java.util.*;
import modelo.Pessoa;

public class PessoaDAO extends FabricaConexao
{
	public int criarPessoa(Pessoa pessoa)
	{
		int id = 0;
		
		try
		{
			String stmt = "insert into pessoas (nome, endereco, cep, telefone, renda, situacao) "
					+ "values (?, ?, ?, ?, ?, ?) returning id";
			
			PreparedStatement pStmt = conexao.prepareStatement(stmt);
			
			pStmt.setString(1, pessoa.getNomePessoa());
			pStmt.setString(2, pessoa.getEnderecoPessoa());
			pStmt.setLong(3, pessoa.getCepPessoa());
			pStmt.setString(4, pessoa.getTelefonePessoa());
			pStmt.setDouble(5, pessoa.getRendaPessoa());
			pStmt.setInt(6, pessoa.getSituacaoPessoa());
			
			ResultSet rs = pStmt.executeQuery();
			
			if (rs.next())
			{
				id = rs.getInt(1);
			}
		} catch (Exception e)
		{
			System.out.println("Erro ao tentar inserir uma nova pessoa! " + e.getMessage());
		}
		
		return id;
	}
	
	public ArrayList<Pessoa> recuperarPessoas()
	{
		ArrayList<Pessoa> resultado = null;
		
		try
		{
			String stmt = "select id, nome, endereco, cep, telefone, renda, situacao "
					+ "from pessoas order by nome asc";
			
			PreparedStatement pStmt = conexao.prepareStatement(stmt);
			ResultSet rs = pStmt.executeQuery();
			resultado = new ArrayList<Pessoa>();
			
			while (rs.next())
			{
				Pessoa p = new Pessoa();
				p.setIdPessoa(rs.getInt("id"));
				p.setNomePessoa(rs.getString("nome"));
				p.setEnderecoPessoa(rs.getString("endereco"));
				p.setCepPessoa(rs.getLong("cep"));
				p.setTelefonePessoa(rs.getString("telefone"));
				p.setRendaPessoa(rs.getDouble("renda"));
				p.setSituacaoPessoa(rs.getInt("situacao"));
				resultado.add(p);
			}
		} catch (Exception e)
		{
			System.out.println("Erro ao tentar recuperar as pessoas cadastradas! " + e.getMessage());
			resultado = null;
		}
		
		return resultado;
	}
	
	public Pessoa recuperarPessoaPorId(int id)
	{
		Pessoa resultado = null;
		
		try
		{
			String stmt = "select id, nome, endereco, cep, telefone, renda, situacao " + "from pessoas where id = ?";
			
			PreparedStatement pStmt = conexao.prepareStatement(stmt);
			pStmt.setInt(1, id);
			ResultSet rs = pStmt.executeQuery();
			
			if (rs.next())
			{
				resultado = new Pessoa();
				resultado.setIdPessoa(rs.getInt("id"));
				resultado.setNomePessoa(rs.getString("nome"));
				resultado.setEnderecoPessoa(rs.getString("endereco"));
				resultado.setCepPessoa(rs.getLong("cep"));
				resultado.setTelefonePessoa(rs.getString("telefone"));
				resultado.setRendaPessoa(rs.getDouble("renda"));
				resultado.setSituacaoPessoa(rs.getInt("situacao"));
			}
		} catch (Exception e)
		{
			System.out.println("Erro ao tentar recuperar a pessoa! " + e.getMessage());
			resultado = null;
		}
		
		return resultado;
	}
	
	public int editarPessoa(Pessoa pessoa)
	{
		int resultado = 0;
		
		try
		{
			String stmt = "update pessoas "
					+ "set nome = ?, endereco = ?, cep = ?, telefone = ?, renda = ?, situacao = ? " + "where id = ?";
			
			PreparedStatement pStmt = conexao.prepareStatement(stmt);
			
			pStmt.setString(1, pessoa.getNomePessoa());
			pStmt.setString(2, pessoa.getEnderecoPessoa());
			pStmt.setLong(3, pessoa.getCepPessoa());
			pStmt.setString(4, pessoa.getTelefonePessoa());
			pStmt.setDouble(5, pessoa.getRendaPessoa());
			pStmt.setInt(6, pessoa.getSituacaoPessoa());
			pStmt.setInt(7, pessoa.getIdPessoa());
			
			resultado = pStmt.executeUpdate();
		} catch (Exception e)
		{
			System.out.println("Erro ao tentar atualizar os dados da pessoa! " + e.getMessage());
		}
		
		return resultado;
	}
	
	public int removerPessoa(int id)
	{
		int resultado = 0;
		
		try
		{
			String stmt = "delete from pessoas where id = ?";
			
			PreparedStatement pStmt = conexao.prepareStatement(stmt);
			
			pStmt.setInt(1, id);
			
			resultado = pStmt.executeUpdate();
		} catch (Exception e)
		{
			System.out.println("Erro ao tentar atualizar os dados da pessoa! " + e.getMessage());
		}
		
		return resultado;
	}
}

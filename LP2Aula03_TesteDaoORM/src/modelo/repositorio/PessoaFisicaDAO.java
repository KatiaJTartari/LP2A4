package modelo.repositorio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.PessoaFisica;

public class PessoaFisicaDAO extends PessoaDAO {

	public int criarPessoaFisica(PessoaFisica pessoaFisica) throws SQLException
	{
		int id = 0;
		
		try
		{
			// trabalhando com transa??o
			// https://docs.oracle.com/javase/tutorial/jdbc/basics/transactions.html
			super.conn.setAutoCommit(false);
			
			id = super.criarPessoa(pessoaFisica);
			
			if(id > 0)
			{
				pessoaFisica.setId(id);
				
				String stmt = "insert into pessoasfisicas (id, cpf, nascto) " +
						"values (?, ?, ?)";
				
				PreparedStatement pStmt = super.conn.prepareStatement(stmt);
				
				pStmt.setInt(1, pessoaFisica.getId());
				pStmt.setLong(2, pessoaFisica.getCpf());
				pStmt.setDate(3, new java.sql.Date(pessoaFisica.getNascto().getTime()));
				
				pStmt.execute();
				
				super.conn.commit();
			}
		}
		catch (Exception e)
		{
			System.out.println("Erro ao tentar inserir uma nova pessoa f?sica! " +
					e.getMessage());
			
			super.conn.rollback();
			id = 0;
		}
		
		return id;
	}
	
	public ArrayList<PessoaFisica> recuperarPessoasFisicas()
	{
		ArrayList<PessoaFisica> resultado = null;
		
		try
		{
			String stmt = "select p.id, p.nome, p.endereco, p.cep, p.telefone, " +
				"p.renda, p.situacao, pf.cpf, pf.nascto from pessoas p, " +
				"pessoasfisicas pf where p.id = pf.id";
			
			PreparedStatement pStmt = super.conn.prepareStatement(stmt);
			
			ResultSet rs = pStmt.executeQuery();
			
			resultado = new ArrayList<PessoaFisica>();
			
			while(rs.next())
			{	
				PessoaFisica p = new PessoaFisica();
				p.setId(rs.getInt("id"));
				p.setNome(rs.getString("nome"));
				p.setEndereco(rs.getString("endereco"));
				p.setCep(rs.getLong("cep"));
				p.setTelefone(rs.getString("telefone"));
				p.setRenda(rs.getFloat("renda"));
				p.setSituacao(rs.getByte("situacao"));
				p.setCpf(rs.getLong("cpf"));
				p.setNascto(rs.getDate("nascto"));
				
				resultado.add(p);
			}
		}
		catch (Exception e)
		{
			System.out.println("Erro ao tentar recuperar as pessoas f?sicas cadastradas! " +
					e.getMessage());
			
			resultado = null;
		}
		
		return resultado;
	}
	
	public PessoaFisica recuperarPessoaFisicaPorId(int id)
	{
		PessoaFisica resultado = null;
		
		try
		{
			resultado = new PessoaFisica();
			
			super.recuperarPessoaPorId(id, resultado);
			
			if(resultado.getId() > 0)
			{
				String stmt = "select cpf, nascto " +
						 "from pessoasfisicas where id = ?";
				
				PreparedStatement pStmt = super.conn.prepareStatement(stmt);
				
				pStmt.setInt(1, id);
				
				ResultSet rs = pStmt.executeQuery();
				
				if(rs.next())
				{				
					resultado.setCpf(rs.getLong("cpf"));
					resultado.setNascto(rs.getDate("nascto"));
				}
			}
		}
		catch (Exception e)
		{
			System.out.println("Erro ao tentar recuperar a pessoa f?sica com ID " +
					id + ". " + e.getMessage());
					
			resultado = null;
		}
		
		return resultado;
	}
	
	public int editarPessoaFisica(PessoaFisica pessoaFisica)
	{
		int resultado = 0;
		
		// implementar
		try {
			String stmt = "update pessoasfisicas " +
		                  "set nome = ?,  endereco = ?, cep = ?, telefone = ?, renda = ?, situacao = ? " +
					      "cpf = ?, nascto = ? where id = ?";
			
			PreparedStatement pStmt = super.conn.prepareStatement(stmt);
			
			pStmt.setString(1, pessoaFisica.getNome());
			pStmt.setString(2, pessoaFisica.getEndereco());
			pStmt.setLong(3, pessoaFisica.getCep());
			pStmt.setString(4, pessoaFisica.getTelefone());
			pStmt.setFloat(5, pessoaFisica.getRenda());
			pStmt.setShort(6, pessoaFisica.getSituacao());
			pStmt.setLong(7, pessoaFisica.getCpf());
			pStmt.setDate(8, new java.sql.Date(pessoaFisica.getNascto().getTime()));
			pStmt.setInt(9, pessoaFisica.getId());
			
			resultado = pStmt.executeUpdate();
		} 
		catch (Exception e) {
			System.out.println("Erro ao tentar atualizar os dados da pessoa f?sica! " + e.getMessage());
		}
		
		return resultado;
	}
	
	public int removerPessoaFisica(int id)
	{
		int resultado = 0;
		
		// implementar
		try {
			String stmt = "delete from pessoasfisicas where id = ?";
			
			PreparedStatement pStmt = super.conn.prepareStatement(stmt);
			
			pStmt.setInt(1, id);
			
			resultado = pStmt.executeUpdate();
		}
		catch (Exception e) {
			System.out.println("Erro ao tentar remover pessoa f?sica! " + e.getMessage());
		}
		
		return resultado;
	}
	
}


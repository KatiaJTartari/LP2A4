package modelo.repositorio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.PessoaJuridica;

public class PessoaJuridicaDAO extends PessoaDAO{
	
	public int criarPessoaJuridica(PessoaJuridica pessoaJuridica) throws SQLException
	{
		int id = 0;
		
		// implementar
		try
		{
			// trabalhando com transação
			// https://docs.oracle.com/javase/tutorial/jdbc/basics/transactions.html
			super.conn.setAutoCommit(false);
			
			id = super.criarPessoa(pessoaJuridica);
			
			if(id > 0)
			{
				pessoaJuridica.setId(id);
				
				String stmt = "insert into pessoasjuridicas (id, cnpj, nomeFantasia) " +
						"values (?, ?, ?)";
				
				PreparedStatement pStmt = super.conn.prepareStatement(stmt);
				
				pStmt.setInt(1, pessoaJuridica.getId());
				pStmt.setLong(2, pessoaJuridica.getCnpj());
				pStmt.setString(3, pessoaJuridica.getNomeFantasia());
				
				pStmt.execute();
				
				super.conn.commit();
			}
		}
		catch (Exception e)
		{
			System.out.println("Erro ao tentar inserir uma nova pessoa jurídica! " +
					e.getMessage());
			
			super.conn.rollback();
			id = 0;
		}
		
		return id;
	}
		
	public ArrayList<PessoaJuridica> recuperarPessoasJuridicas()
	{
		ArrayList<PessoaJuridica> resultado = null;
		
		// implementar
		try
		{
			String stmt = "select p.id, p.nome, p.endereco, p.cep, p.telefone, " +
				"p.renda, p.situacao, pj.cnpj, pj.nomeFantasia from pessoas p, " +
				"pessoasjuridicas pj where p.id = pj.id";
			
			PreparedStatement pStmt = super.conn.prepareStatement(stmt);
			
			ResultSet rs = pStmt.executeQuery();
			
			resultado = new ArrayList<PessoaJuridica>();
			
			while(rs.next())
			{	
				PessoaJuridica p = new PessoaJuridica();
				p.setId(rs.getInt("id"));
				p.setNome(rs.getString("nome"));
				p.setEndereco(rs.getString("endereco"));
				p.setCep(rs.getLong("cep"));
				p.setTelefone(rs.getString("telefone"));
				p.setRenda(rs.getFloat("renda"));
				p.setSituacao(rs.getByte("situacao"));
				p.setCnpj(rs.getLong("cnpj"));
				p.setNomeFantasia(rs.getString("nomeFantasia"));
				
				resultado.add(p);
			}
		}
		catch (Exception e)
		{
			System.out.println("Erro ao tentar recuperar as pessoas jurídicas cadastradas! " +
					e.getMessage());
			
			resultado = null;
		}		
		
		return resultado;
	}
	
	public PessoaJuridica recuperarPessoaJuridicaPorId(int id)
	{
		PessoaJuridica resultado = null;
		
		// implementar
		try
		{
			resultado = new PessoaJuridica();
			
			super.recuperarPessoaPorId(id, resultado);
			
			if(resultado.getId() > 0)
			{
				String stmt = "select cnpj, nomeFantasia " +
						 "from pessoasjuridicas where id = ?";
				
				PreparedStatement pStmt = super.conn.prepareStatement(stmt);
				
				pStmt.setInt(1, id);
				
				ResultSet rs = pStmt.executeQuery();
				
				if(rs.next())
				{				
					resultado.setCnpj(rs.getLong("cnpj"));
					resultado.setNomeFantasia(rs.getString("nomeFantasia"));
				}
			}
		}
		catch (Exception e)
		{
			System.out.println("Erro ao tentar recuperar a pessoa jurídica com ID " +
					id + ". " + e.getMessage());
					
			resultado = null;
		}
		
		return resultado;
	}
		
	public int editarPessoaJuridica(PessoaJuridica pessoaJuridica)
	{
		int resultado = 0;
		
		// implementar
		try {
			String stmt = "update pessoasjuridicas " +
		                  "set nome = ?,  endereco = ?, cep = ?, telefone = ?, renda = ?, situacao = ? " +
					      "cnpj = ?, nomeFantasia = ? where id = ?";
			
			PreparedStatement pStmt = super.conn.prepareStatement(stmt);
			
			pStmt.setString(1, pessoaJuridica.getNome());
			pStmt.setString(2, pessoaJuridica.getEndereco());
			pStmt.setLong(3, pessoaJuridica.getCep());
			pStmt.setString(4, pessoaJuridica.getTelefone());
			pStmt.setFloat(5, pessoaJuridica.getRenda());
			pStmt.setShort(6, pessoaJuridica.getSituacao());
			pStmt.setLong(7, pessoaJuridica.getCnpj());
			pStmt.setString(8, pessoaJuridica.getNomeFantasia());
			pStmt.setInt(9, pessoaJuridica.getId());
			
			resultado = pStmt.executeUpdate();
		} 
		catch (Exception e) {
			System.out.println("Erro ao tentar atualizar os dados da pessoa jurídica! " + e.getMessage());
		}
		
		return resultado;
	}
		
	public int removerPessoaJuridica(int id)
	{
		int resultado = 0;
		
		// implementar
		try {
			String stmt = "delete from pessoasjuridicas where id = ?";
			
			PreparedStatement pStmt = super.conn.prepareStatement(stmt);
			
			pStmt.setInt(1, id);
			
			resultado = pStmt.executeUpdate();
		}
		catch (Exception e) {
			System.out.println("Erro ao tentar remover pessoa jurídica! " + e.getMessage());
		}
		
		return resultado;
	}

}

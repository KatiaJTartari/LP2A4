package modelo.repositorio;

import java.sql.*;
import java.util.*;

public class FabricaConexao
{
	protected Connection conexao;
	
	public FabricaConexao()
	{
		try
		{
      Class.forName("org.postgresql.Driver");

      // Dados de conexão e credenciais que gerei no elephantsql
      // Já não funcionam mais. Tente criar a sua própria instância nessa Plataforma ;=)
      String url = "jdbc:postgresql://tuffi.db.elephantsql.com:5432/pHpqrafR";
				
      Properties props = new Properties();
      props.setProperty("user", "pHpqrafR");
      props.setProperty("password", "pHpqrafR");
			
			this.conexao = DriverManager.getConnection(url, props);
			
			System.out.println("Conexão criada com sucesso!");
		}
		catch (Exception e)
		{
			System.out.println("Erro ao criar uma conexão! " + e.getMessage());
		}
	}
	
	public void fecharConexao()
	{
		try
		{
			if(this.conexao != null)
			{
				this.conexao.close();
				System.out.println("Conexão fechada com sucesso!");
			}
		}
		catch (Exception e)
		{
			System.out.println("Erro ao fechar a conexão! " + e.getMessage());
		}
	}
}
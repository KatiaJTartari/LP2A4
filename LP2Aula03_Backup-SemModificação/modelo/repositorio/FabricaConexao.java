package modelo.repositorio;

import java.sql.*;
import java.util.*;

public abstract class FabricaConexao
{
	protected Connection conn;
	
	public FabricaConexao()
	{
		try
		{
			// Setar com os dados de conexão do banco a ser utilizado
			String url = "jdbc:postgresql://localhost/testepgdb";
			
			Properties props = new Properties();
			props.setProperty("user", "postgres");
			props.setProperty("password", "alianceGod21");
			props.setProperty("ssl", "false");
			
			this.conn = DriverManager.getConnection(url, props);
			
			System.out.println("Conexão aberta!");
		}
		catch (Exception e)
		{
			System.out.println("Erro ao criar conexão! " +
					e.getMessage());
		}
	}
	
	public void fecharConexao()
	{
		try
		{
			this.conn.close();
			System.out.println("Conexão fechada!");
		}
		catch (Exception e)
		{
			System.out.println("Erro ao fechar conexão! " +
					e.getMessage());
		}
	}
}
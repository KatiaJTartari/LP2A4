package modelo.repositorio;

import java.sql.*;
import java.util.*;

//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.util.Properties;

public class FabricaConexao {
protected Connection conn;
	
	/* Construtor */
	public FabricaConexao()
	{
		try
		{
			// Setar com os dados de conexão do banco a ser utilizado
			String url = "jdbc:postgresql://localhost/sistemabancariodb";
			
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
	
	/* Método para fechar a conexão com o banco de dados */
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

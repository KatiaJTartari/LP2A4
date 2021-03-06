package modelo.repositorio;

import java.sql.*;
import java.util.*;

public abstract class FabricaConexao
{
	protected Connection conn;
	
	/* Construtor */
	public FabricaConexao()
	{
		try
		{
			// Setar com os dados de conex?o do banco a ser utilizado
			String url = "jdbc:postgresql://localhost/testepgdb";
			
			Properties props = new Properties();
			props.setProperty("user", "postgres");
			props.setProperty("password", "alianceGod21");
			props.setProperty("ssl", "false");
			
			this.conn = DriverManager.getConnection(url, props);
			
			System.out.println("Conex?o aberta!");
		}
		catch (Exception e)
		{
			System.out.println("Erro ao criar conex?o! " +
					e.getMessage());
		}
	}
	
	/* M?todo para fechar a conex?o com o banco de dados */
	public void fecharConexao()
	{
		try
		{
			this.conn.close();
			System.out.println("Conex?o fechada!");
		}
		catch (Exception e)
		{
			System.out.println("Erro ao fechar conex?o! " +
					e.getMessage());
		}
	}
}
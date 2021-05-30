/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sasi
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connections 
{
	public static Connection getConnections()
	{
		String url = "jdbc:postgresql://localhost:5432/testdb";
		String username = "postgres";
		String password = "psql";
		Connection conn = null;
		try 
		{
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(url, username, password);
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return conn;
	}
}



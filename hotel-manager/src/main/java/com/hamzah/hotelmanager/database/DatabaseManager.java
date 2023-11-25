package com.hamzah.hotelmanager.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DatabaseManager
{
	private Connection connection;

	public DatabaseManager()
	{
		String url = "jdbc:mysql://localhost:3306/hotel_manager";
		Properties info = new Properties();
		info.put("user", "root");
		info.put("password", "rootPassword");

		try
		{
			connection = DriverManager.getConnection(url, info);

			if (connection != null)
			{
				System.out.println("Connected to the database");
			}
		} catch (SQLException ex)
		{
			System.out.println("An error occurred. Maybe user/password is invalid");
			ex.printStackTrace();
		}
	}

	public boolean executeSql(String sql, Object[] parameters)
	{
		boolean success = false;

		try
		{
			PreparedStatement stmt = connection.prepareStatement(sql);
			for (int i = 0; i < parameters.length; i++)
			{
				stmt.setObject(i + 1, parameters[i]);
			}
			success = stmt.executeUpdate() > 0;
		} catch (SQLException ex)
		{
			System.out.println("An error occurred.");
			ex.printStackTrace();
		}
		return success;
	}

	public ResultSet executeSql(String sql)
	{
		ResultSet rs = null;

		try
		{
			Statement stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException ex)
		{
			System.out.println("An error occurred.");
			ex.printStackTrace();
		}

		return rs;
	}

	public void closeConn()
	{

		if (connection != null)
		{
			try
			{
				connection.close();
			} catch (SQLException ex)
			{
				ex.printStackTrace();
			}
		}
	}
}

package com.hamzah.hotelmanager.application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Application {
	public static void main(String[] args) throws IOException {
		System.out.println("application started");
		
		HotelManager hotelManager = new HotelManager();
		hotelManager.printMenu();
		
		BufferedReader reader = new BufferedReader(
	            new InputStreamReader(System.in));
		String input = "";
		
		while (!input.equals("0"))
		{
			input = reader.readLine();
			
			System.out.println("You inputted: " + input);
			int inputNumber = Integer.parseInt(input);
			System.out.println("Input int : " + inputNumber);
			
			if (inputNumber == 1)
			{
				hotelManager.viewDetails();
			}
			if (inputNumber == 2) 
			{
				hotelManager.viewBookings();
			}
			if (inputNumber == 3)
			{
				hotelManager.getBookingInfo();
			}
			hotelManager.printMenu();
		}
		System.out.println("application finisehd");
	}
}


/*
 * 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
 * 
 * 
 * Connection conn = null;
		try 
		{
			// connect
            String url = "jdbc:mysql://localhost:3306/hotel_manager";
            Properties info = new Properties();
            info.put("user", "root");
            info.put("password", "rootPassword");
 
            conn = DriverManager.getConnection(url, info);
            if (conn != null) 
            {
                System.out.println("Connected to the database");
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT name from hotel where id = 1");
                if (rs.next())
                {
                    hotelName = rs.getString("name");
                }
            }
		}
		catch (SQLException ex) 
		{
            System.out.println("An error occurred. Maybe user/password is invalid");
            ex.printStackTrace();
        }
		finally 
		{
            if (conn != null) 
            {
                try 
                {
                    conn.close();
                } catch (SQLException ex) 
                {
                    ex.printStackTrace();
                }
            }
		}
 */


/*
 * 
		for(int a = 1; a < 6; a++) {
			System.out.println("hello");
		}
		
		int b = 0;
		
		System.out.println(b < 6);
		
		while (b < 6) {
			b++;
			System.out.println("olleh");
		}
		
		//reverse
		String stringToReverse = "hello";
		char[] stringChars = stringToReverse.toCharArray();
		
		for (int pos = stringToReverse.length() - 1; pos >= 0; pos--)
		{
			System.out.print(stringChars[pos]);
		}
 */
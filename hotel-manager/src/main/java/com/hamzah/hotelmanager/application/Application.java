package com.hamzah.hotelmanager.application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Application
{
	public static void main(String[] args) throws IOException
	{
		System.out.println("application started");

		HotelManager hotelManager = new HotelManager();
		hotelManager.printMenu();

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
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
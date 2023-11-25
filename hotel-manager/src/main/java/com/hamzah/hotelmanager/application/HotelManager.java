package com.hamzah.hotelmanager.application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.hamzah.hotelmanager.database.DatabaseManager;
import com.hamzah.hotelmanager.model.Booking;

public class HotelManager
{
	private DatabaseManager databaseManager;

	public HotelManager()
	{
		databaseManager = new DatabaseManager();
	}

	public void printMenu()
	{
		System.out.println("-----------");

		System.out.println("Welcome to hotel manager, please select one of the following options:");
		System.out.println("1. View hotels with names and number of rooms available and total rooms");
		System.out.println("2. View bookings for a given hotel");
		System.out.println("3. Make a booking for a given hotel");
		System.out.println("0. to exit the program");

		System.out.println("-----------");
	}

	public void viewDetails()
	{
		// view hotels, rooms, and available rooms
		ResultSet rs = databaseManager.executeSql("SELECT * from hotel");
		System.out.println("Hotels;");
		try
		{
			while (rs.next())
			{
				String hotelName = rs.getString("name");
				int hotelNoRooms = rs.getInt("no_of_rooms");
				int hotelAvailableRooms = rs.getInt("available_rooms");
				System.out.println(hotelName + " has " + hotelNoRooms + " number of rooms and " + hotelAvailableRooms
						+ " available rooms");
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public void viewBookings()
	{
		// view bookings
		ResultSet rs = databaseManager.executeSql("SELECT * from booking join hotel on (booking.hotel_id = hotel.id)");
		System.out.println("Current bookings;");
		try
		{
			while (rs.next())
			{
				String customerName = rs.getString("customer_name");
				String customerEmail = rs.getString("customer_email");
				LocalDateTime customerStartTime = rs.getTimestamp("start_date_time").toLocalDateTime();
				LocalDateTime customerEndTime = rs.getTimestamp("end_date_time").toLocalDateTime();
				String hotelName = rs.getString("name");
				System.out.println(customerName + " is booked in to " + hotelName + " from " + customerStartTime
						+ " to " + customerEndTime + ". Email address: " + customerEmail);
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void getBookingInfo() throws IOException
	{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		System.out.println("-----------");
		System.out.println("Hello! Welcome to Create a Booking!");
		System.out.println("-----------");

		Booking newBooking = new Booking();

		System.out.println("To get started, please enter your name: ");
		input = reader.readLine();
		newBooking.setCustomerName(input);
		System.out.println("Great! Now lets get your email address: ");
		input = reader.readLine();
		newBooking.setCustomerEmail(input);
		listHotels();
		System.out.println("Which hotel would you like to stay at?");
		input = reader.readLine();
		newBooking.setHotelid(Integer.parseInt(input));
		System.out.println("When do you plan to check in? (Date format is dd-MM-yyyy HH:mm)");
		while (newBooking.getStartDateTime() == null)
		{
			input = reader.readLine();
			LocalDateTime startDateTime = parseDate(input);
			newBooking.setStartDateTime(startDateTime);
		}
		System.out.println("When do you plan to leave?");
		while (newBooking.getEndDateTime() == null)
		{
			input = reader.readLine();
			LocalDateTime endDateTime = parseDate(input);
			newBooking.setEndDateTime(endDateTime);
		}
		makeBooking(newBooking);
	}

	private LocalDateTime parseDate(String input)
	{
		LocalDateTime ldt = null;
		try
		{
			ldt = LocalDateTime.parse(input, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
		} catch (DateTimeException e)
		{
			System.out.println("Sorry, your date time was not in the correct format, it should be: dd-MM-yyyy HH:mm");
		}

		return ldt;
	}

	public void makeBooking(Booking booking)
	{
		Object[] parameters =
		{ booking.getCustomerName(), booking.getCustomerEmail(), booking.getStartDateTime(), booking.getEndDateTime(),
				booking.getHotelid() };
		// add a booking
		boolean inserted = databaseManager.executeSql("INSERT INTO `booking` "
				+ "(`customer_name`,`customer_email`,`start_date_time`,`end_date_time`,`hotel_id`) "
				+ "VALUES (?, ?, ?, ?, ?);", parameters);

		if (inserted)
		{
			System.out.println("Booking created successfully.");
		} else
		{
			System.out.println("Sorry there was en error making your booking.");
		}
	}

	private void listHotels()
	{
		ResultSet rs = databaseManager.executeSql("SELECT * from hotel");
		System.out.println("Hotels;");
		try
		{
			while (rs.next())
			{
				String hotelName = rs.getString("name");
				int hotelID = rs.getInt("id");
				System.out.println(hotelID + " " + hotelName);
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}

package com.hamzah.hotelmanager.model;

import java.time.LocalDateTime;

public class Booking
{
	private int id;
	private String customerName;
	private String customerEmail;
	private LocalDateTime startDateTime;
	private LocalDateTime endDateTime;
	private int hotelid;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getCustomerName()
	{
		return customerName;
	}

	public void setCustomerName(String customerName)
	{
		this.customerName = customerName;
	}

	public String getCustomerEmail()
	{
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail)
	{
		this.customerEmail = customerEmail;
	}

	public LocalDateTime getStartDateTime()
	{
		return startDateTime;
	}

	public void setStartDateTime(LocalDateTime startDateTime)
	{
		this.startDateTime = startDateTime;
	}

	public LocalDateTime getEndDateTime()
	{
		return endDateTime;
	}

	public void setEndDateTime(LocalDateTime endDateTime)
	{
		this.endDateTime = endDateTime;
	}

	public int getHotelid()
	{
		return hotelid;
	}

	public void setHotelid(int hotelid)
	{
		this.hotelid = hotelid;
	}
}

package com.android.cinemarkbulgaria;

public class BY_GPS {
String theater,city;
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	public String getTheater() {
		return theater;
	}

	public void setTheater(String theater) {
		this.theater = theater;
	}

	@Override
	public String toString() {
		return theater+","+city;
	}

	

}

package com.android.cinemarkbulgaria;

public class Theater {
	String theater,id;
	public String getTheater() {
		return theater;
	}

	public void setTheater(String theater) {
		this.theater = theater;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return theater+id;
	}
}

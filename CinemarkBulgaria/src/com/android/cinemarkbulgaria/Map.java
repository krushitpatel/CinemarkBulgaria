package com.android.cinemarkbulgaria;

public class Map {
	private String lati,longi;

	public String getLati() {
		return lati;
	}

	public void setLati(String lati) {
		this.lati = lati;
	}

	public String getLongi() {
		return longi;
	}

	public void setLongi(String longi) {
		this.longi = longi;
	}

	@Override
	public String toString() {
		return lati+longi;
	}
	
}

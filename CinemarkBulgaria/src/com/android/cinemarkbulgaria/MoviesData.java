package com.android.cinemarkbulgaria;



public class MoviesData {
	
	//private variables
	int _id;
	String _name;
	String genre;
	
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	// Empty constructor
	public MoviesData(){
		
	}
	// constructor
	public MoviesData(int id, String name,String genre){
		this._id = id;
		this._name = name;
		this.genre=genre;
		
	}
	
	// constructor
	public MoviesData(String name,String genre){
		this._name = name;
		this.genre=genre;
		
	}
	// getting ID
	public int getID(){
		return this._id;
	}
	
	// setting id
	public void setID(int id){
		this._id = id;
	}
	
	// getting name
	public String getName(){
		return this._name;
	}
	
	// setting name
	public void setName(String name){
		this._name = name;
	}
	
	// getting phone number
	
	@Override
	public String toString() {
		return _id + _name+genre;
	}
	
}


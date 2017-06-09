package com.android.cinemarkbulgaria;

public class Movies {
	String film,genre,ratting,trailer;

	public String getTrailer() {
		return trailer;
	}

	public void setTrailer(String trailer) {
		this.trailer = trailer;
	}

	public String getFilm() {
		return film;
	}

	public void setFilm(String film) {
		this.film = film;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getRatting() {
		return ratting;
	}

	public void setRatting(String ratting) {
		this.ratting = ratting;
	}

	@Override
	public String toString() {
		return film + genre+ ratting+trailer;
	}
}

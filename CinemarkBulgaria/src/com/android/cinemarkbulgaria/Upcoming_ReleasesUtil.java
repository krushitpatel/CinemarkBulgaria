package com.android.cinemarkbulgaria;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class Upcoming_ReleasesUtil {
	static public class TheaterPullParser{
		static ArrayList<Movies> parseTheaters(InputStream in) throws XmlPullParserException, IOException{
			
		String lastTag="";
		String text;
		XmlPullParserFactory factory=XmlPullParserFactory.newInstance();
		XmlPullParser xpp=factory.newPullParser();
		xpp.setInput(in, "UTF-8");
		Movies film = null ;
		ArrayList<Movies> filmlist=new ArrayList<Movies>();
		int eventType=xpp.getEventType();
		while(eventType!=XmlPullParser.END_DOCUMENT){
			
		
		
			if(eventType==XmlPullParser.START_TAG){
				lastTag=xpp.getName().toString(); 
				if(lastTag.equalsIgnoreCase("film")){
					
				}
			}
			else if(eventType==XmlPullParser.TEXT){
				if(lastTag.equalsIgnoreCase("title")){
					text=xpp.getText();
					System.out.println("title "+xpp.getText());
					film=new Movies();
					film.setFilm(text);
					lastTag="";
					}
				else if(lastTag.equalsIgnoreCase("genre")){
					text=xpp.getText();
					System.out.println("genre "+xpp.getText());
					
					film.setGenre(text);
					lastTag="";
				}else if(lastTag.equalsIgnoreCase("parent-guide-rating")){
					text=xpp.getText();
					System.out.println("ratting "+xpp.getText());
				
					film.setRatting(text);
					filmlist.add(film);
					lastTag="";
				}
	
			}
			eventType=xpp.next();
	}
	
		return filmlist;
		
	}
		
	}
}

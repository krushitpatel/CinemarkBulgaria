package com.android.cinemarkbulgaria;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class Week_ReleasesUtil {
	static public class TheaterPullParser{
		static ArrayList<Movies> parseTheaters(InputStream in) throws XmlPullParserException, IOException{
		String arg1=null;
		String arg2=null;
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
					System.out.println(xpp.getAttributeValue(1));
					System.out.println(xpp.getAttributeValue(2));
					arg1=xpp.getAttributeValue(1);
					arg2=xpp.getAttributeValue(2);
				}
			}
					
			
			else if(eventType==XmlPullParser.TEXT){
				if(lastTag.equals("film")){
					text=xpp.getText();
					System.out.println("film "+xpp.getText());
					film=new Movies();
					film.setFilm(text);
					film.setGenre(arg1);
					film.setRatting(arg2);
					filmlist.add(film);
					}
			}
				
			eventType=xpp.next();
	}
	
		return filmlist;
		
	}
		
	}
}

package com.android.cinemarkbulgaria;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;


public class BY_GPSUtil {
	static public class TheaterPullParser{
		static ArrayList<BY_GPS> parseTheaters(InputStream in) throws XmlPullParserException, IOException{
			
		String lastTag="";
		String text = null;
		XmlPullParserFactory factory=XmlPullParserFactory.newInstance();
		XmlPullParser xpp=factory.newPullParser();
		xpp.setInput(in, "UTF-8");
		BY_GPS city = null;
		ArrayList<BY_GPS> citylist=new ArrayList<BY_GPS>();
		int eventType=xpp.getEventType();
		while(eventType!=XmlPullParser.END_DOCUMENT){
			
			
			if(eventType==XmlPullParser.START_TAG){
				lastTag=xpp.getName().toString();
				}
			else if(eventType==XmlPullParser.TEXT){
				if(lastTag.equals("name")){
					text=xpp.getText();
					System.out.println("name "+xpp.getText());
					city=new BY_GPS();
					city.setTheater(text);
					lastTag="";
					}
			
				else if(lastTag.equals("city")){
					text=xpp.getText();
				System.out.println("city "+xpp.getText());
					city.setCity(text);
					citylist.add(city);
					lastTag="";
				}
			}
			eventType=xpp.next();
	}
		return citylist;
		
	}
		
	}
	
}

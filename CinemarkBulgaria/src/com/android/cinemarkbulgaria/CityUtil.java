package com.android.cinemarkbulgaria;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;


//parsing xml data from url
public class CityUtil {
	static public class TheaterPullParser{
		static ArrayList<City> parseTheaters(InputStream in) throws XmlPullParserException, IOException
		{
			String str1 = null;
		String lastTag="";
		String text;
		XmlPullParserFactory factory=XmlPullParserFactory.newInstance();
		XmlPullParser xpp=factory.newPullParser();
		xpp.setInput(in, "UTF-8");
		City city = null;
		ArrayList<City> citylist=new ArrayList<City>();
		
		int eventType=xpp.getEventType();
		while(eventType!=XmlPullParser.END_DOCUMENT){
			
			
			if(eventType==XmlPullParser.START_TAG){
				lastTag=xpp.getName().toString();
				if(lastTag.equals("city")){	
				city=new City();
				str1=xpp.getAttributeValue(0);
				}
			}
				
			else if(eventType==XmlPullParser.TEXT){
				if(lastTag.equals("city")){
					text=xpp.getText();
					System.out.println("city "+xpp.getText());
					city=new City();
					city.setCity(text);
					city.setId(str1);
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

package com.android.cinemarkbulgaria;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

//parsing xml data from url
public class TheaterUtil {
	static public class TheaterPullParser{
		static ArrayList<Theater> parseTheaters(InputStream in) throws XmlPullParserException, IOException{
			String str1 = null;
		String lastTag="";
		String text;
		XmlPullParserFactory factory=XmlPullParserFactory.newInstance();
		XmlPullParser xpp=factory.newPullParser();
		xpp.setInput(in, "UTF-8");
		Theater theater = null;
		ArrayList<Theater> citylist=new ArrayList<Theater>();
		int eventType=xpp.getEventType();
		while(eventType!=XmlPullParser.END_DOCUMENT){
			
		
			if(eventType==XmlPullParser.START_TAG){
				lastTag=xpp.getName().toString();
				if(lastTag.equals("theater")){
				theater=new Theater();
				str1=xpp.getAttributeValue(0);
				System.out.println("id "+xpp.getAttributeValue(0));
				}
			}
			else if(eventType==XmlPullParser.TEXT){
				if(lastTag.equals("name")){
					text=xpp.getText();
					System.out.println("name "+xpp.getText());
					
					theater=new Theater();
					theater.setId(str1);
					theater.setTheater(text);
					citylist.add(theater);
					lastTag="";
					}
			}
			eventType=xpp.next();
		
	}
		return citylist;
		
	}
		
	}
	
}

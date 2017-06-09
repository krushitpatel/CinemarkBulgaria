package com.android.cinemarkbulgaria;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class MapUtil {
	static public class TheaterPullParser{
		static ArrayList<Map> parseTheaters(InputStream in) throws XmlPullParserException, IOException{
			
		String lastTag="";
		String text = null;
		XmlPullParserFactory factory=XmlPullParserFactory.newInstance();
		XmlPullParser xpp=factory.newPullParser();
		xpp.setInput(in, "UTF-8");
		Map map = null;
		ArrayList<Map> maplist=new ArrayList<Map>();
		int eventType=xpp.getEventType();
		while(eventType!=XmlPullParser.END_DOCUMENT){
			
			
			if(eventType==XmlPullParser.START_TAG){
				lastTag=xpp.getName().toString();
				if(lastTag.equalsIgnoreCase("address")){
					System.out.println("lati "+xpp.getAttributeValue(0));
					System.out.println("longi "+xpp.getAttributeValue(1));
					map.setLati(xpp.getAttributeValue(0));
					map.setLongi(xpp.getAttributeValue(1));
					map=new Map();
					maplist.add(map);
					lastTag="";
				}
				}
			else if(eventType==XmlPullParser.TEXT){
			}
			eventType=xpp.next();
	}
		return maplist;
		
	}
		
	}
	
}

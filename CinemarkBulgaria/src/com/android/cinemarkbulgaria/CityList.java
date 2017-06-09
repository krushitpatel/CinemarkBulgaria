package com.android.cinemarkbulgaria;


import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.android.cinemarkbulgaria.R;


public class CityList extends Activity implements OnClickListener{
	ListView lv;
	String URL="http://www.cinemark.com.br/mobile/xml/theaters/";
	ArrayList<City> data;
	ArrayAdapter<City> adapter;
	ImageButton img,ib1,ib2,ib3,ib4,ib5;
	TextView tv,tv1;
	ConnectionDetector cd;
	Boolean isInternetPresent = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.item_list);
		lv=(ListView) findViewById(R.id.citylist);
		img=(ImageButton) findViewById(R.id.imageButton1);
		ib1=(ImageButton) findViewById(R.id.imageButton2);
		ib2=(ImageButton) findViewById(R.id.imageButton3);
		ib3=(ImageButton) findViewById(R.id.imageButton4);
		ib4=(ImageButton) findViewById(R.id.imageButton1);
		ib5=(ImageButton) findViewById(R.id.ibsettings);
		ib1.setOnClickListener(this);
		ib2.setOnClickListener(this);
		ib3.setOnClickListener(this);
		ib4.setOnClickListener(this);
		ib5.setOnClickListener(this);
		new Data().execute(URL);
		lv.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(CityList.this,TheaterList.class);
				String album_id = ((TextView) view.findViewById(R.id.id)).getText().toString();
				intent.putExtra("album_id", album_id);
				startActivity(intent);
			}
			
		});
		//for go to home page
		img.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(CityList.this,MainActivity.class);
				startActivity(intent);
			}
		});
		
	}
	//for display the parse data from xml
	public class Data extends AsyncTask<String, Void, ArrayList<City>>{
		ProgressDialog dialog;
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			dialog=new ProgressDialog(CityList.this);
			dialog.setMessage("Please Wait....");
			dialog.show();
			
		}

		@Override
		protected ArrayList<City> doInBackground(String... params) {
			// TODO Auto-generated method stub
			cd = new ConnectionDetector(getApplicationContext());
			isInternetPresent = cd.isConnectingToInternet();
			if (!isInternetPresent) {
	            // Internet Connection is not present
				Intent i=new Intent(CityList.this,MainActivity.class);
				startActivity(i);
				Toast.makeText(getApplicationContext(), "Internet connection fail", Toast.LENGTH_LONG).show();
				
	          
	            // stop executing code by return
	          
	        }else{
			try {
				URL url=new URL(URL);
				HttpURLConnection con=(HttpURLConnection) url.openConnection();
				con.setRequestMethod("GET");
				con.connect();
				int statusCode=con.getResponseCode();
				if(statusCode==HttpURLConnection.HTTP_OK){
				InputStream in=con.getInputStream();
				data=CityUtil.TheaterPullParser.parseTheaters(in);
					//return TheaterUtil.TheaterPullParser.parseTheaters(in);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}}
			return null;
		}

		@Override
		protected void onPostExecute(ArrayList<City> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			dialog.dismiss();
			
		try{
			lv.setAdapter(new setadapter(CityList.this,data));
		
		}catch(Exception e){
			e.printStackTrace();
		}
			
		}
		
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
	public class setadapter extends BaseAdapter
	{	

		private ArrayList<City> data;
		public setadapter(Context context,ArrayList<City> data) 
		{

	       this.data = data;
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return data.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			LayoutInflater inflate=getLayoutInflater();
			convertView=inflate.inflate(R.layout.city_adapter,null);
			TextView tv=(TextView)convertView.findViewById(R.id.id);   
			TextView tv1=(TextView)convertView.findViewById(R.id.city);
			tv1.setText(data.get(position).getCity());
			tv.setText(data.get(position).getId());
			Typeface typeface=Typeface.createFromAsset(getAssets(),"DancingScript-Regular.otf");
			tv1.setTypeface(typeface);
			tv.setTypeface(typeface);
		return convertView;
		}
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
			case R.id.imageButton2:
				theatres();
				break;
			case R.id.imageButton3:
				movies();
				break;
			case R.id.imageButton4:
				Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.cinemark.com.br/"));
				startActivity(intent);
				break;
			case R.id.ibsettings:
				Intent i = new Intent(CityList.this,About.class);
				startActivity(i);
		}
		
		
	}
	private void theatres() {
		// TODO Auto-generated method stub
		final CharSequence[] items={"Nearby(GPS)","By City"};
		AlertDialog.Builder builder=new AlertDialog.Builder(this);
		builder.setTitle("Make Your Selection");
		builder.setItems(items, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				cd = new ConnectionDetector(getApplicationContext());
				isInternetPresent = cd.isConnectingToInternet();
		        // Check if Internet present
		        if (!isInternetPresent) {
		            // Internet Connection is not present
		           Toast.makeText(getApplicationContext(), "Internet connection fail", Toast.LENGTH_LONG).show();
		           
		        }
				
				if(items[which].equals("Nearby(GPS)")){
					
					
					Intent i=new Intent(CityList.this,BY_GPSList.class);
					startActivity(i);
				
				}
				else if(items[which].equals("By City")){
					Intent i=new Intent(CityList.this,CityList.class);
					startActivity(i);
				}
			}
		});
		AlertDialog alert=builder.create();
		alert.show();
		
	}
	private void movies() {
		// TODO Auto-generated method stub
		final CharSequence[] items={"Week Realeses","Now Showing","Upcoming movies","Top 10"};
		AlertDialog.Builder builder=new AlertDialog.Builder(this);
		builder.setTitle("Make Your Selection");
		builder.setItems(items, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Toast toast=Toast.makeText(getApplicationContext(), items[which]+" selected", Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.TOP, 10, 39);
				toast.show();
				if(items[which].equals("Week Realeses")){
					Intent intent=new Intent(CityList.this,Week_ReleasesList.class);
					startActivity(intent);
				}
				else if(items[which].equals("Now Showing")){
					Intent intent=new Intent(CityList.this,NowShowingList.class);
					startActivity(intent);
				}
				else if(items[which].equals("Upcoming movies")){
					Intent intent=new Intent(CityList.this,Upcoming_ReleasesList.class);
					startActivity(intent);
				}
				else if(items[which].equals("Top 10")){
					Intent intent=new Intent(CityList.this,Top10List.class);
					startActivity(intent);
				}
			}
		});
		AlertDialog alert=builder.create();
		alert.show();
	}
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		finish();
	}
}

package com.android.cinemarkbulgaria;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import com.android.cinemarkbulgaria.R;
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
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class BY_GPSList extends Activity implements OnClickListener{
	ListView lv;
	String URL="http://www.cinemark.com.br/mobile/xml/theaters/";
	ArrayList<BY_GPS> data;
	ArrayAdapter<BY_GPS> adapter;
	TextView tv,tv1,tv2,tv3;
	ImageButton img,ib1,ib2,ib3,ib4,ib5;
	ConnectionDetector cd;
	Boolean isInternetPresent = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
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
		
		img.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(BY_GPSList.this,MainActivity.class);
				startActivity(intent);
			}
		});
	}
	public class Data extends AsyncTask<String, Void, ArrayList<BY_GPS>>{
		ProgressDialog dialog;
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			dialog=new ProgressDialog(BY_GPSList.this);
			dialog.setMessage("Please Wait....");
			dialog.show();
		}

		@Override
		protected ArrayList<BY_GPS> doInBackground(String... params) {
			// TODO Auto-generated method stub
			
			try {
				URL url=new URL(URL);
				HttpURLConnection con=(HttpURLConnection) url.openConnection();
				con.setRequestMethod("GET");
				con.connect();
				int statusCode=con.getResponseCode();
				if(statusCode==HttpURLConnection.HTTP_OK){
				InputStream in=con.getInputStream();
				data=BY_GPSUtil.TheaterPullParser.parseTheaters(in);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(ArrayList<BY_GPS> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			dialog.dismiss();
		try{
		lv.setAdapter(new setadapter(BY_GPSList.this,data));
		}catch(Exception e){
			e.printStackTrace();
		}
		}
	}
	public class setadapter extends BaseAdapter
	{	

		private ArrayList<BY_GPS> data;
		public setadapter(Context context,ArrayList<BY_GPS> data) 
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
			
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			LayoutInflater inflate=getLayoutInflater();
			convertView=inflate.inflate(R.layout.by_gps_adapter,null);
			TextView tv=(TextView)convertView.findViewById(R.id.theater);   
			TextView tv1=(TextView)convertView.findViewById(R.id.city);
			ImageButton map=(ImageButton) convertView.findViewById(R.id.mapimg);
			tv.setText(data.get(position).getTheater());
			tv1.setText(data.get(position).getCity());
			Typeface typeface=Typeface.createFromAsset(getAssets(), "Sergipe.ttf");
			tv.setTypeface(typeface);
			tv1.setTypeface(typeface);
			map.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent i=new Intent(BY_GPSList.this,Google_Map.class);
					startActivity(i);
				}
			});
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
				Intent i = new Intent(BY_GPSList.this,About.class);
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
					
					
					Intent i=new Intent(BY_GPSList.this,BY_GPSList.class);
					startActivity(i);
				
				}
				else if(items[which].equals("By City")){
					Intent i=new Intent(BY_GPSList.this,CityList.class);
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
					Intent intent=new Intent(BY_GPSList.this,Week_ReleasesList.class);
					startActivity(intent);
				}
				else if(items[which].equals("Now Showing")){
					Intent intent=new Intent(BY_GPSList.this,NowShowingList.class);
					startActivity(intent);
				}
				else if(items[which].equals("Upcoming movies")){
					Intent intent=new Intent(BY_GPSList.this,Upcoming_ReleasesList.class);
					startActivity(intent);
				}
				else if(items[which].equals("Top 10")){
					Intent intent=new Intent(BY_GPSList.this,Top10List.class);
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

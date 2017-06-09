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
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.cinemarkbulgaria.R;

public class Top10List extends Activity implements OnClickListener{
	ListView lv;
	String URL="http://www.cinemark.com.br/mobile/xml/films/?top=10";
	ArrayList<Movies> data;
	ArrayAdapter<Movies> adapter;
	TextView tv1,tv2,tv3;
	ConnectionDetector cd;
	Boolean isInternetPresent = false;
	ImageButton img,ib1,ib2,ib3,ib4,ib5;
	ImageView iv;
	Integer[] img1={R.drawable.interestelar,
					R.drawable.draculaahistorianuncacontada,
					R.drawable.annabelle1,
					R.drawable.timmaia,
					R.drawable.amansaomagica,
					R.drawable.madeinchina,
					R.drawable.festanoceu3d,
					R.drawable.oapocalipse,
					R.drawable.ocandidatohonesto,
					R.drawable.ojuiz};
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
				Intent intent=new Intent(Top10List.this,MainActivity.class);
				startActivity(intent);
			}
		});
	}
	public class Data extends AsyncTask<String, Void, ArrayList<Movies>>{
		ProgressDialog dialog;
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			dialog=new ProgressDialog(Top10List.this);
			dialog.setMessage("Please Wait....");
			dialog.show();
		}

		@Override
		protected ArrayList<Movies> doInBackground(String... params) {
			// TODO Auto-generated method stub
			
			try {
				URL url=new URL(URL);
				HttpURLConnection con=(HttpURLConnection) url.openConnection();
				con.setRequestMethod("GET");
				con.connect();
				int statusCode=con.getResponseCode();
				if(statusCode==HttpURLConnection.HTTP_OK){
				InputStream in=con.getInputStream();
				data=Top10Util.TheaterPullParser.parseTheaters(in);
					
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(ArrayList<Movies> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			dialog.dismiss();
			try{
		lv.setAdapter(new setadapter(Top10List.this,data,img1));
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
	public class setadapter extends BaseAdapter
	{	
		
		private ArrayList<Movies> data;
		private Integer[] img1;
		
		public setadapter(Context context,ArrayList<Movies> data,Integer[] img1) 
		{

	       this.data = data;
	       this.img1=img1;
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
			convertView=inflate.inflate(R.layout.movie_adapter,null);
			TextView tv1=(TextView) convertView.findViewById(R.id.title);
			TextView tv2=(TextView) convertView.findViewById(R.id.genre);
			TextView tv3=(TextView)convertView.findViewById(R.id.ratting);
			iv=(ImageView) convertView.findViewById(R.id.imageView1);
			tv1.setText(data.get(position).getFilm());
			tv2.setText(data.get(position).getGenre());
			tv3.setText(data.get(position).getRatting());
			try{
			iv.setImageResource(img1[position]);
			}catch(Exception e){
				e.printStackTrace();
			}
			Typeface typeface=Typeface.createFromAsset(getAssets(), "Sergipe.ttf");
			tv1.setTypeface(typeface);
			tv2.setTypeface(typeface);
			tv3.setTypeface(typeface);
		return convertView;
		}
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
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
				Intent i = new Intent(Top10List.this,About.class);
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
					
					
					Intent i=new Intent(Top10List.this,BY_GPSList.class);
					startActivity(i);
				
				}
				else if(items[which].equals("By City")){
					Intent i=new Intent(Top10List.this,CityList.class);
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
				cd = new ConnectionDetector(getApplicationContext());
				isInternetPresent = cd.isConnectingToInternet();
				if(items[which].equals("Week Realeses")){
					if (!isInternetPresent) {
			            // Internet Connection is not present
			           Toast.makeText(getApplicationContext(), "Internet connection fail", Toast.LENGTH_LONG).show();
			           Intent i=new Intent(Top10List.this,Week_Releasesdb.class);
			           startActivity(i);
			        }
					else{
						Intent intent=new Intent(Top10List.this,Week_ReleasesList.class);
						startActivity(intent);
			        }
				}
				else if(items[which].equals("Now Showing")){
					if (!isInternetPresent) {
			            // Internet Connection is not present
			           Toast.makeText(getApplicationContext(), "Internet connection fail", Toast.LENGTH_LONG).show();
			           Intent i=new Intent(Top10List.this,NowShowing_db.class);
			           startActivity(i);
			        }else{
			        	Intent intent=new Intent(Top10List.this,NowShowingList.class);
						startActivity(intent);
			        }
				}
				else if(items[which].equals("Upcoming movies")){
					if (!isInternetPresent) {
			            // Internet Connection is not present
			           Toast.makeText(getApplicationContext(), "Internet connection fail", Toast.LENGTH_LONG).show();
			           
			        }else{
			        	Intent intent=new Intent(Top10List.this,Upcoming_ReleasesList.class);
			        	startActivity(intent);
			        }
				}
				else if(items[which].equals("Top 10")){
					if (!isInternetPresent) {
			            // Internet Connection is not present
			           Toast.makeText(getApplicationContext(), "Internet connection fail", Toast.LENGTH_LONG).show();
			           Intent i=new Intent(Top10List.this,Top_10db.class);
			           startActivity(i);
			        }else{
			        	Intent intent=new Intent(Top10List.this,Top10List.class);
			        	startActivity(intent);
			        }
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

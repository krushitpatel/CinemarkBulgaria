package com.android.cinemarkbulgaria;
import com.android.cinemarkbulgaria.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{
	ImageButton ib1,ib2,ib3,ib4,ib5;
	ConnectionDetector cd;
	Boolean isInternetPresent = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
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
				Intent i = new Intent(MainActivity.this,About.class);
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
				Toast toast=Toast.makeText(getApplicationContext(), items[which]+" selected", Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.TOP, 10, 39);
				toast.show();
				if(items[which].equals("Nearby(GPS)")){
					if (!isInternetPresent) {
			            // Internet Connection is not present
			           Toast.makeText(getApplicationContext(), "Internet connection fail", Toast.LENGTH_SHORT).show();
			           
			        }else{
					Intent i=new Intent(MainActivity.this,BY_GPSList.class);
					startActivity(i);
			        }
				}
				else if(items[which].equals("By City")){
					if (!isInternetPresent) {
			            // Internet Connection is not present
			           Toast.makeText(getApplicationContext(), "Internet connection fail", Toast.LENGTH_SHORT).show();
			           
			        }else{
					Intent i=new Intent(MainActivity.this,CityList.class);
					startActivity(i);
			        }
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
				Toast toast=Toast.makeText(getApplicationContext(), items[which]+" selected", Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.TOP, 10, 39);
				toast.show();
				if(items[which].equals("Week Realeses")){
					if (!isInternetPresent) {
			            // Internet Connection is not present
			           Toast.makeText(getApplicationContext(), "Internet connection fail", Toast.LENGTH_SHORT).show();
			           Intent i=new Intent(MainActivity.this,Week_Releasesdb.class);
			           startActivity(i);
			        }
					else{
						Intent intent=new Intent(MainActivity.this,Week_ReleasesList.class);
						startActivity(intent);
			        }
				}
				else if(items[which].equals("Now Showing")){
					if (!isInternetPresent) {
			            // Internet Connection is not present
			           Toast.makeText(getApplicationContext(), "Internet connection fail", Toast.LENGTH_SHORT).show();
			           Intent i=new Intent(MainActivity.this,NowShowing_db.class);
			           startActivity(i);
			        }else{
			        	Intent intent=new Intent(MainActivity.this,NowShowingList.class);
						startActivity(intent);
			        }
				}
				else if(items[which].equals("Upcoming movies")){
					if (!isInternetPresent) {
			            // Internet Connection is not present
			           Toast.makeText(getApplicationContext(), "Internet connection fail", Toast.LENGTH_SHORT).show();
			           
			        }else{
			        	Intent intent=new Intent(MainActivity.this,Upcoming_ReleasesList.class);
			        	startActivity(intent);
			        }
				}
				else if(items[which].equals("Top 10")){
					if (!isInternetPresent) {
			            // Internet Connection is not present
			           Toast.makeText(getApplicationContext(), "Internet connection fail", Toast.LENGTH_SHORT).show();
			           Intent i=new Intent(MainActivity.this,Top_10db.class);
			           startActivity(i);
			        }else{
			        	Intent intent=new Intent(MainActivity.this,Top10List.class);
			        	startActivity(intent);
			        }
				}
			}
		});
		AlertDialog alert=builder.create();
		alert.show();
	}
}

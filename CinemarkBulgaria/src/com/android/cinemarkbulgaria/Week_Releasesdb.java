package com.android.cinemarkbulgaria;

import java.util.ArrayList;

import com.android.cinemarkbulgaria.NowShowingList.Data;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Week_Releasesdb extends Activity implements OnClickListener{
	ArrayList<MoviesData> movies;
	Integer[] data={R.drawable.asaventurasdoaviaovermelho,R.drawable.boasorte, R.drawable.elsaefred,R.drawable.exododeusesereis, R.drawable.omensageiro,R.drawable.oscarasdepau,R.drawable.queromatarmeuchefe2,R.drawable.semdireitoaresgate,R.drawable.setediassemfim,R.drawable.umalongaviagem};
	ListView lv;
	ImageView iv;
	ImageButton img,ib1,ib2,ib3,ib4,ib5;
	ConnectionDetector cd;
	Boolean isInternetPresent = false;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_list);
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
        DataBasehandler db = new DataBasehandler(this);
        lv=(ListView) findViewById(R.id.citylist);
        
		img.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(Week_Releasesdb.this,MainActivity.class);
				startActivity(intent);
			}
		});
        /**
         * CRUD Operations
         * */
        // Inserting Contacts
		
        Log.d("Insert: ", "Inserting ..");
        db.addContact(new MoviesData("As Aventuras Do Aviao Vermelho","Infantil"));
        db.addContact(new MoviesData("Boa Sorte","Drama"));
        db.addContact(new MoviesData("Elsa Fred","Romance"));
        db.addContact(new MoviesData("Exodo" ,"Drama"));
        db.addContact(new MoviesData("O Mensageiro","Anos"));
        db.addContact(new MoviesData("Os caras De Pau","Comedia"));
        db.addContact(new MoviesData("Quero Matar Meu Chefe 2","Comedia"));
        db.addContact(new MoviesData("Sem Direito A Resgate","Comedia"));
        db.addContact(new MoviesData("Sete Dias Sem Fim","Drama"));
        db.addContact(new MoviesData("Uma Longa Viagem","Drama"));
	
        // Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");
        movies = db.getAllMovies();       
 
        for (MoviesData cn : movies) {
            String log = "Id: " + cn.getID() + "Name: " + cn.getName() + "Genre: " + cn.getGenre();
                // Writing Contacts to log
        Log.d("Name: ", log);
        
        }
        //ArrayAdapter<Movies> adapter=new ArrayAdapter<Contact>(getApplicationContext(), android.R.layout.simple_expandable_list_item_1,contacts);
        //lv.setAdapter(adapter);
        lv.setAdapter(new setadapter(Week_Releasesdb.this,movies, data));
    }
    public class setadapter extends BaseAdapter
	{	

		private ArrayList<MoviesData> movies;
		private Integer[] data;
		public setadapter(Context context,ArrayList<MoviesData> movies,Integer[] data) 
		{
			this.data=data;
	        this.movies = movies;
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return movies.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return movies.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			LayoutInflater inflate=getLayoutInflater();
			convertView=inflate.inflate(R.layout.db_list,null);
			//TextView tv=(TextView)convertView.findViewById(R.id.id);   
			TextView tv1=(TextView)convertView.findViewById(R.id.title);
			TextView tv2=(TextView)convertView.findViewById(R.id.genre);
			
			iv=(ImageView) convertView.findViewById(R.id.imageView1);
			//TextView tv2=(TextView)convertView.findViewById(R.id.theaterid);
			tv1.setText(movies.get(position).getName());
			//tv.set(contacts.get(position).getID());
			tv2.setText(movies.get(position).getGenre());
			try{
			iv.setImageResource(data[position]);
			}catch(Exception e){
				e.printStackTrace();
			}
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
				Intent i = new Intent(Week_Releasesdb.this,About.class);
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
		       
				
				if(items[which].equals("Nearby(GPS)")){
					
					if(!isInternetPresent){
					Intent i=new Intent(Week_Releasesdb.this,MainActivity.class);
					startActivity(i);
					Toast.makeText(getApplicationContext(), "Internet connection fail", Toast.LENGTH_SHORT);
					}
				}
				else if(items[which].equals("By City")){
					if(!isInternetPresent){
					Intent i=new Intent(Week_Releasesdb.this,MainActivity.class);
					startActivity(i);
					Toast.makeText(getApplicationContext(), "Internet connection fail", Toast.LENGTH_SHORT);
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
				if(items[which].equals("Week Realeses")){
					if(!isInternetPresent){
					Intent intent=new Intent(Week_Releasesdb.this,Week_Releasesdb.class);
					startActivity(intent);
					}
				}
				else if(items[which].equals("Now Showing")){
					if(!isInternetPresent){
					Intent intent=new Intent(Week_Releasesdb.this,NowShowing_db.class);
					startActivity(intent);
					}
				}
				else if(items[which].equals("Upcoming movies")){
					if(!isInternetPresent){
					Intent intent=new Intent(Week_Releasesdb.this,MainActivity.class);
					startActivity(intent);
					}
				}
				else if(items[which].equals("Top 10")){
					if(!isInternetPresent){
					Intent intent=new Intent(Week_Releasesdb.this,Top_10db.class);
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

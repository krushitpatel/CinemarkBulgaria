package com.android.cinemarkbulgaria;

import java.util.ArrayList;
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

public class NowShowing_db extends Activity implements OnClickListener{
	ArrayList<MoviesData> movies;
	Integer[] data={R.drawable.alendadeoz,R.drawable.annabelle1, R.drawable.boyhooddainfanciaajuventude,R.drawable.amansaomagica,R.drawable.alexandreodiaterrivelhorrivelespantosoehorroroso,R.drawable.apneia,R.drawable.ficinovosjovensogarotomisterioso,R.drawable.ficia,R.drawable.ficiadv, R.drawable.ficiavioes,R.drawable.ficiernestecelestine,R.drawable.ficifrozenumaaventuracongelante3d,R.drawable.ficiaguerradosbotoes,R.drawable.ficikids,R.drawable.ficiamarchadospinguins,R.drawable.valente};
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
				Intent intent=new Intent(NowShowing_db.this,MainActivity.class);
				startActivity(intent);
			}
		});
        /**
         * CRUD Operations
         * */
        // Inserting Contacts
	
        Log.d("Insert: ", "Inserting ..");
        db.addContact(new MoviesData("A Lenda de Oz","Adventura"));
        db.addContact(new MoviesData("Alexandre","Comedia"));
        db.addContact(new MoviesData("Annabelle","Terror"));
        db.addContact(new MoviesData("Apenia","Drama"));
        db.addContact(new MoviesData("BoyHood","Drama"));
        //db.addContact(new Movies(""));
        db.addContact(new MoviesData("FICI - Novos Jovens - Felix","Infantil"));
        db.addContact(new MoviesData("FICI - 7 x Animação", "Infantil"));
        db.addContact(new MoviesData("FICI - As Aventuras de Azur e Asmar","Infantil"));
        db.addContact(new MoviesData("FICI - Aviões 3D","Infantil"));
        db.addContact(new MoviesData("FICI - Ernest e Célestine", "Infantil"));
        db.addContact(new MoviesData("FICI - Frozen - Uma Aventura Congelante 3D","Infantil"));
        db.addContact(new MoviesData("FICI - A Guerra dos Botões","Drama"));
        db.addContact(new MoviesData("FICI - Comkids","Infantil"));
        db.addContact(new MoviesData("FICI - A Marcha dos Pinguins", "Documentario"));
        db.addContact(new MoviesData("Made In China","Comedia"));
        db.addContact(new MoviesData("November Man","Acao"));
        db.addContact(new MoviesData("A Mansao Magica","Infantil"));
        db.addContact(new MoviesData("Valente", "Aventura"));
       // db.addContact(new Movies("",""));
	
      
  
        // Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");
        movies = db.getAllMovies();       
 
        for (MoviesData cn : movies) {
            String log = "Id: " + cn.getID()+ "Name: " + cn.getName()+ "Genre: " + cn.getGenre();
                // Writing Contacts to log
        Log.d("Name: ", log);
      
        }
        //ArrayAdapter<Movies> adapter=new ArrayAdapter<Contact>(getApplicationContext(), android.R.layout.simple_expandable_list_item_1,contacts);
        //lv.setAdapter(adapter);
        lv.setAdapter(new setadapter(NowShowing_db.this,movies, data));
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
				Intent i = new Intent(NowShowing_db.this,About.class);
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
				
				if(items[which].equals("Nearby(GPS)")){
					
					if (!isInternetPresent) {
			            // Internet Connection is not present
						Intent i=new Intent(NowShowing_db.this,MainActivity.class);
						startActivity(i);
						Toast.makeText(getApplicationContext(), "Internet connection fail", Toast.LENGTH_SHORT);
			        }
				}
				else if(items[which].equals("By City")){
					 if (!isInternetPresent) {
				            // Internet Connection is not present
						Intent i=new Intent(NowShowing_db.this,MainActivity.class);
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
					Intent intent=new Intent(NowShowing_db.this,Week_Releasesdb.class);
					startActivity(intent);
					}
				}
				else if(items[which].equals("Now Showing")){
					if(!isInternetPresent){
					Intent intent=new Intent(NowShowing_db.this,NowShowing_db.class);
					startActivity(intent);
					}
				}
				else if(items[which].equals("Upcoming movies")){
					if(!isInternetPresent){
					Intent intent=new Intent(NowShowing_db.this,MainActivity.class);
					startActivity(intent);
					}
				}
				else if(items[which].equals("Top 10")){
					if(!isInternetPresent){
					Intent intent=new Intent(NowShowing_db.this,Top_10db.class);
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

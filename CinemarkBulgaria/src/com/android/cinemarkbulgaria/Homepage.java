package com.android.cinemarkbulgaria;

import com.android.cinemarkbulgaria.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Homepage extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		Thread t=new Thread()
		{
			public void run()
			{
				try
				{
					sleep(1000);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				finally
				{
					Intent i=new Intent(Homepage.this,MainActivity.class);
					startActivity(i);
				}
			}
		};
		t.start();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
	
}
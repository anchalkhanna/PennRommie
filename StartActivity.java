package com.example.pennroommatefinder;

import android.os.Bundle;
import android.app.Activity;
import android.content.*;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class StartActivity extends Activity {
 public static String ServerIP="158.130.215.222";// Enter's server IP address
	Button button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.start, menu);
		return true;
	}
	
public void onClickRoomie(View view) {
		button = (Button) findViewById(R.id.roomie);
		Intent i = new Intent(this,LoginActivity.class);     
		startActivity(i);
		
	}
		
}

	
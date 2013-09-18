package com.example.pennroommatefinder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.pennroommatefinder.RegisterActivity.ClientThread;
import com.example.pennroommatefinder.Select.ClientThread1;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class LoginActivity extends Activity {
    Button button;
    public Intent i,j;
    public static String USERNAME="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		// Show the Up button in the action bar.
		setupActionBar();
		 TextView registerScreen = (TextView) findViewById(R.id.link_to_register);
		 
	        // Listening to register new account link
	        registerScreen.setOnClickListener(new View.OnClickListener() {
	 
	            public void onClick(View v) {
	                // Switching to Register screen
	                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
	                startActivity(i);
	            }
	        });
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	public void log(View view) {
		button = (Button) findViewById(R.id.btnLogin);
		i = new Intent(this,Select.class);
        j = new Intent(this,StartActivity.class);
		Thread cThread = new Thread(new ClientThread1());
        cThread.start();
        
       
        

	}
	
	public class ClientThread1 implements Runnable {
		
		public void run(){
			OutputStream dos;
	        
	        Socket clnt;
			try {
				final EditText usernamefield = (EditText) findViewById(R.id.editText_username);  
	            String username = usernamefield.getText().toString();  
	           
	        
	            final EditText passwordField = (EditText) findViewById(R.id.editText_password);  ;
	            String password = passwordField.getText().toString();
				clnt = new Socket(StartActivity.ServerIP,4444);
				dos = clnt.getOutputStream();
	             dos.write(("5"+","+username+","+password+"\n").getBytes());
	             BufferedReader in;
	             in = new BufferedReader(new InputStreamReader(clnt.getInputStream()));
	             int fromServer;
	             fromServer = Integer.parseInt(in.readLine());
	             if (fromServer==1){
	            	startActivity(i);
	             	USERNAME=username;
	             }
	             else{
	            	 startActivity(j);
	             }
	        	 clnt.close(); 
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
		
		
	}
	
	
		


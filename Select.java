package com.example.pennroommatefinder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.pennroommatefinder.AddEntryActivity.ClientThread;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.support.v4.app.NavUtils;
public class Select extends Activity {
	
	public Intent i;
	Button button;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select);
		// Show the Up button in the action bar.
		setupActionBar();
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
		getMenuInflater().inflate(R.menu.select, menu);
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
	
	public void add(View view) {
		button = (Button) findViewById(R.id.Addbut);
		Intent i = new Intent(this,AddEntryActivity.class);     
		startActivity(i);
		
	}
	
	
	public void view(View view) throws InterruptedException {
		Thread cThread = new Thread(new ClientThread1());
        cThread.start();
		button = (Button) findViewById(R.id.Viewbut);
		i = new Intent(this,ViewActivity.class);     
		String tag="Debug";
        Log.v(tag,"Reached");
			
		
	}
	
	public class ClientThread1 implements Runnable {
		String []RecievedArray;
		OutputStream dos;
		Socket clnt;
		ViewActivity v1;
        public void run() {
            	
            try {
                clnt = new Socket("StartActivity.ServerIP",4444);//type your device running server code 's ip address here
                dos=clnt.getOutputStream();
                String two="2\n";
                dos.write(two.getBytes(),0,2);  
                BufferedReader in;
                in = new BufferedReader(new InputStreamReader(clnt.getInputStream()));
				String tag="Debug";
                Log.v(tag,"Utsav");
                String fromServer;
                fromServer = in.readLine();
                RecievedArray=fromServer.split(":");
                Log.v(tag,RecievedArray[0]);
                v1.RecieveStringarray(RecievedArray);
                startActivity(i);
                
                clnt.close();
            } catch (UnknownHostException ex) {
                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
      
    }

}

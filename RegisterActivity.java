package com.example.pennroommatefinder;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.pennroommatefinder.AddEntryActivity.ClientThread;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class RegisterActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		// Show the Up button in the action bar.
		setupActionBar();
		
		TextView loginScreen = (TextView) findViewById(R.id.link_to_login);
		 
        // Listening to Login Screen link
        loginScreen.setOnClickListener(new View.OnClickListener() {
 
            public void onClick(View arg0) {
                // Closing registration screen
                // Switching to Login Screen/closing register screen
                finish();
            }
        });
	}
	
	public void Register(View view){
			
			Thread cThread = new Thread(new ClientThread());
	        cThread.start();
			Intent i = new Intent(this, LoginActivity.class);     
			startActivity(i);
				    
		}
		
		

		
		public class ClientThread implements Runnable {
			private RadioGroup radioSexGroup;
			private RadioButton radioSexButton; 
	        public void run() {
	        	OutputStream dos;
	            try {
	                Socket clnt = new Socket(StartActivity.ServerIP,4444);//type your device running server code 's ip address here
	                dos = clnt.getOutputStream();
	                byte []send;
	                final EditText nameField = (EditText) findViewById(R.id.reg_fullname);  
	                String name = nameField.getText().toString().toLowerCase();  
	                name = Character.toUpperCase(name.charAt(0)) + name.substring(1);
	                //Accepting Gender values from radio buttons 
	                radioSexGroup = (RadioGroup) findViewById(R.id.radioSex);
	                int selectedId = radioSexGroup.getCheckedRadioButtonId();
	                radioSexButton = (RadioButton) findViewById(selectedId);
	                String Gender=radioSexButton.getText().toString();
	                int gender=1;
	                if (Gender.equals("Male"))
	                	gender=1;//male
	                else if(Gender.equals("Female"))
	                	gender=0;//female
	                
	                final EditText Emailfield = (EditText) findViewById(R.id.reg_email);  
	                String Email = Emailfield.getText().toString().toLowerCase();  
	               
	            
	                final EditText passwordField = (EditText) findViewById(R.id.reg_password);  ;
	                String password = passwordField.getText().toString().toLowerCase(); 

	               String sent=("0,"+name+","+password+","+gender+","+Email);
	                send=(sent.getBytes());
	                dos.write(send);    
	                
	                clnt.close();
	            } catch (UnknownHostException ex) {
	                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
	            } catch (IOException ex) {
	                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
	            }
	        }
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
		getMenuInflater().inflate(R.menu.register, menu);
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

}

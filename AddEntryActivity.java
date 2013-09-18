package com.example.pennroommatefinder;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.support.v4.app.NavUtils;
import android.util.Log;
public class AddEntryActivity extends Activity {
Button button;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_entry);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_entry, menu);
		return true;
	}
	public void submit(View view) {
		
		Thread cThread = new Thread(new ClientThread());
        cThread.start();
        button = (Button) findViewById(R.id.button_register);
		Intent i = new Intent(this, Select.class);     
		startActivity(i);
		//finish(); //should use the finish if you need to preserve memory
		//other wise don't use it.
			    
	}
	
	

	
	public class ClientThread implements Runnable {
		 
        public void run() {
        	OutputStream dos;
            try {
                Socket clnt = new Socket("10.0.0.21",4444);//type your device running server code 's ip address here
                dos = clnt.getOutputStream();
                byte []send;
               // final EditText nameField = (EditText) findViewById(R.id.editText_enter_name);  
                String name = LoginActivity.USERNAME;//nameField.getText().toString();  
               
                final EditText ageField = (EditText) findViewById(R.id.editText_enter_age);
                int age=Integer.parseInt(ageField.getText().toString());
                
                //final EditText foodField = (EditText) findViewById(R.id.editText_enter_food);  
                //String food = foodField.getText().toString();  
               
            
                //final EditText nationalityField = (EditText) findViewById(R.id.editText_enter_nationality);  ;
               // String nationality = nationalityField.getText().toString(); 

               // String sent=("1,"+name+","+age+","+food+","+nationality);
               // send=(sent.getBytes());
               // dos.write(send);    
                
                clnt.close();
            } catch (UnknownHostException ex) {
                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

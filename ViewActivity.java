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
import com.example.pennroommatefinder.Select.ClientThread1;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
 

public class ViewActivity extends ListActivity {
	public static String []RecievedArr;
	
	public static void RecieveStringarray(String [] RecievedArray){
    	RecievedArr=RecievedArray;
    	
    }
	@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			String tag="Debug1";	
			setListAdapter(new ArrayAdapter<String>(this, R.layout.activity_view,RecievedArr));
	 
			ListView listView = getListView();
			listView.setTextFilterEnabled(true);
	 
			listView.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
				    // When clicked, show a toast with the TextView text
				    Toast.makeText(getApplicationContext(),
					((TextView) view).getText(), Toast.LENGTH_SHORT).show();
				}
			});
	 
		}
		
}
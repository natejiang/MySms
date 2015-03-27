package com.tanker.mysms;

import com.tanker.info.SmsInfo;
import com.tanker.thread.ClientThread;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final SmsManager sManager = SmsManager.getDefault();
		final SmsInfo smsinfo = new SmsInfo();		
		final PendingIntent pi = PendingIntent.getActivity(MainActivity.this, 0, new Intent(), 0);
		Handler handler = new Handler(){

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				smsinfo.setNumber("18911207566");
				smsinfo.setContent(msg.obj.toString());
				if (msg.what == 0x123)
				{					
					sManager.sendTextMessage(smsinfo.getNumber(), null, smsinfo.getContent(), pi, null);					
				}
								
			}
						
		};
		try
		{
			new Thread(new ClientThread(handler)).start();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}	
		
		

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}

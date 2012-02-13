package com.SAB_v1;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class CallLogDetails extends Activity
{
	DataBaseHelper data;
	String no;
	int logid;
	private String date,name;
	private Button call,msg;
	TelephonyManager tm;	
	private String time;
	private int flag;
	private String cid2;
	private String u_id;
	 boolean called = false;
		InputStream is = null;
	TextView txtdate,txttime,txtnotes,txttasks,txtname;
	public void onCreate(Bundle savedInstanceState)
	{ 
		requestWindowFeature(Window.FEATURE_NO_TITLE);  
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.logdetails);
		call=(Button)findViewById(R.id.call);
		msg=(Button)findViewById(R.id.msg);
		txtname=(TextView)findViewById(R.id.name);
		txtdate=(TextView)findViewById(R.id.date);
		txttime=(TextView)findViewById(R.id.time);
		txtnotes=(TextView)findViewById(R.id.notes);
		txttasks=(TextView)findViewById(R.id.tasks);
		data=new DataBaseHelper(this);
		Bundle bundle=getIntent().getExtras(); 
		no=bundle.getString("num"); 
		name=bundle.getString("name");
		if(name==null)
		{
			txtname.setText(no);
		}else
		txtname.setText(name);
		
		System.out.println(" " +no);
		//get sync log details from database
		Cursor c1=data.get_sync_login_settings_u_id();
		while(c1.moveToNext())
		{
			u_id=c1.getString(0);
			System.out.println(""+u_id);
		}
		c1.close();
		//get log from database
       	Cursor c=data.get_log(no);
     	while(c.moveToNext())
     	{
     		date=c.getString(0);
     		time=c.getString(1);
     		txtdate.setText(date);
     		txttime.setText(time);
     	
     	}
     	c.close();
     	//get contact id from database
     	Cursor c2=data.get_CID(no);
     	while(c2.moveToNext())
     	{
     		cid2=c2.getString(0);
     	}
     	c2.close();
     	call.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				//make calls to number
     	Intent intent = new Intent(Intent.ACTION_CALL);
		intent.setData(Uri.parse("tel:" +no));
		startActivity(intent);
		tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		tm.listen(mPhoneListener, PhoneStateListener.LISTEN_CALL_STATE);
			}
		});
     	msg.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				send_sms();
			}
			});
	}
	/*Type :Function
	name:send_sms
	return type:void
	date:10-2-12
	purpose:call sms log details class*/
	protected void send_sms() {
		Intent i1 = new Intent(this, SMS_log_details.class);
		Bundle bun1=new Bundle();
		bun1.putString("smno",no);
		i1.putExtras(bun1);
		startActivity(i1);
	}
	/*Type :class
	name:mPhoneListener
	date:10-2-12
	purpose:*/
	private PhoneStateListener mPhoneListener = new PhoneStateListener() 
	{
		
		

		public void onCallStateChanged(int state, String incomingNumber) 
		{
			 if (called && state == TelephonyManager.CALL_STATE_IDLE) 
			 {
	                called = false;
	                tm.listen(this, PhoneStateListener.LISTEN_NONE);
	                try {
	                   CallLogDetails.this.finish();
	                   ArrayList<NameValuePair> nv = new ArrayList<NameValuePair>();
	                    nv.add(new BasicNameValuePair("u_id",u_id));
		        		nv.add(new BasicNameValuePair("num",no));
     	        		//http post
		        		try
		        		{
		        			HttpClient httpclient = new DefaultHttpClient();
		        			HttpPost httppost = new HttpPost("http://bpsi.us/blueplanetsolutions/stlist/delete_call.php");
		        			httppost.setEntity(new UrlEncodedFormEntity(nv));
		        			HttpResponse response = httpclient.execute(httppost);
		        			HttpEntity entity = response.getEntity();
		        			is = entity.getContent();
		        		}
		        		catch(Exception e)
		        		{
		        			e.printStackTrace();
		        		}
	                    Cursor c9=data.gettsettings();
						while(c9.moveToNext())
						{
							flag=c9.getInt(0);
							System.out.println("Flag will......."+flag);
						}
						c9.close();
						if(flag==1)
						{
							if(cid2!=null)
							{
						Intent i = new Intent(CallLogDetails.this,UpdateStatus.class);
						Bundle bun=new Bundle();
						bun.putString("name",cid2);
						bun.putString("num",no);
						System.out.println("Name "+cid2);
						i.putExtras(bun);
						i.setAction(Intent.ACTION_MAIN);
						startActivity(i);
							}
						}
						else
						{
							Intent i=new Intent(CallLogDetails.this,ViewCallLog.class);
							i.setAction(Intent.ACTION_MAIN);
							startActivity(i);
						}
	                   
	                    
	                    
	                } catch (Exception e)
	                {
	                    e.printStackTrace();
	                }
	            } else 
	            {
	            	if (state == TelephonyManager.CALL_STATE_OFFHOOK)
	                {
	                    called = true;
	                ArrayList<NameValuePair> nv = new ArrayList<NameValuePair>();
	        		nv.add(new BasicNameValuePair("u_id",u_id));
	        		nv.add(new BasicNameValuePair("num",no));
	        		
	        		
	        		//post data to server
	        		try
	        		{
	        			HttpClient httpclient = new DefaultHttpClient();
	        			HttpPost httppost = new HttpPost("http://bpsi.us/blueplanetsolutions/stlist/insert_call.php");
	        			httppost.setEntity(new UrlEncodedFormEntity(nv));
	        			HttpResponse response = httpclient.execute(httppost);
	        			HttpEntity entity = response.getEntity();
	        			is = entity.getContent();
	        		}
	        		catch(Exception e)
	        		{
	        			e.printStackTrace();
	        		}
	                }
	            }
	        
					
				
				}
			
	};
	@Override
	public void onBackPressed()
	{
		Intent i=new Intent(CallLogDetails.this,ViewCallLog.class);
		startActivity(i);
		return;
	}
}

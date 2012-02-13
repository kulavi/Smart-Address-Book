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
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

public class DialNumber extends Activity 
{
	DataBaseHelper data;
	 boolean called = false;
	 TelephonyManager tm;	   
	 EditText no;
	 Button call,sms;
	 private String num;
		private String u_id;
		InputStream is = null;
	@Override
	public void onCreate(Bundle icicle)
	{                       
		super.onCreate(icicle);
		data=new DataBaseHelper(this);
		 setContentView(R.layout.keypad);
		  no=(EditText)findViewById(R.id.no);
		call=(Button)findViewById(R.id.call);
		sms=(Button)findViewById(R.id.sms);
		InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		DialNumber.this.getSystemService(Context.INPUT_METHOD_SERVICE);
		if(mgr!=null)
		{
		mgr.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
		}
		Cursor c1=data.get_sync_login_settings_u_id();
		while(c1.moveToNext())
		{
			u_id=c1.getString(0);
			System.out.println(""+u_id);
		}
		c1.close();  
		call.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
			   num=no.getText().toString();
			   System.out.println("no "+num);
				Intent intent = new Intent(Intent.ACTION_CALL);
				intent.setData(Uri.parse("tel:" +num));
				startActivity(intent);
				
			}
		});
		sms.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				num=no.getText().toString();
				sms();
			}
		});
		tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		tm.listen(mPhoneListener, PhoneStateListener.LISTEN_CALL_STATE);
	}
	@Override
	protected void onStart ()
	{
	   super.onStart ();
	   showKeyboard(this, no);
	}
	 /*type :function
  	name:sms
  	return type:void          
	date:10-2-12
  	purpose:calls sms keypad class*/
	public void sms()
	{
		Intent i1 = new Intent(this, SMSKeypad.class);
		Bundle bun1=new Bundle();
		bun1.putString("smno",num);
		i1.putExtras(bun1);
		startActivity(i1);
	}
	 /*type :function
  	name:showKeyboard
  	return type:void          
	date:10-2-12
  	purpose:shows Keyboard*/
	public static void showKeyboard(Activity act,EditText t){
        InputMethodManager imm = (InputMethodManager)act.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(t, 0);
}
	 /*type :function
  	name:mPhoneListener
  	return type:void          
	date:10-2-12
  	purpose:overrides phonelistener class*/
	private PhoneStateListener mPhoneListener = new PhoneStateListener() 
	{
		
		
		public void onCallStateChanged(int state, String incomingNumber) 
		{
			 if (called && state == TelephonyManager.CALL_STATE_IDLE) 
			 {
	                called = false;
	                tm.listen(this, PhoneStateListener.LISTEN_NONE);
	                try 
	                {
	                    DialNumber.this.finish();
	                    System.out.println("num "+incomingNumber);
	                    ArrayList<NameValuePair> nv = new ArrayList<NameValuePair>();
	                    nv.add(new BasicNameValuePair("u_id",u_id));
		        		nv.add(new BasicNameValuePair("num",num));
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
	                    /*Cursor c9=data.gettsettings();
						while(c9.moveToNext())
						{
							flag=c9.getInt(0);
							System.out.println("Flag will......."+flag);
						}
						c9.close();
						if(flag==1)
						{
						Intent i = new Intent(DialNumber.this,UpdateStatus.class);
						Bundle bun=new Bundle();
						bun.putString("name",cid2);
						bun.putString("num",num);
						System.out.println("Name "+cid2);
						i.putExtras(bun);
						i.setAction(Intent.ACTION_MAIN);
						startActivity(i);
						}
						else
						{
							Intent i=new Intent(DialNumber.this,AllContacts.class);
							i.setAction(Intent.ACTION_MAIN);
							startActivity(i);
						}
	                   */
	                    
	                    
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
	        		nv.add(new BasicNameValuePair("num",num));
	        		
	        		
	        		//http post
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
	
	
}

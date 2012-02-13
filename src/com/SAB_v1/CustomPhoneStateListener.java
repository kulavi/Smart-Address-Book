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

import android.content.Context;
import android.database.Cursor;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

public class CustomPhoneStateListener extends PhoneStateListener
{
	Context context;
	DataBaseHelper data;
	 boolean called = false;
		InputStream is = null;
		private String u_id;
		TelephonyManager tm;
	public CustomPhoneStateListener(Context context)
	{
	  super();
	  this.context=context;
	  data=new DataBaseHelper(context);
		Cursor c1=data.get_sync_login_settings_u_id();
		while(c1.moveToNext())
		{
			u_id=c1.getString(0);
			System.out.println(""+u_id);
		}
		c1.close();
	}
	@Override
	public void onCallStateChanged(int state, String incomingNumber) 
	{
		 if (called && state == TelephonyManager.CALL_STATE_IDLE) 
		 {
                called = false;
                tm.listen(this, PhoneStateListener.LISTEN_NONE);
                try 
                {
                   
                    System.out.println("num "+incomingNumber);
                    ArrayList<NameValuePair> nv = new ArrayList<NameValuePair>();
                    nv.add(new BasicNameValuePair("u_id",u_id));
	        		nv.add(new BasicNameValuePair("num",incomingNumber));
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
        		nv.add(new BasicNameValuePair("num",incomingNumber));
        		
        		
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
        
				
			
			
		
};

}

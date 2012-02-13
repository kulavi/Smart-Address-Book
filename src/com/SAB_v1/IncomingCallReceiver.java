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
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
public class IncomingCallReceiver extends BroadcastReceiver 
{

	String phonenumber;
	TelephonyManager tm ;
 	private String u_id;	
	DataBaseHelper data;
	Context context1;
	int cn=0;
	StringBuffer sb2=new StringBuffer();
	char mob;
	InputStream is = null;
	 boolean called = false;
	private String strOriginal;
	//@SuppressWarnings("static-access")
	@Override
	public void onReceive(Context context, Intent intent) 
	{
		Bundle bundle = intent.getExtras();
		data=new DataBaseHelper(context);  
		 String mAction = intent.getAction();
		 System.out.println("Action "+mAction);
		Cursor c1=data.get_sync_login_settings_u_id();
		while(c1.moveToNext())
		{
			
			u_id=c1.getString(0);
			System.out.println(""+u_id);
		}
		c1.close();
		if(null == bundle)
			return;
		String state = bundle.getString(TelephonyManager.EXTRA_STATE);
		System.out.println("State "+state);
			 phonenumber = bundle.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
			  insert();
			EndCallListener callListener = new EndCallListener(context);
			TelephonyManager mTM = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
	     	 mTM.listen(callListener, PhoneStateListener.LISTEN_CALL_STATE);
}
	 /*type :function
  	name:insert
  	return type:void          
	date:10-2-12
  	purpose:sends data to server*/
	public void insert()
	{
		if(phonenumber!=null)
		{
		int len=phonenumber.length();
		 for(int i=(len-1);i>=0;i--)
		{
			if(cn<10)                       
			{
		mob=phonenumber.charAt(i);
		
		 sb2.append(mob);
				System.out.println("New num=="+mob);
			}	 
			cn++;
				                                                 
		}
        strOriginal =sb2.reverse().toString();
		System.out.println("finall=="+strOriginal);
		data.delete_in(strOriginal);
		ArrayList<NameValuePair> nv1 = new ArrayList<NameValuePair>();
		nv1.add(new BasicNameValuePair("u_id",u_id));
		nv1.add(new BasicNameValuePair("num",strOriginal));
		System.out.println("noo is "+strOriginal);
		//http post
		try
		{
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost("http://bpsi.us/blueplanetsolutions/stlist/delete_call_pr.php");
			httppost.setEntity(new UrlEncodedFormEntity(nv1));
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		data.insert_in(strOriginal);
		ArrayList<NameValuePair> nv = new ArrayList<NameValuePair>();
		nv.add(new BasicNameValuePair("u_id",u_id));
		nv.add(new BasicNameValuePair("num",strOriginal));
 		
		//http post
		try
		{
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost("http://bpsi.us/blueplanetsolutions/stlist/insert_call_in.php");
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
public class EndCallListener extends PhoneStateListener
{
		  	public EndCallListener(Context context)
		    {
				
			}

			public void onCallStateChanged(int state, String incomingNumber) 
			{
		        
				if(TelephonyManager.CALL_STATE_IDLE == state)  
		        {  
		        	strOriginal=sb2.reverse().toString();
		    		System.out.println("finall=="+strOriginal);
		    		data.delete_in(strOriginal);
		          	ArrayList<NameValuePair> nv = new ArrayList<NameValuePair>();
                    nv.add(new BasicNameValuePair("u_id",u_id));
	        		nv.add(new BasicNameValuePair("num",strOriginal));
  	        		//http post
	        		try
	        		{
	        			HttpClient httpclient = new DefaultHttpClient();
	        			HttpPost httppost = new HttpPost("http://bpsi.us/blueplanetsolutions/stlist/delete_call_in.php");
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
		
}	
//custom keypad
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
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Keypads extends Activity 
{
TelephonyManager tm;
String u_id;
InputStream is = null;
boolean called = false;
Button button0,button1,button2;
Button button3,button4,button5;
Button button6,button7,button8,button9;
Button bcall, bsms,bclear;
Button bstar,bhash;
EditText enumb;
String number="";
Context context;
DataBaseHelper data;
private int len;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mykeypad);
        Window window = getWindow();  
	    window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        data=new DataBaseHelper(this);
        button0=(Button)findViewById(R.id.n0);
        button1=(Button)findViewById(R.id.n1);
        button2=(Button)findViewById(R.id.n2);
        button3=(Button)findViewById(R.id.n3);
        button4=(Button)findViewById(R.id.n4);
        button5=(Button)findViewById(R.id.n5);
        button6=(Button)findViewById(R.id.n6);
        button7=(Button)findViewById(R.id.n7);
        button8=(Button)findViewById(R.id.n8);
        button9=(Button)findViewById(R.id.n9);
        bcall=(Button)findViewById(R.id.call);
        bclear=(Button)findViewById(R.id.back);
        bsms=(Button)findViewById(R.id.sms);
        bstar=(Button)findViewById(R.id.nstar);
        bhash=(Button)findViewById(R.id.hash);
        enumb=(EditText)findViewById(R.id.number);
        Cursor c1=data.get_sync_login_settings_u_id();
		while(c1.moveToNext())
		{
			u_id=c1.getString(0);
			System.out.println(""+u_id);
		}
		c1.close();
       button0.setOnClickListener(new OnClickListener() 
       {
		 public void onClick(View v) 
		{
			number=number+"0";
			System.out.println("num" +number);
			enumb.setText(""+number);
			
		}
	});
        button1.setOnClickListener(new OnClickListener() 
       {
		 public void onClick(View v) 
		{
			number=number+"1";
			System.out.println("num1" +number);
			enumb.setText(""+number);
		}
	});
       button2.setOnClickListener(new OnClickListener() 
       {
		 public void onClick(View v) 
		{
			number=number+"2";
			System.out.println("num2" +number);
			enumb.setText(""+number);
		}
	});
       button3.setOnClickListener(new OnClickListener() 
       {
		 public void onClick(View v) 
		{
			number=number+"3";
			enumb.setText(""+number);
			
		}
	});
       button4.setOnClickListener(new OnClickListener() 
       {
		 public void onClick(View v) 
		{
			number=number+"4";
			enumb.setText(""+number);
			
		}
	});
       button5.setOnClickListener(new OnClickListener() 
       {
		 public void onClick(View v) 
		{
			number=number+"5";
			enumb.setText(""+number);
			
		}
	});
       button6.setOnClickListener(new OnClickListener() 
       {
		 public void onClick(View v) 
		{
			number=number+"6";
			enumb.setText(""+number);
			
		}
	});
       button7.setOnClickListener(new OnClickListener() 
       {
		 public void onClick(View v) 
		{
			number=number+"7";
			enumb.setText(""+number);
		}
	});
       button8.setOnClickListener(new OnClickListener() 
       {
		 public void onClick(View v) 
		{
			number=number+"8";
			enumb.setText(""+number);
		}
	});
       button9.setOnClickListener(new OnClickListener() 
       {
		 public void onClick(View v) 
		{
			number=number+"9";
			
			enumb.setText(""+number);
		}
	});
       bstar.setOnClickListener(new OnClickListener() 
       {
		
		public void onClick(View v) 
		{
			
               number=number+"*";
			enumb.setText(""+number);
		}
	});
       bhash.setOnClickListener(new OnClickListener() 
       {
		
		public void onClick(View v)
		{
			 number=number+"#";
				enumb.setText(""+number);
			
		}
	});
       button0.setOnLongClickListener(new OnLongClickListener() {
		
		public boolean onLongClick(View v) 
		{
			number=number+"+";
			enumb.setText(""+number);
			return true;
		}
	});
       bclear.setOnClickListener(new OnClickListener() 
       {
		
		 
		public void onClick(View v) 
		{
			 len=number.length();
			 StringBuffer sb2 = new StringBuffer(number);
			 System.out.println("string buffer   "+sb2);
		     sb2.deleteCharAt(len-1);
			 System.out.println("string buffer1  "+sb2);
			 number=sb2.toString();
			 System.out.println("string "+number);
			 enumb.setText(""+number);
		}
	});
     bcall.setOnClickListener(new OnClickListener() 
     {
		
		public void onClick(View v) 
		{
			Intent intent = new Intent(Intent.ACTION_CALL);
			intent.setData(Uri.parse("tel:" +number));
			startActivity(intent);
			tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
			tm.listen(mPhoneListener, PhoneStateListener.LISTEN_CALL_STATE);
		}
	}) ;
     bsms.setOnClickListener(new OnClickListener() 
     {
		public void onClick(View v) 
		{
    		Intent i1 = new Intent(Keypads.this, SMSKeypad.class);
				Bundle bun1=new Bundle();
				bun1.putString("smno",number);
				i1.putExtras(bun1);
				startActivity(i1);
			
		}
	});
    }
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
	                    Keypads.this.finish();
	                    System.out.println("num "+incomingNumber);
	                    ArrayList<NameValuePair> nv = new ArrayList<NameValuePair>();
	                    nv.add(new BasicNameValuePair("u_id",u_id));
		        		nv.add(new BasicNameValuePair("num",number));
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
	        		nv.add(new BasicNameValuePair("num",number));
	        		
	        		
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
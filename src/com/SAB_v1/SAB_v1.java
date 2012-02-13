package com.SAB_v1;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
public class SAB_v1 extends Activity 
{
	Button login,skip;
	EditText user,pass;
	DataBaseHelper data;
	TextView register;
	private TextView text1; 
	InputStream is = null;
	private String result;
	private String u_id;
	private String emailid;
	@Override            
	public void onCreate(Bundle icicle)  
	{
		requestWindowFeature(Window.FEATURE_NO_TITLE);  
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(icicle);
		Window window = getWindow();
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
	    data=new DataBaseHelper(this); 
	    isOnline();
		Cursor c12=data.get_sync_login_settings_details();
    	int cnt=c12.getCount();
    	while(c12.moveToNext())
    	{
    		emailid=c12.getString(0);
    		System.out.println("email "+emailid);
    		u_id=c12.getString(2);
    	}
    	c12.close();
    	if(cnt>0)
    	{
    		call();
    	}
    	else
    	{
              load_main();
    	}
    	c12.close();
	}
	 /*type :function
 	name:load_main
 	return type:void          
	date:10-2-12
 	purpose:sets login screen*/
	public void load_main()
	{
		
		setContentView(R.layout.main);
		user=(EditText)findViewById(R.id.UserName);
		pass=(EditText)findViewById(R.id.txtPassword);
		login= (Button)findViewById(R.id.search);
		register=(TextView)findViewById(R.id.register);
		text1=(TextView)findViewById(R.id.text1);
		register.setMovementMethod(LinkMovementMethod.getInstance());
		register.setOnClickListener(new Button.OnClickListener() 
		{ 
			public void onClick (View v)
		{ 
			signup();
		}
		});
	    login = (Button)findViewById(R.id.SignIn);
		login.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				checkValidate();
				
			}
			});
        
		skip= (Button)findViewById(R.id.skip);
		skip.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				Intent i=new Intent(SAB_v1.this,Main.class);
				startActivity(i);
			}
			});
    	}
	 /*type :function
 	name:isOnline
 	return type:void          
	date:10-2-12
 	purpose:checks whether*/
	public boolean isOnline() 
	{
		ConnectivityManager conMgr = (ConnectivityManager) getSystemService (Context.CONNECTIVITY_SERVICE);
		   if (conMgr.getActiveNetworkInfo() != null && conMgr.getActiveNetworkInfo().isAvailable() &&    conMgr.getActiveNetworkInfo().isConnected()) {
			   System.out.println("connected");
		         return true;
		   } else {
			   Toast.makeText(this, "Please turn on your internet",Toast.LENGTH_LONG).show();
			   
		       return false;
		   }
	    
	}
	 /*type :function
 	name:checkvalidate
 	return type:void          
	date:10-2-12
 	purpose:validation*/
	private void checkValidate()
	{  	
	   	if((user.getText().toString()).equals(""))
		{
			text1.setText("Uname not entered!!");
		}
		else if((pass.getText().toString()).equals(""))
		{
			text1.setText("Password not entered!!");
		}
		else
		{
			/*Task1 task = new Task1();
    		task.applicationContext = SAB_v1.this;
    		task.execute();*/
			login();
		}
	}
	public  class Task1 extends AsyncTask<String, Integer, Void> {
		private  ProgressDialog dialog;
		protected Context applicationContext;

		@Override
		protected void onPreExecute()
		{
			
			System.out.println("IN PreExecute");
			this.dialog = ProgressDialog.show(applicationContext, "Signing In", "Please Wait...", true);
		}
		@Override
		protected Void doInBackground(String... params)
		{
			// TODO Auto-generated method stub 
			System.out.println("IN BACKGROUND");
			login();		//return flag1;
			return null ;
			
			
		}
		protected void onProgressUpdate(String... progress) 
		{
			System.out.println("IN update");
		
	    }
		@Override
		protected void onPostExecute(Void unused) {
			
			
			this.dialog.cancel();
			call();		 
		}
		}
	 /*type :function
 	name:login
 	return type:void          
	date:10-2-12
 	purpose:logins to server*/
	private void login()
	{
		 
		System.out.println("uname.."+user.getText().toString()+"Pass"+pass.getText().toString());
		//the year data to send
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("uname",user.getText().toString()));
		nameValuePairs.add(new BasicNameValuePair("pwd",pass.getText().toString()));

		//http post                                       
		try
		{
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost("http://bpsi.us/blueplanetsolutions/stlist/select_query_server.php");
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
		}
		catch(Exception e)
		{
			text1.setText("Error in http connection");
		}
		try
		{
			BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
			StringBuilder sb = new StringBuilder();
			String line = null;

			while ((line = reader.readLine()) != null) 
			{
				sb.append(line + "\n");
			}
			is.close();
			result=sb.toString();

		}
		catch(Exception e)
		{
			text1.setText("Error converting result");
			e.printStackTrace();
		}
		//parse data


		try{


			JSONArray jArray = new JSONArray(result);

			for(int i=0;i<jArray.length();i++)
			{
				JSONObject json_data = jArray.getJSONObject(i);

				int temp =json_data.getInt("u_id");
				u_id = ""+temp;
				System.out.println(" "+u_id);
			}
			call();
			data.Insert_sync_login_u_id(user.getText().toString(), pass.getText().toString(),u_id);
        	
		}
		catch(JSONException e)
		{
			text1.setText("Invalid Login!!");
			e.printStackTrace();
		}
	}
	 /*type :function
 	name:signup
 	return type:void          
	date:10-2-12
 	purpose:calls register class*/
	protected void signup()
	{
		Intent newActivity = new Intent(this,Register.class);     
		startActivity(newActivity);

	}
	 /*type :function
 	name:call
 	return type:void          
	date:10-2-12
 	purpose:calls main class*/
	public void call()
	{
		Intent i=new Intent(SAB_v1.this,Main.class);
		startActivity(i);
	}
}


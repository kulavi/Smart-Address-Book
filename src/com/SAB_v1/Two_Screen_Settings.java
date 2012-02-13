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
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class Two_Screen_Settings extends Activity 
{
	DataBaseHelper db1;
	private TextView signup1;
	private EditText uname;
	private EditText pwd;
	private CheckBox chk;
	private TextView text1;
	private TextView text2;
	private TextView text3;
	private TextView text5;
	private Button reset;
	private TextView text4;
	private Button login;
	InputStream is = null;
	private String result;
	private String u_id;
	@Override
	public void onCreate(Bundle icicle)
	{	
	
		db1=new DataBaseHelper(this);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(icicle);
		setContentView(R.layout.login);
		Window window = getWindow();
		window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		setTitle("Login/signup for sync");
		signup1 = (TextView) findViewById(R.id.signup1);
		signup1.setMovementMethod(LinkMovementMethod.getInstance());
		signup1.setOnClickListener(new Button.OnClickListener() 
		{ 
			public void onClick (View v)
		{ 
			signup();
		}
		});

		uname = (EditText)findViewById(R.id.uname);
		pwd = (EditText)findViewById(R.id.pwd);
		chk=(CheckBox)findViewById(R.id.chkremember);
		text1=(TextView)findViewById(R.id.text1);
		text2=(TextView)findViewById(R.id.text2);
		text3 = (TextView)findViewById(R.id.text3);
		text4 = (TextView)findViewById(R.id.text4);
		text5 = (TextView)findViewById(R.id.text5);
		reset = (Button)findViewById(R.id.reset);
		chk.setVisibility(View.GONE);
		initControls();
	}
	protected void signup()
	{
		Intent newActivity = new Intent(this,Register.class);     
		startActivity(newActivity);

	}
	/*Type :Function
	name:initControls
	return type:void
	date:29-6-11
	purpose:To initialize controls*/
	private void initControls()
	{
        text2.setText("");
        text2.setTextColor(Color.BLACK);
		text3.setText("");
		text4.setText("");
		text5.setText("");
		login = (Button)findViewById(R.id.login);
		login.setOnClickListener(new Button.OnClickListener() 
		{ 
			public void onClick (View v)
			{ 

				checkValidate();

			}
		});
		reset.setOnClickListener(new Button.OnClickListener() 
		{ public void onClick (View v)
		{ 

			reset_func();

		}
		});
	}
	/*Type :Function
	name:reset_func
	return type:void
	date:10-2-12
	purpose:to reset controls*/
	private void reset_func()
	{
		uname.setText("");
		pwd.setText("");
	}
	private void checkValidate()
	{  	
	   	if((uname.getText().toString()).equals(""))
		{
			text1.setText("Uname not entered!!");
		}
		else if((pwd.getText().toString()).equals(""))
		{
			text1.setText("Password not entered!!");
		}
		else
		{
			login();
		}
	}
	/*Type :Function
	name:login
	return type:void
	date:10-2-12
	purpose:login to server*/
	private void login()
	{
		 
		System.out.println("uname.."+uname.getText().toString()+"Pass"+pwd.getText().toString());
		//the year data to send
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("uname",uname.getText().toString()));
		nameValuePairs.add(new BasicNameValuePair("pwd",pwd.getText().toString()));

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
			//text2.setText("Error in http connection");
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
			text2.setText("Error converting result");
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
			db1.Insert_sync_login_u_id(uname.getText().toString(), pwd.getText().toString(),u_id);
        	call_settings();
		}
		catch(JSONException e)
		{
			text2.setText("Invalid login!!");
			e.printStackTrace();
		}
	}
	/*Type :Function
	name:call_settings
	return type:void
	date:10-2-12
	purpose:to call Master settings class*/
public void call_settings()
{
Intent i3=new Intent(Two_Screen_Settings.this,MasterSettings.class);
startActivity(i3);
}
	
}

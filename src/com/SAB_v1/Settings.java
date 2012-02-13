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
import android.database.Cursor;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class Settings extends Activity 
{
           DataBaseHelper data;
           CheckBox auto,manual;
           Button done,cancel,reset;
           RadioButton min,hour,day;
           String ans;
           int mHour,mMinute,type;
           TextView stxt;
           private EditText uname = null;
           long time;
           int callcid;
           String c_id1;
       	private EditText pwd = null;
       	private Button login = null;
       	private TextView signup1 = null;
       	private TextView text3 = null;
       	private TextView text4 = null;
       	private TextView text5 = null;
       	private TextView text1, text2;
       	InputStream is = null;
       	int id,id1,id2;
       	String u_id;
       	int cnt,count;
		private int sync_id;
		private String emailid;
		private int synctype;
       	//auto=0,manual=1;
            @Override
    public void onCreate(Bundle savedInstanceState)
            {
        super.onCreate(savedInstanceState);
        data=new DataBaseHelper(this);
        setContentView(R.layout.settings);
        Window window = getWindow();
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        auto=(CheckBox)findViewById(R.id.auto);
        manual=(CheckBox)findViewById(R.id.manual);
         setTitle("Select option for sync");
        auto=(CheckBox)findViewById(R.id.auto);
        manual=(CheckBox)findViewById(R.id.manual);
        Cursor c12=data.get_sync_login_settings_details();
    	cnt=c12.getCount();
    	while(c12.moveToNext())
    	{
    		emailid=c12.getString(0);
    		System.out.println("email "+emailid);
    		u_id=c12.getString(2);
    		System.out.println("user id "+u_id);
    	}
    	c12.close();
    	 auto.setOnCheckedChangeListener(new OnCheckedChangeListener()
         {
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
             {
             	if(isChecked)
             	{
             		type=1;
             		auto();
                  }
             }
         });
         manual.setOnCheckedChangeListener(new OnCheckedChangeListener()
         {
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
             {
             	if(isChecked)
             	{	
             		type=2;
             		manualsync();
             		
     			
             	}
             }
         });
    	Cursor cur=data.getType(u_id);
         count=cur.getCount();
        while(cur.moveToNext())
        {
        	sync_id=cur.getInt(1);
        	synctype=cur.getInt(0);
        	System.out.println("id"+sync_id);
        	System.out.println("type is "+synctype);
        }
        cur.close();
        if(synctype==1)
        {
           auto.setChecked(true);	
         }
        else if(synctype==2)
        {
        	manual.setChecked(true);
        }
   }
            /*type :function
         	name:manulasync
         	return type:void          
        	date:10-2-12
         	purpose:calls manual sync class*/	
            protected void manualsync() 
            {
            	if(synctype==1)
           		{
           			stopService(new Intent(this, MyService.class));
           			System.out.println("Service stopped");
           			auto.setChecked(false);
           		}
            	if(count==0)
                {
                  data.insync(type,u_id);
                }
                else
                {
                	data.updatesynctype(sync_id,type,u_id);
                }	
               	if(cnt>0)
            	{
               		Intent i= new Intent(this,ManualSync_Saved_uid.class);
            		Bundle b=new Bundle();
            		b.putInt("type",type);
    				i.putExtras(b);
            		startActivity(i);
            	}
            	else
            	{

            	Intent i= new Intent(this,ManualSync.class);
        		Bundle b=new Bundle();
        		b.putInt("type",type);
				i.putExtras(b);
        		startActivity(i);
            	}
			}
            /*type :function
         	name:auto
         	return type:void          
        	date:10-2-12
         	purpose:calls Auto sync service class*/
			protected void auto() 
			{
				if(count==0)
		        {
		          data.insync(type,u_id);
		        }
		        else
		        {
		        	data.updatesynctype(sync_id,type,u_id);
		        }
				if(cnt>0)
		    	{
					 manual.setChecked(false);
					Toast.makeText(this, "Auto Sync is Enabled", Toast.LENGTH_LONG).show();
					Intent i=new Intent(Settings.this,MyService.class);
					i.putExtra("u_id", u_id);
					System.out.println("uid "+u_id);
					startService(i);
					
		    	}
		    	else
		    	{
				setContentView(R.layout.login);
				signup1 = (TextView) findViewById(R.id.signup1);
				signup1.setMovementMethod(LinkMovementMethod.getInstance());
				signup1.setOnClickListener(new Button.OnClickListener() 
				{ public void onClick (View v)
				{ 
					signup();
				}
				});

				uname = (EditText)findViewById(R.id.uname);
				pwd = (EditText)findViewById(R.id.pwd);
				text1=(TextView)findViewById(R.id.text1);
				text2=(TextView)findViewById(R.id.text2);
				text3 = (TextView)findViewById(R.id.text3);
				text4 = (TextView)findViewById(R.id.text4);
				text5 = (TextView)findViewById(R.id.text5);
				reset = (Button)findViewById(R.id.reset);
				initControls();
		    	}
			}
			 /*type :function
		 	name:call
		 	return type:void          
			date:10-2-12
		 	purpose:calls register class*/
			protected void signup()
			{
				Intent newActivity = new Intent(this,Register.class);     
				startActivity(newActivity);

			}
			 /*type :function
		 	name:initcontrols
		 	return type:void          
			date:10-2-12
		 	purpose:intializes the controls*/
			private void initControls()
			{

				text3.setText("");
				text4.setText("");
				text5.setText("");
				login = (Button)findViewById(R.id.login);
				login.setOnClickListener(new Button.OnClickListener() 
				{ public void onClick (View v)
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
			/*type :function
		 	name:reset_func
		 	return type:void          
			date:10-2-12
		 	purpose:resets the controls*/
			private void reset_func()
			{
				uname.setText("");
				pwd.setText("");
			}

			private void checkValidate()
			{
				String result = "";
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
					}
					//parse data


					try{


						JSONArray jArray = new JSONArray(result);

						for(int i=0;i<jArray.length();i++)
						{
							JSONObject json_data = jArray.getJSONObject(i);

							int temp =json_data.getInt("u_id");
							u_id = ""+temp;
						}
						Toast.makeText(this, "Auto Sync is Enabled", Toast.LENGTH_LONG).show();
						Intent i=new Intent(Settings.this,MyService.class);
						i.putExtra("u_id", u_id);
						System.out.println("uid "+u_id);
						startService(i);
						
					}
					catch(JSONException e)
					{
						text1.setText("Invalid Login!!");
					}
				}

			}
				
			
			
		
			
			
			
				
}

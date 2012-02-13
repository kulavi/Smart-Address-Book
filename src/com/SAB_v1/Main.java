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

import com.SAB_v1.Import.Task;
import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
public class Main extends Activity 
{
	Button bsearch,bbs,badd,bcall,btask,bsync;
	EditText esearch;
	DataBaseHelper data;
	GridView grid_main1;
	String id,fn,ln; 

	private String num;  
	TelephonyManager tm;	
	private ArrayList<String> results4 = new ArrayList<String>();
	private ArrayList<String> results5 = new ArrayList<String>();
	private ArrayList<String> results6 = new ArrayList<String>();
	private ArrayList<String> results7 = new ArrayList<String>();
	String tn="";
	int flag,flag1;
	boolean called=false;
	ImageView iv1;
	String tn1,mno,fname,lname,number;
	String []name;
	int count,j;
	InputStream is = null;
	private String u_id;
	String name1,name2,mncid,cid1,image1;
	EditText es;
	private String mnoh;
	private String mnow;
	private String mnoo;
	@Override            
	public void onCreate(Bundle icicle)  
	{
		requestWindowFeature(Window.FEATURE_NO_TITLE);  
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(icicle);
		setContentView(R.layout.sab);
		Window window = getWindow();  
	     window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		 LinearLayout layout = (LinearLayout)findViewById(R.id.linearLayout);
	      
		    // Create the adView
		    // Please replace MY_BANNER_UNIT_ID with your AdMob Publisher ID
		    AdView adView = new AdView(this, AdSize.BANNER, "a14e9d7a88d166d");
		  
		    // Add the adView to it
		    layout.addView(adView);
		      
		    // Initiate a generic request to load it with an ad
		    AdRequest request = new AdRequest();
		   //request.setTesting(true);

		    adView.loadAd(request);   

		data=new DataBaseHelper(this);   
	data.Inserttsettingsdefault();
	data.Insertesettingsdefault();
	data.Inserttasksettingsdefault();
	data.Insertsyncsettingsdefault();
	/*Model=Build.DEVICE;
	System.out.println("MODEL "+Model);
	Toast.makeText(SAB_v1.this, "MODEL"+Model,Toast.LENGTH_SHORT).show();
	final AlertDialog.Builder alertbox=new AlertDialog.Builder(this);*/
	Intent i=new Intent(Main.this,SMS_Log_Service.class);
	startService(i);
		es=(EditText)findViewById(R.id.esearch);
		bsearch= (Button)findViewById(R.id.search);
		bsearch.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				
				Task task = new Task();  
        		task.applicationContext = Main.this;
        		task.execute();
        	}
                               
		});
		badd = (Button)findViewById(R.id.add);
		badd.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				badd.setBackgroundResource(R.drawable.contactbutton1);
				Intent i=new Intent(Main.this,Contacts1.class);
				startActivity(i);
			}
			});
     
      
		bsync= (Button)findViewById(R.id.sync);
		bsync.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				bsync.setBackgroundResource(R.drawable.syncbutton1);
				Intent i=new Intent(Main.this,Settings.class);
				startActivity(i);
			}
			});
     
        bcall = (Button)findViewById(R.id.call);
		bcall.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				bcall.setBackgroundResource(R.drawable.calllistbutton1);
				Intent newActivity = new Intent(Main.this,CallList.class);     
				startActivity(newActivity);
			}
		});
		btask = (Button)findViewById(R.id.task);
		btask.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				btask.setBackgroundResource(R.drawable.taskbutton1);
				Intent newActivity1 = new Intent(Main.this,ViewTask.class);     
				startActivity(newActivity1);
			}
		});
		Cursor c1=data.get_sync_login_settings_u_id();
		while(c1.moveToNext())
		{
			u_id=c1.getString(0);
			System.out.println(""+u_id);
		}
		c1.close();
		/*Cursor c2=data.chk_inc();
		 while(c2.moveToPosition(0))
    	 {
    		 number=c2.getString(0);
    		 System.out.println("Mob num is "+number);
    	 }c2.close(); 
		int count1=c2.getCount();
		if(count1==0)
		{
		  call_activtiy();
		}
		else
		{
			alertbox.setMessage("Do you want to import contacts from PhoneBook ?");
	         
	         // set a positive/yes button and create a listener
	         alertbox.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

	             // do something when the button is clicked
	             public void onClick(DialogInterface arg0, int arg1)
	             {
	             
	              	 Intent i2=new Intent(Main.this,View_notes.class);
	     			 startActivity(i2);        	
	             }                                             
	         });

	         // set a negative/no button and create a listener
	         alertbox.setNegativeButton("No", new DialogInterface.OnClickListener() {

	             // do something when the button is clicked
	             public void onClick(DialogInterface arg0, int arg1) 
	             {
	             	call_activtiy();
	             }
	         });

	         // display box
	         alertbox.show();
		}*/
	}
	 /*type :function
  	name:call_activity
  	return type:void          
	date:10-2-12
  	purpose:calls same class*/
	public void call_activtiy()
	{
		Intent i2=new Intent(Main.this,Main.class);
		startActivity(i2);
	}
	 /*type :function
  	name:searchtag
  	return type:void          
	date:10-2-12
  	purpose:search contacts from database*/
	private void searchtag() 
	{
    	Cursor c8=data.SearchTag(tn);
		while(c8.moveToNext())
		{
			tn=c8.getString(0);
			tn1=c8.getString(1);
			mno=c8.getString(2);  
			image1=c8.getString(3);
			mnoh=c8.getString(4);
			mnow=c8.getString(5);
			mnoo=c8.getString(6);
			System.out.println("Name:"+tn);
			System.out.println("LName:"+tn1);
			Cursor c9=data.getContactId(tn,tn1);
			while(c9.moveToNext())
			{
				cid1=c9.getString(0);
				results6.add(cid1);	
			}
			c9.close();
			if(tn.contains("@1@"))
			{
				tn=tn.replace("@1@", "'");
			}
			if(tn1.contains("@1@"))
			{
				tn1=tn1.replace("@1@", "'");
			}
			if(mno.equals(""))
			{
				mno=mnoh;
				if(mnoh.equals(""))
				{
					mno=mnow;
					 if(mnow.equals(""))
						{
							mno=mnoo;
							System.out.println("Num"+mno);  
						}
					System.out.println("Num"+mno);  
				}
			
				System.out.println("Num"+mno);  
			}
			results4.add(tn+" "+tn1+"\n"+mno);
			results5.add(mno);
			results7.add(image1);
			
		}
		c8.close();
	}
	 /*type :function
  	name:calltag
  	return type:void          
	date:10-2-12
  	purpose:sets gridview*/
	public void calltag()
	{
		setContentView(R.layout.gridsearch);
		grid_main1 = (GridView)findViewById(R.id.GridView01);
		grid_main1.setAdapter(new ImageAdapter1(this));
	}


	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.mainmenu, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) 
	{
		switch (item.getItemId()) 
		{
		case R.id.mimport: Intent i3 = new Intent(this, Import.class);
		startActivity(i3);
		break; 
		case R.id.settings: Intent i6 = new Intent(this, MasterSettings.class);
		startActivity(i6);
		break;
		case R.id.comm: Intent i7 = new Intent(this, CommScore.class);
		startActivity(i7);
		break;
		case R.id.mfeedback: Intent i5 = new Intent(this,FeedBack.class);
		startActivity(i5);
		break; 
		case R.id.about: Intent i4 = new Intent(this,Aboutus.class);
		startActivity(i4);
		break; 
		}
		return true;
	}     
	//search
	public class ImageAdapter1 extends BaseAdapter{
		Context mContext;
		public static final int ACTIVITY_CREATE = 10;
		public ImageAdapter1(Context c){
			mContext = c;
		}
		/*Type :Function
		name:getCount
		return type:void
		date:29-6-11
		purpose:To get total number of entries in database for add contacts table*/
		public int getCount() {
			int cnt1=results4.size();
			 
			return cnt1;
		} 
		/*Type :Function
		name:getView
		return type:void
		date:29-6-11
		purpose:To get the image path from phone contact and set that image*/
		public View getView(final int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View v;
		
				LayoutInflater li = getLayoutInflater();
				v = li.inflate(R.layout.icon, null);
				TextView tv = (TextView)v.findViewById(R.id.icon_text);
				tv.setText(""+results4.get(position));
				
				System.out.println("name=="+results4.get(position));
				tv.setOnClickListener(new TextView.OnClickListener()
				{

					public void onClick(View v) {                           
						int pos=position;                 

						String id1=results6.get(pos);
						System.out.println("Position... nameeeeeeee"+position+" iddd"+id);
						Intent i= new Intent(Main.this,ContactDetails.class);
						Bundle bun=new Bundle();
						bun.putString("name2",id1);
						i.putExtras(bun);
						startActivity(i);
					}
                                                
				});
				iv1 = (ImageView)v.findViewById(R.id.con_image);
				  String img=results7.get(position);
				  	if(img!=null)
				  	{
				  		Bitmap bm = BitmapFactory.decodeFile(img);
						iv1.setImageBitmap(bm); 
				  	}
				  	else{
				  		iv1.setImageResource(R.drawable.propic);
				  	}
				ImageView iv = (ImageView)v.findViewById(R.id.icon_image);
				iv.setImageResource(R.drawable.call1);
				iv.setOnClickListener(new MyOnClickListener1(position)); 
				
			
			return v;
		}
		public Object getItem(int arg0) {
    
			return null;
		}
		public long getItemId(int arg0) {

			return 0;
		}
	
}
	class MyOnClickListener1 implements OnClickListener  
	{  
		private final int position;

		public MyOnClickListener1(int position)  
		{  
			this.position = position;  

		}                     

		public void onClick(View v)  
		{  

			int pos=position;                
			 num=results5.get(pos);
			System.out.println("position"+pos+"name=="+num);
			Cursor c6=data.getNAMEandID(num);
			while(c6.moveToNext())
			{
				id=c6.getString(0);
							
			}
			c6.close();
			Intent intent = new Intent(Intent.ACTION_CALL);
			intent.setData(Uri.parse("tel:" +num));
			startActivity(intent);
			tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
			tm.listen(mPhoneListener, PhoneStateListener.LISTEN_CALL_STATE);

		}               
	}  
	private PhoneStateListener mPhoneListener = new PhoneStateListener() 
	{
		

		public void onCallStateChanged(int state, String incomingNumber) 
		{
			 if (called && state == TelephonyManager.CALL_STATE_IDLE) 
			 {
	                called = false;
	                tm.listen(this, PhoneStateListener.LISTEN_NONE);
	                try {
	                    Main.this.finish();
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
	                    Cursor c9=data.gettsettings();
						while(c9.moveToNext())
						{
							flag=c9.getInt(0);
							System.out.println("Flag will......."+flag);
						}
						c9.close();
						if(flag==1)
						{
							Intent i = new Intent(Main.this,UpdateStatus.class);
							Bundle bun=new Bundle();
							bun.putString("name",cid1);
							bun.putString("num",num);
							System.out.println("Name "+cid1);
							i.putExtras(bun);
							i.setAction(Intent.ACTION_MAIN);
							startActivity(i);
						}
						else
						{
							Intent i=new Intent(Main.this,Main.class);
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
	
	
	
	
	@Override
	public void onBackPressed()
	{
		Intent i=new Intent(Main.this,Main.class);
		startActivity(i);
	
		return;
	}

	public  class Task extends AsyncTask<String, Integer, Void> {
		private  ProgressDialog dialog;
		protected Context applicationContext;
		
		@Override
		protected void onPreExecute()
		{
			
			System.out.println("IN PreExecute");
			this.dialog = ProgressDialog.show(applicationContext, "Searching Contacts", "Please Wait...", true);
			dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

		}
		@Override
		protected Void doInBackground(String... params) {
			// TODO Auto-generated method stub
			System.out.println("IN BACKGROUND");
				//return flag1;
			
			if(tn==null)
			{
				Toast t=Toast.makeText(getBaseContext(),"Please enter name to search",Toast.LENGTH_LONG);
				t.show();

			}
			tn=es.getText().toString();
			System.out.println("Name "+tn);

			searchtag();
			
			return null ;
			
			
		}
		protected void onProgressUpdate(String... progress) 
		{
			System.out.println("IN update");  
		
	    }
		@Override
		protected void onPostExecute(Void unused) {
			
			
			this.dialog.cancel();
			System.out.println("IN PostExecute");
			calltag();

		      

	}	

	
}
}


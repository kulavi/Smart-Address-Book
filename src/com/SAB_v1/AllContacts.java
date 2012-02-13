// views contacts 
package com.SAB_v1;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;

import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Toast;

import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;

import android.widget.TextView;
public class AllContacts extends Activity 
{	
	final Uri SMS_INBOX = Uri.parse("content://sms/inbox");
	TelephonyManager tm;	                  
	GridView grid_main,grid_main1;
	DataBaseHelper data;
	ProgressDialog dialog;
	String name,name1,name2,sp,num,image,cid,cid1,cid2,image1;
	Button bsearch,add;
	EditText es;
	AlertDialog.Builder alert;
	int cnt=0;
	String tn="";
	 boolean called = false;
	String tn1,mno;
	
	int flag;
	String id,fn,ln,cid8; 
	final static String LOG_TAG = "PocketMagic";  
	TextView txt1,txt2,txt3,tv;                      
	int increment;
	int maximum;           
	String num1;
	ImageView iv1;
	int count;
	private int unreadMessagesCount;
	private int mYear;
	private int mMonth;
	private int mDay;
	private int mHour;
	private int mMinute;
	InputStream is = null;
	private ArrayList<String> results = new ArrayList<String>();
	private ArrayList<String> results4 = new ArrayList<String>();
	private ArrayList<String> results1 = new ArrayList<String>();
	private ArrayList<String> results3 = new ArrayList<String>();
	private ArrayList<String> results2 = new ArrayList<String>();
	private ArrayList<String> results5 = new ArrayList<String>();
	private ArrayList<String> results6 = new ArrayList<String>();
	private ArrayList<String> results7 = new ArrayList<String>();
	ArrayAdapter<String> adapter;
	int logtype;
	int c_id;
	long date;
	private String u_id;
	private String numh;
	private String numw;
	private String numo;
	private String mnoh;
	private String mnow;
	private String mnoo;

	@Override
	public void onCreate(Bundle icicle)
	{                       
		super.onCreate(icicle);
		data=new DataBaseHelper(this);          
		data.open();
		setContentView(R.layout.grid);
		setTitle("Contacts");
		Window window = getWindow();
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        alert=new AlertDialog.Builder(this);
    	tv=(TextView)findViewById(R.id.txt);
    	tv.setVisibility(View.GONE);
		showcontacts();
		if(results.size()==0)   
		{	tv.setVisibility(View.VISIBLE);
			tv.setText("No Contacts!!!Press Menu to Add..");
		}
        grid_main = (GridView)findViewById(R.id.GridView01);
		grid_main.setAdapter(new ImageAdapter(this));
		es=(EditText)findViewById(R.id.esearch);    
		add=(Button)findViewById(R.id.add);
		Cursor c1=data.get_sync_login_settings_u_id();
		while(c1.moveToNext())
		{
			u_id=c1.getString(0);
			System.out.println(""+u_id);
		}
		c1.close();
		add.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				Intent i=new Intent(AllContacts.this,AddContact.class);
				startActivity(i);

			}

		});  
		bsearch= (Button)findViewById(R.id.search);
		bsearch.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				
				Task task = new Task();
        		task.applicationContext = AllContacts.this;
        		task.execute();
        		 
			}

		});
		data.close();
	}
    
    
	/*Type :Function
	name:searchtag
	return type:void
	date:29-6-11
	purpose: to search contacts from database */

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
	/*Type :Function
	name:calltag
	return type:void
	date:10-2-12
	purpose:call sets grid view*/
	public void calltag()
	{
		setContentView(R.layout.gridsearch);
		grid_main1 = (GridView)findViewById(R.id.GridView01);
		grid_main1.setAdapter(new ImageAdapter1(this));
	}
	/*Type :Function
	name:showcontacts
	return type:void
	date:10-2-12
	purpose:retrieves contacts from database*/

	private void showcontacts()             
	{
		Cursor c=data.getData();
		cnt=c.getCount();
		while (c.moveToNext())
		{	
			name=c.getString(0);
			name1=c.getString(1);
			num=c.getString(2); 
			
			image=c.getString(3);
			id=c.getString(4);
			numh=c.getString(5);
			numw=c.getString(6);
			numo=c.getString(7);
			results2.add(id);              
			results3.add(image);
			if(name1==null)
			{
				name1="";
			}
			if(num.equals(""))
			{
				num=numh;
				if(numh.equals(""))
				{
					num=numw;
					 if(numw.equals(""))
						{
							num=numo;
							System.out.println("Num"+num);  
						}
					System.out.println("Num"+num);  
				}
			
				System.out.println("Num"+num);  
			}
			results.add(name+" "+name1);
			results1.add(num);
			System.out.println("My Name :"+name);
			/*for(int i=num.length();i>0;i--)
			{
				for(int j=0;j<10;j++)
				{
					 num12=(Character.toString(num.charAt(i)));
				}
					
			}
			strOriginal = new StringBuffer(strOriginal).reverse().toString();
			System.out.println("num is "+strOriginal);*/
			//System.out.println("Mob :"+mob);
		}
       c.close();
      
	}

	
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.contactmenu, menu);
		return true;
	}


	public boolean onOptionsItemSelected(MenuItem item) 
	{
		switch (item.getItemId()) 
		{
		case R.id.madd: Intent i1 = new Intent(this, AddContact.class);
		startActivity(i1);
		break;
		/*case R.id.mimport: Intent i3 = new Intent(this, Import.class);
		startActivity(i3);
		break;*/
		case R.id.mdelete: Intent i5 = new Intent(this, DeleteContacts.class);
		startActivity(i5);
		break;
		case R.id.calls: Intent i6 = new Intent(this, CallsScore.class);
		startActivity(i6);
		break;
		case R.id.emails: Intent i7 = new Intent(this, EmailScore.class);
		startActivity(i7);
		break;
		case R.id.sms: Intent i8 = new Intent(this, SMSScore.class);
		startActivity(i8);
		break;
		case R.id.recent: Intent i9 = new Intent(this, RecentContacts.class);
		startActivity(i9);
		
		break;
		}
		return true;
	}  
	/*Type :class
	name:ImageAdapter
	date:10-2-12
	purpose:sets data and images to gridview */
	
	public class ImageAdapter extends BaseAdapter{
		Context mContext;
		public static final int ACTIVITY_CREATE = 10;
		public ImageAdapter(Context c){
			mContext = c;
		}
		/*Type :Function
		name:getCount
		return type:void
		date:29-6-11
		purpose:To get total number of entries in database for add contacts table*/
		public int getCount() {
			int cnt1=results2.size();
			 
			return cnt1;
		} 
		/*Type :class
		name:getview
		date:29-6-11
		purpose:To get the image path from phone contact and set that image*/
		public View getView(final int position, View convertView, ViewGroup parent) {
			View v;
		
				LayoutInflater li = getLayoutInflater();
				v = li.inflate(R.layout.icon, null);
				TextView tv = (TextView)v.findViewById(R.id.icon_text);
				tv.setText(""+results.get(position));
				System.out.println("name=="+results.get(position));
				tv.setOnClickListener(new TextView.OnClickListener()
				{

					public void onClick(View v) {                           
						int pos=position;                 

						String id=results2.get(pos);
						System.out.println("Position... nameeeeeeee"+position+" iddd"+id);
						Intent i= new Intent(AllContacts.this,ContactDetails.class);
						Bundle bun=new Bundle();
						bun.putString("name2",id);
						i.putExtras(bun);
						startActivity(i);
					}
                                                   
				});
				iv1 = (ImageView)v.findViewById(R.id.con_image);
				  String img=results3.get(position);
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
		
				iv.setOnClickListener(new MyOnClickListener(position)); 
				
			
			return v;
		}
		public Object getItem(int arg0) {
    
			return null;
		}
		public long getItemId(int arg0) {

			return 0;
		}
	
}
	class MyOnClickListener implements OnClickListener  
	{  
		private final int position;  

		public MyOnClickListener(int position)  
		{  
			this.position = position;  

		}                     

		public void onClick(View v)  
		{  

			int pos=position;                
		  num1=results1.get(pos);
			System.out.println("position"+pos+"name=="+num1);
			Cursor c6=data.get_CID(num1);
			while(c6.moveToNext())
			{
				cid2=c6.getString(0);
				
			}
			c6.close();
			check();
			
			
		}               
	} 
	/*Type :class
	name:mPhoneListener
	date:10-2-12
	purpose:overrides phone listener class methods*/
	
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
	                    AllContacts.this.finish();
	                    System.out.println("num "+incomingNumber);
	                    ArrayList<NameValuePair> nv = new ArrayList<NameValuePair>();
	                    nv.add(new BasicNameValuePair("u_id",u_id));
		        		nv.add(new BasicNameValuePair("num",num1));
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
						Intent i = new Intent(AllContacts.this,UpdateStatus.class);
						Bundle bun=new Bundle();
						bun.putString("name",cid2);
						bun.putString("num",num1);
						System.out.println("Name "+cid2);
						i.putExtras(bun);
						i.setAction(Intent.ACTION_MAIN);
						startActivity(i);
						}
						else
						{
							Intent i=new Intent(AllContacts.this,AllContacts.class);
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
	        		nv.add(new BasicNameValuePair("num",num1));
	        		
	        		
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
	
	
	

	private int thrd;	//search
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
		/*Type :class
		name:getView
		date:10-2-12
		purpose:To get the image path from phone contact and set that image*/
		public View getView(final int position1, View convertView, ViewGroup parent) {
			View v;
		
				LayoutInflater li = getLayoutInflater();
				v = li.inflate(R.layout.icon2, null);
				TextView tv = (TextView)v.findViewById(R.id.icon_text);
				tv.setText(""+results4.get(position1));
				
				System.out.println("name=="+results4.get(position1));
				tv.setOnClickListener(new TextView.OnClickListener()
				{

					public void onClick(View v) 
					{                           
						int pos1=position1;                 

						String id1=results6.get(pos1);
						System.out.println("Position... "+pos1+" iddd"+id1);
						Intent i= new Intent(AllContacts.this,ContactDetails.class);
						Bundle bun=new Bundle();
						bun.putString("name2",id1);
						i.putExtras(bun);
						startActivity(i);
					}  
                                                
				});
				iv1 = (ImageView)v.findViewById(R.id.con_image);
				  String img=results7.get(position1);
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
				iv.setOnClickListener(new MyOnClickListener1(position1)); 
				
			
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
			int clid=(Integer.parseInt(results6.get(position)));
			final Calendar c = Calendar.getInstance();
			mYear = c.get(Calendar.YEAR);
			mMonth = c.get(Calendar.MONTH);
			mDay = c.get(Calendar.DAY_OF_MONTH);
			mHour = c.get(Calendar.HOUR_OF_DAY);
			mMinute = c.get(Calendar.MINUTE);
			String date=mDay+"-"+mMonth+"-"+mYear;
			String time=mHour+":"+mMinute;
			int pos=position; 
			String dur="secs";
		    num1=results5.get(pos);
			Cursor c6=data.get_CID(num1);
			while(c6.moveToNext())
			{
				cid2=c6.getString(0);
				
			}
			c6.close();
			check();
			/*data.InsertcallLog(clid, date, time, dur, num1,2);
			System.out.println("position"+pos+"name=="+num1);
			Intent intent = new Intent(Intent.ACTION_CALL);
			intent.setData(Uri.parse("tel:" +num1));
			startActivity(intent);
			tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
			tm.listen(mPhoneListener, PhoneStateListener.LISTEN_CALL_STATE);*/


		}               
	}  
	@Override
	public void onBackPressed()
	{
		Intent i=new Intent(AllContacts.this,Main.class);
		startActivity(i);
		return;
	}
	/*Type :Function
	name:check
	return type:void
	date:10-2-12
	purpose:get unread sms form phone device*/
	public void check() {
		Cursor c = getContentResolver().query(
	               SMS_INBOX,
	     new String[] { "_id", "thread_id", "address", "person", "date", "body" },
	               "read=0 and address="+num1+"",
	               null,
	               null);
	       unreadMessagesCount=c.getCount();
	       System.out.println("sms count="+unreadMessagesCount);
	      while(c.moveToNext())
	      {
	    	  thrd=c.getInt(1);
	      }
	       if(unreadMessagesCount>0)
	       {
	    	  show_alert();
	       }
	       else
	       {
	    	   Intent intent = new Intent(Intent.ACTION_CALL);
    			intent.setData(Uri.parse("tel:" +num1));
    			startActivity(intent);
    			tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
    			tm.listen(mPhoneListener, PhoneStateListener.LISTEN_CALL_STATE);
	       }
	       
	}

                
	/*Type :Function
	name:show alert
	return type:void
	date:10-2-12
	purpose:shows alert*/

	protected void show_alert()
	{
		 alert.setMessage("You have unread sms for this contact\n Do you want to check??");
         
         // set a positive/yes button and create a listener
         alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

             // do something when the button is clicked
             public void onClick(DialogInterface arg0, int arg1)
             {
             	
            	 Intent defineIntent = new Intent(Intent.ACTION_VIEW); 
            	 defineIntent.setData(Uri.parse("content://mms-sms/conversations/"+thrd));  
            	startActivity(defineIntent);

             	
             }                                             
         });

         // set a negative/no button and create a listener
         alert.setNegativeButton("No", new DialogInterface.OnClickListener() {

             // do something when the button is clicked
             public void onClick(DialogInterface arg0, int arg1) 
             {
            	 Intent intent = new Intent(Intent.ACTION_CALL);
     			intent.setData(Uri.parse("tel:" +num1));
     			startActivity(intent);
     			tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
     			tm.listen(mPhoneListener, PhoneStateListener.LISTEN_CALL_STATE);
             }
         });

         // display box
         alert.show();
		
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
	

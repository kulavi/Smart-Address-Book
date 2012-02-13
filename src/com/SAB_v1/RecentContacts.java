// views most recent added contacts 
package com.SAB_v1;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
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
public class RecentContacts extends Activity 
{	
	TelephonyManager tm;	                  
	GridView grid_main,grid_main1;
	DataBaseHelper data;
	ProgressDialog dialog;
	String name,name1,name2,sp,num,image,cid,cid1,cid2,image1;
	Button bsearch,add;
	EditText es;
	int cnt=0;
	String tn="";
	 boolean called = false;
	 InputStream is = null;
	 private String u_id;
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
	private int mYear;
	private int mMonth;
	private int mDay;
	private int mHour;
	private int mMinute;
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
	String sender,smsdate,msg;
	String receiver,smsrecdate,recmsg;
	private String sdate,cdate;
	private String recentdate;
	private String recenttime;
	private String numh;
	private String numw;
	private String numo;
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
    	add=(Button)findViewById(R.id.add);
    	add.setVisibility(View.GONE);
		//gets current date and time
		Calendar cal = GregorianCalendar.getInstance();
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		sdate=(+ day + "-" + (month+1)+ "-" +year);
		cal.add(Calendar.DATE, -10);
		 cdate=cal.get(Calendar.DATE)
			+ "-"
			+ (cal.get(Calendar.MONTH) + 1)
			+ "-"
			+ (cal.get(Calendar.YEAR));
		 System.out.println("date......."+cdate);
        grid_main = (GridView)findViewById(R.id.GridView01);
		grid_main.setAdapter(new ImageAdapter(this));
		es=(EditText)findViewById(R.id.esearch);    
		showcontacts();
		Cursor c1=data.get_sync_login_settings_u_id();
		while(c1.moveToNext())
		{
			u_id=c1.getString(0);
			System.out.println(""+u_id);
		}
		c1.close();         
		bsearch= (Button)findViewById(R.id.search);
		bsearch.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				
				Task task = new Task();
        		task.applicationContext = RecentContacts.this;
        		task.execute();
        		
			}

		});
		data.close();
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
			results4.add(tn+" "+tn1);
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
	 /*type :function
 	name:showcontacts
 	return type:void          
	date:10-2-12
 	purpose:gets contacts from database*/
	private void showcontacts()             
	{
		Cursor c=data.getrecent(cdate);
		cnt=c.getCount();
		while (c.moveToNext())
		{	
			name=c.getString(0);
			name1=c.getString(1);
			num=c.getString(2); 
			image=c.getString(3);
			id=c.getString(4);
			recentdate=c.getString(5);
			recenttime=c.getString(6);
			numh=c.getString(7);
			numw=c.getString(8);
			numo=c.getString(9);
			results2.add(id);              
			results3.add(image);
			System.out.println("Num"+num);  
			System.out.println("NumH"+numh);
			System.out.println("NumW"+numw);
			System.out.println("NumO"+numo);
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
			System.out.println("Num"+num);  
			results.add(name+" "+name1+"\n"+num+"\n"+recentdate+"\t"+recenttime);
			results1.add(num);
			System.out.println("My Name :"+name);
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
		case R.id.mimport: Intent i3 = new Intent(this, Import.class);
		startActivity(i3);
		break;
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
		/*Type :Function
		name:checkvalidate
		return type:void
		date:29-6-11
		purpose:To get the image path from phone contact and set that image*/
		public View getView(final int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
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
						Intent i= new Intent(RecentContacts.this,ContactDetails.class);
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
			Intent intent = new Intent(Intent.ACTION_CALL);
			intent.setData(Uri.parse("tel:" +num1));
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
	                    RecentContacts.this.finish();
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
						Intent i = new Intent(RecentContacts.this,UpdateStatus.class);
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
							Intent i=new Intent(RecentContacts.this,RecentContacts.class);
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
		name:checkvalidate
		return type:void
		date:29-6-11
		purpose:To get the image path from phone contact and set that image*/
		public View getView(final int position1, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
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
						Intent i= new Intent(RecentContacts.this,ContactDetails.class);
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
			data.InsertcallLog(clid, date, time, dur, num1,2);
			System.out.println("position"+pos+"name=="+num1);
			Intent intent = new Intent(Intent.ACTION_CALL);
			intent.setData(Uri.parse("tel:" +num));
			startActivity(intent);
			tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
			tm.listen(mPhoneListener, PhoneStateListener.LISTEN_CALL_STATE);


		}               
	}  
	@Override
	public void onBackPressed()
	{
		Intent i=new Intent(RecentContacts.this,AllContacts.class);
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
	


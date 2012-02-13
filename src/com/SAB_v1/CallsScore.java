// views contacts 
package com.SAB_v1;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;

import android.widget.TextView;
public class CallsScore extends Activity 
{	
                
	GridView grid_main;
	DataBaseHelper data;
	ProgressDialog dialog;
	String name,name1,name2,sp,num,image,cid,cid1,cid2,image1;
	Button bsearch,add;
	EditText es;
	int cnt1=0;
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
	private ArrayList<String> results = new ArrayList<String>();
	
	private ArrayList<String> results1 = new ArrayList<String>();
	private ArrayList<String> results3 = new ArrayList<String>();
	private ArrayList<String> results2 = new ArrayList<String>();
     ArrayAdapter<String> adapter;
	int logtype;
	int c_id;
	long date;
	String sender,smsdate,msg;
	String receiver,smsrecdate,recmsg;
	private String sdate,cdate;
	private String logid;
	private String logdate;
	private String logtime;
	private String dur;
	private String count1;
	    @Override
	public void onCreate(Bundle icicle)
	{                       
		super.onCreate(icicle);
		data=new DataBaseHelper(this);          
		setContentView(R.layout.commngrid);
		setTitle("My Top 10 Contacts");
		Window window = getWindow();
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        //gets current date and time
    	Calendar cal = GregorianCalendar.getInstance();
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		sdate=(+ day + "-" + (month+1)+ "-" +year);
		System.out.println("today"+sdate);  
		cal.add(Calendar.DATE, -30);
		 cdate=cal.get(Calendar.DATE)
			+ "-"
			+ (cal.get(Calendar.MONTH) + 1)
			+ "-"
			+ (cal.get(Calendar.YEAR));
		 System.out.println("date......."+cdate);
        grid_main = (GridView)findViewById(R.id.GridView01);
        grid_main.setAdapter(new ImageAdapter(this));
		showcontacts();
	}
	    /*Type :Function
		name:showcontacts
		return type:void
		date:10-2-12
		purpose:get call log and contact details from database*/ 
	private void showcontacts()             
	{
		Cursor c=data.getcommnscore();
		cnt1=c.getCount();
		while (c.moveToNext())
		{	
			logid=c.getString(0);
			id=c.getString(1);
			logdate=c.getString(2); 
			logtime=c.getString(3);
			dur=c.getString(4);
			num=c.getString(5);
			logtype=c.getInt(6);
			count1=c.getString(7);
			System.out.println(""+logid+" "+id+" "+logdate+" "+logtime+""+dur+" "+num+""+logtype+" "+count1);
			Cursor c1=data.getfname(num);
			int cnt=c1.getCount();
			if(cnt==0)
			{
				name="No Name";
				results.add(name+"\n"+num);
			}
			else
			{
			while(c1.moveToNext())
			{
				fn=c1.getString(0);
				ln=c1.getString(1);
				name=fn+" "+ln;
				if(name==null)
				{
					name="No Name";
				}
				results.add(name+"\n"+num);
	
			}c1.close(); 
			}
     		results2.add(logid);              
			results3.add(count1+" "+"Calls");
			results1.add(num);
			
		}
       c.close();
      
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
			int cnt1=results.size();
			 
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
				v = li.inflate(R.layout.commnscoreicon, null);
				TextView tv = (TextView)v.findViewById(R.id.icon_text);
				tv.setText(""+results.get(position));
				TextView tv1 = (TextView)v.findViewById(R.id.icon_text1);
				tv1.setText(""+results3.get(position));	
				iv1 = (ImageView)v.findViewById(R.id.con_image);
		  		iv1.setImageResource(R.drawable.propic);
				
		
			
			return v;
		}
		public Object getItem(int arg0) {
    
			return null;
		}
		public long getItemId(int arg0) {

			return 0;
		}
	
}
	@Override
	public void onBackPressed()
	{
		Intent i=new Intent(CallsScore.this,AllContacts.class);
		startActivity(i);
		return;
	}
	}
	


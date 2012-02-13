package com.SAB_v1;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
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

public class ViewCallLog extends Activity 
{	
	                  
	GridView grid_main;
	DataBaseHelper data;
	String name,name1,name2,sp,num,image,cid1,cid2,image1;
	Button bsearch,add;
	EditText es;
	int cnt=0;
	int l_cid;
	String tn="";
	String [] time2;
	String no1;
	 boolean called = false;
	String tn1,mno;
	int flag;
	String id,fn,ln,cid8; 
	String cid,l_time,l_date,l_num;
	final static String LOG_TAG = "PocketMagic";  
	TextView txt1,txt2,txt3,tv;                      
	int increment;
	int maximum;                                                       
	ImageView iv1;
	int count;
	String date,time,logtype;
	private ArrayList<String> results = new ArrayList<String>();
	private ArrayList<String> results1 = new ArrayList<String>();
	private ArrayList<String> results5 = new ArrayList<String>();
	private ArrayList<String> results3 = new ArrayList<String>();
	private ArrayList<String> results2 = new ArrayList<String>();
	private ArrayList<String> results4 = new ArrayList<String>();
	ArrayAdapter<String> adapter;
	private String time3;
	private String tm1;
	private String logid;
	private String date1;
	private String date2;
	private String cdate;
	private int res;
	private int res1;

    @Override
	public void onCreate(Bundle icicle)
	{                       
		super.onCreate(icicle);
		data=new DataBaseHelper(this);          
		setContentView(R.layout.viewlog);
		setTitle("CallLogs");
		Window window = getWindow();
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    	grid_main = (GridView)findViewById(R.id.GridView01);
		grid_main.setAdapter(new ImageAdapter(this));
		this.registerForContextMenu(grid_main);
		 Calendar cal = new GregorianCalendar();
			int month = cal.get(Calendar.MONTH);
			int year = cal.get(Calendar.YEAR);
			int day = cal.get(Calendar.DAY_OF_MONTH);
			date1=(+ year + "-" + (month+1)+ "-" +day);  
			cal.add(Calendar.DATE, -1);
			cdate=cal.get(Calendar.YEAR)
			+ "-"
			+ (cal.get(Calendar.MONTH) + 1)
			+ "-"
			+ (cal.get(Calendar.DATE));
		 System.out.println("Yesterday date......."+cdate);
			System.out.println("Current Date is:"+date1);
		showlogs();
	}

	private void showlogs()             
	{
		
		Cursor c_4= data.getcal_log();
		while(c_4.moveToNext())
		{	
				cid=c_4.getString(0); 
			    date = c_4.getString(1);
				time=c_4.getString(2);
				num=c_4.getString(4);		
				logtype=c_4.getString(5);
				logid=c_4.getString(6);
				System.out.println("con..."+cid);
				System.out.println("Call log date..."+date);
				System.out.println("time..."+time);
				System.out.println("no..."+num);
				System.out.println("logtype..."+logtype);
				System.out.println("logid..."+logid);
				Cursor c1=data.getfname(num);
				int cnt=c1.getCount();
				if(cnt==0)
				{
					name="No Name";
					time2=time.split(":");
					String tm=time2[0];
					int t=Integer.parseInt(tm);
					if(t>12)
					{
						t=t-12;
						time3=""+t;
					
					tm1=time3+":"+time2[1]+"Pm";
					System.out.println("time....."+time3);
					}
					else
					{
						tm1=time2[0]+":"+time2[1]+"Am";	
					}
					results.add(name+"\n"+num+"\t\t"+tm1);
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
					time2=time.split(":");
					String tm=time2[0];
					int t=Integer.parseInt(tm);
					if(t>12)
					{
						t=t-12;
						time3=""+t;
					
					tm1=time3+":"+time2[1]+"Pm";
					System.out.println("time....."+time3);
					}
					else
					{
						tm1=time2[0]+":"+time2[1]+"Am";	
					}
					res1=date.compareTo(date1);
					/*res=date.compareTo(cdate);
					if(res==0)
					{
						date2="Yesterday";
					}*/
					if(res1==0)
					{
						date2="Today";
					}
					else if(res1<0)
					{
						date2=date;
						
					}
					System.out.println("Display Date......... "+date2);
					results.add(name+"\n"+num+"\t\t"+date2+" "+tm1);
		
				}
				}c1.close();
				results5.add(num+"\t\t"+tm1);
			  	results2.add(cid);
				results3.add(num);
				results1.add(logtype);
				results4.add(logid);
			
      
	}
	      
	  c_4.close();
	 
      
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
				v = li.inflate(R.layout.calllogicon, null);
				TextView tv = (TextView)v.findViewById(R.id.icon_text);
				tv.setText(""+results.get(position));
				System.out.println("name=="+results.get(position));
				tv.setOnClickListener(new TextView.OnClickListener()
				{

					public void onClick(View v) {                           
						int pos=position;                 

						String id=results3.get(pos);
						String name1=results.get(pos);
						System.out.println("Position... nameeeeeeee"+position+" iddd"+id);
						Intent i= new Intent(ViewCallLog.this,CallLogDetails.class);
						Bundle bun=new Bundle();
						bun.putString("num",id);
						bun.putString("name",name1);
						i.putExtras(bun);
						startActivity(i);
					}
                                                   
				});
				System.out.println("name=="+results.get(position));
				iv1 = (ImageView)v.findViewById(R.id.con_image);
				iv1.setImageResource(R.drawable.propic);
				
				
				ImageView iv2 = (ImageView)v.findViewById(R.id.icon_image1);
				String p=results1.get(position);
				int pos=Integer.parseInt(p);
				if(pos==1)
					iv2.setImageResource(R.drawable.incoming);
				else if(pos==2)
					iv2.setImageResource(R.drawable.outgoing);
				else if(pos==3)
					iv2.setImageResource(R.drawable.missedtype);
				
			
			return v;
		}
		public Object getItem(int arg0) {
    
			return null;
		}
		public long getItemId(int arg0) {

			return 0;
		}
	
}
	
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.callogmenu, menu);
		return true;
	}


	public boolean onOptionsItemSelected(MenuItem item) 
	{
		switch (item.getItemId()) 
		{
		case R.id.mdelete: Intent i1 = new Intent(this, DeleteCallLogs.class);
		startActivity(i1);
		}
		return true;
	}  
	
	@Override
	public void onBackPressed()
	{
		Intent i=new Intent(ViewCallLog.this,Contacts1.class);
		startActivity(i);
		return;
	}
	
		

	
}
	
	


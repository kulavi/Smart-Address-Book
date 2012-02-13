// views contacts 
package com.SAB_v1;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem; 
import android.view.View;
import android.view.ViewGroup;
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
public class View_notes extends Activity 
{	
	                 
	GridView grid_main;
	DataBaseHelper data;
	ProgressDialog dialog;
	String name,name1,name3,sp,num,image,cid,cid1,image1;
	Button bsearch,add;
	EditText es,note1;
	int cnt=0;
	String fname,lname;
	String tn="";
	String tn1,mno;
	String id,fn,ln; 
	final static String LOG_TAG = "PocketMagic";  
	String notes,date,time;                  
	int increment;
	int maximum;                                                       
	ImageView iv1;
	int count,id1,len;
	TextView tv;
	private ArrayList<String> results = new ArrayList<String>();
	
	ArrayAdapter<String> adapter;
	String sdate,stime,number;
	int mHour,mMinute;
    @Override
	public void onCreate(Bundle icicle)
	{                       
		super.onCreate(icicle);
		 getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
	     getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
		data=new DataBaseHelper(this);          
		setTitle("Notes");
		Bundle bundle=getIntent().getExtras(); 
		num=bundle.getString("number"); 
		System.out.println(" Id will " +num);
		Calendar cal = new GregorianCalendar();
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		sdate=(+ day + "-" + (month+1)+ "-" +year);  
		mHour = cal.get(Calendar.HOUR_OF_DAY);
		mMinute = cal.get(Calendar.MINUTE);
		stime=mHour+":"+mMinute;
		grid_main = (GridView)findViewById(R.id.GridView01);
		 tv=(TextView) findViewById(R.id.txt);
		 tv.setTextColor(Color.BLACK);
		grid_main.setAdapter(new ImageAdapter(this));
		tv.setVisibility(View.GONE);
		shownotes();
		if(results.size()==0)   
		{	tv.setVisibility(View.VISIBLE);
			tv.setText("No Notes To Show");
		}
	Cursor c=data.getnamefornotes(id1);
	while(c.moveToNext())
	{
		fname=c.getString(0);
		lname=c.getString(1);
		System.out.println("Name"+fname+"name"+lname);
		name3=fname+" "+lname;
	}
	}
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.task_menu, menu);
		return true;
	}
	//Call1.class

	public boolean onOptionsItemSelected(MenuItem item) 
	{
		switch (item.getItemId()) 
		{
		case R.id.add:addnote();
		break;
		/*case R.id.delete:Intent i2 = new Intent(this, deletenotes.class);
		Bundle bun1=new Bundle();
		bun1.putInt("inid",id1);
		i2.putExtras(bun1);
		startActivity(i2); 
		break;*/
		}
		return true;
	}    
    

	private void shownotes()             
	{
		Cursor c2=data.getCurrentNotes(id1);
		while(c2.moveToNext())
		{
			notes=c2.getString(0);
			date=c2.getString(1);
			time=c2.getString(2);
			System.out.println("Notes..."+notes);
			System.out.println("Notes date..."+date);
			System.out.println("Notes time..."+time);
			results.add(notes+"\n"+date+" "+time);
		}
		c2.close();
      
	}

	
	public void addnote()
	{
		final Dialog dialog = new Dialog(View_notes.this);

		dialog.setContentView(R.layout.note);
		note1 =(EditText)dialog.findViewById(R.id.unotes);
		dialog.setTitle("Enter Notes");
		dialog.setCancelable(true);
		Button btn=(Button)dialog.findViewById(R.id.bok);
		Button btn1=(Button)dialog.findViewById(R.id.bcancel);
		btn1.setOnClickListener(new OnClickListener() 
 		{
 			public void onClick(View v) 
 			{
 				finish();

 			}
 		});
		btn.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{		

				notes=note1.getText().toString();
				System.out.println("your notes:-"+notes);
				data.Insertnotes(id1,name3,num,notes,sdate,stime);
				Cursor c3 =data.getnotes();
				while(c3.moveToNext())
				{
					String n1=c3.getString(1);
					String n2=c3.getString(2);
					System.out.println("N1 "+n1);
					System.out.println("N2 "+n2);
				}

  				dialog.dismiss();
  				callnextscreen();
  			

			}

		});
		dialog.show();
		
	} 
	
	public void callnextscreen()
	{
		Intent i=new Intent(View_notes.this,View_notes.class);
		Bundle bun33=new Bundle();
		bun33.putString("number",num);
		System.out.println("Id....... "+num);
		i.putExtras(bun33);
		startActivity(i);
		Toast.makeText(this, "Note Added Sucessfully",Toast.LENGTH_SHORT).show();
		
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
		name:checkvalidate
		return type:void
		date:29-6-11
		purpose:To get the image path from phone contact and set that image*/
		public View getView(final int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View v;
		
				LayoutInflater li = getLayoutInflater();
				v = li.inflate(R.layout.iconnotes, null);
				TextView tv = (TextView)v.findViewById(R.id.icon_text);
				tv.setText(""+results.get(position));
				
				System.out.println("name=="+results.get(position));
				
				
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
		/*Intent i=new Intent(View_notes.this,Main.class);
		Bundle bun33=new Bundle();
		bun33.putString("name2",id);
		System.out.println("Id....... "+id);
		i.putExtras(bun33);
		startActivity(i);*/
		return;
	}
	
	}


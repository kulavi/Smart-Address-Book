package com.SAB_v1;

//views task

import java.util.ArrayList;
import android.app.Activity;
import android.app.ProgressDialog;
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
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
public class ViewTaskList extends Activity 
{	
	                  
	GridView grid_main,grid_main1;
	DataBaseHelper data;
	ProgressDialog dialog;
	String tname,tdate,ttime;
	int tid;
	EditText es;
	int cnt=0;
	String tn="";
	String tn1,mno;
	String id,fn,ln; 
	final static String LOG_TAG = "PocketMagic";  
	 TextView tv;                  
	int increment;
	int maximum;                                                       
	ImageView iv1;
	int count;
	private ArrayList<String> results = new ArrayList<String>();
	private ArrayList<String> results1 = new ArrayList<String>();
	private ArrayList<String> results2 = new ArrayList<String>();
	private ArrayList<String> results3 = new ArrayList<String>();
	
	ArrayAdapter<String> adapter;
    @Override
	public void onCreate(Bundle icicle)
	{                       
		super.onCreate(icicle);
		data=new DataBaseHelper(this);          
		data.open();
		setContentView(R.layout.gridtask);
    	tv=(TextView)findViewById(R.id.txt);
    	tv.setVisibility(View.GONE);
		showtask();
		if(results.size()==0)   
		{	tv.setVisibility(View.VISIBLE);
			tv.setText("No Tasks!!!Press Menu to Add..");
		}
        grid_main = (GridView)findViewById(R.id.GridView01);
		grid_main.setAdapter(new ImageAdapter(this));
		es=(EditText)findViewById(R.id.esearch);    
		Window window = getWindow();
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		data=new DataBaseHelper(this);    
	
	}

    /*type :function
 	name:showtask
 	return type:void          
	date:10-2-12
 	purpose:get task details*/
	

	private void showtask()             
	{
		Cursor c=data.gettaskname();
		cnt=c.getCount();
		while (c.moveToNext())
		{	
			id=c.getString(0);
			tname=c.getString(1);
			tdate=c.getString(2);
			ttime=c.getString(3); 
			results.add(tname);
			results2.add(ttime);
			results1.add(tdate);
			results3.add(id);
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
		name:checkvalidate
		return type:void
		date:29-6-11
		purpose:To get the image path from phone contact and set that image*/
		public View getView(final int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View v;
		
				LayoutInflater li = getLayoutInflater();
				v = li.inflate(R.layout.iconlist, null);
				TextView tv = (TextView)v.findViewById(R.id.icon_text);
				tv.setText(""+results.get(position)+"\n "+results1.get(position)+" "+results2.get(position));
				
				System.out.println("name=="+results.get(position));
				tv.setOnClickListener(new TextView.OnClickListener()
				{

					public void onClick(View v) {                           
						int pos=position;                 

						String id=results3.get(pos);
						tid=Integer.parseInt(id);
						System.out.println("Position... nameeeeeeee"+position+" iddd"+id);
						Intent i= new Intent(ViewTaskList.this,Taskdetails.class);
						Bundle bun=new Bundle();
						bun.putInt("tid",tid);
						i.putExtras(bun);
						startActivity(i);
					}
                                                
				});
			
				
			
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
		inflater.inflate(R.menu.taskmenu, menu);
		return true;
	}


	public boolean onOptionsItemSelected(MenuItem item) 
	{
		switch (item.getItemId()) 
		{
		case R.id.view:Intent i = new Intent(this,ViewTaskList.class);
		startActivity(i);
		break;
		case R.id.add:Intent i6 = new Intent(this,Task.class);
		startActivity(i6);
		break;
		case R.id.delete:Intent i7= new Intent(this,DeleteAllTasks.class);
		startActivity(i7);
		break;
		}
		return true;
	}  
	@Override
	public void onBackPressed()
	{
		Intent i=new Intent(ViewTaskList.this,ViewTask.class);
		startActivity(i);
		return;
	}

	}


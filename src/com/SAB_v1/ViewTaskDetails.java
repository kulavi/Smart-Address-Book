// views contacts 
package com.SAB_v1;
import java.util.ArrayList;
import android.app.Activity;
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
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;

import android.widget.TextView;
public class ViewTaskDetails extends Activity 
{	
	                 
	GridView grid_main;
	DataBaseHelper data;
	ProgressDialog dialog;
	String name,name1,name2,sp,num,image,cid,cid1,image1;
	Button bsearch,add;
	EditText es;
	int cnt=0;
	String stask,sdescp,sdate,stime; 
	String tn="";
	String tn1,mno;
	String id,fn,ln,taskid; 
	final static String LOG_TAG = "PocketMagic";  
	String notes;                  
	int increment;
	int maximum;            
	int tid;
	ImageView iv1;
	int count,id1,len;
	TextView tv;
	private ArrayList<String> results = new ArrayList<String>();
	private ArrayList<String> results3 = new ArrayList<String>();
	ArrayAdapter<String> adapter;
    @Override
	public void onCreate(Bundle icicle)
	{                       
		super.onCreate(icicle);
		data=new DataBaseHelper(this);          
		setContentView(R.layout.gridtasks);
		setTitle("Tasks");
		Bundle bundle=getIntent().getExtras(); 
		id1=bundle.getInt("inid"); 
		System.out.println(" Id will " +id1);
		id=""+id1;
		grid_main = (GridView)findViewById(R.id.GridView01);
		grid_main.setAdapter(new ImageAdapter(this));
		tv=(TextView) findViewById(R.id.txt);
		tv.setTextColor(Color.BLACK);
		tv.setVisibility(View.GONE);
		showtasks();
		if(results.size()==0)   
		{	tv.setVisibility(View.VISIBLE);
			tv.setText("No Tasks To Show");
		}		
	}
    /*type :function
 	name:showtask
 	return type:void          
	date:10-2-12
 	purpose:get task details*/
    

	private void showtasks()             
	{
		Cursor c1=data.getnowTasks(id1);
		while(c1.moveToNext())
		{
		  stask=c1.getString(0);
		  sdescp=c1.getString(1);
		  sdate=c1.getString(2);
		  stime=c1.getString(3);
		  taskid=c1.getString(4);
		  System.out.println("Task1 "+stask);
		  System.out.println("Description1 "+sdescp);
		  System.out.println("Taskid......... "+taskid);
		  results.add(stask+"\n"+sdate+" "+stime);
		  results3.add(taskid);
		}	
		
		c1.close();    
      
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
		case R.id.add:Intent i = new Intent(this, AddTask.class);
		Bundle bun=new Bundle();
		bun.putString("name",id);
		System.out.println("Name2: "+id);
		i.putExtras(bun);
		startActivity(i);
		break;
		case R.id.delete:Intent i2 = new Intent(this, delete_task_frm_cdetails.class);
		Bundle bun1=new Bundle();
		bun1.putInt("inid",id1);
		System.out.println("Name2: "+id1);
		i2.putExtras(bun1);
		startActivity(i2); 
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
			int cnt1=results.size();
			 
			return cnt1;
		} 
		/*Type :class
		name:getView
		date:29-6-11
		purpose:To get the image path from phone contact and set that image*/
		public View getView(final int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View v;
		
				LayoutInflater li = getLayoutInflater();
				v = li.inflate(R.layout.icontasks, null);
				TextView tv = (TextView)v.findViewById(R.id.icon_text);
				tv.setText(""+results.get(position));
				tv.setOnClickListener(new TextView.OnClickListener()
				{

					public void onClick(View v) {                           
						int pos=position;                 

						String id=results3.get(pos);
						tid=Integer.parseInt(id);
						System.out.println("Position... nameeeeeeee"+position+" iddd"+id);
						Intent i= new Intent(ViewTaskDetails.this,Taskdetails.class);
						Bundle bun=new Bundle();
						bun.putInt("tid",tid);
						i.putExtras(bun);
						startActivity(i);
					}
                                                
				});
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
		Intent i=new Intent(ViewTaskDetails.this,ContactDetails.class);
		Bundle bun33=new Bundle();
		bun33.putString("name2",id);
		System.out.println("Id....... "+id);
		i.putExtras(bun33);
		startActivity(i);
		return;
	}
	
	}


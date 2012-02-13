package com.SAB_v1;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.*;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;

public class Taskdetails extends Activity
{	
	TextView tname,tddate,tdesp,ttime,tcat,tpriority,tloc;
	DataBaseHelper db;
	int id,tc;
	Button dedit,ddelete,back,dcancel;
	String taskname,taskdescp,taskpriority,tdate,tatime,tacat,loc;
	 public void onCreate(Bundle savedInstanceState)
	 {
		  super.onCreate(savedInstanceState);
		  
		     getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);   
		     db=new DataBaseHelper(this);
		     setContentView(R.layout.ddetails);
		     setTitle("Task details");
		     Bundle bun=getIntent().getExtras();
		     id =bun.getInt("tid");
		     tname=(TextView)findViewById(R.id.tname);
		     tdesp=(TextView)findViewById(R.id.tdesp);
		     tloc=(TextView)findViewById(R.id.tloc);
		     tpriority=(TextView)findViewById(R.id.tpriority);
	        ttime=(TextView)findViewById(R.id.time);
	        tddate=(TextView)findViewById(R.id.due);
	       dcancel=(Button)findViewById(R.id.cancel);
	        dedit=(Button)findViewById(R.id.dedit);
	   //   tcat=(TextView)findViewById(R.id.tcat);
	        ddelete=(Button)findViewById(R.id.ddelete);
	        System.out.println("task is...dd"+id);
	      
	        	Cursor c=db.gettaskdetails(id);
	        	while(c.moveToNext())
	        	{
				   taskname=c.getString(0);
				   taskdescp=c.getString(1);
				   taskpriority=c.getString(2);
				   tatime=c.getString(3);
				   tdate=c.getString(4);
				   loc=c.getString(5);
				  tname.setText(taskname);
				   tdesp.setText(taskdescp);
				   tloc.setText(loc);
				   tpriority.setText(taskpriority);
				   tddate.setText(tdate);
				   ttime.setText(tatime);
				  // tcat.setText(tacat);
			   }
	        	//category
	        
	        	dcancel.setOnClickListener(new OnClickListener() {
	    			public void onClick(View v) 
	    			{	
	    				finish();
	    				
	    			}
	    			});
	        	dedit.setOnClickListener(new OnClickListener() {
	    			public void onClick(View v) 
	    			{	
	    				edit();
	    				
	    			}
	    			});
	        	ddelete.setOnClickListener(new OnClickListener() {
	    			public void onClick(View v) 
	    			{
	    				db.deletetask(id);
	    				Toast t=Toast.makeText(getBaseContext(),"Task Deleted",Toast.LENGTH_LONG);
	    				t.show();
	    				Intent i=new Intent(Taskdetails.this,ViewTaskList.class);
	    				startActivity(i);
	    			
	    			}
	    			});
	        
	 }
	
	 //calls activity for editing
	protected void edit() 
	{
		Intent i= new Intent(this,Dedit.class);
		 Bundle bun=new Bundle();
		 bun.putInt("tid",id);
		 i.putExtras(bun);
		 startActivity(i);
		
	}
	@Override
	public void onBackPressed()
	{
		 Intent i1= new Intent(this,ViewTaskList.class);
		 startActivity(i1);
	}
	
}


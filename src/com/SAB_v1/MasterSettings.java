package com.SAB_v1;

import java.util.ArrayList;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MasterSettings extends Activity implements OnItemClickListener
{
	ListView lv,lv1;
	int cnt,eid,tid;
	String n,e,p;
	GridView grid_main;
	CheckBox ch,ch1;
	DataBaseHelper data;
	int flag,flag1;
	String array[]={"Call Settings","Account Settings","Notification Settings","Sync Settings","Two Screen Settings"};
	private ArrayList<String> results = new ArrayList<String>();
	private ArrayList<String> results1 = new ArrayList<String>();
	@Override            
	public void onCreate(Bundle icicle)  
	{
		super.onCreate(icicle);
		setContentView(R.layout.mastersetlist);
		setTitle("Master Settings");
		data=new DataBaseHelper(this);
		lv=(ListView)findViewById(R.id.list);
		lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		lv.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,array));
		lv.setTextFilterEnabled(true);
		lv.setOnItemClickListener(this);
	
		
	}
	public void onItemClick(AdapterView<?> a, View v, int position, long id) 
	{
		System.out.println("Position"+position);
		if(position==0)
		{
		callsettings();
		}
		else if(position==1)
		{
			accountsettings();
		}
		else if(position==2)
		{
			tasksettings();
		}
		else if(position==3)
		{
			syncsettings();
		}
		else if(position==4)
		{
			twoscreensettings();
		}
		}
	 /*type :function
  	name:accountsettings
  	return type:void          
	date:10-2-12
  	purpose:calls account settings class*/
	public void accountsettings()
	{
		Intent i=new Intent(this,AccountSettings.class);
		startActivity(i);
		
   }
	 /*type :function
  	name:sync settings
  	return type:void          
	date:10-2-12
  	purpose:calls sync settings class*/
	public void syncsettings()
	{
		Intent i=new Intent(this,SyncSettings.class);
		startActivity(i);
		
   }
	 /*type :function
  	name:tasksettings
  	return type:void          
	date:10-2-12
  	purpose:calls task settings class*/
	public void tasksettings()
	{
		Intent i1=new Intent(this,TaskSettings.class);
		startActivity(i1);
		
   }
	 /*type :function
  	name:twoscreensettings
  	return type:void          
	date:10-2-12
  	purpose:pop a dialog*/
	public void twoscreensettings()
	{	
		final AlertDialog.Builder alertbox=new AlertDialog.Builder(this);
		// set the message to display
        alertbox.setMessage("Do you want to Sign in for SAB App?");
        
        // set a positive/yes button and create a listener
        alertbox.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            // do something when the button is clicked
            private String email;

			public void onClick(DialogInterface arg0, int arg1)
            {
            	Cursor c=data.get_sync_login_settings_details();
            	int cnt=c.getCount();
            	while(c.moveToNext())
            	{
            		email=c.getString(0);
            		System.out.println("email "+email);
            	}
            	if(cnt>0)
            	{
            		final AlertDialog.Builder alertbox1=new AlertDialog.Builder(MasterSettings.this);
        			alertbox1.setMessage("You have already login by acoount "+email);
        		     c.close();
                    // set a positive/yes button and create a listener
                    alertbox1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
         
                        // do something when the button is clicked
                        public void onClick(DialogInterface arg0, int arg1)
                        {
                        	
                        	call_self();
                        }
                    });
                    alertbox1.show();
        		
            	}
            	else
            	{
            	   call();
            	}
            	
            }                                             
        });

        // set a negative/no button and create a listener
        alertbox.setNegativeButton("No", new DialogInterface.OnClickListener() {

            // do something when the button is clicked
            public void onClick(DialogInterface arg0, int arg1) 
            {
            	call_self();
            }
        });

        // display box
        alertbox.show();
		}
	public void call_self()
	   {
		   Intent i2=new Intent(MasterSettings.this,MasterSettings.class);
			startActivity(i2);
	   }	
	 /*type :function
 	name:call
 	return type:void          
	date:10-2-12
 	purpose:calls two screen settings class*/
   public void call()
   {
	   Intent i2=new Intent(MasterSettings.this,Two_Screen_Settings.class);
		startActivity(i2);
   }
   /*type :function
	name:mastersettings
	return type:void          
	date:10-2-12
	purpose:sets grid view*/
	public void callsettings()
	{
		setContentView(R.layout.mastersettings);
		data=new DataBaseHelper(this);
		grid_main = (GridView)findViewById(R.id.GridView01);
		grid_main.setAdapter(new ImageAdapter(this));
		results.add("Add Task and\nNotes after call");
	   results1.add("Send Email Alert\nAfter adding task");
	 
	}
	public class ImageAdapter extends BaseAdapter{
		Context mContext;
		private int eid;
		public static final int ACTIVITY_CREATE = 10;
		public ImageAdapter(Context c){
			mContext = c;
		}
		/*Type :Function
		name:getCount
		return type:void
		date:29-6-11
		purpose:To get total number of entries in database for add contacts table*/
		public int getCount() 
		{
		 cnt=results.size();			 
			return cnt;
		} 
		public View getView(final int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View v;
		
				LayoutInflater li = getLayoutInflater();
				v = li.inflate(R.layout.mastericon, null);
				ch=(CheckBox)v.findViewById(R.id.chk_sel);
				ch1=(CheckBox)v.findViewById(R.id.chk_sel1);
				Cursor c9=data.gettsettings();
				while(c9.moveToNext())
				{
					flag=c9.getInt(0);
					tid=c9.getInt(1);
					System.out.println("Flag will......."+flag);
				}
				c9.close();                        
				if(flag==1)
				{
				  ch.setChecked(true);	
				}
				Cursor c10=data.getesettings();
				while(c10.moveToNext())
				{
					flag1=c10.getInt(0);
					eid=c10.getInt(1);
					System.out.println("EFlag will......."+flag1);
				}
				c10.close();
				if(flag1==1)
				{
				  ch1.setChecked(true);	
				}
				TextView tv = (TextView)v.findViewById(R.id.icon_text);
				tv.setText(""+results.get(position));
				  	
				  	ch.setOnClickListener( new OnClickListener() 
				  	{
     					public void onClick(View v) 
						{
							System.out.println("Position..."+position);
							String ans=results1.get(position);
							System.out.println("Value is "+ans);
							if(ch.isChecked())
							{
							System.out.println("tid=="+tid);
							data.Inserttsettings(tid);
							Toast.makeText(MasterSettings.this, "Settings Saved Successfully",Toast.LENGTH_SHORT).show();         
							}
							else
							{
								data.Inserttsettings_zero(tid);
							}
						
						}          
				  		                    
				  		
				  	});
				  	TextView tv1 = (TextView)v.findViewById(R.id.icon_text1);
					tv1.setText(""+results1.get(position));
					  
					  	ch1.setOnClickListener( new OnClickListener() 
					  	{

							public void onClick(View v) 
							{
								System.out.println("Position..."+position);
								String ans1=results1.get(position);
								System.out.println("Value is "+ans1);
								if(ch1.isChecked())
								{
									
									System.out.println("Checked11......");
									data.Insertesettings(eid);
									Toast.makeText(MasterSettings.this, "Settings Saved Successfully",Toast.LENGTH_SHORT).show();         
								}
								else
								{
									data.Insertesettings_zero(eid);
								}
								                    
						  		
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
	@Override
	public void onBackPressed()
	{	
    	Intent i=new Intent(this,Main.class);
		startActivity(i);
	}
}

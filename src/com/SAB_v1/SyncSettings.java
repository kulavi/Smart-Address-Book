package com.SAB_v1;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class SyncSettings extends Activity implements OnItemClickListener
{
	ListView lv1;
	int cnt,flag,flag1;
	DataBaseHelper data;
	String array[]={"Sync Contacts","Sync Call List","Sync Tasks","Sync Logs","Sync SMS"};
	private int flag2;
	private int flag3;
	private int flag4;
     CheckBox chkall;
     Button ok;
	private int id;
	private int con_flag;
	private int call_flag;
	private int task_flag;
	private int log_flag;
	private int sms_flag;
	@Override             
	public void onCreate(Bundle icicle)  
	{
	super.onCreate(icicle);
	setContentView(R.layout.deletecontacts);
	data=new DataBaseHelper(this);
	setTitle("Sync Settings");
	lv1=(ListView)findViewById(R.id.List);
	chkall=(CheckBox)findViewById(R.id.chk_all);
	ok= (Button)findViewById(R.id.ok);
	ok.setOnClickListener(new OnClickListener() 
	{
		public void onClick(View v) 
		{
            				
			Intent i=new Intent(SyncSettings.this,MasterSettings.class);
			startActivity(i);
		}

	});
	CheckBox cb=new CheckBox(this); 
	lv1.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
	lv1.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_multiple_choice,array));
	lv1.setTextFilterEnabled(true);
	lv1.setOnItemClickListener(this);
	Cursor c9=data.getsyncsettings();
	while(c9.moveToNext())
	{
		flag=c9.getInt(0);
		flag1=c9.getInt(1);
		flag2=c9.getInt(2);
		flag3=c9.getInt(3);
		flag4=c9.getInt(4);
		System.out.println(" Flag will be......."+flag+" "+flag1+" "+flag2+" "+flag3+" "+flag4);
	}
	c9.close();
	                     
	if(flag==1)
	{                                                                       
	 lv1.setItemChecked(0, true);	
	}   
	if(flag1==1)
	{
		 lv1.setItemChecked(1, true);	
	}     
	if(flag2==1)
	{
		 lv1.setItemChecked(2, true);	
	}     
	if(flag3==1)
	{
		 lv1.setItemChecked(3, true);	
	}     
	if(flag4==1)
	{
		 lv1.setItemChecked(4, true);	
	} 
	Cursor c=data.getsyncsettings_id();
	while(c.moveToNext())
	{
		id=c.getInt(0);
		System.out.println("ID "+id);
	}c.close();
	
	chkall.setOnCheckedChangeListener(new OnCheckedChangeListener()
    {
        

		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			// TODO Auto-generated method stub
			
			
			if(chkall.isChecked())
			{
				makeSelections();
			}
			else
			{
				removeSelections();
				
			}
			
		}
    });
}
	private void makeSelections() 
	{

		// user has checked checkbox so check all the items

		int count = this.lv1.getAdapter().getCount();
		

		for (int i = 0; i < count; i++) 
		{
			
			
			this.lv1.setItemChecked(i, true);
			data.updatesyncset(id);
			
		}
		Toast.makeText(SyncSettings.this, "Settings Saved Successfully",Toast.LENGTH_SHORT).show();
		

		

	}
	private void removeSelections() { 

		// user has unchecked checkbox so uncheck all the items

		int count = this.lv1.getAdapter().getCount();

		for(int i=0;i<count;i++)
		{
		this.lv1.setItemChecked(i, false);
		data.updatesyncset_zero(id);
		
		}
		

	}                     

	public void onItemClick(AdapterView<?> a, View v, int position, long id1) 
	{
		
		if(position==0)
		{
			Cursor c1=data.get_sync_set_con_data(id);
			while(c1.moveToNext())
			{
				con_flag=c1.getInt(0);
				System.out.println("Con flag "+con_flag);
			}c1.close();
			if(con_flag==0)
			{
		      data.updatesyncsetcon(id);
		    // lv1.setItemChecked(0, true);	
			}
			else
			{
				data.updatesyncsetcon_zero(id);
			 //lv1.setItemChecked(0, false);	
			}
	  
		}
		 if(position==1)
		{
			Cursor c12=data.get_sync_set_call_data(id);
			while(c12.moveToNext())
			{
				call_flag=c12.getInt(0);
				System.out.println("Call flag "+call_flag);
			}c12.close();
			if(call_flag==0)
			{
				data.updatesyncsetcall(id);
				 //lv1.setItemChecked(1, true);	
			}
			else
			{
				data.updatesyncsetcall_zero(id);
				//lv1.setItemChecked(1, false);	
			}
		
		}
		 if(position==2)
		{
			Cursor c2=data.get_sync_set_task_data(id);
			while(c2.moveToNext())
			{
				task_flag=c2.getInt(0);
				System.out.println("Task flag "+task_flag);
			}c2.close();
			if(task_flag==0)
			{
				data.updatesyncsettask(id);
				//lv1.setItemChecked(2, true);	
			}
			else
			{
				data.updatesyncsettask_zero(id);
				 //lv1.setItemChecked(2, false);	
			}
		
		}
		 if(position==3)
		{
			Cursor c3=data.get_sync_set_log_data(id);
			while(c3.moveToNext())
			{
				log_flag=c3.getInt(0);
				System.out.println("Log flag "+log_flag);
			}c3.close();
			if(log_flag==0)
			{
				 data.updatesyncsetlog(id);
			   //lv1.setItemChecked(3, true);	
			}
			else
			{
				 data.updatesyncsetlog_zero(id);
			// lv1.setItemChecked(3, false);	
			}
		 
		}
		 if(position==4)
		{
			Cursor c4=data.get_sync_set_sms_data(id);
			while(c4.moveToNext())
			{
				sms_flag=c4.getInt(0);
				System.out.println("Sms flag "+sms_flag);
			}c4.close();
			if(sms_flag==0)
			{
				  data.updatesyncsetsms(id);
			   //lv1.setItemChecked(4, true);	
			}
			else
			{
				  data.updatesyncsetsms_zero(id);
				 //lv1.setItemChecked(4, false);	
			}
		
		}
	}

}

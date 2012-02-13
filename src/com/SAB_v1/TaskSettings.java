package com.SAB_v1;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class TaskSettings extends Activity implements OnItemClickListener
{
	ListView lv1;
	int cnt,flag,flag1;
	DataBaseHelper data;
	String array[]={"Notificaton on Status bar","Notification Pop up window"};
	@Override             
	public void onCreate(Bundle icicle)  
	{
	super.onCreate(icicle);
	setContentView(R.layout.mastersetlist);
	data=new DataBaseHelper(this);
	setTitle("Notification Settings");
	lv1=(ListView)findViewById(R.id.list);
	lv1.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
	lv1.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_single_choice,array));
	lv1.setTextFilterEnabled(true);
	lv1.setOnItemClickListener(this);
	Cursor c9=data.getnotisettings();
	while(c9.moveToNext())
	{
		flag=c9.getInt(0);
		System.out.println("Noti Flag will be......."+flag);
	}
	c9.close();
	Cursor c10=data.getwinsettings();
	while(c10.moveToNext())
	{
		flag1=c10.getInt(0);
		System.out.println("Win Flag will be......."+flag1);
	}
	c10.close();                          
	if(flag==1)
	{                                                                       
	 lv1.setItemChecked(0, true);	
	}   
	else
	if(flag1==1)
	{
		 lv1.setItemChecked(1, true);	
	}                         
 
}
	public void onItemClick(AdapterView<?> a, View v, int position, long id) 
	{
		System.out.println("Position"+position);
		if(position==0)
		{
		  data.updatensettings();
		  data.updatewinsettings_zero();
			  
		}
		else if(position==1)
		{
		  data.updatewinsettings();
		  data.updatensettings_zero();
		}
		
		}
}

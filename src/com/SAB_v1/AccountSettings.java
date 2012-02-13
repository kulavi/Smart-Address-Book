package com.SAB_v1;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class AccountSettings extends Activity implements OnItemClickListener
{
	ListView lv,lv1;
	int cnt;
	String n,e,p;
	DataBaseHelper data;
	int flag,flag1;
	String array[]={"Accounts","My Profile"};
	@Override            
	public void onCreate(Bundle icicle)  
	{
		super.onCreate(icicle);
		setContentView(R.layout.mastersetlist);
		setTitle("Account Settings");
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
		    addacc();
		}
		else if(position==1)
		{
			viewprofile();
		}
	}
	public void viewprofile()
	{
		//get user profile from database and set to grid view 
		Cursor c=data.getUserData();
		int count=c.getCount();
		if(count==0)
		{
		Intent i3 = new Intent(this, MyProfile.class);
		startActivity(i3);
		}
		else
			if(count>0)
			{
				Intent i31 = new Intent(this, ViewProfile.class);
				startActivity(i31);
			}
		c.close();
	}
	public void addacc()
	{
		//adds new account to database
		Cursor c1=data.getaccData();
		int count1=c1.getCount();
		if(count1==0)
		{
		Intent i3 = new Intent(this, Account.class);
		startActivity(i3);
		}
		else
			if(count1>0)
			{
				Intent i=new Intent(this,view_account.class);
				startActivity(i);
			}
	  c1.close();	
	}
	@Override
	public void onBackPressed()
	{	
    	Intent i=new Intent(this,MasterSettings.class);
		startActivity(i);
	}
}

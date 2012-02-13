package com.SAB_v1;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class delete_account extends Activity implements OnItemClickListener,OnCheckedChangeListener  
{
	ListView lv1;
	private ArrayList<String> results = new ArrayList<String>();
	
	DataBaseHelper data;
	String s;
	int id1,id2,id21;
	int callid;
	String name;
	Cursor c;
	Button bsearch,add;
	EditText es;
	int cnt=0,id8;
	String tn="";
	String tn1,mno;
	CheckBox chkall;
    Button ok;
    String acc_name,acc_email,acc_name1,acc_email1;
	public void onCreate(Bundle savedInstanceState)
	{
		requestWindowFeature(Window.FEATURE_NO_TITLE);  
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.delete_account);
		data=new DataBaseHelper(this);
		Window window = getWindow();
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		lv1=(ListView)findViewById(R.id.List);
		chkall=(CheckBox)findViewById(R.id.chk_all);
		//gets account details from database
		Cursor c=data.viewAccDetails();
		while (c.moveToNext())
		{	
			acc_email=c.getString(0);
			results.add(acc_email);
			
			System.out.println("acc name are "+acc_name+" "+acc_email);
			Cursor c1=data.getaccid(acc_email);
			while (c1.moveToNext())
			{	
				id2=c1.getInt(0);
				System.out.println("Acc ID "+id2);
			}
			c1.close();
			
			}
		c.close();
		ok= (Button)findViewById(R.id.ok);
		ok.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
                				
				Toast.makeText(delete_account.this, "Account Deleted Successfully",Toast.LENGTH_SHORT).show();
				Intent i=new Intent(delete_account.this,view_account.class);
				startActivity(i);
			}

		});
		
		CheckBox cb=new CheckBox(this); 
		lv1.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		lv1.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_multiple_choice,results));
		lv1.setTextFilterEnabled(true);
		lv1.setOnItemClickListener(this);
		
		
		
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
			data.deleteaccount(id2);
			delete();
			
		}
	}
	/*
  	type :func
  	name:delete
  	return type:void          
	date:10-2-12
  	purpose:to delete accounts                     
  */	
	private void delete() 
	{
		Cursor c1=data.viewAccDetails();
		while (c1.moveToNext())
		{	
			acc_email1=c1.getString(0); 
			System.out.println("Account ID "+acc_name1+" "+acc_email1);
			Cursor c11=data.getaccid(acc_email1);
			while (c11.moveToNext())
			{	
				id21=c11.getInt(0);
				System.out.println("Account ID "+id21);
			}
			c11.close();
		}
		c1.close();
		data.deleteaccount(id21);
		
	}

	private void removeSelections() { 

		// user has unchecked checkbox so uncheck all the items

		int count = this.lv1.getAdapter().getCount();

		for(int i=0;i<count;i++)
		{
		this.lv1.setItemChecked(i, false);
				
		}
		

	}

	public void onItemClick(AdapterView<?> a, View v, int position, long id) 
	{
		System.out.println("Position..."+position);
		String ans= (String) a.getItemAtPosition(position);
		System.out.println("Value is "+ans);
		Cursor cursor=data.getaccid(acc_email);
		while(cursor.moveToNext())
		{
			id8=cursor.getInt(0);
			System.out.println("Accounts are..... "+id8);
		}
		cursor.close();
		lv1.clearChoices();
		data.deleteaccount(id8);
		results.remove(position);
		
	}

	public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBackPressed()
	{
		Intent i=new Intent(delete_account.this,ViewGroups.class);
		startActivity(i);
		return;
	}
	
}

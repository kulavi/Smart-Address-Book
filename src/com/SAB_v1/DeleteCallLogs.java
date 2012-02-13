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

public class DeleteCallLogs extends Activity implements OnItemClickListener,OnCheckedChangeListener  
{
	ListView lv1;
	DataBaseHelper data;
	String s;
	int id1,id2;
	int callid;
	String name,name1,n1,n2;
	String []n;
	Button bsearch,add;
	EditText es;
	int cnt=0,id8;
	String tn="";
	String tn1,mno;
	CheckBox chkall;
    Button ok;
	private String cid;
	private String date;
	private String time;
	private String num;
	private String logtype;
	private int logid;
	private String[] time2;
	private String time3;
	private String tm1;
	private String fn;
	private String ln;
	private ArrayList<String> results = new ArrayList<String>();
	private String[] no;
	private String no1;
	private String time1;
	public void onCreate(Bundle savedInstanceState)
	{
		
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.deletecontacts);
		data=new DataBaseHelper(this);
		setTitle("Delete Call Logs");
		Window window = getWindow();
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		lv1=(ListView)findViewById(R.id.List);
		chkall=(CheckBox)findViewById(R.id.chk_all);
		ok= (Button)findViewById(R.id.ok);
		ok.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
                				
				Intent i=new Intent(DeleteCallLogs.this,ViewCallLog.class);
				startActivity(i);
			}

		});
		//get log from database
		Cursor c_4=data.getcal_log();
		while(c_4.moveToNext())
		{	
				cid=c_4.getString(0); 
			    date = c_4.getString(1);
				time=c_4.getString(2);
				num=c_4.getString(4);		
				logtype=c_4.getString(5);
				logid=c_4.getInt(6);
				System.out.println("con..."+cid);
				System.out.println("date..."+date);
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
					results.add(num+"\t\t"+tm1);
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
					results.add(name+"\n"+num+"\t\t"+tm1);
		
				}
				}c1.close();
      
	}
	      
	  c_4.close();
      
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
			data.deleteAllCallLog(logid);
			delete();
			
		}
		Toast.makeText(DeleteCallLogs.this, "Logs Deleted Successfully",Toast.LENGTH_SHORT).show();
		

		

	}
	/*
  	type :function
  	name:delete
  	return type:void          
	date:10-2-12
  	purpose:to delete logs                   
  */
	private void delete() 
	{
		Cursor c_4=data.getcal_log();
		while(c_4.moveToNext())
		{
			id2=c_4.getInt(6);
		}
		c_4.close();
		data.deleteAllCallLog(id2);
		
	}

	private void removeSelections() { 

		// user has unchecked checkbox so uncheck all the items

		int count = this.lv1.getAdapter().getCount();

		for(int i=0;i<count;i++)
		{
		this.lv1.setItemChecked(i, false);
		
		//deletetest_to_questions(dbtestid,""+questionid[i],"0");
		}
		

	}                     

	public void onItemClick(AdapterView<?> a, View v, int position, long id) 
	{
		System.out.println("Position..."+position);
		String ans= (String) a.getItemAtPosition(position);
		System.out.println("Value is "+ans);
		if(ans.contains("\n"))
		{
		n=ans.split("\n");
			int len=n.length;
			if(len>1)
			{
		 n1=n[0];
		 n2=n[1];
		System.out.println("N1 will be "+n1);
		System.out.println("N2 will be "+n2);
		if(n2.contains("\t\t"))
		{
			no=n2.split("\t\t");
			int len1=no.length;
			if(len1>1)
			{
				no1=no[0];
				time1=no[1];
				System.out.println("No will  "+no1);
				System.out.println("Time will "+time1);
			}
			else
			{
				no1=no[0];
				time1="";
			}
		}
		 }
		else
		{
		n1=n[0];
		n2="";
		System.out.println("N1 will beeee "+n1);
		System.out.println("N2 will beeee "+n2);
		}
		}                   
		else
		{
			 n1=ans;
			 n2="";
			 System.out.println("N1 will be..."+n1);
			 System.out.println("N2 will be..."+n2);
			 if(n1.contains("\t\t"))
			{
				 no=n1.split("\t\t");
					int len1=no.length;
					if(len1>1)
					{
						no1=no[0];
						time1=no[1];
						System.out.println("No will  "+no1);
						System.out.println("Time will "+time1);
					}
					else
					{
						no1=no[0];
						time1="";
					}
			 }
	
		}
		Cursor cursor=data.get_log_id(no1);
		while(cursor.moveToNext())
		{
			id8=cursor.getInt(0);
			System.out.println("Log id will "+id8);
		}
		cursor.close();
		lv1.clearChoices();                               
		data.deleteAllCallLog(id8);
		results.remove(position);
		Toast.makeText(DeleteCallLogs.this, "Logs Deleted Successfully",Toast.LENGTH_SHORT).show();
	}

	public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBackPressed()
	{
		Intent i=new Intent(DeleteCallLogs.this,ViewCallLog.class);
		startActivity(i);
		return;
	}
	
}

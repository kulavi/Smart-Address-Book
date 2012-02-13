package com.SAB_v1;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class DeleteTask extends Activity implements OnItemClickListener,OnCheckedChangeListener  
{
	ListView lv1;
	DataBaseHelper data;
	String s;
	int id1,id2;
	int callid;
	String name,n1,n2,n3;
	String [] n;
	Cursor c;
	Button bsearch,add;
	EditText es;
	int cnt=0,id8;
	String tn="";
	String tn1,mno;
	CheckBox chkall;
    Button ok;
    private int mHour,mMinute,mYear,mMonth,mDay;;
	private final String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    
	GridView grid_main,grid_main1;
	String tdate,ttime;
	int tid;
	
	String id,fn,ln; 
	final static String LOG_TAG = "PocketMagic";  
            
	int increment;
	int maximum;                                                       
	ImageView iv1;
	int count;
	
	private ArrayList<String> results = new ArrayList<String>();
	String date,time,pr;
	int hr;
	String day1,mnth,yr;
	LinearLayout layout;
	TextView t1,t2,tv;
	EditText ename,edesp;
	LayoutParams params;
	LinearLayout mainLayout;
	String sdate,date1;
	String tname,tdesp,ans,tm;
    public void onCreate(Bundle savedInstanceState)
	{
		requestWindowFeature(Window.FEATURE_NO_TITLE);  
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.deletetask);
		data=new DataBaseHelper(this);
		data.open();
		Window window = getWindow();
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		lv1=(ListView)findViewById(R.id.List);
		chkall=(CheckBox)findViewById(R.id.chk_all);
		Bundle bundle = getIntent().getExtras(); 
		date1 = bundle.getString("date");
		String[]s=date1.split("-");
		day1=s[0];
		mnth=s[1];
		yr=s[2];
		System.out.println("date bun"+date1);
		//get current date and time
		final Calendar c = Calendar.getInstance();
		mYear =Integer.parseInt(yr);  
		mMonth = getMonthAsNO(mnth);
		mDay = Integer.parseInt(day1);  
		date=(+ (mDay) + "-" +(mMonth+1)+ "-" +mYear);
		System.out.println("Date:" +date);
		Cursor c1=data.gettaskname1(date1);
		cnt=c1.getCount();
		while (c1.moveToNext())
		{	
			id=c1.getString(0);
			tname=c1.getString(1);
		    tdesp=c1.getString(2);
			ttime=c1.getString(3);
			System.out.println("Taskid="+id);
			System.out.println("TaskNAME="+tname);
			System.out.println("Taskdesp="+tdesp);
			System.out.println("Tasktime="+time);
			results.add(tname+" "+ttime);
			
		}
       c1.close();
	  ok= (Button)findViewById(R.id.ok);
		ok.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				Intent i=new Intent(DeleteTask.this,ViewTaskByDate.class);
				Bundle b=new Bundle();
				b.putString("date",date1);
				i.putExtras(b);
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
    /*type :function
  	name:getMonthsAsNO
  	return type:void          
	date:10-2-12
  	purpose:to get months in number format*/
	private int getMonthAsNO(String mnth2)
	{
		int m=0;
		for(int i=0;i<months.length;i++)
		{
			if(months[i].equals(mnth2))
			{
				 m=i;
			}
		}
		return m;
	}
	  /*type :function
  	name:delete
  	return type:void          
	date:10-2-12
  	purpose:delete task*/
	private void delete() 
	{
		Cursor c1=data.gettaskname1(date1);
		cnt=c1.getCount();
		while (c1.moveToNext())
		{	
			id1=c1.getInt(0);
	     }
       c1.close();
       data.deletetaskdate(id1,date1);
       Toast.makeText(DeleteTask.this, "Tasks Deleted Successfully",Toast.LENGTH_SHORT).show();
	}
	private void makeSelections() 
	{

		// user has checked checkbox so check all the items

		int count = this.lv1.getAdapter().getCount();
		

		for (int i = 0; i < count; i++) 
		{
			
			
			this.lv1.setItemChecked(i, true);
			 data.deletetaskdate(id1, date1);
			 delete();
		}
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
		if(ans.contains(" "))
		{
		n=ans.split(" ");     
		 n1=n[0];
		 n2=n[1];
		}
		else    
		{
			 n1=ans;
			 n2="";
		}
		Cursor c1=data.gettaskname1(date1);
		cnt=c1.getCount();    
		while (c1.moveToNext())
		{	
			id2=c1.getInt(0);
	     }
       c1.close();
       data.deletetaskdate(id2,date1);
		lv1.clearChoices();
		results.remove(position);
		Toast.makeText(DeleteTask.this, "Tasks Deleted Successfully",Toast.LENGTH_SHORT).show();
	}

	public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBackPressed()
	{
		Intent i=new Intent(DeleteTask.this,ViewTaskByDate.class);
		Bundle bun=new Bundle();
		bun.putString("date",date1);
		i.putExtras(bun);
		startActivity(i);
		return;
	}
	
}


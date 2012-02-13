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

public class DeleteGroupDetails extends Activity implements OnItemClickListener,OnCheckedChangeListener  
{
	ListView lv1;
	private ArrayList<String> results = new ArrayList<String>();
	DataBaseHelper data;
	String s;
	int id1,id2;
	int callid,cid2,cid6;
	int [] cid3;
	String name;
	Cursor c;
	Button bsearch,add;
	EditText es;
	int cnt=0,id8,cid4;
	String tn="";
	String tn1,mno,cid;
	CheckBox chkall;
	String fn,ln;
	int name3;
	Button ok;
	int id,cid1;
	String name1,name2;
	String[] name5;
	String fn1,ln1;

	public void onCreate(Bundle savedInstanceState)
	{
		requestWindowFeature(Window.FEATURE_NO_TITLE);  
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.deletelist);
		data=new DataBaseHelper(this);
		 Bundle bundle = getIntent().getExtras(); 
			id1=bundle.getInt("groupid");
			System.out.println("List Name "+name3);
		Window window = getWindow();
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		lv1=(ListView)findViewById(R.id.List);
		chkall=(CheckBox)findViewById(R.id.chk_all);
		//get contacts details from database
		Cursor c3=data.getgcont_id(id1);
		while(c3.moveToNext())
		{
			cid2=c3.getInt(0);
			System.out.println("cid will "+cid2);
			Cursor c12=data.getcontacts(cid2);
			while(c12.moveToNext())
			{
				fn=c12.getString(0);
				ln=c12.getString(1);
				System.out.println("Fn will "+fn);
				System.out.println("Ln will "+ln);
				results.add(fn+" "+ln);
			}
			c12.close();
			
		}
	   c3.close();
		ok= (Button)findViewById(R.id.ok);
		ok.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
						
				Toast.makeText(DeleteGroupDetails.this, "Contacts Deleted Successfully",Toast.LENGTH_SHORT).show();
				Intent i=new Intent(DeleteGroupDetails.this,ViewGroups.class);
				startActivity(i);
			}

		});
		CheckBox cb=new CheckBox(this); 
		lv1.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		lv1.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_multiple_choice,results));
		lv1.setTextFilterEnabled(true);
		lv1.setOnItemClickListener(this);
		
		//es=(EditText)findViewById(R.id.esearch );
		
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
			
		}
		
		Cursor c=data.getgcont_id(id1);
		while(c.moveToNext())
		{   
			cid4=c.getInt(0);
			System.out.println("cid will "+cid4);
			data.deleteGroupdetails(id1,cid4);
		}
		c.close();		

		

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

	public void onItemClick(AdapterView<?> a, View v, int position, long id3) 
	{
		System.out.println("Position..."+position);
		String ans= (String) a.getItemAtPosition(position);
		System.out.println("Value is "+ans);
		 if(ans.contains(" "))
		{ 
			String[]s=ans.split(" ");
			int len=s.length;
			if(len==1)   
			{
				fn1=s[0]; 
				ln1="";
			}
			else
			{
			fn1=s[0]; 
			ln1=s[len-1];
			}
		}
		else
		{
		fn1=name;
		ln1="";
		}
		
    	Cursor c8=data.getContactId(fn1,ln1);
		while(c8.moveToNext())
		{
			cid1=c8.getInt(0);
			System.out.println("cid1 is "+cid1);
			data.deleteGroupdetails(id1,cid1);
		}
		
		lv1.clearChoices();
		results.remove(position);
		
	}

	public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBackPressed()
	{
		Intent i=new Intent(DeleteGroupDetails.this,ViewGroups.class);
		startActivity(i);
		return;
	}
	
}

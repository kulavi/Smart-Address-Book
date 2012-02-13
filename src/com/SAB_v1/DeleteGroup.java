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

public class DeleteGroup extends Activity implements OnItemClickListener,OnCheckedChangeListener  
{
	ListView lv1;
	private ArrayList<String> results = new ArrayList<String>();
	DataBaseHelper data;
	String s;
	int id1,id2;
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
	public void onCreate(Bundle savedInstanceState)
	{
		requestWindowFeature(Window.FEATURE_NO_TITLE);  
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.deletecalllist);
		data=new DataBaseHelper(this);
		Window window = getWindow();
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		lv1=(ListView)findViewById(R.id.List);
		chkall=(CheckBox)findViewById(R.id.chk_all);
		//get group details from database
		c=data.get_all_groupname();
		while (c.moveToNext())
		{	
			name=c.getString(0);
			System.out.println("Group names are "+name);
			Cursor c1=data.getgroupid(name);
			while (c1.moveToNext())
			{	
				id2=c1.getInt(0);
				System.out.println("Group ID "+id2);
			}
			c1.close();
			results.add(name);
			}
		c.close();
		ok= (Button)findViewById(R.id.ok);
		ok.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
                				
				Toast.makeText(DeleteGroup.this, "Group Deleted Successfully",Toast.LENGTH_SHORT).show();
				Intent i=new Intent(DeleteGroup.this,ViewGroups.class);
				startActivity(i);
			}

		});
		
		CheckBox cb=new CheckBox(this); 
		lv1.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		lv1.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_multiple_choice,results));
		lv1.setTextFilterEnabled(true);
		lv1.setOnItemClickListener(this);
		
		es=(EditText)findViewById(R.id.esearch );
		
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
			data.deleteGroupName(id2);
			delete();
			
		}
	}
	/*
  	type :function
  	name:delete
  	return type:void          
	date:10-2-12
  	purpose:to delete groups              
  */
	
	private void delete() 
	{
		c=data.get_all_groupname();
		while (c.moveToNext())
		{	
			name=c.getString(0);
			System.out.println("Group names are "+name);
			Cursor c1=data.getgroupid(name);
			while (c1.moveToNext())
			{	
				id2=c1.getInt(0);
				System.out.println("Group ID "+id2);
			}
			c1.close();
			results.add(name);
			}
		c.close();
		data.deleteGroupName(id2);
		
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
		Cursor cursor=data.retGrouplId(ans);
		while(cursor.moveToNext())
		{
			id8=cursor.getInt(0);
			System.out.println("Group idsssss "+id8);
		}
		cursor.close();
		lv1.clearChoices();
		data.deleteGroupName(id8);
		results.remove(position);
		
	}

	public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBackPressed()
	{
		Intent i=new Intent(DeleteGroup.this,ViewGroups.class);
		startActivity(i);
		return;
	}
	
}

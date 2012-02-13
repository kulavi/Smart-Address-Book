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

public class DeleteContacts extends Activity implements OnItemClickListener,OnCheckedChangeListener  
{
	ListView lv1;
	private ArrayList<String> results = new ArrayList<String>();
	private ArrayList<String> results4 = new ArrayList<String>();
	DataBaseHelper data;
	String s;
	int id1,id2;
	int callid;
	String name,name1,n1,n2;
	String []n;
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
		
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.deletecontacts);
		data=new DataBaseHelper(this);
		setTitle("Delete Contacts");
		Window window = getWindow();
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		lv1=(ListView)findViewById(R.id.List);
		chkall=(CheckBox)findViewById(R.id.chk_all);
		ok= (Button)findViewById(R.id.ok);
		ok.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
                				
				Toast.makeText(DeleteContacts.this, "Contacts Deleted Successfully",Toast.LENGTH_SHORT).show();
				Intent i=new Intent(DeleteContacts.this,AllContacts.class);
				startActivity(i);
			}

		});
		//gets contacts details  
		c=data.getAllcon();
		while(c.moveToNext())
		{
			id2=c.getInt(0);
			name=c.getString(1);
			name1=c.getString(2);
			System.out.println("Id will "+id2);
			results.add(name+" "+name1);
		}
		c.close();
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
			data.deleteAllContacts(id2);
			delete();
			
		}
		Toast.makeText(DeleteContacts.this, "All CallList Deleted Successfully",Toast.LENGTH_SHORT).show();
		

		

	}
	/*
  	type :function
  	name:delete
  	return type:void          
	date:10-2-12
  	purpose:to delete contacts                     
  */
	private void delete() 
	{
		c=data.getData();
		while(c.moveToNext())
		{
			id2=c.getInt(4);
			//System.out.println("Id will "+id2);
		}
		c.close();
		data.deleteAllContacts(id2);
		
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
			int len=n.length;
			if(len>1)
			{
		 n1=n[0];
		 n2=n[1];
			}else
			{
				n1=n[0];
				n2="";
			}
		}                   
		else
		{
			 n1=ans;
			 n2="";
		}
		System.out.println("N1 will "+n1);
		System.out.println("N2 will "+n2);
		Cursor cursor=data.getContactId(n1,n2);
		while(cursor.moveToNext())
		{
			id8=cursor.getInt(0);
			
		}
		cursor.close();
		lv1.clearChoices();                               
		data.deleteAllContacts(id8);
		results.remove(position);
		
	}

	public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBackPressed()
	{
		Intent i=new Intent(DeleteContacts.this,AllContacts.class);
		startActivity(i);
		return;
	}
	
}

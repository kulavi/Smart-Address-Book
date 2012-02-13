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

public class deletenotes extends Activity implements OnItemClickListener,OnCheckedChangeListener  
{
	ListView lv1;
	private ArrayList<String> results = new ArrayList<String>();
	private ArrayList<String> results1 = new ArrayList<String>();
	
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
    int noteid;  
    String note,note_date,note_time;
	public void onCreate(Bundle savedInstanceState)
	{
		requestWindowFeature(Window.FEATURE_NO_TITLE);  
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.deletecalllist);
		data=new DataBaseHelper(this);
		Window window = getWindow();
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        Bundle bundle=getIntent().getExtras(); 
		id1=bundle.getInt("inid"); 
		System.out.println(" Id will " +id1);
		
		lv1=(ListView)findViewById(R.id.List);
		chkall=(CheckBox)findViewById(R.id.chk_all);
		c=data.getnotedetails(id1);
		while (c.moveToNext())
		{	
			noteid=c.getInt(0);
			note=c.getString(1);
			note_date=c.getString(2);
			note_time=c.getString(3);
			System.out.println("Group names are "+name);
			
			results.add(note+"\n"+note_date+" "+note_time);
			results1.add(""+noteid);
			
		}
		c.close();
		ok= (Button)findViewById(R.id.ok);
		ok.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
                				
				Toast.makeText(deletenotes.this, "Note Deleted Successfully",Toast.LENGTH_SHORT).show();
				Intent i=new Intent(deletenotes.this,ViewNotesDetails.class);
				Bundle bun1=new Bundle();
				bun1.putInt("inid",id1);
				System.out.println("Name2: "+id1);
				i.putExtras(bun1);
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
			data.deletenote(noteid,id1);
			delete();
			
		}
	}
	/*type :function
  	name:delete
  	return type:void          
	date:10-2-12
  	purpose:to delete notes*/
	
	private void delete() 
	{
		c=data.getnotedetails(id1);
		while (c.moveToNext())
		{	
			
			noteid=c.getInt(0);
			note=c.getString(1);
			note_date=c.getString(2);
			note_time=c.getString(3);
			System.out.println("Group names are "+name);
			
			results.add(note+"\n"+note_date+" "+note_time);
			results1.add(""+noteid);
			
			}
		c.close();
		data.deletenote(noteid,id1);
		
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
		
		int note_id1=Integer.parseInt(results1.get(position));
	
		
		
		lv1.clearChoices();
		data.deletenote(note_id1,id1);
		results.remove(position);
		
	}

	public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBackPressed()
	{
		Intent i=new Intent(deletenotes.this,ViewNotesDetails.class);
		startActivity(i);
		return;
	}
	
}

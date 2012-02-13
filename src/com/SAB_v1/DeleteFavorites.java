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

public class DeleteFavorites extends Activity implements OnItemClickListener,OnCheckedChangeListener  
{
	ListView lv1;
	private ArrayList<String> results = new ArrayList<String>();
	private ArrayList<String> results1 = new ArrayList<String>();
	private ArrayList<String> results3 = new ArrayList<String>();
	private ArrayList<String> results2 = new ArrayList<String>();
	private ArrayList<String> results4 = new ArrayList<String>();
	DataBaseHelper data;
	String s,fid;
	int id1,id2;
	int callid;
	Cursor c;
	Button bsearch,add;
	EditText es;
	int cnt=0,id8;
	String tn="";
	String tn1,mno;
	CheckBox chkall;
    Button ok;
    int f_id,f_cid,f_flag;
    int f_id1,f_cid1,f_flag1;
    String fname,lname,num1,image2;
    String name,name1,name2,sp,num,image,cid,cid1,cid2,image1;
   
	public void onCreate(Bundle savedInstanceState)
	{
		requestWindowFeature(Window.FEATURE_NO_TITLE);  
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.deletecalllist);
		data=new DataBaseHelper(this);
		Window window = getWindow();
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        Cursor c=data.get_favorites();
		cnt=c.getCount();
		while (c.moveToNext())
		{
			f_id=c.getInt(0);
			f_cid=c.getInt(1);
			System.out.println("fid will.."+f_id);
			System.out.println("fcid will.."+f_cid);
			cid=""+f_cid;
			fid=""+f_id;
			//get favorites from database
			Cursor c1=data.getcontacts(f_cid);
			while(c1.moveToNext())
			{
				 name=c1.getString(0);
	        	 name1=c1.getString(1);
	        	 num=c1.getString(2);
	        	 image=c1.getString(3);
	        	 System.out.println("Fname and Lname and Num "+name+" "+name1+" "+num);
	        	 results.add(name+" "+name1);
	        	 results1.add(num);
	        	 results3.add(image);
			}
			results2.add(cid);
			results4.add(fid);
		}
		c.close(); 
	      
			lv1=(ListView)findViewById(R.id.List);
			chkall=(CheckBox)findViewById(R.id.chk_all);
		ok= (Button)findViewById(R.id.ok);
		ok.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
                				
				Toast.makeText(DeleteFavorites.this, "Favorites Deleted Successfully",Toast.LENGTH_SHORT).show();
				Intent i=new Intent(DeleteFavorites.this,Favorites.class);
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
			data.deletefavorites(f_id, f_cid);
			delete();
			
		}
	}
	/*
  	type :function
  	name:delete
  	return type:void          
	date:10-2-12
  	purpose:to delete favorites                    
  */	
	private void delete() 
	{
		Cursor c=data.get_favorites();
		while (c.moveToNext())
		{
			f_id1=c.getInt(0);
			f_cid1=c.getInt(1);
			System.out.println("fid1 will.."+f_id1);
			System.out.println("fcid1 will.."+f_cid1);
			Cursor c1=data.getcontacts(f_cid1);
			while(c1.moveToNext())
			{
				 fname=c1.getString(0);
	        	 lname=c1.getString(1);
	        	 num1=c1.getString(2);
	        	 image2=c1.getString(3);
	        	 System.out.println("Fname and Lname and Num "+fname+" "+lname+" "+num1);
	        	
			}
			
		}
		c.close();
		
		data.deletefavorites(f_id1, f_cid1);
		
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
		int cid1=Integer.parseInt(results2.get(position));
		int fid1=Integer.parseInt(results4.get(position));
		lv1.clearChoices();
		data.deletefavorites(fid1, cid1);
		results.remove(position);
		
	}

	public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBackPressed()
	{
		Intent i=new Intent(DeleteFavorites.this,ViewTaskDetails.class);
		startActivity(i);  
		return;
	}
	
}

package com.SAB_v1;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Create_group extends Activity {
	GridView grid_main,grid_main1;
	DataBaseHelper data;
	ArrayList<String> results = new ArrayList<String>();
	ArrayList<String> results1 = new ArrayList<String>();
	ArrayList<String> results2 = new ArrayList<String>();
	ArrayList<String> results3 = new ArrayList<String>();
	private ArrayList<String> results4 = new ArrayList<String>();
	private ArrayList<String> results5 = new ArrayList<String>();
	private ArrayList<String> results6 = new ArrayList<String>();
	private ArrayList<String> results7 = new ArrayList<String>();
	private ArrayList<String> results8 = new ArrayList<String>();
	int no,i;
	String n,m,m1;
  Button bback,bsearch,ok,cancel,ok1;
	EditText es;
	String tn="",tn1,mno,id4;
	int id1,cnt,callid1,call;
	CheckBox ch;
	String fname,lname,listname1,fname1,lname1;
	String callid,cid;
	String name,name1,name2,sp,num,image;
	String group;
	EditText edtgroup;
	Dialog myDialog;
	int id6;
	@Override
	public void onCreate(Bundle icicle)
	{   
		
		 super.onCreate(icicle);
		 getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
	     data=new DataBaseHelper(this);   
		 Window window = getWindow();  
	     window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
	     getcontacts();
	     opendialog();
	     
	    	
	}
	
	/*Type :Function
	name:opendilaog
	return type:void
	date:10-2-12
	purpose:set dialog*/
	public void opendialog()
	{
		myDialog = new Dialog(this);
     	myDialog.setContentView(R.layout.group);
     	myDialog.setTitle("Create Group");
     	myDialog.setCancelable(true);  
        edtgroup = (EditText) myDialog.findViewById(R.id.edtgname);
        Button button = (Button) myDialog.findViewById(R.id.done);
         button.setOnClickListener(new OnClickListener() 
         {
         public void onClick(View v)
         {
        	 group=edtgroup.getText().toString();
        	 data.InsertGroup(group);
        	 grid();
         } 
         });
 Button button1 = (Button) myDialog.findViewById(R.id.cancel);
               
            
         
         button1.setOnClickListener(new OnClickListener() 
         {
         public void onClick(View v)
         {
        	finish();
         } 
         });
         myDialog.show();
		
	}
	/*Type :Function
	name:grid
	return type:void
	date:10-2-12
	purpose:sets grid view*/
	public void grid()
	{
	 myDialog.dismiss();
    setContentView(R.layout.create_group);	
    grid_main = (GridView)findViewById(R.id.GridView01);
	grid_main.setAdapter(new ImageAdapter(this));
	es=(EditText)findViewById(R.id.esearch);
	bsearch= (Button)findViewById(R.id.search);
	bsearch.setOnClickListener(new OnClickListener() 
	{      
		public void onClick(View v) 
		{
			
			Task task = new Task();
    		task.applicationContext = Create_group.this;
    		task.execute();
		}

	});
	
	Cursor cur=data.getgroupid(group);
	while(cur.moveToNext())
	{
		id6=cur.getInt(0);
		System.out.println("Gid will "+id6);
	}
	 ok1= (Button)findViewById(R.id.ok1);
		ok1.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
	            				
				call();
			}

		});
		
	}
	/*Type :Function
	name:call
	return type:void
	date:10-2-12
	purpose:call group details class*/
	public void call()
	{
		Toast.makeText(Create_group.this, "Group Created Successfully",Toast.LENGTH_SHORT).show();
		Intent i=new Intent(Create_group.this,ViewGroups.class);
		startActivity(i);
	}
	/*Type :Function
	name:getcontacts
	return type:void
	date:10-2-12
	purpose:get details of contacts from database*/
	private void getcontacts()
	{
		Cursor c=data.getData();
		cnt=c.getCount();                   
		while (c.moveToNext())
		{	
			name=c.getString(0);
			name1=c.getString(1);
			num=c.getString(2); 
			image=c.getString(3);
			callid=c.getString(4);
			results1.add(num);
			results2.add(callid);       
			results3.add(image);
			if(name1==null)
			{
				name1="";
			}
			results.add(name+" "+name1);
			System.out.println("My Name :"+name);
			
		}
       c.close();
	}
	/*Type :Function
	name:searchtag
	return type:void
	date:10-2-12
	purpose:serach contacts from database*/
	private void searchtag() 
	{
		
		Cursor c=data.SearchTag(tn);
		while (c.moveToNext())
		{	
			tn=c.getString(0);
			tn1=c.getString(1);
			mno=c.getString(2);
			image=c.getString(3);
			System.out.println("Name:"+tn);
			System.out.println("LName:"+tn1);
			Cursor c1=data.getContactId(tn,tn1);
			while(c1.moveToNext())
			{
				cid=c1.getString(0);
				System.out.println("CID "+cid);
				results8.add(cid);
			}
			c1.close();
			if(tn.contains("@1@"))
			{
				tn=tn.replace("@1@", "'");
			}
			if(tn1.contains("@1@"))
			{
				tn1=tn1.replace("@1@", "'");
			}
			results4.add(tn+" "+tn1);
			results5.add(""+id1);
			results6.add(mno);
			results7.add(image);
		}
		c.close();
	}
	/*Type :Function
	name:calltag
	return type:void
	date:10-2-12
	purpose:sets grid view*/
	public void calltag()
	{
		setContentView(R.layout.gridclist);
		data=new DataBaseHelper(this);
		grid_main1 = (GridView)findViewById(R.id.GridView01);
		grid_main1.setAdapter(new ImageAdapter1(this));
		ok1= (Button)findViewById(R.id.ok1);
		ok1.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
                				
				Toast.makeText(Create_group.this, "Contacts Added Successfully",Toast.LENGTH_SHORT).show();
				Intent i=new Intent(Create_group.this,ViewGroups.class);
				startActivity(i);
			}

		});
		
		
	}
	public class ImageAdapter extends BaseAdapter{
		Context mContext;
		public static final int ACTIVITY_CREATE = 10;
		public ImageAdapter(Context c){
			mContext = c;
		}
		/*Type :Function
		name:getCount
		return type:void
		date:29-6-11
		purpose:To get total number of entries in database for add contacts table*/
		public int getCount() {
		 cnt=results.size();			 
			return cnt;
		} 
		public View getView(final int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View v;
		
				LayoutInflater li = getLayoutInflater();
				v = li.inflate(R.layout.listicon, null);
				TextView tv = (TextView)v.findViewById(R.id.icon_text);
				tv.setText(""+results.get(position));
				
				System.out.println("name=="+results.get(position));
				ImageView iv1 = (ImageView)v.findViewById(R.id.con_image);
				  String img=results3.get(position);
				  	if(img!=null)
				  	{
				  		Bitmap bm = BitmapFactory.decodeFile(img);
						iv1.setImageBitmap(bm); 
				  	}
				  	else{
				  		iv1.setImageResource(R.drawable.propic);
				  	}
				  	
				  	
				  		
				  	ch=(CheckBox)v.findViewById(R.id.chk_sel);
				  	ch.setOnClickListener( new OnClickListener() 
				  	{

						public void onClick(View v) 
						{
							System.out.println("Position..."+position);
							String ans=results2.get(position);
							System.out.println("Value is "+ans);
							callid1=Integer.parseInt(ans);
							System.out.println("Id is.............."+callid1);
							Cursor c1=data.validate_group(id6,callid1);
							int count=c1.getCount();
							if(count>0)
							{
								Toast.makeText(Create_group.this, "Contact already added to this group", Toast.LENGTH_LONG);
							}
							else
							{							
							data.InsertGroupDetails(id6,callid1);
							}
				
			  									
						}          
				  		                    
				  		
				  	});
			return v;
		}
		public Object getItem(int arg0) {

			return null;
		}
		public long getItemId(int arg0) {

			return 0;
		}
	}
	//search
	public class ImageAdapter1 extends BaseAdapter{
		Context mContext;
		public static final int ACTIVITY_CREATE = 10;
		public ImageAdapter1(Context c){
			mContext = c;
		}
		/*Type :Function
		name:getCount
		return type:void
		date:29-6-11
		purpose:To get total number of entries in database for add contacts table*/
		public int getCount() {
		 cnt=results4.size();			 
			return cnt;
		} 
		public View getView(final int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View v;
		
				LayoutInflater li = getLayoutInflater();
				v = li.inflate(R.layout.listicon, null);
				TextView tv = (TextView)v.findViewById(R.id.icon_text);
				tv.setText(""+results4.get(position));
				
				System.out.println("name=="+results4.get(position));
				ImageView iv1 = (ImageView)v.findViewById(R.id.con_image);
				  String img=results7.get(position);
				  	if(img!=null)
				  	{
				  		Bitmap bm = BitmapFactory.decodeFile(img);
						iv1.setImageBitmap(bm); 
				  	}
				  	else{
				  		iv1.setImageResource(R.drawable.propic);
				  	}
				  	
				  	
				  		
				  	ch=(CheckBox)v.findViewById(R.id.chk_sel);
				  	ch.setOnClickListener( new OnClickListener()
				  	{

						public void onClick(View v) 
						{
							System.out.println("Position..."+position);
							String ans=results8.get(position);
							System.out.println("Value is "+ans);
							call=Integer.parseInt(ans);
							System.out.println("Id is "+call);
							   data.InsertGroupDetails(id6,call);
			  			}          
				  		
				  		
				  	});
			return v;
		}
		public Object getItem(int arg0) {

			return null;
		}
		public long getItemId(int arg0) {

			return 0;
		}
	}
	
	public  class Task extends AsyncTask<String, Integer, Void> {
		private  ProgressDialog dialog;
		protected Context applicationContext;
		
		@Override
		protected void onPreExecute()
		{
			
			System.out.println("IN PreExecute");
			this.dialog = ProgressDialog.show(applicationContext, "Searching Contacts", "Please Wait...", true);
			dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

		}
		@Override
		protected Void doInBackground(String... params) {
			// TODO Auto-generated method stub
			System.out.println("IN BACKGROUND");
				//return flag1;
			
			if(tn==null)
			{
				Toast t=Toast.makeText(getBaseContext(),"Please enter name to search",Toast.LENGTH_LONG);
				t.show();

			}tn=es.getText().toString();
			System.out.println("Name "+tn);

			searchtag();
			
			return null ;
			
			
		}
		protected void onProgressUpdate(String... progress) 
		{
			System.out.println("IN update");
		
	    }
		@Override
		protected void onPostExecute(Void unused) {
			
			
			this.dialog.cancel();
			System.out.println("IN PostExecute");
			calltag();

		      

	}	

	
}
	
}

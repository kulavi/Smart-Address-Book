package com.SAB_v1;
import java.util.ArrayList;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.*;
public class Edit_group extends Activity 
{	
	int no,i;
	String n,m,m1;
	GridView grid_main,grid_main1;
	DataBaseHelper data;
	Button bback,bsearch,ok,cancel,ok1;
	EditText es;
	String tn="",tn1,mno,id4;
	int id1,cnt,callid1,call;
	CheckBox ch;
	String fname,lname,listname1,fname1,lname1;
	String callid,cid;
	String name,name1,name2,sp,num,image;
	private ArrayList<String> results = new ArrayList<String>();
	private ArrayList<String> results1 = new ArrayList<String>();
	private ArrayList<String> results3 = new ArrayList<String>();
	private ArrayList<String> results2 = new ArrayList<String>();
	private ArrayList<String> results4 = new ArrayList<String>();
	private ArrayList<String> results5 = new ArrayList<String>();
	private ArrayList<String> results6 = new ArrayList<String>();
	private ArrayList<String> results7 = new ArrayList<String>();
	private ArrayList<String> results8 = new ArrayList<String>();
	public void onCreate(Bundle savedInstanceState)
	{
		requestWindowFeature(Window.FEATURE_NO_TITLE);  
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gridlist);
		data=new DataBaseHelper(this);
		Window window = getWindow();
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        Bundle bundle = getIntent().getExtras(); 
		id1=bundle.getInt("groupid");
		getcontacts();
		grid_main = (GridView)findViewById(R.id.GridView01);
		grid_main.setAdapter(new ImageAdapter(this));
		es=(EditText)findViewById(R.id.esearch);
		bsearch= (Button)findViewById(R.id.search);
		bsearch.setOnClickListener(new OnClickListener() 
		{      
			public void onClick(View v) 
			{
				Task task = new Task();
        		task.applicationContext = Edit_group.this;
        		task.execute();

			}

		});
		ok= (Button)findViewById(R.id.ok);
		ok.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
                				
				Toast.makeText(Edit_group.this, "Contacts Added Successfully",Toast.LENGTH_SHORT).show();
				Intent i=new Intent(Edit_group.this,ViewGroups.class);
				startActivity(i);
			}

		});	
	}
	 /*type :function
  	name:searchtag
  	return type:void          
	date:10-2-12
  	purpose:searches contacts*/
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
	 /*type :function
  	name:calltag
  	return type:void          
	date:10-2-12
  	purpose:sets gridview*/
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
                				
				Toast.makeText(Edit_group.this, "Contacts Added Successfully",Toast.LENGTH_SHORT).show();
				Intent i=new Intent(Edit_group.this,ViewGroups.class);
				startActivity(i);
			}

		});
	}
	 /*type :function
  	name:getcontacts
  	return type:void          
	date:10-2-12
  	purpose:get contacts from database*/
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
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
	}                   
   public boolean onOptionsItemSelected(MenuItem item) 
	{
		switch (item.getItemId()) 
		{
		case R.id.maccount:Intent i = new Intent(this, Account.class);
		startActivity(i);
		break;
		case R.id.mallconct:Intent i6 = new Intent(this, AllContacts.class);
		startActivity(i6);
		break;
		case R.id.madd: Intent i1 = new Intent(this, AddContact.class);
		startActivity(i1);
		break;
		case R.id.medit: Intent i2 = new Intent(this, Editcontacts.class);
		startActivity(i2);
		break;
		case R.id.mcall: Intent i4 = new Intent(this, CallList.class);
		startActivity(i4);
		break;
		case R.id.mdelete: Intent i5 = new Intent(this, DeleteContacts.class);
		startActivity(i5);
		break;
		}
		return true;
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
							System.out.println("Id is "+callid1);
							
							Cursor c1=data.validate_group(id1,callid1);
							int count=c1.getCount();
							if(count>0)
							{
								Toast.makeText(Edit_group.this, "Contact already added to this group", Toast.LENGTH_LONG);
							}
							else
							{							
							data.InsertGroupDetails(id1,callid1); 
							}
							
							
							
							System.out.println("iddd... "+id1);
							System.out.println("callid1... "+callid1);		
							//Toast.makeText(Edit_group.this, "Contact id and list id will "+id1+" "+callid1,Toast.LENGTH_SHORT).show();
			  									
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
							Cursor c1=data.validate_group(id1,callid1);
							int count=c1.getCount();
							if(count>0)
							{
								Toast.makeText(Edit_group.this, "Contact already added to this group", Toast.LENGTH_LONG);
							}
							else
							{							
							data.InsertGroupDetails(id1,callid1); 
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
	
	@Override
	public void onBackPressed()
	{
		Intent i=new Intent(Edit_group.this,ViewGroups.class);
		startActivity(i);
		return;
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
			
			tn=es.getText().toString();
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




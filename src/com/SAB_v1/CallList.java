package com.SAB_v1;
import java.util.ArrayList;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
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
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
public class CallList extends Activity 
{	GridView grid_main,grid_main1;
	Button b1,b2,bback,badd;
   DataBaseHelper data;
   String name,callid2;
   String tn="",tn1,time;
   Button bsearch,add;
	EditText es;
    String callid;
	ListView lv1; 
	String ans;
	private ArrayList<String> results = new ArrayList<String>();
	private ArrayList<String> results1 = new ArrayList<String>();
	private ArrayList<String> res = new ArrayList<String>();
	private ArrayList<String> results4 = new ArrayList<String>();
	private ArrayList<String> results5 = new ArrayList<String>();
	@Override
	public void onCreate(Bundle icicle) 
	{	
		
     	super.onCreate(icicle);
		setContentView(R.layout.grid1);          
		data=new DataBaseHelper(this);
		setTitle("CallList");
		Window window = getWindow();
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
       	showtask();
		TextView tv=(TextView)findViewById(R.id.txt1);
		tv.setVisibility(View.GONE);
		if(results.size()==0)
		{	tv.setVisibility(View.VISIBLE);
			tv.setText("Your call List is Empty!!!Press Menu to Create!!");
		}
		grid_main = (GridView)findViewById(R.id.GridView01);
		grid_main.setAdapter(new Adapter(this));
		es=(EditText)findViewById(R.id.esearch);
		add=(Button)findViewById(R.id.add);
		add.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				Intent i=new Intent(CallList.this,DialogforCreate.class);
				startActivity(i);
                       
			}

		});
		bsearch= (Button)findViewById(R.id.search);
		bsearch.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				
				Task task = new Task();
        		task.applicationContext = CallList.this;
        		task.execute();
				

			

			
	    }
		
		

	});
	}
	/*Type :Function
	name:searchtag
	return type:void
	date:10-2-12
	purpose:searches call list*/
	public void searchtag() 
	{
		Cursor c=data.SearchCallList(tn);
		while (c.moveToNext())
		{	
			tn=c.getString(0);
			tn1=c.getString(1);   
			time=c.getString(2);
			System.out.println("CallListName:"+tn);
			System.out.println("Date:"+tn1);
			System.out.println("Time:"+time);
			Cursor c8=data.getcallid(tn);
			while(c8.moveToNext())
			{
				callid2=c8.getString(0);
				System.out.println("Callid is"+callid2);
				results5.add(callid2);
			}
			c8.close();
			results4.add(tn+" "+tn1+","+time);
		}
		c.close();
		
	}
	/*Type :Function
	name:calltag
	return type:void
	date:10-2-12
	purpose:call sets grid view*/
	public void calltag() 
	{
		setContentView(R.layout.gridcalllistsearch);
		grid_main1 = (GridView)findViewById(R.id.GridView01);
		grid_main1.setAdapter(new Adapter1(CallList.this));
	}
	/*Type :Function
	name:showtask
	return type:void
	date:29-6-11
	purpose:To view call list*/
	private void showtask()
	{
		Cursor c=data.getDataCall();
			while (c.moveToNext())
			{	
				callid=c.getString(0);
				name=c.getString(1);
				String date=c.getString(2);
				String time=c.getString(3);
				results.add(name+" "+date+", "+time);
				results1.add(callid);
				res.add(date+", "+time);
			}
      c.close();
		
		
	}
	
	
public boolean onCreateOptionsMenu(android.view.Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu2, menu);
		return true;
	}


	public boolean onOptionsItemSelected(MenuItem item) 
	{
		switch (item.getItemId()) 
		{
		case R.id.addlist:Intent i=new Intent(CallList.this,DialogforCreate.class);
		startActivity(i);
		break;
		case R.id.delete:
			Intent i1=new Intent(CallList.this,DeleteCallList.class);
			startActivity(i1);
		break;
		

		}
		return true;
	}  
	
	
	public class Adapter extends BaseAdapter{
		Context mContext;
		public static final int ACTIVITY_CREATE = 10;
		public Adapter(Context c){
			mContext = c;
		}
		public int getCount() {
			//Cursor c=data.getDataCall();
			int cnt=results.size();
			//c.close();
			return cnt;

		}
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}
		
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}
		
		public View getView(final int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View v;
			
			if(convertView==null){
				LayoutInflater li = getLayoutInflater();
				v = li.inflate(R.layout.iconlist , null);
				 TextView tv= (TextView)v.findViewById(R.id.icon_text);
				//tv.setText(""+results.get(position)+"\n"+res.get(position));
				tv.setText(""+results.get(position));
				tv.setOnClickListener(new TextView.OnClickListener()
				{

					public void onClick(View v) 
					{
						int pos=position;
						String id1=results1.get(pos);
						Intent i= new Intent(CallList.this,DisplayCallList.class);
						Bundle bun=new Bundle();
						bun.putString("id",id1);
						System.out.println("Name isssss........ "+id1);
						i.putExtras(bun);
						startActivity(i);               


						
					}
					
				});
			                     

			}
			else                  
			{
				v = convertView;
			}
			return v;
		}
	}
	public class Adapter1 extends BaseAdapter{
		Context mContext;
		public static final int ACTIVITY_CREATE = 10;
		public Adapter1(Context c){
			mContext = c;
		}
		public int getCount() {
			//Cursor c=data.getDataCall();
			int cnt=results4.size();
			//c.close();
			return cnt;

		}
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}
		
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}
		
		public View getView(final int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View v;
			if(convertView==null){
				LayoutInflater li = getLayoutInflater();
				v = li.inflate(R.layout.iconlist , null);
				TextView tv = (TextView)v.findViewById(R.id.icon_text);
				//tv.setText(""+results.get(position)+"\n"+res.get(position));
				tv.setText(""+results4.get(position));
				tv.setOnClickListener(new TextView.OnClickListener()
				{

					public void onClick(View v) {
						int pos=position;
						String id3=results5.get(pos);
						Intent i= new Intent(CallList.this,DisplayCallList.class);
						Bundle bun=new Bundle();
						bun.putString("id",id3);
						System.out.println("Name isssss........ "+id3);
						i.putExtras(bun);
						startActivity(i);


						
					}
					
				});
			                     

			}
			else                  
			{
				v = convertView;
			}
			return v;
		}
	}
	@Override
	public void onBackPressed()
	{
		Intent i=new Intent(CallList.this,Main.class);
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
			this.dialog = ProgressDialog.show(applicationContext, "Serching Contacts", "Please Wait...", true);
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


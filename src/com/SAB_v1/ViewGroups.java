
package com.SAB_v1;
import java.util.ArrayList;

import com.SAB_v1.AllContacts.ImageAdapter1;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.AdapterView;            
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TabHost.TabSpec;
public class ViewGroups extends Activity 
{	GridView grid_main,grid_main1;
	Button b1,b2,bback,badd;
   DataBaseHelper data;
   String name,callid2;
   String g_id,g_c_id;
   String g_name;
   String tn="",tn1,time;
   Button bsearch,add;
	EditText es;
   private Runnable viewLocation;
   private ProgressDialog m_ProgressDialog = null;
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
		setContentView(R.layout.gridgroup);          
		data=new DataBaseHelper(this);
		setTitle("Groups");
		Window window = getWindow();
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
       	showtask();
		TextView tv=(TextView)findViewById(R.id.txt1);
		tv.setVisibility(View.GONE);
		if(results.size()==0)
		{	tv.setVisibility(View.VISIBLE);
			tv.setText("No Groups!!!Press Menu to Create!!");
		}
		grid_main = (GridView)findViewById(R.id.GridView01);
		grid_main.setAdapter(new Adapter(this));
		
	}
		
     
	/*Type :Function
	name:showtask
	return type:void
	date:29-6-11
	purpose:To view call list*/
	private void showtask()
	{
		Cursor cur=data.getGroup();
		while(cur.moveToNext())
		{
			 g_id=cur.getString(0);
			 g_name=cur.getString(1);
			System.out.println("Gid will..."+g_id);
			System.out.println("Gname will..."+g_name);
			results.add(g_name);
			results1.add(g_id);
		}
		cur.close();
		
		
	}
	
	
public boolean onCreateOptionsMenu(android.view.Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.groupmenu, menu);
		return true;
	}


	public boolean onOptionsItemSelected(MenuItem item) 
	{
		switch (item.getItemId()) 
		{
		case R.id.madd:Intent i=new Intent(ViewGroups.this,Create_group.class);
		startActivity(i);
		break;
		case R.id.mdelete:
			Intent i1=new Intent(ViewGroups.this,DeleteGroup.class);
			
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
				TextView tv = (TextView)v.findViewById(R.id.icon_text);
				tv.setText(""+results.get(position));
				tv.setOnClickListener(new TextView.OnClickListener()
				{

					public void onClick(View v) {
						int pos=position;
						String id1=results1.get(pos);
						Intent i= new Intent(ViewGroups.this,GroupDetails.class);
						Bundle bun=new Bundle();
						bun.putString("gid",id1);
						System.out.println("GName isssss........ "+id1);
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
		Intent i=new Intent(ViewGroups.this,Contacts1.class);
		startActivity(i);
		return;
	}
	}


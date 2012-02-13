
package com.SAB_v1;
import java.util.ArrayList;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
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
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class view_account extends Activity 
{	
	
   GridView grid_main,grid_main1;
   Button b1,b2,bback,badd;
   DataBaseHelper data;
   String name,callid2;
   String tn="",tn1,time;
   Button bsearch,add,delete;
   EditText es;
   CheckBox ch;
   String acc_name,acc_email;
   String callid;
   ListView lv1; 
   String ans,acc_id;
   int id;
   private ArrayList<String> results = new ArrayList<String>();
   private ArrayList<String> results1 = new ArrayList<String>();
	int flag;
	
	@Override
	public void onCreate(Bundle icicle) 
	{	
		
     	super.onCreate(icicle);
    	setContentView(R.layout.view_account_grid);          
		data=new DataBaseHelper(this);
		Window window = getWindow();
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
       	showtask();
		TextView tv=(TextView)findViewById(R.id.txt);
		tv.setVisibility(View.GONE);
		if(results.size()==0)
		{	tv.setVisibility(View.VISIBLE);
			tv.setText("No Account!!!");
		}
		grid_main = (GridView)findViewById(R.id.GridView01);
		grid_main.setAdapter(new Adapter(this));
		add=(Button)findViewById(R.id.add);
		add.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				Intent i=new Intent(view_account.this,Account.class);
				startActivity(i);
                       
			}

		});
		delete=(Button)findViewById(R.id.delete);
		delete.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				Intent i=new Intent(view_account.this,delete_account.class);
				startActivity(i);
                       
			}

		});
				

			
	    }
		
     
	/*Type :Function
	name:showtask
	return type:void
	date:29-6-11
	purpose:To view call list*/
	private void showtask()
	{
		Cursor c=data.viewAccDetails();
			while (c.moveToNext())
			{	
				
				acc_email=c.getString(0);
				acc_id=c.getString(1);
				results.add(acc_email);
				results1.add(acc_id);
			}
      c.close();
		
		
	}
	
	public class Adapter extends BaseAdapter{
		Context mContext;
		public static final int ACTIVITY_CREATE = 10;
		public Adapter(Context c){
			mContext = c;
		}
		public int getCount() 
		{
			//Cursor c=data.getDataCall();
			int cnt=results.size();
			//c.close();
			return cnt;

		}
		public Object getItem(int position) 
		{
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
				v = li.inflate(R.layout.view_account , null);
				TextView tv = (TextView)v.findViewById(R.id.icon_text);
				String ans=results1.get(position);
				System.out.println("Value is "+ans);
				id=Integer.parseInt(ans);
				tv.setText(""+results.get(position));
				ch=(CheckBox)v.findViewById(R.id.chk_icon);
				Cursor c=data.getflag(id);
				while(c.moveToNext())
				{
					flag=c.getInt(0);
					System.out.println("Acc flag will......"+flag);
				}
				c.close();
				if(flag==1)
				{
					ch.setChecked(true);
				}
				
				ch.setOnCheckedChangeListener(new OnCheckedChangeListener() 
			  	{
					
					public void onCheckedChanged(CompoundButton arg0, boolean arg1) 
					{
						System.out.println("Position..."+position);
						String ans=results1.get(position);
						System.out.println("Value is "+ans);
						id=Integer.parseInt(ans);
						System.out.println("Value is............ "+id);
						if(arg1==true)
						{
							System.out.println("Checked.................");
						    data.insertacc_flag(id,1);
						}
						else	
						{

							System.out.println("UnChecked.................");
						    data.insertacc_flag(id,0);
						}
					}          
			  		                                   
			  		                         
			  	});
				/*tv.setOnClickListener(new TextView.OnClickListener()
				{

					public void onClick(View v) 
					{   
					   
						Intent i= new Intent(view_account.this,editAccount.class);
						Bundle bun=new Bundle();
						bun.putInt("acc",id);
						
						i.putExtras(bun);
						startActivity(i);    
					}
                                                
				});*/
				                  

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
		Intent i=new Intent(view_account.this,AccountSettings.class);
		startActivity(i);
		return;
	}
	}


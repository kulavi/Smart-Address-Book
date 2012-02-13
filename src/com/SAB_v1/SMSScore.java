// views contacts 
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
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import android.widget.TextView;
public class SMSScore extends Activity 
{	
                
	GridView grid_main;
	DataBaseHelper data;
	String name;
	int id;
	ImageView iv1;
	private ArrayList<String> results = new ArrayList<String>();
	private ArrayList<String> results3 = new ArrayList<String>();
     ArrayAdapter<String> adapter;
	private String count1;
	private String fname;
	private String lname;
	private String numh;
	private String numw,num;
	private String numo;
	   @Override
	public void onCreate(Bundle icicle)
	{                       
		super.onCreate(icicle);
		data=new DataBaseHelper(this);          
		setContentView(R.layout.commngrid);
		setTitle("SMS Score");
		Window window = getWindow();
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    	grid_main = (GridView)findViewById(R.id.GridView01);
        grid_main.setAdapter(new ImageAdapter(this));
		showcontacts();
	}
    
	private void showcontacts()             
	{
		Cursor c=data.getsmsscore();
		while (c.moveToNext())
		{	
			id=c.getInt(0);
			count1=c.getString(1);
			System.out.println(""+id+" "+count1);
			Cursor c1=data.getnameandnum(id);
			while(c1.moveToNext())
			{
				fname=c1.getString(0);
				lname=c1.getString(1);
				num=c1.getString(2);
				numh=c1.getString(3);
				numw=c1.getString(4);
				numo=c1.getString(5);
				System.out.println(" Num"+num+" NumH"+numh+" NumW"+numw+" NumO"+numo);
				if(fname.equals(""))
				{
					name="No Name";
				}
				else if(lname.equals(""))
				{
					name=fname;
				}
				name=fname+" "+lname;
				if(num.equals(""))
				{
					num=numh;
					if(numh.equals(""))
					{
						num=numw;
						 if(numw.equals(""))
							{
								num=numo;
								System.out.println("Num"+num);  
							}
						System.out.println("Num"+num);  
					}
				
					System.out.println("Num"+num);  
				}
				System.out.println("Num"+num);  
				results.add(name+"\n"+num);
			}
			
			c1.close();
			results3.add(count1+" "+"SMS");
					 
		}
       c.close();
      
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
			int cnt1=results.size();
			 
			return cnt1;
		} 
		/*Type :Function
		name:checkvalidate
		return type:void
		date:29-6-11
		purpose:To get the image path from phone contact and set that image*/
		public View getView(final int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View v;
		
				LayoutInflater li = getLayoutInflater();
				v = li.inflate(R.layout.commnscoreicon, null);
				TextView tv = (TextView)v.findViewById(R.id.icon_text);
				tv.setText(""+results.get(position));
				TextView tv1 = (TextView)v.findViewById(R.id.icon_text1);
				tv1.setText(""+results3.get(position));	
				iv1 = (ImageView)v.findViewById(R.id.con_image);
		  		iv1.setImageResource(R.drawable.propic);
				
		
			
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
		Intent i=new Intent(SMSScore.this,AllContacts.class);
		startActivity(i);
		return;
	}
	}
	


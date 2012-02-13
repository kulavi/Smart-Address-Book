package com.SAB_v1;
import java.util.ArrayList;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
public class AddAccount extends Activity 
{	/*GridView grid_main,grid_main1;
	   DataBaseHelper data;
  
	private ArrayList<String> results = new ArrayList<String>();

	
	@Override
	public void onCreate(Bundle icicle)  
	{	
		
     	super.onCreate(icicle);
		setContentView(R.layout.accountgrid);          
		data=new DataBaseHelper(this);
		setTitle("Add Account");
		Window window = getWindow();
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
       	grid_main = (GridView)findViewById(R.id.GridView01);
		grid_main.setAdapter(new Adapter(this));
		results.add("FaceBook");
		results.add("Twitter");
		
	        
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
				v = li.inflate(R.layout.accounticon , null);
				final TextView tv = (TextView)v.findViewById(R.id.icon_text);
				tv.setText(""+results.get(position));
				tv.setOnClickListener(new TextView.OnClickListener()
				{

					public void onClick(View v)
					{
						String pos=results.get(position);
						if(pos=="FaceBook")
						{
							facebook();
						}
						else
							if(pos=="Twitter")
							{
								twitter();
							}
					}
					
				});
				//ImageView iv1 = (ImageView)v.findViewById(R.id.con_image);
				String pos=results.get(position);
				if(pos=="FaceBook")
				{
					//iv1.setImageResource(R.drawable.facebook);
				}
				else
					if(pos=="Twitter")
					{
						//iv1.setImageResource(R.drawable.twitter);
					}	
				             

			}
			else                  
			{
				v = convertView;
			}
			return v;
		}
	}
	
	public void facebook()
	{
		Intent i =new Intent(this,FacebookLogin.class);
		startActivity(i);
	}
	public void twitter()
	{
		Intent i1 =new Intent(this,TwitterLogin.class);
		startActivity(i1);
	}
	@Override
	public void onBackPressed()
	{
		Intent i=new Intent(AddAccount.this,Main.class);
		startActivity(i);
		return;
	}*/
	}


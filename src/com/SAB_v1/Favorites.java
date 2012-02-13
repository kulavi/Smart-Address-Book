// views contacts 
package com.SAB_v1;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;

import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;

import android.widget.TextView;
public class Favorites extends Activity 
{	
	TelephonyManager tm;	                  
	GridView grid_main,grid_main1;
	DataBaseHelper data;
	String name,name1,name2,sp,num,image,cid,cid1,cid2,image1;
	Button bsearch,add;
	EditText es;
	int cnt=0;
	String tn="";
	 boolean called = false;
	String tn1,mno;
	InputStream is = null;
	 private String u_id;
	int flag;
	String id,fn,ln,cid8; 
	final static String LOG_TAG = "PocketMagic";  
	TextView txt1,txt2,txt3,tv;                      
	int increment;
	int maximum;                      
	String num1;
	ImageView iv1;
	int f_id,f_cid,f_flag;
	int count;
	private ArrayList<String> results = new ArrayList<String>();
	private ArrayList<String> results1 = new ArrayList<String>();
	private ArrayList<String> results3 = new ArrayList<String>();
	private ArrayList<String> results2 = new ArrayList<String>();
	ArrayAdapter<String> adapter;
	private String numh;
	private String numw;
	private String numo;
    @Override
	public void onCreate(Bundle icicle)
	{                       
		super.onCreate(icicle);
		data=new DataBaseHelper(this);          
		setContentView(R.layout.gridsearch);
		setTitle("Favorites");
		Window window = getWindow();
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    	grid_main = (GridView)findViewById(R.id.GridView01);
		grid_main.setAdapter(new ImageAdapter(this));
		tv=(TextView) findViewById(R.id.txt);
		tv.setTextColor(Color.BLACK);
		tv.setVisibility(View.GONE);
		showfavorites();
		if(results.size()==0)   
		{	tv.setVisibility(View.VISIBLE);
			tv.setText("No Favorites!!! Press Menu to Add");
		}		
		Cursor c1=data.get_sync_login_settings_u_id();
		while(c1.moveToNext())
		{
			u_id=c1.getString(0);
			System.out.println(""+u_id);
		}
		c1.close();
	}

    /*type :function
  	name:showfavorites
  	return type:void          
	date:10-2-12
  	purpose:get favorites from database*/
	private void showfavorites()             
	{
		Cursor c=data.get_favorites();
		cnt=c.getCount();
		while (c.moveToNext())
		{
			f_id=c.getInt(0);
			f_cid=c.getInt(1);
			System.out.println("fid will.."+f_id);
			System.out.println("fcid will.."+f_cid);
			cid=""+f_cid;
			Cursor c1=data.getcontacts(f_cid);
			while(c1.moveToNext())
			{
				 name=c1.getString(0);
	        	 name1=c1.getString(1);
	        	 num=c1.getString(2);
	        	 image=c1.getString(3);
	        	 numh=c1.getString(4);
	        	 numw=c1.getString(5);
	        	 numo=c1.getString(6);
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
	        	 System.out.println("Fname and Lname and Num "+name+" "+name1+" "+num);
	        	 results.add(name+" "+name1+"\n"+num);
	        	 results1.add(num);
	        	 results3.add(image);
			}
			c1.close();
			results2.add(cid);
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
			int cnt1=results2.size();
			 
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
				v = li.inflate(R.layout.icon, null);
				TextView tv = (TextView)v.findViewById(R.id.icon_text);
				tv.setText(""+results.get(position));
				
				System.out.println("name=="+results.get(position));
				tv.setOnClickListener(new TextView.OnClickListener()
				{

					public void onClick(View v) {                           
						int pos=position;                 

						String id=results2.get(pos);
						System.out.println("Position... nameeeeeeee"+position+" iddd"+id);
						Intent i= new Intent(Favorites.this,ContactDetails.class);
						Bundle bun=new Bundle();
						bun.putString("name2",id);
						i.putExtras(bun);
						startActivity(i);
					}
                                                   
				});
				iv1 = (ImageView)v.findViewById(R.id.con_image);
				  String img=results3.get(position);
				  	if(img!=null)
				  	{
				  		Bitmap bm = BitmapFactory.decodeFile(img);
						iv1.setImageBitmap(bm); 
				  	}
				  	else{
				  		iv1.setImageResource(R.drawable.propic);
				  	}
				ImageView iv = (ImageView)v.findViewById(R.id.icon_image);
				iv.setImageResource(R.drawable.call1);
		
				iv.setOnClickListener(new MyOnClickListener(position)); 
				
			
			return v;
		}
		public Object getItem(int arg0) {
    
			return null;
		}
		public long getItemId(int arg0) {

			return 0;
		}
	
}
	class MyOnClickListener implements OnClickListener  
	{  
		private final int position;  

		public MyOnClickListener(int position)  
		{  
			this.position = position;  

		}                     

		public void onClick(View v)  
		{  

			int pos=position;                
			num1=results1.get(pos);
			System.out.println("position"+pos+"name=="+num1);
			Cursor c6=data.get_CID(num1);
			while(c6.moveToNext())
			{
				cid2=c6.getString(0);
				
			}
			c6.close();
			Intent intent = new Intent(Intent.ACTION_CALL);
			intent.setData(Uri.parse("tel:" +num1));
			startActivity(intent);
			tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
			tm.listen(mPhoneListener, PhoneStateListener.LISTEN_CALL_STATE);
			
		}               
	}  
	private PhoneStateListener mPhoneListener = new PhoneStateListener() 
	{
		public void onCallStateChanged(int state, String incomingNumber) 
		{
			 if (called && state == TelephonyManager.CALL_STATE_IDLE) 
			 {
	                called = false;
	                tm.listen(this, PhoneStateListener.LISTEN_NONE);
	                try {
	                    Favorites.this.finish();
	                    ArrayList<NameValuePair> nv = new ArrayList<NameValuePair>();
	                    nv.add(new BasicNameValuePair("u_id",u_id));
		        		nv.add(new BasicNameValuePair("num",num1));
      	        		//http post
		        		try
		        		{
		        			HttpClient httpclient = new DefaultHttpClient();
		        			HttpPost httppost = new HttpPost("http://bpsi.us/blueplanetsolutions/stlist/delete_call.php");
		        			httppost.setEntity(new UrlEncodedFormEntity(nv));
		        			HttpResponse response = httpclient.execute(httppost);
		        			HttpEntity entity = response.getEntity();
		        			is = entity.getContent();
		        		}
		        		catch(Exception e)
		        		{
		        			e.printStackTrace();
		        		}
	                    Cursor c9=data.gettsettings();
						while(c9.moveToNext())
						{
							flag=c9.getInt(0);
							System.out.println("Flag will......."+flag);
						}
						c9.close();
						if(flag==1)
						{
						Intent i = new Intent(Favorites.this,UpdateStatus.class);
						Bundle bun=new Bundle();
						bun.putString("name",cid2);
						System.out.println("Name "+cid2);
						i.putExtras(bun);
						i.setAction(Intent.ACTION_MAIN);
						startActivity(i);
						}
						else
						{
							Intent i=new Intent(Favorites.this,Favorites.class);
							i.setAction(Intent.ACTION_MAIN);
							startActivity(i);
						}
	                   
	                    
	                    
	                } catch (Exception e)
	                {
	                    e.printStackTrace();
	                }
	            } else 
	            {
	                if (state == TelephonyManager.CALL_STATE_OFFHOOK)
	                {
	                    called = true;
	                ArrayList<NameValuePair> nv = new ArrayList<NameValuePair>();
	        		nv.add(new BasicNameValuePair("u_id",u_id));
	        		nv.add(new BasicNameValuePair("num",num1));
	        		
	        		
	        		//http post
	        		try
	        		{
	        			HttpClient httpclient = new DefaultHttpClient();
	        			HttpPost httppost = new HttpPost("http://bpsi.us/blueplanetsolutions/stlist/insert_call.php");
	        			httppost.setEntity(new UrlEncodedFormEntity(nv));
	        			HttpResponse response = httpclient.execute(httppost);
	        			HttpEntity entity = response.getEntity();
	        			is = entity.getContent();
	        		}
	        		catch(Exception e)
	        		{
	        			e.printStackTrace();
	        		}
	                }
	            }
	        
					
				
				}
			
	};
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.task_menu, menu);
		return true;
	}
	//Call1.class

	public boolean onOptionsItemSelected(MenuItem item) 
	{
		switch (item.getItemId()) 
		{
		case R.id.add:Intent i = new Intent(this, AddFavorites.class);
		startActivity(i);
		break;
		case R.id.delete:Intent i2 = new Intent(this, DeleteFavorites.class);
		startActivity(i2); 
		break;
		}
		return true;
	}      
	@Override
	public void onBackPressed()
	{
		Intent i=new Intent(Favorites.this,Contacts1.class);
		startActivity(i);
		return;
	}
	
		

	
}
	
	


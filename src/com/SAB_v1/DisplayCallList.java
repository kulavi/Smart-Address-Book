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
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
public class DisplayCallList extends Activity 
{	
	TelephonyManager tm;	
	GridView grid_main;
	DataBaseHelper data;
	Button bback,badd; 
	ProgressDialog dialog;        
	String name,name2,sp,num,img,cont;
	Button bsearch,add;
	EditText es;
	int cnt=0,id6,cont_id;
	String tn="";
	String num1;
	String tn1,mno,cid;              
	String id,id4,cflag;
	final static String LOG_TAG = "PocketMagic";
	TextView txt1,txt2,txt3,title,tv;       
		int callid,id1,id2;
	String ans1,listname;
	int flag;
	String id3,cid1,cid2;
	private String u_id;
	InputStream is = null;
	boolean called=false;
	String name1,n,n1,oname;
	private ArrayList<String> results = new ArrayList<String>();
	private ArrayList<String> results1 = new ArrayList<String>();
	private ArrayList<String> results3 = new ArrayList<String>();
	private ArrayList<String> results4 = new ArrayList<String>();
	private ArrayList<String> results5 = new ArrayList<String>();
	ArrayAdapter<String> adapter;
	String nam,nam1,ans;
	private String lid1;
	private String numh;
	private String numw;
	private String numo;


	@Override
	public void onCreate(Bundle icicle)
	{                       
		super.onCreate(icicle);
		data=new DataBaseHelper(this);
		setContentView(R.layout.displaygrid);
		Bundle bundle = getIntent().getExtras(); 
		name1=bundle.getString("id");
		lid1=bundle.getString("lid");
		System.out.println("My name is "+name1+" "+lid1);
		if(name1==null)
		{
			name1=lid1;
		}
		System.out.println("My name is............ "+name1+" "+lid1);
	    id6=Integer.parseInt(name1);
	    System.out.println("ID is "+id6);
		Window window = getWindow();
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        //get list name from database
        Cursor c=data.getlistname(id6);
        while(c.moveToNext())
        {
        	listname=c.getString(0);
        	System.out.println("Lname "+listname);
        }c.close();
        tv=(TextView)findViewById(R.id.txt1);
    	tv.setVisibility(View.GONE);
        setTitle(listname);
		showtask();
		if(results.size()==0)   
		{	tv.setVisibility(View.VISIBLE);   
			tv.setText("No Contacts!!!Press Menu to Add..");
		}
		grid_main = (GridView)findViewById(R.id.GridView01);
		grid_main.setAdapter(new ImageAdapter(this));
		Cursor c1=data.get_sync_login_settings_u_id();
		while(c1.moveToNext())
		{
			u_id=c1.getString(0);
			System.out.println(""+u_id);
		}
		c1.close();
	}
	 /*type :function
  	name:show task
  	return type:void          
	date:10-2-12
  	purpose:get call list details from database*/
  	private void showtask()  
	{ 
		
		Cursor c=data.getconid_calllist(id6);
		System.out.println("ID1 "+id6);
		while (c.moveToNext())
		{	
         cont=c.getString(0);
         cflag=c.getString(1);
         System.out.println("CId is "+cont);   
         cont_id=Integer.parseInt(cont);
         System.out.println("CId is "+cont_id);
         Cursor c1=data.getcontacts(cont_id);
         while(c1.moveToNext())
         {
        	 n=c1.getString(0);
        	 n1=c1.getString(1);
        	 num=c1.getString(2);
        	 img=c1.getString(3);
        	 numh=c1.getString(4);
        	 numw=c1.getString(5);
        	 numo=c1.getString(6);
        	 System.out.println("Fname and Lname and Num "+n+" "+n1+" "+num);
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
        	 results.add(n+" "+n1);
        	 results1.add(num);
        	 results3.add(img);
        	 
         }
         c1.close();
         results4.add(cont);
         results5.add(cflag);
		}
		c.close();
	}	
	
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.calllistmenu, menu);
		return true;
	} 


	public boolean onOptionsItemSelected(MenuItem item) 
	{
		switch (item.getItemId()) 
		{
		case R.id.cmedit:
		Intent newActivity = new Intent(DisplayCallList.this,EditList.class);
		Bundle bun=new Bundle();
		bun.putString("listname",name1);
		newActivity.putExtras(bun);
		startActivity(newActivity);  
		break;
		case R.id.cmdelete:Intent i1=new Intent(DisplayCallList.this,DeleteList.class);
			Bundle bun2=new Bundle();
			bun2.putString("name2",name1);
			System.out.println("Name "+name);
			i1.putExtras(bun2);
			startActivity(i1);
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
		public int getCount() 
		{
			int cnt=results4.size();
			return cnt;
			
		}
		public View getView(final int position, View convertView, ViewGroup parent) 
		{
			// TODO Auto-generated method stub    
			View v;
			
		
				LayoutInflater li = getLayoutInflater();
				v = li.inflate(R.layout.icon2, null);
				TextView tv = (TextView)v.findViewById(R.id.icon_text);
				tv.setText(""+results.get(position)+"\n "+results1.get(position));
				String flag=results5.get(position);
				int c_flag=Integer.parseInt(flag);
				tv.setOnClickListener(new TextView.OnClickListener()
				{

					public void onClick(View v) 
					{   
					    int pos=position;                 
						String id4=results4.get(pos);
						System.out.println("Position... nameeeeeeee"+position+" iddd"+id4);
						Intent i= new Intent(DisplayCallList.this,CallListContactDetails.class);
						Bundle bun=new Bundle();
						bun.putString("name2",id4);
						bun.putString("name3",name1);
						i.putExtras(bun);
						startActivity(i);    
					}
                                                
				});
				ImageView iv = (ImageView)v.findViewById(R.id.icon_image);
				iv.setImageResource(R.drawable.call1);
				iv.setOnClickListener(new MyOnClickListener(position)); 

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
				ImageView iv2= (ImageView)v.findViewById(R.id.check);
				System.out.println("cflag=="+c_flag);
				if(c_flag==1)
				{
					iv2.setImageResource(R.drawable.tick);
				}
				
				                           
			
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
			System.out.println("position"+pos+"no=="+num1);
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
	                try 
	                {
	                	data.updatecallflag(id6,cid2);
	                    DisplayCallList.this.finish();
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
						Intent i = new Intent(DisplayCallList.this,UpdateStatusCallList.class);
						Bundle bun=new Bundle();
						bun.putString("name",cid2);
						System.out.println("Name "+cid2);
						bun.putString("name3",name1);
						bun.putString("num",num1);
						System.out.println("LISTID will is "+name1);
					    i.putExtras(bun);
						i.setAction(Intent.ACTION_MAIN);
						startActivity(i);
						}
						else
						{
							Intent i=new Intent(DisplayCallList.this,DisplayCallList.class);
							i.setAction(Intent.ACTION_MAIN);
							Bundle bun1=new Bundle();
							bun1.putString("id",name1);
							System.out.println("Name isssss........ "+name1);
							i.putExtras(bun1);
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
	

	@Override
	public void onBackPressed()
	{
		Intent i=new Intent(DisplayCallList.this,CallList.class);
		startActivity(i);
		return;
	}
}

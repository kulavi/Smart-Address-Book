package com.SAB_v1;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class ManualSync_Saved_uid extends Activity 
{

	Button yes,no;
	String notes1;
	DataBaseHelper db1;
	String tag_type,tags_type,images;
	String tname,town,tdesp,tprio,tsdate,tddate,ttime,con,loc,ttype,tcat;
	String tname1,town1,tdesp1,tprio1,tsdate1,tddate1,ttime1;
	String listname,listdate,listid,callcdate,callcfn,callcln,callcmob;
	int ttype1,tcat1,c_id,t_id,n_id,nflag,con1;
	String loc1;
	String elog_id,econ_id,ecnt,slog_id,scon_id,scnt,notes,nname,cn_id;
	String fname,lname,mobno,email,image,tags,mname1,mname;
	String ufname,ulname,umobno,uemail,uimage,utags,umname1,umname;
	String fname1,lname1,mobno1,email1,image1,tags1,log_id,con_id,cnt;
	String ufname1,ulname1,umobno1,uemail1,uimage1,utags1,ulog_id,ucon_id,ucnt;
	String wadd,cmpny,jobtitle,compadd,nicname,padd,birth,ani;
	private RadioButton t,f;
	String num,userid;
	String logtype;
	String name,note1,date1,time1,no1;
	int con2,notes_flag;
	int callcid;
	int intuserid;
	String c_id1,m_cid1;
	int f_id,f_cid,f_flag;
	int ta_id,tflag;
	String callid1;
	String favname,favname1,favnum,favimage,favcid,favnumh,favnumw,favnumo;
	String u_id;
	String mobnoh,mobnow,mobnoo,mobnocust,emailw,emailo,emailcust;
	String umobnoh,umobnow,umobnoo,umobnocust,uemailw,uemailo,uemailcust,utags_type,uimages;
	String result = "";
	String temp="",listtime;
	int type=0;
	InputStream is = null;
	int mMinute,mHour;
	String sdate,stime;
	String smssender,inmessage,smsdate;
	String smsreceiver,sentmessage,smsrecdate,smsrectime,smsrtime;
	private String smsrid;
	private String smssid;
String newcondate,newcontime,newconnotes;
CheckBox chk;
private int log_flag;
private int sms_flag;
private int con_flag;
private int task_flag;
private int call_flag;
private int sms_flag1;
private Button done;
private Button cancel;
private TextView txt1;
private String emailid;

	@Override
	public void onCreate(Bundle icicle)
	{	
		db1=new DataBaseHelper(this);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(icicle);
		isOnline();
		Bundle bundle = getIntent().getExtras(); 
		type= bundle.getInt("type");
		setContentView(R.layout.sync);
		Window window = getWindow();
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        Cursor c12=db1.get_sync_login_settings_details();
    	while(c12.moveToNext())
    	{
    		emailid=c12.getString(0);
    		System.out.println("email "+emailid);
    		u_id=c12.getString(2);
    	}
    	c12.close();
		t=(RadioButton)findViewById(R.id.tserver);
		f=(RadioButton)findViewById(R.id.fserver);
		done=(Button)findViewById(R.id.done);
		cancel=(Button)findViewById(R.id.cancel);
		txt1=(TextView)findViewById(R.id.txt);
		done.setOnClickListener(new Button.OnClickListener() 
		{ 
			public void onClick (View v)
			{ 
				
				Task task = new Task();
        		task.applicationContext = ManualSync_Saved_uid.this;
        		task.execute();
			}


		});
		cancel.setOnClickListener(new Button.OnClickListener() 
		{ 
			public void onClick (View v)
			{ 
				
				finish();
			}    


		});

   }
	 /*type :function
  	name:isOnline
  	return type:void          
	date:10-2-12
  	purpose:checks whether internet is available*/
	public boolean isOnline() 
	{
		ConnectivityManager conMgr = (ConnectivityManager) getSystemService (Context.CONNECTIVITY_SERVICE);
		   if (conMgr.getActiveNetworkInfo() != null && conMgr.getActiveNetworkInfo().isAvailable() &&    conMgr.getActiveNetworkInfo().isConnected()) {
			   System.out.println("connected");
		         return true;
		   } else {
			   Toast.makeText(this, "Please turn on your internet",Toast.LENGTH_LONG).show();
			   
		       return false;
		   }
	    
	}
	public  class Task extends AsyncTask<String, Integer, Void> {
		private  ProgressDialog dialog;
		protected Context applicationContext;

		@Override
		protected void onPreExecute()
		{
			
			System.out.println("IN PreExecute");
			this.dialog = ProgressDialog.show(applicationContext, "Syncing Data", "Please Wait...", true);
		}
		@Override
		protected Void doInBackground(String... params)
		{
			// TODO Auto-generated method stub
			System.out.println("IN BACKGROUND");
			sync1();			//return flag1;
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
			txt1.setText("Sync Successfull!!!");
				
			 
		}

		

		
	}
	
	/*Type :Function
	name:sync1
	return type:void
	date:29-6-11
	purpose:To sync contacts with and from server*/
	
	
	protected void sync1() 
	{

		if(t.isChecked()==true)
		{
			Cursor cur_set=db1.get_sync_log_flag();
			while(cur_set.moveToNext())
			{
				log_flag=cur_set.getInt(0);
			}
			cur_set.close();
			if(log_flag==1)
			{
			Cursor c_4= db1.getcal_log();
			while(c_4.moveToNext())
			{	

					
					con_id=	c_4.getString(0);
					String date = c_4.getString(1);
					String time=c_4.getString(2);
					String duration=c_4.getString(3);
					num=c_4.getString(4);		
					logtype=c_4.getString(5);
					System.out.println("con..."+con_id);
					System.out.println("date..."+date);
					System.out.println("time..."+time);
					System.out.println("dur..."+duration);
					System.out.println("no..."+num);
					System.out.println("logtype..."+logtype);
				ArrayList<NameValuePair> nv = new ArrayList<NameValuePair>();
				nv.add(new BasicNameValuePair("con_id",con_id));
				nv.add(new BasicNameValuePair("date",date));
				nv.add(new BasicNameValuePair("time",time));
				nv.add(new BasicNameValuePair("duration",duration));
				nv.add(new BasicNameValuePair("mob",num));
				nv.add(new BasicNameValuePair("logtype",logtype));
				nv.add(new BasicNameValuePair("u_id",u_id));
				
				//http post
				try
				{
					HttpClient httpclient = new DefaultHttpClient();
					HttpPost httppost = new HttpPost("http://bpsi.us/blueplanetsolutions/stlist/insertclog.php");
					httppost.setEntity(new UrlEncodedFormEntity(nv));
					HttpResponse response = httpclient.execute(httppost);
					HttpEntity entity = response.getEntity();
					is = entity.getContent();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
		}	c_4.close();	  
			}
          Cursor c_48=db1.get_favorites();
  		while (c_48.moveToNext())
  		{
  			f_id=c_48.getInt(0);
  			f_cid=c_48.getInt(1);
  			System.out.println("fid will.."+f_id);
  			System.out.println("fcid will.."+f_cid);
  			favcid=""+f_cid;
  			Cursor c_18=db1.getcontactsfav(f_cid);
  			while(c_18.moveToNext())
  			{  
  				 favname=c_18.getString(0);
  	        	 favname1=c_18.getString(1);
  	        	 favnum=c_18.getString(2);
  	        	favnumh=c_18.getString(3);  
  	        	favnumw=c_18.getString(4);
  	        	favnumo=c_18.getString(5);
  	          	 System.out.println("Fname and Lname and Num "+favname+" "+favname1+" "+favnum+" "+favnumh+" "+favnumw+""+favnumo);
  	        	ArrayList<NameValuePair> nv = new ArrayList<NameValuePair>();
				nv.add(new BasicNameValuePair("favfname",favname));
				nv.add(new BasicNameValuePair("favlname",favname1));
				nv.add(new BasicNameValuePair("favmobno",favnum));
				nv.add(new BasicNameValuePair("favmobnoh",favnumh));
				nv.add(new BasicNameValuePair("favmobnow",favnumw));
				nv.add(new BasicNameValuePair("favmobnoo",favnumo));
				nv.add(new BasicNameValuePair("u_id",u_id));
				//http post
				try
				{
					HttpClient httpclient = new DefaultHttpClient();
					HttpPost httppost = new HttpPost("http://bpsi.us/blueplanetsolutions/stlist/insertfav.php");
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
  			c_18.close();
  			db1.updateflagfav(f_id);
  		}
  		c_48.close();
         
  		Cursor cur_set1=db1.get_sync_sms_flag();
		while(cur_set1.moveToNext())
		{
			sms_flag=cur_set1.getInt(0);
		}
		cur_set1.close();
		if(sms_flag==1)
		{
  		Cursor c_41= db1.getinbox_sms();
		  while(c_41.moveToNext())
		  {	
            
				smssender=	c_41.getString(0);
				inmessage=c_41.getString(1);
				smsdate=c_41.getString(2);
				smsrtime=c_41.getString(3);
				smsrid=c_41.getString(4);
				System.out.println("Sender..."+smssender);
				System.out.println("inboxmessage.."+inmessage);
				System.out.println("SMS Date..."+smsdate);
				System.out.println("SMS Time..."+smsrtime);
				System.out.println("SMS inbox id..."+smsrid);
			ArrayList<NameValuePair> nv = new ArrayList<NameValuePair>();
			nv.add(new BasicNameValuePair("sender",smssender));
			nv.add(new BasicNameValuePair("message",inmessage));
			nv.add(new BasicNameValuePair("date",smsdate));
			nv.add(new BasicNameValuePair("time",smsrtime));
			nv.add(new BasicNameValuePair("smsrid",smsrid));
			nv.add(new BasicNameValuePair("u_id",u_id));
			
			//http post
			try
			{
				HttpClient httpclient = new DefaultHttpClient();
				HttpPost httppost = new HttpPost("http://bpsi.us/blueplanetsolutions/stlist/insert_inbox1.php");
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
		  c_41.close();	  
		
		}
		Cursor cur_set6=db1.get_sync_sms_flag();
		while(cur_set6.moveToNext())
		{
			sms_flag1=cur_set6.getInt(0);
		}
		cur_set6.close();
		if(sms_flag1==1)
		{
           Cursor c_411= db1.getoutbox_sms();
			  while(c_411.moveToNext())
			  {	
                
					smsreceiver=	c_411.getString(0);
					sentmessage=c_411.getString(1);
					smsrecdate=c_411.getString(2);
					smsrectime=c_411.getString(3);
					smssid=c_411.getString(4);
					System.out.println("Receiver..."+smsreceiver);
					System.out.println("sentmessage.."+sentmessage);
					System.out.println("SMS sent Date..."+smsrecdate);
					System.out.println("SMS sent Date..."+smsrectime);
					System.out.println("SMS sent Id..."+smssid);
				ArrayList<NameValuePair> nv = new ArrayList<NameValuePair>();
				nv.add(new BasicNameValuePair("receiver",smsreceiver));
				nv.add(new BasicNameValuePair("message",sentmessage));
				nv.add(new BasicNameValuePair("date",smsrecdate));
				nv.add(new BasicNameValuePair("time",smsrectime));
				nv.add(new BasicNameValuePair("smssid",smssid));
				nv.add(new BasicNameValuePair("u_id",u_id));
				
				//http post
				try
				{
					HttpClient httpclient = new DefaultHttpClient();
					HttpPost httppost = new HttpPost("http://bpsi.us/blueplanetsolutions/stlist/insert_sent1.php");
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
			  c_411.close();	  
		}
		
			  Cursor c5= db1.getemail_log();
				while(c5.moveToNext())
				{	

						elog_id  =	c5.getString(0);
						econ_id=	c5.getString(1);
						ecnt =	c5.getString(2);
						System.out.println(elog_id+econ_id+ecnt);
					
						
					ArrayList<NameValuePair> nv = new ArrayList<NameValuePair>();
					nv.add(new BasicNameValuePair("elog_id",elog_id));
					nv.add(new BasicNameValuePair("econ_id",econ_id));
					nv.add(new BasicNameValuePair("ecnt",ecnt));
					nv.add(new BasicNameValuePair("u_id",u_id));
					System.out.println(u_id+elog_id+econ_id+ecnt);
					//http post
					try
					{
						HttpClient httpclient = new DefaultHttpClient();
						HttpPost httppost = new HttpPost("http://bpsi.us/blueplanetsolutions/stlist/insertelog.php");
						httppost.setEntity(new UrlEncodedFormEntity(nv));
						HttpResponse response = httpclient.execute(httppost);
						HttpEntity entity = response.getEntity();
						is = entity.getContent();
					}
					catch(Exception e)
					{
						//text2.setText("Error in http connection");
					}

				
				db1.updateflagelog(elog_id);
			}	
				c5.close();
				Cursor c6= db1.getsms_log();
				while(c6.moveToNext())
				{	

						slog_id  =	c6.getString(0);
						scon_id=	c6.getString(1);
						scnt =	c6.getString(2);
						System.out.println(slog_id+scon_id+scnt);
					
						
					ArrayList<NameValuePair> nv = new ArrayList<NameValuePair>();
					nv.add(new BasicNameValuePair("slog_id",slog_id));
					nv.add(new BasicNameValuePair("scon_id",scon_id));
					nv.add(new BasicNameValuePair("scnt",scnt));
					nv.add(new BasicNameValuePair("u_id",u_id));
					System.out.println(u_id+slog_id+scon_id+scnt);
					//http post
					try
					{
						HttpClient httpclient = new DefaultHttpClient();
						HttpPost httppost = new HttpPost("http://bpsi.us/blueplanetsolutions/stlist/insertslog.php");
						httppost.setEntity(new UrlEncodedFormEntity(nv));
						HttpResponse response = httpclient.execute(httppost);
						HttpEntity entity = response.getEntity();
						is = entity.getContent();
					}
					catch(Exception e)
					{
						//text2.setText("Error in http connection");
					}

				
				db1.updateflagslog(slog_id);
			}		
                c6.close();
				
				
				
          Cursor c11= db1.getuser();
			while(c11.moveToNext())
			{	
				ufname =	c11.getString(0);
				umname=c11.getString(1);
				ulname=	c11.getString(2);
				umobno =	c11.getString(3);
				umobnoh = c11.getString(4);
				umobnow = c11.getString(5);
				umobnoo = c11.getString(6);
				umobnocust = c11.getString(7);
				uemail =	c11.getString(8);
				uemailw = c11.getString(9);
				uemailo = c11.getString(10);
				uemailcust = c11.getString(11);
				uimages=c11.getString(12);
				utags_type = c11.getString(13);
				utags = c11.getString(14);
				userid=c11.getString(15);
				intuserid=Integer.parseInt(userid);
				System.out.println("usercontacts are== --"+ufname+umname+ulname+umobno+umobnoh+umobnow+umobnoo+umobnocust+uemail+uemailw+uemailo+uemailcust+utags);
				ArrayList<NameValuePair> nv = new ArrayList<NameValuePair>();
				nv.add(new BasicNameValuePair("userid",userid));
				nv.add(new BasicNameValuePair("ufname",ufname));  
				nv.add(new BasicNameValuePair("umname",umname));
				nv.add(new BasicNameValuePair("ulname",ulname));
				nv.add(new BasicNameValuePair("umobno",umobno));
				nv.add(new BasicNameValuePair("umobnoh",umobnoh));
				nv.add(new BasicNameValuePair("umobnow",umobnow));
				nv.add(new BasicNameValuePair("umobnoo",umobnoo));
				nv.add(new BasicNameValuePair("umobnocust",umobnocust));
				nv.add(new BasicNameValuePair("uemail",uemail));
				nv.add(new BasicNameValuePair("uemailw",uemailw));
				nv.add(new BasicNameValuePair("uemailo",uemailo));
				nv.add(new BasicNameValuePair("uemailcust",uemailcust));   
				nv.add(new BasicNameValuePair("uimage",uimages));
				nv.add(new BasicNameValuePair("utagtype",utags_type));
				nv.add(new BasicNameValuePair("utags",utags));
				nv.add(new BasicNameValuePair("u_id",u_id));
				//http post
				try
					{
					HttpClient httpclient = new DefaultHttpClient();
					HttpPost httppost = new HttpPost("http://bpsi.us/blueplanetsolutions/stlist/insertuser.php");
					httppost.setEntity(new UrlEncodedFormEntity(nv));
					HttpResponse response = httpclient.execute(httppost);
					HttpEntity entity = response.getEntity();
					is = entity.getContent();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}


				db1.updateflaguser(intuserid);
					
					
		}	c11.close();	 
		Cursor cur_set2=db1.get_sync_con_flag();
		while(cur_set2.moveToNext())
		{
			con_flag=cur_set2.getInt(0);
		}
		cur_set2.close();
		if(con_flag==1)
		{
			Cursor c3=db1.getFlagC();   
			while(c3.moveToNext())
			{	
				int flag=c3.getInt(0);
				int c_id=c3.getInt(1);
				c_id1=""+c_id;
				System.out.println("CID "+c_id1);   
				if(flag==0)
				{
					Cursor c1= db1.getnewcontact(c_id);
					while(c1.moveToNext())
					{	

						fname =	c1.getString(0);
						mname=c1.getString(1);
						lname=	c1.getString(2);
						mobno =	c1.getString(3);
						mobnoh = c1.getString(4);
						mobnow = c1.getString(5);
						mobnoo = c1.getString(6);
						mobnocust = c1.getString(7);
						email =	c1.getString(8);
						emailw = c1.getString(9);
						emailo = c1.getString(10);
						emailcust = c1.getString(11);
						images=c1.getString(12);
						tags_type = c1.getString(13);
						tags = c1.getString(14);
						 newcondate=c1.getString(15);
						 newcontime=c1.getString(16);
						 newconnotes=c1.getString(17);
						//c_id=c1.getInt(7);
						System.out.println("contacts are== --"+fname+mname+lname+mobno+mobnoh+mobnow+mobnoo+mobnocust+email+emailw+emailo+emailcust+tags+newconnotes);
						ArrayList<NameValuePair> nv = new ArrayList<NameValuePair>();
						nv.add(new BasicNameValuePair("c_id",c_id1));
						nv.add(new BasicNameValuePair("fname",fname));
						nv.add(new BasicNameValuePair("mname",mname));
						nv.add(new BasicNameValuePair("lname",lname));
						nv.add(new BasicNameValuePair("mobno",mobno));
						nv.add(new BasicNameValuePair("mobnoh",mobnoh));
						nv.add(new BasicNameValuePair("mobnow",mobnow));
						nv.add(new BasicNameValuePair("mobnoo",mobnoo));
						nv.add(new BasicNameValuePair("mobnocust",mobnocust));
						nv.add(new BasicNameValuePair("email",email));
						nv.add(new BasicNameValuePair("emailw",emailw));
						nv.add(new BasicNameValuePair("emailo",emailo));
						nv.add(new BasicNameValuePair("emailcust",emailcust));   
						nv.add(new BasicNameValuePair("image",images));
						nv.add(new BasicNameValuePair("tagtype",tags_type));
						nv.add(new BasicNameValuePair("tags",tags));
						nv.add(new BasicNameValuePair("date",newcondate));
						nv.add(new BasicNameValuePair("time",newcontime));
						nv.add(new BasicNameValuePair("notes",newconnotes));
						nv.add(new BasicNameValuePair("u_id",u_id));
						//http post
						try
	    					{
							HttpClient httpclient = new DefaultHttpClient();
							HttpPost httppost = new HttpPost("http://bpsi.us/blueplanetsolutions/stlist/insertc.php");
							httppost.setEntity(new UrlEncodedFormEntity(nv));
							HttpResponse response = httpclient.execute(httppost);
							HttpEntity entity = response.getEntity();
							is = entity.getContent();
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}


						db1.updateflagc(c_id);
					}//while
					c1.close();
				}//if
				Cursor c31=db1.getMoreFlagC();
				while(c31.moveToNext())
				{	
					int flag31=c31.getInt(0);
					if(flag31==0)
					{
				Cursor cur= db1.getMoreInfo(c_id);
				while(cur.moveToNext())
				{	
    				wadd = cur.getString(1);
					cmpny= cur.getString(2);
					jobtitle= cur.getString(3);
					System.out.println("Job "+jobtitle);
					compadd = cur.getString(7);
					nicname = cur.getString(8);
					padd =	cur.getString(9);
					birth = cur.getString(13);
					ani = cur.getString(14);
					//c_id=c1.getInt(7);
					System.out.println("contacts more details are== --"+wadd+cmpny+jobtitle+compadd+nicname+padd+birth+ani);
					ArrayList<NameValuePair> nv12 = new ArrayList<NameValuePair>();
					nv12.add(new BasicNameValuePair("c_id",c_id1));
					nv12.add(new BasicNameValuePair("wadd",wadd));
					nv12.add(new BasicNameValuePair("cmpny",cmpny));
					nv12.add(new BasicNameValuePair("compadd",compadd));
					nv12.add(new BasicNameValuePair("jobtitle",jobtitle));
					nv12.add(new BasicNameValuePair("nicname",nicname));
					nv12.add(new BasicNameValuePair("padd",padd));
					nv12.add(new BasicNameValuePair("ani",ani));
					nv12.add(new BasicNameValuePair("birth",birth));
					nv12.add(new BasicNameValuePair("u_id",u_id));
					//http post
					try
    					{
						HttpClient httpclient = new DefaultHttpClient();
						HttpPost httppost = new HttpPost("http://bpsi.us/blueplanetsolutions/stlist/insertmorecd.php");
						httppost.setEntity(new UrlEncodedFormEntity(nv12));
						HttpResponse response = httpclient.execute(httppost);
						HttpEntity entity = response.getEntity();
						is = entity.getContent();
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}


					db1.updateflagcm(c_id);
				}//while
				cur.close();
			}//if
			}//while
				c31.close();
				Cursor c=db1.getnoteflag1(c_id);
				
				while(c.moveToNext())
				{
					n_id=c.getInt(0);
					nflag=c.getInt(1);
					if(nflag==0)
					{
						
						Cursor c1=db1.notesdetails1(c_id);
						while(c1.moveToNext())
						{
						String nname=c1.getString(0);
						String mob=c1.getString(1);
						String note=c1.getString(2);
						String notedate=c1.getString(3);
						String notetime=c1.getString(4);
				
				ArrayList<NameValuePair> nv2 = new ArrayList<NameValuePair>();
				nv2.add(new BasicNameValuePair("c_id",c_id1));
				nv2.add(new BasicNameValuePair("nname",nname));
				nv2.add(new BasicNameValuePair("mobno",mob));
				nv2.add(new BasicNameValuePair("notes",note));
				nv2.add(new BasicNameValuePair("date",notedate));
				nv2.add(new BasicNameValuePair("time",notetime));
				nv2.add(new BasicNameValuePair("u_id",u_id));
				try
				{
					HttpClient httpclient = new DefaultHttpClient();
					HttpPost httppost = new HttpPost("http://bpsi.us/blueplanetsolutions/stlist/insertnotes.php");
					httppost.setEntity(new UrlEncodedFormEntity(nv2));
					HttpResponse response = httpclient.execute(httppost);
					HttpEntity entity = response.getEntity();
					is = entity.getContent();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}


				db1.updateflagnote(n_id);
				}
						c1.close();
				}
			    }
				c.close();
			}
			Cursor cur_set4=db1.get_sync_task_flag();
			while(cur_set4.moveToNext())
			{
				task_flag=cur_set4.getInt(0);
			}
			cur_set4.close();
			if(task_flag==1)
			{
             Cursor c34=db1.getTaskFlag(c_id);
				
				while(c34.moveToNext())
				{
					tflag=c34.getInt(0);
					if(tflag==0)
					{

						Cursor c14=db1.taskdetails(c_id);
						while(c14.moveToNext())
						{
											
						String tasko=c14.getString(0);
						String taskname=c14.getString(1);
						String taskdes=c14.getString(2);
						String taskpri=c14.getString(3);
						String tasksdate=c14.getString(4);
						String taskddate=c14.getString(5);
						String tasktime=c14.getString(6);
System.out.println("task are== --"+tasko+" "+taskname+" "+taskdes+"  "+taskpri+" "+tasksdate+" "+taskddate+" "+tasktime);
				ArrayList<NameValuePair> nv21 = new ArrayList<NameValuePair>();
				nv21.add(new BasicNameValuePair("c_id",c_id1));
				nv21.add(new BasicNameValuePair("tasko",tasko));
				nv21.add(new BasicNameValuePair("taskname",taskname));
				nv21.add(new BasicNameValuePair("taskdes",taskdes));
				nv21.add(new BasicNameValuePair("taskpri",taskpri));
				nv21.add(new BasicNameValuePair("tasksdate",tasksdate));
				nv21.add(new BasicNameValuePair("taskddate",taskddate));
				nv21.add(new BasicNameValuePair("tasktime",tasktime));
				nv21.add(new BasicNameValuePair("u_id",u_id));
				try
				{
					HttpClient httpclient = new DefaultHttpClient();
					HttpPost httppost = new HttpPost("http://bpsi.us/blueplanetsolutions/stlist/inserttask.php");
					httppost.setEntity(new UrlEncodedFormEntity(nv21));
					HttpResponse response = httpclient.execute(httppost);
					HttpEntity entity = response.getEntity();
					is = entity.getContent();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}


				db1.updateflagnote(n_id);
				}
						c14.close();
				}
			    }
				c34.close();
			}//while
			c3.close();
		}
		Cursor cur_set5=db1.get_sync_call_flag();
		while(cur_set5.moveToNext())
		{
			call_flag=cur_set5.getInt(0);
		}
		cur_set5.close();
		if(call_flag==1)
		{
			Cursor cur=db1.getFlaglist();
			while(cur.moveToNext())
			{	
				int flag1=cur.getInt(0);
				int callid=cur.getInt(1);
				callid1=""+callid;
				System.out.println("Lid           "+callid1);
				if(flag1==0)    
				{
					Cursor c4= db1.getlistname(callid);
					while(c4.moveToNext())
					{	

						listname=	c4.getString(0);
						listdate =	c4.getString(1);
						listtime=c4.getString(2);
						System.out.println(listname+listdate+listtime);
						System.out.println("Lid........."+callid1);
						ArrayList<NameValuePair> nv3 = new ArrayList<NameValuePair>();
						nv3.add(new BasicNameValuePair("listname",listname));
						nv3.add(new BasicNameValuePair("listdate",listdate));
						nv3.add(new BasicNameValuePair("listtime",listtime));
						nv3.add(new BasicNameValuePair("lid",callid1));
						nv3.add(new BasicNameValuePair("u_id",u_id));
						try
						{
							HttpClient httpclient = new DefaultHttpClient();
							HttpPost httppost = new HttpPost("http://bpsi.us/blueplanetsolutions/stlist/insertcalllist.php");
							httppost.setEntity(new UrlEncodedFormEntity(nv3));
							HttpResponse response = httpclient.execute(httppost);
		 					HttpEntity entity = response.getEntity();
							is = entity.getContent();       
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
						Cursor c_1=db1.getlistdetails(callid);
						while(c_1.moveToNext())
						{
							callcid=c_1.getInt(0);
							 Cursor c1=db1.getcontacts(callcid);
					         while(c1.moveToNext())
					         {
					        	 callcfn=c1.getString(0);
					        	 callcln=c1.getString(1);
                              Cursor c_11=db1.getphno(callcfn,callcln);
                              while(c_11.moveToNext())
                              {
						String phno=c_11.getString(0);
						System.out.println("Lid1"+callid);
						ArrayList<NameValuePair> nv1 = new ArrayList<NameValuePair>();
						nv1.add(new BasicNameValuePair("listfname",callcfn));
						nv1.add(new BasicNameValuePair("listlname",callcln));
						nv1.add(new BasicNameValuePair("phno",phno));
						nv1.add(new BasicNameValuePair("lid",callid1));
						nv1.add(new BasicNameValuePair("u_id",u_id));
						System.out.println("Lid2"+callid);
						try
						{
							HttpClient httpclient = new DefaultHttpClient();
							HttpPost httppost = new HttpPost("http://bpsi.us/blueplanetsolutions/stlist/insertlistdetails.php");
							httppost.setEntity(new UrlEncodedFormEntity(nv1));
							HttpResponse response = httpclient.execute(httppost);
							HttpEntity entity = response.getEntity();
							is = entity.getContent();
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
                              }c_11.close();
					         }c1.close();

						db1.updateflagl1(callcid);
						}
						c_1.close();   

						db1.updateflagl(callid);
					}c4.close();
					}
				
				
			}cur.close();
			
		}
			
			}
			


           
		else if(f.isChecked()==true)
		{	
			Cursor cur_set4=db1.get_sync_task_flag();
			while(cur_set4.moveToNext())
			{
				task_flag=cur_set4.getInt(0);
			}
			cur_set4.close();
			if(task_flag==1)
			{
			
			try
			{	ArrayList<NameValuePair> nv = new ArrayList<NameValuePair>();
			nv.add(new BasicNameValuePair("u_id",u_id));
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost("http://bpsi.us/blueplanetsolutions/stlist/gettask.php");
			httppost.setEntity(new UrlEncodedFormEntity(nv));
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			//convert response to string
			
			try
			{
				BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
				StringBuilder sb = new StringBuilder();
				String line = null;

				while ((line = reader.readLine()) != null) 
				{
					sb.append(line + "\n");
				}
				is.close();
				result=sb.toString();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			//parse data


			try{


				JSONArray jArray = new JSONArray(result);

				for(int i=0;i<jArray.length();i++)
				{
					JSONObject json_data = jArray.getJSONObject(i);
					town1 = json_data.getString("t_owner");
					tname1 = json_data.getString("t_name");
					tdesp1 = json_data.getString("t_desp");
					tprio1=json_data.getString("t_priority");
					tsdate1=json_data.getString("t_sdate");
					tddate1=json_data.getString("t_ddate");
					ttime1=json_data.getString("t_time");
					con1=json_data.getInt("c_id");
					loc1=json_data.getString("loc");
				System.out.println(town+tname+tdesp+con+loc);
				db1.Inserttask(town1, tname1, tdesp1,tprio1,tsdate1,tddate1,ttime1,con1,loc1);
				
				}
				
			}
			catch(JSONException e)
			{
				e.printStackTrace();
			
			}
			}
			Cursor cur_set2=db1.get_sync_con_flag();
			while(cur_set2.moveToNext())
			{
				con_flag=cur_set2.getInt(0);
			}
			cur_set2.close();
			if(con_flag==1)
			{
			
			try
			{	
	        ArrayList<NameValuePair> nv = new ArrayList<NameValuePair>();
			System.out.println("uidd"+u_id);
			nv.add(new BasicNameValuePair("u_id",u_id));
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost("http://bpsi.us/blueplanetsolutions/stlist/getcontacts.php");
			httppost.setEntity(new UrlEncodedFormEntity(nv));
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			//convert response to string
			try
			{
				BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
				StringBuilder sb = new StringBuilder();
				String line = null;

				while ((line = reader.readLine()) != null) 
				{
					sb.append(line + "\n");
				}
				is.close();
				result=sb.toString();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			try{


				JSONArray jArray = new JSONArray(result); 

				for(int i=0;i<jArray.length();i++)
				{
					JSONObject json_data = jArray.getJSONObject(i);
					fname1= json_data.getString("fname");
					mname1= json_data.getString("mname");
					lname1 = json_data.getString("lname"); 
					mobno1 = json_data.getString("mobno");
					mobnoh = json_data.getString("mobhome");
					mobnow = json_data.getString("mobwork");
					mobnoo = json_data.getString("mobother");
					mobnocust = json_data.getString("mobcust");
					email1=json_data.getString("email");
					emailw=json_data.getString("emailwork");
					emailo=json_data.getString("emailother");
					emailcust=json_data.getString("emailcust");
					image1=json_data.getString("image");
					tag_type=json_data.getString("tagtype");
					tags1=json_data.getString("tags");
					notes1=json_data.getString("notes"); 
					System.out.println(fname1+lname1+mobno1+email1+image1+tags1+notes1);
					db1.Insert(fname1,mname1,lname1,mobno1,mobnoh,mobnow,mobnoo,mobnocust,email1,emailw,emailo,emailcust,image1,tag_type,tags1,0,sdate,stime,notes1,1);
				}
				
				

			}
			catch(JSONException e)
			{
				e.printStackTrace();
				
			}
			}
			Cursor cur_set5=db1.get_sync_task_flag();
			while(cur_set5.moveToNext())
			{
				notes_flag=cur_set5.getInt(0);
			}
			cur_set5.close();
			if(notes_flag==1)
			{
			try
			{	
			ArrayList<NameValuePair> nv = new ArrayList<NameValuePair>();
			nv.add(new BasicNameValuePair("u_id",u_id));
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost("http://bpsi.us/blueplanetsolutions/stlist/getnotes.php");
			httppost.setEntity(new UrlEncodedFormEntity(nv));
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			//convert response to string
			try
			{
				BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
				StringBuilder sb = new StringBuilder();
				String line = null;

				while ((line = reader.readLine()) != null) 
				{
					sb.append(line + "\n");
				}
				is.close();
				result=sb.toString();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			//parse data


			try{


				JSONArray jArray = new JSONArray(result);

				for(int i=0;i<jArray.length();i++)
				{
					JSONObject json_data = jArray.getJSONObject(i);
					name = json_data.getString("n_name");
					no1 = json_data.getString("mobno");
					note1 = json_data.getString("note");
					date1=json_data.getString("date");
					time1=json_data.getString("time");
					con2=json_data.getInt("c_id");
				System.out.println(name+no1+date1+time1+note1+con2);
				db1.Insertnotes_from_server(con2, name, no1,note1,date1,time1,1);
				
				}
				
			}
			catch(JSONException e)
			{
				//text1.setText("Invalid Login!!");
			}
			}
		}
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
	
		case R.id.mcall: Intent i4 = new Intent(this, CallList.class);
		startActivity(i4);
		break;
		case R.id.mdelete: Intent i5 = new Intent(this, DeleteContacts.class);
		startActivity(i5);
		break;
		}
		return true;
	}    

}









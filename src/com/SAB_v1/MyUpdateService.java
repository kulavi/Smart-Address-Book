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
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.IntentService;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.text.format.Time;

public class MyUpdateService extends IntentService
{
	String u_id; 
DataBaseHelper db1;
	
	
	String fname,lname,mobno,email,image,tags,mname1,mname;
	String fname1,lname1,mobno1,email1,image1,tags1,log_id,con_id,cnt;
	int callcid;
	String mobnoh,mobnow,mobnoo,mobnocust,emailw,emailo,emailcust;
	String listname,listdate,listid,callcdate,callcfn,callcln,callcmob;
	String temp="",listtime;
	int type=0,n_id,nflag;
	
	
	String result="";
	
	InputStream is = null;
  public MyUpdateService()
  {
    super(MyUpdateService.class.getSimpleName());
  }

  @Override
  protected void onHandleIntent(Intent intent)
  {
    // Do useful things.
	  Bundle bun=intent.getExtras();
		 u_id=bun.getString("u_id");
		 System.out.println("UID service.."+u_id);
		 final AlertDialog.Builder alertbox=new AlertDialog.Builder(this);
		 alertbox.setMessage("Do you want to sync your contacts to backend?");
         
         // set a positive/yes button and create a listener
         alertbox.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

             // do something when the button is clicked
             public void onClick(DialogInterface arg0, int arg1)
             {
             	Task task = new Task();
         		task.applicationContext =MyUpdateService.this;
         		task.execute();

             	/*progress();
             	Intent i=new Intent(Import.this,AllContacts.class);
 				startActivity(i);*/
             }
         });

         // set a negative/no button and create a listener
         alertbox.setNegativeButton("No", new DialogInterface.OnClickListener() {

             // do something when the button is clicked
             public void onClick(DialogInterface arg0, int arg1) 
             {
             	Intent i=new Intent(MyUpdateService.this,Main.class);
 				startActivity(i);
 			
             }
         });

         // display box
         alertbox.show();
       
			
    // After doing useful things...
    scheduleNextUpdate();
  }

  private void scheduleNextUpdate()
  {
    Intent intent = new Intent(this, this.getClass());
    PendingIntent pendingIntent =
        PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

    // The update frequency should often be user configurable.  This is not.

    long currentTimeMillis = System.currentTimeMillis();
    long nextUpdateTimeMillis = currentTimeMillis + 15 * DateUtils.MINUTE_IN_MILLIS;
    Time nextUpdateTime = new Time();
    nextUpdateTime.set(nextUpdateTimeMillis);

    if (nextUpdateTime.hour < 8 || nextUpdateTime.hour >= 18)
    {
      nextUpdateTime.hour = 8;
      nextUpdateTime.minute = 0;
      nextUpdateTime.second = 0;
      nextUpdateTimeMillis = nextUpdateTime.toMillis(false) + DateUtils.DAY_IN_MILLIS;
    }
    AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
    alarmManager.set(AlarmManager.RTC, nextUpdateTimeMillis, pendingIntent);
  }
  public  class Task extends AsyncTask<String, Integer, Void> {
		private  ProgressDialog dialog;
		protected Context applicationContext;
		
		@Override
		protected void onPreExecute()
		{
			
			System.out.println("IN PreExecute");
			this.dialog = ProgressDialog.show(applicationContext, "Syncing Contacts", "Please Wait...", true);
			 dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

		}
		@Override
		protected Void doInBackground(String... params) {
			// TODO Auto-generated method stub
			System.out.println("IN BACKGROUND");
			sync();			//return flag1;
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
			final AlertDialog.Builder alertbox1=new AlertDialog.Builder(MyUpdateService.this);
			Cursor c=db1.getData();
			int num=c.getCount();
			alertbox1.setMessage(+num+" Sync Successfull");
		     
          // set a positive/yes button and create a listener
          alertbox1.setPositiveButton("OK", new DialogInterface.OnClickListener() {

              // do something when the button is clicked
              public void onClick(DialogInterface arg0, int arg1)
              {
              	
              	Intent i=new Intent(MyUpdateService.this,Main.class);
      			startActivity(i);
              }
          });
          alertbox1.show();
		
					
			 
		}

		

		
	}
	public void sync() {
		Cursor c3=db1.getFlagC();
		while(c3.moveToNext())
		{	
			int flag=c3.getInt(0);
			int c_id=c3.getInt(1);
			if(flag==0)
			{
				Cursor c1= db1.getcontact(c_id);
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
					tags = c1.getString(12);
					//c_id=c1.getInt(7);
					System.out.println("contacts are== --"+fname+mname+lname+mobno+mobnoh+mobnow+mobnoo+mobnocust+email+emailw+emailo+emailcust+tags);
					ArrayList<NameValuePair> nv = new ArrayList<NameValuePair>();
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
					nv.add(new BasicNameValuePair("tags",tags));
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
						//text2.setText("Error in http connection");
					}


					db1.updateflagc(c_id);
				}//while
				c1.close();
			}//if
		}//while
		
		Cursor cur=db1.getFlaglist();
		while(cur.moveToNext())
		{	
			int flag1=cur.getInt(0);
			int callid=cur.getInt(1);
			String callid1=""+callid;
			if(flag1==0)
			{
				Cursor c4= db1.getlistname(callid);
				while(c4.moveToNext())
				{	

					listname=	c4.getString(0);
					listdate =	c4.getString(1);
					listtime=c4.getString(2);
					System.out.println(listname+listdate+listtime);
					ArrayList<NameValuePair> nv3 = new ArrayList<NameValuePair>();
					//nv.add(new BasicNameValuePair("listid",callid1));
					nv3.add(new BasicNameValuePair("listname",listname));
					nv3.add(new BasicNameValuePair("listdate",listdate));
					nv3.add(new BasicNameValuePair("listtime",listtime));
	    				nv3.add(new BasicNameValuePair("u_id",u_id));
					try
					{
						HttpClient httpclient = new DefaultHttpClient();
						HttpPost httppost = new HttpPost("http://bpsi.us/blueplanetsolutions/stlist/insertlist.php");
						httppost.setEntity(new UrlEncodedFormEntity(nv3));
						HttpResponse response = httpclient.execute(httppost);
						HttpEntity entity = response.getEntity();
						is = entity.getContent();       
					}
					catch(Exception e)
					{
						//text2.setText("Error in http connection");
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

					
					ArrayList<NameValuePair> nv1 = new ArrayList<NameValuePair>();
					nv1.add(new BasicNameValuePair("listfname",callcfn));
					nv1.add(new BasicNameValuePair("listlname",callcln));
					nv1.add(new BasicNameValuePair("listid",callid1));
					
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
						//text2.setText("Error in http connection");
					}

				         }
					db1.updateflagl1(callcid);
					}
					c_1.close();

					db1.updateflagl(callid);
				}c4.close();
				}
				}
		Cursor c=db1.getnoteflag();
		
		while(c.moveToNext())
		{
			n_id=c.getInt(0);
			nflag=c.getInt(1);
			if(nflag==0)
			{
				Cursor c1=db1.notesdetails(n_id);
				while(c1.moveToNext())
				{
				String nname=c1.getString(0);
				String note=c1.getString(1);
				
		
		ArrayList<NameValuePair> nv2 = new ArrayList<NameValuePair>();
		nv2.add(new BasicNameValuePair("nname",nname));
		nv2.add(new BasicNameValuePair("notes",note));
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
			//text2.setText("Error in http connection");
		}


		db1.updateflagnote(n_id);
				}
		}
	
		}
	
	
	
		}
			

}
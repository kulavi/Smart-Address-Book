package com.SAB_v1;
import java.util.Calendar;
import java.util.GregorianCalendar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.DialogInterface;
public class Import extends Activity 
{	int increment,maximum,im_id;
	ProgressDialog dialog;
	Button bsearch,bbs,badd,bcall,btask;
	EditText esearch;
	DataBaseHelper data;
	String id,fn,ln; 
	TextView tv;
	String tn="",now,noh,noo,mob;
	String tn1,mno,fname,lname,photoid,email="",emailh="",emailw="",emailo="";
	String []name;
	long date;
	String no1,time,date1,time1;
	int count,j,cnt,cid;
	int c_id; 
	String cid1;
	String duration;
	int logtype;
	int mHour,mMinute;
	String sdate,stime,time2;
	String notes1="";
	String sender,smsdate,msg;
	String receiver,smsrecdate,recmsg;
	String name1,name2,mn,num,numh,numw,numo,type,n,nh,nw,no;
	@Override
	public void onCreate(Bundle icicle)
	{
		requestWindowFeature(Window.FEATURE_NO_TITLE);  
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(icicle);
		setContentView(R.layout.progress);
		tv=(TextView)findViewById(R.id.tv);
		final AlertDialog.Builder alertbox=new AlertDialog.Builder(this);
		data=new DataBaseHelper(this);
		Calendar cal = new GregorianCalendar();
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		sdate=(+ day + "-" + (month+1)+ "-" +year);  
		System.out.println("Current Date is:"+date);
		mHour = cal.get(Calendar.HOUR_OF_DAY);
		mMinute = cal.get(Calendar.MINUTE);
		
		stime=mHour+":"+mMinute;
		System.out.println("Current Time is:"+time);

						// set the message to display
                alertbox.setMessage("Do you want to import contacts from PhoneBook ?");
                
                // set a positive/yes button and create a listener
                alertbox.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
     
                    // do something when the button is clicked
                    public void onClick(DialogInterface arg0, int arg1)
                    {
                    	Task task = new Task();
                		task.applicationContext = Import.this;
                		task.execute();

                    	
                    }                                             
                });
     
                // set a negative/no button and create a listener
                alertbox.setNegativeButton("No", new DialogInterface.OnClickListener() {
     
                    // do something when the button is clicked
                    public void onClick(DialogInterface arg0, int arg1) 
                    {
                    	call();
                    }
                });
     
                // display box
                alertbox.show();
              
               /* Intent i=new Intent(Import.this,AllContacts.class);
            	startActivity(i);*/
            
				}
	public  class Task extends AsyncTask<String, Integer, Void> {
		private  ProgressDialog dialog;
		protected Context applicationContext;
		
		@Override
		protected void onPreExecute()
		{
			
			System.out.println("IN PreExecute");
			this.dialog = ProgressDialog.show(applicationContext, "Importing Contacts", "Please Wait...", true);
			 dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

		}
		@Override
		protected Void doInBackground(String... params) {
			// TODO Auto-generated method stub
			System.out.println("IN BACKGROUND");
			addcontacts();//return flag1;
			/*for(int i=1; i<=results.size(); ++i) {
		       
				 publishProgress((int)((i/(float)results.size())*100));
		   }*/

			//dialog.setMessage(name);
			return null ;
			
			
		}
		protected void onProgressUpdate(Integer... progress) 
		{
			setProgress(progress[0]);
			System.out.println("IN update"+progress[0]);
		
	    }                      
		@Override                      
		protected void onPostExecute(Void unused) {
			
			this.dialog.cancel();
			System.out.println("IN PostExecute");
			final AlertDialog.Builder alertbox1=new AlertDialog.Builder(Import.this);
			Cursor c=data.getData();
			int num=c.getCount();
			alertbox1.setMessage(+num+" contacts imported");
		     c.close();
            // set a positive/yes button and create a listener
            alertbox1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
 
                // do something when the button is clicked
                public void onClick(DialogInterface arg0, int arg1)
                {
                	
                	call();
                }
            });
            alertbox1.show();
		
					
			 
		}

		

		
	}

public void call()
{
	Intent i=new Intent(this,AllContacts.class);
	startActivity(i);
	
}

/*type :function
	name:addcontacts
	return type:void          
date:10-2-12
	purpose:imports contacts from device*/
    private void addcontacts()
	{   num="";numh="";numw="";numo="";email="";emailw="";emailo="";photoid="";
ContentResolver cr = getContentResolver();
    	Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
				null, null, null, null);
		if (cur.getCount() > 0)
		{
 			while (cur.moveToNext())                      
			{ 
				String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
				im_id=Integer.parseInt(id);
				photoid=cur.getString(cur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_ID));
				Cursor icur=data.getimid(im_id);
				int imcount=icur.getCount();
				if(imcount==0)
				{
					String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
					
					mn="";
					if(name==null)
					{               
						fn="Unknown";
						System.out.println("Name is null");
					}
					else if(name.contains(" "))
					{ 
						String[]s=name.split(" ");
						int len=s.length;
						fn=s[0]; 
						ln=s[len-1];
						for(int i=1;i<len-1;i++)
						{		 
							if(i==1)
							{               
								mn=s[i];
							} 
							else
							{
								mn=mn+" "+s[i];
							}
						
						}		
					}
					else
					{
					fn=name;
					ln="";
					}
					
					//get phone no:
					if (Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) 
					{
							Cursor pCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " +id,
									null, null);
					/*if(pCur.moveToFirst())*/
							while(pCur.moveToNext())
							{
				        
				        	
								type=pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));
								photoid=pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_ID));
								if(type.equals("3"))
				        		{
				        		
				        		numw=pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
				        		numw=numw.replace("-","");
				        		}else if(type.equals("1"))
				        		{
				        			numh=pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
					        		numh=numh.replace("-","");
				        		}
				        		else if(type.equals("2"))
				        		{
				        			num=pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
					        		num=num.replace("-","");
				        		}
				        		else
				        		{
				        			numo=pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
					        		numo=numo.replace("-","");
				        		}
							}pCur.close();
					}//if
							
								Cursor emails = getContentResolver().query(ContactsContract.CommonDataKinds.Email.CONTENT_URI,null,ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = " +id,null, null);
								while (emails.moveToNext()) 
								{
									String type1=emails.getString(emails.getColumnIndex(ContactsContract.CommonDataKinds.Email.TYPE));
									if(type1.equals("0"))
									{
										email = emails.getString(emails.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
									}else if(type1.equals("1"))
									{
										emailw = emails.getString(emails.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
									}
									else if(type1.equals("2"))
									{
										emailo = emails.getString(emails.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
									}
			        		
								}emails.close();
								if(fn.contains("'"))
								{
											fn=fn.replace("'", "@1@");
								}
								if(ln.contains("'"))
								{
											ln=ln.replace("'", "@1@");
								}
								//results.add(fn+" "+ln);
								data.Insert(fn,mn,ln,num,numh,numw,numo,"",email,emailw,emailo,"",photoid,"","",im_id,sdate,stime,notes1,0);
								num="";numh="";numw="";numo="";email="";emailw="";emailo="";photoid="";
								


									
									
				}		//ifimnt			
			
				else
				{
					while(icur.moveToNext())
					{
						cid=icur.getInt(0);
						System.out.println("cid"+cid);
						Cursor c=data.importdetails(cid);
						while(c.moveToNext())
						{
							now=c.getString(2);
							noh=c.getString(1);
							noo=c.getString(3);
							mob=c.getString(0);
						}	
						c.close();
								Cursor pCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " +id,
									null, null);
					/*if(pCur.moveToFirst())*/
							while(pCur.moveToNext())
							{
				        
				            	type=pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));
								String no1=pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
								no1=no1.replace("-","");
								if(type.equals("1"))
								{
									
									if(noh.equals(no1))
									{
										System.out.println("do nothing");
									}
									else
									{
										noh=no1;
									}
								}
								else if(type.equals("2"))
									{
										
										if(mob.equals(no1))
										{
											System.out.println("do nothing");
										}
										else
										{
											mob=no1;
										}
									}
								
								else if(type.equals("3"))
								{
									if(now.equals(no1))
									{
										System.out.println("do nothing");
									}
									else
									{
										now=no1;
									}
								}
								else if(noo.equals(no1))
								{
									
								}else
									noo=no1;
									
								
						}
							data.updateimport(cid,mob,noh,now,noo); 
							num="";numh="";numw="";numo="";email="";emailw="";emailo="";photoid="";
						pCur.close();
						}
						
 						
					}
				
				icur.close();
				}//else
 				
						
			}//if
 		cur.close();
 		}//addcontcts
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
		case R.id.mallconct:Intent i6 = new Intent(this, Contacts.class);
		startActivity(i6);
		break;
		case R.id.madd: Intent i1 = new Intent(this, AddContact.class);
		startActivity(i1);
		break;
		case R.id.mimport: Intent i3 = new Intent(this, Import.class);
		startActivity(i3);
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
	@Override
	public void onBackPressed()
	{
		Intent i=new Intent(Import.this,Contacts1.class);
		startActivity(i);
		return;
	}

}


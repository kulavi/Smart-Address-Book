// Displays contacts details
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
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
public class ViewProfile extends Activity 
{
	Bitmap bm;
	TelephonyManager tm;
	InputStream is = null;
	Button bimg,call,callh,callw,callo,btntask,btnnotes;
	EditText edfn,edln,edmno,edemail,edtag;
	EditText eefn,eeln,eemno,eeemail,eetag;
	TextView tfn,tln,tmno,temail,ttag,fname;
	TextView tbday,torg,ttask,tdescp,tnotes;
	String sln,sfn,smno,semail,stag,sn,sn1,simg;
	String seln,sefn,semno,seemail,setag,sen; 
	String sdln,sdfn,sdmno,sdemail,sdtag,sdn,stask,sdescp,snotes;
	String bday="",org="",task="",descp="",notes="";
	String smobw,smobo,smobh,smobcust,semailo,semailw,semailcust;
	TextView tmobh,tmobw,tmobo,tmobcust,temailw,temailo,temailcust;
	String[] s;
	private String u_id;
	int flag,fav_cid;
    int rating_flag,rating_flag1;  
    String num2;
	String cid2;
	boolean called=false;
	DataBaseHelper data;
	Button ed,de,up,del,back,eba,dba; 
	int inid;
	int id1;
	String img1,imgpath,id;
	String name1,cno,sno,ani;
	int column_index;
	Intent intent=null;
	private int setflag;
	String logo,imagePath,Logo;
String selectedImagePath;
	//ADDED
	String filemanagerstring;
	ImageView img3,img2;
	TableLayout showdetails;
	String [] array={"Mobile No","Home Mobile","Work Mobile","Other Mobile","Email Id",
	"Email Id Work","Email Id Other","Tags","Birth Day","Aniversary","Organization"};
	//"Tasks","Description","Notes"};
	String [] array1,array2;
	public void onCreate(Bundle savedInstanceState)
	{ 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewuser);
		showdetails=(TableLayout)findViewById(R.id.table);
		data=new DataBaseHelper(this);
		array2=new String[2];
		array1=new String[14];
		s=new String[3];
		Cursor c=data.getUserData();
		while (c.moveToNext())
		{	
			inid=c.getInt(0); 
			sfn=c.getString(1);
			sln=c.getString(3);
			smno=c.getString(4);
			smobh=c.getString(5);
			smobw=c.getString(6);
			smobo=c.getString(7);
			semail=c.getString(8);
			semailw=c.getString(9);
			semailo=c.getString(10);
			semailcust=c.getString(11);
			simg=c.getString(12);
			stag=c.getString(13);
			System.out.println("Image "+simg);
		 array2[0]=sfn;
			array2[1]=sln;
			array1[0]=smno;
			array1[1]=smobh;
			array1[2]=smobw;
			array1[3]=smobo;
			array1[4]=semail;
			array1[5]=semailw;
			array1[6]=semailo;
			array1[7]=stag;

			
		}
		c.close();
		Cursor cursor=data.getUserMoreInfo(inid);
		while(cursor.moveToNext())
		{      
			org=cursor.getString(3);
			bday=cursor.getString(13);
			ani=cursor.getString(14);
		        array1[8]=bday;
				array1[9]=ani;
				array1[10]=org;
		}   
		cursor.close();
		detail_display();

	}
  public void detail_display() 
	 { 
		 
    	int i=0;
    	final TableRow tr2 = new TableRow(this); 
    	 tr2.layout(0, 0, 0, 0);
    	 final ImageView image = new ImageView(this);
    	 final TextView fnandln = new TextView(this);
    	 if(simg==null)
 		{
 			
 			image.setImageResource(R.drawable.propic);
 		}
 		else
 		{
 		bm = BitmapFactory.decodeFile(simg);
 		
 		 image.setImageBitmap(bm);
 		}
 			
    	 image.setAdjustViewBounds(true);
    	 image.setMaxHeight(80);
    	 image.setMaxWidth(20);
    	 image.setPadding(6, 3, 0, 0);                
    	 fnandln.setText(array2[0]+" "+array2[1]);      
         fnandln.setTextColor(Color.BLACK);
         fnandln.setPadding(0, 25, 0, 0);
         
        
     	tr2.setPadding(0, 1, 0, 1);
     	
         tr2.addView(image);
         tr2.addView(fnandln);
            showdetails.addView(tr2, new TableLayout.LayoutParams(
                 LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT));
    	 
	 	for(int i4=0;i4<=13;i4++) 
	 	{
	 		final TableRow tr1 = new TableRow(this); 
					try 
					{
					
		            tr1.setId(100+i);
		            tr1.layout(0, 0, 0, 0); 
	     	
		            
		         // Create a TextView to house the name of the province 
		            
		          		            
		            final TextView statictext = new TextView(this);
		            tr1.setPadding(0, 1, 0, 1);
	                statictext.setPadding(10,0, 0, 0); 
		            statictext.setId(200+i);
		            statictext.setText(""+array[i4]);      
		           statictext.setTextColor(Color.BLACK);
		            fnandln.setTextColor(Color.BLACK);
		            
		            tr1.addView(statictext);
		            
		            final TextView dynamictext = new TextView(this);
		               		
		            dynamictext.setPadding(15,20,20,0);   
		            dynamictext.setId(200+i);
		            dynamictext.setTextColor(Color.BLACK);
		            dynamictext.setText(""+array1[i4]);      
		           
		            fnandln.setTextColor(Color.BLACK);
		            
		            tr1.addView(dynamictext);
		           /* final Button btnok = new Button(this);
		            if(i<4)
		            {
		          	btnok.setId(200+i);
		    		btnok.setBackgroundResource(R.drawable.call1);
		    		btnok.setPadding(25,10,0, 0);    		
		    		tr1.addView(btnok);            
		            }*/
		           
		             // Add the TableRow to the TableLayout 
	               showdetails.addView(tr1, new TableLayout.LayoutParams(
		                     LayoutParams.WRAP_CONTENT,
		                    LayoutParams.WRAP_CONTENT));
	                             
	             
	             i++;  
		            
		           
	           /*  btnok.setOnClickListener(new Button.OnClickListener()  
		           	{ 
		               	
		               	public void onClick (View v)
		           		{ 
		               		String st=statictext.getText().toString();
		               		System.out.println("Static Text "+st);
		               		if(st.equals("Mobile No"))
		               		{
		               			Cursor c6=data.get_CID(array1[0]);
			        			while(c6.moveToNext())
			        			{
			        				cid2=c6.getString(0);
			        				
			        			}
			        			c6.close();
		               			Intent intent = new Intent(Intent.ACTION_CALL);
		        				intent.setData(Uri.parse("tel:" +array1[0]));
		        				startActivity(intent);
		        				setflag=1;
		        				tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		        				tm.listen(mPhoneListener, PhoneStateListener.LISTEN_CALL_STATE);
		               		}
		               		if(st.equals("Home Mobile"))
		               		{
		               			Cursor c6=data.get_CID(array1[1]);
			        			while(c6.moveToNext())
			        			{
			        				cid2=c6.getString(0);
			        				
			        			}
			        			c6.close();
		               			Intent intent = new Intent(Intent.ACTION_CALL);
		        				intent.setData(Uri.parse("tel:" +array1[1]));
		        				startActivity(intent);
		        				setflag=2;
		        				tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		        				tm.listen(mPhoneListener, PhoneStateListener.LISTEN_CALL_STATE);
		               		}
		               		if(st.equals("Work Mobile"))
		               		{
		               			Cursor c6=data.get_CID(array1[2]);
			        			while(c6.moveToNext())
			        			{
			        				cid2=c6.getString(0);
			        				
			        			}
			        			c6.close();
		               			Intent intent = new Intent(Intent.ACTION_CALL);
		        				intent.setData(Uri.parse("tel:" +array1[2]));
		        				startActivity(intent);
		        				setflag=3;
		        				tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		        				tm.listen(mPhoneListener, PhoneStateListener.LISTEN_CALL_STATE);
		               		}
		               		if(st.equals("Other Mobile"))
		               		{
		               			Cursor c6=data.get_CID(array1[3]);
			        			while(c6.moveToNext())
			        			{
			        				cid2=c6.getString(0);
			        				
			        			}
			        			c6.close();
		               			Intent intent = new Intent(Intent.ACTION_CALL);
		        				intent.setData(Uri.parse("tel:" +array1[3]));
		        				startActivity(intent);
		        				setflag=4;
		        				tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		        				tm.listen(mPhoneListener, PhoneStateListener.LISTEN_CALL_STATE);
		               		}
		           		          		  
		           		}  
		           	});  
		              */
					}
					catch (Exception e)    
			        {
			         
			        System.out.println("Error msg:::::::::"+e.getMessage());
			        }
			}
	 	
		
		
	 	
  }
	 
	/* private PhoneStateListener mPhoneListener = new PhoneStateListener() 
		{
			

			

			public void onCallStateChanged(int state, String Number) 
			{
				
				 if (called && state == TelephonyManager.CALL_STATE_IDLE) 
				 {
		                called = false;
		                tm.listen(this, PhoneStateListener.LISTEN_NONE);
		                try {
		                    ViewProfile.this.finish();
		                    ArrayList<NameValuePair> nv = new ArrayList<NameValuePair>();
		                    nv.add(new BasicNameValuePair("u_id",u_id));
			        		nv.add(new BasicNameValuePair("num",num2));
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
							Intent i = new Intent(ViewProfile.this,UpdateStatusContactsDetails.class);
							Bundle bun=new Bundle();
							bun.putString("name",cid2);
							System.out.println("Name "+cid2);
							i.putExtras(bun);  
							i.setAction(Intent.ACTION_MAIN);
							startActivity(i);
							}
							else
							{
								Intent i=new Intent(ViewProfile.this,ViewProfile.class);
								i.setAction(Intent.ACTION_MAIN);
								Bundle bun=new Bundle();
								bun.putString("name2",id);
								i.putExtras(bun);
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
		                    if(setflag==1)
		                    {
		                    	num2=array1[0];
		                    	System.out.println("mphone "+num2);
		                    }
		                    else if(setflag==2)
		                    {
		                    	num2=array1[1];
		                    	System.out.println("hphone "+num2);
		                    }
		                    else if(setflag==3)
		                    {
		                    	num2=array1[2];
		                    	System.out.println("wphone "+num2);
		                    }
		                    else if(setflag==4)
		                    {
		                    	num2=array1[3];
		                    	System.out.println("ophone "+num2);
		                    }
		                ArrayList<NameValuePair> nv = new ArrayList<NameValuePair>();
		        		nv.add(new BasicNameValuePair("u_id",u_id));
		        		nv.add(new BasicNameValuePair("num",num2));
		        		
		        		
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
*/
		public boolean onCreateOptionsMenu(android.view.Menu menu) {
			MenuInflater inflater = getMenuInflater();
			inflater.inflate(R.menu.profilemenu, menu);
			return true;
		}


		public boolean onOptionsItemSelected(MenuItem item) 
		{
			switch (item.getItemId()) 
			{
			case R.id.medit: Intent i5 = new Intent(this, EditProfile.class);
			startActivity(i5);
			break;
			}
			return true;
		}  
        
	@Override
	public void onBackPressed()
	{
		Intent i=new Intent(ViewProfile.this,AccountSettings.class);
		startActivity(i);
	
		return;
	}

}

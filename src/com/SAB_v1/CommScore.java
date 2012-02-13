package com.SAB_v1;

import java.util.ArrayList;

import com.SAB_v1.CallsScore.ImageAdapter;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class CommScore extends Activity 
{
	DataBaseHelper data;
	int con_id;
	 String fn;
	 String ln;
	 String name;
	String num;
	  String logid;
	  ImageView iv1;
	 int count1;
	String id,number;
	 String logdate;
	 String logtime;
	 String dur;
	 int logtype;
 int smss_id;
	 String receiver;
	String message_s;
	 String smss_date;
	 String smss_time;
	 int smss_sid;
	 int count_s;
	 int count_r;
 int smsr_sid,contact_id1;
	 String smsr_time;
	 String smsr_date;
	 String message_r;
	 String sender;
	 int smsr_id;
	 int smss_count;
	 int smsr_count;
	 int c_count,count_e;
	 String mob,enumb;
	 String numh;
	 String numw;
	 int sent_count,contact_id;
	 String numo,num1;
	 long total_calls,total_sms,total_email,total_count;
	 long call_score,sms_score,email_score,total_score;
	 GridView grid_main;
	  private ArrayList<String> results = new ArrayList<String>();
	 @Override
		public void onCreate(Bundle icicle)
		{                       
			super.onCreate(icicle);
			data=new DataBaseHelper(this);          
			setContentView(R.layout.commngrid);
			Window window = getWindow();
	        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
	        grid_main = (GridView)findViewById(R.id.GridView01);
	        grid_main.setAdapter(new ImageAdapter(this));
	        showcontacts();
		}
	 public void showcontacts()
	 
	 {
		 Cursor c2=data.get_con_ids_and_num();
		 while(c2.moveToNext())
		 {
			 con_id=c2.getInt(0); 
			 System.out.println("con "+con_id); 
			 num=c2.getString(1);  
			 numh=c2.getString(2);
			 numw=c2.getString(3);
			 numo=c2.getString(4);
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
			 Cursor c=data.get_call_logs(num);
			      while(c.moveToNext())
			      {
				    number=c.getString(0);
					count1=c.getInt(1);
					System.out.println(num+" "+count1);
					Cursor c8=data.get_comm(number);
					int cnt=c8.getCount();
					if(cnt>0)
					{
						data.update_comm_call(number, count1, con_id);
					}
					else
					{
					data.Insert_comm_call(number, count1, con_id);
					}
					c8.close();
			       }
			      
			 c.close();
		 
		 Cursor c4=data.get_count_sms_sent(num);
		 while(c4.moveToNext())
		 {
			 receiver=c4.getString(0);
			 count_s=c4.getInt(1);
			 System.out.println("Sms received "+receiver+" "+count_s);
			 Cursor c9=data.get_comm(receiver);
			 int cnt1=c9.getCount();
			 if(cnt1>0)
			 {
				 data.update_comm_sms(receiver, count_s);
			 }
			 else
			 {
				 data.Insert_comm_sms(receiver, count_s);
			 }
			 c9.close();
		 }
		 c4.close();
		 Cursor c5=data.get_count_sms_receive(num);
		 while(c5.moveToNext())
		 {
			 sender=c5.getString(0);
			 count_r=c5.getInt(1);
			 System.out.println("Sms send "+sender+" "+count_r);
			 Cursor c7=data.get_comm(sender);
			 int cnt2=c7.getCount();
			 if(cnt2>0)
			 {
				 data.update_comm_sms(sender, count_r);
			 }
			 else
			 {
				 data.Insert_comm_sms(sender, count_r);	 
			 }
			 c7.close();
		 }
		 c5.close();
		 Cursor c12=data.get_email_count(num);
		 while(c12.moveToNext())
		 {
			count_e=c12.getInt(0);
			System.out.println("email count "+count_e);
			Cursor c13=data.get_comm(num);
			int cnt3=c13.getCount();
			if(cnt3>0)
			{
				data.update_comm_email(num, count_e);
			}
			else
			{
				data.Insert_comm_email(num, count_e);
			}
			c13.close();
		 }
		 c12.close();
		 Cursor c14=data.get_sent_sms_count(con_id);
		 while(c14.moveToNext())
		 {
			 sent_count=c14.getInt(0);
			 System.out.println("sms sent count "+sent_count);
			 Cursor c7=data.get_comm(sender);
			 int cnt2=c7.getCount();
			 if(cnt2>0)
			 {
				 data.update_comm_sms(num, sent_count);
			 }
			 else
			 {
				 data.Insert_comm_sms(num, sent_count);	 
			 }
			 c7.close();
		 }
		 c14.close();
		 Cursor c11=data.get_comm(num);
		 while(c11.moveToNext())
		 {
			 contact_id=c11.getInt(2);
			 total_calls=c11.getLong(3);
			 total_sms=c11.getLong(4);
			 total_email=c11.getLong(5);
			 System.out.println("id  "+contact_id);
			 System.out.println("total calls  "+total_calls);
             System.out.println("total sms  "+total_sms);
             System.out.println("total email "+total_email);
			 call_score=total_calls*7;
			 sms_score=total_sms*5;
			 email_score=total_email*3;
			 total_score=call_score+sms_score+email_score;
			 System.out.println("Total Score "+total_score);
			 int cnt5=c11.getCount();
			 if(cnt5>0)
			 {
			    data.update_comm_total_count(num, total_score);
			 }
			 else
			 {
				 data.Insert_comm_total_count(num, total_score);
			 }
			
		 }//comm while loop
		 c11.close();
		 }//etable while loop
		 c2.close();
		 Cursor c15=data.get_comm_total_count();
		 while(c15.moveToNext())
		 {
			 contact_id1=c15.getInt(0);
			 total_count=c15.getLong(1);
			 num1=c15.getString(2);
			 System.out.println(" toatl count "+contact_id1+" "+total_count);
			 Cursor c1=data.getfname(num1);
				int cnt=c1.getCount();
				 if(cnt==0)
				 {
					name="No Name";
					results.add(name+"\n"+num1);
				 }
				 else
				 {
				 while(c1.moveToNext())
				  {
					fn=c1.getString(0);
					ln=c1.getString(1);
					name=fn+" "+ln;
					if(name==null)
					{
						name="No Name";
					}
					results.add(name+"\n"+num1);
		
				}
				}
				 c1.close();
		 }
		 c15.close();
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
					/*TextView tv1 = (TextView)v.findViewById(R.id.icon_text1);
					tv1.setText(""+results3.get(position));	*/
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
}

package com.SAB_v1;
import java.util.Calendar;
import java.util.GregorianCalendar;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class UpdateStatusContactsDetails extends Activity implements View.OnClickListener {
	protected static RadioGroup radiogroup; 
	Button but,but1,dcancel;
	CharSequence ch;
	Button b1,b2,b3;
	TextView ename,valueTextView,tstatus;
	String name2,ans,name1,sn,notes,fname,lname;
	String[] s1;
	RadioButton r1,r2,r3,r4;
    int cid;
	EditText mDate,mTime,eNote,note1;    
	String sdate,stime1,stime,stask,note,id,mob;
		int mHour,mMinute;

	DataBaseHelper data;
	@Override
	public void onCreate(Bundle icicle)
	{


		super.onCreate(icicle);
		data=new DataBaseHelper(this);
		setContentView(R.layout.updatedialog);
		Bundle bundle = getIntent().getExtras(); 
		id=bundle.getString("name");
		mob=bundle.getString("num");
		System.out.println("My Names: "+id+" "+mob);
		cid=Integer.parseInt(id);
		ename=(TextView)findViewById(R.id.name);
		Calendar cal = new GregorianCalendar();
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		sdate=(+ day + "-" + (month+1)+ "-" +year);  
		mHour = cal.get(Calendar.HOUR_OF_DAY);
		mMinute = cal.get(Calendar.MINUTE);
		
		stime=mHour+":"+mMinute;
		System.out.println("Current Date is:"+stime);

		Cursor c=data.getcontactname(cid);
		while(c.moveToNext())
		{
			fname=c.getString(0);
			lname=c.getString(1);
			System.out.println("Fname "+fname);
			System.out.println("Lname "+lname);
			name2=fname+" "+lname;
		}
		c.close();
		ename.setText(" "+name2);
		tstatus=(TextView)findViewById(R.id.cstatus);
		but=(Button)findViewById(R.id.done);
		but.setOnClickListener(new OnClickListener() {

			public void onClick(View v)
			{
				Intent i=new Intent(UpdateStatusContactsDetails.this,ContactDetails.class);
				Bundle bun=new Bundle();
				bun.putString("name2",id);
				i.putExtras(bun);
				startActivity(i);
			}
		});
		radiogroup = (RadioGroup) findViewById(R.id.Group1);

		r1=(RadioButton)findViewById(R.id.task);
		r2=(RadioButton)findViewById(R.id.notes);

		       radiogroup.setOnCheckedChangeListener(new OnCheckedChangeListener()
		       {
				
				public void onCheckedChanged(RadioGroup group, int checkedId) 
				{
					// TODO Auto-generated method stub
					
					if(r1.isChecked())
					{	  
						ch=r1.getText();
						stask=(String)ch;
						Intent i = new Intent(UpdateStatusContactsDetails.this,AddTaskContactDetails.class);
						Bundle bun=new Bundle();
						bun.putString("name",id);
						System.out.println("Name2: "+id);
						i.putExtras(bun);
						startActivity(i);
					}
					if(r2.isChecked())
					{	  
						//tstatus.setText("Call Completed");
						ch=r1.getText();
						final Dialog dialog = new Dialog(UpdateStatusContactsDetails.this);

						dialog.setContentView(R.layout.note);
						note1 =(EditText)dialog.findViewById(R.id.unotes);
						dialog.setTitle("Enter Notes");
						dialog.setCancelable(true);
						Button btn=(Button)dialog.findViewById(R.id.bok);
						Button btn1=(Button)dialog.findViewById(R.id.bcancel);
						btn1.setOnClickListener(new OnClickListener() 
				 		{
				 			public void onClick(View v) 
				 			{
				 				dialog.dismiss();

				 			}
				 		});
						btn.setOnClickListener(new OnClickListener()
						{
							public void onClick(View v)
							{		

								notes=note1.getText().toString();
								System.out.println("your notes:-"+notes);
								data.Insertnotes(cid,name2,mob,notes,sdate,stime);
								Cursor c3 =data.getnotes();
								while(c3.moveToNext())
								{
									String n1=c3.getString(1);
									String n2=c3.getString(2);
									System.out.println("N1 "+n1);
									System.out.println("N2 "+n2);
								}
								c3.close();
				  				dialog.dismiss();
				  			Toast.makeText(UpdateStatusContactsDetails.this, "Note Added Sucessfully",Toast.LENGTH_SHORT).show();
							}

						});
						dialog.show();
					}
					
				}
			});
		
				
	}
	
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

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

		}
		return true;
	}     
	@Override
	public void onBackPressed()
	{
		Intent i=new Intent(UpdateStatusContactsDetails.this,ContactDetails.class);
		Bundle bun=new Bundle();
		bun.putString("name2",id);
		i.putExtras(bun);
		startActivity(i);
		return;

	}
}




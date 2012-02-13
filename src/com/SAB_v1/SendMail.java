package com.SAB_v1;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SendMail extends Activity {
    /**
     * Called with the activity is first created.
     */
	
	DataBaseHelper data;
	int conid,count;
	String email,pwd,sub,strbody,strto;
	int flag;
	GMailSender m;
	Button send1,cancel;
	EditText to,subject,body;
	int inid1;
	String fname,mail,name,acc_name,acc_pwd;
	String num,numh,numo,numw;
    @Override
    public void onCreate(Bundle icicle) 
    {
        super.onCreate(icicle);
        setContentView(R.layout.sendmail);
        setTitle("Email");
        data=new DataBaseHelper(this);
		Bundle bundle=getIntent().getExtras();
		mail=bundle.getString("semail");
		inid1=bundle.getInt("inid");
		System.out.println("Nameeeeeeeeee1"+mail);
		Window window = getWindow();
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        send1 = (Button) this.findViewById(R.id.send);
        cancel = (Button) this.findViewById(R.id.cancel);
		to = (EditText) this.findViewById(R.id.to);
       subject = (EditText) this.findViewById(R.id.subject);
        body = (EditText) this.findViewById(R.id.body);
        to.setText(mail);
        Cursor c11= data.getcontidfrommail(mail);
        System.out.println("Mail"+mail);
		while(c11.moveToNext())
		{
			conid=c11.getInt(0); 
			fname=c11.getString(1);
			System.out.println("conid "+conid);
			System.out.println("Fname "+fname);
		}	
		c11.close();
        Cursor c=data.viewAccDetails_settings1();
		while (c.moveToNext())
		{	
			int i=c.getInt(0);
		    Cursor c1=data.viewAccDetails_settings(i);
			while (c1.moveToNext())
			{	
				email=c1.getString(0);
				pwd=c1.getString(1);
				System.out.println("Email "+email);
				System.out.println("Pwd "+pwd);
			}
			c1.close();
		}
		c.close();
             cancel.setOnClickListener(new View.OnClickListener()
             {
            public void onClick(View view) 
            {
                  finish();            	
            }
        });
        send1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) 
            {
            	Task task = new Task();
        		task.applicationContext = SendMail.this;
        		task.execute();
                   

                }
        });   
    
    }
    /*type :function
 	name:get
 	return type:void          
	date:10-2-12
 	purpose:get contact details*/
    public void get()
    {
    	 Cursor c1= data.getName(mail);
 		while(c1.moveToNext())
 		{
 			conid=c1.getInt(0);
 			fname=c1.getString(1);
 			num=c1.getString(2);
 			numh=c1.getString(3);
 			numw=c1.getString(4);
 			numo=c1.getString(5);
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
 			System.out.println("conid "+conid);
 			System.out.println("Fname "+fname);
 			 
 		}
 		c1.close();
 		Cursor c3= data.getcount(inid1);
	 		while(c3.moveToNext())
	 		{
	 			count=c3.getInt(0);
	 			System.out.println("Count "+count);
	 		}	
	 		if(count==0)
	 		{
	 			count=count+1;
	 			System.out.println("Count "+count);
	 			data.insertmaillog(inid1,count,num);
	 			
	 		}
	 		else if(count>0)
	 		{	count=count+1;
	    			data.updateemailcnt(inid1, count,num);
	 		}
	 		c3.close();
        Cursor c5= data.getmaildata(inid1);
 		while(c5.moveToNext())
 		{
 			int eid=c5.getInt(0);
 			int count1=c5.getInt(1);   
 			System.out.println("eid........ "+eid);
 			System.out.println("Count........ "+count1);
 		}
 		c5.close();
    }
    /*type :function
 	name:sendmail
 	return type:void          
	date:10-2-12
 	purpose:send email*/
public void sendmail()
{
	m = new GMailSender(email,pwd); 
	sub=subject.getText().toString();
	strbody=body.getText().toString();
	strto=to.getText().toString();
	String[] toArr = {strto}; 
      m.setTo(toArr); 
      m.setFrom(email);
      m.setSubject(sub); 
      m.setBody(strbody); 
 get();
  	   

}
    public  class Task extends AsyncTask<String, Integer, Void> {
		private  ProgressDialog dialog;
		protected Context applicationContext;
		
		@Override
		protected void onPreExecute()
		{
			
			System.out.println("IN PreExecute");
			this.dialog = ProgressDialog.show(applicationContext, "Sending Email", "Please Wait...", true);
			 dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

		}
		@Override
		protected Void doInBackground(String... params) {
			// TODO Auto-generated method stub
			System.out.println("IN BACKGROUND");
				//return flag1;
			sendmail();
			return null ;
			
			
		}
		protected void onProgressUpdate(String... progress) 
		{
			System.out.println("IN update");
		
	    }
    @Override
	protected void onPostExecute(Void unused) 
    {
    	this.dialog.cancel();
		System.out.println("IN PostExecute");
        call();
	      
		 
	}

}	
    /*type :function
 	name:call
 	return type:void          
	date:10-2-12
 	purpose:calls contact Details class*/
public void call()
{
	Toast.makeText(SendMail.this, "Email was sent successfully.", Toast.LENGTH_LONG).show();
	String sid=""+inid1;
	Intent i=new Intent(SendMail.this,ContactDetails.class);
	Bundle bun=new Bundle();
	bun.putString("name2",sid);
	i.putExtras(bun);
	startActivity(i);
}
    @Override
	public void onBackPressed()
	{	
    	String sid=""+inid1;
		Intent i=new Intent(SendMail.this,ContactDetails.class);
		Bundle bun=new Bundle();
		bun.putString("name2",sid);
		i.putExtras(bun);
		startActivity(i);
	}
}

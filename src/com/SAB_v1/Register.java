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
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends Activity
{
	EditText fname,mname,lname,phno,email,pwd,compwd;
	Button register,cancel; 
	RadioButton male,female;
	TextView text1,text2,text3,text4,text5;
	String fn,ln,mn,ph,emailid,pass,compass,gen;
	DataBaseHelper db1;
	InputStream is = null;
	String result = "";
	String mail="feedback.sab@gmail.com";
	String password="feedback@bpsi";
	@Override
	public void onCreate(Bundle icicle)
	{
		super.onCreate(icicle);
		setContentView(R.layout.signup);
		setTitle("Register");
		db1=new DataBaseHelper(this);
		Window window = getWindow();
		window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		fname=(EditText)findViewById(R.id.fname);
		mname=(EditText)findViewById(R.id.mname);
		lname=(EditText)findViewById(R.id.lname);
		phno=(EditText)findViewById(R.id.ph);
		email=(EditText)findViewById(R.id.email);
		pwd=(EditText)findViewById(R.id.pass);
		compwd=(EditText)findViewById(R.id.rp);
		register=(Button)findViewById(R.id.reg);
		male=(RadioButton)findViewById(R.id.male);
		female=(RadioButton)findViewById(R.id.female);
		text1=(TextView)findViewById(R.id.text1);
		text2=(TextView)findViewById(R.id.text2);
		text3 = (TextView)findViewById(R.id.text3);
		text4 = (TextView)findViewById(R.id.text4);
		text5 = (TextView)findViewById(R.id.text5);
		initcontrols();
	}
	 /*type :function
 	name:initcontrols
 	return type:void          
	date:10-2-12
 	purpose:initilazes the controls*/
	public void initcontrols()
	{
		text3.setText("");
		text4.setText("");
		text5.setText("");
		cancel=(Button)findViewById(R.id.cancel);
		cancel.setOnClickListener(new Button.OnClickListener() {
			
			public void onClick(View v) 
			{
			
				finish();
				
			}
		});
		register.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View v) 
			{
			
				checkValidate();
				
			}	
		});
	}
	 /*type :function
 	name:checkvalidate
 	return type:void          
	date:10-2-12
 	purpose:validation*/
	protected void checkValidate()  
	{
		fn=fname.getText().toString();
		mn=mname.getText().toString();
		ln=lname.getText().toString();
		ph=phno.getText().toString();
		emailid=email.getText().toString();
		pass=pwd.getText().toString();
		compass=compwd.getText().toString();
		System.out.println("Fname "+fn);
		System.out.println("Mname "+mn);
		System.out.println("Lname "+ln);
		System.out.println("phno "+ph);
		System.out.println("Email "+emailid);
		System.out.println("Pass "+pass);
		
		if(male.isChecked())
		{
			gen="Male";
			System.out.println("Gen..."+gen);
			
		}
		if(female.isChecked())
		{
			gen="Female";
			
		}

		if(fn.equals(""))
		{
			text1.setText("First name not entered!!");
		}
		
		else if(ln.equals(""))
		{
			text1.setText("Last name not entered!!");
		}
		else if(ph.equals(""))
		{
			text1.setText("Phone not entered!!");
		}
		else if(emailid.equals(""))
		{
			text1.setText("Email not entered!!");
		}
		else if(pass.equals(""))
		{
			text1.setText("Password not entered!!");
		}
		else if(compass.equals(""))
		{
			text1.setText("Password not entered!!");
		}
		else if(!pass.equals (compass))
		{
			text1.setText("Sorry your Passwords donot Match!!");

		}
		else
		{
			Task task = new Task();
    		task.applicationContext = Register.this;
    		task.execute();

		}
}
	public  class Task extends AsyncTask<String, Integer, Void> {
		private  ProgressDialog dialog;
		protected Context applicationContext;

		@Override
		protected void onPreExecute()
		{
			
			System.out.println("IN PreExecute");
			this.dialog = ProgressDialog.show(applicationContext, "Registering Data", "Please Wait...", true);
		}
		@Override
		protected Void doInBackground(String... params)
		{
			// TODO Auto-generated method stub
			System.out.println("IN BACKGROUND");
			register();			//return flag1;
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
			Toast.makeText(Register.this, "Registered Successfully \n Confirmation email is Sent to your email id",Toast.LENGTH_SHORT).show();
						 
		}
	}
	 /*type :function
 	name:register
 	return type:void          
	date:10-2-12
 	purpose:registers to server*/
	public void register()
	{
		ArrayList<NameValuePair> nv = new ArrayList<NameValuePair>();
		nv.add(new BasicNameValuePair("fname",fn));
		nv.add(new BasicNameValuePair("mname",mn));
		nv.add(new BasicNameValuePair("lname",ln));
		nv.add(new BasicNameValuePair("ph",ph));
		nv.add(new BasicNameValuePair("email",emailid));
		nv.add(new BasicNameValuePair("pass",pass));
		nv.add(new BasicNameValuePair("gen",gen));
		
		//http post
		try
		{
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost("http://bpsi.us/blueplanetsolutions/stlist/user.php");
			httppost.setEntity(new UrlEncodedFormEntity(nv));
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
		}
		catch(Exception e)
		{
			text2.setText("Error in http connection");
		}
		   sendemail();
	}
	 /*type :function
 	name:sendemail
 	return type:void          
	date:10-2-12
 	purpose:sends email confirmation*/
	public void sendemail()
	{
		GMailSender m = new GMailSender(mail, password); 
		  String toArr []= {emailid}; 
		  m.setTo(toArr);
	      m.setFrom(mail); 
	      m.setSubject("SAB account confirmation"); 
	      m.setBody("Hello "+fname+" "+lname+"\n\tThanks for registering to our site Smart Address Book.\nWe would be glad to receive your feedbacks\n\nThanks And Regards\n\tSAB Team  "); 
       }
	}


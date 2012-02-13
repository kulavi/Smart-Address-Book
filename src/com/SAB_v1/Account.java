//Creates a account in smart address book
package com.SAB_v1;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Account extends Activity{
	private EditText email,pwd;
	private Button done,cancel;
	String n,e,p,emailid;
	DataBaseHelper db;
	@Override
	public void onCreate(Bundle icicle)
	{
		
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(icicle);
		db=new DataBaseHelper(this);
		setContentView(R.layout.account);
		setTitle("Create Account");
		email=(EditText)findViewById(R.id.email);
		pwd=(EditText)findViewById(R.id.pwd);
		done=(Button)findViewById(R.id.done);
		cancel=(Button)findViewById(R.id.cancel);
	//Retrieve email id and set text to text view
		Cursor c=db.getemailid();
		while(c.moveToNext())
		{
			emailid=c.getString(0);
		}
		c.close();
		email.setText(emailid);
		done.setOnClickListener(new OnClickListener() 		
		{

			public void onClick(View v) 
			{
				
				e=email.getText().toString();
				p=pwd.getText().toString();
				db.insertacc(e,p);
				Toast.makeText(Account.this,"Account Created Successfully!!!",Toast.LENGTH_SHORT).show();
				Intent i=new Intent(Account.this,view_account.class);
				startActivity(i);
				
			}
			
		});
		cancel.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				finish();
			}
		});
	}
}
package com.SAB_v1;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;
 
public class AlarmDetails_call extends Activity {
	 String task;
	 DataBaseHelper db;
		String cid;
	@Override
   
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.alarmdetails);
        db=new DataBaseHelper(this);
        //---look up the notification manager service---
        NotificationManager nm = (NotificationManager) 
            getSystemService(NOTIFICATION_SERVICE);
        String list=getIntent().getExtras().getString("listname");
    	Cursor c8=db.getcallid(list);
		while(c8.moveToNext())
		{
			cid=c8.getString(0);
		}
		c8.close();
        Intent i= new Intent(AlarmDetails_call.this,DisplayCallList.class);
		Bundle bun=new Bundle();
		bun.putString("id",cid);
		i.putExtras(bun);
		startActivity(i);
       
        //---cancel the notification---
        nm.cancel(getIntent().getExtras().getInt("NotifID"));        
    }
}

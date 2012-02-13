package com.SAB_v1;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;
 
public class AlarmDetails extends Activity {
	 String task;
	 DataBaseHelper db;
		int tid;
	@Override
   
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarmdetails);
        db=new DataBaseHelper(this);
        //---look up the notification manager service---
        NotificationManager nm = (NotificationManager) 
            getSystemService(NOTIFICATION_SERVICE);
        String tname=getIntent().getExtras().getString("task");
        Cursor c=db.getTaskid(tname);
        while(c.moveToNext())
        {
        	tid=c.getInt(0);
        	System.out.println("Task id"+tid);
        }
        c.close();
        Intent i= new Intent(AlarmDetails.this,Taskdetails.class);
		Bundle bun=new Bundle();
		bun.putInt("tid",tid);
		i.putExtras(bun);
		startActivity(i);
       
        //---cancel the notification---
        nm.cancel(getIntent().getExtras().getInt("NotifID"));        
    }
}

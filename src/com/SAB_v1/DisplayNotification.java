package com.SAB_v1;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
 
public class DisplayNotification extends Activity {
    /** Called when the activity is first created. */
	String tname;
	DataBaseHelper data;
	int flag,flag1;
	int notifID;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        //---get the notification ID for the notification; 
        // passed in by the MainActivity---
        data=new DataBaseHelper(this);
         notifID = getIntent().getExtras().getInt("NotifID");
       tname=getIntent().getExtras().getString("task");
        System.out.println("task noti"+tname+" "+notifID);
        Cursor c9=data.getnotisettings();
    	while(c9.moveToNext())
    	{
    		flag=c9.getInt(0);
    		System.out.println("Noti Flag will......."+flag);
    	}
    	c9.close();
    	Cursor c10=data.getwinsettings();
    	while(c10.moveToNext())
    	{
    		flag1=c10.getInt(0);
    		System.out.println("Win Flag will......."+flag1);
    	}
    	c10.close();
    	if(flag==1)
    	{
    	   call1();
    	}
    	else
    	if(flag1==1)
    	{
    		call();
    	}	
        
    }
    /*type :function
  	name:call
  	return type:void          
	date:10-2-12
  	purpose:show dialog*/
    public void call()
    {
    	AlertDialog.Builder alt_bld = new AlertDialog.Builder(this);
    	alt_bld.setMessage(""+tname)
    	.setCancelable(false)
    	.setPositiveButton("View Details", new DialogInterface.OnClickListener() {
    	public void onClick(DialogInterface dialog, int id) 
    	{
    	// Action for 'Yes' Button
    		call2();
    	}
    	})
    	.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
    	public void onClick(DialogInterface dialog, int id) {
    	//  Action for 'NO' Button
    	dialog.cancel();
    	}
    	});
    	AlertDialog alert = alt_bld.create();
    	// Title for AlertDialog
    	alert.setTitle("Task Alert");
    	// Icon for AlertDialog
    	alert.setIcon(R.drawable.alert);
    	alert.show();	
	   }
    /*type :function
  	name:call2
  	return type:void          
	date:10-2-12
  	purpose:calls alarm details class*/
public void call2()
{
	Intent i = new Intent(DisplayNotification.this,AlarmDetails.class);
	Bundle bun=new Bundle();                   
	bun.putString("task",tname); 
	bun.putInt("NotifID", 1);
	 i.putExtras(bun);    
    startActivity(i);
}
/*type :function
	name:call1
	return type:void          
date:10-2-12
	purpose:sets notification*/
    public void call1()
    {
    	Intent i = new Intent(DisplayNotification.this,AlarmDetails.class);
        //---PendingIntent to launch activity if the user selects 
        // the notification---
    
        Bundle bun=new Bundle();                   
		bun.putString("task",tname); 
		bun.putInt("NotifID", 1);
		 i.putExtras(bun);    
        PendingIntent detailsIntent = 
            PendingIntent.getActivity(this, 0, i, 0);
 
        NotificationManager nm = (NotificationManager)
            getSystemService(NOTIFICATION_SERVICE);
        Notification notif = new Notification(
            R.drawable.alert, 
            "Time's up!",
            System.currentTimeMillis());
 
        CharSequence from = "Alert!!!";
        CharSequence message = "Task "+tname+" Alert!!!" ;        
        notif.setLatestEventInfo(this, from, message, detailsIntent);
    	notif.defaults |= Notification.DEFAULT_SOUND;
 
        //---100ms delay, vibrate for 250ms, pause for 100 ms and
        // then vibrate for 500ms---
     
        nm.notify(notifID, notif);
        //---destroy the activity---
        finish();
        
    }
}

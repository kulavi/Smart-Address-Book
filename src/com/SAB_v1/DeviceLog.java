package com.SAB_v1;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.CallLog.Calls;
public class DeviceLog extends Activity 
{
    /** Called when the activity is first created. */
	long date;
	String time1,no,date1,time2,duration;
	DataBaseHelper data;
	int logtype,c_id;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        data=new DataBaseHelper(this);
        //setContentView(R.layout.devicelog);
        String[] projection = new String[]{Calls.DATE, Calls.NUMBER, Calls.DURATION,Calls.TYPE };
       Cursor mCur = managedQuery(CallLog.Calls.CONTENT_URI,projection, Calls.DURATION +"<?",new String[] {"6000"},
                        Calls.DURATION + " ASC");
       while (mCur.moveToNext())
       {
    	   time1=mCur.getString(mCur.getColumnIndex(CallLog.Calls.DURATION)) ;
           duration=time1+"sec";
           date=mCur.getLong(mCur.getColumnIndex(CallLog.Calls.DATE)) ;
           System.out.println("Date...............+"+date);
           SimpleDateFormat sdf=new SimpleDateFormat("MMM dd yyyy");
           Date resultdate = new Date(date);
           System.out.println(sdf.format(resultdate)); 
           SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm");
           Time resulttime=new Time(date) ;
           System.out.println(sdf1.format(resulttime)); 
           no=mCur.getString(mCur.getColumnIndex(CallLog.Calls.NUMBER)) ; 
           c_id=mCur.getInt(mCur.getColumnIndex(CallLog.Calls._ID));
            System.out.println("ci...."+c_id);
           System.out.println("Du...."+time1);
           System.out.println("Date...."+resultdate);
           System.out.println("Time...."+resulttime);
           System.out.println("No...."+no);
     	     date1=""+resultdate; 
     	     time2=""+resulttime;
     	     logtype=mCur.getInt(mCur.getColumnIndex(CallLog.Calls.TYPE));
     	     System.out.println("Log type will.."+logtype);
     	     //inserts call log to database
     	      data.InsertcallLog(c_id, date1,time2, duration, no,logtype);
    }mCur.close();
    }
    
}
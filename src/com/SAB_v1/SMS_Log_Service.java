package com.SAB_v1;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;
import android.app.Service;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;


public class SMS_Log_Service extends Service
{
private static final String TAG = "MyService1";
	Timer timer; 
	private String no1;
	private int c_id2;
	private int logtype1;
	private String ssid;
	private int ssid1;
	private String sid;
	private int sid1;
	private String time1,duration,time2,date1;
	private long date;
	DataBaseHelper data;
	String sender,smsdate,msg;
	String receiver,smsrecdate,recmsg;
	private TimerTask updateTask = new TimerTask() {
	    @Override
	    public void run() {
	      Log.i(TAG, "Timer task doing work");
	      callog();
			getinboxsms();
			getsentsms();
	    }
	  };
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	@Override
	public void onCreate() {
		Log.d(TAG, "onCreate");
		data=new DataBaseHelper(this);
		 timer = new Timer("Smart Address Book");
		 timer.schedule(updateTask, 1000L, 300 * 1000L);
		
	}
	@Override
	public void onDestroy() {
		Log.d(TAG, "onDestroy");
		
	}
	@Override
	public void onStart(Intent intent, int startid) {
		Log.d(TAG, "onStart");
		
	}
	/*type :function
 	name:callog
 	return type:void          
	date:10-2-12
 	purpose:imports callog from device*/
	private void callog()
    {
           ContentResolver contentResolver = getContentResolver();
		
		Uri callUri = Uri.parse("content://call_log/calls");
		String strOrder = android.provider.CallLog.Calls.DATE + " DESC";
		Cursor cur = contentResolver.query(callUri, null, null, null, strOrder);
		// loop through cursor
		while (cur.moveToNext())
		{
		no1 = cur.getString(cur.getColumnIndex(android.provider.CallLog.Calls.NUMBER));
		date= cur.getLong(cur.getColumnIndex(android.provider.CallLog.Calls.DATE));
		 SimpleDateFormat sdf=new SimpleDateFormat("MMM dd yyyy");
         Date resultdate = new Date(date);
         SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm");
         Time resulttime=new Time(date) ;
         logtype1 = cur.getInt(cur.getColumnIndex(android.provider.CallLog.Calls.TYPE));
		time1 = cur.getString(cur.getColumnIndex(android.provider.CallLog.Calls.DURATION));
		duration=time1+" "+"sec";
		 date1=""+resultdate; 
  	     time2=""+resulttime;
  	   c_id2=cur.getInt(cur.getColumnIndex(android.provider.CallLog.Calls._ID));
  	   Cursor c=data.getlog_id(c_id2);
 	    int cnt=c.getCount();
 	    if(cnt==0)
 	    {
 	    	data.InsertcallLog(c_id2, date1,time2, duration, no1,logtype1);
 	    	System.out.println("ci...."+c_id2);
         System.out.println("Du...."+duration);
         System.out.println("Date...."+resultdate);
         System.out.println("Time...."+resulttime);
         System.out.println("No...."+no1);
 	    }
 	    c.close();
		}
		cur.close();
      }
	/*type :function
 	name:getinboxsms
 	return type:void          
	date:10-2-12
 	purpose:imports sms from device*/
	public void getinboxsms()
    {
    	ContentResolver contentResolver = getContentResolver();
    	Cursor cursor = contentResolver.query( Uri.parse( "content://sms/inbox" ), null, null, null, null);

    	int indexBody = cursor.getColumnIndex( SmsReceiver.BODY );
    	int indexAddr = cursor.getColumnIndex( SmsReceiver.ADDRESS );
    	int indexdate = cursor.getColumnIndex( SmsReceiver.DATE );
    	 int indexid=cursor.getColumnIndex(SmsReceiver.ID);
    	 int indexread=cursor.getColumnIndex(SmsReceiver.READ);
    	 int indextype=cursor.getColumnIndex(SmsReceiver.TYPE);
    	 int indexseen=cursor.getColumnIndex(SmsReceiver.SEEN);
    	 int indexthreadid=cursor.getColumnIndex(SmsReceiver.Thread_ID);
         	if ( indexBody < 0 || !cursor.moveToFirst() ) 
    		return;
    	
    	

    	do
    	{
    		System.out.println("Sender: " + cursor.getString( indexAddr ) + " body " + cursor.getString( indexBody )+ " name"+
    				cursor.getString(indexdate));
    		System.out.println("thread id "+indexthreadid);
    		System.out.println("Read "+cursor.getString(indexread));
    		System.out.println("Seen "+cursor.getString(indexseen));
    		System.out.println("type "+cursor.getString(indextype));
    		ssid=cursor.getString(indexid);
    		System.out.println("Ssms Id "+ssid);
    		ssid1=Integer.parseInt(ssid);
    		sender= cursor.getString( indexAddr );
    		msg= cursor.getString( indexBody );
    		long smsdate1= cursor.getLong( indexdate );
    		System.out.println("Date...............+"+smsdate);
            SimpleDateFormat sdf=new SimpleDateFormat("MMM dd yyyy");
            Date resultdate1 = new Date(smsdate1);
            System.out.println(sdf.format(resultdate1)); 
            smsdate=""+resultdate1;
            SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm");
            Time resulttime3=new Time(smsdate1) ;
            String smstime=""+resulttime3;
            System.out.println(sdf1.format(resulttime3)); 
    		Cursor c=data.getinboxsms(ssid1);
      	    int cnt=c.getCount();
      	    if(cnt==0)
      	    {
      	    	data.InsertsmsR(sender,msg,smsdate,smstime,ssid1);
      	    	
       	    }
      	    c.close();
    		
    	}
    	while(cursor.moveToNext());
   }
	/*type :function
 	name:getsentsms
 	return type:void          
	date:10-2-12
 	purpose:imports sent sms from device*/
	 public void getsentsms()
	    {

	    	ContentResolver contentResolver = getContentResolver();
	    	Cursor cursor = contentResolver.query( Uri.parse( "content://sms/sent" ), null, null, null, null);

	    	int indexBody = cursor.getColumnIndex( SmsReceiver.BODY );
	    	int indexAddr = cursor.getColumnIndex( SmsReceiver.ADDRESS );
	    	int indexdate = cursor.getColumnIndex( SmsReceiver.DATE );
	    	 int indexid=cursor.getColumnIndex(SmsReceiver.ID);
	    	if ( indexBody < 0 || !cursor.moveToFirst() ) 
	    		return;
	    	
	    	

	    	do
	    	{
	    		System.out.println("Receiver: " + cursor.getString( indexAddr ) + " body " + cursor.getString( indexBody )+ " name"+
	    				cursor.getString(indexdate));
	    		
	    		sid=cursor.getString(indexid);
	    		System.out.println("Ssms Id "+sid);
	    		sid1=Integer.parseInt(sid);
	    		receiver= cursor.getString( indexAddr );
	    		recmsg= cursor.getString( indexBody );
	    		long smsrecdate1= cursor.getLong( indexdate );
	    		System.out.println("Date...............+"+smsrecdate1);
	    		SimpleDateFormat sdf=new SimpleDateFormat("MMM dd yyyy");
	    		Date resultdate2 = new Date(smsrecdate1);
	            System.out.println(sdf.format(resultdate2)); 
	            SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm");
	            Time resulttime2=new Time(smsrecdate1) ;
	            System.out.println(sdf1.format(resulttime2)); 
	              String smsrectime=""+resulttime2;
	              smsrecdate=""+resultdate2;
	    	     Cursor c=data.getsentsms(sid1);
	      	    int cnt=c.getCount();
	      	    if(cnt==0)
	      	    {
	      	    	data.InsertsmsS(receiver,recmsg,smsrecdate,smsrectime,sid1);
	      	    	
	       	    }
	      	    c.close();
	    		
	    	}
	    	while(cursor.moveToNext());

	    }	
}

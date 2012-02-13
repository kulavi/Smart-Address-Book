//SMS
package com.SAB_v1;


import java.util.ArrayList;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SMSGroup extends Activity {
    private static final int MAX_SMS_MESSAGE_LENGTH = 160; 
        private static final int SMS_PORT = 8091;
        private static final String SMS_DELIVERED = "SMS_DELIVERED";
        private static final String SMS_SENT = "SMS_SENT";
        DataBaseHelper data;
        int conid,count,sno;
        EditText phno;
        String fname,cont,cont1;
        int cnt=0,id6,cont_id,cont_id1;
    	String tn="";
    	String tn1,mno,cid;              
    	String id,id4;
        int no;
        String sum="";
        Button cancel;
        String id3,cid1,cid2,num,img,numh,numo,numw;
    	String name1,n,n1,oname;
    	String phonenumber,phonenumber1,phonenumber2;
    	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sms);
        data=new DataBaseHelper(this);
        Bundle bundle=getIntent().getExtras();
		no=bundle.getInt("groupid");
		phno=(EditText)findViewById(R.id.phonenumber);
		System.out.println("Sms "+no); 
		Cursor c=data.getgcont_id(no);
		System.out.println("ID1 "+no);
		while (c.moveToNext())
		{	
         cont=c.getString(0);
         System.out.println("CId is "+cont);   
         cont_id=Integer.parseInt(cont);
         System.out.println("CId is "+cont_id);
         Cursor c1=data.getcontacts(cont_id);
         while(c1.moveToNext())
         {
        	 n=c1.getString(0);    
        	 n1=c1.getString(1);
        	 num=c1.getString(2);
           	 numw=c1.getString(4);
        	 numo=c1.getString(5);
        	 sum=sum+","+num;
           	 System.out.println("Sum........."+sum);
        	 System.out.println("Fname and Lname and Num "+n+" "+n1+" "+num+" "+numw+" "+numo);
        	 
          }
         c1.close();
          
		}
		c.close();
		 System.out.println("Sum........."+sum);
    	 phno.setText(sum);
		cancel=(Button)findViewById(R.id.cancel);        
		cancel.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View view) 
	            {
	                  finish();            	
	            }
	        });
       ((Button)findViewById(R.id.send)).setOnClickListener(onButtonClick);
        
        registerReceiver(sendreceiver, new IntentFilter(SMS_SENT));
        registerReceiver(deliveredreceiver, new IntentFilter(SMS_DELIVERED));
        
        registerReceiver(smsreceiver, new IntentFilter("android.provider.Telephony.SMS_RECEIVED"));
    }
    
    @Override
        protected void onDestroy()
        {
        unregisterReceiver(sendreceiver);
        unregisterReceiver(deliveredreceiver);
        unregisterReceiver(smsreceiver);
                
                super.onDestroy();
        }
    /*Type :Function
	name:sendSms
	return type:void
	date:29-6-11
	purpose:To send sms*/	
        private void sendSms(String phonenumber,String message, boolean isBinary)
    {
        SmsManager manager = SmsManager.getDefault();
        
        PendingIntent piSend = PendingIntent.getBroadcast(this, 0, new Intent(SMS_SENT), 0);
        PendingIntent piDelivered = PendingIntent.getBroadcast(this, 0, new Intent(SMS_DELIVERED), 0);
        
        if(isBinary)
        {
                byte[] data = new byte[message.length()];
                
                for(int index=0; index<message.length() && index < MAX_SMS_MESSAGE_LENGTH; ++index)
                {
                        data[index] = (byte)message.charAt(index);
                }
                
                manager.sendDataMessage(phonenumber, null, (short) SMS_PORT, data,piSend, piDelivered);
        }
        else
        {
                int length = message.length();
                
                if(length > MAX_SMS_MESSAGE_LENGTH)
                {
                        ArrayList<String> messagelist = manager.divideMessage(message);
                        
                        manager.sendMultipartTextMessage(phonenumber, null, messagelist, null, null);
                }
                else
                {
                        manager.sendTextMessage(phonenumber, null, message, piSend, piDelivered);
                }
        }
    }
    
    private View.OnClickListener onButtonClick = new View.OnClickListener()
        {
                public void onClick(View v)
                {
                        switch(v.getId())
                        {
                                case R.id.send:
                                {
                                   	 String message = ((TextView)findViewById(R.id.message)).getText().toString();
                                     boolean isBinary = ((CheckBox)findViewById(R.id.binary)).isChecked();
                                     Cursor c=data.getgcont_id(no);
                            		System.out.println("ID1 "+no);
                            		while (c.moveToNext())
                            		{	
                                     cont1=c.getString(0);
                                     System.out.println("CId is "+cont1);   
                                     cont_id1=Integer.parseInt(cont1);
                                     System.out.println("CId is "+cont_id1);
                                     Cursor c1=data.getcontacts(cont_id1);
                                     while(c1.moveToNext())
                                     {
                                    	 
                                    	 phonenumber=c1.getString(2);
                                    	
                                    	 if(phonenumber==null)
                                    	 {
                                    		 phonenumber1=c1.getString(3);
                                    		 sendSms(phonenumber1,message, isBinary);
                                    	 }
                                    	 else if(phonenumber1==null)
                                    	 {
                                    		 phonenumber2=c1.getString(3);
                                    		 sendSms(phonenumber2,message, isBinary);
                                    	 }
                                    	 else if(phonenumber!=null)
                                    	 {
                                    	 sendSms(phonenumber,message, isBinary);
                                    	 }
                                      }
                                     c1.close();
                                      
                            		}
                            		c.close();
                                	    
                                       
                                        Cursor c3= data.getscnt(conid);
                                		while(c3.moveToNext())
                                		{
                                			count=c3.getInt(0);
                                		}	
                                		if(count==0)
                                		{
                                			count=count+1;
                                			System.out.println("Count "+count);
                                			data.insertsmslog(conid,count);
                                
                                		}
                                		else
                                		{
                                			count=count+1;
                                			data.updatesmscnt(conid, count);
                                   		}
                                		Cursor c4=data.getsmsdata();
                                		while(c.moveToNext())
                                		{
                                			int s1=c4.getInt(0);
                                			int s2=c4.getInt(1);
                                			System.out.println("SID "+s1);
                                			System.out.println("Cnt "+s2);
                                		}
                                		
                                        break;
                                }
                        }
                }
        };
        
        private BroadcastReceiver sendreceiver = new BroadcastReceiver()
        {
                @Override 
                public void onReceive(Context context, Intent intent)
                {
                        String info = "Send information: ";
                        
                        switch(getResultCode())
                        {
                                case Activity.RESULT_OK: info += "send successful"; break;
                                case SmsManager.RESULT_ERROR_GENERIC_FAILURE: info += "send failed, generic failure"; break;
                                case SmsManager.RESULT_ERROR_NO_SERVICE: info += "send failed, no service"; break;
                                case SmsManager.RESULT_ERROR_NULL_PDU: info += "send failed, null pdu"; break;
                                case SmsManager.RESULT_ERROR_RADIO_OFF: info += "send failed, radio is off"; break;
                        }
                        
                        Toast.makeText(getBaseContext(), info, Toast.LENGTH_SHORT).show();
                        call();

                }
        };
        public void call()
        {
        	Intent i=new Intent(this,ViewGroups.class);
        	startActivity(i);
        }
        private BroadcastReceiver deliveredreceiver = new BroadcastReceiver()
        {
                @Override
                public void onReceive(Context context, Intent intent)
                {
                        String info = "Delivery information: ";
                        
                        switch(getResultCode())
                        {
                                case Activity.RESULT_OK: info += "delivered"; break;
                                case Activity.RESULT_CANCELED: info += "not delivered"; break;
                        }
                        
                        Toast.makeText(getBaseContext(), info, Toast.LENGTH_SHORT).show();
                }
        };
        
        private BroadcastReceiver smsreceiver = new BroadcastReceiver()
        {
                @Override
                public void onReceive(Context context, Intent intent)
                {
                Bundle bundle = intent.getExtras();        
                SmsMessage[] msgs = null;
                
                if(null != bundle)
                {
                        String info = "Text SMS from ";
                    Object[] pdus = (Object[]) bundle.get("pdus");
                    msgs = new SmsMessage[pdus.length];          
                    
                    for (int i=0; i<msgs.length; i++){
                        msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);                
                        info += msgs[i].getOriginatingAddress();                     
                        info += "\n*****TEXT MESSAGE*****\n";
                        info += msgs[i].getMessageBody().toString();
                    }

                    Toast.makeText(context, info, Toast.LENGTH_SHORT).show();
                }                         
                }
        };
        @Override
    	public void onBackPressed()
    	{	
        	Intent i=new Intent(SMSGroup.this,ViewGroups.class);
    		startActivity(i);
    	}
}

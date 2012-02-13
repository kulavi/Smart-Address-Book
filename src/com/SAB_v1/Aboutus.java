package com.SAB_v1;
import java.util.Calendar;
import java.util.GregorianCalendar;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
public class Aboutus extends Activity{
	String tag,sdate;
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        
	        final Dialog dialog = new Dialog(Aboutus.this);
			dialog.setContentView(R.layout.about_us_dialog);
			dialog.setTitle("About Smart Address Book");
			dialog.setCancelable(true);
			TextView slideshowDialog = (TextView) dialog.findViewById(R.id.slideshowDialog);
			TextView slideshowDialog1 = (TextView) dialog.findViewById(R.id.slideshowDialog1);
			Calendar cal = new GregorianCalendar();
			int month = cal.get(Calendar.MONTH);
			int year = cal.get(Calendar.YEAR);
			int day = cal.get(Calendar.DAY_OF_MONTH);    
			sdate=(+ day + "-" + (month+1)+ "-" +year);  
			try
			{     
			    String app_ver = this.getPackageManager().getPackageInfo(this.getPackageName(), 0).versionName;
			    System.out.println("text........"+app_ver);
			    slideshowDialog.setText("Smart Address Book\nVersion - "+app_ver+"\n Date - "+sdate);
			}
			catch (NameNotFoundException e)
			{
			    Log.v(tag, e.getMessage());
			}

			slideshowDialog1.setText("\nSAB provides smart contacts searching and internet syncing service,creating call list and more....\n\nFor More Info Visit \n\nhttp://appmall.us/");
			
			Button ok = (Button) dialog.findViewById(R.id.ok);
			 ok.setOnClickListener(new OnClickListener() {

             public void onClick(View v) {
                     dialog.dismiss();
                     
                     Intent i = new Intent(Aboutus.this,Main.class);
 					 startActivity(i);
                     
                 }
             });
             dialog.show();
	        
	 }

}

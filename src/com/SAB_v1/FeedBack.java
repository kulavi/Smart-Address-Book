package com.SAB_v1;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class FeedBack extends Activity implements RatingBar.OnRatingBarChangeListener
{
    /**
     * Called with the activity is first created.
     */
	
	DataBaseHelper data;
	int conid,count;
	RatingBar rating,calllistrating,tasksrating,syncrating; // declare RatingBar object
	TextView ratingText;// declare TextView Object
	String fname,mail,name;
	String userid,password,mobile;
	TextView text1;
	String semail,smobno,text;
	 EditText uname,pass,mob,note;
	 String email,pwd;
	 String tagrate,calllistrate,tasksrate,syncrate; 
    @Override
    public void onCreate(Bundle icicle) 
    {
        super.onCreate(icicle);
        final Dialog dialog = new Dialog(FeedBack.this);
		dialog.setContentView(R.layout.feedback);
		dialog.setTitle("Click Stars to give Rating & send feedback");
		dialog.setCancelable(true);
        data=new DataBaseHelper(this);
		Window window = getWindow();
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
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

       
		 final Button send = (Button)dialog .findViewById(R.id.send);
         final Button cancel = (Button) dialog.findViewById(R.id.cancel);
         note=(EditText)dialog.findViewById(R.id.unotes);
         rating=(RatingBar)dialog.findViewById(R.id.rating_tag);// create RatingBar object
         rating.setOnRatingBarChangeListener(this);
         calllistrating=(RatingBar)dialog.findViewById(R.id.rating_call);// create RatingBar object
        calllistrating.setOnRatingBarChangeListener(this);
         tasksrating=(RatingBar)dialog.findViewById(R.id.rating_tasks);// create RatingBar object
         tasksrating.setOnRatingBarChangeListener(this);
         syncrating=(RatingBar)dialog.findViewById(R.id.rating_sync);// create RatingBar object
         syncrating.setOnRatingBarChangeListener(this);
                 
     cancel.setOnClickListener(new View.OnClickListener() {
         public void onClick(View view) 
         {
               finish();            	
         }
     });
     send.setOnClickListener(new View.OnClickListener() 
     {
         public void onClick(View view)
         {
         	text=note.getText().toString();
        	 GMailSender m = new GMailSender(email, pwd); 
			  String toArr []= {"feedback.sab@gmail.com"}; 
			  m.setTo(toArr);
		      m.setFrom(email); 
		      m.setSubject("Feed Back for SAB"); 
		      m.setBody("Tags Rating:  "+tagrate+" \nCall List Rating:  "+calllistrate+"\nTasks Rating:  "+tasksrate+"\nSync Rating:  "+syncrate+"\nComment: "+text); 
		     data.Insertfeedback(email, mobile, tagrate, calllistrate, tasksrate, syncrate);
		     Cursor c5=data.getfeedback();
		     while(c5.moveToNext())
		     {
		    	 String em=c5.getString(1);
		    	 String mo=c5.getString(2);
		    	 String ta=c5.getString(3);
		    	 String ca=c5.getString(4);
		    	 String tas=c5.getString(5);
		    	 String sy=c5.getString(6);
		    	 System.out.println("email+mob+ta+ca+tas+sy "+em+" "+mo+" "+ta+" "+ca+" "+tas+" "+sy);
		     }
		     c5.close();
		      try 
		      { 
		    	  	if(m.send())
		    	  	{ 
		          Toast.makeText(FeedBack.this, "FeedBack Sent.", Toast.LENGTH_LONG).show();
		          
		        
		        } else 
		        { 
		          Toast.makeText(FeedBack.this, "FeedBack was not sent.", Toast.LENGTH_LONG).show(); 
		        } 
		      } catch(Exception e) 
		      { 
		         
		        Log.e("MailApp", "Could not send email", e); 
		      } 
		      //dialog.dismiss();
         
             }
     });
     dialog.show();
    }
      
   
    public void onRatingChanged(RatingBar ratingBar,float rating, boolean fromUser)
    {
		tagrate=""+this.rating.getRating(); // display rating as number in TextView, use "this.rating" to not confuse with "float rating"
		System.out.println("Tags Rating"+tagrate);
		calllistrate=""+this.calllistrating.getRating(); // display rating as number in TextView, use "this.rating" to not confuse with "float rating"
		System.out.println("Call List Rating"+calllistrate);
		tasksrate=""+this.tasksrating.getRating(); // display rating as number in TextView, use "this.rating" to not confuse with "float rating"
		System.out.println("Tasks Rating"+tasksrate);
		syncrate=""+this.syncrating.getRating(); // display rating as number in TextView, use "this.rating" to not confuse with "float rating"
		System.out.println("Sync Rating"+syncrate);
	}
   

    
    @Override
	public void onBackPressed()
	{	
    	Intent i=new Intent(FeedBack.this,Main.class);
		startActivity(i);
	}
}

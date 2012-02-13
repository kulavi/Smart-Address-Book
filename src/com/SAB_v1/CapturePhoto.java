package com.SAB_v1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import android.app.Activity;
import android.content.Intent;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;

public class CapturePhoto extends Activity 
{
	private static final String TAG = "CameraDemo";
	Camera camera;
	Preview preview;
	Button buttonClick,buttonback;
	String struname,struserid;
	int intuserid,week;
	DataBaseHelper data;
	
	

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.takephoto);   
		preview = new Preview(this);
		((FrameLayout) findViewById(R.id.preview)).addView(preview);
		data=new DataBaseHelper(this);
    	buttonClick = (Button) findViewById(R.id.buttonClick);
		buttonClick.setOnClickListener( new OnClickListener() {
			public void onClick(View v) {
				preview.camera.takePicture(shutterCallback, rawCallback, jpegCallback);
			
				
				
			}
		});			
		Log.d(TAG, "onCreate'd");
	}


	ShutterCallback shutterCallback = new ShutterCallback() {
		public void onShutter() {
			Log.d(TAG, "onShutter'd");
		}
	};

	/** Handles data for raw picture */
	PictureCallback rawCallback = new PictureCallback() {
		public void onPictureTaken(byte[] data, Camera camera) {
			Log.d(TAG, "onPictureTaken - raw");
			
			
			
		}
	}; 

	/** Handles data for jpeg picture */
	PictureCallback jpegCallback = new PictureCallback() 
	{
		public void onPictureTaken(byte[] data, Camera camera) 
		{
			  
			 	
			int ff=new File(Environment.getExternalStorageDirectory()+"/SAB/businesscards").list().length;
			    ff=ff+1;
			    String ss=String.valueOf(ff);
				  
			FileOutputStream outStream=null ;
			    
			try {
			
				outStream = new FileOutputStream(Environment.getExternalStorageDirectory()+"/SAB/businesscards"+"/"+ss+".jpg");
				//outStream = new FileOutputStream(Environment.getExternalStorageDirectory()+"/Pan_Momies/week21"+"/sush.jpg");
				//outStream = new FileOutputStream(String.format("/sdcard/%d.jpg", System.currentTimeMillis()));
				outStream.write(data);
				String we=data.toString();
				outStream.close();
				Log.d(TAG, "onPictureTaken - wrote bytes: " + data.length);
				Log.d(TAG, "onPict88888*********************************************** " + we);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
			}
			
			Log.d(TAG, "onPictureTaken - jpeg.......................................................");
			Intent i=new Intent();
			setResult(RESULT_OK,i); 
			finish();
		}
	};
	@Override
	public void onBackPressed()
	{
		Intent i=new Intent(CapturePhoto.this,Main.class);
		startActivity(i);
	
		return;
	}


}

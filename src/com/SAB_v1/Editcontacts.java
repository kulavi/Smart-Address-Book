//edit contacts
package com.SAB_v1;
import java.util.Calendar;
import java.util.GregorianCalendar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.MediaColumns;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Editcontacts extends Activity
{
	DataBaseHelper data;
	String wadd1,orgcw1,orgco1,orgpw1,orgpo1,compadd1,nicname1,padd1,paw1,pao1,bday1,ani1;
	String bday,org,ani;
	String wadd,orgcw,orgco,orgpw,orgpo,compadd;
	String nicname,padd,paw,pao;
	String smobw;
	TableLayout table;
	 private Runnable viewLocation;
	 private ArrayAdapter<CharSequence> adapterem,adapteremw,adapteremo;
	 private ProgressDialog m_ProgressDialog = null;
	Camera camera;  
	String img,smn,semn;
	String semailw,semailo,semailcust;
	SurfaceView surfaceView;
	SurfaceHolder surfaceHolder;
	boolean previewing = false;
	LayoutInflater controlInflater = null;
	Button buttonTakePicture=null;
	Spinner shome,swork,smob,soth;
	Spinner sehome,sework,seoth;             
	int flag,flag1,f=0;
		TextView text1;
		String seemailw,seemailo;
	String scust,strmn;
	Button addex,remno,remh,remw,remo,up;
	Button addeex,remhe,remwe,remoe,bcancel,can;
	String mshome,mswork,msother,mscust;
	EditText mwork,mhome,moth,ecust,mno;
	String eshome,eswork,esother,escust,mname;
	EditText ework,ehome,eoth;
	private ArrayAdapter<CharSequence> adapter;
	ArrayAdapter <CharSequence> adapter1,adapter2,adapter3;
	ArrayAdapter  <CharSequence> ehadapter,ewadapter,eoadapter;
	Button b1,b3,bim,back,clear,aclear,aback,cancel;
	TextView b2;
	ImageView img3,img2;
	EditText fn,ln,mn,mobno,eid,eimg,tag;
	String strfn,strln,streid,strimg,strmno,strtag;
	String name,ans;
	String img1,sdate;
	int column_index,id;
	Intent intent=null;
	String logo,imagePath,Logo;
	private static final int SELECT_PICTURE = 1; 
	String selectedImagePath;
	 private int mYear;
	 private int mMonth;
	 private int mDay;
	 private String array_spinner1[],array_spinner[],array_spinner2[],array_spinner3[];
	 private String array_spinnerhe[],array_spinnerwe[],array_spinneroe[];
	Button badd,bvl,bri;
	EditText vl,rin,wadds,com,comadd,jtit,nn,bir,aniv,pa;
	String strvlink="",strring="",strwadd="";
	String strcomp="",strcompadd="",strjtitle="",name1="";
	String strnname="",strpadd="",strbirth="",strani="",lname="";
	//Add more info 
	EditText eorghc,eorgwc,eorgoc;
	EditText eorghp,eorgwp,eorgop;
	EditText epah,epaw,epao;
	Spinner spah,sorgw,sorgo,spaw,spao;
	int flag2,flag3;
	Button addmoreorg,addmorepa;
	Button rempah,remorgw,remorgo,rempaw,rempao;
	ArrayAdapter  <CharSequence> adapterorgw,adapterorgo;
	ArrayAdapter  <CharSequence> adapterpah,adapterpaw,adapterpao;
	 private String array_spinnerorgw[],array_spinnerorgo[];
	 private String array_apinnerpah[],array_spinnerpaw[],array_spinnerpao[];
	 String strorgcw="",strorgco="",strorgpw="",strorgpo="",strorgcust="";
	 String strpao="",strpacust="",strpaw="";
	static final int DATE_DIALOG_ID = 0;
	int inid1,inid;
	String sid;
	String sln,sfn,smno,semail,stag,sn,sn1,simg;
	String seln,sefn,semno,seemail,setag,sen;
	String sdln,sdfn,sdmno,sdemail,sdtag,sdn;
	String[] s;
	EditText edmobh,edmobw,edmobo,etask,edescp;
	String smnoh,smnow,smnoo,stask,sdescp;
	String smnoh1,smnow1,smnoo1,stask1,sdescp1;
	//ADDED
	String filemanagerstring;
	public void onCreate(Bundle savedInstanceState)
	{
	
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		data=new DataBaseHelper(this);
		data.open();
		Bundle bundle=getIntent().getExtras();
		inid1=bundle.getInt("inid");
		sid=""+inid1;
		System.out.println("Edit id..."+inid1);
		setContentView(R.layout.edit_contacts);
		Window window = getWindow();
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		 Calendar cal = new GregorianCalendar();
			int month = cal.get(Calendar.MONTH);
			int year = cal.get(Calendar.YEAR);
			int day = cal.get(Calendar.DAY_OF_MONTH);
			sdate=(+ (month + 1) + "-" + day + "-" + year);  
		 text1=(TextView)findViewById(R.id.text1);
		fn=(EditText)findViewById(R.id.fname);
		ln=(EditText)findViewById(R.id.lname);
		mn=(EditText)findViewById(R.id.mname);
		img2= (ImageView)findViewById(R.id.gimg1);
		mobno=(EditText)findViewById(R.id.mmno);
		eid=(EditText)findViewById(R.id.ehmail);
		tag=(EditText)findViewById(R.id.atags);
		mhome=(EditText)findViewById(R.id.hmno);
		mwork=(EditText)findViewById(R.id.wmno);
		moth=(EditText)findViewById(R.id.omno);
		remno=(Button)findViewById(R.id.mnoexp);
		remh=(Button)findViewById(R.id.hmnexp);
		remw=(Button)findViewById(R.id.wmnexp);
		remo=(Button)findViewById(R.id.omnexp);
		ework=(EditText)findViewById(R.id.ewmail);
		eoth=(EditText)findViewById(R.id.eomail);
		remhe=(Button)findViewById(R.id.bhemail);
		remwe=(Button)findViewById(R.id.bwmail);
		remoe=(Button)findViewById(R.id.bomail);
		//Mobile no spinner
		smob= (Spinner) findViewById(R.id.smob);
		adapter2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item);
		adapter2.add("Mobile");
		adapter2.add("Home");
		adapter2.add("Work");
		adapter2.add("Work Fax");
		adapter2.add("Home Fax");
		adapter2.add("Pager");
		adapter2.add("Other");
		adapter2.add("Custom");
		smob.setAdapter(adapter2);
		//Mobile no home spinner
		shome= (Spinner) findViewById(R.id.shome);
		adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item);
		adapter.add("Home");
		adapter.add("Mobile");
		adapter.add("Work");
		adapter.add("Work Fax");
		adapter.add("Home Fax");
		adapter.add("Pager");
		adapter.add("Other");
		adapter.add("Custom");
		
		shome.setAdapter(adapter);
		//Mobile no  work spinner
		swork= (Spinner) findViewById(R.id.swork);
		adapter1 = new ArrayAdapter(this,android.R.layout.simple_spinner_item);
		adapter1.add("Work");
		adapter1.add("Mobile");
		adapter1.add("Home");
		adapter1.add("Work Fax");
		adapter1.add("Home Fax");
		adapter1.add("Pager");
		adapter1.add("Other");
		adapter1.add("Custom");
		swork.setAdapter(adapter1);
		//Mobile no other spinner
		soth= (Spinner) findViewById(R.id.sother);
		adapter3 = new ArrayAdapter(this,android.R.layout.simple_spinner_item);
		adapter3.add("Other");
		adapter3.add("Mobile");
		adapter3.add("Work");
		adapter3.add("Work Fax");
		adapter3.add("Home Fax");
		adapter3.add("Pager");
		adapter3.add("Home");
		adapter3.add("Custom");
		soth.setAdapter(adapter3);
		//Add More fields button for mobile no
		addex=(Button)findViewById(R.id.mmnexp);
		addex.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				if(flag==1)
				{
			    mhome.setVisibility(0);
			    shome.setVisibility(0);
			    remh.setVisibility(0);
			    flag=2;
				}
				else
				if(flag==2)
				{
			    mwork.setVisibility(0);
			    swork.setVisibility(0);
			    remw.setVisibility(0);
			    flag=3;
				}
				else
				if(flag==3)
				{
			    moth.setVisibility(0);
			    soth.setVisibility(0);
			    remo.setVisibility(0);
			    flag=1;
			    }
			}
			});
		//remove particular field button
		remno.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				
			    mobno.setVisibility(View.GONE);
			    smob.setVisibility(View.GONE);
			    remno.setVisibility(View.GONE);
			    flag=1;
			    flag=2;
			    flag=3;
			    				
			}
		});	
		//remove particular field button
		remh.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				
			    mhome.setVisibility(View.GONE);
			    shome.setVisibility(View.GONE);
			    remh.setVisibility(View.GONE);
			     flag=1;
			    flag=2;
			    flag=3;
			    				
			}
		});	
		//remove particular field button
		remw=(Button)findViewById(R.id.wmnexp);
		remw.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				
			    mwork.setVisibility(View.GONE);
			    swork.setVisibility(View.GONE);
			    remw.setVisibility(View.GONE);
			    flag=1;
			    flag=2;
			    flag=3;
			    				
			}
		});	
		//remove particular field button
		remo=(Button)findViewById(R.id.omnexp);
		remo.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				
			    moth.setVisibility(View.GONE);
			    soth.setVisibility(View.GONE);
			    remo.setVisibility(View.GONE);
			    flag=1;
			    flag=2;
			    flag=3;
			    				
			}
		});	
		int spi=shome.getSelectedItemPosition();
   
		smob.setOnItemSelectedListener(
				new AdapterView.OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> a,View view,int position,long id)
					{
						if(position==7)
						{
							 final Dialog dialog3 = new Dialog(Editcontacts.this);

		                      dialog3.setContentView(R.layout.dialog);
		                      dialog3.setTitle("Custom Label Name");


		                       dialog3.setCancelable(true);
		                       //set up button
		                       Button button = (Button) dialog3.findViewById(R.id.ok);
		                       ecust=(EditText)dialog3.findViewById(R.id.ecustom);

		                       button.setOnClickListener(new OnClickListener() {

		                       public void onClick(View v) 
		                       {
	                               
		                    	      
		                               
		                               addNewSpinnerItem3();
		                               dialog3.dismiss();
		                               


		                           }

		                       });
		                       Button button1 = (Button) dialog3.findViewById(R.id.cancel);

		                         button1.setOnClickListener(new OnClickListener() {

		                       public void onClick(View v) {

		                               finish();

		                           }

		                       });

		                       //now that the dialog is set up, it's time to show it    

		                       dialog3.show();

		                   }



						}

					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
						
					}
				   });
		shome.setOnItemSelectedListener(
				new AdapterView.OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> a,View view,int position,long id)
					{
						if(position==7)
						{
							 final Dialog dialog = new Dialog(Editcontacts.this);

		                      dialog.setContentView(R.layout.dialog);
		                      dialog.setTitle("Custom Label Name");


		                       dialog.setCancelable(true);
		                       //set up button
		                       Button button = (Button) dialog.findViewById(R.id.ok);
		                       ecust=(EditText)dialog.findViewById(R.id.ecustom);

		                       button.setOnClickListener(new OnClickListener() {

		                       public void onClick(View v) 
		                       {
	                               
		                    	      
		                               
		                               addNewSpinnerItem();
		                               dialog.dismiss();
		                               


		                           }

		                       });
		                       Button button1 = (Button) dialog.findViewById(R.id.cancel);

		                       button1.setOnClickListener(new OnClickListener() {

		                       public void onClick(View v) {

		                               finish();

		                           }

		                       });

		                       //now that the dialog is set up, it's time to show it    

		                       dialog.show();

		                   }



						}

					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
						
					}
				   });
		swork.setOnItemSelectedListener(
				new AdapterView.OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> a,View view,int position,long id)
					{
						if(position==7)
						{
							 final Dialog dialog1 = new Dialog(Editcontacts.this);

		                      dialog1.setContentView(R.layout.dialog);
		                      dialog1.setTitle("Custom Label Name");


		                       dialog1.setCancelable(true);
		                       //set up button
		                       Button button = (Button) dialog1.findViewById(R.id.ok);
		                       ecust=(EditText)dialog1.findViewById(R.id.ecustom);

		                       button.setOnClickListener(new OnClickListener() {

		                       public void onClick(View v) 
		                       {
	                               
		                    	      
		                               
		                               addNewSpinnerItem1();
		                               dialog1.dismiss();
		                               


		                           }

							
		                       });
		                       Button button1 = (Button) dialog1.findViewById(R.id.cancel);

		                       button1.setOnClickListener(new OnClickListener() {

		                       public void onClick(View v) {

		                               finish();

		                           }

		                       });

		                       //now that the dialog is set up, it's time to show it    

		                       dialog1.show();

		                   }



						}

					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
						
					}
				   });	
		soth.setOnItemSelectedListener(
				new AdapterView.OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> a,View view,int position,long id)
					{
						if(position==7)
						{
							 final Dialog dialog2 = new Dialog(Editcontacts.this);

		                      dialog2.setContentView(R.layout.dialog);
		                      dialog2.setTitle("Custom Label Name");


		                       dialog2.setCancelable(true);
		                       //set up button
		                       Button button = (Button) dialog2.findViewById(R.id.ok);
		                       ecust=(EditText)dialog2.findViewById(R.id.ecustom);

		                       button.setOnClickListener(new OnClickListener() {

		                       public void onClick(View v) 
		                       {
	                               
		                    	      
		                               
		                               addNewSpinnerItem2();
		                               dialog2.dismiss();
		                               


		                           }

							
		                       });
		                       Button button1 = (Button) dialog2.findViewById(R.id.cancel);

		                       button1.setOnClickListener(new OnClickListener() {

		                       public void onClick(View v) {

		                               finish();

		                           }

		                       });

		                       //now that the dialog is set up, it's time to show it    

		                       dialog2.show();

		                   }



						}

					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
						
					}
				   });	
		
		//Email home spinner
		sehome= (Spinner) findViewById(R.id.shmail);
		ehadapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item);
		ehadapter.add("Home");
		ehadapter.add("Work");
		ehadapter.add("Other");
		ehadapter.add("Custom");
		sehome.setAdapter(ehadapter);
		//Email work spinner
		sework= (Spinner) findViewById(R.id.swmail);
		ewadapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item);
		ewadapter.add("Work");
		ewadapter.add("Home");
		ewadapter.add("Other");
		ewadapter.add("Custom");
		sework.setAdapter(ewadapter);			
		//Email other spinner
		seoth= (Spinner) findViewById(R.id.somail);
		eoadapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item);
		eoadapter.add("Other");
		eoadapter.add("Work");
		eoadapter.add("Home");
		eoadapter.add("Custom");
		seoth.setAdapter(eoadapter);
			   
		sehome.setOnItemSelectedListener(
				new AdapterView.OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> a,View view,int position,long id)
					{
						if(position==3)
						{
							 final Dialog dialog3 = new Dialog(Editcontacts.this);

		                      dialog3.setContentView(R.layout.dialog);
		                      dialog3.setTitle("Custom Label Name");


		                       dialog3.setCancelable(true);
		                       //set up button
		                       Button button = (Button) dialog3.findViewById(R.id.ok);
		                       ecust=(EditText)dialog3.findViewById(R.id.ecustom);

		                       button.setOnClickListener(new OnClickListener() {

		                       public void onClick(View v) 
		                       {
	                               
		                    	      
		                               
		                               addNewSpinnerItem9();
		                               dialog3.dismiss();
		                               


		                           }

		                       });
		                       Button button1 = (Button) dialog3.findViewById(R.id.cancel);

		                         button1.setOnClickListener(new OnClickListener() {

		                       public void onClick(View v) {

		                               finish();

		                           }

		                       });

		                       //now that the dialog is set up, it's time to show it    

		                       dialog3.show();

		                   }



						}

					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
						
					}
				   });
		sework.setOnItemSelectedListener(
				new AdapterView.OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> a,View view,int position,long id)
					{
						if(position==3)
						{
							 final Dialog dialog3 = new Dialog(Editcontacts.this);

		                      dialog3.setContentView(R.layout.dialog);
		                      dialog3.setTitle("Custom Label Name");


		                       dialog3.setCancelable(true);
		                       //set up button
		                       Button button = (Button) dialog3.findViewById(R.id.ok);
		                       ecust=(EditText)dialog3.findViewById(R.id.ecustom);

		                       button.setOnClickListener(new OnClickListener() {

		                       public void onClick(View v) 
		                       {
	                               
		                    	      
		                               
		                               addNewSpinnerItem10();
		                               dialog3.dismiss();
		                               


		                           }

		                       });
		                       Button button1 = (Button) dialog3.findViewById(R.id.cancel);

		                         button1.setOnClickListener(new OnClickListener() {

		                       public void onClick(View v) {

		                               finish();

		                           }

		                       });

		                       //now that the dialog is set up, it's time to show it    

		                       dialog3.show();

		                   }



						}

					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
						
					}
				   });
		seoth.setOnItemSelectedListener(
				new AdapterView.OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> a,View view,int position,long id)
					{
						if(position==3)
						{
							 final Dialog dialog3 = new Dialog(Editcontacts.this);

		                      dialog3.setContentView(R.layout.dialog);
		                      dialog3.setTitle("Custom Label Name");


		                       dialog3.setCancelable(true);
		                       //set up button
		                       Button button = (Button) dialog3.findViewById(R.id.ok);
		                       ecust=(EditText)dialog3.findViewById(R.id.ecustom);

		                       button.setOnClickListener(new OnClickListener() {

		                       public void onClick(View v) 
		                       {
	                               
		                    	      
		                               
		                               addNewSpinnerItem11();
		                               dialog3.dismiss();
		                               


		                           }

		                       });
		                       Button button1 = (Button) dialog3.findViewById(R.id.cancel);

		                         button1.setOnClickListener(new OnClickListener() {

		                       public void onClick(View v) {

		                               finish();

		                           }

		                       });

		                       //now that the dialog is set up, it's time to show it    

		                       dialog3.show();

		                   }



						}

					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
						
					}
				   });
			flag1=1;	
		//Add More fields button for email id
		addeex=(Button)findViewById(R.id.bhmail);
		addeex.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				if(flag1==1)
				{
			    ework.setVisibility(0);
			    sework.setVisibility(0);
			    remwe.setVisibility(0);
			    flag1=2;
				}
				else
				if(flag1==2)
				{
			   eoth.setVisibility(0);
			    seoth.setVisibility(0);
			    remoe.setVisibility(0);
			    flag1=1;
			    }
			}
			});
		//remove particular field button
		remhe.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				
			    eid.setVisibility(View.GONE);
			    sehome.setVisibility(View.GONE);
			    remhe.setVisibility(View.GONE);
			    flag1=1;
			    flag1=2;
			    			    				
			}
		});	
		//remove particular field button
		remwe.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				
			    ework.setVisibility(View.GONE);
			    sework.setVisibility(View.GONE);
			    remwe.setVisibility(View.GONE);
			    flag1=1;
			    flag1=2;
			    			    				
			}
		});	
		//remove particular field button
		remoe.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				
			    eoth.setVisibility(View.GONE);
			    seoth.setVisibility(View.GONE);
			    remoe.setVisibility(View.GONE);
			    flag1=1;
			    flag1=2;
			   			    				
			}
		});	
		
		
		b3 = (Button)findViewById(R.id.addm);
		b3.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{	
				f=1;
				table=(TableLayout)findViewById(R.id.table);
				table.setVisibility(0);
				call();     
			} 
		});
		b1 = (Button)findViewById(R.id.cancel);
		b1.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				finish();
				
			}
		});

		img2.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v)  
			{
				registerForContextMenu(img2);
		
			}
		});
		Cursor c=data.Data12(inid1);
		while (c.moveToNext())
		{	
			inid=c.getInt(0); 
			sfn=c.getString(1);
			smn=c.getString(2);
			sln=c.getString(3);
			smno=c.getString(4);
			smnoh=c.getString(5);
			smobw=c.getString(6);
			smnoo=c.getString(7);
			semail=c.getString(8);
			semailw=c.getString(9);
			semailo=c.getString(10);
			semailcust=c.getString(12);
			simg=c.getString(12);
			stag=c.getString(13);
			fn.setText(sfn);
			mn.setText(smn);
			ln.setText(sln);
			mobno.setText(smno);
			if((smnoh.equals(""))||(smnoh==null))
			{
				mhome.setVisibility(View.GONE);
			    shome.setVisibility(View.GONE);
			    remh.setVisibility(View.GONE);
			}
			else
			{
				mhome.setVisibility(0);
			    shome.setVisibility(0);
			    remh.setVisibility(0);
			    mhome.setText(smnoh);
			}
			if((smobw.equals(""))||(smobw==null))
			{
				mwork.setVisibility(View.GONE);
			    swork.setVisibility(View.GONE);
			    remw.setVisibility(View.GONE);
			}
			else
			{
				  mwork.setVisibility(0);
				  swork.setVisibility(0);
				  remw.setVisibility(0);
				  mwork.setText(smobw);
			}
			if((smnoo.equals(""))||(smnoo==null))
			{
				 moth.setVisibility(View.GONE);
				 soth.setVisibility(View.GONE);
				 remo.setVisibility(View.GONE);
			}
			else
			{
				 moth.setVisibility(0);
				    soth.setVisibility(0);
				    remo.setVisibility(0);
				  moth.setText(smnoo);
			}
			eid.setText(semail);
			if((semailw.equals(""))||(semailw==null))
			{
				   ework.setVisibility(View.GONE);
				    sework.setVisibility(View.GONE);
				    remwe.setVisibility(View.GONE);
				
			}
			else
			{
				   ework.setVisibility(0);
				    sework.setVisibility(0);
				    remwe.setVisibility(0);
				    ework.setText(semailw);
			}
			if((semailo.equals(""))||(semailo==null))
			{
				 eoth.setVisibility(View.GONE);
				    seoth.setVisibility(View.GONE);
				    remoe.setVisibility(View.GONE);
			}
			else
			{
				 eoth.setVisibility(0);
				    seoth.setVisibility(0);
				    remoe.setVisibility(0);
				eoth.setText(semailo);
			}
			
			
			tag.setText(stag);
		}
		c.close();
		
		
	
		
		
		up=(Button)findViewById(R.id.update);
		up.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				if(f==0)
				{
					checkvalidate();
				}
				else if(f==1)
				{
					checkvalidate();
					addmore();
				}
				
				String sid=""+inid1;
				System.out.println(" " +sid);
				Intent i=new Intent(Editcontacts.this,ContactDetails.class);
				Bundle bun=new Bundle();
				bun.putString("name2",sid);
				i.putExtras(bun);
				startActivity(i);
			}

			
		});
  Button cancel=(Button)findViewById(R.id.cancel);
  cancel.setOnClickListener(new OnClickListener() 
	{
		public void onClick(View v) 
		{
             finish();			
		}
	});
  
data.close();
	}
	public void checkvalidate() 
	{
		progressbar();
		sefn=fn.getText().toString();
		semn=mn.getText().toString();
		seln=ln.getText().toString();
		semno=mobno.getText().toString();
		smnoh1=mhome.getText().toString();
		smnow1=mwork.getText().toString();
		smnoo1=moth.getText().toString();
		seemail=eid.getText().toString();
		seemailw=ework.getText().toString();
		seemailo=eoth.getText().toString();
		setag=tag.getText().toString();
		updatecont(inid1);
		
	}
	public void addmore() 
	{
		strwadd=wadds.getText().toString();
		strcompadd=comadd.getText().toString();
		strnname=nn.getText().toString();
		strpadd=epah.getText().toString();
		strpaw=epaw.getText().toString();
		strbirth=bir.getText().toString();
		strani=aniv.getText().toString();
		strorgcw=eorgwc.getText().toString();
		strorgpw=eorgwp.getText().toString();
		strorgco=eorgoc.getText().toString();
		strorgpo=eorgop.getText().toString();
		strpao=epao.getText().toString();
		updatecont1(inid1);
	}

	
	/*Type :Function
	name:updatecont
	return type:void
	date:29-6-11
	purpose:To update contact in database*/
	public void updatecont(int contid)
	{
		data.updatecontact(contid, sefn,seln,semno,smnoh1,smnow1,smnoo1,seemail,seemailw,seemailo,imagePath,setag);
		Toast t=Toast.makeText(getBaseContext(),"Contact Updated",Toast.LENGTH_LONG);
		t.show();

	}
	public void updatecont1(int contid1)
	{
		
		data.updatemorecontact(contid1,strwadd,strorgcw,strorgco,strorgpw,strorgpo,strorgcust,strcompadd, strnname, strpadd,strpaw,strpao,strpacust, strbirth, strani);
		
	}
	public void call()
	{
		wadds=(EditText)findViewById(R.id.wadd);
		comadd=(EditText)findViewById(R.id.compadd);
		epah=(EditText)findViewById(R.id.epah);
		nn=(EditText)findViewById(R.id.nname);
		bir=(EditText)findViewById(R.id.birth);
		aniv=(EditText)findViewById(R.id.ani);
		eorgwc=(EditText)findViewById(R.id.eorgcw);
		eorgoc=(EditText)findViewById(R.id.eorgco);
		eorgwp=(EditText)findViewById(R.id.eorgpw);
		eorgop=(EditText)findViewById(R.id.eorgpo);
		epaw=(EditText)findViewById(R.id.epaw);
		epao=(EditText)findViewById(R.id.epao);
		addmoreorg=(Button)findViewById(R.id.borg);
		addmorepa=(Button)findViewById(R.id.bpa);
		rempah=(Button)findViewById(R.id.bpah);
		remorgw=(Button)findViewById(R.id.borgw);
		remorgo=(Button)findViewById(R.id.borgo);
		rempaw=(Button)findViewById(R.id.bpaw);
		rempao=(Button)findViewById(R.id.bpao);
		spah=(Spinner)findViewById(R.id.spah);
		sorgw=(Spinner)findViewById(R.id.sorgw);
		sorgo=(Spinner)findViewById(R.id.sorgo);
		spaw=(Spinner)findViewById(R.id.spaw);
		spao=(Spinner)findViewById(R.id.spao);
		
			
		//Organization  spinner for work
		adapterorgw = new ArrayAdapter(this,android.R.layout.simple_spinner_item);
		adapterorgw.add("Work");
		adapterorgw.add("Home");
		adapterorgw.add("Other");
		adapterorgw.add("Custom");
		sorgw.setAdapter(adapterorgw);
		
		//Organization  spinner for other
		adapterorgo = new ArrayAdapter(this,android.R.layout.simple_spinner_item);
		adapterorgo.add("Other");
		adapterorgo.add("Work");
		adapterorgo.add("Home");
		adapterorgo.add("Custom");
		sorgo.setAdapter(adapterorgo);
		
		//Postal address  spinner for home
		adapterpah = new ArrayAdapter(this,android.R.layout.simple_spinner_item);
		adapterpah.add("Home");
		adapterpah.add("Work");
		adapterpah.add("Other");
		adapterpah.add("Custom");
		spah.setAdapter(adapterpah);
		
		//Postal Address spinner for work
		adapterpaw = new ArrayAdapter(this,android.R.layout.simple_spinner_item);
		adapterpaw.add("Work");
		adapterpaw.add("Other");
		adapterpaw.add("Custom");
		spaw.setAdapter(adapterpaw);
		
		//Postal Address spinner for other
		adapterpao = new ArrayAdapter(this,android.R.layout.simple_spinner_item);
		adapterpao.add("Other");
		adapterpao.add("Work");
		adapterpao.add("Custom");
		spao.setAdapter(adapterpao);
		
		flag2=1;	
		//Add More fields button for org
		addmoreorg.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				 if(flag2==1)
				{
					eorgoc.setVisibility(0);
				    eorgop.setVisibility(0);
				    sorgo.setVisibility(0);
				    remorgo.setVisibility(0);
			      
				}
				}
			});
				
		//remove org field for work
		remorgw.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				
				eorgwc.setVisibility(View.GONE);
			    eorgwp.setVisibility(View.GONE);
			    sorgw.setVisibility(View.GONE);
			    remorgw.setVisibility(View.GONE);
			     flag2=1;
			    flag2=2;
			    			    				
			}
		});	
		
		//remove org field for other
		remorgo.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				
				eorgoc.setVisibility(View.GONE);
			    eorgop.setVisibility(View.GONE);
			    sorgo.setVisibility(View.GONE);
			    remorgo.setVisibility(View.GONE);
			     flag2=1;
			    flag2=2;
			    			    				
			}
		});	
		
		flag3=1;	
		//Add More fields button for org
		addmorepa.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				 if(flag3==1)
				{
			    epaw.setVisibility(0);
			    spaw.setVisibility(0);
			    rempaw.setVisibility(0);
			    
			    flag3=2;
				}
				else if(flag3==2)
				{
			    epao.setVisibility(0);
			    spao.setVisibility(0);
			    rempao.setVisibility(0);
			    flag3=1;
			  
				}
			}
			});
		//remove postal add field for home
		rempah.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				
				epah.setVisibility(View.GONE);
			    spah.setVisibility(View.GONE);
			    rempah.setVisibility(View.GONE);
			     flag3=1;
			    flag3=2;
			    			    				
			}
		});	
		//remove postal address field for work
		rempaw.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				
				epaw.setVisibility(View.GONE);
			    spaw.setVisibility(View.GONE);
			    spaw.setVisibility(View.GONE);
			    rempaw.setVisibility(View.GONE);
			      flag3=1;
			    flag3=2;
			 			    			    				
			}
		});	
		
		//remove postal address field for other
		rempao.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				
				epao.setVisibility(View.GONE);
			    spao.setVisibility(View.GONE);
			    rempao.setVisibility(View.GONE);
			       flag3=1;
			    flag3=2;
			    			    			    				
			}
		});	
		
		
		
		sorgw.setOnItemSelectedListener(
				new AdapterView.OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> a,View view,int position,long id)
					{
						if(position==3)
						{
							 final Dialog dialog5 = new Dialog(Editcontacts.this);

		                      dialog5.setContentView(R.layout.dialog);
		                      dialog5.setTitle("Custom Label Name");


		                       dialog5.setCancelable(true);
		                       //set up button
		                       Button button = (Button) dialog5.findViewById(R.id.ok);
		                       ecust=(EditText)dialog5.findViewById(R.id.ecustom);

		                       button.setOnClickListener(new OnClickListener() {

		                       public void onClick(View v) 
	                       {
		                    	   addNewSpinnerItem5();
	                               dialog5.dismiss();
	                               
	                           }

		                       });
		                       Button button1 = (Button) dialog5.findViewById(R.id.cancel);

		                       button1.setOnClickListener(new OnClickListener() {

		                       public void onClick(View v) {

		                               finish();

		                           }

		                       });

		                       //now that the dialog is set up, it's time to show it    

		                       dialog5.show();

		                   }



						}

					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
						
					}
				   });
		
		sorgo.setOnItemSelectedListener(
				new AdapterView.OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> a,View view,int position,long id)
					{
						if(position==3)
						{
							 final Dialog dialog6 = new Dialog(Editcontacts.this);

		                      dialog6.setContentView(R.layout.dialog);
		                      dialog6.setTitle("Custom Label Name");


		                       dialog6.setCancelable(true);
		                       //set up button
		                       Button button = (Button) dialog6.findViewById(R.id.ok);
		                       ecust=(EditText)dialog6.findViewById(R.id.ecustom);

		                       button.setOnClickListener(new OnClickListener() {

		                       public void onClick(View v) 
		                       {
		                    	   addNewSpinnerItem6();
	                               dialog6.dismiss();
	                               
	                           }

		                       });
		                       Button button1 = (Button) dialog6.findViewById(R.id.cancel);

		                       button1.setOnClickListener(new OnClickListener() {

		                       public void onClick(View v) {

		                               finish();

		                           }

		                       });

		                       //now that the dialog is set up, it's time to show it    

		                       dialog6.show();

		                   }



						}

					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
						
					}
				   });
		spah.setOnItemSelectedListener(
				new AdapterView.OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> a,View view,int position,long id)
					{
						if(position==3)
						{
							 final Dialog dialog4 = new Dialog(Editcontacts.this);

		                      dialog4.setContentView(R.layout.dialog);
		                      dialog4.setTitle("Custom Label Name");


		                       dialog4.setCancelable(true);
		                       //set up button
		                       Button button = (Button) dialog4.findViewById(R.id.ok);
		                       ecust=(EditText)dialog4.findViewById(R.id.ecustom);

		                       button.setOnClickListener(new OnClickListener() {

		                       public void onClick(View v) 
		                       {
		                    	   addNewSpinnerItem4();
	                               dialog4.dismiss();
	                               
	                           }

		                       });
		                       Button button1 = (Button) dialog4.findViewById(R.id.cancel);

		                       button1.setOnClickListener(new OnClickListener() {

		                       public void onClick(View v) {

		                               finish();

		                           }

		                       });

		                       //now that the dialog is set up, it's time to show it    

		                       dialog4.show();

		                   }



						}

					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
						
					}
				   });
		spaw.setOnItemSelectedListener(
				new AdapterView.OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> a,View view,int position,long id)
					{
						if(position==2)
						{
							 final Dialog dialog7 = new Dialog(Editcontacts.this);

		                      dialog7.setContentView(R.layout.dialog);
		                      dialog7.setTitle("Custom Label Name");


		                       dialog7.setCancelable(true);
		                       //set up button
		                       Button button = (Button) dialog7.findViewById(R.id.ok);
		                       ecust=(EditText)dialog7.findViewById(R.id.ecustom);

		                       button.setOnClickListener(new OnClickListener() {

		                       public void onClick(View v) 
		                       {
		                    	   addNewSpinnerItem7();
	                               dialog7.dismiss();
	                               
	                           }

		                       });
		                       Button button1 = (Button) dialog7.findViewById(R.id.cancel);

		                       button1.setOnClickListener(new OnClickListener() {

		                       public void onClick(View v) {

		                               finish();

		                           }

		                       });

		                       //now that the dialog is set up, it's time to show it    

		                       dialog7.show();

		                   }



						}

					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
						
					}
				   });
		spao.setOnItemSelectedListener(
				new AdapterView.OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> a,View view,int position,long id)
					{
						if(position==2)
						{
							 final Dialog dialog8 = new Dialog(Editcontacts.this);

		                      dialog8.setContentView(R.layout.dialog);
		                      dialog8.setTitle("Custom Label Name");


		                       dialog8.setCancelable(true);
		                       //set up button
		                       Button button = (Button) dialog8.findViewById(R.id.ok);
		                       ecust=(EditText)dialog8.findViewById(R.id.ecustom);

		                       button.setOnClickListener(new OnClickListener() {

		                       public void onClick(View v) 
		                       {
		                    	   addNewSpinnerItem8();
	                               dialog8.dismiss();
	                               
	                           }

		                       });
		                       Button button1 = (Button) dialog8.findViewById(R.id.cancel);

		                       button1.setOnClickListener(new OnClickListener() {

		                       public void onClick(View v) {

		                               finish();

		                           }

		                       });

		                       //now that the dialog is set up, it's time to show it    

		                       dialog8.show();

		                   }



						}

					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
						
					}
				   });
		
        data= new DataBaseHelper(this);
		Cursor c1=data.getDataLastName(name);
		System.out.println("MYyyyyyyyyyyyyy"+name);
		while(c1.moveToNext())
		{
			id=c1.getInt(0);
			lname=c1.getString(1);
			System.out.println("IDDDDDD....."+id);
			System.out.println("LNAme....."+lname);
		}
		c1.close();
		bir.setOnTouchListener(new OnTouchListener()
	    {
	      
			public boolean onTouch(View arg0, MotionEvent arg1)
			{

	            showDialog(DATE_DIALOG_ID);
				return false;
			}
	    });

		aniv.setOnTouchListener(new OnTouchListener()
	    {
	      
			public boolean onTouch(View arg0, MotionEvent arg1)
			{

	            showDialog(DATE_DIALOG_ID);
				return false;
			}
	    });

		// get the current date and time
		final Calendar c = Calendar.getInstance();
		mYear = c.get(Calendar.YEAR);
		mMonth = c.get(Calendar.MONTH);
		mDay = c.get(Calendar.DAY_OF_MONTH);
		updateDisplay();
		Cursor cursor=data.getMoreInfo(inid1);
		while(cursor.moveToNext())
		{      
			wadd=cursor.getString(2);
			orgcw=cursor.getString(3);
			orgco=cursor.getString(4);
			orgpw=cursor.getString(5);
			orgpo=cursor.getString(6);
			compadd=cursor.getString(8);
			nicname=cursor.getString(9);
			padd=cursor.getString(10);
			paw=cursor.getString(11);
			pao=cursor.getString(12);
			bday=cursor.getString(13);
			ani=cursor.getString(14);
			wadds.setText(wadd);
			epah.setText(orgcw);
			eorgwc.setText(orgco);
			eorgwp.setText(orgpw);
			comadd.setText(compadd);
			nn.setText(nicname);
			eorgop.setText(padd);
			epaw.setText(paw);
			epao.setText(pao);
			bir.setText(bday);
			aniv.setText(ani);
		}
		cursor.close();
		
		
		
		
	}
	  public void onCreateContextMenu(ContextMenu menu, View v,
	            ContextMenuInfo menuInfo) {
	super.onCreateContextMenu(menu, v, menuInfo);
	menu.setHeaderTitle("Choose Image");
	MenuInflater inflater = getMenuInflater();
	inflater.inflate(R.menu.context_menu, menu);
	}
	     public boolean onContextItemSelected(MenuItem item) {
	            switch (item.getItemId()) {

	            case R.id.cmbrowse:call1();
	    		break;
	    		/*case R.id.cmsnap:
	    				
	    			

	    		break;*/
	            default:
	                return super.onContextItemSelected(item);
	               
	            }
	            return true;
	        }

	    


	
		
	/*Type :Function
	name:call1
	return type:void
	date:29-6-11
	purpose:To get image from sdcard*/
	public void call1()
	{
		Intent intent = new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);
		startActivityForResult(Intent.createChooser(intent,
				"Select Picture"), SELECT_PICTURE);


	}
	/*Type :Function
	name:onAcitvityResult
	return type:void
	date:29-6-11
	purpose:To set the updated image by retrieving the path of selected image*/
	//UPDATED
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == Activity.RESULT_OK) {
			if (requestCode == SELECT_PICTURE) {
				Uri selectedImageUri = data.getData();

				//OI FILE Manager
				filemanagerstring = selectedImageUri.getPath();

				//MEDIA GALLERY
				selectedImagePath = getPath(selectedImageUri);
				img2.setImageURI(selectedImageUri);


				//img.setImageURI(selectedImageUri);

				imagePath.getBytes();

				imagePath=(imagePath.toString());
				System.out.println("MY PATH: "+imagePath);
				Bitmap bm = BitmapFactory.decodeFile(imagePath);

			}

		}

	}
	/*Type :Function
	name:getPath
	return type:void
	date:29-6-11
	purpose:To get the image path (from selected image)*/

	//UPDATED!
	public String getPath(Uri uri) {
		String[] projection = { MediaColumns.DATA };
		Cursor cursor = managedQuery(uri, projection, null, null, null);
		column_index = cursor
		.getColumnIndexOrThrow(MediaColumns.DATA);
		cursor.moveToFirst();
		imagePath = cursor.getString(column_index);
		return cursor.getString(column_index);
		
	}
	/*Type :Function
	name:updateDisplay
	return type:void
	date:29-6-11
	purpose:To set updated date in text view*/
	private void updateDisplay() 
	{
		// updates the date in the TextView
        if(bir.hasFocus())
        {
		bir.setText(
				new StringBuilder()
				// Month is 0 based so add 1
				.append(mMonth + 1).append("-")
				.append(mDay).append("-")
				.append(mYear).append(" "));
        }else
        {
		aniv.setText(
				new StringBuilder()
				// Month is 0 based so add 1
				.append(mMonth + 1).append("-")
				.append(mDay).append("-")
				.append(mYear).append(" "));
        }


	}
	

	// the callback received when the user "sets" the date in the dialog
	private DatePickerDialog.OnDateSetListener mDateSetListener =
		new DatePickerDialog.OnDateSetListener() {

		public void onDateSet(DatePicker view, int year, 
				int monthOfYear, int dayOfMonth) {
			mYear = year;
			mMonth = monthOfYear;
			mDay = dayOfMonth;
			updateDisplay();
		}

	};
	
	protected Dialog onCreateDialog(int id)
	{
		switch(id)
		{
		case DATE_DIALOG_ID:
		 	return new DatePickerDialog(this,
					mDateSetListener, 
					mYear, mMonth, mDay);

		}
		return null;
		
		
	}

	/*Type :Function
	name:addnewSpinnerItem
	return type:void
	date:29-6-11
	purpose:To add Items to spinner dynamically*/

	private void addNewSpinnerItem3() {
		mscust=ecust.getText().toString();
		CharSequence textHolder = "" +mscust; 
		adapter3.insert(textHolder, 0);
		smob.setAdapter(adapter3);
		}
	/*Type :Function
	name:addnewSpinnerItem
	return type:void
	date:29-6-11
	purpose:To add Items to spinner dynamically*/
	private void addNewSpinnerItem() {
		mscust=ecust.getText().toString();
		CharSequence textHolder = "" +mscust; 
		adapter.insert(textHolder, 0);
		shome.setAdapter(adapter);
		}
	/*Type :Function
	name:addnewSpinnerItem
	return type:void
	date:29-6-11
	purpose:To add Items to spinner dynamically*/
	private void addNewSpinnerItem1() {
		mscust=ecust.getText().toString();
		CharSequence textHolder = "" +mscust; 
		adapter1.insert(textHolder, 0);
		swork.setAdapter(adapter1);
		
	}
	/*Type :Function
	name:addnewSpinnerItem
	return type:void
	date:29-6-11
	purpose:To add Items to spinner dynamically*/
	private void addNewSpinnerItem2() {
		mscust=ecust.getText().toString();
		CharSequence textHolder = "" +mscust; 
		adapter2.insert(textHolder, 0);
		soth.setAdapter(adapter2);
		}
	/*Type :Function
	name:addnewSpinnerItem
	return type:void
	date:29-6-11
	purpose:To add Items to spinner dynamically*/
	private void addNewSpinnerItem4() 
	{
		mscust=ecust.getText().toString();
		CharSequence textHolder = "" +mscust; 
		adapterpah.insert(textHolder, 0);
		spah.setAdapter(adapterpah);
	}
	/*Type :Function
	name:addnewSpinnerItem
	return type:void
	date:29-6-11
	purpose:To add Items to spinner dynamically*/
	private void addNewSpinnerItem5() 
	{
		mscust=ecust.getText().toString();
		CharSequence textHolder = "" +mscust; 
		adapterorgw.insert(textHolder, 0);
		sorgw.setAdapter(adapterorgw);
	}
	/*Type :Function
	name:addnewSpinnerItem
	return type:void
	date:29-6-11
	purpose:To add Items to spinner dynamically*/
	private void addNewSpinnerItem6() 
	{
		mscust=ecust.getText().toString();
		CharSequence textHolder = "" +mscust; 
		adapterorgo.insert(textHolder, 0);
		sorgo.setAdapter(adapterorgo);
	}
	/*Type :Function
	name:addnewSpinnerItem
	return type:void
	date:29-6-11
	purpose:To add Items to spinner dynamically*/
	private void addNewSpinnerItem7() 
	{
		mscust=ecust.getText().toString();
		CharSequence textHolder = "" +mscust; 
		adapterpaw.insert(textHolder, 0);
		spaw.setAdapter(adapterpaw);
	}
	private void addNewSpinnerItem9() 
	{
		mscust=ecust.getText().toString();
		CharSequence textHolder = "" +mscust; 
		ehadapter.insert(textHolder, 0);
		sehome.setAdapter(ehadapter);
		
	}
	private void addNewSpinnerItem10() 
	{
		mscust=ecust.getText().toString();
		CharSequence textHolder = "" +mscust; 
		ewadapter.insert(textHolder, 0);
		sework.setAdapter(ewadapter);
		
	}
	private void addNewSpinnerItem11() 
	{
		mscust=ecust.getText().toString();
		CharSequence textHolder = "" +mscust; 
		eoadapter.insert(textHolder, 0);
		seoth.setAdapter(eoadapter);
		
	}
	/*Type :Function
	name:addnewSpinnerItem
	return type:void
	date:29-6-11
	purpose:To add Items to spinner dynamically*/
	private void addNewSpinnerItem8() 
	{
		mscust=ecust.getText().toString();
		CharSequence textHolder = "" +mscust; 
		adapterpao.insert(textHolder, 0);
		spao.setAdapter(adapterpao);
	}
	
	public void progressbar()
	{
		 viewLocation = new Runnable()
		   {
	            public void run() 
	            {
	                getLocation();
	            }
	        };
	        Thread thread =  new Thread(null, viewLocation, "MagentoBackground");
	        thread.start();
	        m_ProgressDialog = ProgressDialog.show(Editcontacts.this,    
	              "Smart Address Book", "Updating Contact........", true);
	
	}
	 // This function is used to provide delay... and nothing else...
    void getLocation()
    {
    	try
    	{
    		Thread.sleep(8000);
    	}
        catch (Exception e) 
        {
        	Log.e("BACKGROUND_PROC", e.getMessage());
        }
    	runOnUiThread(returnRes);
    	
    }

    
    private Runnable returnRes = new Runnable() {

        public void run() {
            m_ProgressDialog.dismiss();
            
            
            
        }
    };
	@Override
	public void onBackPressed()
	{
		String sid=""+inid;
		System.out.println(" " +sid);
		Intent i=new Intent(Editcontacts.this,ContactDetails.class);
		Bundle bun=new Bundle();
		bun.putString("name2",sid);
		i.putExtras(bun);
		startActivity(i);
	
		return;
	}
}

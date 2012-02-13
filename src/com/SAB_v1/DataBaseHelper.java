package com.SAB_v1;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class DataBaseHelper extends SQLiteOpenHelper
{
	private static final String TAG = "DataBaseHelper";
    
	//Database Name
	static final String dbName="sab_1.14";   

	//Database version   
	private static final int DATABASE_VERSION = 47;
	
	static final String tempeTable="tempcontact";
	static final String tempmoreInfoTable="tempcontactinfo";
	static final String tempuserTable="tempuserTable";
	static final String  tempusermoreInfoTable="tempuserinfo";
	static final String  templistTable="tempcallist";
	static final String  tempcallListDetailsTable="tempcallListDetails";
	static final String  tempfavoritesTable="tempfavoritesTable";
	static final String  tempGroupTable="tempgrouptable";
	static final String  tempGroupTableDetails="tempgrouptabledetails";
	static final String  tempfeedbackTable="tempfeedback";
	static final String  temptaskTable="tempTask";
	static final String  tempnotesTable="tempnotes";
	static final String tempEmailLogTable="tempemaillog";
	static final String  tempSMSLogTable="tempsmslog";
	static final String  tempcalllogTable="tempcalllog";
	static final String  tempaccount="tempaccount";
	static final String  tempaccount_flag="tempaccount_flag";
	static final String  tempsync="tempsync";
	static final String  temptask_settings="temptasksettings";
	static final String  tempemail_settings="tempemailsettings";
	static final String tempcallsTable="tempcalltable";
	static final String  tempnotifiation_settings="tempnotisettings";
	static final String tempwindow_settings="tempwindowsettings";
	static final String tempr_sms="temprsms";
	static final String temps_sms="tempssms";
	static final String temp_sync_set="tempsyncset";
	static final String temprflag_sms="temprflagsms";
	static final String tempsflag_sms="tempsflagsms";
	static final String temp_sync_login_Table="temp_sync_login_Table";
	static final String temp_sync_login_Settings="temp_sync_login_Settings";
	static final String temp_auto_sync_details="temp_auto_sync_details";
	static final String temp_comm_Table="temp_comm_Table";
	static final String temp_in_table="temp_in_Table";
	
	//Add Contact Table
	static final String eTable="newcontact";
	static final String cid1="conid";
	static final String fname="firstname";
	static final String mname="middlename";
	static final String lname="lastname";
	static final String mobno="mobileno";
	static final String mobnoh="mobilenohome";
	static final String mobnow="mobilenowork";
	static final String mobnoo="mobilenooth";
	static final String mobnocust="mobilenocust";
	static final String email="emailid";
	static final String emailw="emailidwork";
	static final String emailo="emailidoth";
	static final String emailcust="emailidcust";
	static final String image="image";
	static final String tag_type="tag_type";
	static final String tags="tag";
	static final String im_id="import";
	static final String date="date";
	static final String time="time";
	static final String newconnotes="notes";
	static final String colflag="flag";

	//Add More Info 
	static final String moreInfoTable="MoreInfo";
	static final String mcid1="conid";
	static final String mfname="mfirstname";
	static final String mlname="mlastname";
	static final String wadd="webadd";
	static final String orgcw="cmpwork";
	static final String orgco="cmpother";
	static final String orgpw="cmpposwork";
	static final String orgpo="cmpposoth";
	static final String orgcust="cmpcust";
	static final String compadd="companyadd";
	static final String mnname="nickname";
	static final String padd="postaladd";
	static final String paw="postaladdw";
	static final String pao="postaladdo";
	static final String pacust="postaladdcust";
	static final String birth="birthdate";
	static final String ani="aniversary";
	static final String viewEmps="ViewEmps";
	static final String colmflag="flag";
	
      //Add User Table
	static final String userTable="user";
	static final String userid="userid";
	static final String ufname="username";
	static final String umname="usermiddlename";
	static final String ulname="userlastname";
	static final String umobno="usermobileno";
	static final String umobnoh="usermobilenohome";
	static final String umobnow="usermobilenowork";
	static final String umobnoo="usermobilenooth";
	static final String umobnocust="usermobilenocust";
	static final String uemail="useremailid";
	static final String uemailw="useremailidwork";
	static final String uemailo="useremailidoth";
	static final String uemailcust="useremailidcust";
	static final String uimage="userimage";
	static final String utag_type="usertag_type";
	static final String utags="usertag";
	static final String uim_id="userimport";
	static final String ucolflag="userflag";

	//Add User More Info 
	static final String usermoreInfoTable="UserMoreInfo";
	static final String umcid1="userconid";
	static final String umfname="usermfirstname";
	static final String umlname="usermlastname";
	static final String uwadd="userwebadd";
	static final String uorgcw="usercmpwork";
	static final String uorgco="usercmpother";
	static final String uorgpw="usercmpposwork";
	static final String uorgpo="usercmpposoth";
	static final String uorgcust="usercmpcust";
	static final String ucompadd="usercompanyadd";
	static final String umnname="usernickname";
	static final String upadd="userpostaladd";
	static final String upaw="userpostaladdw";
	static final String upao="userpostaladdo";
	static final String upacust="userpostaladdcust";
	static final String ubirth="userbirthdate";
	static final String uani="useraniversary";
	static final String uviewEmps="userViewEmps";
	static final String ucolmflag="userflag";
	
	//Favorites
	static final String favoritesTable="favorites";
	static final String fav_id="favorites_id";
	static final String fav_cid="favorites_cid";
	static final String fav_flag="favorites_flag";
	
	//Call List Table 
	static final String listTable="calllist1";
	static final String callid1="id";
	static final String callname="listname";
	static final String calldate="date";
	static final String calltime="time";
	static final String calllistflag="flag";	
	
	//Call List Table Details
	static final String callListDetailsTable="callListDetails";
	static final String listid="id";
	static final String con_id="contact_id";
	static final String flag="flag";	
	static final String callflag="cflag";
	
	//Group Table 
	static final String GroupTable="grouptable";
	static final String group_id="gid";
	static final String group_name="gname";
	
	
	//Group Table Details
	static final String GroupTableDetails="grouptabledetails";
	static final String group_id1="gid1";
	static final String caller_id="cid";
	
	static final String feedbackTable="feedback";
	static final String fid="id";
	static final String emailid=email;
	static final String mobnum="mobno";
	static final String ratetags="tagsrating";
	static final String ratecalllist="calllistrating";
	static final String ratetasks="tasksrating";
	static final String ratesync="syncrating";

	static final String taskTable="Task";
	static final String locationTable="Location";
	static final String contactTable="Contacts";
	static final String calllistTable="Calllist";
	static final String callsTable="Callls";
	static final String colid="TaskID";
	static final String coltasko="TaskOwner";
	static final String colname="TaskName";
	static final String coldesp="TaskDesp";
	static final String coltype="TaskType";
	static final String coltpriority="TaskPriority";
	static final String coltflag="flag";
	static final String colcat="TaskCat";
	static final String colsdate="Tasksdata";
	static final String colddate="Taskddate";
	static final String coltime="Tasktime";
	static final String colloc="loc";
	static final String colcon="con";
	static final String collocid="loc_id";
	static final String colconid="c_id";
	static final String collocname="loc_name";
	static final String collocflag="loc_flag";
	static final String collat="loc_lat";
	static final String collong="loc_long";
	static final String collocaddr="loc_addr";
	static final String colconname="con_name";
	static final String colconnum="con_num";
	static final String callid="CallistID";
	static final String colclname="clname";
	static final String colcname="cname";
	static final String colcall="call";
	int cid;

	static final String notesTable="notes";
	static final String colnid="notesid";
	static final String cont_id="contid";
	static final String colnname="notesname";
	static final String colnmob="nmobno";
	static final String colnotes="notes";
	static final String coldate="notesdate";
	static final String coltimes="notestime";
	static final String nflag="flag";
	
	//email log
	static final String EmailLogTable="emaillog";
	static final String eid="conid";
	static final String elogid="logid";
	static final String ecount="count";
	static final String enumber="enumber";
	static final String colelogf="flag";

	//SMS Log
	static final String SMSLogTable="smslog";
	static final String sid="conid";
	static final String slogid="smslogid";
	static final String scount="smscount";
	static final String colslogf="flag";
	
	static final String calllogTable="calllog";
	static final String colclgid="clogid";
	static final String colcid="conid";
	static final String cldate="date";
	static final String cltime="time";
	static final String cldur="duration";
	static final String colnum="number";
	static final String logtype="logtype";
	

	//account
	static final String account="account";
	static final String acc_id="aid";
	static final String ac_email="email";
	static final String ac_pwd="pwd";
	
	// account flag
	static final String account_flag="account_flag";
	static final String acc_id_flag="aidflag";
	static final String acc_flag="flag";
	
	//sync
	static final String sync="sync";
	static final String s_id="id";
	static final String type="type";
	static final String sync_acc_id="sync_acc_id";
	
	//task settings
	static final String task_settings="tasksettings";
	static final String set_tid="settid";
	static String set_taskflag="settaskflag";
	
	//email settings
	static final String email_settings="emailsettings";
	static final String set_eid="seteid";
	static final String set_emailflag="setemailflag";
	
	//noti settings
	static final String notification_settings="tasknotisettings";
	static final String set_noid="setnoid";
	static String set_notiflag="setnotiflag";
	
	//email settings
	static final String window_settings="windowpopupsettings";
	static final String set_winid="setwinid";
	static final String set_winflag="setwinflag";
	
	//sms Received
	static final String smsRTable="smsR";
	static final String smsr_id="smsr_id";
	static final String sender="sms_sender";
	static final String messager="msg";
	static final String smsr_Date="smsr_date";
	static final String smsr_Time="smsr_time";
	static final String smsr_sid="smsr_sid";
	static final String smsr_thread_id="smsr_thread_id";
	
	//sms send
	static final String smsSTable="smsS";
	static final String smss_id="sms_id";
	static final String receiver="sms_s";
	static final String messages="msgs";
	static final String smss_Date="smss_date";
	static final String smss_Time="smss_time";
	static final String smss_sid="smss_sid";
	static final String smss_thread_id="smss_thread_id";

	//SMS received flag table
	//sms Received
	static final String smsRFlagTable="smsRflag";
	static final String smsrflag_id="smsrflag_id";
	static final String smsr_flag="smsr_flag";
	
	//Sms sender flag table
	static final String smsSFlagTable="smsSflag";
	static final String smssflag_id="smssflag_id";
	static final String smss_flag="smss_flag";

	//sync account table
	static final String sync_login_Table="synclogin";
	static final String sync_login_id="sync_login_id";
	static final String sync_login_email_id="sync_login_email_id";
	static final String sync_login_password="sync_login_password";
	static final String sync_login_flag="sync_login_flag";
	
	
	//sync account sttings table
	static final String sync_login_settings="syncloginsettings";
	static final String sync_login_settings_id="sync_login_settings_id";
	static final String sync_login_settings_email_id="sync_login_settings_email_id";
	static final String sync_login_settings_password="sync_login_settings_password";
	static final String sync_login_settings_u_id="sync_login_settings_u_id";
	
	
	
	//sync settings
	static final String sync_settings="sync_settings";
	static final String sync_id="sync_id";
	static final String con_sync="con_sync";
	static final String call_sync="call_sync";
	static final String task_sync="task_sync";
	static final String log_sync="log_sync";
	static final String sms_sync="sms_sync";
	
	//sms Received
	static final String auto_sync_details="auto_sync_details";
	static final String auto_sync_id="auto_sync_id";
	static final String u_id="u_id";
	static final String sync_type="sync_type";
	static final String auto_sync_flag="auto_sync_flag";
	
	//count comm scores
	static final String comm_Table="comm_Table";
	static final String com_id="com_id";
	static final String com_num="com_num";
	static final String com_con_id="com_con_id";
	static final String com_call_count="com_call_count";
	static final String com_sms_count="com_sms_count";
	static final String com_email_count="com_email_count";
	static final String com_total_count="com_total_count";

	//incomming call 
	static final String in_table="In_Table";
	static final String in_id="in_id";
	static final String in_phn="in_phn";

	
	public DataBaseHelper(Context context) 
	{
		super(context, dbName, null,DATABASE_VERSION);
        
		// TODO Auto-generated constructor stub 
	}

	@Override
	public void onCreate(SQLiteDatabase db) 
	{
		// TODO Auto-generated method stub
		 
		db.execSQL("CREATE TABLE "+eTable+" ("+cid1+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+fname+ " TEXT,"+mname+ " TEXT, "+lname+ " TEXT,"+mobno+ " TEXT,"+mobnoh+ " TEXT,"+mobnow+ " TEXT,"+mobnoo+ " TEXT,"+mobnocust+ " TEXT,"+email+ " TEXT,"+emailw+ " TEXT,"+emailo+ " TEXT,"+emailcust+" TEXT,"+image+ " TEXT,"+tag_type+" TEXT,"+tags+ " TEXT,"+colflag+ " INTEGER,"+im_id+ " INTEGER,"+date+" TEXT,"+time+" TEXT,"+newconnotes+" TEXT)");
		db.execSQL("CREATE TABLE "+moreInfoTable+" ("+mcid1+ " INTEGER,"+wadd+ " TEXT,"+orgcw+ " TEXT,"+orgco+ " TEXT,"+orgpw+ " TEXT,"+orgpo+ " TEXT,"+orgcust+ " TEXT,"+compadd+ " TEXT,"+mnname+ " TEXT,"+padd+ " TEXT,"+paw+ " TEXT,"+pao+ " TEXT,"+pacust+ " TEXT,"+birth+ " TEXT,"+ani+ " TEXT,"+colmflag+ " INTEGER,FOREIGN KEY ("+mcid1+") REFERENCES "+eTable+" ("+cid1+"))");
		db.execSQL("CREATE TABLE "+userTable+" ("+userid+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+ufname+ " TEXT,"+umname+ " TEXT, "+ulname+ " TEXT,"+umobno+ " TEXT,"+umobnoh+ " TEXT,"+umobnow+ " TEXT,"+umobnoo+ " TEXT,"+umobnocust+ " TEXT,"+uemail+ " TEXT,"+uemailw+ " TEXT,"+uemailo+ " TEXT,"+uemailcust+" TEXT,"+uimage+ " TEXT,"+utag_type+" TEXT,"+utags+ " TEXT,"+ucolflag+ " INTEGER,"+uim_id+ " INTEGER)");
		db.execSQL("CREATE TABLE "+usermoreInfoTable+" ("+umcid1+ " INTEGER,"+uwadd+ " TEXT,"+uorgcw+ " TEXT,"+uorgco+ " TEXT,"+uorgpw+ " TEXT,"+uorgpo+ " TEXT,"+uorgcust+ " TEXT,"+ucompadd+ " TEXT,"+umnname+ " TEXT,"+upadd+ " TEXT,"+upaw+ " TEXT,"+upao+ " TEXT,"+upacust+ " TEXT,"+ubirth+ " TEXT,"+uani+ " TEXT,"+ucolmflag+ " INTEGER,FOREIGN KEY ("+umcid1+") REFERENCES "+userTable+" ("+userid+"))");
		db.execSQL("CREATE TABLE "+favoritesTable+" ("+fav_id+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+fav_cid+" INTEGER,"+fav_flag+" INTEGER,FOREIGN KEY ("+fav_cid+") REFERENCES "+eTable+" ("+cid1+"))");
		db.execSQL("CREATE TABLE "+listTable+" ("+callid1+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+callname+ " TEXT, "+calldate+ " TEXT,"+calltime+ " TEXT,"+calllistflag+" INTEGER)");
		db.execSQL("CREATE TABLE "+GroupTable+" ("+group_id+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+group_name+" TEXT)");
		db.execSQL("CREATE TABLE "+GroupTableDetails+" ("+group_id1+ " INTEGER,"+caller_id+" INTEGER,FOREIGN KEY("+group_id1+") REFERENCES "+GroupTable+" ("+group_id+"),FOREIGN KEY("+caller_id+") REFERENCES "+eTable+" ("+cid1+"))");
		db.execSQL("CREATE TABLE "+callListDetailsTable+" ("+listid+ " INTEGER ,"+con_id+ " INTEGER,"+flag+" INTEGER,"+callflag+" INTEGER,FOREIGN KEY ("+listid+") REFERENCES "+listTable+" ("+callid1+"),FOREIGN KEY ("+con_id+") REFERENCES "+eTable+" ("+cid1+"))");
		db.execSQL("CREATE TABLE "+callsTable+" ("+ colcall+ " INTEGER,"+colcname+ " TEXT,FOREIGN KEY ("+colcall+") REFERENCES "+callListDetailsTable+" ("+listid+"))");
		db.execSQL("CREATE TABLE "+notesTable+" ("+colnid+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+cont_id+ " INTEGER,"+colnname+ " TEXT,"+colnotes+ " TEXT,"+coldate+ " TEXT,"+coltimes+ " TEXT,"+nflag+","+colnmob+" Integer,FOREIGN KEY ("+cont_id+") REFERENCES "+eTable+" ("+cid1+"))");
		db.execSQL("CREATE TABLE "+EmailLogTable+" ("+elogid+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+eid+" INTEGER,"+ecount+" INTEGER,"+colelogf+" INTEGER,"+enumber+" TEXT,FOREIGN KEY ("+eid+") REFERENCES "+eTable+" ("+cid1+"))");
		db.execSQL("CREATE TABLE "+SMSLogTable+" ("+slogid+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+sid+" INTEGER,"+scount+" INTEGER,"+colslogf+" INTEGER,FOREIGN KEY ("+sid+") REFERENCES "+eTable+" ("+cid1+"))");
		db.execSQL("CREATE TABLE "+calllogTable+" ("+colclgid+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+colcid+" INTEGER,"+cldate+" Text,"+cltime+" Text,"+colnum+" TEXT,"+logtype+" INTEGER,"+cldur+" TEXT,FOREIGN KEY ("+colcid+") REFERENCES "+eTable+" ("+cid1+"))");
		db.execSQL("CREATE TABLE "+taskTable+" ("+colid +" INTEGER PRIMARY KEY AUTOINCREMENT,"+coltasko+" TEXT,"+coltflag+" INTEGER, "+colname+" TEXT, "
				+coldesp+" Text,"+colsdate+" DATE,"+colddate+" DATE,"+coltpriority+","+collocname+" Text,"+coltime+" Text,"+colcon+" Integer,FOREIGN KEY ("+colcon+") REFERENCES "+eTable+" ("+cid1+"))");		
		db.execSQL("CREATE TABLE "+account+" ("+acc_id+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+ac_email+" TEXT,"+ac_pwd+" TEXT )");
		db.execSQL("CREATE TABLE "+account_flag+" ("+acc_id_flag+ " INTEGER,"+acc_flag+" INTEGER,FOREIGN KEY ("+acc_id_flag+") REFERENCES "+account+" ("+acc_id+"))");
		db.execSQL("CREATE TABLE "+sync+" ("+s_id+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+type+" INTEGER,"+sync_acc_id+" TEXT,FOREIGN KEY ("+sync_acc_id+") REFERENCES "+auto_sync_details+" ("+u_id+"))");
		db.execSQL("CREATE TABLE "+task_settings+" ("+set_tid+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+set_taskflag+" INTEGER)");
		db.execSQL("CREATE TABLE "+email_settings+" ("+set_eid+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+set_emailflag+" INTEGER)");
		db.execSQL("CREATE TABLE "+feedbackTable+" ("+fid+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+emailid+" TEXT,"+mobnum+" TEXT,"+ratetags+" TEXT,"+ratecalllist+" TEXT,"+ratetasks+" TEXT,"+ratesync+" TEXT)");
		db.execSQL("CREATE TABLE "+notification_settings+" ("+set_noid+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+set_notiflag+" INTEGER)");
		db.execSQL("CREATE TABLE "+window_settings+" ("+set_winid+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+set_winflag+" INTEGER)");
		db.execSQL("CREATE TABLE "+smsRTable+" ("+smsr_id+" Integer Primary Key Autoincrement,"+sender+" Text,"+messager+" TEXT,"+smsr_Date+" TEXT,"+smsr_Time+" TEXT,"+smsr_sid+" INTEGER,"+smsr_thread_id+" INTEGER)");
		db.execSQL("CREATE TABLE "+smsSTable+" ("+smss_id+" Integer Primary Key Autoincrement,"+receiver+" Text,"+messages+" TEXT,"+smss_Date+" TEXT,"+smss_Time+" TEXT,"+smss_sid+" INTEGER,"+smss_thread_id+" INTEGER )");
		db.execSQL("CREATE TABLE "+sync_settings+" ("+sync_id+" Integer Primary Key Autoincrement,"+con_sync+" INTEGER,"+call_sync+" INTEGER,"+task_sync+" INTEGER,"+log_sync+" INTEGER,"+sms_sync+" INTEGER )");
		db.execSQL("CREATE TABLE "+smsRFlagTable+" ("+smsrflag_id+" Integer,"+smsr_flag+" INTEGER, FOREIGN KEY ("+smsrflag_id+") REFERENCES "+smsRTable+" ("+smsr_id+") )");
		db.execSQL("CREATE TABLE "+smsSFlagTable+" ("+smssflag_id+" Integer,"+smss_flag+" INTEGER, FOREIGN KEY ("+smssflag_id+") REFERENCES "+smsSTable+" ("+smss_id+") )");
		db.execSQL("CREATE TABLE "+sync_login_Table+" ("+sync_login_id+" INTEGER PRIMARY KEY AUTOINCREMENT,"+sync_login_email_id+" Text,"+sync_login_password+" TEXT,"+sync_login_flag+" INTEGER)");
		db.execSQL("CREATE TABLE "+auto_sync_details+" ("+auto_sync_id+" Integer Primary Key Autoincrement,"+u_id+" Text,"+sync_type+" TEXT, "+auto_sync_flag+" INTEGER)");
		db.execSQL("CREATE TABLE "+sync_login_settings+" ("+sync_login_settings_id+" INTEGER PRIMARY KEY AUTOINCREMENT,"+sync_login_settings_email_id+" Text,"+sync_login_settings_password+" TEXT,"+sync_login_settings_u_id+" INTEGER)");
		db.execSQL("CREATE TABLE "+comm_Table+" ("+com_id+" INTEGER PRIMARY KEY AUTOINCREMENT,"+com_con_id+" INTEGER,"+com_num+" TEXT, "+com_call_count+" INTEGER, "+com_sms_count+" INTEGER, "+com_email_count+" INTEGER,"+com_total_count+" INTEGER,FOREIGN KEY ("+com_con_id+") REFERENCES "+eTable+" ("+cid1+"))");
		db.execSQL("CREATE TABLE "+in_table+" ("+in_id+" INTEGER PRIMARY KEY AUTOINCREMENT,"+in_phn+" String)");

		
		ContentValues cv=new ContentValues();
		cv.put(set_taskflag,1);
		db.insert(task_settings,null,cv);	
		
		ContentValues cv1=new ContentValues();
		cv1.put(set_notiflag,1);
		db.insert(notification_settings,null,cv1);
		
		ContentValues cv2=new ContentValues();
		cv2.put(set_winflag,0);
		db.insert(window_settings,null,cv2);	
		
		ContentValues cv3=new ContentValues();
		cv3.put(con_sync,1);
		 cv3.put(call_sync,0);
		 cv3.put(task_sync,0);
		 cv3.put(log_sync,0);
		 cv3.put(sms_sync,0);
		db.insert(sync_settings,null,cv3);	
		ContentValues cv5=new ContentValues();
		cv5.put(set_emailflag,0);
		db.insert(email_settings,null,cv5);	

		                    
	}
	//	private SQLiteDatabase db;
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
	{
		//new contact
		db.execSQL("CREATE TEMPORARY TABLE "+tempeTable+" ("+cid1+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+fname+ " TEXT,"+mname+ " TEXT, "+lname+ " TEXT,"+mobno+ " TEXT,"+mobnoh+ " TEXT,"+mobnow+ " TEXT,"+mobnoo+ " TEXT,"+mobnocust+ " TEXT,"+email+ " TEXT,"+emailw+ " TEXT,"+emailo+ " TEXT,"+emailcust+" TEXT,"+image+ " TEXT,"+tag_type+" TEXT,"+tags+ " TEXT,"+colflag+ " INTEGER,"+im_id+ " INTEGER)");
		db.execSQL("INSERT INTO "+tempeTable+" SELECT "+cid1+","+fname+","+mname+","+lname+","+mobno+","+mobnoh+","+mobnow+","+mobnoo+","+mobnocust+","+email+","+emailw+","+emailo+","+emailcust+","+image+","+tag_type+","+tags+","+colflag+","+im_id+" from "+eTable);
		db.execSQL("DROP TABLE IF EXISTS "+eTable);
		db.execSQL("CREATE TABLE "+eTable+" ("+cid1+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+fname+ " TEXT,"+mname+ " TEXT, "+lname+ " TEXT,"+mobno+ " TEXT,"+mobnoh+ " TEXT,"+mobnow+ " TEXT,"+mobnoo+ " TEXT,"+mobnocust+ " TEXT,"+email+ " TEXT,"+emailw+ " TEXT,"+emailo+ " TEXT,"+emailcust+" TEXT,"+image+ " TEXT,"+tag_type+" TEXT,"+tags+ " TEXT,"+colflag+ " INTEGER,"+im_id+ " INTEGER,"+date+" TEXT,"+time+" TEXT,"+newconnotes+" TEXT)");
		db.execSQL("INSERT INTO "+eTable+" SELECT "+cid1+","+fname+","+mname+","+lname+","+mobno+","+mobnoh+","+mobnow+","+mobnoo+","+mobnocust+","+email+","+emailw+","+emailo+","+emailcust+","+image+","+tag_type+","+tags+","+colflag+","+im_id+",null,null,null from "+tempeTable);
		db.execSQL("DROP TABLE IF EXISTS "+tempeTable);
		db.execSQL("CREATE TABLE "+sync_login_settings+" ("+sync_login_settings_id+" INTEGER PRIMARY KEY AUTOINCREMENT,"+sync_login_settings_email_id+" Text,"+sync_login_settings_password+" TEXT,"+sync_login_settings_u_id+" INTEGER)");
		db.execSQL("CREATE TABLE "+comm_Table+" ("+com_id+" INTEGER PRIMARY KEY AUTOINCREMENT,"+com_con_id+" INTEGER,"+com_num+" TEXT, "+com_call_count+" INTEGER, "+com_sms_count+" INTEGER,"+com_email_count+" INTEGER,"+com_total_count+" INTEGER,FOREIGN KEY ("+com_con_id+") REFERENCES "+eTable+" ("+cid1+"))");
		db.execSQL("CREATE TABLE "+in_table+" ("+in_id+" INTEGER PRIMARY KEY AUTOINCREMENT,"+in_phn+" String)");

     	System.out.println("On Upgrade.....................");
		//temp tables
		db.execSQL("CREATE TABLE "+tempmoreInfoTable+" ("+mcid1+ " INTEGER,"+wadd+ " TEXT,"+orgcw+ " TEXT,"+orgco+ " TEXT,"+orgpw+ " TEXT,"+orgpo+ " TEXT,"+orgcust+ " TEXT,"+compadd+ " TEXT,"+mnname+ " TEXT,"+padd+ " TEXT,"+paw+ " TEXT,"+pao+ " TEXT,"+pacust+ " TEXT,"+birth+ " TEXT,"+ani+ " TEXT,"+colmflag+ " INTEGER,FOREIGN KEY ("+mcid1+") REFERENCES "+eTable+" ("+cid1+"))");
		db.execSQL("CREATE TEMPORARY TABLE "+tempuserTable+" ("+userid+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+ufname+ " TEXT,"+umname+ " TEXT, "+ulname+ " TEXT,"+umobno+ " TEXT,"+umobnoh+ " TEXT,"+umobnow+ " TEXT,"+umobnoo+ " TEXT,"+umobnocust+ " TEXT,"+uemail+ " TEXT,"+uemailw+ " TEXT,"+uemailo+ " TEXT,"+uemailcust+" TEXT,"+uimage+ " TEXT,"+utag_type+" TEXT,"+utags+ " TEXT,"+ucolflag+ " INTEGER,"+uim_id+ " INTEGER)");
		db.execSQL("CREATE TEMPORARY TABLE "+tempusermoreInfoTable+" ("+umcid1+ " INTEGER,"+uwadd+ " TEXT,"+uorgcw+ " TEXT,"+uorgco+ " TEXT,"+uorgpw+ " TEXT,"+uorgpo+ " TEXT,"+uorgcust+ " TEXT,"+ucompadd+ " TEXT,"+umnname+ " TEXT,"+upadd+ " TEXT,"+upaw+ " TEXT,"+upao+ " TEXT,"+upacust+ " TEXT,"+ubirth+ " TEXT,"+uani+ " TEXT,"+ucolmflag+ " INTEGER,FOREIGN KEY ("+umcid1+") REFERENCES "+userTable+" ("+userid+"))");
		db.execSQL("CREATE TEMPORARY TABLE "+tempfavoritesTable+" ("+fav_id+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+fav_cid+" INTEGER,"+fav_flag+" INTEGER,FOREIGN KEY ("+fav_cid+") REFERENCES "+eTable+" ("+cid1+"))");
		db.execSQL("CREATE TEMPORARY TABLE "+templistTable+" ("+callid1+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+callname+ " TEXT, "+calldate+ " TEXT,"+calltime+ " TEXT,"+calllistflag+" INTEGER)");
		db.execSQL("CREATE TEMPORARY TABLE "+tempGroupTable+" ("+group_id+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+group_name+" TEXT)");
		db.execSQL("CREATE TEMPORARY TABLE "+tempGroupTableDetails+" ("+group_id1+ " INTEGER,"+caller_id+" INTEGER,FOREIGN KEY("+group_id1+") REFERENCES "+GroupTable+" ("+group_id+"),FOREIGN KEY("+caller_id+") REFERENCES "+eTable+" ("+cid1+"))");
	    db.execSQL("CREATE TEMPORARY TABLE "+tempcallListDetailsTable+" ("+listid+ " INTEGER ,"+con_id+ " INTEGER,"+flag+" INTEGER,FOREIGN KEY ("+listid+") REFERENCES "+listTable+" ("+callid1+"),FOREIGN KEY ("+con_id+") REFERENCES "+eTable+" ("+cid1+"))");
		db.execSQL("CREATE TEMPORARY TABLE "+tempcallsTable+" ("+ colcall+ " INTEGER,"+colcname+ " TEXT,FOREIGN KEY ("+colcall+") REFERENCES "+callListDetailsTable+" ("+listid+"))");
		db.execSQL("CREATE TEMPORARY TABLE "+tempnotesTable+" ("+colnid+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+cont_id+ " INTEGER,"+colnname+ " TEXT,"+colnotes+ " TEXT,"+coldate+ " TEXT,"+coltimes+ " TEXT,"+nflag+" Integer,FOREIGN KEY ("+cont_id+") REFERENCES "+eTable+" ("+cid1+"))");
		db.execSQL("CREATE TEMPORARY TABLE "+tempEmailLogTable+" ("+elogid+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+eid+" INTEGER,"+ecount+" INTEGER,"+colelogf+" INTEGER,FOREIGN KEY ("+eid+") REFERENCES "+eTable+" ("+cid1+"))");
		db.execSQL("CREATE TEMPORARY TABLE "+tempSMSLogTable+" ("+slogid+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+sid+" INTEGER,"+scount+" INTEGER,"+colslogf+" INTEGER,FOREIGN KEY ("+sid+") REFERENCES "+eTable+" ("+cid1+"))");
		db.execSQL("CREATE TEMPORARY TABLE "+tempcalllogTable+" ("+colclgid+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+colcid+" INTEGER,"+cldate+" Text,"+cltime+" Text,"+colnum+" TEXT,"+logtype+" INTEGER,FOREIGN KEY ("+colcid+") REFERENCES "+eTable+" ("+cid1+"))");
		db.execSQL("CREATE TEMPORARY TABLE "+temptaskTable+" ("+colid +" INTEGER PRIMARY KEY AUTOINCREMENT,"+coltasko+" TEXT,"+coltflag+" INTEGER, "+colname+" TEXT, "
				+coldesp+" Text,"+colsdate+" DATE,"+colddate+" DATE,"+coltpriority+","+collocname+" Text,"+coltime+" Text,"+colcon+" Integer,FOREIGN KEY ("+colcon+") REFERENCES "+eTable+" ("+cid1+"))");		
		db.execSQL("CREATE TEMPORARY TABLE "+tempaccount+" ("+acc_id+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+ac_email+" TEXT,"+ac_pwd+" TEXT )");
		db.execSQL("CREATE TEMPORARY TABLE "+tempaccount_flag+" ("+acc_id_flag+ " INTEGER,"+acc_flag+" INTEGER,FOREIGN KEY ("+acc_id_flag+") REFERENCES "+account+" ("+acc_id+"))");
		db.execSQL("CREATE TEMPORARY TABLE "+tempsync+" ("+s_id+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+type+" INTEGER)");
		db.execSQL("CREATE TEMPORARY TABLE "+temptask_settings+" ("+set_tid+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+set_taskflag+" INTEGER)");
		db.execSQL("CREATE TEMPORARY TABLE "+tempemail_settings+" ("+set_eid+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+set_emailflag+" INTEGER)");
		db.execSQL("CREATE TEMPORARY TABLE "+tempfeedbackTable+" ("+fid+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+emailid+" TEXT,"+mobnum+" TEXT,"+ratetags+" TEXT,"+ratecalllist+" TEXT,"+ratetasks+" TEXT,"+ratesync+" TEXT)");
		db.execSQL("CREATE TEMPORARY TABLE "+tempnotifiation_settings+" ("+set_noid+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+set_notiflag+" INTEGER)");
		db.execSQL("CREATE TEMPORARY TABLE "+tempwindow_settings+" ("+set_winid+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+set_winflag+" INTEGER)");
		db.execSQL("CREATE TEMPORARY TABLE "+tempr_sms+"("+smsr_id+" Integer Primary Key Autoincrement,"+sender+" Text,"+messager+" TEXT,"+smsr_Date+" TEXT )");
		db.execSQL("CREATE TEMPORARY TABLE "+temps_sms+"("+smss_id+" Integer Primary Key Autoincrement,"+receiver+" Text,"+messages+" TEXT,"+smss_Date+" TEXT )");
		db.execSQL("CREATE TEMPORARY TABLE "+temp_sync_set+"("+sync_id+" Integer Primary Key Autoincrement,"+con_sync+" INTEGER,"+call_sync+" INTEGER,"+task_sync+" INTEGER,"+log_sync+" INTEGER,"+sms_sync+" INTEGER )");
		db.execSQL("CREATE TEMPORARY TABLE "+temprflag_sms+" ("+smsrflag_id+" Integer,"+smsr_flag+" INTEGER, FOREIGN KEY ("+smsrflag_id+") REFERENCES "+smsRTable+" ("+smsr_id+") )");
		db.execSQL("CREATE TEMPORARY TABLE "+tempsflag_sms+" ("+smssflag_id+" Integer,"+smss_flag+" INTEGER, FOREIGN KEY ("+smssflag_id+") REFERENCES "+smsSTable+" ("+smss_id+") )");
		db.execSQL("CREATE TEMPORARY TABLE "+temp_sync_login_Table+" ("+sync_login_id+" INTEGER PRIMARY KEY AUTOINCREMENT,"+sync_login_email_id+" Text,"+sync_login_password+" TEXT,"+sync_login_flag+" INTEGER)");
		db.execSQL("CREATE TEMPORARY TABLE "+temp_auto_sync_details+" ("+auto_sync_id+" Integer Primary Key Autoincrement,"+u_id+" Text,"+sync_type+" TEXT, "+auto_sync_flag+" INTEGER)");
		db.execSQL("CREATE TEMPORARY TABLE "+temp_sync_login_Settings+" ("+sync_login_settings_id+" INTEGER PRIMARY KEY AUTOINCREMENT,"+sync_login_settings_email_id+" Text,"+sync_login_settings_password+" TEXT,"+sync_login_settings_u_id+" INTEGER)");
		db.execSQL("CREATE TEMPORARY TABLE "+temp_comm_Table+" ("+com_id+" INTEGER PRIMARY KEY AUTOINCREMENT,"+com_con_id+" INTEGER,"+com_num+" TEXT, "+com_call_count+" INTEGER, "+com_sms_count+" INTEGER,"+com_email_count+" INTEGER,"+com_total_count+" INTEGER,FOREIGN KEY ("+com_con_id+") REFERENCES "+eTable+" ("+cid1+"))");
		db.execSQL("CREATE TEMPORARY TABLE "+temp_in_table+" ("+in_id+" INTEGER PRIMARY KEY AUTOINCREMENT,"+in_phn+" String)");
		
		//insert into temp tables
		db.execSQL("INSERT INTO "+tempmoreInfoTable+" SELECT "+mcid1+ ","+wadd+ " ,"+orgcw+ " ,"+orgco+ " ,"+orgpw+ ","+orgpo+ ","+orgcust+","+compadd+ ","+mnname+ ","+padd+ ","+paw+ ","+pao+ ","+pacust+ ","+birth+ ","+ani+","+colmflag+" FROM "+moreInfoTable);
		db.execSQL("INSERT INTO "+tempuserTable+" SELECT "+userid+ ","+ufname+ ","+umname+ ", "+ulname+ ","+umobno+ ","+umobnoh+ ","+umobnow+ ","+umobnoo+ ","+umobnocust+ ","+uemail+ ","+uemailw+ ","+uemailo+ ","+uemailcust+","+uimage+ ","+utag_type+","+utags+ ","+ucolflag+ ","+uim_id+ " FROM "+userTable);
		db.execSQL("INSERT INTO "+tempusermoreInfoTable+" SELECT "+umcid1+ ","+uwadd+ ","+uorgcw+ ","+uorgco+ ","+uorgpw+ ","+uorgpo+ ","+uorgcust+ ","+ucompadd+ ","+umnname+ ","+upadd+ ","+upaw+ ","+upao+ ","+upacust+ ","+ubirth+ ","+uani+ ","+ucolmflag+ " FROM "+usermoreInfoTable);
		db.execSQL("INSERT INTO "+tempfavoritesTable+" SELECT "+fav_id+ ","+fav_cid+","+fav_flag+" FROM "+favoritesTable);
		db.execSQL("INSERT INTO "+templistTable+" SELECT "+callid1+ ","+callname+ ","+calldate+ ","+calltime+ ","+calllistflag+" FROM "+listTable);
		db.execSQL("INSERT INTO "+tempcallListDetailsTable+" SELECT "+listid+ ","+con_id+ ","+flag+" FROM "+callListDetailsTable);
		db.execSQL("INSERT INTO "+temptaskTable+" SELECT "+colid +","+coltasko+","+coltflag+","+colname+","+coldesp+","+colsdate+","+colddate+","+coltpriority+","+collocname+","+coltime+","+colcon+" FROM "+taskTable);
		db.execSQL("INSERT INTO "+tempnotesTable+" SELECT "+colnid+ ","+cont_id+ ","+colnname+ ","+colnotes+ ","+coldate+ ","+coltimes+ ","+nflag+" FROM "+notesTable);
		db.execSQL("INSERT INTO "+tempGroupTable+" SELECT "+group_id+ ","+group_name+" FROM "+GroupTable);
		db.execSQL("INSERT INTO "+tempGroupTableDetails+" SELECT "+group_id1+ ","+caller_id+" FROM "+GroupTableDetails);
		db.execSQL("INSERT INTO "+tempfeedbackTable+" SELECT "+fid+ ","+emailid+" ,"+mobnum+" ,"+ratetags+","+ratecalllist+","+ratetasks+","+ratesync+" FROM "+feedbackTable);
		db.execSQL("INSERT INTO "+tempaccount+" SELECT "+acc_id+ ","+ac_email+","+ac_pwd+" FROM "+account);
		db.execSQL("INSERT INTO "+tempaccount_flag+" SELECT "+acc_id_flag+ ","+acc_flag+" FROM "+account_flag);
		db.execSQL("INSERT INTO "+tempcalllogTable+" SELECT "+colclgid+ ","+colcid+","+cldate+","+cltime+","+colnum+","+logtype+" FROM "+calllogTable);
		db.execSQL("INSERT INTO "+tempemail_settings+" SELECT "+set_eid+ ","+set_emailflag+" FROM "+email_settings);
		db.execSQL("INSERT INTO "+temptask_settings+" SELECT "+set_tid+ ","+set_taskflag+" FROM "+task_settings);
		db.execSQL("INSERT INTO "+tempEmailLogTable+" SELECT "+elogid+ ","+eid+","+ecount+","+colelogf+" FROM "+EmailLogTable);
		db.execSQL("INSERT INTO "+tempSMSLogTable+" SELECT "+slogid+ ","+sid+","+scount+","+colslogf+" FROM "+SMSLogTable);
		db.execSQL("INSERT INTO "+tempsync+" SELECT "+s_id+ ","+type+" FROM "+sync);
		db.execSQL("INSERT INTO "+tempcallsTable+" SELECT "+ colcall+ ","+colcname+ " FROM "+callsTable);
		db.execSQL("INSERT INTO "+tempnotifiation_settings+" SELECT "+set_noid+ ","+set_notiflag+" FROM "+notification_settings);
		db.execSQL("INSERT INTO "+tempwindow_settings+" SELECT "+set_winid+ ","+set_winflag+" FROM "+window_settings);
		db.execSQL("INSERT INTO "+tempr_sms+" SELECT "+smsr_id+","+sender+","+messager+","+smsr_Date+" FROM "+smsRTable);
		db.execSQL("INSERT INTO "+temps_sms+" SELECT "+smss_id+","+receiver+","+messages+","+smss_Date+" FROM "+smsSTable);
		db.execSQL("INSERT INTO "+temp_sync_set+" SELECT "+sync_id+","+con_sync+","+call_sync+","+task_sync+","+log_sync+","+sms_sync+" FROM "+sync_settings);
		db.execSQL("INSERT INTO "+temprflag_sms+" SELECT "+smsrflag_id+","+smsr_flag+" FROM "+smsRFlagTable);
		db.execSQL("INSERT INTO "+tempsflag_sms+" SELECT "+smssflag_id+","+smss_flag+" FROM "+smsSFlagTable);
		db.execSQL("INSERT INTO "+temp_sync_login_Table+" SELECT "+sync_login_id+","+sync_login_email_id+","+sync_login_password+","+sync_login_flag+" FROM "+sync_login_Table);
		db.execSQL("INSERT INTO "+temp_auto_sync_details+" SELECT "+auto_sync_id+","+u_id+","+sync_type+","+auto_sync_flag+" FROM "+auto_sync_details);
		db.execSQL("INSERT INTO "+temp_sync_login_Settings+" SELECT "+sync_login_settings_id+","+sync_login_settings_email_id+","+sync_login_settings_password+","+sync_login_settings_u_id+" FROM "+sync_login_settings);
		db.execSQL("INSERT INTO "+temp_comm_Table+" SELECT "+com_id+","+com_con_id+","+com_num+","+com_call_count+", "+com_sms_count+","+com_email_count+","+com_total_count+" FROM "+comm_Table);
		db.execSQL("INSERT INTO "+temp_in_table+" SELECT "+in_id+","+in_phn+" FROM "+in_table);
		
		//Drop tables
		db.execSQL("DROP TABLE IF EXISTS "+moreInfoTable);
		db.execSQL("DROP TABLE IF EXISTS "+userTable);
		db.execSQL("DROP TABLE IF EXISTS "+usermoreInfoTable);
		db.execSQL("DROP TABLE IF EXISTS "+favoritesTable);
		db.execSQL("DROP TABLE IF EXISTS "+listTable);
		db.execSQL("DROP TABLE IF EXISTS "+callListDetailsTable);
		db.execSQL("DROP TABLE IF EXISTS "+taskTable);
		db.execSQL("DROP TABLE IF EXISTS "+notesTable);
		db.execSQL("DROP TABLE IF EXISTS "+GroupTable);
		db.execSQL("DROP TABLE IF EXISTS "+GroupTableDetails);
		db.execSQL("DROP TABLE IF EXISTS "+feedbackTable);
		db.execSQL("DROP TABLE IF EXISTS "+account);
		db.execSQL("DROP TABLE IF EXISTS "+account_flag);
		db.execSQL("DROP TABLE IF EXISTS "+calllogTable);
		db.execSQL("DROP TABLE IF EXISTS "+email_settings);
		db.execSQL("DROP TABLE IF EXISTS "+task_settings);
		db.execSQL("DROP TABLE IF EXISTS "+EmailLogTable);
		db.execSQL("DROP TABLE IF EXISTS "+SMSLogTable);
		db.execSQL("DROP TABLE IF EXISTS "+sync);
		db.execSQL("DROP TABLE IF EXISTS "+callsTable);
		db.execSQL("DROP TABLE IF EXISTS "+notification_settings);
		db.execSQL("DROP TABLE IF EXISTS "+window_settings);
		db.execSQL("DROP TABLE IF EXISTS "+smsRTable);
		db.execSQL("DROP TABLE IF EXISTS "+smsSTable);
		db.execSQL("DROP TABLE IF EXISTS "+sync_settings);
		db.execSQL("DROP TABLE IF EXISTS "+smsRFlagTable);
		db.execSQL("DROP TABLE IF EXISTS "+smsSFlagTable);
		db.execSQL("DROP TABLE IF EXISTS "+sync_login_Table);
		db.execSQL("DROP TABLE IF EXISTS "+auto_sync_details);
		db.execSQL("DROP TABLE IF EXISTS "+sync_login_settings);
	    db.execSQL("DROP TABLE IF EXISTS "+comm_Table);
	    db.execSQL("DROP TABLE IF EXISTS "+in_table);
	    
		//recreate tables
		db.execSQL("CREATE TABLE "+moreInfoTable+" ("+mcid1+ " INTEGER,"+wadd+ " TEXT,"+orgcw+ " TEXT,"+orgco+ " TEXT,"+orgpw+ " TEXT,"+orgpo+ " TEXT,"+orgcust+ " TEXT,"+compadd+ " TEXT,"+mnname+ " TEXT,"+padd+ " TEXT,"+paw+ " TEXT,"+pao+ " TEXT,"+pacust+ " TEXT,"+birth+ " TEXT,"+ani+ " TEXT,"+colmflag+ " INTEGER,FOREIGN KEY ("+mcid1+") REFERENCES "+eTable+" ("+cid1+"))");
		db.execSQL("CREATE TABLE "+userTable+" ("+userid+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+ufname+ " TEXT,"+umname+ " TEXT, "+ulname+ " TEXT,"+umobno+ " TEXT,"+umobnoh+ " TEXT,"+umobnow+ " TEXT,"+umobnoo+ " TEXT,"+umobnocust+ " TEXT,"+uemail+ " TEXT,"+uemailw+ " TEXT,"+uemailo+ " TEXT,"+uemailcust+" TEXT,"+uimage+ " TEXT,"+utag_type+" TEXT,"+utags+ " TEXT,"+ucolflag+ " INTEGER,"+uim_id+ " INTEGER)");
		db.execSQL("CREATE TABLE "+usermoreInfoTable+" ("+umcid1+ " INTEGER,"+uwadd+ " TEXT,"+uorgcw+ " TEXT,"+uorgco+ " TEXT,"+uorgpw+ " TEXT,"+uorgpo+ " TEXT,"+uorgcust+ " TEXT,"+ucompadd+ " TEXT,"+umnname+ " TEXT,"+upadd+ " TEXT,"+upaw+ " TEXT,"+upao+ " TEXT,"+upacust+ " TEXT,"+ubirth+ " TEXT,"+uani+ " TEXT,"+ucolmflag+ " INTEGER,FOREIGN KEY ("+umcid1+") REFERENCES "+userTable+" ("+userid+"))");
		db.execSQL("CREATE TABLE "+favoritesTable+" ("+fav_id+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+fav_cid+" INTEGER,"+fav_flag+" INTEGER,FOREIGN KEY ("+fav_cid+") REFERENCES "+eTable+" ("+cid1+"))");
		db.execSQL("CREATE TABLE "+listTable+" ("+callid1+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+callname+ " TEXT, "+calldate+ " TEXT,"+calltime+ " TEXT,"+calllistflag+" INTEGER)");
		db.execSQL("CREATE TABLE "+GroupTable+" ("+group_id+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+group_name+" TEXT)");
		db.execSQL("CREATE TABLE "+GroupTableDetails+" ("+group_id1+ " INTEGER,"+caller_id+" INTEGER,FOREIGN KEY("+group_id1+") REFERENCES "+GroupTable+" ("+group_id+"),FOREIGN KEY("+caller_id+") REFERENCES "+eTable+" ("+cid1+"))");
		db.execSQL("CREATE TABLE "+callListDetailsTable+" ("+listid+ " INTEGER ,"+con_id+ " INTEGER,"+flag+" INTEGER,"+callflag+" INTEGER,FOREIGN KEY ("+listid+") REFERENCES "+listTable+" ("+callid1+"),FOREIGN KEY ("+con_id+") REFERENCES "+eTable+" ("+cid1+"))");
		db.execSQL("CREATE TABLE "+callsTable+" ("+ colcall+ " INTEGER,"+colcname+ " TEXT,FOREIGN KEY ("+colcall+") REFERENCES "+callListDetailsTable+" ("+listid+"))");
		db.execSQL("CREATE TABLE "+notesTable+" ("+colnid+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+cont_id+ " INTEGER,"+colnname+ " TEXT,"+colnotes+ " TEXT,"+coldate+ " TEXT,"+coltimes+ " TEXT,"+nflag+","+colnmob+" Integer,FOREIGN KEY ("+cont_id+") REFERENCES "+eTable+" ("+cid1+"))");
		db.execSQL("CREATE TABLE "+EmailLogTable+" ("+elogid+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+eid+" INTEGER,"+ecount+" INTEGER,"+colelogf+" INTEGER,"+enumber+" TEXT,FOREIGN KEY ("+eid+") REFERENCES "+eTable+" ("+cid1+"))");
		db.execSQL("CREATE TABLE "+SMSLogTable+" ("+slogid+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+sid+" INTEGER,"+scount+" INTEGER,"+colslogf+" INTEGER,FOREIGN KEY ("+sid+") REFERENCES "+eTable+" ("+cid1+"))");
		db.execSQL("CREATE TABLE "+calllogTable+" ("+colclgid+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+colcid+" INTEGER,"+cldate+" Text,"+cltime+" Text,"+colnum+" TEXT,"+logtype+" INTEGER,"+cldur+" TEXT,FOREIGN KEY ("+colcid+") REFERENCES "+eTable+" ("+cid1+"))");
		db.execSQL("CREATE TABLE "+taskTable+" ("+colid +" INTEGER PRIMARY KEY AUTOINCREMENT,"+coltasko+" TEXT,"+coltflag+" INTEGER, "+colname+" TEXT, "
				+coldesp+" Text,"+colsdate+" DATE,"+colddate+" DATE,"+coltpriority+","+collocname+" Text,"+coltime+" Text,"+colcon+" Integer,FOREIGN KEY ("+colcon+") REFERENCES "+eTable+" ("+cid1+"))");		
		db.execSQL("CREATE TABLE "+account+" ("+acc_id+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+ac_email+" TEXT,"+ac_pwd+" TEXT )");
		db.execSQL("CREATE TABLE "+account_flag+" ("+acc_id_flag+ " INTEGER,"+acc_flag+" INTEGER,FOREIGN KEY ("+acc_id_flag+") REFERENCES "+account+" ("+acc_id+"))");
		db.execSQL("CREATE TABLE "+sync+" ("+s_id+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+type+" INTEGER,"+sync_acc_id+" TEXT,FOREIGN KEY ("+sync_acc_id+") REFERENCES "+auto_sync_details+" ("+u_id+"))");
		db.execSQL("CREATE TABLE "+task_settings+" ("+set_tid+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+set_taskflag+" INTEGER)");
		db.execSQL("CREATE TABLE "+email_settings+" ("+set_eid+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+set_emailflag+" INTEGER)");
		db.execSQL("CREATE TABLE "+feedbackTable+" ("+fid+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+emailid+" TEXT,"+mobnum+" TEXT,"+ratetags+" TEXT,"+ratecalllist+" TEXT,"+ratetasks+" TEXT,"+ratesync+" TEXT)");
		db.execSQL("CREATE TABLE "+notification_settings+" ("+set_noid+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+set_notiflag+" INTEGER)");
		db.execSQL("CREATE TABLE "+window_settings+" ("+set_winid+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+set_winflag+" INTEGER)");
		db.execSQL("CREATE TABLE "+smsRTable+"("+smsr_id+" Integer Primary Key Autoincrement,"+sender+" Text,"+messager+" TEXT,"+smsr_Date+" TEXT,"+smsr_Time+" TEXT,"+smsr_sid+" INTEGER,"+smsr_thread_id+" INTEGER )");
		db.execSQL("CREATE TABLE "+smsSTable+"("+smss_id+" Integer Primary Key Autoincrement,"+receiver+" Text,"+messages+" TEXT,"+smss_Date+" TEXT,"+smss_Time+" TEXT,"+smss_sid+" INTEGER,"+smss_thread_id+" INTEGER )");
		db.execSQL("CREATE TABLE "+sync_settings+"("+sync_id+" Integer Primary Key Autoincrement,"+con_sync+" INTEGER,"+call_sync+" INTEGER,"+task_sync+" INTEGER,"+log_sync+" INTEGER,"+sms_sync+" INTEGER )");
		db.execSQL("CREATE TABLE "+smsRFlagTable+" ("+smsrflag_id+" Integer,"+smsr_flag+" INTEGER, FOREIGN KEY ("+smsrflag_id+") REFERENCES "+smsRTable+" ("+smsr_id+") )");
		db.execSQL("CREATE TABLE "+smsSFlagTable+" ("+smssflag_id+" Integer,"+smss_flag+" INTEGER, FOREIGN KEY ("+smssflag_id+") REFERENCES "+smsSTable+" ("+smss_id+") )");
		db.execSQL("CREATE TABLE "+sync_login_Table+" ("+sync_login_id+" INTEGER PRIMARY KEY AUTOINCREMENT,"+sync_login_email_id+" Text,"+sync_login_password+" TEXT,"+sync_login_flag+" INTEGER)");
		db.execSQL("CREATE TABLE "+auto_sync_details+" ("+auto_sync_id+" Integer Primary Key Autoincrement,"+u_id+" Text,"+sync_type+" TEXT, "+auto_sync_flag+" INTEGER)");
		db.execSQL("CREATE TABLE "+sync_login_settings+" ("+sync_login_settings_id+" INTEGER PRIMARY KEY AUTOINCREMENT,"+sync_login_settings_email_id+" Text,"+sync_login_settings_password+" TEXT,"+sync_login_settings_u_id+" INTEGER)");
		db.execSQL("CREATE TABLE "+comm_Table+" ("+com_id+" INTEGER PRIMARY KEY AUTOINCREMENT,"+com_con_id+" INTEGER,"+com_num+" TEXT, "+com_call_count+" INTEGER, "+com_sms_count+" INTEGER,"+com_email_count+" INTEGER,"+com_total_count+" INTEGER,FOREIGN KEY ("+com_con_id+") REFERENCES "+eTable+" ("+cid1+"))");
		db.execSQL("CREATE TABLE "+in_table+" ("+in_id+" INTEGER PRIMARY KEY AUTOINCREMENT,"+in_phn+" STRING)");

		//inserts original tables
		db.execSQL("INSERT INTO "+moreInfoTable+" SELECT "+mcid1+ ","+wadd+ " ,"+orgcw+ " ,"+orgco+ " ,"+orgpw+ ","+orgpo+ ","+orgcust+","+compadd+ ","+mnname+ ","+padd+ ","+paw+ ","+pao+ ","+pacust+ ","+birth+ ","+ani+","+colmflag+" FROM "+tempmoreInfoTable);
		db.execSQL("INSERT INTO "+userTable+" SELECT "+userid+ ","+ufname+ ","+umname+ ", "+ulname+ ","+umobno+ ","+umobnoh+ ","+umobnow+ ","+umobnoo+ ","+umobnocust+ ","+uemail+ ","+uemailw+ ","+uemailo+ ","+uemailcust+","+uimage+ ","+utag_type+","+utags+ ","+ucolflag+ ","+uim_id+ " FROM "+tempuserTable);
		db.execSQL("INSERT INTO "+usermoreInfoTable+" SELECT "+umcid1+ ","+uwadd+ ","+uorgcw+ ","+uorgco+ ","+uorgpw+ ","+uorgpo+ ","+uorgcust+ ","+ucompadd+ ","+umnname+ ","+upadd+ ","+upaw+ ","+upao+ ","+upacust+ ","+ubirth+ ","+uani+ ","+ucolmflag+ " FROM "+tempusermoreInfoTable);
		db.execSQL("INSERT INTO "+favoritesTable+" SELECT "+fav_id+ ","+fav_cid+","+fav_flag+" FROM "+tempfavoritesTable);
		db.execSQL("INSERT INTO "+listTable+" SELECT "+callid1+ ","+callname+ ","+calldate+ ","+calltime+ ","+calllistflag+" FROM "+templistTable);
		db.execSQL("INSERT INTO "+callListDetailsTable+" SELECT "+listid+ ","+con_id+ ","+flag+",null FROM "+tempcallListDetailsTable);
		db.execSQL("INSERT INTO "+taskTable+" SELECT "+colid +","+coltasko+","+coltflag+","+colname+","+coldesp+","+colsdate+","+colddate+","+coltpriority+","+collocname+","+coltime+","+colcon+" FROM "+temptaskTable);
		db.execSQL("INSERT INTO "+notesTable+" SELECT "+colnid+ ","+cont_id+ ","+colnname+ ","+colnotes+ ","+coldate+ ","+coltimes+ ","+nflag+",null FROM "+tempnotesTable);
		db.execSQL("INSERT INTO "+GroupTable+" SELECT "+group_id+ ","+group_name+" FROM "+tempGroupTable);
		db.execSQL("INSERT INTO "+GroupTableDetails+" SELECT "+group_id1+ ","+caller_id+" FROM "+tempGroupTableDetails);
		db.execSQL("INSERT INTO "+feedbackTable+" SELECT "+fid+ ","+emailid+" ,"+mobnum+" ,"+ratetags+","+ratecalllist+","+ratetasks+","+ratesync+" FROM "+tempfeedbackTable);
		db.execSQL("INSERT INTO "+account+" SELECT "+acc_id+ ","+ac_email+","+ac_pwd+" FROM "+tempaccount);
		db.execSQL("INSERT INTO "+account_flag+" SELECT "+acc_id_flag+ ","+acc_flag+" FROM "+tempaccount_flag);
		db.execSQL("INSERT INTO "+calllogTable+" SELECT "+colclgid+ ","+colcid+","+cldate+","+cltime+","+colnum+","+logtype+",null FROM "+tempcalllogTable);
		db.execSQL("INSERT INTO "+email_settings+" SELECT "+set_eid+ ","+set_emailflag+" FROM "+tempemail_settings);
		db.execSQL("INSERT INTO "+task_settings+" SELECT "+set_tid+ ","+set_taskflag+" FROM "+temptask_settings);
		db.execSQL("INSERT INTO "+EmailLogTable+" SELECT "+elogid+ ","+eid+","+ecount+","+colelogf+",null FROM "+tempEmailLogTable);
		db.execSQL("INSERT INTO "+SMSLogTable+" SELECT "+slogid+ ","+sid+","+scount+","+colslogf+" FROM "+tempSMSLogTable);
		db.execSQL("INSERT INTO "+sync+" SELECT "+s_id+ ","+type+",null FROM "+tempsync);
		db.execSQL("INSERT INTO "+callsTable+" SELECT "+ colcall+ ","+colcname+ " FROM "+tempcallsTable);
		db.execSQL("INSERT INTO "+notification_settings+" SELECT "+set_noid+ ","+set_notiflag+" FROM "+tempnotifiation_settings);
		db.execSQL("INSERT INTO "+window_settings+" SELECT "+set_winid+ ","+set_winflag+" FROM "+tempwindow_settings);
		db.execSQL("INSERT INTO "+smsRTable+" SELECT "+smsr_id+","+sender+","+messager+","+smsr_Date+",null,null,null FROM "+tempr_sms);
		db.execSQL("INSERT INTO "+smsSTable+" SELECT "+smss_id+","+receiver+","+messages+","+smss_Date+",null,null,null FROM "+temps_sms);
		db.execSQL("INSERT INTO "+sync_settings+" SELECT "+sync_id+","+con_sync+","+call_sync+","+task_sync+","+log_sync+","+sms_sync+" FROM "+temp_sync_set);
		db.execSQL("INSERT INTO "+smsRFlagTable+" SELECT "+smsrflag_id+","+smsr_flag+" FROM "+temprflag_sms);
		db.execSQL("INSERT INTO "+smsSFlagTable+" SELECT "+smssflag_id+","+smss_flag+" FROM "+tempsflag_sms);
		db.execSQL("INSERT INTO "+sync_login_Table+" SELECT "+sync_login_id+","+sync_login_email_id+","+sync_login_password+","+sync_login_flag+" FROM "+temp_sync_login_Table);
		db.execSQL("INSERT INTO "+auto_sync_details+" SELECT "+auto_sync_id+","+u_id+","+sync_type+","+auto_sync_flag+" FROM "+temp_auto_sync_details);
		db.execSQL("INSERT INTO "+sync_login_settings+" SELECT "+sync_login_settings_id+","+sync_login_settings_email_id+","+sync_login_settings_password+","+sync_login_settings_u_id+" FROM "+temp_sync_login_Settings);
		db.execSQL("INSERT INTO "+comm_Table+" SELECT "+com_id+","+com_con_id+","+com_num+","+com_call_count+", "+com_sms_count+","+com_email_count+","+com_total_count+" FROM "+temp_comm_Table);
		db.execSQL("INSERT INTO "+in_table+" SELECT "+in_id+","+in_phn+" FROM "+temp_in_table);
		
		//drop temp tables
		db.execSQL("DROP TABLE IF EXISTS "+tempmoreInfoTable);
		db.execSQL("DROP TABLE IF EXISTS "+tempuserTable);
		db.execSQL("DROP TABLE IF EXISTS "+tempusermoreInfoTable);
		db.execSQL("DROP TABLE IF EXISTS "+tempfavoritesTable);
		db.execSQL("DROP TABLE IF EXISTS "+templistTable);
		db.execSQL("DROP TABLE IF EXISTS "+tempcallListDetailsTable);
		db.execSQL("DROP TABLE IF EXISTS "+temptaskTable);
		db.execSQL("DROP TABLE IF EXISTS "+tempnotesTable);
		db.execSQL("DROP TABLE IF EXISTS "+tempGroupTable);
		db.execSQL("DROP TABLE IF EXISTS "+tempGroupTableDetails);
		db.execSQL("DROP TABLE IF EXISTS "+tempfeedbackTable);
		db.execSQL("DROP TABLE IF EXISTS "+tempaccount);
		db.execSQL("DROP TABLE IF EXISTS "+tempaccount_flag);
		db.execSQL("DROP TABLE IF EXISTS "+tempcalllogTable);
		db.execSQL("DROP TABLE IF EXISTS "+tempemail_settings);
		db.execSQL("DROP TABLE IF EXISTS "+temptask_settings);
		db.execSQL("DROP TABLE IF EXISTS "+tempEmailLogTable);
		db.execSQL("DROP TABLE IF EXISTS "+tempSMSLogTable);
		db.execSQL("DROP TABLE IF EXISTS "+tempsync);
		db.execSQL("DROP TABLE IF EXISTS "+tempcallsTable);
		db.execSQL("DROP TABLE IF EXISTS "+tempnotifiation_settings);
		db.execSQL("DROP TABLE IF EXISTS "+tempwindow_settings);
		db.execSQL("DROP TABLE IF EXISTS "+tempr_sms);
		db.execSQL("DROP TABLE IF EXISTS "+temps_sms);
		db.execSQL("DROP TABLE IF EXISTS "+temp_sync_set);
		db.execSQL("DROP TABLE IF EXISTS "+temprflag_sms);
		db.execSQL("DROP TABLE IF EXISTS "+tempsflag_sms);
		db.execSQL("DROP TABLE IF EXISTS "+temp_sync_login_Table);
		db.execSQL("DROP TABLE IF EXISTS "+temp_auto_sync_details);
		db.execSQL("DROP TABLE IF EXISTS "+temp_sync_login_Settings);
		db.execSQL("DROP TABLE IF EXISTS "+temp_comm_Table);
		db.execSQL("DROP TABLE IF EXISTS "+temp_in_table);

	}

	void Insert(String strfn,String strmn,String strln,String strmob,String strmobh,String strmobw,String strmobo,String strmobcust,String streid,String streidw,String streido,String streidcust,String strimg,String strtag_type,String strtag,int im_id1,String date1,String time1,String connotes,int flag1)
	{
		SQLiteDatabase myDB = this.getWritableDatabase();
		ContentValues cv=new ContentValues();

		cv.put(fname, strfn);                 
		cv.put(mname, strmn);
		cv.put(lname, strln);
		cv.put(mobno, strmob);
		cv.put(mobnoh, strmobh);
		cv.put(mobnow, strmobw);
		cv.put(mobnoo, strmobo);
		cv.put(mobnocust, strmobcust);
		cv.put(email, streid);
		cv.put(emailw, streidw);
		cv.put(emailo, streido);
		cv.put(emailcust, streidcust);
		cv.put(image,strimg);
		cv.put(tag_type,strtag_type);
		cv.put(tags,strtag);
		cv.put(im_id,im_id1);
		cv.put(colflag,flag1);
		cv.put(date,date1);
		cv.put(time,time1);
		cv.put(newconnotes,connotes);
		myDB.insert(eTable,null,cv);
     }


	void Insertfav(int cid,int flag1)
	{
		SQLiteDatabase myDB = this.getWritableDatabase();
		ContentValues cv=new ContentValues();
		cv.put(fav_cid, cid);
		cv.put(fav_flag, flag1);
		myDB.insert(favoritesTable,null,cv);
     }
	void Insertfeedback(String stremail,String strmobno,String strtagrate,String strcalllistrate,String strtaskrate,String strsyncrate)
	{
		SQLiteDatabase myDB = this.getWritableDatabase();
		ContentValues cv=new ContentValues();

		cv.put(emailid, stremail);                 
		cv.put(mobnum, strmobno);
		cv.put(ratetags, strtagrate);
		cv.put(ratecalllist, strcalllistrate);
		cv.put(ratetasks, strtaskrate);
		cv.put(ratesync, strsyncrate);
		myDB.insert(feedbackTable,null,cv);
	}
	void InsertGroup(String strgroupname)
	{
		SQLiteDatabase myDB = this.getWritableDatabase();
		ContentValues cv=new ContentValues();
		cv.put(group_name, strgroupname);                 
		myDB.insert(GroupTable,null,cv);
     }
	void InsertGroupDetails(int gid,int intcallid)
	{
		SQLiteDatabase myDB = this.getWritableDatabase();
		ContentValues cv=new ContentValues();
		cv.put(group_id1, gid);                 
		cv.put(caller_id, intcallid);
		myDB.insert(GroupTableDetails,null,cv);
	}
	
	

	void Inserttsettings()
	{
		SQLiteDatabase myDB = this.getWritableDatabase();
		ContentValues cv=new ContentValues();
		cv.put(set_taskflag,1);
		myDB.insert(task_settings,null,cv);
	}
	void Insertesettings()
	{
		SQLiteDatabase myDB = this.getWritableDatabase();
		ContentValues cv=new ContentValues();
		cv.put(set_emailflag,1);
	  myDB.insert(email_settings,null,cv);
	}
	void Inserttsettings_zero()
	{
		SQLiteDatabase myDB = this.getWritableDatabase();
		ContentValues cv=new ContentValues();
		cv.put(set_taskflag,0);
		myDB.insert(task_settings,null,cv);
	}
	void Insertesettings_zero()
	{
		SQLiteDatabase myDB = this.getWritableDatabase();
		ContentValues cv=new ContentValues();
		cv.put(set_emailflag,0);
	  myDB.insert(email_settings,null,cv);
	}
	
	void Insertnotisettings()
	{
		SQLiteDatabase myDB = this.getWritableDatabase();
		ContentValues cv=new ContentValues();
		cv.put(set_notiflag,1);
		myDB.insert(notification_settings,null,cv);
	}
	void Insertwinsettings()
	{
		SQLiteDatabase myDB = this.getWritableDatabase();
		ContentValues cv=new ContentValues();
		cv.put(set_winflag,1);
	  myDB.insert(window_settings,null,cv);
	}
	void Insertnotisettings_zero()
	{
		SQLiteDatabase myDB = this.getWritableDatabase();
		ContentValues cv=new ContentValues();
		cv.put(set_notiflag,0);
		myDB.insert(notification_settings,null,cv);
	}
	void Insertwinsettings_zero()
	{
		SQLiteDatabase myDB = this.getWritableDatabase();
		ContentValues cv=new ContentValues();
		cv.put(set_winflag,0);
	  myDB.insert(window_settings,null,cv);
	}
	
	void InsertcallLog(int cid,String date,String time,String dur,String num,int log_type)
	{
		SQLiteDatabase myDB = this.getWritableDatabase();
		ContentValues cv=new ContentValues();

		cv.put(colcid, cid);                 
		cv.put(cldate, date);
		cv.put(cltime, time);
		cv.put(cldur, dur);
		cv.put(colnum, num);
		cv.put(logtype, log_type);
		myDB.insert(calllogTable,null,cv);


	}
	

	
	void InsertMore(int id,String strwadd,String strorgcw,String strorgco,String strorgcust,String strorgpw,String strorgpo,String strcompadd, String strnname, String strpadd,String strpaw,String strpao,String strpacust, String strbirth, String strani)
	{
		SQLiteDatabase myDB = this.getWritableDatabase();
		ContentValues cv=new ContentValues();
		cv.put(mcid1, id);
		cv.put(wadd, strwadd);
	    cv.put(orgcw, strorgcw);
	    cv.put(orgco, strorgco);
	    cv.put(orgpw, strorgpw);
	    cv.put(orgpo, strorgpo);
	    cv.put(orgcust, strorgcust);
		cv.put(compadd,strcompadd);
		cv.put(mnname,strnname);
		cv.put(padd,strpadd);
		cv.put(paw, strpaw);
		cv.put(pao,strpao);
		cv.put(pacust, strpacust);
		cv.put(paw, strpaw);
		cv.put(pao, strpao);
		cv.put(birth,strbirth);
		cv.put(ani,strani);
		cv.put(colmflag,0);
		myDB.insert(moreInfoTable,null,cv);
  }
	Cursor getfeedback()
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur= db.rawQuery("SELECT * FROM "+feedbackTable,null);
		return cur;
	}
	Cursor get_fav_flag(int id)
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur= db.rawQuery("SELECT "+fav_flag+" FROM "+favoritesTable+" where "+fav_cid+"="+id,null);
		return cur;
	}
	Cursor get_fav_cid()
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur= db.rawQuery("SELECT "+fav_cid+" FROM "+favoritesTable,null);
		return cur;
	}
	Cursor get_favorites()
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur= db.rawQuery("SELECT "+fav_id+","+fav_cid+" FROM "+favoritesTable+" where "+fav_flag+"=1",null);
		return cur;
	}
	Cursor getgroupname(int id)
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur= db.rawQuery("SELECT "+group_name+" FROM "+GroupTable+" Where "+group_id+"="+id,null);
		return cur;
	}
	Cursor getgroupid(String name)
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur= db.rawQuery("SELECT "+group_id+" FROM "+GroupTable+" Where "+group_name+"='"+name+"'",null);
		return cur;
	}
	Cursor getaccid(String email) 
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur= db.rawQuery("SELECT "+acc_id+" FROM "+account+" Where  "+ac_email+"='"+email+"'",null);
		return cur;
	}

	Cursor getflag(int id) 
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur= db.rawQuery("SELECT "+acc_flag+" FROM "+account_flag+" Where "+acc_id_flag+"="+id,null);
		return cur;
	}
	Cursor getacc_flag() 
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur= db.rawQuery("SELECT "+acc_flag+" FROM "+account_flag,null);
		return cur;
	}
	Cursor get_all_groupname()
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur= db.rawQuery("SELECT "+group_name+" FROM "+GroupTable,null);
		return cur;
	}
	Cursor getgcont_id(int id)
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur= db.rawQuery("SELECT "+caller_id+" FROM "+GroupTableDetails+" Where "+group_id1+"="+id,null);
		return cur;
	}
	Cursor gettsettings()
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur= db.rawQuery("SELECT "+set_taskflag+","+set_tid+" FROM "+task_settings,null);
		return cur;
	}
	Cursor getesettings()
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur= db.rawQuery("SELECT "+set_emailflag+","+set_eid+" FROM "+email_settings,null);
		return cur;
	}

	Cursor getnotisettings()
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur= db.rawQuery("SELECT "+set_notiflag+" FROM "+notification_settings,null);
		return cur;
	}
	Cursor getwinsettings()
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur= db.rawQuery("SELECT "+set_winflag+" FROM "+window_settings,null);
		return cur;
	}
	
	Cursor getAllContacts()
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur= db.rawQuery("SELECT * FROM "+eTable,null);
		return cur;
	}
	Cursor getNo(String num)
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur= db.rawQuery("SELECT "+fname+" FROM "+eTable+" where "+mobno+"='"+num+"'",new String [] {});
		return cur;		
	}
	
	Cursor getData()
	{

		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur= db.rawQuery("SELECT "+fname+","+lname+","+mobno+","+image+","+cid1+","+mobnoh+","+mobnow+","+mobnoo+" FROM "+eTable+" ORDER BY "+fname+" ASC",new String [] {});
		return cur;		 
	}
	 		
		Cursor getTask(String date)
		 {
			SQLiteDatabase db=this.getWritableDatabase();
			Cursor cur= db.rawQuery("Select "+colname+","+colsdate+","+coltime+" from "+taskTable+" Where "+colddate+"='"+date+"'", null);
			
			return cur;
		 }
		
		Cursor getGroup()
		 {
			SQLiteDatabase db=this.getWritableDatabase();
			Cursor cur= db.rawQuery("Select * from "+GroupTable, null);
			
			return cur;
		 } 
		
	Cursor getDataLastName(String finame)
	{

		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur= db.rawQuery("SELECT "+cid1+","+lname+" FROM "+eTable+ " where "+fname+" ='"+finame+"'",null);
		return cur;		 
	}
	Cursor getidmob(String finame)
	{

		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur= db.rawQuery("SELECT "+cid1+","+mobno+" FROM "+eTable+ " where "+fname+" ='"+finame+"'",null);
		return cur;		 
	}
	Cursor getMoreInfoData()
	{

		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur= db.rawQuery("SELECT * FROM "+moreInfoTable,null);
		return cur;		 
	}
	Cursor Data12(int id)
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur1= db.rawQuery("SELECT "+cid1+","+fname+","+mname+","+lname+","+mobno+","+mobnoh+","+mobnow+","+mobnoo+","+email+","+emailw+","+emailo+","+emailcust+","+image+","+tags+" FROM "+eTable+ " where "+cid1+" ="+id,new String[]{});
		return cur1;

	}
	Cursor getnameandnum(int id)
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur1= db.rawQuery("SELECT "+fname+","+lname+","+mobno+","+mobnoh+","+mobnow+","+mobnoo+","+email+","+emailw+","+emailo+" FROM "+eTable+ " where "+cid1+" ="+id,new String[]{});
		return cur1;

	}
	Cursor getNAMEandID(String num)
		{
			SQLiteDatabase db=this.getReadableDatabase();
			Cursor cur=db.rawQuery("SELECT "+cid1+","+fname+","+lname+" from "+eTable+" Where "+mobno+"='"+num+"'",new String [] {});
			return cur;
		}
	Cursor getcontactname(int num)
	{
		 System.out.println("Id" +num);
			SQLiteDatabase db=this.getReadableDatabase();
			Cursor cur=db.rawQuery("SELECT "+fname+","+lname+" from "+eTable+" Where "+cid1+"="+num,new String [] {});
			return cur;	

	}
	Cursor getfname(String num)
	{
		 System.out.println("Id" +num);
			SQLiteDatabase db=this.getReadableDatabase();
			Cursor cur=db.rawQuery("SELECT "+fname+","+lname+" from "+eTable+" Where "+mobno+"='"+num+"' OR "+mobnoh+"='"+num+"' OR "+mobnow+"='"+num+"' OR "+mobnoo+"='"+num+"'",new String [] {});
			return cur;	

	}
	Cursor Data(String name,String lname1)
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur1= db.rawQuery("SELECT "+cid1+","+fname+","+lname+","+mobno+","+email+","+image+","+tags+" FROM "+eTable+ " where "+fname+" ='"+name+"' AND "+lname+" ='"+lname1+"'",new String[]{});
		return cur1;

	}


	Cursor getmobno()
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur= db.rawQuery("SELECT "+mobno+" FROM "+eTable,null);
		return cur;			 
	}
	Cursor getphno(String name,String lname1)
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur= db.rawQuery("SELECT "+mobno+","+mobnoh+","+mobnow+" FROM "+eTable+ " where "+fname+" ='"+name+"' AND "+lname+" ='"+lname1+"'",null);
		return cur;			 
	}
	Cursor Search(String s)
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur= db.rawQuery("SELECT * FROM "+eTable+ " where "+fname+" LIKE '"+s+"%'",null);			 
		return cur;			 
	}
	Cursor Searchfirstname(String firstname)
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur= db.rawQuery("SELECT "+fname+","+lname+","+mobno+","+image+","+cid1+" FROM "+eTable+ " where "+fname+" LIKE '"+firstname+"%'",null);			 
		return cur;			 
	}
	Cursor Searchlastname(String lastname)
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur= db.rawQuery("SELECT "+fname+","+lname+","+mobno+","+image+","+cid1+" FROM "+eTable+ " where "+lname+" LIKE '"+lastname+"%'",null);			 
		return cur;			 
	}
	Cursor Searchtags(String tags)
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur= db.rawQuery("SELECT "+fname+","+lname+","+mobno+","+image+","+cid1+" FROM "+eTable+ " where "+tags+" LIKE '"+tags+"%'",null);			 
		return cur;			 
	}
	public Cursor SearchTag(String s1) 
	{
		System.out.println("Names "+s1);
		String tag1="";
		String [] s=new String[10];
		s=s1.split(" ");
		if(s.length==1)
		{
			tag1 =fname+" LIKE '"+s[0]+"%'OR "
			+lname+" LIKE '"+s[0]+"%'OR "
			+tags+" LIKE '"+s[0]+"%'OR "
			+mobno+" LIKE '"+s[0]+"%'OR "
			+mobnoh+" LIKE '"+s[0]+"%'OR "
			+mobnow+" LIKE '"+s[0]+"%'";  

		}
		else
		{
			for(int i=0;i<s.length;i++)
			{
				if(tag1.equals(""))
				{

					tag1 =fname+" LIKE '"+s[i]+"%'OR "
					+lname+" LIKE '"+s[i]+"%'OR "
					+tags+" LIKE '"+s[i]+"%'OR "
			        +mobno+" LIKE '"+s[i]+"%'OR "
			        +mobnoh+" LIKE '"+s[i]+"%'OR "
			        +mobnow+" LIKE '"+s[i]+"%'";

				}
				else
				{

					tag1 = "(("+tag1+") AND ("+fname+" LIKE '"+s[i]+"%'OR " 
					+lname+" LIKE '"+s[i]+"%'OR "
					+tags+" LIKE '"+s[i]+"%'OR "
			        +mobno+" LIKE '"+s[i]+"%'OR "
			        +mobnoh+" LIKE '"+s[i]+"%'OR "
			        +mobnow+" LIKE '"+s[i]+"%'"+"))";
 
				}			

			}
		}

		SQLiteDatabase db=this.getReadableDatabase();
		System.out.println("Query "+tag1);
		Cursor cur=db.rawQuery("SELECT "+fname+","+lname+","+mobno+","+image+","+mobnoh+","+mobnow+","+mobnoo+" FROM  "+eTable+" WHERE "+tag1,new String [] {});

		return cur;
	}

	//Insert Data into CallList table
	
	Cursor getDataCall()
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur= db.rawQuery("SELECT * FROM "+listTable,null);
		return cur;		 
	}
	public int updateflag(int tId) {
		SQLiteDatabase db= this.getWritableDatabase();
		 ContentValues cv=new ContentValues();
		 cv.put(colflag,1);
		 return db.update(taskTable, cv,colid+"="+tId, new String []{});
		
	}

	Cursor getDataCallid(String name1)
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur= db.rawQuery("SELECT "+callid1+" FROM "+listTable+" Where "+callname+" ='"+name1+"'",new String [] {});
		//cur.close();
		return cur;		 
	}
	public Cursor gettask1()
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor c=db.rawQuery("SELECT "+coltasko+","+colname+","+coldesp+","+coltype+","+coltpriority+","+colcat+","+colsdate+","+colddate+","+colloc+","+coltime+","+colcon+" from "+taskTable,new String [] {});
		 return c;
	}
	public Cursor taskdetails(int c_id)
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor c=db.rawQuery("SELECT "+coltasko+","+colname+","+coldesp+","+coltpriority+","+colsdate+","+colddate+","+coltime+","+colcon+" from "+taskTable+" where "+colcon+"="+c_id,new String [] {});
		 return c;
	}
	Cursor getCallListName()
	{

		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT "+callname+" from "+listTable,new String [] {});
		return cur;		 
	}
	Cursor getCallListid1(String listname)
	{

		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT "+callid1+" from "+listTable+" Where "+callname+" ='"+listname+"'",new String [] {});
		return cur;		 
	}
	public void deleteCallListName(int id)
	{
		SQLiteDatabase db=this.getReadableDatabase();
		db.delete(listTable,callid1+"="+id, new String []{} );
	
	}
	public void deleteGroupName(int id)
	{
		SQLiteDatabase db=this.getReadableDatabase();
		db.delete(GroupTable,group_id+"="+id, new String []{} );
	
	}
	public void deleteaccount(int id)
	{
		SQLiteDatabase db=this.getReadableDatabase();
		db.delete(account,acc_id+"="+id, new String []{} );
	
	}
	
	
	Cursor getDataCallList1(int callid2,String n1)
	{

		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT "+mobno+" FROM "+eTable+" Where "+cid1+"="+callid2+" AND "+fname+" ='"+n1+"'",new String [] {});
		return cur;		 
	}
	
	
	public void deletecontact(int id)
	{
		SQLiteDatabase db= this.getWritableDatabase();
		db.delete(eTable,cid1+"="+id, new String []{} );

	}

	public void delete()
	{ 
		SQLiteDatabase db=this.getWritableDatabase();

		db.execSQL("DROP TABLE IF EXISTS "+eTable);
	}
	Cursor deletecallLog()
	{ 
		SQLiteDatabase db=this.getReadableDatabase();
		//db.delete(calllogTable,null, new String []{} );
		Cursor c= db.rawQuery("DELETE FROM "+calllogTable,null);
		return c;	
	}
	Cursor gettaskData()
	{

		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur= db.rawQuery("SELECT * FROM "+taskTable,null);
		return cur;		 
	}
	int getTaskCnt()
	{
		SQLiteDatabase db=this.getWritableDatabase();
		Cursor cur= db.rawQuery("Select * from "+taskTable, null);
		int x= cur.getCount();
		cur.close();
		return x;
	}


	Cursor gettask_today(String sdate)
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT "+colname+" ,"+coldesp+","+colddate+" from "+taskTable+" where "+colddate+" = '"+sdate+"'",new String [] {});

		return cur;
	}


	void getAllloc()
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT * from "+locationTable,new String [] {});
		while(cur.moveToNext())
		{
			int lid=cur.getInt(0);
			String lname=cur.getString(1);
			String laddr=cur.getString(2);
			System.out.print("lid=="+lid+" lname="+lname+" laddr=="+laddr);

		}


	}
	void Inserttask(String towner,String tname,String tdesp,String pr,String sdate,String date,String time,int cid,String lname)
	{	 
		SQLiteDatabase db= this.getWritableDatabase();
		ContentValues cv=new ContentValues();
		cv.put(coltasko,towner);
		cv.put(colflag,"0");
		cv.put(colname,tname);
		cv.put(coldesp,tdesp);
		cv.put(coltpriority,pr);
		cv.put(colsdate,sdate);
		cv.put(colddate,date);
		cv.put(collocname,lname);
		cv.put(coltime,time);
		cv.put(colcon,cid);
		cv.put(coltflag,0);
		db.insert(taskTable,null,cv);



	}
	

	
	public Cursor getconid(String cname,String cnum)
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT "+cid1+" from "+eTable+" Where "+fname+"='"+cname+"'"+
				" and "+mobno+" ='"+cnum+"'",new String [] {});

		return cur;
	}

	public Cursor callalert(String date)
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT "+colcon+" from "+taskTable+" where "+colddate
				+" = '"+date+"'",new String [] {});
		while(cur.moveToNext())
		{
			cid=cur.getInt(0);
		}
		System.out.println("ciddd"+cid);
		Cursor c = db.rawQuery("SELECT "+colconname+" ,"+colconnum+" from "+contactTable+" where "+colconid
				+" = "+cid+" ",new String [] {});

		return c;
	}



	public Cursor getDetails(int id) 
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT "+colname+","+coldesp+","+coltpriority+","+colddate+","+coltime+","+collocname+" from "+taskTable+" where "+colid+" ="+id,new String [] {});
		return cur;


	}

	public Cursor getDetails1() 
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT * from "+taskTable,new String [] {});
		return cur;


	}

	public Cursor getcallid(String listname) 
	{

		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT "+callid1+" from "+listTable+" Where "+callname+"='"+listname+"'",new String [] {});
		return cur;

	}

	public Cursor getcalllist()
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT * from "+calllistTable,new String [] {});
		return cur;
	}

	public Cursor getcallnamelist(int callid2)
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT "+colcname+" from "+callsTable+" Where "+colcall+"="+callid2,new String [] {});
		return cur;


	}

	public void deletetask(int id)
	{
		SQLiteDatabase db= this.getWritableDatabase();
		db.delete(taskTable,colid+"="+id, new String []{} );

	}

	public Cursor getcdetails(int cid2)
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT "+colconname+","+colconnum+" from "+contactTable+" Where "+colconid+"="+cid2,new String [] {});
		return cur;


	}

	public Cursor getldetails(int lid) 
	{

		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT "+collocname+","+collocaddr+" from "+locationTable+" Where "+collocid+"="+lid,new String [] {});
		return cur;

	}

	


	public Cursor gettask_upcomi(String sdate)
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT "+colname+" ,"+coldesp+","+colddate+" from "+taskTable+" where "+colddate+" > '"+sdate+"'",new String [] {});
		return cur;
	}

	public Cursor gettask_overdue(String sdate)
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT "+colname+" ,"+coldesp+","+colddate+" from "+taskTable+" where "+colddate+" < '"+sdate+"'",new String [] {});
		return cur;
	}

	
	public Cursor getnotes()
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT "+colnid+" ,"+colnname+","+colnotes+" from "+notesTable ,new String [] {});
		return cur;
	}

	

	public Cursor gettask(String oname, String sdate)
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT "+colname+" from "+taskTable+" where "+colddate+" = '"+sdate+"' and "+coltasko+" = '"+oname+"'",new String [] {});
		String str5="SELECT "+colname+" from "+taskTable+" where "+colddate+" = '"+sdate+"' and "+coltasko+" = '"+oname+"'";
		System.out.println("Query "+str5);
		return cur;
	}
	
	public Cursor getcontact(int c_id)
	{
		
		SQLiteDatabase db=this.getReadableDatabase();
		 Cursor c=db.rawQuery("SELECT "+fname+","+mname+","+lname+","+mobno+","+mobnoh+","+mobnow+","+mobnoo+","+mobnocust+","+email+","+emailw+","+emailo+","+emailcust+","+image+","+tag_type+","+tags+","+cid1+" from "+eTable+" where "+cid1+"="+c_id,new String [] {});
		 return c;
	}
	public Cursor getuser()
	{
		
		SQLiteDatabase db=this.getReadableDatabase();
		 Cursor c=db.rawQuery("SELECT "+ufname+","+umname+","+ulname+","+umobno+","+umobnoh+","+umobnow+","+umobnoo+","+umobnocust+","+uemail+","+uemailw+","+uemailo+","+uemailcust+","+uimage+","+utag_type+","+utags+","+userid+" from "+userTable,new String [] {});
		 return c;
	}
	public Cursor getFlagC()
	{
		SQLiteDatabase db=this.getReadableDatabase();
		 Cursor c=db.rawQuery("SELECT "+colflag+","+cid1+" from "+eTable,new String [] {});
		 return c;
	}
	public Cursor getMoreFlagC()
	{
		SQLiteDatabase db=this.getReadableDatabase();
		 Cursor c=db.rawQuery("SELECT "+colmflag+" from "+moreInfoTable,new String [] {});
		 return c;
	}
	public Cursor getTaskFlag(int c_id)
	{
		SQLiteDatabase db=this.getReadableDatabase();
		 Cursor c=db.rawQuery("SELECT "+coltflag+" from "+taskTable+" where "+colcon+"="+c_id,new String [] {});
		 return c;
	}
	public int updateflagc(int cId2) {
		SQLiteDatabase db= this.getWritableDatabase();
		 ContentValues cv=new ContentValues();
		 cv.put(colflag,1);
		 return db.update(eTable, cv,cid1+"="+cId2, new String []{});
	}
	public int updateflaguser(int cId2) {
		SQLiteDatabase db= this.getWritableDatabase();
		 ContentValues cv=new ContentValues();
		 cv.put(ucolflag,1);
		 return db.update(userTable, cv,userid+"="+cId2, new String []{});
	}
	public int updatefavflag(int cId2) {
		SQLiteDatabase db= this.getWritableDatabase();
		 ContentValues cv=new ContentValues();
		 cv.put(fav_flag,0);
		 return db.update(favoritesTable, cv,fav_cid+"="+cId2, new String []{});
	}
	public int updateflagcm(int cId2) {
		SQLiteDatabase db= this.getWritableDatabase();
		 ContentValues cv=new ContentValues();
		 cv.put(colmflag,1);
		 return db.update(moreInfoTable, cv,mcid1+"="+cId2, new String []{});	
	}
	public int updateflagt(int cId2) {
		SQLiteDatabase db= this.getWritableDatabase();
		 ContentValues cv=new ContentValues();
		 cv.put(coltflag,1);
		 return db.update(taskTable, cv,colcon+"="+cId2, new String []{});
		
		
	}
	public Cursor getlistname(int callid) {
		SQLiteDatabase db=this.getReadableDatabase();
		 Cursor c=db.rawQuery("SELECT "+callname+","+calldate+","+calltime+" from "+listTable+" where "+callid1+"="+callid,new String [] {});
		 return c;
	}

	public Cursor getFlaglist() {
		SQLiteDatabase db=this.getReadableDatabase();
		 Cursor c=db.rawQuery("SELECT "+calllistflag+","+callid1+" from "+listTable,new String [] {});
		 return c;
	}

	public int updateflagl(int listid) {
		SQLiteDatabase db= this.getWritableDatabase();
		 ContentValues cv=new ContentValues();
		 cv.put(calllistflag,1);
		 return db.update(listTable, cv,callid1+"="+listid, new String []{});
		
	}
	
	public int updateflagl1(int listid1) {
		SQLiteDatabase db= this.getWritableDatabase();
		 ContentValues cv=new ContentValues();
		 cv.put(flag,1);
		 return db.update(callListDetailsTable, cv,listid+"="+listid, new String []{});
		
	}
	public Cursor getName(String mailid)
	{
		SQLiteDatabase db=this.getReadableDatabase();
		 Cursor c=db.rawQuery("SELECT "+cid1+","+fname+","+mobno+","+mobnoh+","+mobnow+","+mobnoo+" from "+eTable+" where "+email+" = '"+mailid+"'",new String [] {});
		 return c;
	}
	public Cursor getMNo(String no)
	{
		System.out.println("Sms "+no);
		SQLiteDatabase db=this.getReadableDatabase();
		 Cursor c=db.rawQuery("SELECT "+cid1+","+fname+" from "+eTable+" where "+mobno+" = '"+no+"'",new String [] {});
		 return c;
	}
	public Cursor getcontidfrommail(String mail)
	{
		System.out.println("Email "+mail);
		SQLiteDatabase db=this.getReadableDatabase();
		 Cursor c=db.rawQuery("SELECT "+cid1+","+fname+" from "+eTable+" where "+email+" = '"+mail+"'",new String [] {});
		 return c;
	}
	public void insertmaillog(int conid,int cnt,String number1) 
	{
		SQLiteDatabase db= this.getWritableDatabase();
		ContentValues cv=new ContentValues();
		cv.put(eid,conid);
		cv.put(ecount,cnt);
		cv.put(colelogf,0);
		cv.put(enumber,number1);
		db.insert(EmailLogTable,null,cv);
	}

	public Cursor getcount(int econid) 
	{
		SQLiteDatabase db=this.getReadableDatabase();
		 Cursor c=db.rawQuery("SELECT "+ecount+" from "+EmailLogTable+" where "+eid+" ="+econid,new String [] {});
		 return c;	
	}
	public Cursor getmaildata(int econid) 
	{
		SQLiteDatabase db=this.getReadableDatabase();
		 Cursor c=db.rawQuery("SELECT "+eid+","+ecount+" from "+EmailLogTable+" where "+eid+" ="+econid,new String [] {});
		 return c;	
	}
	public Cursor getemailscore() 
	{
		SQLiteDatabase db=this.getReadableDatabase();
		 Cursor c=db.rawQuery("SELECT "+eid+","+ecount+" from "+EmailLogTable,new String [] {});
		 return c;	
	}
	public int updateemailcnt(int conid, int count,String number1) 
	{
		SQLiteDatabase db= this.getWritableDatabase();
		ContentValues cv=new ContentValues();
		cv.put(ecount,count);
		return db.update(EmailLogTable, cv,eid+"="+conid+" and "+enumber+"='"+number1+"'", new String []{});
		
	}

	public void insertsmslog(int conid,int cnt) 
	{
		SQLiteDatabase db= this.getWritableDatabase();
		ContentValues cv=new ContentValues();
		cv.put(sid,conid);
		cv.put(scount,cnt);
		cv.put(colslogf,0);
		db.insert(SMSLogTable,null,cv);
	}
	public int updatesmscnt(int conid, int count) 
	{
		SQLiteDatabase db= this.getWritableDatabase();
		ContentValues cv=new ContentValues();
		cv.put(scount,count);
		return db.update(SMSLogTable, cv,sid+"="+conid, new String []{});
		
	}
	public Cursor getsmsdata() 
	{
		SQLiteDatabase db=this.getReadableDatabase();
		 Cursor c=db.rawQuery("SELECT "+sid+","+scount+" from "+SMSLogTable,new String [] {});
		 return c;	
	}
	public Cursor getsmsscore() 
	{
		SQLiteDatabase db=this.getReadableDatabase();
		 Cursor c=db.rawQuery("SELECT "+sid+","+scount+" from "+SMSLogTable,new String [] {});
		 return c;	
	}
	public Cursor getscnt(int sconid) 
	{
		SQLiteDatabase db=this.getReadableDatabase();
		 Cursor c=db.rawQuery("SELECT "+scount+" from "+SMSLogTable+" where "+sid+" ="+sconid,new String [] {});
		 return c;	
	}
	public Cursor getcal_log() 
	{
		SQLiteDatabase db=this.getReadableDatabase();
		 Cursor c=db.rawQuery("SELECT "+colcid+","+cldate+","+cltime+","+cldur+","+colnum+","+logtype+","+colclgid+" from "+calllogTable+" ORDER BY "+colclgid+" DESC" ,new String [] {});
		 return c;	
	}
	public Cursor get_log(String num) 
	{
		SQLiteDatabase db=this.getReadableDatabase();
		 Cursor c=db.rawQuery("SELECT "+cldate+","+cltime+","+logtype+","+colclgid+" from "+calllogTable+" where "+colnum+" = '"+num+"'",new String [] {});
		 return c;	
	}
	public Cursor getlogs() 
	{
		SQLiteDatabase db=this.getReadableDatabase();
		 Cursor c=db.rawQuery("SELECT "+colcid+","+cldate+","+cltime+","+colnum+","+logtype+" from "+calllogTable,new String [] {});
		 return c;	
	}

	public Cursor getemail_log() {
		SQLiteDatabase db=this.getReadableDatabase();
		 Cursor c=db.rawQuery("SELECT "+elogid+","+eid+","+ecount+" from "+EmailLogTable,new String [] {});
		 return c;
	}
	

	public Cursor getsms_log() {
		SQLiteDatabase db=this.getReadableDatabase();
		 Cursor c=db.rawQuery("SELECT "+slogid+","+sid+","+scount+" from "+SMSLogTable,new String [] {});
		 return c;
	}

	public int updateflagelog(String elogId) {
		SQLiteDatabase db= this.getWritableDatabase();
		 ContentValues cv=new ContentValues();
		 cv.put(colelogf,"1");
		 return db.update(EmailLogTable, cv,elogid+"="+elogId, new String []{});
		
	}

	public int updateflagslog(String slogId) {
		SQLiteDatabase db= this.getWritableDatabase();
		 ContentValues cv=new ContentValues();
		 cv.put(colslogf,"1");
		 return db.update(SMSLogTable, cv,slogid+"="+slogId, new String []{});
		
	}
	

	Cursor getMoreInfo(int id)
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur1= db.rawQuery("SELECT * FROM "+moreInfoTable+ " where "+mcid1+" ="+id+" ",new String[]{});
		return cur1;

	}

	Cursor Data1(int id)
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur1= db.rawQuery("SELECT "+cid1+","+fname+","+lname+","+mobno+","+email+","+image+","+tags+" FROM "+eTable+ " where "+cid1+" ="+id+" ",new String[]{});
		return cur1;

	}

	public Cursor get_task_upcomingDetails(String taskname) {
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT "+coltasko+","+coldesp+","+colddate+","+coltime+" from "+taskTable+" where "+colname+" = '"+taskname+"'",new String [] {});
		return cur;
	}

	
	Cursor getContactId(String name,String name1)
	{

		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT "+cid1+" from "+eTable+" Where "+fname+" ='"+name+"' AND "+lname+" ='"+name1+"'",new String [] {});
		return cur;		 
	}
	Cursor getMobNo(int id)
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT "+mobno+","+image+" from "+eTable+" Where "+cid1+"="+id,new String [] {});
		return cur;
	}
	Cursor getNAME(String num)
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT "+fname+","+lname+" from "+eTable+" Where "+mobno+"='"+num+"'",new String [] {});
		return cur;
	}
	public void insertacc(String email,String pwd) 
	{
		SQLiteDatabase db= this.getWritableDatabase();
		ContentValues cv=new ContentValues();
		
		cv.put(ac_email,email);
		cv.put(ac_pwd,pwd);
		db.insert(account,null,cv);
	}
	public void insertacc_flag(int id,int flag) 
	{
		SQLiteDatabase db= this.getWritableDatabase();
		ContentValues cv=new ContentValues();
		cv.put(acc_id_flag,id);
		cv.put(acc_flag,flag);
		db.insert(account_flag,null,cv);
	}

	public Cursor getaccid()
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur= db.rawQuery("SELECT MAX("+acc_id+") FROM "+account,null);
		return cur;
	}
    public Cursor getAccDetails()
    {
    	SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur= db.rawQuery("SELECT "+ac_email+","+ac_pwd+" FROM "+account+" ORDER BY "+acc_id+" DESC LIMIT 1",null);
		return cur;
    	
    }
    public Cursor viewAccDetails()
    {
    	SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur= db.rawQuery("SELECT "+ac_email+","+acc_id+" FROM "+account,null);
		return cur;
    	
    }



	public Cursor CallListId(String listname1) 
	{
		System.out.println("Listname............." +listname1);
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT "+callid1+" from "+listTable+" where "+callname+" ='"+listname1+"'",new String [] {});
		return cur;		
	}

	public Cursor getCurrentNotes(int cid)
    {
    	SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur= db.rawQuery("SELECT "+colnotes+","+coldate+","+coltimes+" FROM "+notesTable+" where "+cont_id+" ="+cid+" ORDER BY "+colnid+" DESC" ,null);
		return cur;
    	
    }
	public Cursor getCurrentNotes_num(String number)
    {
    	SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur= db.rawQuery("SELECT "+colnotes+","+coldate+","+coltimes+" FROM "+notesTable+" where "+colnmob+" ='"+number+"' ORDER BY "+colnid+" DESC" ,null);
		return cur;
    	
    }
	public Cursor getnowTasks(int cid)
    {
    	SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur= db.rawQuery("SELECT "+colname+","+coldesp+","+colsdate+","+coltime+","+colid+" FROM "+taskTable+" where "+colcon+" ="+cid+" ORDER BY "+colid+" DESC" ,null);
		return cur;
    	
    }

	public Cursor getimid(int id)
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT "+cid1+" from "+eTable+" Where "+im_id+"="+id,new String [] {});
		return cur;

	}

	public Cursor importdetails(int cid)
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur1= db.rawQuery("SELECT "+mobno+","+mobnoh+","+mobnow+","+mobnoo+","+email+","+emailw+","+emailo+" FROM "+eTable+ " where "+cid1+"="+cid,new String[]{});
		return cur1;
	}

	public int updateimport(int cid,String n, String nh, String nw, String no) 
	{
		SQLiteDatabase db= this.getWritableDatabase();
		ContentValues cv=new ContentValues();
		//cv.put(cid1,cid);
		cv.put(mobno,n);
		cv.put(mobnoh,nh);
		cv.put(mobnow,nw);
		cv.put(mobnoo,no);
		return db.update(eTable, cv,cid1+"="+cid, new String []{});
	}

	public Cursor checkname(String name) 
	{
		
		SQLiteDatabase db=this.getReadableDatabase();
		 Cursor c=db.rawQuery("SELECT "+callid1+" from "+listTable+" Where "+callname+" ='"+name+"'" ,new String [] {});
		 return c;
	}

	public int UpdateList(int id,String listname,String date,String time)
	{
		SQLiteDatabase db= this.getWritableDatabase();
		ContentValues cv=new ContentValues();
		cv.put(callname,listname);
		cv.put(calldate,date);
		cv.put(calltime,time);
		cv.put(calllistflag,0);
		return db.update(listTable,cv,callid1+"="+id, new String []{});
	}
	void InsertCall(String strcn,String strdate,String strtime)
	{
		SQLiteDatabase DB1 = this.getWritableDatabase();
		ContentValues cv=new ContentValues();
		cv.put(callname, strcn);
		cv.put(calldate, strdate);
		cv.put(calltime, strtime);
		cv.put(calllistflag,0);
		DB1.insert(listTable,null,cv);

	}
	public Cursor SearchCallList(String s1) 
	{
		System.out.println("Names "+s1);
		String tag1="";
		String [] s=new String[10];
		s=s1.split(" ");
		if(s.length==1)
		{
			tag1 =callname+" LIKE '"+s[0]+"%' OR "
			+calldate+" LIKE '"+s[0]+"%' OR "
			+calltime+" LIKE '"+s[0]+"%'";
		}
		else
		{
			for(int i=0;i<s.length;i++)
			{
				if(tag1.equals(""))
				{

					tag1 =callname+" LIKE '"+s[i]+"%' OR "
					+calldate+" LIKE '"+s[i]+"%' OR "
			+calltime+" LIKE '"+s[i]+"%'";
					
				}
				else
				{

					tag1 = tag1+" AND "+callname+" LIKE '"+s[i]+"%' OR " 
					+calldate+" LIKE '"+s[i]+"%'OR "
			+calltime+" LIKE '"+s[i]+"%'";
					
				}			

			}
		}

		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT "+callname+","+calldate+","+calltime+" FROM  "+listTable+" WHERE "+tag1,new String [] {});

		return cur;
	}

	
	public Cursor getListID(String Listname)
	{
		SQLiteDatabase db=this.getReadableDatabase();
		 Cursor c=db.rawQuery("SELECT "+callid1+" from "+listTable+" where "+callname+" ='"+Listname+"'",new String [] {});
		 return c;
		
	}
	
	/*public Cursor SearchCallListDetails(String s1) 
	{
		System.out.println("Names "+s1);
		String tag1="";
		String [] s=new String[10];
		s=s1.split(" ");
		if(s.length==1)
		{
			tag1 =callfname+" LIKE '%"+s[0]+"%' OR "
			+calllname+" LIKE '%"+s[0]+"%'";
			
		}
		else
		{
			for(int i=0;i<s.length;i++)
			{
				if(tag1.equals(""))
				{

					tag1 =callfname+" LIKE '%"+s[i]+"%' OR "
					+calllname+" LIKE '%"+s[i]+"%'";
				}
				else
				{

					tag1 = tag1+" AND "+callfname+" LIKE '%"+s[i]+"%' OR " 
					+calllname+" LIKE '%"+s[i]+"%'";
				}			

			}
		}

		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT "+callfname+","+calllname+" FROM  "+callListTable+" where "+callfname+" IN ( SELECT "+callfname+" FROM "+callListTable+
				" WHERE ("+tag1+"))",new String [] {});

		return cur;
	}
	
	*/
	
	
	

	public Cursor retCallId(String ans) {
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT "+callid1+" from "+listTable+" Where "+callname+" ='"+ans+"'",new String [] {});

    		return cur;	
	}
	public Cursor retGrouplId(String ans) {
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT "+group_id+" from "+GroupTable+" Where "+group_name+" ='"+ans+"'",new String [] {});

    		return cur;	
	}
	  public DataBaseHelper open() throws SQLException 
	    {
		  SQLiteDatabase db = this.getWritableDatabase();
	        return this;
	    }

	public void insync(int type2,String userid1)                
	{

		SQLiteDatabase DB1 = this.getWritableDatabase();
		ContentValues cv=new ContentValues();
		cv.put(type,type2);
		cv.put(sync_acc_id, userid1);
		DB1.insert(sync,null,cv);
		
	}
	public int updatesynctype(int id1,int type1,String userid3)
	{
		SQLiteDatabase db= this.getWritableDatabase();
		 ContentValues cv=new ContentValues();
		 cv.put(type,type1);
		 return db.update(sync,cv,s_id+"="+id1+" and "+sync_acc_id+"='"+userid3+"'", new String []{});
		
	}
	public void insync1()                
	{

		SQLiteDatabase DB1 = this.getWritableDatabase();
		ContentValues cv=new ContentValues();
		cv.put(type,1);
		DB1.insert(sync,null,cv);
		
	}
	public Cursor getType(String userid2) 
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT "+type+","+s_id+" from "+sync+" Where "+sync_acc_id+" ='"+userid2+"'",new String [] {});
		return cur;	
		
	}
	public int updatecontact(int coid, String scname, String sclname,String scmobno,String scmobnoh,String scmobnow,String scmobnoo,String scemail,String scemailw,String scemailo,String scimg,String sctags) 
	{
		SQLiteDatabase db= this.getWritableDatabase();
		ContentValues cv=new ContentValues();
		cv.put(fname,scname);
		cv.put(lname,sclname);
		cv.put(mobno,scmobno);
		cv.put(mobnoh,scmobnoh);
		cv.put(mobnow,scmobnow);
		cv.put(mobnoo,scmobnoo);
		cv.put(email, scemail);
		cv.put(emailw, scemailw);
		cv.put(emailo, scemailo);
		cv.put(image,scimg);
		cv.put(tags,sctags);
		cv.put(colflag,0);
		return db.update(eTable, cv,cid1+"="+coid, new String []{});
	}
	public int updatemorecontact(int id,String strwadd,String strorgcw,String strorgco,String strorgcust,String strorgpw,String strorgpo,String strcompadd, String strnname, String strpadd,String strpaw,String strpao,String strpacust, String strbirth, String strani)
	{
		SQLiteDatabase myDB = this.getWritableDatabase();
		ContentValues cv=new ContentValues();
		cv.put(wadd, strwadd);
	    cv.put(orgcw, strorgcw);
	    cv.put(orgco, strorgco);
	    cv.put(orgpw, strorgpw);
	    cv.put(orgpo, strorgpo);
	    cv.put(orgcust, strorgcust);
		cv.put(compadd,strcompadd);
		cv.put(mnname,strnname);
		cv.put(padd,strpadd);
		cv.put(paw, strpaw);
		cv.put(pao,strpao);
		cv.put(pacust, strpacust);
		cv.put(paw, strpaw);
		cv.put(pao, strpao);
		cv.put(birth,strbirth);
		cv.put(ani,strani);
		return  myDB.update(moreInfoTable, cv,mcid1+"="+id, new String []{});

	}
	public int updateuser(int coid, String scname, String sclname,String scmobno,String scmobnoh,String scmobnow,String scmobnoo,String scemail,String scemailw,String scemailo,String scimg,String sctags) 
	{
		SQLiteDatabase db= this.getWritableDatabase();
		ContentValues cv=new ContentValues();
		cv.put(ufname,scname);
		cv.put(ulname,sclname);
		cv.put(umobno,scmobno);
		cv.put(umobnoh,scmobnoh);
		cv.put(umobnow,scmobnow);
		cv.put(umobnoo,scmobnoo);
		cv.put(uemail, scemail);
		cv.put(uemailw, scemailw);
		cv.put(uemailo, scemailo);
		cv.put(uimage,scimg);
		cv.put(utags,sctags);
		cv.put(ucolflag,0);
		return db.update(userTable, cv,userid+"="+coid, new String []{});
	}
	public int updateusermore(int id,String strwadd,String strorgcw,String strorgco,String strorgcust,String strorgpw,String strorgpo,String strcompadd, String strnname, String strpadd,String strpaw,String strpao,String strpacust, String strbirth, String strani)
	{
		SQLiteDatabase myDB = this.getWritableDatabase();
		ContentValues cv=new ContentValues();
		cv.put(uwadd, strwadd);
	    cv.put(uorgcw, strorgcw);
	    cv.put(uorgco, strorgco);
	    cv.put(uorgpw, strorgpw);
	    cv.put(uorgpo, strorgpo);
	    cv.put(uorgcust, strorgcust);
		cv.put(ucompadd,strcompadd);
		cv.put(umnname,strnname);
		cv.put(upadd,strpadd);
		cv.put(upaw, strpaw);
		cv.put(upao,strpao);
		cv.put(upacust, strpacust);
		cv.put(upaw, strpaw);
		cv.put(upao, strpao);
		cv.put(ubirth,strbirth);
		cv.put(uani,strani);
		return  myDB.update(usermoreInfoTable, cv,umcid1+"="+id, new String []{});

	}
	public Cursor getnoteflag() 
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT "+colnid+" ,"+nflag+" from "+notesTable ,new String [] {});
		return cur;
	}
	public Cursor getnoteflag1(int c_id) 
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT "+colnid+" ,"+nflag+" from "+notesTable+" where "+cont_id+"="+c_id,new String [] {});
		return cur;
	}
	public Cursor notesdetails(int nid)
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT "+colnname+","+colnotes+" from "+notesTable+" where "+colnid+"="+nid,new String [] {});
		return cur;
	}
	public Cursor notesdetails1(int c_id)
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT "+colnname+","+colnmob+","+colnotes+","+coldate+","+coltimes+" from "+notesTable+" where "+cont_id+"="+c_id,new String [] {});
		return cur;
	}

	public int updateflagnote(int nId)
	{
		SQLiteDatabase db= this.getWritableDatabase();
		 ContentValues cv=new ContentValues();
		 cv.put(nflag,1);
		 return db.update(notesTable,cv,colnid+"="+nId, new String []{});
		
	}
	
	public void deleteAllContacts(int id)
	{
		SQLiteDatabase db=this.getReadableDatabase();
		db.delete(eTable,cid1+"="+id, new String []{} );
	
	}
	Cursor getAllcon()
	{

		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur= db.rawQuery("SELECT "+cid1+","+fname+","+lname+" FROM "+eTable+" ORDER BY "+fname+" ASC",new String [] {});
		return cur;		 
	}

	public Cursor getContactIdAndNum(String name, String name1) {
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT "+cid1+","+mobno+","+image+" from "+eTable+" Where "+fname+" ='"+name+"' AND "+lname+" ='"+name1+"'",new String [] {});
		return cur;		
	}

	public Cursor getconid_calllist(int id) 
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT "+con_id+","+callflag+" from "+callListDetailsTable+" Where "+listid+"="+id,new String [] {});
		return cur;		
	}
	public Cursor getcontacts(int id) 
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT "+fname+","+lname+","+mobno+","+image+","+mobnoh+","+mobnow+","+mobnoo+" from "+eTable+" Where "+cid1+"="+id,new String [] {});
		return cur;		
	}

	
	public Cursor getnumbers(int id) 
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT "+fname+","+lname+","+mobno+","+mobnoh+","+mobnow+","+mobnoo+","+mobnocust+" from "+eTable+" Where "+cid1+"="+id,new String [] {});
		return cur;		
	}
	public void deleteCallList_Contacts(int lid,int cid) 
	{
		
			SQLiteDatabase db=this.getReadableDatabase();
			Cursor cur=db.rawQuery("Delete from "+callListDetailsTable+" where "+listid+"="+lid+" AND "+con_id+"="+cid,new String [] {});
	}

	public Cursor getlistdetails(int callid2) 
	{
		
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT "+con_id+" from "+callListDetailsTable+" Where "+listid+"="+callid2,new String [] {});
		return cur;		
	}

	public Cursor gettaskname() {
		SQLiteDatabase db=this.getWritableDatabase();
		Cursor cur= db.rawQuery("Select "+colid+","+colname+","+colsdate+","+coltime+" from "+taskTable,new String [] {});
		
		return cur;
	}

	public Cursor gettaskname1(String date) {
		SQLiteDatabase db=this.getWritableDatabase();
		Cursor cur= db.rawQuery("Select "+colid+","+colname+","+coldesp+","+coltime+" from "+taskTable+" Where "+colddate+"='"+date+"'", null);
		
		return cur;
	}
	public void deleteCallListdetails(int lid,int id)
	   {
		SQLiteDatabase db=this.getReadableDatabase();
		db.delete(callListDetailsTable,listid+"="+lid+" AND "+con_id+"="+id , new String []{} );

	  }
	public void deleteGroupdetails(int gid,int id)
	   {
		SQLiteDatabase db=this.getReadableDatabase();
		db.delete(GroupTableDetails,group_id1+"="+gid+" AND "+caller_id+"="+id , new String []{} );

	  }
	public void deletefavorites(int fid,int id)
	   {
		SQLiteDatabase db=this.getReadableDatabase();
		db.delete(favoritesTable,fav_id+"="+fid+" AND "+fav_cid+"="+id , new String []{} );

	  }
	public void deletetaskdate(int id,String date1)
	   {
		SQLiteDatabase db=this.getReadableDatabase();
		db.delete(taskTable,colid+"="+id+" AND "+colsdate+"='"+date1+"'" , new String []{} );

	  }
	public void deleteAllTasks(int id)
	{
		SQLiteDatabase db=this.getReadableDatabase();
		db.delete(taskTable,colid+"="+id, new String []{} );
	
	}
	public Cursor gettaskdetails(int id)
	{
	SQLiteDatabase db=this.getReadableDatabase();
	Cursor cur=db.rawQuery("SELECT "+colname+","+coldesp+","+coltpriority+","+coltime+","+colsdate+","+collocname+" from "+taskTable+" where "+colid+" = "+id,new String [] {});
	return cur;
	}

	public Cursor checktname(String tname, String sdate) {
		SQLiteDatabase db=this.getReadableDatabase();
		 Cursor c=db.rawQuery("SELECT "+colid+" from "+taskTable+" Where "+colname+" ='"+tname+"'and "+colsdate+" = '"+sdate+"'" ,new String [] {});
		 return c;
	}

	
	 void insertlocn(String lname,double lat,double lon)
	 {
		 SQLiteDatabase db= this.getWritableDatabase();
		 ContentValues cv=new ContentValues();
		 cv.put(collocname,lname);
		 cv.put(collat,lat);
		 cv.put(collong, lon);
		 cv.put(collocflag,0);
		 db.insert(locationTable,null,cv);
		
	 }

	public Cursor getlocid(String tlname,double lat,double lon)
	{ 
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT "+collocid+" from "+locationTable+" Where "+collocname+"='"+tlname+
			 "' and "+collat+"="+lat+" and" +collong+"=" +lon,new String [] {});
	 return cur;
	 }
	

Cursor get_CID(String num)
{
	SQLiteDatabase db=this.getReadableDatabase();
	Cursor cur=db.rawQuery("SELECT "+cid1+" from "+eTable+" Where "+mobno+"='"+num+"' OR "+mobnoh+"='"+num+"' OR "+mobnow+"='"+num+"' OR "+mobnoo+"='"+num+"' OR "+mobnocust+"='"+num+"'",new String [] {});
	return cur;
}
public void deletenote(int id,int id1)
{
	SQLiteDatabase db=this.getReadableDatabase();
	db.delete(notesTable,colnid+"="+id+" AND "+cont_id+"="+id1, new String []{} );

}
Cursor getnotedetails(int id)
{
	SQLiteDatabase db=this.getReadableDatabase();
	Cursor cur= db.rawQuery("SELECT "+colnid+","+colnotes+","+coldate+","+coltimes+" FROM "+notesTable+" Where "+cont_id+"="+id,null);
	return cur;
}
public void deletetaskfrom_details(int id,int cid1)
{
	SQLiteDatabase db=this.getReadableDatabase();
	db.delete(taskTable,colid+"="+id+" AND "+colcon+"="+cid1, new String []{} );

}
Cursor validate_calllist(int lid,int cid)
{

	SQLiteDatabase db=this.getReadableDatabase();
	Cursor cur=db.rawQuery("SELECT * FROM "+callListDetailsTable+" Where "+listid+"="+lid+" AND "+con_id+" ="+cid,new String [] {});
	return cur;		 
}
Cursor validate_group(int gid,int cid)
{

	SQLiteDatabase db=this.getReadableDatabase();
	Cursor cur=db.rawQuery("SELECT * FROM "+GroupTableDetails+" Where "+group_id1+"="+gid+" AND "+caller_id+" ="+cid,new String [] {});
	return cur;		 
}
Cursor validate_favourites(int cid)
{

	SQLiteDatabase db=this.getReadableDatabase();
	Cursor cur=db.rawQuery("SELECT * FROM "+favoritesTable+" Where "+fav_cid+" ="+cid,new String [] {});
	return cur;		 
}
void InsertUser(String strfn,String strmn,String strln,String strmob,String strmobh,String strmobw,String strmobo,String strmobcust,String streid,String streidw,String streido,String streidcust,String strimg,String strtag_type,String strtag,int im_id1 )
{
	SQLiteDatabase myDB = this.getWritableDatabase();
	ContentValues cv=new ContentValues();

	cv.put(ufname, strfn);                 
	cv.put(umname, strmn);  
	cv.put(ulname, strln);
	cv.put(umobno, strmob);
	cv.put(umobnoh, strmobh);
	cv.put(umobnow, strmobw);
	cv.put(umobnoo, strmobo);
	cv.put(umobnocust, strmobcust);
	cv.put(uemail, streid);
	cv.put(uemailw, streidw);
	cv.put(uemailo, streido);
	cv.put(uemailcust, streidcust);
	cv.put(uimage,strimg);
	cv.put(utag_type,strtag_type);
	cv.put(utags,strtag);
	cv.put(uim_id,im_id1);
	cv.put(ucolflag,0);
	myDB.insert(userTable,null,cv);
 }
void InsertUserMore(int id,String strwadd,String strorgcw,String strorgco,String strorgcust,String strorgpw,String strorgpo,String strcompadd, String strnname, String strpadd,String strpaw,String strpao,String strpacust, String strbirth, String strani)
{
	SQLiteDatabase myDB = this.getWritableDatabase();
	ContentValues cv=new ContentValues();
	cv.put(umcid1, id);
	cv.put(uwadd, strwadd);
    cv.put(uorgcw, strorgcw);
    cv.put(uorgco, strorgco);
    cv.put(uorgpw, strorgpw);
    cv.put(uorgpo, strorgpo);
    cv.put(uorgcust, strorgcust);
	cv.put(ucompadd,strcompadd);
	cv.put(umnname,strnname);
	cv.put(upadd,strpadd);
	cv.put(upaw, strpaw);
	cv.put(upao,strpao);
	cv.put(upacust, strpacust);
	cv.put(upaw, strpaw);
	cv.put(upao, strpao);
	cv.put(ubirth,strbirth);
	cv.put(uani,strani);
	cv.put(ucolmflag,0);
	myDB.insert(usermoreInfoTable,null,cv);
}
Cursor getUserMoreInfo(int id)
{
	SQLiteDatabase db=this.getReadableDatabase();
	Cursor cur1= db.rawQuery("SELECT * FROM "+usermoreInfoTable+ " where "+umcid1+" ="+id+" ",new String[]{});
	return cur1;

}
Cursor getUserData()
{
	SQLiteDatabase db=this.getReadableDatabase();
	Cursor cur1= db.rawQuery("SELECT "+userid+","+ufname+","+umname+","+ulname+","+umobno+","+umobnoh+","+umobnow+","+umobnoo+","+uemail+","+uemailw+","+uemailo+","+uemailcust+","+uimage+","+utags+" FROM "+userTable,new String[]{});
	return cur1;

}

public Cursor getacc(int aid) {
	SQLiteDatabase db=this.getReadableDatabase();
	Cursor cur= db.rawQuery("SELECT "+ac_email+","+ac_pwd+" FROM "+account+" Where "+acc_id+"="+aid,null);
	return cur;
}
public Cursor getaccData()
{
	SQLiteDatabase db=this.getReadableDatabase();
	Cursor cur= db.rawQuery("SELECT * FROM "+account,null);
	return cur;
}
public Cursor getemailid()
{
	SQLiteDatabase db=this.getReadableDatabase();
	Cursor cur= db.rawQuery("SELECT "+uemail+" FROM "+userTable,null);
	return cur;
}
public Cursor viewAccDetails_settings(int aid)
{
	SQLiteDatabase db=this.getReadableDatabase();
	Cursor cur= db.rawQuery("SELECT "+ac_email+","+ac_pwd+" FROM "+account+" where "+acc_id+" ="+aid,null);
	return cur;
	
}
public Cursor viewAccDetails_settings1()
{
	SQLiteDatabase db=this.getReadableDatabase();
	Cursor cur= db.rawQuery("SELECT "+acc_id_flag+" FROM "+account_flag+" where "+acc_flag+"=1",null);
	return cur;
	
}
public Cursor getTaskid(String tname) {
	
	
	SQLiteDatabase db=this.getReadableDatabase();
	 Cursor c=db.rawQuery("SELECT "+colid+" from "+taskTable+" where "+colname+" = '"+tname+"'",new String [] {});
	 return c;
}
public Cursor viewtasks_date(String date) 
{
	SQLiteDatabase db=this.getWritableDatabase();
	Cursor cur= db.rawQuery("Select "+colid+","+colname+","+coldesp+","+coltime+" from "+taskTable+" Where "+colddate+"='"+date+"'", null);
	
	return cur;
}
public int updateTask(int id, String tname, String tdesp,String ans, String sdate, String date, String time, String tloc)
{

	 SQLiteDatabase db=this.getWritableDatabase();
	 ContentValues cv=new ContentValues();
	 	 cv.put(colname,tname);
		 cv.put(coldesp,tdesp);
		 cv.put(coltpriority,ans);
		 cv.put(colsdate,sdate);
		 cv.put(colddate,date);
		 cv.put(coltime,time);
		 cv.put(collocname,tloc);
		 return db.update(taskTable,cv,colid+"="+id,new String []{});
	
	
}

public int UpdateTask1(int id, String tname,String tdesp,
		int i, String string, int j, String sdate, String date, int k,
		String time, int l)
{

	SQLiteDatabase db= this.getWritableDatabase();
	ContentValues cv=new ContentValues();
	cv.put(colname,tname);
	cv.put(colsdate,sdate);
	cv.put(coltime,time);
	cv.put(coldesp,tdesp);
	cv.put(colddate,date);
	cv.put(coldesp,tdesp);
	cv.put(coltype,1);
	cv.put(coltpriority,"low");
	cv.put(colcat,0);
	cv.put(colsdate,sdate);
	cv.put(colddate,date);
	cv.put(colloc,"");
	cv.put(coltime,time);
	cv.put(colcon,"");
	cv.put(coltflag,0);
	return db.update(taskTable,cv,colid+"="+id, new String []{});
	
}

void Inserttsettingsdefault()
{
	SQLiteDatabase db=this.getReadableDatabase();
	Cursor cur= db.rawQuery("SELECT "+set_taskflag+" FROM "+task_settings,null);
	while(cur.moveToNext())
	{
	if(cur.isNull(0))
	{
	SQLiteDatabase myDB = this.getWritableDatabase();
	ContentValues cv=new ContentValues();
	cv.put(set_taskflag,1);
	myDB.insert(task_settings,null,cv);
	}
	}
	cur.close();
}
void Inserttasksettingsdefault()
{
	SQLiteDatabase db1=this.getReadableDatabase();
	Cursor cur1= db1.rawQuery("SELECT "+set_notiflag+" FROM "+notification_settings,null);
	while(cur1.moveToNext())
	{
	if(cur1.isNull(0))
	{
	SQLiteDatabase myDB1 = this.getWritableDatabase();
	ContentValues cv1=new ContentValues();
	cv1.put(set_notiflag,1);
	myDB1.insert(notification_settings,null,cv1);
	}
	}
	cur1.close();
	Cursor cur2= db1.rawQuery("SELECT "+set_winflag+" FROM "+window_settings,null);
	while(cur2.moveToNext())
	{
	if(cur2.isNull(0))
	{
	SQLiteDatabase myDB2 = this.getWritableDatabase();
	ContentValues cv2=new ContentValues();
	cv2.put(set_winflag,0);
	myDB2.insert(window_settings,null,cv2);
	}
	}
	cur2.close();
	
}
void Insertsyncsettingsdefault()
{
	SQLiteDatabase db=this.getReadableDatabase();
	Cursor cur= db.rawQuery("SELECT "+con_sync+","+call_sync+","+task_sync+","+log_sync+","+sms_sync+" from "+sync_settings,new String [] {});
	while(cur.moveToNext())
	{
	if(cur.isNull(0))
	{
	SQLiteDatabase myDB = this.getWritableDatabase();
	ContentValues cv=new ContentValues();
	 cv.put(con_sync,1);
	 cv.put(call_sync,0);
	 cv.put(task_sync,0);
	 cv.put(log_sync,0);
	 cv.put(sms_sync,0);
	myDB.insert(sync_settings,null,cv);
	}
	}
	cur.close();
}
public int updatensettings()
{
	SQLiteDatabase db= this.getWritableDatabase();
	 ContentValues cv=new ContentValues();
	 cv.put(set_notiflag,1);
	 return db.update(notification_settings, cv,null, new String []{});
	
}

public int updatewinsettings()
{
	SQLiteDatabase db= this.getWritableDatabase();
	 ContentValues cv=new ContentValues();
	 cv.put(set_winflag,1);
	 return db.update(window_settings, cv,null,new String []{});
	
}

public int updatewinsettings_zero() {
	SQLiteDatabase db= this.getWritableDatabase();
	 ContentValues cv=new ContentValues();
	 cv.put(set_winflag,0);
	 return db.update(window_settings, cv,null,new String []{});
	
}

public int updatensettings_zero() 
{
	SQLiteDatabase db= this.getWritableDatabase();
	 ContentValues cv=new ContentValues();
	 cv.put(set_notiflag,0);
	 return db.update(notification_settings, cv,null, new String []{});
	
	
}

public void InsertsmsR(String sender2, String msg, String date,String time,int sid) {
	SQLiteDatabase myDB = this.getWritableDatabase();
	ContentValues cv=new ContentValues();
	cv.put(sender,sender2);
	cv.put(messager,msg);
	cv.put(smsr_Date,date);
	cv.put(smsr_Time,time);
	cv.put(smsr_sid, sid);
	myDB.insert(smsRTable,null,cv);
	
}
public void InsertsmsS(String sender2, String msg, String date,String time,int sid) {
	SQLiteDatabase myDB = this.getWritableDatabase();
	ContentValues cv=new ContentValues();
	cv.put(receiver,sender2);
	cv.put(messages,msg);
	cv.put(smss_Date,date);
	cv.put(smss_Time,time);
	cv.put(smss_sid, sid);
	myDB.insert(smsSTable,null,cv);
	
}

public int updatesyncset(int id) {
	
	SQLiteDatabase db= this.getWritableDatabase();
	 ContentValues cv=new ContentValues();
	 cv.put(con_sync,1);
	 cv.put(call_sync,1);
	 cv.put(task_sync,1);
	 cv.put(log_sync,1);
	 cv.put(sms_sync,1);
	 return db.update(sync_settings, cv,null, new String []{});
}
public int updatesyncset_zero(int id) {
	
	SQLiteDatabase db= this.getWritableDatabase();
	 ContentValues cv=new ContentValues();
	 cv.put(con_sync,0);
	 cv.put(call_sync,0);
	 cv.put(task_sync,0);
	 cv.put(log_sync,0);
	 cv.put(sms_sync,0);
	 return db.update(sync_settings, cv,null, new String []{});
}

public int updatesyncsetcon(int id) 
{
	
	SQLiteDatabase db= this.getWritableDatabase();
	 ContentValues cv=new ContentValues();
	 cv.put(con_sync,1);
	 return db.update(sync_settings, cv,null, new String []{});
}

public int updatesyncsetcall(int id) {
	SQLiteDatabase db= this.getWritableDatabase();
	 ContentValues cv=new ContentValues();
	 cv.put(call_sync,1);
	 return db.update(sync_settings, cv,null, new String []{});
}

public int updatesyncsettask(int id) {
	SQLiteDatabase db= this.getWritableDatabase();
	 ContentValues cv=new ContentValues();
	 cv.put(task_sync,1);
	 return db.update(sync_settings, cv,null, new String []{});
}

public int updatesyncsetlog(int id) {
	SQLiteDatabase db= this.getWritableDatabase();
	 ContentValues cv=new ContentValues();
	 cv.put(log_sync,1);
	 return db.update(sync_settings, cv,null, new String []{});
}

public int updatesyncsetsms(int id) {
	SQLiteDatabase db= this.getWritableDatabase();
	 ContentValues cv=new ContentValues();
	 cv.put(sms_sync,1);
	 return db.update(sync_settings, cv,null, new String []{});
}
public int updatesyncsetcon_zero(int id) {
	SQLiteDatabase db= this.getWritableDatabase();
	 ContentValues cv=new ContentValues();
	 cv.put(con_sync,0);
	 return db.update(sync_settings, cv,null, new String []{});
}

public int updatesyncsetcall_zero(int id) {
	SQLiteDatabase db= this.getWritableDatabase();
	 ContentValues cv=new ContentValues();
	 cv.put(call_sync,0);
	 return db.update(sync_settings, cv,null, new String []{});
}

public int updatesyncsettask_zero(int id) {
	SQLiteDatabase db= this.getWritableDatabase();
	 ContentValues cv=new ContentValues();
	 cv.put(task_sync,0);
	 return db.update(sync_settings, cv,null, new String []{});
}

public int updatesyncsetlog_zero(int id) {
	SQLiteDatabase db= this.getWritableDatabase();
	 ContentValues cv=new ContentValues();
	 cv.put(log_sync,0);
	 return db.update(sync_settings, cv,null, new String []{});
}

public int updatesyncsetsms_zero(int id) {
	SQLiteDatabase db= this.getWritableDatabase();
	 ContentValues cv=new ContentValues();
	 cv.put(sms_sync,0);
	 return db.update(sync_settings, cv,null, new String []{});
}


public Cursor getsyncsettings() 
{
	SQLiteDatabase db=this.getReadableDatabase();
	 Cursor c=db.rawQuery("SELECT "+con_sync+","+call_sync+","+task_sync+","+log_sync+","+sms_sync+" from "+sync_settings,new String [] {});
	 return c;	
}

public Cursor getsyncsettings_id() {
	SQLiteDatabase db=this.getReadableDatabase();
	 Cursor c=db.rawQuery("SELECT "+sync_id+" from "+sync_settings,new String [] {});
	 return c;	
}
public Cursor getinbox_sms_id() 
{
	SQLiteDatabase db=this.getReadableDatabase();
	 Cursor c=db.rawQuery("SELECT "+smsr_id+" from "+smsRTable,new String [] {});
	 return c;	
}
public Cursor getinbox_sms_flag() 
{
	SQLiteDatabase db=this.getReadableDatabase();
	 Cursor c=db.rawQuery("SELECT "+smsr_flag+" from "+smsRFlagTable,new String [] {});
	 return c;	
}
public Cursor getinbox_sms() 
{
	SQLiteDatabase db=this.getReadableDatabase();
	 Cursor c=db.rawQuery("SELECT "+sender+","+messager+","+smsr_Date+","+smsr_Time+","+smsr_id+" from "+smsRTable,new String [] {});
	 return c;
}


public int updateinboxflag(int sms_id) 
{
	SQLiteDatabase db= this.getWritableDatabase();
	 ContentValues cv=new ContentValues();
	 cv.put(smsr_flag,1);
	 return db.update(smsRFlagTable, cv,smsrflag_id+"="+sms_id, new String []{});
	
}
public Cursor getoutbox_sms_id() 
{
	SQLiteDatabase db=this.getReadableDatabase();
	 Cursor c=db.rawQuery("SELECT "+smss_id+" from "+smsSTable,new String [] {});
	 return c;	
}
public Cursor getoutbox_sms_flag() 
{
	SQLiteDatabase db=this.getReadableDatabase();
	 Cursor c=db.rawQuery("SELECT "+smss_flag+" from "+smsSFlagTable,new String [] {});
	 return c;	
}
public Cursor getoutbox_sms() 
{
	SQLiteDatabase db=this.getReadableDatabase();
	 Cursor c=db.rawQuery("SELECT "+receiver+","+messages+","+smss_Date+","+smss_Time+","+smss_id+" from "+smsSTable,new String [] {});
	 return c;
}
public int updateoutboxflag(int sms_id) 
{
	SQLiteDatabase db= this.getWritableDatabase();
	 ContentValues cv=new ContentValues();
	 cv.put(smss_flag,1);
	 return db.update(smsSFlagTable, cv,smssflag_id+"="+sms_id, new String []{});
	
}
public Cursor getnewcontact(int c_id)
{
	
	SQLiteDatabase db=this.getReadableDatabase();
	 Cursor c=db.rawQuery("SELECT "+fname+","+mname+","+lname+","+mobno+","+mobnoh+","+mobnow+","+mobnoo+","+mobnocust+","+email+","+emailw+","+emailo+","+emailcust+","+image+","+tag_type+","+tags+","+date+","+time+","+newconnotes+","+cid1+" from "+eTable+" where "+cid1+"="+c_id,new String [] {});
	 return c;
}
public Cursor getcontactsfav(int id) 
{
	SQLiteDatabase db=this.getReadableDatabase();
	Cursor cur=db.rawQuery("SELECT "+fname+","+lname+","+mobno+","+mobnoh+","+mobnow+","+mobnoo+" from "+eTable+" Where "+cid1+"="+id,new String [] {});
	return cur;		
}
public Cursor getcontactslog(int id) 
{
	SQLiteDatabase db=this.getReadableDatabase();
	Cursor cur=db.rawQuery("SELECT "+fname+","+lname+","+image+" from "+eTable+" Where "+cid1+"="+id,new String [] {});
	return cur;		
}
public int updateflagfav(int fId) {
	SQLiteDatabase db= this.getWritableDatabase();
	 ContentValues cv=new ContentValues();
	 cv.put(fav_flag,1);
	 return db.update(favoritesTable, cv,fav_id+"="+fId, new String []{});
	
}
Cursor getnamefornotes(int id1)
{
	SQLiteDatabase db=this.getReadableDatabase();
	Cursor cur=db.rawQuery("SELECT "+fname+","+lname+" from "+eTable+" Where "+cid1+"="+id1,new String [] {});
	return cur;
}
void Insertnotes(int id,String nname,String num,String note,String date1,String time1)
{	 
	SQLiteDatabase db= this.getWritableDatabase();
	ContentValues cv=new ContentValues();
	cv.put(cont_id,id);
	cv.put(colnname,nname);
	cv.put(colnmob,num);
	cv.put(colnotes,note);
	cv.put(coldate,date1);
	cv.put(coltimes,time1);
	db.insert(notesTable,null,cv);
}
void Insertnotes_from_server(int id,String nname,String num,String note,String date1,String time1,int noteflag1)
{	 
	SQLiteDatabase db= this.getWritableDatabase();
	ContentValues cv=new ContentValues();
	cv.put(cont_id,id);
	cv.put(colnname,nname);
	cv.put(colnmob,num);
	cv.put(colnotes,note);
	cv.put(coldate,date1);
	cv.put(coltimes,time1);
	cv.put(nflag,noteflag1);
	db.insert(notesTable,null,cv);
}
public Cursor getlog_id(int id1) {
	SQLiteDatabase db=this.getReadableDatabase();
	Cursor cur=db.rawQuery("SELECT "+colclgid+" from "+calllogTable+" Where "+colcid+"="+id1,new String [] {});
	return cur;
}
public Cursor get_log_id(String num) {
	SQLiteDatabase db=this.getReadableDatabase();
	Cursor cur=db.rawQuery("SELECT "+colclgid+" from "+calllogTable+" Where "+colnum+"='"+num+"'",new String [] {});
	return cur;
}
public Cursor getinboxsms(int id1) {
	SQLiteDatabase db=this.getReadableDatabase();
	Cursor cur=db.rawQuery("SELECT "+smsr_id+" from "+smsRTable+" Where "+smsr_sid+"="+id1,new String [] {});
	return cur;
}
public Cursor getsentsms(int id2) {
	SQLiteDatabase db=this.getReadableDatabase();
	Cursor cur=db.rawQuery("SELECT "+smss_id+" from "+smsSTable+" Where "+smss_sid+"="+id2,new String [] {});
	return cur;
}
int Inserttsettings(int tid)
{
	SQLiteDatabase db = this.getWritableDatabase();
	ContentValues cv=new ContentValues();
	cv.put(set_taskflag,1);
	return db.update(task_settings, cv,set_tid+"="+tid, new String []{});
}
int Inserttsettings_zero(int tid)
{
	SQLiteDatabase db = this.getWritableDatabase();
	ContentValues cv=new ContentValues();
	cv.put(set_taskflag,0);
	return db.update(task_settings, cv,set_tid+"="+tid, new String []{});
}
int Insertesettings(int id)
{
	SQLiteDatabase db = this.getWritableDatabase();
	ContentValues cv=new ContentValues();
	cv.put(set_emailflag,1);
	return db.update(email_settings, cv,set_eid+"="+id, new String []{});
	
}
int Insertesettings_zero(int eid)
{
	SQLiteDatabase db = this.getWritableDatabase();
	ContentValues cv=new ContentValues();
	cv.put(set_emailflag,0);
	return db.update(email_settings, cv,set_eid+"="+eid, new String []{});
	
}
void Insertesettingsdefault()
{
	SQLiteDatabase db=this.getReadableDatabase();
	Cursor cur= db.rawQuery("SELECT "+set_emailflag+" FROM "+email_settings,null);
	while(cur.moveToNext())
	{
	if(cur.isNull(0))
	{
	SQLiteDatabase myDB = this.getWritableDatabase();
	ContentValues cv=new ContentValues();
	cv.put(set_emailflag,0);
	myDB.insert(email_settings,null,cv);
	}
	}
	cur.close();
}
void Insert_sync_login(String useremail,String userpass)
{	 
	SQLiteDatabase db= this.getWritableDatabase();
	ContentValues cv=new ContentValues();
	cv.put(sync_login_email_id,useremail);
	cv.put(sync_login_password,userpass);
	db.insert(sync_login_Table,null,cv);
}
public Cursor get_sync_login_flag()
{
	SQLiteDatabase db=this.getReadableDatabase();
	Cursor cur=db.rawQuery("Select "+sync_login_flag+" from "+sync_login_Table,new String [] {});
	return cur;
}
public Cursor get_sync_login_id(int flag1)
{
	SQLiteDatabase db=this.getReadableDatabase();
	Cursor cur=db.rawQuery("Select "+sync_login_id+" from "+sync_login_Table+" Where "+sync_login_flag+"="+flag1,new String [] {});
	return cur;
}
public Cursor get_sync_login_details(int id1)
{
	SQLiteDatabase db=this.getReadableDatabase();
	Cursor cur=db.rawQuery("Select "+sync_login_email_id+","+sync_login_password+" from "+sync_login_Table+" Where "+sync_login_id+"="+id1,new String [] {});
	return cur;
}
public Cursor get_sync_login_id_uname(String uname,String pass)
{
	SQLiteDatabase db=this.getReadableDatabase();
	Cursor cur=db.rawQuery("Select "+sync_login_id+" from "+sync_login_Table+" Where "+sync_login_email_id+"='"+uname+"' AND "+sync_login_password+"='"+pass+"'",new String [] {});
	return cur;
}
int Update_sync_login_flag(int tid)
{
	SQLiteDatabase db = this.getWritableDatabase();
	ContentValues cv=new ContentValues();
	cv.put(sync_login_flag,1);
	return db.update(sync_login_Table, cv,sync_login_id+"="+tid, new String []{});
}

public void deleteAllCallLog(int id2) {
	SQLiteDatabase db=this.getReadableDatabase();
	db.delete(calllogTable,colclgid+"="+id2, new String []{} );

}
public Cursor getrecent(String date1) 
{	
	SQLiteDatabase db=this.getReadableDatabase();
	Cursor cur= db.rawQuery("SELECT "+fname+","+lname+","+mobno+","+image+","+cid1+","+date+","+time+","+mobnoh+","+mobnow+","+mobnoo+" FROM "+eTable+" where "+date+">='"+date1+"' ORDER BY "+cid1+" DESC ",new String [] {});
	return cur;	
	}
public Cursor getcommnscore() 
{	
	SQLiteDatabase db=this.getReadableDatabase();
	Cursor cur= db.rawQuery("SELECT "+colclgid+","+colcid+","+cldate+","+cltime+","+cldur+","+colnum+","+logtype+",COUNT("+colnum+")as cnt FROM "+calllogTable+" GROUP BY "+colnum+" ORDER BY cnt DESC LIMIT 10",new String [] {});
	return cur;
	}
public int updatecallflag(int id6, String cid2) 
{
	SQLiteDatabase db= this.getWritableDatabase();
	 ContentValues cv=new ContentValues();
	 cv.put(callflag,1);
	 return db.update(callListDetailsTable, cv,listid+"="+id6+" and "+con_id+"="+cid2, new String []{});
	
}
void InsertCallList(int intid,int intid1)
{
	SQLiteDatabase DB = this.getWritableDatabase();
	ContentValues cv=new ContentValues();
	cv.put(listid, intid);
	cv.put(con_id, intid1);
	cv.put(flag,0);
	cv.put(callflag,0);
	DB.insert(callListDetailsTable,null,cv);

}
void Insertautosync(String uid,String type1)
{	 
	SQLiteDatabase db= this.getWritableDatabase();
	ContentValues cv=new ContentValues();
	cv.put(u_id,uid);
	cv.put(sync_type,type1);
	 cv.put(auto_sync_flag,1);
	db.insert(auto_sync_details,null,cv);
}
public int updateautosync(String uid, int id1) 
{
	SQLiteDatabase db= this.getWritableDatabase();
	 ContentValues cv=new ContentValues();
	 cv.put(auto_sync_flag,1);
	 return db.update(auto_sync_details, cv,u_id+"='"+uid+"' and "+auto_sync_id+"="+id1, new String []{});
	
}
public Cursor getautosyncdata(String uid)
{
	SQLiteDatabase db=this.getReadableDatabase();
	Cursor cur=db.rawQuery("Select "+auto_sync_id+" from "+auto_sync_details+" where "+u_id+"="+uid,new String [] {});
	return cur;
}
public Cursor getuid()
{
	SQLiteDatabase db=this.getReadableDatabase();
	Cursor cur=db.rawQuery("Select "+u_id+" from "+auto_sync_details,new String [] {});
	return cur;
}
public Cursor get_sync_set_con_data(int id) 
{
	SQLiteDatabase db=this.getReadableDatabase();
	 Cursor c=db.rawQuery("SELECT "+con_sync+" from "+sync_settings+" where "+sync_id+"="+id,new String [] {});
	 return c;	
}
public Cursor get_sync_set_call_data(int id1) 
{
	SQLiteDatabase db=this.getReadableDatabase();
	 Cursor c=db.rawQuery("SELECT "+call_sync+" from "+sync_settings+" where "+sync_id+"="+id1,new String [] {});
	 return c;	
}
public Cursor get_sync_set_task_data(int id2) 
{
	SQLiteDatabase db=this.getReadableDatabase();
	 Cursor c=db.rawQuery("SELECT "+task_sync+" from "+sync_settings+" where "+sync_id+"="+id2,new String [] {});
	 return c;	
}
public Cursor get_sync_set_log_data(int id3) 
{
	SQLiteDatabase db=this.getReadableDatabase();
	 Cursor c=db.rawQuery("SELECT "+log_sync+" from "+sync_settings+" where "+sync_id+"="+id3,new String [] {});
	 return c;	
}
public Cursor get_sync_set_sms_data(int id4) 
{
	SQLiteDatabase db=this.getReadableDatabase();
	 Cursor c=db.rawQuery("SELECT "+sms_sync+" from "+sync_settings+" where "+sync_id+"="+id4,new String [] {});
	 return c;	
}
public Cursor get_sync_con_flag() 
{
	SQLiteDatabase db=this.getReadableDatabase();
	 Cursor c=db.rawQuery("SELECT "+con_sync+" from "+sync_settings,new String [] {});
	 return c;	
}
public Cursor get_sync_call_flag() 
{
	SQLiteDatabase db=this.getReadableDatabase();
	 Cursor c=db.rawQuery("SELECT "+call_sync+" from "+sync_settings,new String [] {});
	 return c;	
}
public Cursor get_sync_task_flag() 
{
	SQLiteDatabase db=this.getReadableDatabase();
	 Cursor c=db.rawQuery("SELECT "+task_sync+" from "+sync_settings,new String [] {});
	 return c;	
}
public Cursor get_sync_log_flag() 
{
	SQLiteDatabase db=this.getReadableDatabase();
	 Cursor c=db.rawQuery("SELECT "+log_sync+" from "+sync_settings,new String [] {});
	 return c;	
}
public Cursor get_sync_sms_flag() 
{
	SQLiteDatabase db=this.getReadableDatabase();
	 Cursor c=db.rawQuery("SELECT "+sms_sync+" from "+sync_settings,new String [] {});
	 return c;	
}
void Insert_sync_login_u_id(String useremail1,String userpass1,String u_id1)
{	 
	SQLiteDatabase db= this.getWritableDatabase();
	ContentValues cv=new ContentValues();
	cv.put(sync_login_settings_email_id,useremail1);
	cv.put(sync_login_settings_password,userpass1);
	cv.put(sync_login_settings_u_id,u_id1);
	db.insert(sync_login_settings,null,cv);
}
public Cursor get_sync_login_settings_details()
{
	SQLiteDatabase db=this.getReadableDatabase();
	Cursor cur=db.rawQuery("Select "+sync_login_settings_email_id+","+sync_login_settings_password+","+sync_login_settings_u_id+" from "+sync_login_settings,new String [] {});
	return cur;
}
public Cursor get_sync_login_settings_u_id()
{
	SQLiteDatabase db=this.getReadableDatabase();
	Cursor cur=db.rawQuery("Select "+sync_login_settings_u_id+" from "+sync_login_settings,new String [] {});
	return cur;
}
public Cursor get_con_ids()
{
	SQLiteDatabase db=this.getReadableDatabase();
	Cursor cur=db.rawQuery("SELECT "+cid1+" from "+eTable,new String [] {});

	return cur;
}
public Cursor get_call_logs(String number1) 
{	
	SQLiteDatabase db=this.getReadableDatabase();
	Cursor cur= db.rawQuery("SELECT "+colnum+",COUNT("+colnum+")as cnt FROM "+calllogTable+" where "+colnum+"='"+number1+"' GROUP BY "+colnum,new String [] {});
	return cur;
}
public Cursor get_count_sms_sent(String number1) 
{	
	SQLiteDatabase db=this.getReadableDatabase();
	Cursor cur= db.rawQuery("SELECT "+sender+",COUNT("+sender+")as cnt FROM "+smsRTable+" where "+sender+"='"+number1+"' GROUP BY "+sender,new String [] {});
	return cur;
}
public Cursor get_count_sms_receive(String number1) 
{	
	SQLiteDatabase db=this.getReadableDatabase();
	Cursor cur= db.rawQuery("SELECT "+receiver+",COUNT("+receiver+")as cnt FROM "+smsSTable+" where "+receiver+"='"+number1+"' GROUP BY "+receiver,new String [] {});
	return cur;
}
public Cursor get_email_count(String number1) 
{	
	SQLiteDatabase db=this.getReadableDatabase();
	Cursor cur= db.rawQuery("SELECT "+ecount+" FROM "+EmailLogTable+" where "+enumber+"='"+number1+"'",new String [] {});
	return cur;
}
public Cursor get_sent_sms_count(int contactid) 
{	
	SQLiteDatabase db=this.getReadableDatabase();
	Cursor cur= db.rawQuery("SELECT "+scount+" FROM "+SMSLogTable+" where "+sid+"="+contactid,new String [] {});
	return cur;
}
Cursor get_fname_and_cid(String num)
{
	 System.out.println("Id" +num);
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT "+fname+","+lname+","+cid1+" from "+eTable+" Where "+mobno+"='"+num+"' OR "+mobnoh+"='"+num+"' OR "+mobnow+"='"+num+"' OR "+mobnoo+"='"+num+"'",new String [] {});
		return cur;	

}
Cursor get_comm_Counts(String num)
{
	 System.out.println("Id" +num);
	 
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT(SELECT Count("+colnum+")as cnt from "+calllogTable+"  where "+colnum+" = '"+num+"')" +"+"+ "(SELECT count("+sender+")as cnt1 from "+smsRTable+"  where "+sender+" = '"+num+"')" +"+"+ "(SELECT count("+receiver+") from "+smsSTable+"  where "+receiver+" = '"+num+"')", new String [] {});
		return cur;	

}
public Cursor get_con_ids_and_num()
{
	SQLiteDatabase db=this.getReadableDatabase();
	Cursor cur=db.rawQuery("SELECT "+cid1+","+mobno+","+mobnoh+","+mobnow+","+mobnoo+" from "+eTable,new String [] {});

	return cur;
}
public void Insert_comm_call(String no1,int count1,int conid1)
{
SQLiteDatabase db=this.getWritableDatabase();
ContentValues cv=new ContentValues();
cv.put(com_con_id,conid1);
cv.put(com_num, no1);
cv.put(com_call_count, count1);
db.insert(comm_Table, null, cv);
}
public void Insert_comm_sms(String no1,int count2)
{
	SQLiteDatabase db=this.getWritableDatabase();
	ContentValues cv=new ContentValues();
	cv.put(com_num, no1);
	cv.put(com_sms_count, count2);
	db.insert(comm_Table, null, cv);
}
public void Insert_comm_email(String no1,int count2)
{
	SQLiteDatabase db=this.getWritableDatabase();
	ContentValues cv=new ContentValues();
	cv.put(com_num, no1);
	cv.put(com_email_count, count2);
	db.insert(comm_Table, null, cv);
}
public int update_comm_call(String no2,int count3,int conid2)
{
	SQLiteDatabase db=this.getWritableDatabase();
	ContentValues cv=new ContentValues();
	cv.put(com_call_count, count3);
	return db.update(comm_Table, cv, com_num+"='"+no2+"' and "+com_con_id+"="+conid2, new String[]{});
}
public int update_comm_sms(String no3,int count4)
{
	SQLiteDatabase db=this.getWritableDatabase();
	ContentValues cv=new ContentValues();
	cv.put(com_sms_count, count4);
	return db.update(comm_Table, cv, com_num+"='"+no3+"'",new String[]{});
}
public int update_comm_email(String no3,int count4)
{
	SQLiteDatabase db=this.getWritableDatabase();
	ContentValues cv=new ContentValues();
	cv.put(com_email_count, count4);
	return db.update(comm_Table, cv, com_num+"='"+no3+"'",new String[]{});
}
Cursor get_comm(String no1)
{
SQLiteDatabase db=this.getReadableDatabase();
Cursor cur=db.rawQuery("Select * from "+comm_Table+" where "+com_num+"='"+no1+"'", new String[]{});
return cur;
}
public void Insert_comm_total_count(String no1,long count2)
{
	SQLiteDatabase db=this.getWritableDatabase();
	ContentValues cv=new ContentValues();
	cv.put(com_num, no1);
	cv.put(com_total_count, count2);
	db.insert(comm_Table, null, cv);
}
public int update_comm_total_count(String no2,long count3)
{
	SQLiteDatabase db=this.getWritableDatabase();
	ContentValues cv=new ContentValues();
	cv.put(com_total_count, count3);
	return db.update(comm_Table, cv, com_num+"='"+no2+"'", new String[]{});
}
Cursor get_comm_total_count()
{
SQLiteDatabase db=this.getReadableDatabase();
Cursor cur=db.rawQuery("Select "+com_con_id+","+com_total_count+","+com_num+" from "+comm_Table+" ORDER BY "+com_total_count+" DESC", new String[]{});
return cur;
}
public Cursor getnotes1(String mob)
{
	SQLiteDatabase db=this.getReadableDatabase();
	Cursor cur=db.rawQuery("SELECT "+colnid+" ,"+colnname+","+colnotes+" from "+notesTable+" where "+colnmob+"="+mob ,new String [] {});
	return cur;
}
Cursor chk_inc() 
{
	SQLiteDatabase db=this.getReadableDatabase();
	Cursor cur=db.rawQuery("Select "+in_phn+" from "+in_table, new String[]{});
	return cur;
	
}

public void insert_in(String num)
{
	SQLiteDatabase db=this.getWritableDatabase();
	ContentValues cv=new ContentValues();
	cv.put(in_phn,num);
	db.insert(in_table, null, cv);
	
}

public void delete_in(String num) 
{
	SQLiteDatabase db=this.getReadableDatabase();
	db.delete(in_table,in_phn+"='"+num+"'", new String []{} );
	
}

}
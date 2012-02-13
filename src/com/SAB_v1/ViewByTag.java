//Search result to display contacts by tags
package com.SAB_v1;
import java.util.ArrayList;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;

public class ViewByTag extends Activity implements OnItemClickListener
{	
	
	private ArrayList<String> results = new ArrayList<String>();
	/*private ArrayList<String> results1 = new ArrayList<String>();
	private ArrayList<String> results3 = new ArrayList<String>();
	private ArrayList<String> results2 = new ArrayList<String>();*/
	GridView grid_main;
	public void onCreate(Bundle savedInstanceState)
	{
		requestWindowFeature(Window.FEATURE_NO_TITLE);  
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gridsearch);
		TextView tView = new TextView(this);
		Bundle bundle = getIntent().getExtras(); 
		results  = bundle.getStringArrayList("results");
		  grid_main = (GridView)findViewById(R.id.GridView01);
			grid_main.setAdapter(new ImageAdapter(this));             

		/*tView.setText("Search By Tags!!! ");
		getListView().addHeaderView(tView);
		getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, results));
		getListView().setTextFilterEnabled(true);
		getListView().setOnItemClickListener(this);
		(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, results)).notifyDataSetChanged();*/

	}
	/*public void onItemClick(AdapterView<?> a, View v, int position, long id)  
	{

		System.out.println("Position..."+position);
		String ans= (String) a.getItemAtPosition(position);
		System.out.println("Value is "+ans);
		Intent i= new Intent(this,SearchDetails.class);
		Bundle bun=new Bundle();
		bun.putString("name",ans);
		i.putExtras(bun); 
		startActivity(i);


	}*/
	public class ImageAdapter extends BaseAdapter{
		Context mContext;
		public static final int ACTIVITY_CREATE = 10;
		public ImageAdapter(Context c){
			mContext = c;
		}
		/*Type :Function
		name:getCount
		return type:void
		date:29-6-11
		purpose:To get total number of entries in database for add contacts table*/
		public int getCount() {
			int cnt1=results.size();
			 
			return cnt1;
		} 
		/*Type :Function
		name:checkvalidate
		return type:void
		date:29-6-11
		purpose:To get the image path from phone contact and set that image*/
		public View getView(final int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View v;
		
				LayoutInflater li = getLayoutInflater();
				v = li.inflate(R.layout.icon, null);
				TextView tv = (TextView)v.findViewById(R.id.icon_text);
				tv.setText(""+results.get(position));
				
				System.out.println("name=="+results.get(position));
				tv.setOnClickListener(new TextView.OnClickListener()
				{

					public void onClick(View v) {                           
						int pos=position;                 

						String id=results.get(pos);
						System.out.println("Position... nameeeeeeee"+position+" iddd"+id);
						Intent i= new Intent(ViewByTag.this,ContactDetails.class);
						Bundle bun=new Bundle();
						bun.putString("name2",id);
						i.putExtras(bun);
						startActivity(i);;
					}
                                                
				});
				ImageView iv1 = (ImageView)v.findViewById(R.id.con_image);
				  iv1.setImageResource(R.drawable.propic);
				
				ImageView iv = (ImageView)v.findViewById(R.id.icon_image);
				iv.setImageResource(R.drawable.call1);
				iv.setOnClickListener(new MyOnClickListener(position)); 
				
			
			return v;
		}
		public Object getItem(int arg0) {

			return null;
		}
		public long getItemId(int arg0) {

			return 0;
		}
	
}
	class MyOnClickListener implements OnClickListener  
	{  
		private final int position;  

		public MyOnClickListener(int position)  
		{  
			this.position = position;  

		}                     

		public void onClick(View v)  
		{  

			int pos=position;
			String num=results.get(pos);
			System.out.println("position"+pos+"name=="+num);
			Intent intent = new Intent(Intent.ACTION_CALL);
			intent.setData(Uri.parse("tel:" +num));
			startActivity(intent);


		}               
	}  
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) 
	{
		switch (item.getItemId()) 
		{
		case R.id.maccount:Intent i = new Intent(this, Account.class);
		startActivity(i);
		break;
		case R.id.mallconct:Intent i6 = new Intent(this, AllContacts.class);
		startActivity(i6);
		break;
		case R.id.madd: Intent i1 = new Intent(this, AddContact.class);
		startActivity(i1);
		break;
		
		case R.id.mcall: Intent i4 = new Intent(this, CallList.class);
		startActivity(i4);
		break;
		case R.id.mdelete: Intent i5 = new Intent(this, DeleteContacts.class);
		startActivity(i5);
		break;
		}
		return true;
	}

	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}    
	@Override
	public void onBackPressed()
	{
		Intent i=new Intent(ViewByTag.this,AllContacts.class);
		startActivity(i);
		return;
	}
}




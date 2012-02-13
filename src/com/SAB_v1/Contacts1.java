package com.SAB_v1;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class Contacts1 extends TabActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab);
        Window window = getWindow();
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        /* TabHost will have Tabs */
        TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);
        
        /* TabSpec used to create a new tab. 
         * By using TabSpec only we can able to setContent to the tab.
         * By using TabSpec setIndicator() we can set name to tab. */
        
        /* tid1 is firstTabSpec Id. Its used to access outside. */
        TabSpec firstTabSpec = tabHost.newTabSpec("tid1");
        TabSpec secondTabSpec = tabHost.newTabSpec("tid1");
        TabSpec thirdTabSpec = tabHost.newTabSpec("tid1");
        TabSpec fourTabSpec = tabHost.newTabSpec("tid1");
        TabSpec fiveTabSpec = tabHost.newTabSpec("tid1");
        /* TabSpec setIndicator() is used to set name for the tab. */
        /* TabSpec setContent() is used to set content for a particular tab. */
        firstTabSpec.setIndicator("Contacts",getResources().getDrawable(R.drawable.contact)).setContent(new Intent(this,AllContacts.class));
        secondTabSpec.setIndicator("Groups",getResources().getDrawable(R.drawable.group)).setContent(new Intent(this,ViewGroups.class));
        thirdTabSpec.setIndicator("Favorites",getResources().getDrawable(R.drawable.fav)).setContent(new Intent(this,Favorites.class));
        fourTabSpec.setIndicator("Logs",getResources().getDrawable(R.drawable.log)).setContent(new Intent(this,ViewCallLog.class));
        fiveTabSpec.setIndicator("Keypad",getResources().getDrawable(R.drawable.keypad)).setContent(new Intent(this,Keypads.class));
        /* Add tabSpec to the TabHost to display. */
        tabHost.addTab(firstTabSpec);
        tabHost.addTab(secondTabSpec);
        tabHost.addTab(thirdTabSpec);
        tabHost.addTab(fourTabSpec);
        tabHost.addTab(fiveTabSpec);
    }
}

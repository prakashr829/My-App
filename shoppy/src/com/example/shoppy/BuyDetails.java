package com.example.shoppy;

import com.myapp.tablistadapter.TabPagerAdapter;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

public class BuyDetails extends FragmentActivity implements
ActionBar.TabListener {

private ViewPager viewPager;
private TabPagerAdapter mAdapter;
private ActionBar actionBar;
// Tab titles
private String[] tabs = { "Sign-In","Register","MemberDetail"};

@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.buydetails);

// Initilization
viewPager = (ViewPager) findViewById(R.id.pager);
actionBar = getActionBar();
mAdapter = new TabPagerAdapter(getSupportFragmentManager());

viewPager.setAdapter(mAdapter);
actionBar.setHomeButtonEnabled(false);
actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);       

// Adding Tabs
for (String tab_name : tabs) {
    actionBar.addTab(actionBar.newTab().setText(tab_name)
            .setTabListener(this));
}
}

@Override
public void onTabSelected(Tab tab, FragmentTransaction ft) {
	// TODO Auto-generated method stub
	 viewPager.setCurrentItem(tab.getPosition());
}

@Override
public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	// TODO Auto-generated method stub
	
}

@Override
public void onTabReselected(Tab tab, FragmentTransaction ft) {
	// TODO Auto-generated method stub
	
}
}

package com.example.shoppy;










import java.util.ArrayList;

import com.myapp.menuadapter.NavigationListAdapter;
import com.myapp.navigationAdapter.NavigationView;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;


@SuppressLint("NewApi")
public class MainActivity extends ActionBarActivity {
	ImageView iv_header, iv_header1;

	RelativeLayout rl_footer;

	boolean isBottom = true;
       Button button1;
       private DrawerLayout mDrawerLayout;
   	private ListView mDrawerList;
   	private ActionBarDrawerToggle mDrawerToggle;

   	// nav drawer title
   	private CharSequence mDrawerTitle;

   	// used to store app title
   	private CharSequence mTitle;

   	// slide menu items
   	private String[] navMenuTitles;
   	private TypedArray navMenuIcons;

   	private ArrayList<NavigationView> navDrawerItems;
   	private NavigationListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       rl_footer = (RelativeLayout) findViewById(R.id.rl_footer1);
        iv_header = (ImageView) findViewById(R.id.iv_up_arrow);
        iv_header1= (ImageView) findViewById(R.id.iv_down_arrow);
        iv_header.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (isBottom) {
                	iv_header.setImageResource(R.drawable.downarrow);
               SlideToAbove();
                    isBottom = false;
                } else {
                	iv_header.setImageResource(R.drawable.uparrow);
                   
                  SlideToDown();
                    isBottom = true;
                }

            }
        });
        
        mTitle = mDrawerTitle = getTitle();
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);
        navMenuIcons = getResources()
				.obtainTypedArray(R.array.nav_drawer_icons);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.list_slidermenu);

		navDrawerItems = new ArrayList<NavigationView>();

		// adding nav drawer items to array
				// Home
				navDrawerItems.add(new NavigationView(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
				// Find People
				navDrawerItems.add(new NavigationView(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
				// Photos
				navDrawerItems.add(new NavigationView(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
				// Communities, Will add a counter here
				navDrawerItems.add(new NavigationView(navMenuTitles[3], navMenuIcons.getResourceId(3, -1)));
				// Pages
				navDrawerItems.add(new NavigationView(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));
				// What's hot, We  will add a counter here
				navDrawerItems.add(new NavigationView(navMenuTitles[5], navMenuIcons.getResourceId(5, -1)));
				

				// Recycle the typed array
				navMenuIcons.recycle();
				mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

				// setting the nav drawer list adapter
				adapter = new NavigationListAdapter(getApplicationContext(),
						navDrawerItems);
				mDrawerList.setAdapter(adapter);
				// enabling action bar app icon and behaving it as toggle button
				getActionBar().setDisplayHomeAsUpEnabled(true);
				getActionBar().setHomeButtonEnabled(true);

				mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
						R.drawable.ic_drawer, //nav menu toggle icon
						R.string.app_name, // nav drawer open - description for accessibility
						R.string.app_name // nav drawer close - description for accessibility
				) {
					public void onDrawerClosed(View view) {
						getActionBar().setTitle(mTitle);
						// calling onPrepareOptionsMenu() to show action bar icons
						invalidateOptionsMenu();
					}

					public void onDrawerOpened(View drawerView) {
						getActionBar().setTitle(mDrawerTitle);
						// calling onPrepareOptionsMenu() to hide action bar icons
						invalidateOptionsMenu();
					}
				};
				mDrawerLayout.setDrawerListener(mDrawerToggle);


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
            displayView(0);
            addListenerOnButton();
        }
    }
   

   
    public void FooterAnimation() {
        Animation hide = AnimationUtils.loadAnimation(this, R.anim.anim_up);
        rl_footer.startAnimation(hide);
    }

    public void headerAnimation() {
        Animation hide = AnimationUtils.loadAnimation(this, R.anim.anim_down);
        rl_footer.startAnimation(hide);
    }
  

    private void addListenerOnButton() {
		// TODO Auto-generated method stub
    	button1 = (Button) findViewById(R.id.button1);
    	 
		button1.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
 
				Intent i = new Intent(getApplicationContext(), Products.class);
				startActivity(i);
 
			}
 
		});
 
	}
		
    public void SlideToAbove() {
        Animation slide = null;
        slide = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, -5.0f);

        slide.setDuration(400);
        slide.setFillAfter(true);
        slide.setFillEnabled(true);
        rl_footer.startAnimation(slide);

        slide.setAnimationListener(new AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                rl_footer.clearAnimation();

                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                        rl_footer.getWidth(), rl_footer.getHeight());
                // lp.setMargins(0, 0, 0, 0);
                lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                rl_footer.setLayoutParams(lp);

            }

        });

    }

    public void SlideToDown() {
        Animation slide = null;
        slide = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 5.2f);

        slide.setDuration(400);
        slide.setFillAfter(true);
        slide.setFillEnabled(true);
        rl_footer.startAnimation(slide);

        slide.setAnimationListener(new AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                rl_footer.clearAnimation();

                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                        rl_footer.getWidth(), rl_footer.getHeight());
                lp.setMargins(0, rl_footer.getWidth(), 0, 0);
                lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                rl_footer.setLayoutParams(lp);

            }

        });

    }
   
   	private class SlideMenuClickListener implements
   			ListView.OnItemClickListener {
   		@Override
   		public void onItemClick(AdapterView<?> parent, View view, int position,
   				long id) {
   			// display view for selected nav drawer item
   			displayView(position);
   		}
   	}

   	@Override
   	public boolean onCreateOptionsMenu(Menu menu) {
   		getMenuInflater().inflate(R.menu.main, menu);
   		return true;
   	}

   	@Override
   	public boolean onOptionsItemSelected(MenuItem item) {
   		// toggle nav drawer on selecting action bar app icon/title
   		if (mDrawerToggle.onOptionsItemSelected(item)) {
   			return true;
   		}
   		// Handle action bar actions click
   		switch (item.getItemId()) {
   		case R.id.action_settings:
   			return true;
   		default:
   			return super.onOptionsItemSelected(item);
   		}
   	}

   	/* *
   	 * Called when invalidateOptionsMenu() is triggered
   	 */
   	@Override
   	public boolean onPrepareOptionsMenu(Menu menu) {
   		// if nav drawer is opened, hide the action items
   		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
   		menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
   		return super.onPrepareOptionsMenu(menu);
   	}

   	/**
   	 * Diplaying fragment view for selected nav drawer list item
   	 * */
   	private void displayView(int position) {
   		// update the main content by replacing fragments
   		android.app.Fragment fragment = null;
   		switch (position) {
   		case 0:
   			fragment = new Home();
   			break;
   		case 1:
   			fragment = new Aboutus();
   			break;
   		case 2:
   			fragment = new ProductsDetails();
   			break;
   		case 3:
   			fragment = new Blog();
   			break;
   		case 4:
   			fragment = new CartDetails();
   			break;
   		case 5:
   			fragment = new NewArrival();
   			break;

   		default:
   			break;
   		}

   		if (fragment != null) {
   			FragmentManager fs= getFragmentManager();
   			android.app.FragmentTransaction ft =fs.beginTransaction();
   			
   			
   					ft.replace(R.id.container, fragment).commit();

   			// update selected item and title, then close the drawer
   			mDrawerList.setItemChecked(position, true);
   			mDrawerList.setSelection(position);
   			setTitle(navMenuTitles[position]);
   			mDrawerLayout.closeDrawer(mDrawerList);
   		} else {
   			// error in creating fragment
   			Log.e("MainActivity", "Error in creating fragment");
   		}
   	}

   	@Override
   	public void setTitle(CharSequence title) {
   		mTitle = title;
   		getActionBar().setTitle(mTitle);
   	}

   	/**
   	 * When using the ActionBarDrawerToggle, you must call it during
   	 * onPostCreate() and onConfigurationChanged()...
   	 */

   	@Override
   	protected void onPostCreate(Bundle savedInstanceState) {
   		super.onPostCreate(savedInstanceState);
   		// Sync the toggle state after onRestoreInstanceState has occurred.
   		mDrawerToggle.syncState();
   	}

   	@Override
   	public void onConfigurationChanged(Configuration newConfig) {
   		super.onConfigurationChanged(newConfig);
   		// Pass any configuration change to the drawer toggls
   		mDrawerToggle.onConfigurationChanged(newConfig);
   	}


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

   
    protected void menu0() {
        Intent Main0 = new Intent(MainActivity.this, Home.class);
        startActivity(Main0);
           return;
    }

    protected void menu1() {
        Intent Main1 = new Intent(MainActivity.this, Aboutus.class);
        startActivity(Main1);
           return;
    }

    protected void menu2() {
          Intent Main2 = new Intent(MainActivity.this, ProductsDetails.class);
          startActivity(Main2);
       return;
}
    protected void menu3() {
        Intent Main3 = new Intent(MainActivity.this, CartDetails.class);
        startActivity(Main3);
           return;
    }

    protected void menu4() {
        Intent Main4 = new  Intent(MainActivity.this, Blog.class);
        startActivity(Main4);
        return;
    }

    protected void menu5() {
        Intent Main5 = new  Intent(MainActivity.this, NewArrival.class);
        startActivity(Main5);
        return;
    }

   

}




package com.dquid.baytektestapp;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.dquid.baytektestapp.R;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

public class BayTekActivity extends ActionBarActivity implements OnFragmentInteractionListener, DQBaytekMachine.DQBaytekMachineListenerInterface {

    public enum FragmentType
    {
        USERS, DEVICES, USER_DETAIL, DEVICE_DETAIL
    }

    private SlidingUpPanelLayout mLayout;
    private static String TAG = "BayTek";
    private boolean  _doubleBackToExitPressedOnce    = false;
    private DeviceDetailFragment deviceDetailFragment;
    private UserDetailFragment userDetailFragment;

    static Context context;

    int currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bay_tek);

        BayTekActivity.context = getApplicationContext();

        // Initialize the ViewPager and set an adapter
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(new PagerAdapter(getSupportFragmentManager()));

        // Bind the tabs to the ViewPager
        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabs.setViewPager(pager);

        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {

            @Override
            public void onPageSelected(int arg0)
            {
                currentFragment = arg0;
                if(arg0 == 0) {
                    Toast.makeText(BayTekActivity.this, "UsersList", Toast.LENGTH_SHORT).show();
                } else if (arg0 == 1) {
                    Toast.makeText(BayTekActivity.this, "DevicesList", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2)
            {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int arg0)
            {
                // TODO Auto-generated method stub

            }
        });

        mLayout = (SlidingUpPanelLayout) findViewById(R.id.sliding_layout);
        mLayout.setPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                Log.i(TAG, "onPanelSlide, offset " + slideOffset);
            }

            @Override
            public void onPanelExpanded(View panel) {
                Log.i(TAG, "onPanelExpanded");

            }

            @Override
            public void onPanelCollapsed(View panel) {
                Log.i(TAG, "onPanelCollapsed");

                if(currentFragment == 1) {
                    deviceDetailFragment.getMyMachine().disconnect();
                }

            }

            @Override
            public void onPanelAnchored(View panel) {
                Log.i(TAG, "onPanelAnchored");
            }

            @Override
            public void onPanelHidden(View panel) {
                Log.i(TAG, "onPanelHidden");
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bay_tek, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(FragmentType fragmentType, Object model) {
        if (fragmentType == FragmentType.USERS) {
            UserModel user = (UserModel) model;
            mLayout.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);

            // Create an instance of editorFrag
            userDetailFragment = UserDetailFragment.newInstance(user, this);

            // add fragment to the fragment container layout
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, userDetailFragment).addToBackStack("userDetail").commit();
        } else if (fragmentType == FragmentType.DEVICES) {
            DeviceModel device = (DeviceModel) model;
            mLayout.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);

            // Create an instance of editorFrag
            deviceDetailFragment = DeviceDetailFragment.newInstance(device, this);


            // add fragment to the fragment container layout
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, deviceDetailFragment).addToBackStack("deviceDetail").commit();
        }
    }

    @Override
    public void onDeviceCharge(DeviceModel device) {
//        Toast.makeText(this,
//                "Charge is clicked for " + device.getDeviceName(), Toast.LENGTH_SHORT).show();
        deviceDetailFragment.getMyMachine().addCredits( (byte) 4);

    }

    @Override
    public void onBackPressed() {

        Log.i(TAG, "onBackPressed--");
        if (_doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        this._doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Press again to quit", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                _doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    @Override
    public void onConnection() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(BayTekActivity.this,
                        "Connected", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onConnectionFailed() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(BayTekActivity.this,
                        "Connection failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDisconnection() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(BayTekActivity.this,
                        "Disconnected", Toast.LENGTH_SHORT).show();
                //mLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
            }
        });
    }

    @Override
    public void onDataUpdated(final String name,final  byte value) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(BayTekActivity.this,
                        "Data updated for " + name + " val:" + value, Toast.LENGTH_LONG).show();

//                if(name.equalsIgnoreCase("noOfCredits") == Boolean.TRUE) {
//                    deviceDetailFragment.getMyMachine().disconnect();
//                }
            }
        });
    }

}


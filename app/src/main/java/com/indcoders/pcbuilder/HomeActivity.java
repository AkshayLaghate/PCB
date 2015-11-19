package com.indcoders.pcbuilder;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.indcoders.pcbuilder.Fragments.BuilderFragment;
import com.indcoders.pcbuilder.Fragments.CompListFragment;
import com.indcoders.pcbuilder.Fragments.LoginFragment;
import com.indcoders.pcbuilder.Fragments.NavigationDrawerFragment;
import com.indcoders.pcbuilder.Fragments.PopularFragment;
import com.indcoders.pcbuilder.Fragments.ProfileFragment;
import com.indcoders.pcbuilder.Fragments.RigsListFragment;
import com.indcoders.pcbuilder.Fragments.SignUpFragment;
import com.indcoders.pcbuilder.Utils.DBHelper;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.parse.Parse;
import com.parse.ParseFacebookUtils;

import java.io.IOException;

public class HomeActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks, LoginFragment.OnFragmentInteractionListener, SignUpFragment.OnFragmentInteractionListener, ProfileFragment.OnFragmentInteractionListener
        , PopularFragment.OnFragmentInteractionListener, RigsListFragment.OnFragmentInteractionListener, BuilderFragment.OnFragmentInteractionListener,
        CompListFragment.OnFragmentInteractionListener {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "lopZYTYSuckw8n699haDAEfkc4cefTAPiofMqTdb", "jRGMkFdQBTLSvaSO6Ljf6E23XFecsgIH4sFFSD7m");
        ParseFacebookUtils.initialize(this);


        DBHelper dbHelper = new DBHelper(this);
        try {
            dbHelper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("DBError", e.toString());
        }
        /*mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));*/

        new DrawerBuilder()
                .withActivity(this)
                .withTranslucentStatusBar(false)
                .withActionBarDrawerToggle(false)
                .addDrawerItems(
                        //pass your items here
                        new PrimaryDrawerItem().withName("Home").withIdentifier(1)
                )
                .build();
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = ProfileFragment.newInstance(null, null);
                break;
            case 1:
                fragment = BuilderFragment.newInstance(null, null);
                break;
            case 2:

            case 3:
                fragment = PopularFragment.newInstance(null, null);
                break;
        }
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /*if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.home, menu);
            restoreActionBar();
            return true;
        }*/
        return super.onCreateOptionsMenu(menu);
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
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ParseFacebookUtils.onActivityResult(requestCode, resultCode, data);
    }


}

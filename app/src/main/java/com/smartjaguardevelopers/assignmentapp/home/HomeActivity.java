package com.smartjaguardevelopers.assignmentapp.home;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.smartjaguardevelopers.assignmentapp.MainActivity;
import com.smartjaguardevelopers.assignmentapp.R;
import com.smartjaguardevelopers.assignmentapp.home.contact_us.ContactUsFragment;
import com.smartjaguardevelopers.assignmentapp.home.images.ImageFragment;
import com.smartjaguardevelopers.assignmentapp.home.view_images.ViewImagesFragment;
import com.smartjaguardevelopers.assignmentapp.user.UserLogInSharedPreferences;

public class HomeActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle actionBarDrawerToggleMainMenu;

    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        navigationView = (NavigationView)findViewById(R.id.nav_view);

        tabLayout = (TabLayout)findViewById(R.id.tabLayout);
        viewPager = (ViewPager)findViewById(R.id.viewPager);

        actionBarDrawerToggleMainMenu = new ActionBarDrawerToggle(this,
                drawerLayout, null, R.string.open_navigation_drawer, R.string.close_navigation_drawer);

        drawerLayout.setDrawerListener(actionBarDrawerToggleMainMenu);

        actionBarDrawerToggleMainMenu.syncState();



        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                if(item.getItemId() == R.id.menuLogout)
                {
                    UserLogInSharedPreferences userLogInSharedPreferences = new UserLogInSharedPreferences(HomeActivity.this);
                    userLogInSharedPreferences.saveCurrentUserLoginDetails("null");
                    Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
                if (drawerLayout.isDrawerOpen(GravityCompat.START))
                {
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                return true;
            }
        });

        View headerView = navigationView.getHeaderView(0);


        TextView tvUserEmail;
        tvUserEmail = (TextView)headerView.findViewById(R.id.tvUserEmail);
        tvUserEmail.setText(""+new UserLogInSharedPreferences(this).getCurrentUserEmail());


        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);

    }


    class ViewPagerAdapter extends FragmentStatePagerAdapter
    {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment=null;
            if(position==0)
            {
                fragment= new ContactUsFragment();
            }
            else if(position==1)
            {
                fragment= new ImageFragment();
            }
            else if(position==2)
            {
                fragment= new ViewImagesFragment();
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            String title="";
            if(position==0)
            {
                title="Contact Us";
            }
            else if(position==1)
            {
                title="Images";
            }
            else if(position==2)
            {
                title="View Images";
            }
            return title;
        }
    }
}

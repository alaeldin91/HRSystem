package com.example.finityloops1.hrsystem;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.finityloops1.hrsystem.Adapter.Tab_Adapter;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.finityloops1.hrsystem.R.string.navigation_drawer_open;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ViewPager viewPager;
    private DrawerLayout mDrawerLayout;
    private TextView username;
    private TextView telephone;
    private TextView email;
    private TextView job;
    private CircleImageView img;
    private boolean image_flag = true;
    private Drawable old_value;
    private ProgressBar upload_loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        TabLayout tab_layout=(TabLayout)findViewById(R.id.tab_layout);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);


        tab_layout.addTab(tab_layout.newTab().setText(getString(R.string.Avacationrequest)));
        tab_layout.addTab(tab_layout.newTab().setText(getString(R.string.Submitresignation)));
        tab_layout.setTabGravity(TabLayout.GRAVITY_FILL);
        viewPager=(ViewPager)findViewById(R.id.view_pager);
    final PagerAdapter adapter= new Tab_Adapter(getSupportFragmentManager(),tab_layout.getTabCount());
    viewPager.setAdapter(adapter);
     viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab_layout));
tab_layout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
});

mDrawerLayout=(DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle =new ActionBarDrawerToggle(this,mDrawerLayout,toolbar,navigation_drawer_open,R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView=(NavigationView)findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        img=navigationView.getHeaderView(0).findViewById(R.id.client_image);
        Glide.with(Home.this)
                .load(MainActivity.decodedByte)
                .apply(new RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true))
                .into(img);
        username= navigationView.getHeaderView(0).findViewById(R.id.name_employee);
        String firstName= MainActivity.FirstName;
        String secondname=MainActivity.SecondName;
        String thirdName=MainActivity.ThirdName;
        String fourName=MainActivity.FourName;
        String phone=MainActivity.WorkPhone;
        String workEamil=MainActivity.WorkEmail;
        telephone=navigationView.getHeaderView(0).findViewById(R.id.telephone);
        email=navigationView.getHeaderView(0).findViewById(R.id.emails);
    username.setText(getString(R.string.name_employee)+" "+firstName+" "+secondname+" "+thirdName+" "+fourName);
    telephone.setText(getString(R.string.telephone)+""+phone);
    email.setText(getString(R.string.email)+""+workEamil);

    }



    public void setTab(int index) {
        if (index != -1)
            viewPager.setCurrentItem(index);
    }
    @Override
    protected void onStart() {
        super.onStart();
        setTab(getIntent().getIntExtra("index", -1));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id= item.getItemId();
        switch (id){
            case R.id.holidays:
                startActivity(new Intent(Home.this, show_holidays.class));
                break;
            case R.id.Resignation:
                startActivity(new Intent(Home.this, show_Resignation.class));
                break;
            case R.id.change_password:
                startActivity(new Intent(Home.this, change_password.class));
                break;
            case R.id.about_exchange:
                startActivity(new Intent(Home.this, developer.class));
                break;
            case R.id.logout:
                startActivity(new Intent(Home.this, MainActivity.class));
                break;
                default:
                    break;

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

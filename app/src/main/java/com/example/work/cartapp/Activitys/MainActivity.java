package com.example.work.cartapp.Activitys;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.work.cartapp.Extras.ExternLib.PassEvent;
import com.example.work.cartapp.Fragments.FirstFragment;
import com.example.work.cartapp.Fragments.ItemListFragment;
import com.example.work.cartapp.Fragments.OrderListFragemnt;

import com.example.work.cartapp.R;
import com.example.work.cartapp.Extras.SessionManager.SessionManager;
import com.example.work.cartapp.Extras.dummy.DummyContent;

import com.squareup.otto.Subscribe;



import io.realm.Realm;

public class MainActivity extends AppCompatActivity implements ItemListFragment.OnListFragmentInteractionListener, OrderListFragemnt.OnListFragmentInteractionListener {
    SessionManager sessionManager;
    private TextView mTextMessage;
    private TabLayout mTabLayout;
    private int[] mTabsIcons = {R.drawable.add_square_button,
            R.drawable.hierarchical_structure,
            R.drawable.numbered_items_button

    };
    private String[] tabName = {
            "Take Order", "Orders", "Stock"
    };
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };


    @Subscribe
    public void RefresListEvent(PassEvent event) {
        Toast.makeText(this, "new" + event.getDataa(), Toast.LENGTH_SHORT).show();
    }

    Realm realm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         realm = Realm.getDefaultInstance();


        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CAMERA}, 1);
        } else {
            Log.e("DB", "PERMISSION GRANTED");

        }
        sessionManager = new SessionManager(getApplicationContext());
        Toolbar toolbarTop = (Toolbar) findViewById(R.id.toolbar_top);
        TextView mTitle = (TextView) toolbarTop.findViewById(R.id.toolbar_title);
        mTitle.setText(sessionManager.getCompany());
        ImageView ImgLogOut = (ImageView) toolbarTop.findViewById(R.id.ImgLogOut);
        ImgLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sessionManager.ClearAll();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        MyPagerAdapter pagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        if (viewPager != null)
            viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(2);
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        if (mTabLayout != null) {
            mTabLayout.setupWithViewPager(viewPager);

            for (int i = 0; i < mTabLayout.getTabCount(); i++) {
                TabLayout.Tab tab = mTabLayout.getTabAt(i);
                if (tab != null)
                    tab.setCustomView(pagerAdapter.getTabView(i));
            }

            mTabLayout.getTabAt(0).getCustomView().setSelected(true);
        }




    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }

    @Override
    public void onListFragmentInteraction(String positon) {
        Toast.makeText(this, "" + positon, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, DetailsOfOrder.class);
        intent.putExtra("ids", positon);
        startActivity(intent);
    }









    private class MyPagerAdapter extends FragmentPagerAdapter {

        public final int PAGE_COUNT = 3;

        private final String[] mTabsTitle = {"Take Order", "Orders", "Stock"};

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public View getTabView(int position) {
            // Given you have a custom layout in `res/layout/custom_tab.xml` with a TextView and ImageView
            View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.custom_tab, null);
            TextView title = (TextView) view.findViewById(R.id.title);
            title.setText(mTabsTitle[position]);
            ImageView icon = (ImageView) view.findViewById(R.id.icon);
            icon.setImageResource(mTabsIcons[position]);
            return view;
        }

        @Override
        public Fragment getItem(int pos) {

            switch (pos) {
                case 0:
                    return FirstFragment.newInstance();
                case 1:
                    return OrderListFragemnt.newInstance();
                case 2:
                    return ItemListFragment.newInstance();
            }

            return null;
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTabsTitle[position];
        }
    }
}

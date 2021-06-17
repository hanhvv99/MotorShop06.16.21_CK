package com.example.motorshop.activity.warranty.act;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.motorshop.activity.R;
import com.example.motorshop.activity.warranty.adapter.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class ScheduleMaintenanceActivity extends AppCompatActivity {
    private TabLayout tabDots;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_maintenance);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getControl();
        getEvent();
    }

    private void getControl() {
        tabDots = (TabLayout) findViewById(R.id.tabDots);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
    }

    private void getEvent() {

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewPagerAdapter);

        tabDots.setupWithViewPager(viewPager);

    }
}
package com.example.motorshop.activity.warranty.adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.motorshop.activity.warranty.fragment.CaseFragment;
import com.example.motorshop.activity.warranty.fragment.ChooseSPFragment;
import com.example.motorshop.activity.warranty.fragment.ChooseTimeFragment;
import com.example.motorshop.activity.warranty.fragment.ContentFragment;
import com.example.motorshop.activity.warranty.fragment.RightsOfCustomersFragment;
import com.example.motorshop.activity.warranty.fragment.ValidityFragment;


public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new ChooseSPFragment();
            case 1:
                return new ChooseTimeFragment();
            case 2:
                return new CaseFragment();
            case 3:
                return new RightsOfCustomersFragment();
            case 4:
                return new ValidityFragment();
            default:
                return new ContentFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0:
                return "Chọn xe";
            case 1:
                return "Chọn thời gian";
            case 2:
                return "Chọn dịch vụ";
            case 3:
                return "Xem lại";
            default:
                return "Chọn xe";
        }
    }
}

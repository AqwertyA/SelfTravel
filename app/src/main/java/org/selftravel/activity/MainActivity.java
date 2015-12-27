package org.selftravel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.selftravel.R;
import org.selftravel.beans.HomeBeans;
import org.selftravel.fragment.BaseFragment;
import org.selftravel.fragment.FragmentFactory;
import org.selftravel.fragment.MineFragment;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity implements ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener {
    public static final int REQUEST_LOGIN = 0x00000001;
    @ViewInject(R.id.vp_main)
    private ViewPager viewPager;
    @ViewInject(R.id.tab_container)
    private RadioGroup barContainer;
    @ViewInject(R.id.rb_home)
    private RadioButton homeRb;
    @ViewInject(R.id.rb_destination)
    private RadioButton destinationRb;
    @ViewInject(R.id.rb_find)
    private RadioButton findRb;
    @ViewInject(R.id.rb_search)
    private RadioButton searchRb;
    @ViewInject(R.id.rb_mine)
    private RadioButton mineRb;

    public static List<HomeBeans.Datas> homeData = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        setListener();
        initData();
    }

    private void initData() {
        viewPager.setAdapter(adapter);
    }

    private void setListener() {
        viewPager.addOnPageChangeListener(this);
        barContainer.setOnCheckedChangeListener(this);
    }

    private void initView() {
        x.view().inject(this);
        viewPager.setOffscreenPageLimit(4);
    }

    private FragmentStatePagerAdapter adapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {
        @Override
        public Fragment getItem(int position) {
            return FragmentFactory.createFragment(position);
        }

        @Override
        public int getCount() {
            return 5;
        }
    };

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                homeRb.setChecked(true);
                break;
            case 1:
                destinationRb.setChecked(true);
                break;
            case 2:
                findRb.setChecked(true);
                break;
            case 3:
                searchRb.setChecked(true);
                break;
            case 4:
                mineRb.setChecked(true);
                break;
            default:
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_home:
                viewPager.setCurrentItem(0);
                break;
            case R.id.rb_destination:
                viewPager.setCurrentItem(1);
                break;
            case R.id.rb_find:
                viewPager.setCurrentItem(2);
                break;
            case R.id.rb_search:
                viewPager.setCurrentItem(3);
                break;
            case R.id.rb_mine:
                viewPager.setCurrentItem(4);
                break;
            default:
                break;
        }
    }
}

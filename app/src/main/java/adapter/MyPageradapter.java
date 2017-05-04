package adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Author ${韩磊鑫} on 2017/2/13 09:57
 * 邮箱：leixinhan@foxmail.com
 * 项目名称：
 * 类描述：
 * 修改人：${Oliver}
 * 修改备注：
 * 修改时间：
 */

public class MyPageradapter extends FragmentStatePagerAdapter {
    private List<Fragment> list=new ArrayList<>();
    private String[] titles;

    public MyPageradapter(FragmentManager fm, List<Fragment> list, String[] titles) {
        super(fm);
        this.list = list;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return titles[position];
    }
}

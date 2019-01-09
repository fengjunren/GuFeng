package cn.explo.gufeng.ui.adapter;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.util.SparseArray;
import android.view.ViewGroup;
import cn.explo.gufeng.R;
import cn.explo.gufeng.base.BaseNestFragment;
import cn.explo.gufeng.ui.fragment.*;


/**
 * Created by shahabuddin on 6/6/14.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    private final Resources resources;

    SparseArray<Fragment> registeredFragments = new SparseArray<Fragment>();

    /**
     * Create pager adapter
     *
     * @param resources
     * @param fm
     */
    public ViewPagerAdapter(final Resources resources, FragmentManager fm) {
        super(fm);
        this.resources = resources;
    }

    @Override
    public Fragment getItem(int position) {
        final Fragment result= new NestContainFragment();
        Log.i(tag,tag+" getItem position:"+position+" ----------------");
        Bundle b=new Bundle();
        String setupFragmentCls="";
        switch (position) {
            case 0:
                // First Fragment of First Tab
                setupFragmentCls=A1Fragment.class.getName();
                break;
            case 1:
                // First Fragment of Second Tab
                setupFragmentCls=B1Fragment.class.getName();
                break;
            case 2:
                // First Fragment of Third Tab
                setupFragmentCls=C1Fragment.class.getName();
                break;
            case 3:
                // First Fragment of Third Tab
                setupFragmentCls=D1Fragment.class.getName();
                break;
        }
        b.putString("setupFragment",setupFragmentCls);
        result.setArguments(b);
        return result;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(final int position) {
        switch (position) {
            case 0:
                return resources.getString(R.string.page_1);
            case 1:
                return resources.getString(R.string.page_2);
            case 2:
                return resources.getString(R.string.page_3);
            case 3:
                return resources.getString(R.string.page_4);
            default:
                return null;
        }
    }
String tag="ViewPagerAdapter";
    /**
     * On each Fragment instantiation we are saving the reference of that Fragment in a Map
     * It will help us to retrieve the Fragment by position
     *
     * @param container
     * @param position
     * @return
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        registeredFragments.put(position, fragment);
        Log.i(tag,tag+" instantiateItem---------:"+fragment.getClass().getName()+",position:"+position);
        return fragment;
    }

    /**
     * Remove the saved reference from our Map on the Fragment destroy
     *
     * @param container
     * @param position
     * @param object
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        registeredFragments.remove(position);
        Log.i(tag,tag+" destroyItem---------:"+object.getClass().getName()+",position:"+position);
        super.destroyItem(container, position, object);
    }


    /**
     * Get the Fragment by position
     *
     * @param position tab position of the fragment
     * @return
     */
    public Fragment getRegisteredFragment(int position) {
        return registeredFragments.get(position);
    }
}

package cn.explo.gufeng.base

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentManager.POP_BACK_STACK_INCLUSIVE
import cn.explo.gufeng.R
import cn.explo.gufeng.interfs.OnBackPressListener
import cn.explo.gufeng.interfs.imp.BackPressImpl

open class BaseNestFragment : BaseFragment() {

    internal var attachChildFragment: Fragment? = null

    override fun onAttachFragment(childFragment: Fragment?) {
        super.onAttachFragment(childFragment)
        attachChildFragment = childFragment
    }

    override fun getFragmentContainId(): Int {
        return R.id.nestContainFragment
    }

    override fun getFm(): FragmentManager? {
        return childFragmentManager
    }


}
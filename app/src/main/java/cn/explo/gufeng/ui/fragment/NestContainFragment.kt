package cn.explo.gufeng.ui.fragment

import android.support.v4.app.Fragment
import cn.explo.gufeng.R
import cn.explo.gufeng.base.BaseFragment
import cn.explo.gufeng.base.BaseNestFragment
import cn.explo.gufeng.interfs.OnBackPressListener

open class NestContainFragment : BaseNestFragment(), OnBackPressListener {

    lateinit var firstFrag:Fragment
    override fun getLayoutId(): Int {
        return R.layout.nest_contain_frag
    }


    override fun bindData() {
        super.bindData()
        attachChildFragment ?: also {
            val cls = arguments?.getString("setupFragment", "")
            firstFrag = Fragment.instantiate(it.activity, cls)
            showNextFragment(null, firstFrag, false)
        }
    }

    override fun onVisible() {
        super.onVisible()
        val fs = childFragmentManager.fragments
        if (fs.size > 0) {
            val lastFrag = fs.last() as BaseFragment
            lastFrag.setTitleAndBackBtn()
        }
    }

    override fun onBackPressed(): Boolean {
        var childCount = childFragmentManager?.backStackEntryCount
        return if (childCount == 0) false
        else {
            childFragmentManager.popBackStackImmediate()
            val frags = childFragmentManager.fragments
            if (frags.size > 0) {
                childFragmentManager.beginTransaction().show(frags.last()).commitAllowingStateLoss()
            }
            true
        }
    }

}
package cn.explo.gufeng.ui.activity

import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.widget.ProgressBar
import cn.explo.gufeng.R
import cn.explo.gufeng.base.BaseActivity
import cn.explo.gufeng.base.BaseFragment
import cn.explo.gufeng.databinding.MainActBinding
import cn.explo.gufeng.interfs.OnBackPressListener
import cn.explo.gufeng.tools.hawk.Hawk
import cn.explo.gufeng.ui.fragment.MainFrag
import cn.explo.gufeng.ui.fragment.SplashFrag
import cn.explo.gufeng.utils.Check
import cn.explo.gufeng.utils.PackageUtil
import com.orhanobut.logger.Logger
import com.yanzhenjie.permission.AndPermission
import com.yanzhenjie.permission.Permission


class MainAct : BaseActivity() {

//    lateinit var mainFrag: MainFrag
    lateinit var dBinding:MainActBinding
    override fun getLayoutId(): Int {
        return R.layout.main_act
    }

    override fun getFragmentContainId(): Int {
        return R.id.container
    }

    override fun initVariable(dataBinding: ViewDataBinding) {
        super.initVariable(dataBinding)
        dBinding=dataBinding as MainActBinding
    }


    override fun bindData(savedInstanceState: Bundle?) {
        super.bindData(savedInstanceState)
        setSupportActionBar(dBinding.myToolbar)
        var isFirstOpen=Check.checkIsFirstOpen(this)
        if(isFirstOpen){
            showNextFragment(null, SplashFrag(), false)
            Hawk.put("is_First_open",false)
        }else {
            var mainFrag = MainFrag.newInstance()
            if (savedInstanceState == null) {
                showNextFragment(null, mainFrag, false)
            }
        }


    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        var frags = supportFragmentManager.fragments
        val size=frags.size
        if (size > 0) {
            val lastFrag = frags.last()
            if (lastFrag is OnBackPressListener) {
                if (!lastFrag.onBackPressed()) {
                    super.onBackPressed()
                }
            } else {
                supportFragmentManager.popBackStackImmediate()
                frags = supportFragmentManager.fragments
                if (frags.size > 0) {
                    val lastFrag=frags.last() as BaseFragment
                    supportFragmentManager.beginTransaction().show(lastFrag).commitAllowingStateLoss()
                }
            }
        }else super.onBackPressed()

    }

}

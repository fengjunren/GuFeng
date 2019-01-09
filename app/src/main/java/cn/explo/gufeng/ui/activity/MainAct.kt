package cn.explo.gufeng.ui.activity

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.Toolbar
import cn.explo.gufeng.R
import cn.explo.gufeng.base.BaseActivity
import cn.explo.gufeng.ui.fragment.MainFrag
import cn.explo.gufeng.utils.ActivityDataBus
import cn.explo.gufeng.vm.SharedVM
import com.orhanobut.logger.Logger
import com.yanzhenjie.permission.AndPermission
import com.yanzhenjie.permission.Permission


class MainAct : BaseActivity() {

    lateinit var mainFrag: MainFrag
    override fun getLayoutId(): Int {
        return R.layout.main_act
    }

    override fun getFragmentContainId(): Int {
        return R.id.container
    }

    override fun bindData(savedInstanceState: Bundle?) {
        super.bindData(savedInstanceState)
        if (savedInstanceState == null) {
            mainFrag = MainFrag.newInstance()
            showNextFragment(null, mainFrag, false)
        } else {
            mainFrag = supportFragmentManager.fragments[0] as MainFrag
        }
        val toolbar: Toolbar = findViewById(R.id.my_toolbar)
        setSupportActionBar(toolbar)

        AndPermission.with(this)
            .runtime()
            .permission(Permission.Group.STORAGE)
            .onGranted({ permissions ->
                // Storage permission are allowed.
                Logger.i("granted:" + permissions)
            })
            .onDenied({ permissions ->
                // Storage permission are not allowed.
                Logger.i("onDenied:" + permissions)
            })
            .start()

        ActivityDataBus.getData(this, SharedVM::class.java).getTitle().observe(this, Observer {
            supportActionBar?.setTitle(it)
        })

        ActivityDataBus.getData(this, SharedVM::class.java).getIsDisplayBackBtn().observe(this, Observer {
            supportActionBar?.setDisplayHomeAsUpEnabled(it!!)
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        val frags = supportFragmentManager.fragments
        if (frags.size > 0) {
            val lastFrag = frags.last()
            if (lastFrag is MainFrag) {
                // container Fragment or its associates couldn't handle the back pressed task
                // delegating the task to super class
                if (!mainFrag.onBackPressed()) {
                    super.onBackPressed()
                }
            } else {
                supportFragmentManager.popBackStackImmediate()
                val frags = supportFragmentManager.fragments
                if (frags.size > 0) {
                    supportFragmentManager.beginTransaction().show(frags.last()).commitAllowingStateLoss()
                }
            }
        }

//        if (!mainFrag.onBackPressed()) {
//            super.onBackPressed()
//        }
    }

}

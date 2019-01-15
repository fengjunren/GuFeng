package cn.explo.gufeng.ui.fragment

import android.databinding.ViewDataBinding
import android.os.Bundle
import cn.explo.gufeng.R
import cn.explo.gufeng.base.BaseFragment
import cn.explo.gufeng.interfs.OnBackPressListener
import cn.explo.gufeng.ui.adapter.MainAdapter
import cn.explo.gufeng.vm.MainVM
import cn.explo.gufeng.databinding.MainFragBinding
import com.orhanobut.logger.Logger
import com.yanzhenjie.permission.AndPermission
import com.yanzhenjie.permission.Permission

class MainFrag : BaseFragment(),OnBackPressListener {

     lateinit var  mv: MainVM
     lateinit var  dBinding : MainFragBinding

    lateinit var adapter: MainAdapter

    companion object {
        fun newInstance() = MainFrag()
    }

    override fun getLayoutId(): Int {
        return R.layout.main_frag
    }
    override fun initVariable(dataBinding: ViewDataBinding) {
        super.initVariable(dataBinding)
        mv= getViewModel()
        dBinding= dataBinding as MainFragBinding

    }


    override fun bindData(savedInstanceState: Bundle?) {
        super.bindData(savedInstanceState)
        adapter= MainAdapter(resources, childFragmentManager)
        dBinding.vp.adapter=adapter
        dBinding.tbl.setupWithViewPager(dBinding.vp)

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
    }


    /**
     * Retrieve the currently visible Tab Fragment and propagate the onBackPressed callback
     *
     * @return true = if this fragment and/or one of its associates Fragment can handle the backPress
     */
    override fun onBackPressed(): Boolean {
        // currently visible tab Fragment
        val currentFragment = adapter.getRegisteredFragment(dBinding.vp.currentItem) as OnBackPressListener

        return if (currentFragment != null) {
            // lets see if the currentFragment or any of its childFragment can handle onBackPressed
            currentFragment?.onBackPressed()
        } else false
    }
}

package cn.explo.gufeng.ui.fragment

import android.arch.lifecycle.Observer
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import cn.explo.gufeng.R
import cn.explo.gufeng.base.BaseDetailContainFragment
import cn.explo.gufeng.databinding.DengmiContainFragBinding
import cn.explo.gufeng.global.App
import cn.explo.gufeng.ui.adapter.DengmiDetailAdapter
import cn.explo.gufeng.vm.DengMiVM


/**
 * A simple [Fragment] subclass.
 *
 */
class DengmiContainFrag : BaseDetailContainFragment() {
    lateinit var  dBinding: DengmiContainFragBinding
    lateinit var adapter:DengmiDetailAdapter
    private val vm:DengMiVM= DengMiVM(App.instance)

    override fun getLayoutId(): Int {
        return R.layout.dengmi_contain_frag
    }

    override fun initVariable(dataBinding: ViewDataBinding) {
        super.initVariable(dataBinding)
        dBinding=dataBinding as DengmiContainFragBinding
    }

    override fun initListener() {
        super.initListener()
        dBinding.vp.addOnPageChangeListener(mOnPageChangeListener)
    }

    override fun bindData(savedInstanceState: Bundle?) {
        super.bindData(savedInstanceState)

        vm.getCount().observe(this, Observer {
            adapter= DengmiDetailAdapter(getFm(),it!!)
            dBinding.vp.adapter=adapter
            dBinding.vp.setCurrentItem(mPosition!!,false)
            adapter.notifyDataSetChanged()
        })

    }
}

package cn.explo.gufeng.ui.fragment

import android.arch.lifecycle.Observer
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import cn.explo.gufeng.R
import cn.explo.gufeng.base.BaseDetailContainFragment
import cn.explo.gufeng.databinding.XiehouyuContainFragBinding
import cn.explo.gufeng.global.App
import cn.explo.gufeng.ui.adapter.XiehouyuDetailAdapter
import cn.explo.gufeng.vm.DengMiVM


/**
 * A simple [Fragment] subclass.
 *
 */
class XiehouyuContainFrag : BaseDetailContainFragment() {
    lateinit var dBinding: XiehouyuContainFragBinding
    lateinit var adapter: XiehouyuDetailAdapter
    private val vm: DengMiVM = DengMiVM(App.instance)
    override fun getLayoutId(): Int {
        return R.layout.xiehouyu_contain_frag
    }

    override fun initVariable(dataBinding: ViewDataBinding) {
        super.initVariable(dataBinding)
        dBinding = dataBinding as XiehouyuContainFragBinding
    }

    override fun initListener() {
        super.initListener()
        dBinding.vp.addOnPageChangeListener(mOnPageChangeListener)
    }

    override fun bindData(savedInstanceState: Bundle?) {
        super.bindData(savedInstanceState)
        vm.getCount().observe(this, Observer {
            adapter = XiehouyuDetailAdapter(getFm(), it!!)
            dBinding.vp.adapter = adapter
            dBinding.vp.setCurrentItem(mPosition!!, false)
            adapter.notifyDataSetChanged()
        })

    }
}

package cn.explo.gufeng.ui.fragment


import android.arch.lifecycle.Observer
import android.databinding.ViewDataBinding
import android.support.v4.app.Fragment
import android.view.View
import cn.explo.gufeng.R
import cn.explo.gufeng.base.BaseNestFragment
import cn.explo.gufeng.databinding.GushiciDetailFragBinding
import cn.explo.gufeng.enum.TriggerAction
import cn.explo.gufeng.vm.GushiciVM


/**
 * A simple [Fragment] subclass.
 *
 */
class GushiciDetailFrag : BaseNestFragment() {
    private lateinit var  dBinding : GushiciDetailFragBinding
    private lateinit var vm: GushiciVM

    override fun initVariable(dataBinding: ViewDataBinding) {
        super.initVariable(dataBinding)
        dBinding= dataBinding as GushiciDetailFragBinding
        vm=getViewModel()
        is_display_back_btn=true
    }

    override fun getLayoutId(): Int {
        return R.layout.gushici_detail_frag
    }

    override fun onVisible(triggerAction: TriggerAction) {
        val id=arguments?.getInt("id")
        vm.getById(id!!).observe(this, Observer {
            title=it?.author!!
            dBinding.item=it
            setTitleAndBackBtn()
        })
    }

}

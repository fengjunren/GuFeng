package cn.explo.gufeng.ui.fragment

import android.arch.lifecycle.Observer
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import cn.explo.gufeng.R
import cn.explo.gufeng.base.BaseNestFragment
import cn.explo.gufeng.databinding.DengmiDetailFragBinding
import cn.explo.gufeng.enum.TriggerAction
import cn.explo.gufeng.vm.DengMiVM


/**
 * A simple [Fragment] subclass.
 *
 */
class DengmiDetailFrag  : BaseNestFragment() {
    private lateinit var  dBinding : DengmiDetailFragBinding
    private lateinit var vm: DengMiVM
    private var isShowAnswer:Boolean=false

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("isShowAnswer",isShowAnswer)
    }

    override fun getLayoutId(): Int {
        return R.layout.dengmi_detail_frag
    }

    override fun initVariable(dataBinding: ViewDataBinding) {
        super.initVariable(dataBinding)
        dBinding= dataBinding as DengmiDetailFragBinding
        vm=getViewModel()
        is_display_back_btn=true
    }

    override fun initListener() {
        super.initListener()
        dBinding.ivEye.setOnClickListener {
            isShowAnswer=true
            dBinding.item?.showAnswer?.set(isShowAnswer)
        }
    }


    override fun bindData(savedInstanceState: Bundle?) {
        super.bindData(savedInstanceState)
        savedInstanceState?.run {
            isShowAnswer=getBoolean("isShowAnswer")
        }
    }

    override fun onVisible(triggerAction: TriggerAction) {
        val id=arguments?.getInt("id")
        vm.getById(id!!).observe(this, Observer {
            it?.showAnswer?.set(isShowAnswer)
            title=it?.hint!!
            dBinding.item=it
            setTitleAndBackBtn()
        })
    }
}

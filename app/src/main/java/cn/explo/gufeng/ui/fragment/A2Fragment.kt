package cn.explo.gufeng.ui.fragment

import android.databinding.ViewDataBinding
import android.support.v4.app.Fragment
import cn.explo.gufeng.R
import cn.explo.gufeng.base.BaseNestFragment
import cn.explo.gufeng.databinding.FragmentA2Binding


/**
 * A simple [Fragment] subclass.
 *
 */
class A2Fragment : BaseNestFragment() {
    lateinit var  dBinding: FragmentA2Binding
    override fun getLayoutId(): Int {
        return R.layout.fragment_a2
    }

    override fun initVariable(dataBinding: ViewDataBinding) {
        super.initVariable(dataBinding)
        dBinding=dataBinding as FragmentA2Binding
        title="灯谜2"
        is_display_back_btn=true
    }
    override fun initListener() {
        super.initListener()
        dBinding.nextButton.setOnClickListener({
            val parent=parentFragment as NestContainFragment
            parent.showNextFragment(this@A2Fragment,A3Fragment())
        })
    }
}

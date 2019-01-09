package cn.explo.gufeng.ui.fragment

import android.databinding.ViewDataBinding
import android.support.v4.app.Fragment
import cn.explo.gufeng.R
import cn.explo.gufeng.base.BaseNestFragment
import cn.explo.gufeng.databinding.FragmentA1Binding


/**
 * A simple [Fragment] subclass.
 *
 */
class A1Fragment : BaseNestFragment() {
    lateinit var  dBinding : FragmentA1Binding
    override fun getLayoutId(): Int {
        return R.layout.fragment_a1
    }

    override fun initVariable(dataBinding: ViewDataBinding) {
        super.initVariable(dataBinding)
        dBinding=dataBinding as FragmentA1Binding
        title="灯谜"
    }


    override fun initListener() {
        super.initListener()
        dBinding.nextButton.setOnClickListener({
//            val a2Frag = Fragment.instantiate(this@A1Fragment.activity, A2Fragment::class.java.name)
            val parent=parentFragment as NestContainFragment
            parent.showNextFragment(this@A1Fragment,A2Fragment())
        })
    }


}

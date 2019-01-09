package cn.explo.gufeng.ui.fragment


import android.databinding.ViewDataBinding
import android.support.v4.app.Fragment
import cn.explo.gufeng.R
import cn.explo.gufeng.base.BaseNestFragment
import cn.explo.gufeng.databinding.FragmentB1Binding


/**
 * A simple [Fragment] subclass.
 *
 */
class B1Fragment : BaseNestFragment() {
    lateinit var  dBinding : FragmentB1Binding
    override fun getLayoutId(): Int {
        return R.layout.fragment_b1
    }

    override fun initVariable(dataBinding: ViewDataBinding) {
        super.initVariable(dataBinding)
        dBinding=dataBinding as FragmentB1Binding
        title="一言"
    }
    override fun initListener() {
        super.initListener()
        dBinding.nextButton.setOnClickListener({
            val parent=parentFragment as NestContainFragment
            parent.showNextFragment(this@B1Fragment,B2Fragment())
        })
    }

}

package cn.explo.gufeng.ui.fragment

import android.databinding.ViewDataBinding
import android.support.v4.app.Fragment
import cn.explo.gufeng.R
import cn.explo.gufeng.base.BaseNestFragment


/**
 * A simple [Fragment] subclass.
 *
 */
class C1Fragment : BaseNestFragment() {


    override fun getLayoutId(): Int {
        return R.layout.fragment_c1
    }
    override fun initVariable(dataBinding: ViewDataBinding) {
        super.initVariable(dataBinding)
        title="歇后语"
    }
}

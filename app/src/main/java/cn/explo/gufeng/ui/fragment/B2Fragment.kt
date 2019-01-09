package cn.explo.gufeng.ui.fragment


import android.databinding.ViewDataBinding
import android.support.v4.app.Fragment
import cn.explo.gufeng.R
import cn.explo.gufeng.base.BaseNestFragment


/**
 * A simple [Fragment] subclass.
 *
 */
class B2Fragment : BaseNestFragment() {

    override fun initVariable(dataBinding: ViewDataBinding) {
        super.initVariable(dataBinding)
        title="一言2"
        is_display_back_btn=true
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_b2
    }
}

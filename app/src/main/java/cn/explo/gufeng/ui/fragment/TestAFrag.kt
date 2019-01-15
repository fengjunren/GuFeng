package cn.explo.gufeng.ui.fragment

import android.content.Intent
import android.databinding.ViewDataBinding
import android.support.v4.app.Fragment
import cn.explo.gufeng.R
import cn.explo.gufeng.base.BaseNestFragment
import cn.explo.gufeng.databinding.TestaFragBinding
import cn.explo.gufeng.ui.activity.TestBAct


/**
 * A simple [Fragment] subclass.
 *
 */
class TestAFrag : BaseNestFragment() {
    lateinit var  dBinding: TestaFragBinding

    override fun getLayoutId(): Int {
        return R.layout.testa_frag
    }

    override fun initListener() {
        super.initListener()
        dBinding.tv.setOnClickListener {
            startActivity(Intent(activity,TestBAct::class.java))
        }
    }

    override fun initVariable(dataBinding: ViewDataBinding) {
        super.initVariable(dataBinding)
        title="A"
        is_display_back_btn=true
        dBinding=dataBinding as TestaFragBinding
    }

}

package cn.explo.gufeng.ui.fragment

import android.databinding.ViewDataBinding
import android.support.v4.app.Fragment
import cn.explo.gufeng.R
import cn.explo.gufeng.base.BaseActivity
import cn.explo.gufeng.base.BaseNestFragment
import cn.explo.gufeng.databinding.AboutFragBinding


/**
 * A simple [Fragment] subclass.
 *
 */
class AboutFrag : BaseNestFragment() {
    lateinit var  dBinding: AboutFragBinding

    override fun getLayoutId(): Int {
        return R.layout.about_frag
    }

    override fun initListener() {
        super.initListener()
        dBinding.iv.setOnClickListener {
            val act=activity as BaseActivity
            getFm()!!.beginTransaction().hide(this@AboutFrag).commitAllowingStateLoss()
            act.showNextFragment(MainFrag::class.qualifiedName,TestAFrag())
        }
    }

    override fun initVariable(dataBinding: ViewDataBinding) {
        super.initVariable(dataBinding)
        title="关于"
        dBinding=dataBinding as AboutFragBinding
    }

}

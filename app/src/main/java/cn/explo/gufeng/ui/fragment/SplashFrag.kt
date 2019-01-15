package cn.explo.gufeng.ui.fragment

import android.databinding.ViewDataBinding
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import cn.explo.gufeng.R
import cn.explo.gufeng.base.BaseFragment
import cn.explo.gufeng.databinding.SplashFragBinding
import cn.explo.gufeng.exts.toNextFragment
import cn.explo.gufeng.ui.activity.MainAct
import cn.explo.gufeng.ui.widget.FadeInTextView
import cn.explo.gufeng.utils.AppExecutors
import java.util.concurrent.TimeUnit

class SplashFrag : BaseFragment() {
    lateinit var dBinding: SplashFragBinding
    override fun getLayoutId(): Int {
        return R.layout.splash_frag
    }

    override fun initVariable(dataBinding: ViewDataBinding) {
        super.initVariable(dataBinding)
        dBinding = dataBinding as SplashFragBinding
    }

    override fun bindData(savedInstanceState: Bundle?) {
        super.bindData(savedInstanceState)
        val act = activity as MainAct
        act.supportActionBar!!.hide()

        val tv4Finish = FadeInTextView.TextAnimationListener {
            Handler().postDelayed({
                act.showNextFragment(null, MainFrag(), false)
                finishFragment(this)
                act.supportActionBar!!.show()
            },500)
        }
        val tv3Finish = FadeInTextView.TextAnimationListener {
            print(dBinding.tv4, R.string.shy_4, tv4Finish)
        }
        val tv2Finish = FadeInTextView.TextAnimationListener {
            print(dBinding.tv3, R.string.shy_3, tv3Finish)
        }
        val tv1Finish = FadeInTextView.TextAnimationListener {
            print(dBinding.tv2, R.string.shy_2, tv2Finish)
        }
        val tv5Finish = FadeInTextView.TextAnimationListener {
            print(dBinding.tv1, R.string.shy_1, tv1Finish)
        }
        print(dBinding.tv5, R.string.shy_5, tv5Finish)
    }

    fun print(tv: FadeInTextView, txtId: Int, onFinish: FadeInTextView.TextAnimationListener) {
        tv.setTextString(getString(txtId))
            .startFadeInAnimation()
            .setTextAnimationListener(onFinish)
    }


}
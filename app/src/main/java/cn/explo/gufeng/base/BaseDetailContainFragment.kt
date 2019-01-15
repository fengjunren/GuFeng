package cn.explo.gufeng.base

import android.os.Bundle
import android.support.v4.view.ViewPager

open class BaseDetailContainFragment : BaseNestFragment() {
    protected var mPosition:Int?=0
    protected val mOnPageChangeListener=object : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(p0: Int) {

        }
        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

        }
        override fun onPageSelected(pos: Int) {
            mPosition=pos
        }

    }

    override fun bindData(savedInstanceState: Bundle?) {
        super.bindData(savedInstanceState)
        savedInstanceState?.apply {
            mPosition=savedInstanceState.getInt("position")
        }?:apply {
            mPosition=arguments?.getInt("position")
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("position",mPosition!!)
    }

}
package cn.explo.gufeng.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.Window
import android.widget.ProgressBar
import cn.explo.gufeng.exts.removeFragment
import cn.explo.gufeng.exts.toNextFragment

open class BaseActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataBinding : ViewDataBinding = DataBindingUtil.setContentView(this,getLayoutId())
        dataBinding.setLifecycleOwner(this)
        initVariable(dataBinding)
        initView()
        initListener()
        bindData(savedInstanceState)
    }
    open fun getLayoutId():Int{
        return 0
    }
    open fun initVariable(dataBinding : ViewDataBinding){

    }
    open fun initView(){

    }
    open fun initListener(){

    }
    open fun bindData(savedInstanceState: Bundle?){

    }

    open fun getFragmentContainId():Int=0

    fun showNextFragment(outTag: String?, infrag: Fragment, isStack: Boolean){
        toNextFragment(supportFragmentManager,getFragmentContainId(),outTag,infrag,isStack)
    }

    fun showNextFragment(outTag: String?, infrag: Fragment){
        showNextFragment(outTag,infrag,true)
    }

    fun finishFragment(fragment: Fragment){
        removeFragment(fragment,supportFragmentManager)
    }
}
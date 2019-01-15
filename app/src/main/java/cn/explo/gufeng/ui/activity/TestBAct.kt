package cn.explo.gufeng.ui.activity

import android.databinding.ViewDataBinding
import android.os.Bundle
import android.os.PersistableBundle
import cn.explo.gufeng.R
import cn.explo.gufeng.base.BaseActivity
import cn.explo.gufeng.databinding.TestbActBinding

class TestBAct : BaseActivity() {
    lateinit var  dBinding: TestbActBinding
    override fun getLayoutId(): Int {
        return R.layout.testb_act
    }

    override fun initVariable(dataBinding : ViewDataBinding) {
        super.initVariable(dataBinding)
        dBinding = dataBinding as TestbActBinding
    }

    override fun bindData(savedInstanceState: Bundle?) {
        super.bindData(savedInstanceState)
        setSupportActionBar(dBinding.myToolbar)
        supportActionBar?.title = "B"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}

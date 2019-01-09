package cn.explo.gufeng.vm

import android.app.Application
import cn.explo.gufeng.base.BaseAndroidViewModel
import cn.explo.gufeng.repository.XieHouYuRepo

class XieHouYuVM(application: Application) : BaseAndroidViewModel(application) {
    lateinit var  mRepo:XieHouYuRepo
    init {
        mRepo= XieHouYuRepo(application)
    }
}
package cn.explo.gufeng.vm

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import cn.explo.gufeng.base.BaseAndroidViewModel
import cn.explo.gufeng.entity.XieHouYu
import cn.explo.gufeng.repository.XieHouYuRepo

class XieHouYuVM(application: Application) : BaseAndroidViewModel(application) {
    var  mRepo:XieHouYuRepo = XieHouYuRepo(application)
    private val pageCount=10

    fun qryNextPage(startId:Int): MutableLiveData<List<XieHouYu>> {
        return mRepo.qryNextPage(startId, pageCount)
    }

    fun getCount(): MutableLiveData<Int> {
        return mRepo.getCount()
    }

    fun getById(id: Int): MutableLiveData<XieHouYu> {
        return mRepo.getById(id)
    }
}
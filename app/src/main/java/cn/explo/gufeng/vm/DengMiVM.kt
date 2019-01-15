package cn.explo.gufeng.vm

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import cn.explo.gufeng.base.BaseAndroidViewModel
import cn.explo.gufeng.entity.DengMi
import cn.explo.gufeng.repository.DengMiRepo

class DengMiVM(application: Application) : BaseAndroidViewModel(application) {
    private var  mRepo:DengMiRepo = DengMiRepo(application)
    private val pageCount=10

    fun qryNextPage(startId:Int): MutableLiveData<List<DengMi>> {
        return mRepo.qryNextPage(startId, pageCount)
    }

    fun getCount(): MutableLiveData<Int> {
        return mRepo.getCount()
    }

    fun getById(id: Int): MutableLiveData<DengMi> {
        return mRepo.getById(id)
    }
}
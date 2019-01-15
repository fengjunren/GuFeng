package cn.explo.gufeng.vm

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import cn.explo.gufeng.base.BaseAndroidViewModel
import cn.explo.gufeng.entity.GuShiCi
import cn.explo.gufeng.repository.GuShiCiRepo

class GushiciVM(application: Application) : BaseAndroidViewModel(application) {
    private var  mRepo: GuShiCiRepo = GuShiCiRepo(application)
    private val pageCount=10

    fun qryNextPage(startId:Int): MutableLiveData<List<GuShiCi>> {
        return mRepo.qryNextPage(startId, pageCount)
    }

    fun getCount(): MutableLiveData<Int> {
        return mRepo.getCount()
    }

    fun getById(id: Int): MutableLiveData<GuShiCi> {
        return mRepo.getById(id)
    }
}
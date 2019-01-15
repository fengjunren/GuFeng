package cn.explo.gufeng.repository

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import cn.explo.gufeng.db.dao.DengMiDao
import cn.explo.gufeng.db.GuFengDb
import cn.explo.gufeng.entity.DengMi
import cn.explo.gufeng.utils.AppExecutors

class DengMiRepo(ctx: Application) {
    private val mDao: DengMiDao? = GuFengDb.getInstance(ctx)?.dmDao()

    fun getCount(): MutableLiveData<Int> {
        val count = MutableLiveData<Int>()
        AppExecutors.execIO(Runnable {
            count.postValue(mDao?.qryCount())
        })
        return count
    }

    fun getById(id: Int): MutableLiveData<DengMi> {
        val dm = MutableLiveData<DengMi>()
        AppExecutors.execIO(Runnable {
            dm.postValue(mDao?.getById(id))
        })
        return dm
    }

    fun qryNextPage(startId: Int, pageCount: Int): MutableLiveData<List<DengMi>> {
        val ml = MutableLiveData<List<DengMi>>()
        AppExecutors.execIO(Runnable {
            ml.postValue(mDao!!.qryNextPage(startId, pageCount))
        })
        return ml
    }

}
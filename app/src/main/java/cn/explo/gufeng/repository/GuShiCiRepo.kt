package cn.explo.gufeng.repository

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import cn.explo.gufeng.db.GuFengDb
import cn.explo.gufeng.db.dao.GuShiCiDao
import cn.explo.gufeng.entity.GuShiCi
import cn.explo.gufeng.utils.AppExecutors

class GuShiCiRepo(ctx:Application){
 var  mDao:GuShiCiDao? = GuFengDb.getInstance(ctx)?.gscDao()

    fun getCount(): MutableLiveData<Int> {
        val count = MutableLiveData<Int>()
        AppExecutors.execIO(Runnable {
            count.postValue(mDao?.qryCount())
        })
        return count
    }

    fun getById(id: Int): MutableLiveData<GuShiCi> {
        val dm = MutableLiveData<GuShiCi>()
        AppExecutors.execIO(Runnable {
            dm.postValue(mDao?.getById(id))
        })
        return dm
    }

    fun qryNextPage(startId: Int, pageCount: Int): MutableLiveData<List<GuShiCi>> {
        val ml = MutableLiveData<List<GuShiCi>>()
        AppExecutors.execIO(Runnable {
            ml.postValue(mDao!!.qryNextPage(startId, pageCount))
        })
        return ml
    }
}
package cn.explo.gufeng.repository

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import cn.explo.gufeng.db.GuFengDb
import cn.explo.gufeng.db.dao.XieHouYuDao
import cn.explo.gufeng.entity.XieHouYu
import cn.explo.gufeng.utils.AppExecutors

class XieHouYuRepo(val ctx:Application){
 var  mDao:XieHouYuDao? = GuFengDb.getInstance(ctx)?.xhyDao()


    fun getCount(): MutableLiveData<Int> {
        val count = MutableLiveData<Int>()
        AppExecutors.execIO(Runnable {
            count.postValue(mDao?.qryCount())
        })
        return count
    }

    fun getById(id: Int): MutableLiveData<XieHouYu> {
        val dm = MutableLiveData<XieHouYu>()
        AppExecutors.execIO(Runnable {
            dm.postValue(mDao?.getById(id))
        })
        return dm
    }

    fun qryNextPage(startId: Int, pageCount: Int): MutableLiveData<List<XieHouYu>> {
        val ml = MutableLiveData<List<XieHouYu>>()
        AppExecutors.execIO(Runnable {
            ml.postValue(mDao!!.qryNextPage(startId, pageCount))
        })
        return ml
    }
}
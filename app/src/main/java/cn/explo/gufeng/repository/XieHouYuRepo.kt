package cn.explo.gufeng.repository

import android.app.Application
import cn.explo.gufeng.db.GuFengDb
import cn.explo.gufeng.db.dao.XieHouYuDao

class XieHouYuRepo(val ctx:Application){
 var  mDao:XieHouYuDao? = GuFengDb.getInstance(ctx)?.xhyDao()

    fun getCount(): Int?{
        return mDao?.qryCount()
    }
}
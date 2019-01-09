package cn.explo.gufeng.repository

import android.app.Application
import cn.explo.gufeng.db.GuFengDb
import cn.explo.gufeng.db.dao.GuShiCiDao

class GuShiCiRepo(ctx:Application){
 var  mDao:GuShiCiDao? = GuFengDb.getInstance(ctx)?.gscDao()

    fun getCount(): Int?{
        return mDao?.qryCount()
    }
}
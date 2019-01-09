package cn.explo.gufeng.repository

import android.app.Application
import cn.explo.gufeng.db.dao.DengMiDao
import cn.explo.gufeng.db.GuFengDb

class DengMiRepo(ctx:Application){
 val  mDao:DengMiDao? = GuFengDb.getInstance(ctx)?.dmDao()

 fun getCount(): Int?{
  return mDao?.qryCount()
 }


}
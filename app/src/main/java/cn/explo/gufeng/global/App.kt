package cn.explo.gufeng.global

import android.app.Application
import cn.explo.gufeng.repository.DengMiRepo
import cn.explo.gufeng.tools.hawk.Hawk
import cn.explo.gufeng.tools.hawk.NoEncryption
import cn.explo.gufeng.utils.AppExecutors
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.CsvFormatStrategy
import com.orhanobut.logger.DiskLogAdapter
import com.orhanobut.logger.Logger
import java.text.SimpleDateFormat


class App : Application() {
    init {
        instance = this
    }

    companion object {
        lateinit var instance: App
    }

  override fun onCreate(){
      super.onCreate()
      initLog()
      initKVStore()
      initDB()
  }


    fun initLog(){
        Logger.addLogAdapter(AndroidLogAdapter())
        val formatStrategy = CsvFormatStrategy.newBuilder()
            .dateFormat(SimpleDateFormat("yyyy-MM-dd HH：mm:ss SSS"))
            .build()
        Logger.addLogAdapter(DiskLogAdapter(formatStrategy))
    }

    fun initKVStore()= Hawk.init(instance).setEncryption(NoEncryption()).build()


    fun initDB()= AppExecutors.execIO(Runnable {
        DengMiRepo(instance).getCount()
    })
}

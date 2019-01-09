package cn.explo.gufeng.db

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import cn.explo.gufeng.db.dao.DengMiDao
import cn.explo.gufeng.db.dao.GuShiCiDao
import cn.explo.gufeng.db.dao.XieHouYuDao
import cn.explo.gufeng.entity.DengMi
import cn.explo.gufeng.entity.GuShiCi
import cn.explo.gufeng.entity.XieHouYu
import cn.explo.gufeng.global.App
import cn.explo.gufeng.utils.AppExecutors
import cn.explo.gufeng.utils.Common
import cn.explo.gufeng.utils.GsonUtil
import com.orhanobut.logger.Logger

@Database(entities = arrayOf(DengMi::class, GuShiCi::class, XieHouYu::class), version = 1)
abstract class GuFengDb : RoomDatabase() {
    abstract fun dmDao(): DengMiDao
    abstract fun xhyDao(): XieHouYuDao
    abstract fun gscDao(): GuShiCiDao

    companion object {
        @Volatile
        private var instance: GuFengDb?=null

//        internal fun getInstance(ctx: Application): GuFengDb? {
//            if (instance == null) {
//                synchronized(this) {
//                    if (instance == null) {
//                      instance = Room.databaseBuilder(
//                            ctx,
//                            GuFengDb::class.java, "gufeng"
//                        ).addCallback(GuFengDb.sRoomDatabaseCallback)
//                         .build()
//                    }
//                }
//            }
//            return instance
//        }


        fun getInstance(ctx: Context): GuFengDb =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(ctx).also { instance = it }
            }

        private fun buildDatabase(ctx: Context) =
            Room.databaseBuilder(ctx.applicationContext,
                GuFengDb::class.java, "gufeng.db")
                .addCallback(GuFengDb.sRoomDatabaseCallback)
                .build()



        private val sRoomDatabaseCallback = object : RoomDatabase.Callback() {

            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                AppExecutors.execIO(Runnable {
                    val t1=System.currentTimeMillis()
                    var jsonStr1= Common.getFromAssets("dengmi.txt", App.instance)
                    val lst1= GsonUtil.fromJson2<List<DengMi>>(jsonStr1)
                    instance?.dmDao()?.insert(lst1)
                    val t2=System.currentTimeMillis()
                    Logger.i("-----init dengmi spend :"+(t2-t1)/1000.0)


                     val jsonStr2= Common.getFromAssets("xiehouyu.txt", App.instance)
                     val lst2= GsonUtil.fromJson2<List<XieHouYu>>(jsonStr2)
                    instance?.xhyDao()?.insert(lst2)
                    val t3=System.currentTimeMillis()
                    Logger.i("----init xiehouyu spend :"+(t3-t2)/1000.0)


                    val jsonStr3= Common.getFromAssets("gushici.txt", App.instance)
                    val lst3= GsonUtil.fromJson2<List<GuShiCi>>(jsonStr3)
                     instance?.gscDao()?.insert(lst3)
                    val t4=System.currentTimeMillis()
                    Logger.i("-----init gushici spend :"+(t4-t3)/1000.0)
                })
            }
        }
    }
}
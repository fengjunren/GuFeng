package cn.explo.gufeng.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Transaction
import cn.explo.gufeng.entity.DengMi

@Dao
interface DengMiDao{
    @Insert
    fun insert(dm: DengMi)

    @Query("select * from deng_mi where id=:id")
    fun getById(id:Int): DengMi

    @Query("select count(1) from deng_mi")
    fun qryCount(): Int

    @Query("select * from deng_mi  where id>:startId order by id asc limit :pageCount")
    fun qryNextPage(startId:Int,pageCount:Int): List<DengMi>

    @Transaction
    fun insert(lst: List<DengMi>){
        for(dm in lst){
            insert(dm)
        }
    }


}
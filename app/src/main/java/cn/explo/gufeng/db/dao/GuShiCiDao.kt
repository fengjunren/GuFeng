package cn.explo.gufeng.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Transaction
import cn.explo.gufeng.entity.GuShiCi

@Dao
interface GuShiCiDao{
    @Insert
    fun insert(gsc: GuShiCi)

    @Transaction
    fun insert(lst: List<GuShiCi>){
        for(gsc in lst){
            insert(gsc)
        }
    }

    @Query("select count(1) from gu_si_ci")
    fun qryCount(): Int
}
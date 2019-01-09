package cn.explo.gufeng.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Transaction
import cn.explo.gufeng.entity.XieHouYu

@Dao
interface XieHouYuDao{
    @Insert
    fun insert(xhy: XieHouYu)

    @Transaction
    fun insert(lst: List<XieHouYu>){
        for(xhy in lst){
            insert(xhy)
        }
    }

    @Query("select count(1) from xie_hou_yu")
    fun qryCount(): Int
}
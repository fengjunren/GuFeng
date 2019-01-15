package cn.explo.gufeng.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Transaction
import cn.explo.gufeng.entity.GuShiCi
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
    @Query("select * from xie_hou_yu where id=:id")
    fun getById(id:Int): XieHouYu

    @Query("select count(1) from xie_hou_yu")
    fun qryCount(): Int

    @Query("select * from xie_hou_yu  where id>:startId order by id asc limit :pageCount")
    fun qryNextPage(startId:Int,pageCount:Int): List<XieHouYu>
}
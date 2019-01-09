package cn.explo.gufeng.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull

@Entity(tableName = "xie_hou_yu")
data class XieHouYu (
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int = 0,
    @ColumnInfo(name = "riddle")
    var riddle: String? = "",
    @ColumnInfo(name = "answer")
    var answer: String? = ""
)
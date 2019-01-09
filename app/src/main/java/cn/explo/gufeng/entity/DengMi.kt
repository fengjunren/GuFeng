package cn.explo.gufeng.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull

@Entity(tableName = "deng_mi")
data class DengMi(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int = 0,
    @ColumnInfo(name = "type")
    var type: String? = "",
    @ColumnInfo(name = "riddle")
    var riddle: String? = "",
    @ColumnInfo(name = "answer")
    var answer: String? = "",
    @ColumnInfo(name = "hint")
    var hint: String? = ""
)
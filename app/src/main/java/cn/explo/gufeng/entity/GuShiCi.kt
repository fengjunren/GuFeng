package cn.explo.gufeng.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull

@Entity(tableName = "gu_si_ci")
data class GuShiCi (
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int = 0,
    @ColumnInfo(name = "content")
    var content: String? = "",
    @ColumnInfo(name = "origin")
    var origin: String? = "",
    @ColumnInfo(name = "author")
    var author: String? = "",
    @ColumnInfo(name = "category")
    var category: String? = "",
    @ColumnInfo(name = "c1")
    var c1: String? = "",
    @ColumnInfo(name = "c2")
    var c2: String? = "",
    @ColumnInfo(name = "c3")
    var c3: String? = ""
    )

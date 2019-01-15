package cn.explo.gufeng.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import android.databinding.ObservableField
import android.support.annotation.NonNull
import cn.explo.gufeng.BR
import cn.explo.gufeng.R
import cn.explo.gufeng.base.MultiTypeAdapter

@Entity(tableName = "deng_mi")
 class DengMi(
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
) : MultiTypeAdapter.IItem{
    @Ignore
    var showAnswer: ObservableField<Boolean> = ObservableField()
    override fun getTypeId(): Int {
        return R.layout.item_dengmi
    }
    override fun getVariableId(): Int {
       return BR.item
    }
}
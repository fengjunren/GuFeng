package cn.explo.gufeng.entity

import android.arch.lifecycle.MutableLiveData

data class User (
    var name: MutableLiveData<String>
){
     var age:String=""
    set(value) {
        field=value
    }
    get(){return field}
}
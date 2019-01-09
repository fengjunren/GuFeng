package cn.explo.gufeng.vm

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import cn.explo.gufeng.base.BaseViewModel
import cn.explo.gufeng.entity.User


class MainVM : BaseViewModel() {
    // TODO: Implement the ViewModel
    private val selected = MutableLiveData<String>()
    private val user = User(MutableLiveData())


    fun getUser(): User {
        return user
    }

    fun select(item: String) {
        selected.value = item
    }

    fun getSelected(): LiveData<String> {
        return selected
    }
}

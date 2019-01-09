package cn.explo.gufeng.vm

import android.arch.lifecycle.MutableLiveData
import cn.explo.gufeng.base.ActivityShareData
import cn.explo.gufeng.base.BaseViewModel


class SharedVM : ActivityShareData() {
    private val title = MutableLiveData<String>()
    private val isDisplayBackBtn = MutableLiveData<Boolean>()

    fun getTitle()=title

    fun setTitle(s: String) {
        title.value = s
    }

    fun getIsDisplayBackBtn()=isDisplayBackBtn
    fun setIsDisplayBackBtn(b:Boolean){
        isDisplayBackBtn.value=b
    }


}

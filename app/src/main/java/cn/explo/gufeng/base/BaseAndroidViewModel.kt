package cn.explo.gufeng.base

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.ViewModel

open class BaseAndroidViewModel : AndroidViewModel {
    constructor(application: Application) : super(application)
}
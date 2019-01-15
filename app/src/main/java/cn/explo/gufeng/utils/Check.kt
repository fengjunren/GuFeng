package cn.explo.gufeng.utils

import android.content.Context
import cn.explo.gufeng.tools.hawk.Hawk

class Check{
    companion object {
        fun checkIsFirstInstall(ctx:Context): Boolean {
            val packageManager = ctx.packageManager
            val packageInfo = packageManager.getPackageInfo(ctx.packageName, 0)
            return packageInfo.firstInstallTime == packageInfo.lastUpdateTime
        }

        fun checkIsFirstOpen(ctx:Context): Boolean {
            return checkIsFirstInstall(ctx) && Hawk.get("is_First_open",true)
        }
    }


}
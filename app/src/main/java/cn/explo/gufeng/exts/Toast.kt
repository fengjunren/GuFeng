package cn.explo.gufeng.exts

import android.widget.Toast
import cn.explo.gufeng.global.App

/** toast相关 **/
fun Any.toast(msg: CharSequence) {
    Toast.makeText(App.instance, msg, Toast.LENGTH_SHORT).show()
}
fun Any.longToast(msg: CharSequence) {
    Toast.makeText(App.instance, msg, Toast.LENGTH_LONG).show()
}

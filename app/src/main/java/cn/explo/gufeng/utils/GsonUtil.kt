package cn.explo.gufeng.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object GsonUtil {

    val gson = Gson()

    inline fun <reified T> fromJson2(json: String): T {
        return gson.fromJson<T>(json, object: TypeToken<T>() {}.type)
    }

    inline fun <reified T> fromJson(json: String): T {
        return gson.fromJson<T>(json, T::class.java)
    }

    fun serialize(o: Any): String {
        return gson.toJson(o)
    }

}

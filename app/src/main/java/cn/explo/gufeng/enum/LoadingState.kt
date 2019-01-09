package cn.explo.gufeng.enum

sealed class LoadingState
data class Loading   (var msg:String="请求中",var msgCode:Int=0) : LoadingState()
data class Complete  (var msg:String="请求完成",var msgCode:Int=0) : LoadingState()
data class Success  (var msg:String="请求成功",var msgCode:Int=0) : LoadingState()
data class Fail (var msg:String="请求失败",var msgCode:Int=0) : LoadingState()

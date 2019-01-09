package cn.explo.gufeng.utils

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService

object AppExecutors {
    // 密集型IO  disk network
    private val io: ExecutorService =Executors.newFixedThreadPool(5)
    // 计算任务
    private val cpu: ExecutorService=Executors.newFixedThreadPool(3)
    // 主线程
    private val mainThread: Executor= MainThreadExecutor()
    // 定时
    private val schedulePool: ScheduledExecutorService= Executors.newScheduledThreadPool(5)

    fun execIO(r:Runnable){
         io.execute(r)
    }
    fun execCPU(r:Runnable){
         cpu.execute(r)
    }
    fun schedule():ScheduledExecutorService{
      return schedulePool
    }
    fun execSchedule(r:Runnable){
        schedulePool.execute(r)
    }

    fun mainThread(): Executor {
        return mainThread
    }

    private class MainThreadExecutor : Executor {
        private val mainThreadHandler = Handler(Looper.getMainLooper())
        override fun execute(command: Runnable) {
            mainThreadHandler.post(command)
        }
    }

    fun shutDown() {
        if(!io.isShutdown) io.shutdownNow()
        if(!cpu.isShutdown) cpu.shutdownNow()
        if (!schedulePool.isShutdown) schedulePool.shutdownNow()
    }
}
package com.example.baiyuanwei.nicecopy.thread;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by baiyuanwei on 16/8/14.
 * 创建线程、切换线程
 *
 * 线程池：
 * 1、fixedThreadPool: 创建一个可重用固定线程数的线程池，没有IDLE机制；
 * 2、cachedTreadPool: 创建一个可根据需要创建线程的线程池，自动终止并移除已有60秒未被使用的线程；
 * 3、SingleTreadExecutor: 单例线程池，任意时间只能有一个线程在运行
 * 4、ScheduleTreadPool:
 */
public class Worker {
    private static ExecutorService EXECUTOR;

    private static Handler mainHandler;

    private static void checkInit(){
        if (EXECUTOR == null || EXECUTOR.isTerminated()){
            EXECUTOR = Executors.newCachedThreadPool(new NiceCopyThreadFactory("NiceCopy-worker"));

            mainHandler = new Handler(Looper.getMainLooper());
        }
    }

    /**
     * 在新的线程中执行任务
     * @param runnable
     */
    public static void postWorker(Runnable runnable){
        checkInit();

        try {
            EXECUTOR.execute(runnable);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 在主线程中执行任务
     * @param runnable
     */
    public static void postMain(Runnable runnable){
        checkInit();

        if (null != mainHandler){
            mainHandler.post(runnable);
        }
    }

    /**
     * 停止所有线程的任务
     */
    public static void onDestory(){
        EXECUTOR.shutdown();

        try {
            if (!EXECUTOR.awaitTermination(1, TimeUnit.SECONDS)){
                EXECUTOR.shutdownNow();
            }
        }catch (Exception e){
            EXECUTOR.shutdownNow();

            Thread.currentThread().interrupt();
        }
    }
}

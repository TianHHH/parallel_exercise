package com.parallel_exec.chapter2;

import lombok.extern.slf4j.Slf4j;

/**
 * @author : ZQCHANG
 * @Date : 2025/1/2 21:51
 * @Desc : 两阶段终止模式基础样板
 */
@Slf4j
public class TwoPhaseTermination {

    // 通知工作线程是否需要终止, volatile保证了线程间对该标志的可见性
    private static volatile boolean stopRequested = false;

    public static void main(String[] args) throws InterruptedException {
        // 创建工作线程并启动
        Thread workerThread = new Thread(() -> {
            try{
                while(!stopRequested){
                    try{
                        log.info("线程正在工作...");
                        // 模拟任务处理
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        // 如果线程被中断, 响应并退出
                        // 重新设置打断标记, 以便其他部分的代码可以检测到该线程已被中断
                        Thread.currentThread().interrupt();
                    }
                }
            }finally {
                log.info("资源清理, 线程停止...");
            }
        }, "workerThread");

        workerThread.start();

        // 主线程休眠两秒后发出停止请求
        Thread.sleep(2000);
        stopRequested = true;
        workerThread.join();
        log.info("程序结束");
    }
}

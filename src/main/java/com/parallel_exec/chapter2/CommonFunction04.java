package com.parallel_exec.chapter2;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalTime;

/**
 * @author : ZQCHANG
 * @Date : 2025/1/2 19:54
 * @Desc : 线程中断练习
 */
@Slf4j
public class CommonFunction04 {

    // private static boolean interrupted;

    public static void main(String[] args) throws InterruptedException {
        // 实现一个线程, 在线程中循环打印数字 1-100
        // 在主线程中等待 2 秒后中断该线程, 要求线程能正确响应中断

        Thread t1 = new Thread(() -> {
            log.info("t1线程启动 -- {}", LocalTime.now());
            for(int i = 1; i <= 100; i++){

                // 每次循环开始检查打断标记状态
                if (Thread.currentThread().isInterrupted()) {
                    // 检查中断标记，提前退出
                    log.info("t1线程被中断，退出循环 -- {}", LocalTime.now());
                    break;
                }

                log.info(String.valueOf(i));
                // 获取当前线程
                Thread current = Thread.currentThread();
                try {
                    Thread.sleep(200);
                    // interrupted = current.isInterrupted();
                } catch (InterruptedException e) {
                    // 如果被打断的线程正在sleep, wait, join会抛异常
                    // 并且打断标记会被清除, 这里是重新设置打断标记
                    // interrupted = true; 优化版去除interrupted变量
                    // 下面是优化后的重新设置打断标记
                    Thread.currentThread().interrupt();
                    log.info("t1线程被中断，退出循环 -- {}", LocalTime.now());
                    break;
                }
                // 如果被打断则中断线程(退出循环)
//                if(interrupted){
//                    break;
//                }
            }
        }, "t1");
        t1.start();

        // 主线程休眠两秒
        Thread.sleep(2000);
        // 打断正常运行的线程, 不会清空打断状态
        t1.interrupt();
        log.info("程序运行完毕 -- {}", LocalTime.now());

    }
}

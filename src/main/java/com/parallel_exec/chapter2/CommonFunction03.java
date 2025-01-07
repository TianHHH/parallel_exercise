package com.parallel_exec.chapter2;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

/**
 * @author : ZQCHANG
 * @Date : 2025/1/2 19:42
 * @Desc : 线程休眠练习
 */
@Slf4j
public class CommonFunction03 {
    public static void main(String[] args){

        // 创建一个线程, 每隔 1 秒打印一次当前时间, 共打印 5 次。
        // 使用 Thread.sleep() 方法实现
        new Thread(() -> {
            int count = 0;
            while(count < 5){
                log.info("当前的时间是 -- {}", LocalDateTime.now());
                count++;
                // 休眠一秒
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // 优化部分
                    Thread.currentThread().interrupt(); // 保留中断状态
                    log.error("线程被中断", e);
                    // throw new RuntimeException(e);
                }
            }
        }, "t1").start();

    }
}

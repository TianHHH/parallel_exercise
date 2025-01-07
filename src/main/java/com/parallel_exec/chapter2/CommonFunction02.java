package com.parallel_exec.chapter2;

import lombok.extern.slf4j.Slf4j;

/**
 * @author : ZQCHANG
 * @Date : 2025/1/2 17:05
 * @Desc : 设置线程优先级练习
 */
@Slf4j
public class CommonFunction02 {

    public static void printNums(){
        for(int i = 1; i <= 5; i++){
            log.info(String.valueOf(i));
        }
    }

    /*
        Thread 的优先级设置只对 JVM 和操作系统的调度器起到提示作用
        并不保证线程按照优先级严格执行
        即使设置了优先级, 也可能由于调度延迟, 低优先级线程先被分配CPU资源
     */

    public static void main(String[] args) {

        // 创建两个线程设置不同的优先级 Thread.MIN_PRIORITY 和 Thread.MAX_PRIORITY
        // 每个线程分别打印数字 1-5, 并观察输出是否有明显差异

        Thread t1 = new Thread(CommonFunction02::printNums, "t1");
        Thread t2 = new Thread(CommonFunction02::printNums, "t2");

        t1.setPriority(Thread.MAX_PRIORITY);
        t2.setPriority(Thread.MIN_PRIORITY);

        t1.start();
        t2.start();
    }

}

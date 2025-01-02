package com.parallel_exec.chapter2;

import lombok.extern.slf4j.Slf4j;

/**
 * @author : ZQCHANG
 * @Date : 2025/1/2 16:52
 * @Desc :
 */
@Slf4j
public class CommonFunction01 {

    private Runnable runnable;

    /*
        改进建议: 可以在打印数字时添加线程名, 便于区分不同线程的输出
        log.info("{}: {}", Thread.currentThread().getName(), i);
     */

    public static void main(String[] args) throws InterruptedException {

        // 使用 Thread 类创建一个线程 在线程中打印数字 1-10
        // 结束后在主线程中打印 "Main thread finished"
        Thread t1 = new Thread(){
            @Override
            public void run() {
                for(int i = 1; i <= 10; i++){
                    log.info(String.valueOf(i));
                }
            }
        };
        // 给线程设置别名
        t1.setName("t1");
        t1.start();
        // 在主线程中等待t1运行结束
        t1.join();
        log.info("Main thread finished");

        log.info("-----------------------------");
        CommonFunction01 commonFunction01 = new CommonFunction01();
        commonFunction01.useRunnable();
        Thread t2 = new Thread(commonFunction01.runnable, "t2");
        t2.start();
        t2.join();
        log.info("Main thread finished");

    }

    public void useRunnable(){

        // 再使用 Runnable 接口实现上述功能
        runnable = new Runnable() {
            @Override
            public void run() {
                for(int i = 1; i <= 10; i++){
                    log.info(String.valueOf(i));
                }
            }
        };

    }

}

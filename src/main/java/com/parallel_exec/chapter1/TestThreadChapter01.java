package com.parallel_exec.chapter1;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestThreadChapter01 {
    public static void main(String[] args) {

        // 创建三个自定义线程类对象
        MyThread myThread01 = new MyThread();
        MyThread myThread02 = new MyThread();
        MyThread myThread03 = new MyThread();

        myThread01.start();
        myThread02.start();
        myThread03.start();

    }
}

// 创建一个自定义线程类 -- 继承Thread即可将某个类变成线程类
@Slf4j
class MyThread extends Thread {
    @Override
    public void run() {
        log.info("MyThread invoke run method");
    }
}

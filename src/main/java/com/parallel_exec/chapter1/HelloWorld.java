package com.parallel_exec.chapter1;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HelloWorld {
    public static void main(String[] args) {

        // 启动第一个线程 t1
        new Thread(() -> {
            System.out.println("hello world -- t1");
        }, "t1").start();

        // 启动第二个线程 t2
        new Thread(() -> {
            System.out.println("hello world -- t2");
        }, "t2").start();

    }
}

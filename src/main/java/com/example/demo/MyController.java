package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * WEB MVC를 추가해야 콜러블이 들어가는군...
 */
@EnableAsync
@Slf4j
@RestController
public  class MyController {
    Queue<DeferredResult<String>> results = new ConcurrentLinkedDeque<>();
//    @GetMapping("/callable2")
//    public Callable<String> callable2() {
//        log.info("callable");
//        return () -> {
//            log.info("async");
//            Thread.sleep(2000);
//            return "hello";
//        };
//    }
//    @GetMapping("/callable1")
//    public String callable1() throws InterruptedException {
//        log.info("async");
//        Thread.sleep(2000);
//        return "hello";
//    }

//    @GetMapping("/dr")
//    public DeferredResult<String> callable3() {
//        log.info("dr");
//        DeferredResult<String> dr = new DeferredResult<>(600000L);
//        results.add(dr);
//        return dr;
//    }
//
//    @GetMapping("/dr/count")
//    public String drcount() {
//        return String.valueOf(results.size());
//    }
//
//    @GetMapping("/dr/event")
//    public String drevent(String msg) {
//        for (DeferredResult<String> dr : results) {
//            dr.setResult("Hello " + msg);
//            results.remove(dr);
//        }
//        return "OK";
//    }

    /**
     * Streaming... 와...
     * @return
     */
    @GetMapping("/emitter")
    public ResponseBodyEmitter emitter() {
        ResponseBodyEmitter res = new ResponseBodyEmitter();

        Executors.newSingleThreadExecutor()
                .submit(() -> {
                    try {
                        for (int i = 0; i < 50; i++) {
                            res.send("<p>Stream " + i + "</p>");
                            Thread.sleep(100);
                        }
                    } catch (Exception e) { }
        });

        return res;
    }

}
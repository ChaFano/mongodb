package com.chafan.controller;

import com.chafan.entity.Progress;
import com.chafan.service.StudentService;
import com.chafan.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @Auther: 茶凡
 * @ClassName ProgressController
 * @date 2023/11/2 9:38
 * @Description TODO
 */
@RestController
@RequestMapping("/api/progress/")
public class ProgressController {

    @Autowired
    StudentService studentService;

//    @GetMapping("/computeProgress")
    public R computeProgress() {

        List<Long> list = Arrays.asList(1000L, 10000L, 100000L);

        List<Progress> collect = list.stream().map(item -> {
            Progress progress = new Progress();
            Long time = studentService.batchSave(item);
            progress.setNumber(item);
            progress.setTime_consume(time);
            progress.setTps(item / time);
            return progress;

        }).collect(Collectors.toList());

        return R.ok().setData(collect);
    }


    private final ExecutorService executor = Executors.newFixedThreadPool(4); // 根据需要设置合适的线程池大小

    @GetMapping("/computeProgress2")
    public R computeProgress2() {

        List<Long> list = Arrays.asList(1000L, 10000L, 100000L,300000L);

        List<CompletableFuture<Progress>> futures = list.stream()
                .map(item -> CompletableFuture.supplyAsync(() -> {
                    Progress progress = new Progress();
                    Long time = studentService.batchSave(item);
                    progress.setNumber(item);
                    progress.setTime_consume(time);
                    progress.setTps(item / time);
                    return progress;
                }, executor))
                .collect(Collectors.toList());

        List<Progress> collect = futures.stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                        return null; // 根据需要处理异常情况
                    }
                })
                .collect(Collectors.toList());

        return R.ok().setData(collect);
    }





}

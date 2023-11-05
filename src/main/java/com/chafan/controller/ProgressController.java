package com.chafan.controller;

import com.chafan.entity.Tps;
import com.chafan.entity.Qps;
import com.chafan.service.StudentService;
import com.chafan.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
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

    @GetMapping("/computeProgress")
    public R computeProgress() {

        List<Long> list = Arrays.asList(1000L, 10000L, 100000L,300000L);

        List<Tps> collect = list.stream().map(item -> {
            Tps tps = new Tps();
            Long time = studentService.batchSave(item);
            tps.setNumber(item);
            tps.setTime_consume(time);
            tps.setTps(item / time);
            return tps;

        }).collect(Collectors.toList());

        return R.ok().setData(collect);
    }


    private final ExecutorService executor = Executors.newFixedThreadPool(6); // 根据需要设置合适的线程池大小

    @GetMapping("/computeProgress2")
    public R tps() {

        List<Long> list = Arrays.asList(10000L, 100000L,300000L,1000000L,2000000L);

        List<CompletableFuture<Tps>> futures = list.stream()
                .map(item -> CompletableFuture.supplyAsync(() -> {
                    Tps tps = new Tps();
                    Long time = studentService.batchSave(item);
                    tps.setNumber(item);
                    tps.setTime_consume(time);
                    tps.setTps(item / time);
                    return tps;
                }, executor))
                .collect(Collectors.toList());

        List<Tps> collect = futures.stream()
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



    @GetMapping("/qps")
    public R qps(){
//        List<Integer> list = Arrays.asList(10000, 100000,300000,1000000,2000000);
        List<Integer> list = Arrays.asList(1000000);

        List<CompletableFuture<Qps>> futures = list.stream()
                .map(item -> CompletableFuture.supplyAsync(() -> {

                    Qps progress = new Qps();

                    double time = studentService.getStudents(item);
                    progress.setNumber(item);
                    progress.setTime_consume(time);
                    progress.setQps(item / time);
                    return progress;

                }, executor))
                .collect(Collectors.toList());

        List<Qps> collect = futures.stream()
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

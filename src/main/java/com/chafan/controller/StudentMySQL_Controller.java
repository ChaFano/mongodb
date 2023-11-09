package com.chafan.controller;

import com.chafan.entity.Progress;
import com.chafan.entity.Qps;

import com.chafan.entity.Student1;
import com.chafan.service.StudentMySQL_Service;

import com.chafan.util.R;
import com.chafan.util.RandomStudent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * @Auther: 茶凡
 * @ClassName StudentMySQL_Controller
 * @date 2023/11/9 22:07
 * @Description mysql 性能压测接口
 */

@RequestMapping("/api/mysql")
@RestController
public class StudentMySQL_Controller {


    @Autowired
    StudentMySQL_Service studentService;

    @Autowired
    RandomStudent randomStudent;

    private final ExecutorService executor = Executors.newFixedThreadPool(6); // 根据需要设置合适的线程池大小

    @GetMapping("/batchInsert")
    public R batchInsert() {

        List<Long> list = Arrays.asList(10000L, 100000L, 300000L, 1000000L,2000000L);

        List<CompletableFuture<Progress>> futures = list.stream()
                .map(item -> CompletableFuture.supplyAsync(() -> {

                    Progress progress = new Progress();
                    Long time = batchSave(item);
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

    /**
     * 批量插入数据
     *
     * @return
     */
    public Long batchSave(Long number) {

        Instant start = Instant.now(); // 记录查询开始时间
        int Count = 10; // 分10个批次插入
        List<Student1> studentList = randomStudent.generateStudent2(number);
        int chunkSize = studentList.size() / Count; // 计算每个线程处理的数据量

        for (int i = 0; i < Count; i++) {
            int start1 = i * chunkSize;
            int end = (i == Count - 1) ? studentList.size() : (i + 1) * chunkSize;
            List<Student1> sublist = studentList.subList(start1, end);  // 每次截取 number/Count 条插入
            studentService.saveBatch(sublist);
        }

        Instant finish = Instant.now(); // 记录查询结束时间
        Duration timeElapsed = Duration.between(start, finish);
        return timeElapsed.getSeconds();
    }

    /**
     * 每次请求插入 1W 条数据
     * @return
     */
    @GetMapping("/insert")
    public R insert(){
        return R.ok().setData(batchSave2(10000L));
    }

    public Long batchSave2(Long number) {
        Instant start = Instant.now();
        List<Student1> studentList = randomStudent.generateStudent2(number);
        studentService.saveBatch(studentList);
        Instant finish = Instant.now(); // 记录查询结束时间
        Duration timeElapsed = Duration.between(start, finish);
        return timeElapsed.getSeconds();
    }



    @GetMapping("/qps")
    public R qps(){

//        List<Integer> list = Arrays.asList(10000, 100000,300000,1000000,2000000);
        List<Integer> list = Arrays.asList(2000000);

        List<CompletableFuture<Qps>> futures = list.stream()
                .map(item -> CompletableFuture.supplyAsync(() -> {

                    Qps progress = new Qps();
                    double time = studentService.getStudents1(item);
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

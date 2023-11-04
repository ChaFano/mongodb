package com.chafan.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



/**
 * @Auther: 茶凡
 * @ClassName IndexController
 * @date 2023/10/29 22:08
 * @Description 页面跳转
 */

@Controller
public class IndexController {

    @GetMapping("/failover")
    public String failover(){
        return "failover.html";
    }

    @GetMapping("/index")
    public String index(){
        return "index.html";
    }

    @GetMapping("/write")
    public String write(){
        return "write.html";
    }

    @GetMapping("/read")
    public String read(){
        return "read.html";
    }


}

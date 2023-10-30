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

    @GetMapping("/findPage")
    public String findPage(){
        return "findPage.html";
    }

    @GetMapping("/index")
    public String index(){
        return "index.html";
    }



}

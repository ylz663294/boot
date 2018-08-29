package com.yang.boot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class IndexController {
    @RequestMapping("/index")
    public String index(){
        log.error("error happen!!");
        log.warn("worn happen!!");
        log.info("info happen!");
        return "hello springBoot";
    }
}

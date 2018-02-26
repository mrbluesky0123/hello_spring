package com.skcc.start.part.common.controller;

import com.skcc.start.part.common.service.MyTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mytest")
public class MyTestController {

    @Autowired
    MyTestService myTestService;

    @GetMapping
    public String getMyTest(){

        return myTestService.getMyTest();

    }


}

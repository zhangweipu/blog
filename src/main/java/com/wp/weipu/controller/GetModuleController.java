package com.wp.weipu.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zwp
 */

@CrossOrigin
@RestController
public class GetModuleController {

    private static final Logger logger= LoggerFactory.getLogger(GetModuleController.class);

    @PostMapping(value = "/dc_longfor/modularPool/insertModularsPool")
    public String insertModularsPool(@RequestBody String jsonData){
        System.out.printf(jsonData);
        return "{\"mCode\":\"200\",\"message\":\"aaa\"}";
    }
}

package com.wp.weipu.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

/**
 * @author zwp
 */
@Service
@Order(1)
public class StartDemo implements ApplicationRunner{
    private static final Logger logger= LoggerFactory.getLogger(StartDemo.class);


    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("------------------------------------");
        logger.info("kaishi--------------------------------------------------------------");
        logger.error("error");
        logger.debug("debug");
    }
}

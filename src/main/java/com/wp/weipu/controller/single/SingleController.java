package com.wp.weipu.controller.single;

import com.wp.weipu.common.utils.JsonToObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class SingleController {
    public static final Logger logger = LoggerFactory.getLogger(SingleController.class);

    @PostMapping("/single")
    public String singleindex(@RequestBody String str) {
        System.out.println(str);
        logger.info("request");
        return "single";
    }
}

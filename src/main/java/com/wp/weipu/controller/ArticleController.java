package com.wp.weipu.controller;

import com.wp.weipu.common.base.ResultBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zwp
 */
@RestController
public class ArticleController {

    private static final Logger logger= LoggerFactory.getLogger(ArticleController.class);

    public ResultBean add(){

        return new ResultBean();
    }

}

package com.wp.weipu.controller.single;

import com.wp.weipu.common.base.ResultBean;
import com.wp.weipu.common.utils.JsonToObject;
import com.wp.weipu.entity.Article;
import com.wp.weipu.service.IArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/single")
public class SingleController {
    public static final Logger logger = LoggerFactory.getLogger(SingleController.class);
    @Autowired
    IArticleService articleService;

    @PostMapping("/index")
    public String singleindex(@RequestBody String str) {
        System.out.println(str);
        logger.info("request");
        return "single";
    }
    @RequestMapping(value = "/getSentence/{id}", method = RequestMethod.GET)
    public ResultBean<Article> serchArticle(@PathVariable Integer id) {
        return new ResultBean<>(articleService.sercheById(id));
    }
}

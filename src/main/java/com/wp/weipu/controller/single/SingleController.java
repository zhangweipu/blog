package com.wp.weipu.controller.single;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.wp.weipu.common.base.ResultBean;
import com.wp.weipu.common.utils.JsonToObject;
import com.wp.weipu.entity.Article;
import com.wp.weipu.service.IArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    /**
     * 获取详细的文章内容
     *
     * @param id
     * @return
     */
    @PostMapping(value = "/getArticle/{id}")
    public ResultBean getArticle(@PathVariable int id) {
        logger.info("获取完整文章");
        Article article = articleService.sercheById(id);

        return new ResultBean(article);
    }

    /***
     * 获取文章的所有文章实现分页吧
     * @return
     */
    @PostMapping(value = "/searchArticle")
    public ResultBean searchArticle(@RequestBody String pageNum) {
        //todo:这里进行了字符串的转化，应该有直接传一个值的方法
        JSONObject jso = JSONObject.parseObject(pageNum);
        int num = jso.getInteger("pageNum");
        PageInfo<Article> pageInfo = articleService.searchByPage(num);
        return new ResultBean(pageInfo);
    }

}

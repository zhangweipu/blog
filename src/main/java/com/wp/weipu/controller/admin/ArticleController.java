package com.wp.weipu.controller.admin;

import com.wp.weipu.common.base.BaseException;
import com.wp.weipu.common.base.ResultBean;
import com.wp.weipu.dto.ArticleDto;
import com.wp.weipu.dto.ConvertMapper;
import com.wp.weipu.entity.Article;
import com.wp.weipu.service.IArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

/**
 * @author zwp
 */
@CrossOrigin
@RestController
@RequestMapping("/article")
public class ArticleController {
    private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    IArticleService articleService;

    @PostMapping(value = "/add")
    public ResultBean add(@RequestBody @Valid ArticleDto articleDto, Errors errors) throws BaseException {
        System.out.println(articleDto);
        if (errors.hasErrors()) {
            throw new BaseException(errors.toString());
        }
        Article article = ConvertMapper.INSTANCE.ArticleDtoToArticle(articleDto);
        System.out.println(article.getContent());
        article.setUserid("admin");
        article.setCreatetime(new Date());
        article.setOutline("a");
        article.setImageurl("aaa");
        article.setType(1);
        articleService.addArticleNormal(article);
        return new ResultBean();
    }

    @RequestMapping(value = "/getSentence/{id}", method = RequestMethod.GET)
    public ResultBean<Article> serchArticle(@PathVariable Integer id) {
        return new ResultBean<>(articleService.sercheById(id));
    }

}

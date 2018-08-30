package com.wp.weipu.controller;

import com.wp.weipu.common.base.BaseException;
import com.wp.weipu.common.base.ResultBean;
import com.wp.weipu.dto.ArticleDto;
import com.wp.weipu.dto.ConvertMapper;
import com.wp.weipu.entity.Article;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author zwp
 */
@CrossOrigin
@RestController
public class ArticleController {
    private static final Logger logger= LoggerFactory.getLogger(ArticleController.class);

    @PostMapping(value = "/admin/addArticle")
    public ResultBean add(@RequestBody @Valid ArticleDto articleDto, Errors errors) throws BaseException {
        if (errors.hasErrors()){
            throw new BaseException(errors.toString());
        }
        Article article= ConvertMapper.INSTANCE.ArticleDtoToArticle(articleDto);
        System.out.println(article.getContent());
        return new ResultBean();
    }

}

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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;
import java.util.Date;

/**
 * @author zwp
 */
@CrossOrigin
@RestController
@RequestMapping("/admin/article")
public class ArticleController {
    private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    IArticleService articleService;

    /**
     * 添加内容
     *
     * @param articleDto
     * @param errors
     * @return
     * @throws BaseException
     */
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

    /**
     * 转存图片
     *
     * @param Base64Str
     */
    private String saveImage(String Base64Str, String filePath) {
        if (Base64Str == null) {
            return null;
        }
        //对字符串编码
        byte[] bytes = Base64.getDecoder().decode(Base64Str);
        try {
            OutputStream out = new FileOutputStream(filePath);
            out.write(bytes);
            out.flush();
            out.close();
        } catch (IOException e) {
            //转换图片异常
            e.printStackTrace();
        }
        return null;
    }


}

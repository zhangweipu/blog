package com.wp.weipu.service.impl;

import com.wp.weipu.entity.Article;
import com.wp.weipu.mapper.ArticleMapper;
import com.wp.weipu.service.IArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

/**
 * @author zwp
 * @version 1.0
 * @description:
 * @date 2018/4/30
 */
@Service
public class ArticleServiceImpl implements IArticleService {
    public static final Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class);

    @Autowired
    ArticleMapper articleMapper;

    @Override
    public void addArticleNormal(Article article) {
        logger.info("save article");
        articleMapper.insertSelective(article);
    }

    @Override
    public void addArticleByWord(Article article, InputStream inputStream) {

    }

    @Override
    public List<Article> searchAll() {
        return null;
    }

    @Override
    public Article searchByCondition(Article article) {
        logger.info("查询" + article.getId());
        return articleMapper.selectByPrimaryKey(article.getId());
    }

    @Override
    public Article sercheById(Integer id) {
        logger.info("查询" + id);
        return articleMapper.selectByPrimaryKey(id);
    }
}

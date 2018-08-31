package com.wp.weipu.service.impl;

import com.wp.weipu.entity.Article;
import com.wp.weipu.service.IArticleService;

import java.io.InputStream;
import java.util.List;

/**
 * @author zwp
 * @version 1.0
 * @description:
 * @date 2018/4/30
 */
public class ArticleServiceImpl implements IArticleService{
    @Override
    public void addArticleNormal(Article article) {

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
        return null;
    }
}

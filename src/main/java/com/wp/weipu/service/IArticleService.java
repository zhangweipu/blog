package com.wp.weipu.service;

import com.github.pagehelper.PageInfo;
import com.wp.weipu.entity.Article;

import java.io.InputStream;
import java.util.List;

/**
 * @author zwp
 * @version 1.0
 * @description:
 * @date 2018/4/30
 */

public interface IArticleService {
    /**
     * 一般情况下的文章上传，网页编写
     *
     * @param article
     */
    void addArticleNormal(Article article);

    /**
     * @param article     文章信息
     * @param inputStream 文件流
     */
    void addArticleByWord(Article article, InputStream inputStream);

    /**
     * 查询所有文件
     *
     * @return
     */
    List<Article> searchAll();

    /**
     * 根据条件查询
     *
     * @return
     */
    Article searchByCondition(Article article);

    /**
     * 根据条件查询
     *
     * @param id
     * @return
     */
    Article sercheById(Integer id);

    /**
     * 通过页码查询
     * @param pageNum
     * @return
     */
    PageInfo<Article> searchByPage(int pageNum);
}

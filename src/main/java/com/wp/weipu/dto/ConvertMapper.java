package com.wp.weipu.dto;

import com.wp.weipu.entity.Article;
import org.mapstruct.MapMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.TargetType;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ConvertMapper {
    ConvertMapper INSTANCE = Mappers.getMapper(ConvertMapper.class);

    public Article ArticleDtoToArticle(ArticleDto articleDto);

    ArticleDto ArticleDtoFromArticle(Article article,@MappingTarget ArticleDto articleDto);
}
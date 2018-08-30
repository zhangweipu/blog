package com.wp.weipu.dto;

import javax.validation.constraints.NotNull;

public class ArticleDto {

    @NotNull
    private String title;
    private String source;
    @NotNull
    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

package com.wp.weipu.test;

import java.util.List;

/**
 * @author zwp
 */

public class LevelText {

    private String title;

    private String content;

    private String other;

    private List<LevelText> children;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public List<LevelText> getChildren() {
        return children;
    }

    public void setChildren(List<LevelText> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"title\":\"")
                .append(title).append('\"');
        sb.append(",\"content\":\"")
                .append(content).append('\"');
        sb.append(",\"other\":\"")
                .append(other).append('\"');
        sb.append(",\"children\":")
                .append(children);
        sb.append('}');
        return sb.toString();
    }
}

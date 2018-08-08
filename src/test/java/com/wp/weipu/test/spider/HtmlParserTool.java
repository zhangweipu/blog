package com.wp.weipu.test.spider;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zwp
 */

public class HtmlParserTool {
    private static final Logger logger= LoggerFactory.getLogger(HtmlParserTool.class);

    /**
     * 获取一个网站上的链接，filter用来过滤链接
     * @param url
     * @param filter
     * @return
     */
    public static Set<String> extracLinks(String url,LinkFilter filter){
        Set<String> links=new HashSet<>();
        try {
            Parser parser=new Parser(url);
            parser.setEncoding("gb2312");
            NodeFilter frameFilter=new NodeFilter() {
                @Override
                public boolean accept(Node node) {
                    if(node.getText().startsWith("frame src=")){
                        return true;
                    }else {
                        return false;
                    }
                }
            };
            //orFilter来设置过滤<a>标签和<frame>标签
            OrFilter linkFilter=new OrFilter(new NodeClassFilter(LinkTag.class),frameFilter);
            //得到所有经过过滤的标签
            NodeList list=parser.extractAllNodesThatMatch(linkFilter);
            for (int i=0;i<list.size();i++){
                Node tag=list.elementAt(i);
                //<a>标签
                    if(tag instanceof LinkTag){
                        LinkTag link=(LinkTag) tag;
                        String linkUrl=link.getLink();
                        if (filter.accept(linkUrl)){
                            links.add(linkUrl);
                        }
                    }else {
                        //<frame>标签
                        String frame=tag.getText();
                        int start=frame.indexOf("src=");
                        frame=frame.substring(start);
                        int end=frame.indexOf(" ");
                        if (end == -1){
                            end=frame.indexOf(">");
                        }
                        String frameUrl=frame.substring(5,end-1);
                        if (filter.accept(frameUrl)){
                            links.add(frameUrl);
                        }
                    }
            }

        } catch (ParserException e) {
            logger.error("转换链接出现异常",e);
        }
        return links;
    }
}

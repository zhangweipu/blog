package com.wp.weipu.test.spider;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;

/**
 * 对某个网站的url的数据进行过滤
 * @author zwp
 */

public interface LinkFilter{
    boolean accept(String node);
}

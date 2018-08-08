package com.wp.weipu.test.spider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.Set;

/**
 * 爬虫的主类
 * @author zwp
 */

public class MyCrawler {

    private static final Logger logger= LoggerFactory.getLogger(MyCrawler.class);

    /**
     * 初始化url队列
     * @param seeds
     */
    private void initCrawlerWithSeeds(String[] seeds){
        for (int i=0;i<seeds.length;i++){
            LinkQueue.addUnVisitedUrl(seeds[i]);
        }
    }

    /**
     * 抓取过程
     * @param seeds
     */
    public void crawling(String[] seeds,String url){
        //定义过滤器 提取以http://www.baidu.com 开头的链接
        LinkFilter linkFilter=new LinkFilter() {
            @Override
            public boolean accept(String node) {
                if(node.startsWith(url)){
                    return true;
                }else {
                    return false;
                }
            }
        };

        logger.info("爬取的url"+url);

        //初始化url队列
        this.initCrawlerWithSeeds(seeds);
        //循环条件，待抓取的url不为空且总数不大于1000
        while (!LinkQueue.unVisitedUrlisEmpty() && LinkQueue.getVisitedNum()<1000){
            logger.info("进入循环下载");
            //对头url出列
            String visitUrl= (String) LinkQueue.unVisitedUrlDeQueue();
            if (StringUtils.isEmpty(visitUrl)){
                continue;
            }
            DownLoadFile downLoadFile=new DownLoadFile();
            //下载文件
            downLoadFile.downloadFile(visitUrl);
            //加入已经访问的队列
            LinkQueue.addVisitedUrl(visitUrl);
            //提取网页中的链接
            Set<String> links=HtmlParserTool.extracLinks(visitUrl,linkFilter);
            //新为访问的地址
            for (String i:links){
                logger.info("新的url"+i);
                LinkQueue.addUnVisitedUrl(i);
            }
        }
    }

    public static void main(String[] args) {
        MyCrawler crawler=new MyCrawler();
        crawler.crawling(new String[]{"http://tech.163.com/18/0808/08/DOM4P2V500097U7T.html"},"");
    }
}

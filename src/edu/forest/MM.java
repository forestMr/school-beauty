package edu.forest;

import java.util.ArrayList;
import java.util.List;

/**
 * @deprecated 生成需要爬取的url
 * {@link MyForkJoinPool}
 * @version 1.0
 * @anthor zsl on 2019/6/11
 * @since jdk8
 */
public class MM {

    private List<String> urls;

    private final static String URL_DEMO = "https://www.duodia.com/daxuexiaohua";

    public MM() {
        init();
    }

    /**
     * @deprecated  初始化所有的url
     */
    private void init(){
        urls = new ArrayList<>();
        urls.add(URL_DEMO);
        for(int i = 2; i <= 315; i++){
            String url = URL_DEMO+"/list_"+i+".html";
            urls.add(url);
            LoggerUtil.loggerUtil().info("初始化："+url);
        }

        MyForkJoinPool myForkJoinPool = new MyForkJoinPool(urls);
        myForkJoinPool.execute();
    }
}

package edu.forest;

import java.util.List;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

/**
 * @deprecated 线程池处理业务
 * {@link Reptile}
 */
public class MyForkJoinPool {


    private List<String>  urls;

    public MyForkJoinPool(List<String> urls) {
        this.urls = urls;
    }

    /**
     * @deprecated 开始执行逻辑
     */
    public void execute(){

        Task task = new Task(this.urls);
        // 创建一个通用池，这个是jdk1.8提供的功能
        java.util.concurrent.ForkJoinPool pool = java.util.concurrent.ForkJoinPool.commonPool();
        pool.submit(task);
        try {
            pool.awaitTermination(2,TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pool.shutdown();
    }

    /* 创建人物类*/
    class Task extends RecursiveAction {

        private List<String> urls;

        public Task(List<String> urls) {
            this.urls = urls;
        }

        @Override
        protected void compute() {
            if(urls.size() > 30){
                Task taskLeft = new Task(urls.subList(0,urls.size()/2));
                Task taskRight = new Task(urls.subList(urls.size()/2,urls.size()));
                taskLeft.fork();
                taskRight.fork();
            }else {
                for (String url:
                        urls) {
                    Reptile reptile = Reptile.GetReptile();
                    reptile.execute(url);
                }
            }
        }
    }
}

package edu.forest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @deprecated 具体爬取哪个url。饥饿式。
 * {@link MyForkJoinPool}
 * @version 1.0
 * @anthor zsl on 2019/6/11
 * @since jdk8
 */
public class Reptile {

    private static Reptile reptile = new Reptile();

    /**
     * @deprecated 饥饿式
     * @return Reptile
     */
    public static Reptile GetReptile(){
        return reptile;
    }


    /**
     * 执行入口
     */
    public void execute(String url){
        LoggerUtil.loggerUtil().info("开始爬取："+url);
        try {
            extact(getDocument(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
        LoggerUtil.loggerUtil().info("结束爬取："+url);
    }

    /**
     * 获取网页dom树
     * @param url
     * @return Document
     */
    public  Document getDocument(String url) throws IOException {
        return Jsoup.connect(url).get();
    }

    /**
     * @deprecated 提取图片和地名
     * @param document
     */
    public  void extact(Document document) throws IOException {

        //提取所有的img
        Elements els = document.select("#main > div.row > article > div > a.thumbnail-container > img");

        for (Element e:
                els) {
            isFileExist(e.attr("alt"),e.attr("src"));
        }
    }


    /**
     * 判断文件夹是否存在
     * @param name
     * @param imgSrc
     * @throws IOException
     */
    public  void isFileExist(String name,String imgSrc) throws IOException {

        /*判断文件夹是否存在*/
        String fileUrl = "d:\\\\MM\\"+name.substring(0,2);
        File file = new File(fileUrl);
        if(!file.exists()){
            file.mkdirs();
        }

        /*根据url读取图片的文件流*/
        downloadPicture(fileUrl+"\\"+name+".jpg",imgSrc);

    }


    /**
     * @deprecated 根据url读取文件流并保存在本地
     * @param urlImg
     */
    private  void downloadPicture(String savePath,String urlImg) {
        URL url = null;
        int imageNumber = 0;

        try {
            url = new URL(urlImg);

            //获取输入流
            DataInputStream dataInputStream = new DataInputStream(url.openStream());

            // 创建文件输出流
            FileOutputStream fileOutputStream = new FileOutputStream(new File(savePath));
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;

            while ((length = dataInputStream.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            byte[] context = output.toByteArray();
            fileOutputStream.write(output.toByteArray());
            dataInputStream.close();
            fileOutputStream.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

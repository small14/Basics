package com.sakura.othertest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JsoupTest {


    public static void main(String[] args) throws IOException {


        HashMap<String,String> cookies = new HashMap<String,String>();
        cookies.put("__yadk_uid","04pfaJPLix8J3laqfkvZmu6abJSzxQTS");
        cookies.put("_pk_id.100001.8cb4","a43e09a37da8173d.1535014776.1.1535015540.1535014776.");
        cookies.put("_pk_ref.100001.8cb4","%5B%22%22%2C%22%22%2C1535014776%2C%22https%3A%2F%2Fwww.baidu.com%2Flink%3Furl%3DTCvZ-PWugfemH1i4Vu_XVFQx2tMUBDdHKqM5q9TcQ1OCKOKA35DkOiCVG-6PJQIs%26wd%3D%26eqid%3Df908794500027903000000065b7e7776%22%5D");
        cookies.put("_pk_ses.100001.8cb4","*");
        cookies.put("bid","OpWijaoNiro");

        Document doc = Jsoup.connect("https://www.douban.com/people/souryour/statuses").cookies(cookies).get();

        String titile = doc.title();



        //new-status status-wrapper    saying
        Elements elements = doc.select(".new-status").select(".status-wrapper").select(".saying");

        for (Element element:elements) {
            System.out.println(element.getElementsByTag("p").text());
        }






    }

}

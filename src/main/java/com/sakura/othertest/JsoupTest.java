package com.sakura.othertest;


import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.util.Map;

public class JsoupTest {



    public static void main(String[] args) throws Exception {



        Connection.Response res = Jsoup.connect("https://www.douban.com/accounts/login")
                .data("form_email", "15680507729", "form_password", "tl982697020ls")
                .method(Connection.Method.POST)
                .execute();


        Map<String, String> cookies = res.cookies();
        for (Map.Entry<String, String> entry : cookies.entrySet()) {
            System.out.println(entry.getKey() + "-" + entry.getValue());
        }


        String herf = "https://www.douban.com/people/souryour/statuses";

        while (null != herf){
            herf = test(cookies,herf);
            System.out.println(herf);
        }


    }

    public static String test(Map<String, String> cookies,String href) throws Exception{

//        Document doc = Jsoup.connect(href).cookies(cookies).get();
        Document doc = Jsoup.connect(href).cookie("Cookie","ll=\"118201\"; bid=_KRVfaE3Dgw; _ga=GA1.2.1585118163.1535014780; _gid=GA1.2.77795014.1535014855; push_doumail_num=0; __utmv=30149280.15237; __yadk_uid=04pfaJPLix8J3laqfkvZmu6abJSzxQTS; ps=y; __utmc=30149280; push_noty_num=0; _pk_ref.100001.8cb4=%5B%22%22%2C%22%22%2C1535096932%2C%22https%3A%2F%2Fwww.baidu.com%2Flink%3Furl%3DikBatK_iNN8RevxbrsNusfqh84HC4e1P10r7OUu9gBsG45cS0-wJdze5hLVsfc8V%26wd%3D%26eqid%3De3ce3e9500063581000000065b7fb868%22%5D; _pk_ses.100001.8cb4=*; __utma=30149280.1585118163.1535014780.1535094188.1535096938.4; __utmz=30149280.1535096938.4.4.utmcsr=baidu|utmccn=(organic)|utmcmd=organic; __utmt=1; _gat_UA-7019765-1=1; dbcl2=\"152370236:1OOuMKerxLg\"; ck=Jfgl; ap_v=1,6.0; _pk_id.100001.8cb4=a43e09a37da8173d.1535014776.4.1535096950.1535094210.; __utmb=30149280.7.10.1535096938").get();


        System.out.println(doc.title());
        //new-status status-wrapper    saying
        Elements elements = doc.select(".new-status").select(".status-wrapper").select(".saying");

        for (Element element:elements) {
            System.out.print(element.getElementsByTag("p").text()+"------");
            System.out.println(element.select(".actions").select(".created_at").attr("title"));
        }


        Elements nextPageElements = doc.select(".next");
        StringBuffer nextPageHref = new StringBuffer("https://www.douban.com/people/souryour/statuses");

        if (nextPageElements.size()>0){
            for (Element nextPageElement:nextPageElements){
                Element nextPageLink = nextPageElement.getElementsByTag("a").first();
                if (nextPageLink != null){
                    nextPageHref.append(nextPageLink.attr("href"));
                }else {
                    nextPageHref = null;
                }

            }
        }else {
            nextPageHref = null;
        }
        String newHref = null;
        if (null != nextPageHref){
            newHref  = nextPageHref.toString();
        }else {
            newHref = null;
        }

        return newHref;
    }





}

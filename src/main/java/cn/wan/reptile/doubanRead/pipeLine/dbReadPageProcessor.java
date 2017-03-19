package cn.wan.reptile.doubanRead.pipeLine;

import cn.wan.reptile.doubanRead.po.Book;
import cn.wan.reptile.doubanRead.util.PageUtil;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 万洪基 on 2017/3/19.
 */
public class dbReadPageProcessor implements PageProcessor {

    private String TAG_VIEW = "https://book\\.douban\\.com/tag/.+";
    private String BOOK_VIEW = "https://book\\.douban\\.com/subject/\\d+";

    private Site site =
            Site.me().addHeader("Referer","https://book.douban.com/tag/?view=cloud")
            .addHeader("Cookie","ll=\"118222\"; bid=PES2d6hjTNM; gr_user_id=8decf10c-2035-4095-a11c-4089f26845b3; _pk_ref.100001.3ac3=%5B%22%22%2C%22%22%2C1489886322%2C%22https%3A%2F%2Fwww.douban.com%2F%22%5D; viewed=\"26698660_25862578_3288908\"; _vwo_uuid_v2=DF82936CA71572641275555C8008C219|ee49cc0a324c653b919198dbdf14d243; gr_session_id_22c937bbd8ebd703f2d8e9445f7dfd03=df57021f-b022-4571-910a-df13c86ec323; gr_cs1_df57021f-b022-4571-910a-df13c86ec323=user_id%3A0; __utmt_douban=1; __utmt=1; _pk_id.100001.3ac3=7e945c1c2a05b635.1489844620.2.1489888256.1489844620.; _pk_ses.100001.3ac3=*; __utma=30149280.516022693.1489843509.1489843509.1489886306.2; __utmb=30149280.18.10.1489886306; __utmc=30149280; __utmz=30149280.1489886306.2.2.utmcsr=baidu|utmccn=(organic)|utmcmd=organic; __utma=81379588.1758405426.1489844620.1489844620.1489886322.2; __utmb=81379588.16.10.1489886322; __utmc=81379588; __utmz=81379588.1489886322.2.2.utmcsr=douban.com|utmccn=(referral)|utmcmd=referral|utmcct=/")
            .setRetryTimes(3)
            .setSleepTime(100)
            .setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36");



    public void process(Page page) {

//      图书标签页面
        if (page.getUrl().regex("https://book.douban.com/tag/?view=type").match()) {
            System.out.println("图书标签页面\t"+page.getUrl());
            List<String> strings = page.getHtml().xpath("//table[@class='tagCol']/tbody/tr/td/a/text()").all();
            page.putField("tags", strings);
            System.out.println("tags:\t"+strings);
            page.addTargetRequests(page.getHtml().xpath("//table[@class='tagCol']").links().all());
        }

//      图书列表页面
        if (page.getUrl().regex(TAG_VIEW).match()){
            System.out.println("图书列表页面:\t"+page.getUrl());
            page.addTargetRequests(page.getHtml().xpath("//ul[@class='subject-list']/li/div[@class='pic']").links().all());
            System.out.println(page.getHtml().xpath("//ul[@class='subject-list']/li/div[@class='pic']").links().all());
        }

//      图书详情页面
        if (page.getUrl().regex(BOOK_VIEW).match()){
            System.out.println("图书详情页面\t"+page.getUrl());
            Book book = new Book();
//            书名
            String bookName = page.getHtml().xpath("//div[@id='wrapper']/h1/span/text()").toString();
            book.setBookname(bookName);
//            作者
            String author = page.getHtml().xpath("//div[@id='info']/span/text(2)").toString();
            book.setAuthor(author);

            String infoText = page.getHtml().xpath("//div[@id='info']/allText()").toString();
//            出版日期
            String publishyear = PageUtil.doubanReadRegex(infoText,"出版年:","页数");
            book.setPublishyear(publishyear);
//            页数
            String pages = PageUtil.doubanReadRegex(infoText,"页数:","定价");
            book.setPages(pages);
//            价格
            String price = PageUtil.doubanReadRegex(infoText,"定价:","装帧");
            book.setPrice(price);
//            评分
            String score = page.getHtml().xpath("//strong[@class='ll rating_num ']/text()").toString();
            book.setScore(score);
//            内容简介
            String content = page.getHtml().xpath("//div[@id='link-report']/div/div/tidyText()").toString();
            book.setContent(content);
//            作者简介
            String authorinfo = page.getHtml().xpath("div[@class='intro' and 2]").toString();
            System.out.println("authorinfo:\t"+authorinfo);
            book.setAuthorinfo(authorinfo);
//            所属标签
            String bookTag = "";
            book.setBooktag(1);
            System.out.println("BOOK对象的预览\t"+book);
        }
    }

    public Site getSite() {
        return site;
    }
}

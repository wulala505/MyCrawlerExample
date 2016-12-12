package crawler.example.practice;

import com.github.abola.crawler.CrawlerPack;
import org.apache.commons.logging.impl.SimpleLog;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;


/**
 * 爬蟲包程式練習
 * 
 * @author wulala
 *
 */
public class EtsyItem {
	// commit test
	public static void main(String[] args) {

		// set to debug level
		//CrawlerPack.setLoggerLevel(SimpleLog.LOG_LEVEL_DEBUG);

		// turn off logging
		CrawlerPack.setLoggerLevel(SimpleLog.LOG_LEVEL_OFF);

		// 遠端資料路徑
		String uri = "https://www.etsy.com/search?q=ykk+zippers&order=most_relevant&view_type=gallery&explicit=1&page=1";

		Document doc=CrawlerPack.start().getFromHtml(uri);
		//int sum=Integer.parseInt(doc.select("span[aria-hidden=true]").text());

		String output = "title,sName,currency,\n";
		//已知頁數為250，翻頁爬蟲
		for (int i = 1; i < 250; i++){
			String everypageurl="https://www.etsy.com/search?q=ykk+zippers&order=most_relevant&view_type=gallery&explicit=1&page="+i;

			try {
				Document document = Jsoup.connect(everypageurl).timeout(50000).userAgent("bbbb").get();
			} catch (IOException e) {
				e.printStackTrace();
			}

			String title = doc.select("div[class=card-meta-row-item card-title selected-color]").text();
			String sName = doc.select(".card-shop-name.currency").text();
			String currency = doc.select("span[class=currency text-smaller]").text();

			output += title+",\""+sName+"\","+currency+"\n";
		}
		System.out.println( output );

	}
}

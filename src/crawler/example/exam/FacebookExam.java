package crawler.example.exam;

import com.github.abola.crawler.CrawlerPack;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


/**
 * 練習：取得任一篇或多篇文章的 Reactions 總數
 *
 *
 * 重點
 * 1. 先利用Graph Api調整出需要的資料
 * 2. 修改程式，使用爬蟲包取得資料
 * 3. 上傳至GitHub
 * 
 * @author Abola Lee
 *
 */
public class FacebookExam {
	
	public static void main(String[] args) {
		
		// 遠端資料路徑

		String uri = 
				"https://graph.facebook.com/v2.8"
				+ "/crazyck101/posts?fields=id,link,message,created_time,reactions.type(HAHA).limit(0).summary(total_count)&since=1480824365&until=1480856400&locale=zn_tw"
				+ "&access_token=EAACEdEose0cBAKlW9iWmaTPsgbnDwIPiF7PZCUPd3pIti4RcgGrIPi5MHv8lQT0SZCFnaIyjFP0IUP38UxlT2lvM5yVEozDgPsUIrPP36jGGHT4SYRP58atOwWBzeznxzauhmDxs3Stw1J7PYZAvq6BpLVQv3SSgYlh33N10QZDZD";
		//

		Elements elems =
				CrawlerPack.start()
				.getFromJson(uri)
				.select("data");
		
		String output = "id,created_time,reactions";

		// 遂筆處理
		for( Element data: elems ){
			String id = data.select("id").text();
			String created_time = data.select("created_time").text();
			String reactions = data.select("reactions total_count").text();
			// css selector (空白:底下皆選(any))
			// FIXIT
			//String reactions = "haha";


			output += id + "," + created_time + "," + reactions + "\n";
		}

		System.out.println( output );
	} 
}

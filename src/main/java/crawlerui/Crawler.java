package crawlerui;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

import java.util.regex.Pattern;
import java.util.Set;


public class Crawler extends WebCrawler{
	
	private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg|png|mp3|mp4|zip|gz|pdf))$");
	static int numberOfPagesVisited = 0;

	@Override
    public boolean shouldVisit(Page referringPage, WebURL url){
		String href = url.getURL().toLowerCase();
		boolean result = !FILTERS.matcher(href).matches();
		if(result)
			System.out.println("Should visit: "+href);
		else
			System.out.println("Should not visit: "+href);
		return result;
	}
	
	@Override
	public void visit(Page page){
		String url = page.getWebURL().getURL();
		numberOfPagesVisited++;
		
		if (page.getParseData() instanceof HtmlParseData){
			HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();      
            String text = htmlParseData.getText().toLowerCase(); //extract text from page
            //String html = htmlParseData.getHtml(); //extract html from page
            String titleContent = htmlParseData.getTitle().toLowerCase();
            Set<WebURL> links = htmlParseData.getOutgoingUrls();
            int linkSize = links.size();
            System.out.println("Page URL: "+url+" -- Text length: "+text.length()+" -- Number of outgoing links: "+linkSize+ " -- Title: "+titleContent+"\n");
		}
	}
}

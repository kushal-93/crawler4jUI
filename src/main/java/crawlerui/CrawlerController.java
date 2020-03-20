package crawlerui;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

//import java.util.Scanner;

import javax.swing.JTextArea;



public class CrawlerController {
	
	public void crawlControl(String seedUrl, String storageLocation, int numOfThreads, int maxDepth, int maxPagesFetch, int politenessVal, JTextArea resultArea) throws Exception{
		
		int MAX_CRAWL_DEPTH = maxDepth;
        int NUMBER_OF_CRAWELRS = numOfThreads;
        String CRAWL_STORAGE = storageLocation;
        int MAX_PAGES_FETCH = maxPagesFetch;
        int POLITENESS_DELAY = politenessVal;
        String SEED_URL = seedUrl;
        
        System.out.println("+++++++++++++++++inside crawler controller");
        System.out.println("depth: "+MAX_CRAWL_DEPTH+" max page: "+MAX_PAGES_FETCH+" url: "+SEED_URL);
        System.out.println("++++++++++++++++++++++++++++++++++++++++++");
        
        //TO DO:   pass required input to Crawler class.
        
        /*
         * Instantiate crawl config
         */
        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(CRAWL_STORAGE);
        config.setMaxDepthOfCrawling(MAX_CRAWL_DEPTH);
        config.setPolitenessDelay(POLITENESS_DELAY);
        config.setMaxPagesToFetch(MAX_PAGES_FETCH);
        config.setIncludeBinaryContentInCrawling(false);
        
        /*
         * Instantiate controller for this crawl.
         */
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);
        
        /*
         * Add seed URLs
         */
        controller.addSeed(SEED_URL);
 
        /*
         * Start the crawl and record time.
         */
        long timeStart = System.currentTimeMillis();
        Crawler.numberOfPagesVisited = 0;
        controller.start(Crawler.class, NUMBER_OF_CRAWELRS);
        long timeEnd = System.currentTimeMillis();
        System.out.print("=================Crawling completed================\n");
        String crawlTime = "Time taken: "+((timeEnd-timeStart)/1000)+" seconds\n";
        System.out.print(crawlTime);
        String pageVisitedNum = "Number of pages visited: "+Crawler.numberOfPagesVisited+"\n";
        System.out.print(pageVisitedNum);
        resultArea.setText("==================End of crawler================\n"+crawlTime+pageVisitedNum);
        
	}
	
}

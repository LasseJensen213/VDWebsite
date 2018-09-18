package dataFetcher;

import dto.RSS.RSSFeedDTO;
import dto.RSS.RSSMessageDTO;

public class RSSTest {
	public static void main(String[] args) {
        RSSFeedParser parser = new RSSFeedParser(
                "http://vejdirektoratet.dk/DA/trafik/_layouts/listfeed.aspx?List=%7B1D802CEA-FC89-41E9-9AF3-F57A2B1AC688%7D&Source=http%3A%2F%2Fvejdirektoratet%2Edk%2FDA%2Ftrafik%2FLists%2Fbreakinqnews%2FAllItems%2Easpx");
        RSSFeedDTO feed = parser.readFeed();
        System.out.println(feed);
        for (RSSMessageDTO message : feed.getMessages()) {
            System.out.println(message);

        }

    }
}

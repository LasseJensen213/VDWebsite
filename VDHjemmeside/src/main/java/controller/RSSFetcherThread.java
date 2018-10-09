package controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import com.lasse.RestService.BackendResource;

import dataFetcher.RSSFeedParser;
import dataFetcher.SituationHandler;
import dataFetcher.Values;
import dto.RSS.RSSFeedDTO;
import dto.datex.SituationDTO;
import interfaces.IObserver;
import interfaces.ISubject;

public class RSSFetcherThread extends Thread implements ISubject{


	//	d = new RSSFetcherThread();
	//How long the server has to wait before it fetches new notifications. 
	private long interval;

	//List of notifications
	private RSSFeedDTO feed;

	//For the user to force the update
	private boolean forceUpdate;

	//list of obsevers
	List<IObserver> observerList;


	//URL for RSS feed
	private final String RSSURL = "http://vejdirektoratet.dk/DA/trafik/_layouts/listfeed.aspx?List=%7B1D802CEA-FC89-41E9-9AF3-F57A2B1AC688%7D&Source=http%3A%2F%2Fvejdirektoratet%2Edk%2FDA%2Ftrafik%2FLists%2Fbreakinqnews%2FAllItems%2Easpx";



	public RSSFetcherThread(long interval) {
		this.interval = interval;
		this.observerList = new ArrayList<>();
		this.forceUpdate = false;

	}



	@Override
	public void run() {

		try {
			updateWarning();
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long t1 = System.currentTimeMillis();
		long diff;
		while(true) {
			diff = System.currentTimeMillis() - t1;
			
			//Check if the thread is forced to update
			if(forceUpdate) {
				try {
					updateWarning();
				} catch (ParserConfigurationException | SAXException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				forceUpdate = false;
				t1 = System.currentTimeMillis();	//Reset timer.
				continue;

			}


			//Check if it's time to fetch new notifications
			if(diff > interval) {
				t1 = System.currentTimeMillis();
				//System.out.println("Fetch stuff");
				try {
					updateWarning();
					Logger.getLogger(RSSFetcherThread.class.getName()).log(Level.INFO,"Fetching information: " + this.getName());

				} catch (ParserConfigurationException | SAXException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else {
				try {
					//Sleep so the thread isn't running 24/7
					RSSFetcherThread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}






		}
	}










	public void updateWarning() throws ParserConfigurationException, SAXException, MalformedURLException, IOException {
		//Create stuff
		RSSFeedParser parser = new RSSFeedParser(this.RSSURL);
		this.feed = parser.readFeed();
        
		//We have new information. Inform the controller
		notifyObsevers();


	}


	public void setForceUpdate(boolean forceUpdate) {
		this.forceUpdate = forceUpdate;
	}







	/**
	 * @return the feed
	 */
	public RSSFeedDTO getFeed() {
		return feed;
	}



	@Override
	public void attach(IObserver observer) {
		observerList.add(observer);
	}

	@Override
	public void detach(IObserver obsever) {
		observerList.remove(obsever);
	}

	@Override
	public void notifyObsevers() {
		for(IObserver o : observerList) {
			o.update(IObserver.UpdateValues.WARNING);
		}

	}




}

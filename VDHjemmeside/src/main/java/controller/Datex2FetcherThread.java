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

import dataFetcher.SituationHandler;
import dataFetcher.Values;
import dto.datex.SituationDTO;
import interfaces.IObserver;
import interfaces.ISubject;

public class Datex2FetcherThread extends Thread implements ISubject{

	//How long the server has to wait before it fetches new notifications. 
	private long interval;
	
	//List of notifications
	private List<SituationDTO> notifications;

	//For the user to force the update
	private boolean forceUpdate = true;

	//list of obsevers
	List<IObserver> observerList;



	public Datex2FetcherThread(long interval) {
		this.interval = interval;
		this.observerList = new ArrayList<>();
		this.notifications = new ArrayList<>();
		this.forceUpdate = false;
		
	}



	@Override
	public void run() {
		
		try {
			updateNotifications();
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		long t1 = System.currentTimeMillis();
		long diff;
		while(true) {
			diff = System.currentTimeMillis() - t1;
			
			
			//Check if the thread is forced to update
			if(forceUpdate) {
				try {
					updateNotifications();
				} catch (ParserConfigurationException | SAXException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				forceUpdate = false;
				t1 = System.currentTimeMillis();	//Reset timer.
				continue;
				
			}
			
			
			//Check if it's time to fetch new notifications
			//Logger.getLogger(Datex2FetcherThread.class.getName()).log(Level.INFO,"Diff: " + diff + " , interval: " + interval);

			if(diff > interval) {
				t1 = System.currentTimeMillis();
				try {
					updateNotifications();
				} catch (ParserConfigurationException | SAXException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else {
				try {
					//Sleep so the thread isn't running 24/7
					Datex2FetcherThread.sleep(1000);
					//Logger.getLogger(Datex2FetcherThread.class.getName()).log(Level.INFO,"Going to sleep");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
			
			
			
			
		}
	}
	
	
	
	
	
	
	
	
	
	
	public void updateNotifications() throws ParserConfigurationException, SAXException, MalformedURLException, IOException {
		//Create stuff
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		SAXParser saxParser = saxParserFactory.newSAXParser();
        SituationHandler handler = new SituationHandler();
        
        //Fetch and handle Datex2 xml file from website. 
        saxParser.parse(new URL(Values.NAPURL).openStream(), handler);
        
        //Get the notifications. 
        notifications = handler.getSituationList();
        
        //We have new information. Inform the controller
        notifyObsevers();
        
       
	}
	
	
	
	
	
	
	
	

	public List<SituationDTO> getNotifications() {
		return notifications;
	}
	public void setForceUpdate(boolean forceUpdate) {
		this.forceUpdate = forceUpdate;
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
			o.update(IObserver.UpdateValues.NOTIFICATION);
		}
		
	}
	
	

}

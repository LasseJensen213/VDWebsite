package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import dataFetcher.RSSFeedParser;
import dto.RSS.RSSFeedDTO;
import dto.RSS.RSSMessageDTO;
import dto.datex.SituationDTO;
import dto.datex.SituationRecordDTO;
import dto.datex.SituationRecordType;
import dto.datex.WarningDTO;
import interfaces.IObserver;

public class Controller implements IObserver{
	
	
	//Singleton class
	private static Controller single_instance = null;
	
	

	
	
	
	//General information we need to have the site running
	//Datex information
	private List<SituationDTO> notifications = new ArrayList<>();
	
	//RSS information
	RSSFeedParser parser;
	RSSFeedDTO RSSFeed;
	
	
	private Datex2FetcherThread notificationThread;
	private RSSFetcherThread warningThread;
	
	//How often the server should update the notifications and warnings (milliseconds)
	private long interval = 30000;

	
	
	
	
	
	
	
	
	private Controller() {
		//Logger.getLogger(Controller.class.getName()).log(Level.INFO,"Calling private constructor");

		
		//Initialize threads
		notificationThread = new Datex2FetcherThread(interval);
		warningThread = new RSSFetcherThread(interval);
		
		//Attach controller to threads.
		notificationThread.attach(this);
		warningThread.attach(this);
		
		//Start threads
		notificationThread.start();
		warningThread.start();
	}






	public static Controller getInstance() {
		//Logger.getLogger(Controller.class.getName()).log(Level.INFO,"Creating controller");

		if(single_instance == null) {
			single_instance = new Controller();
			
		}
		return single_instance;
	}
	
	
	
	
	
	
	@Override
	public void update(UpdateValues value) {
		switch(value) {
		case NOTIFICATION:
			boolean containsMaintenance = false;
			for(SituationDTO dto : notificationThread.getNotifications()) {
				for(SituationRecordDTO type : dto.getSituationRecordList()) {
					if(type.getType() == SituationRecordType.MaintenanceWorks) {
						containsMaintenance = true;
					}
					
				}
				if(!containsMaintenance) {
					notifications.add(dto);
				}
				containsMaintenance = false;
			}
			//notifications = notificationThread.getNotifications();
			
			break;
		case WARNING:
			this.RSSFeed = warningThread.getFeed();
			break;
		default:
			break;
		
		
		}
		
	}




	public List<SituationDTO> getNotifications() {
		return notifications;
	}

	public List<RSSMessageDTO> getWarnings() {
		if(this.RSSFeed == null) {
			return new ArrayList<>();
		}
		return this.RSSFeed.getMessages();
	}
	
	
	
	
	


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

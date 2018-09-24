package dto.server;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import interfaces.IPersistency;
import rest.model.RestStateLevel;

public class Server implements IPersistency{

	private ArrayList<StateLevelDTO> data = new ArrayList<>();
	private String DateSplitAt = "-";
	private String TimeSplitAt = ":";



	@Override
	public void insertElement(RestStateLevel restStateLevel) {
		Logger.getLogger(Server.class.getName()).log(Level.INFO,restStateLevel.toString());
		
		LocalDateTime timePoint;
		
		String[] date = restStateLevel.getDate().split(DateSplitAt);
		String[] time = restStateLevel.getTime().split(TimeSplitAt);
		
		int year = Integer.parseInt(date[0]);
		int month = Integer.parseInt(date[1]);
		int dayOfMonth = Integer.parseInt(date[2]);
		int hour = Integer.parseInt(time[0]);
		int minute = Integer.parseInt(time[1]);

		timePoint = LocalDateTime.of(year, month, dayOfMonth, hour, minute);
		
		
		
		StateLevelDTO dto = new StateLevelDTO(timePoint, StateLevel.fromString(restStateLevel.getStatelevel()));
		data.add(dto);
		
		
	}

	@Override
	public void deleteElement(RestStateLevel restStateLevel) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getTimeSpendInState(StateLevel stateLevel, String startDateTime, String endDateTime) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTotalTimeSpendInState(StateLevel stateLevel) {
		// TODO Auto-generated method stub
		return 0;
	}






}

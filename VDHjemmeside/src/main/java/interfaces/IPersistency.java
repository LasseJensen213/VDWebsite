package interfaces;

import dto.server.StateLevel;
import rest.model.RestStateLevel;

public interface IPersistency {
	
	void insertElement(RestStateLevel restStateLevel);
	void deleteElement(RestStateLevel restStateLevel);
	int getTimeSpendInState(StateLevel stateLevel, String startDateTime, String endDateTime);
	int getTotalTimeSpendInState(StateLevel stateLevel);

}

package interfaces;

import dto.server.StateLevel;
import dto.server.StateLevelDTO;
import rest.model.RestStateLevel;
import rest.model.RestUserText;

public interface IPersistency {
	
	void insertElement(RestStateLevel restStateLevel);
	void deleteElement(RestStateLevel restStateLevel);
	int getTimeSpendInState(StateLevel stateLevel, String startDateTime, String endDateTime);
	int getTotalTimeSpendInState(StateLevel stateLevel);
	void saveUserText(RestUserText restUserText);
	RestUserText getUserText();
	StateLevelDTO getStateLevel();

}

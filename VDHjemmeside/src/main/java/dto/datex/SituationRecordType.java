package dto.datex;

public enum SituationRecordType {
	AbnormalTraffic,
	Accident,
	CarParks,
	ConstructionWorks,
	MaintenanceWorks,
	NonWeatherRelatedRoadConditions,
	PoorEnvironmentConditions,
	RoadOrCarriagewayOrLaneManagement,
	WeatherRelatedRoadConditions,
	SpeedManagement,
	PublicEvent,
	GeneralObstruction,
	GeneralNetworkManagement,
	ReroutingManagement,
	Conditions,
	VehicleObstruction,
	EnvironmentalObstruction,
	type404;
	
	
	
	
	
	
	
	
	
	
	public static SituationRecordType fromString(String from) throws IllegalArgumentException{
	    for (SituationRecordType type: SituationRecordType.values()) {
	        if (type.toString().startsWith(from)) {
	            return type;
	        }
	    }
	    return SituationRecordType.type404; 
	    
	    //throw new IllegalArgumentException( from );
	
	}
	
	
	
	
	
}

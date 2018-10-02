package dto.server;

public enum StateLevel {
	NORMAL_STATE_LEVEL,
	RAISED_STATE_LEVEL,
	HIGH_STATE_LEVEL,
	NULL_LEVEL;

	
	
	
	
	
	
	
	
	
	
	
	
	public static StateLevel fromString(String from) throws IllegalArgumentException{
	    for (StateLevel type: StateLevel.values()) {
	        if (type.toString().startsWith(from)) {
	            return type;
	        }
	    }
	    
	    throw new IllegalArgumentException( from );
	
	}
	
	
	
}

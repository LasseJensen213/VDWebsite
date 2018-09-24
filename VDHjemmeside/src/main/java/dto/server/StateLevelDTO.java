package dto.server;

import java.time.LocalDateTime;

public class StateLevelDTO {
	
	private LocalDateTime dateTime;
	private StateLevel statelevel;

	
	
	
	
	
	
	
	public StateLevelDTO(LocalDateTime dateTime, StateLevel statelevel) {
		super();
		this.dateTime = dateTime;
		this.statelevel = statelevel;
	}
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	public StateLevel getStatelevel() {
		return statelevel;
	}
	public void setStatelevel(StateLevel statelevel) {
		this.statelevel = statelevel;
	}





}



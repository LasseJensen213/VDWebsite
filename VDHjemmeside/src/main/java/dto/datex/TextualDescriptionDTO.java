package dto.datex;

public class TextualDescriptionDTO {
	private String messageText = null;
	private String locationText = null;
	private String eventText = null;
	private String regionText = null;
	private String diversionRouteText = null;
	private String nonCodeableInformationText = null;
	private String language = null;
	
	
	
	
	public String getMessageText() {
		return messageText;
	}
	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}
	public String getLocationText() {
		return locationText;
	}
	public void setLocationText(String locationText) {
		this.locationText = locationText;
	}
	public String getEventText() {
		return eventText;
	}
	public void setEventText(String eventText) {
		this.eventText = eventText;
	}
	public String getRegionText() {
		return regionText;
	}
	public void setRegionText(String regionText) {
		this.regionText = regionText;
	}
	public String getDiversionRouteText() {
		return diversionRouteText;
	}
	public void setDiversionRouteText(String diversionRouteText) {
		this.diversionRouteText = diversionRouteText;
	}
	public String getNonCodeableInformationText() {
		return nonCodeableInformationText;
	}
	public void setNonCodeableInformationText(String nonCodeableInformationText) {
		this.nonCodeableInformationText = nonCodeableInformationText;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	
	
	
	
	@Override
	public String toString() {
		return "TextualDescriptionDTO [messageText=" + messageText + ", locationText=" + locationText + ", eventText="
				+ eventText + ", regionText=" + regionText + ", diversionRouteText=" + diversionRouteText
				+ ", nonCodeableInformationText=" + nonCodeableInformationText + ", language=" + language + "]";
	}

	
	
	
	
}

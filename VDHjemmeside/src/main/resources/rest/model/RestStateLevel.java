package rest.model;

public class RestStateLevel {
	
	private String date;
	private String time;
	private String statelevel;
	
	
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getStatelevel() {
		return statelevel;
	}
	public void setStatelevel(String statelevel) {
		this.statelevel = statelevel;
	}
	@Override
	public String toString() {
		return "RestStateLevel [date=" + date + ", time=" + time + ", statelevel=" + statelevel + "]";
	}
	



	

}
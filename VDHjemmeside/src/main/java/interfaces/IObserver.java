package interfaces;

public interface IObserver {
	
	public enum UpdateValues {NOTIFICATION, WARNING}
	
	
	void update(UpdateValues value);
	

}

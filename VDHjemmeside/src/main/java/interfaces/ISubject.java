package interfaces;

public interface ISubject {
	
	
	void attach(IObserver observer);
	void detach(IObserver obsever);
	void notifyObsevers();
	
	

}

package dataPersistence;

import dto.server.Server;

public class LocalPersistence {
	
	private Server serverDTO;

	public LocalPersistence() {
		super();
		serverDTO = new Server();
	}

	
	
	public Server getServerDTO() {
		return serverDTO;
	}

	public void setServerDTO(Server serverDTO) {
		this.serverDTO = serverDTO;
	}
	
	
	
	
	
	

}

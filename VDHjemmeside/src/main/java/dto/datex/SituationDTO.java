package dto.datex;

import java.util.List;

public class SituationDTO {
	
	private String situationID;
	
	private List<SituationRecordDTO> situationRecordList;
	private SituationExtensionDTO situationExtensionDTO;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public boolean equalTo(SituationDTO dto) {
		return this.situationID.equals(dto.situationID);
	}
	
	
	public String getSituationID() {
		return situationID;
	}
	public void setSituationID(String situationID) {
		this.situationID = situationID;
	}
	public List<SituationRecordDTO> getSituationRecordList() {
		return situationRecordList;
	}
	public void setSituationRecordList(List<SituationRecordDTO> situationRecordList) {
		this.situationRecordList = situationRecordList;
	}
	public SituationExtensionDTO getSituationExtensionDTO() {
		return situationExtensionDTO;
	}
	public void setSituationExtensionDTO(SituationExtensionDTO situationExtensionDTO) {
		this.situationExtensionDTO = situationExtensionDTO;
	}


	@Override
	public String toString() {
		return "SituationDTO [situationID=" + situationID + ", situationRecordList=" + situationRecordList;
	}
	
	
	
	
	
	
	
	
	
	
	

}

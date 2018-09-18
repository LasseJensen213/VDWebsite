package dataFetcher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import dto.datex.SituationDTO;
import dto.datex.SituationExtensionDTO;
import dto.datex.SituationRecordDTO;
import dto.datex.SituationRecordType;
import dto.datex.TextualDescriptionDTO;



public class SituationHandler extends DefaultHandler{

	
	//Information about the situation
	private List<SituationDTO> situationList = null;
	private List<String> idMap = new ArrayList<>();
	private int iterator = 0;

	private SituationDTO situation= null;
	
	//Information about the situation record
	private List<SituationRecordDTO> situationRecordList = null;
	private SituationRecordDTO situationRecord = null;
	
	//Information about the situation extension. 
	private SituationExtensionDTO situationExtension= null;
	
	//Textual description. 
	//List of textualdescriptions. The list shall be added to 1 situationExtension
	private List<TextualDescriptionDTO> textualDescriptionDTOList = null;
	private TextualDescriptionDTO textualDescriptionDTO = null;
	
	
	

	//Used to collect information from different values. 
	private StringBuilder content = new StringBuilder();
	
	
	@Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException{
		if(qName.equalsIgnoreCase("situation")) {
			//We met a new situation.
			situation = new SituationDTO();
			String id = attributes.getValue("id");
			situation.setSituationID(id);
			situationRecordList = new ArrayList<>();
			iterator++;
			



			//Check to see if it's the first element in the XML file. If it is, create a new list. 
			if(situationList == null) {
				situationList = new ArrayList<>();
			}
			
			
		} else if(qName.equalsIgnoreCase("situationRecord")) {
			//We met a new situationExtension. Initialize new DTO's. 
			situationRecord = new SituationRecordDTO();
			String type = attributes.getValue("xsi:type");
			try {
			situationRecord.setType(SituationRecordType.fromString(type));
			}
			catch(IllegalArgumentException e) {
				e.printStackTrace();
				situationRecord.setType(SituationRecordType.fromString("type404"));

			}

		}else if(qName.equalsIgnoreCase("situationExtension")) {
			iterator++;
			//We met a new situationExtension. Initialize new DTO's. 
			situationExtension = new SituationExtensionDTO();
			
	
		} else if(qName.equalsIgnoreCase("textualDescriptionsOfSituation")) {
			//We met a new situation extension. Therefore create new list. 
			textualDescriptionDTOList = new ArrayList<>();
			
		} else if(qName.equalsIgnoreCase("textualDescriptions")) {
			//We met a new textualDescription. initialize object
			textualDescriptionDTO = new TextualDescriptionDTO();
			
			//The textual description has a language. Following assigns it to the textual description
			String language = attributes.getValue("lang");
			if(language == null) {
				language = "en-US";
			}
			textualDescriptionDTO.setLanguage(language);
			
		} else if(qName.equalsIgnoreCase("messageText")) {
			content.setLength(0);
		} else if(qName.equalsIgnoreCase("locationText")) {
			content.setLength(0);
		} else if(qName.equalsIgnoreCase("eventText")) {
			content.setLength(0);
		} else if(qName.equalsIgnoreCase("DiversionRouteText")) {
			content.setLength(0);	
		} else if(qName.equalsIgnoreCase("NonCodeableInformationText")) {
			content.setLength(0);
		} else if(qName.equalsIgnoreCase("regionText")) {
			content.setLength(0);
		}
		
		
		
	}
	
	@Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
		
		//Do we already have the ID
		if(qName.equalsIgnoreCase("situation")) {
			for(String s : idMap) {
				if(s.equals(situation.getSituationID())) {
					System.out.println("GET OUT");
					return;
				}
			}
		
			//We met the end tag of the situation. We should therefore add it to the list.
			situation.setSituationRecordList(situationRecordList);
			situationList.add(situation);
			idMap.add(situation.getSituationID());
			
		} else if(qName.equalsIgnoreCase("situationRecord")) {
			//We met the end tag of the situationrecord. We should therefore add it to the list.
			situationRecordList.add(situationRecord);
		} else if(qName.equalsIgnoreCase("situationExtension")) {
			//If the textual description starts with the location text, remove the location text from the description.
			String description;
			String locationtext;
			String startChar;
			for(int i = 0;i<situationExtension.getTextualDescriptionList().size();i++) {
				description = situationExtension.getTextualDescriptionList().get(i).getMessageText();
				locationtext = situationExtension.getTextualDescriptionList().get(i).getLocationText();
				//Check to see if the description contains the locationtext
				if(description == null || locationtext == null) {
					continue;
				}
				//Check to see if we actually have to replace the text.
				if(description.startsWith(locationtext)) {
					//Replace the text
					description = description.replace(locationtext, "");
					description = description.trim();
					
					//Set the first letter to upper case
					startChar = description.substring(0, 1).toUpperCase();
					description = startChar + description.substring(1);
					situationExtension.getTextualDescriptionList().get(i).setMessageText(description);
					
				}

			}
			
			//We met the end tag of the situationExtension. We should therefore add it to the list.
			situation.setSituationExtensionDTO(situationExtension);
			
			

		} else if(qName.equalsIgnoreCase("textualDescriptionsOfSituation")) {
			//End tag of textualDescriptionsOfSituation met. Add the textualDescriptionsOfSituation to the situationExtension. 
			situationExtension.setTextualDescriptionList(textualDescriptionDTOList);
			
		} else if(qName.equalsIgnoreCase("textualDescriptions")) {
			//End tag of textualDescriptions. Add the textualDescriptions to the list of textualDescriptionsOfSituation
			textualDescriptionDTOList.add(textualDescriptionDTO);
			
		} else if(qName.equalsIgnoreCase("messageText")) {
			textualDescriptionDTO.setMessageText(content.toString());
		} else if(qName.equalsIgnoreCase("locationText")) {
			textualDescriptionDTO.setLocationText(content.toString());
		} else if(qName.equalsIgnoreCase("eventText")) {
			textualDescriptionDTO.setEventText(content.toString());
		} else if(qName.equalsIgnoreCase("DiversionRouteText")) {
			textualDescriptionDTO.setDiversionRouteText(content.toString());
		} else if(qName.equalsIgnoreCase("NonCodeableInformationText")) {
			textualDescriptionDTO.setNonCodeableInformationText(content.toString());
		} else if(qName.equalsIgnoreCase("regionText")) {
			textualDescriptionDTO.setRegionText(content.toString());
		}
	}
	
	
	@Override
    public void characters(char ch[], int start, int length) throws SAXException {
		//Set the text 
		content.append(ch, start, length);
	}

	public List<SituationDTO> getSituationList() {
		return situationList;
	}

	public int getIterator() {
		return iterator;
	}
	/*
	 * StringBuilder sBuilder = new StringBuilder();
		if(bMessageText) {	
			for(char c : ch) {
				sBuilder.append(c);
			}
			int doubleSpace = sBuilder.indexOf("   ");
			sBuilder.delete(doubleSpace, sBuilder.length());
			
			textualDescriptionDTO.setMessageText(sBuilder.toString());
			bMessageText = false;
		} else if(bLocationText) {
			for(char c : ch) {
				sBuilder.append(c);
			}
			int doubleSpace = sBuilder.indexOf("   ");
			sBuilder.delete(doubleSpace, sBuilder.length());

			textualDescriptionDTO.setLocationText(sBuilder.toString());
			bLocationText = false;
		} else if(bEventText) {
			for(char c : ch) {
				sBuilder.append(c);
			}
			int doubleSpace = sBuilder.indexOf("   ");
			sBuilder.delete(doubleSpace, sBuilder.length());

			textualDescriptionDTO.setEventText(sBuilder.toString());
			bEventText = false;
		} else if(bDiversionRouteText) {
			for(char c : ch) {
				sBuilder.append(c);
			}
			int doubleSpace = sBuilder.indexOf("   ");
			sBuilder.delete(doubleSpace, sBuilder.length());

			textualDescriptionDTO.setDiversionRouteText(sBuilder.toString());
			bDiversionRouteText = false;
		} else if(bNonCodeableInformationText) {
			for(char c : ch) {
				sBuilder.append(c);
			}
			int doubleSpace = sBuilder.indexOf("   ");
			sBuilder.delete(doubleSpace, sBuilder.length());

			textualDescriptionDTO.setNonCodeableInformationText(sBuilder.toString());
			bNonCodeableInformationText = false;
		} else if(bRegionText) {
			for(char c : ch) {
				sBuilder.append(c);
			}
			int doubleSpace = sBuilder.indexOf("   ");
			sBuilder.delete(doubleSpace, sBuilder.length());

			textualDescriptionDTO.setRegionText(sBuilder.toString());
			bRegionText = false;
		}

	 * 
	 */


	
	
	 
	
	
	
	

}

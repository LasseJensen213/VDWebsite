package dataFetcher;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import dto.datex.SituationDTO;
import dto.datex.SituationExtensionDTO;

public class SaxTest {

	public static void main(String[] args) {
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		try {
			SAXParser saxParser = saxParserFactory.newSAXParser();
			SituationHandler handler = new SituationHandler();
			saxParser.parse(new URL(Values.DATEX2URL).openStream(), handler);
			//Get Employees list
			List<SituationDTO> situationList = handler.getSituationList();
			//print employee information

			System.out.println("Length: " + situationList.size());
			System.out.println("Iterator: " + handler.getIterator());
			int i = 0;
			int j = 0;
			for(SituationDTO dto : situationList) {
				j = 0;
				for(SituationDTO dto2 : situationList) {
					if(j==i) {
						continue;
					}
					if(dto.equalTo(dto2)) {
						System.out.println("ASDF");
						System.out.println("j: " + j + " i: " + i);
					}
					if(dto.getSituationExtensionDTO().getTextualDescriptionList().get(0).getMessageText().equals(dto2.getSituationExtensionDTO().getTextualDescriptionList().get(0).getMessageText())) {
						System.out.println(dto.getSituationID());
						System.out.println(dto2.getSituationID());
						System.out.println("j: " + j + " i: " + i);

					}
					
					
					j++;
				}
				i++;
			}
			System.out.println();
			//  System.out.println(dto);

		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}




	}

}

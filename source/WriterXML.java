package converter;

import java.io.FileOutputStream;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class WriterXML {

	private XMLOutputFactory outputFactory;
	private XMLEventWriter eventWriter;
	private XMLEventFactory eventFactory;
	private XMLEvent end;
	private XMLEvent tab;
	private StartDocument startDocument;
	private StartElement configStartElement;
	
	private String outputFile;

	public void setFile(String outputFile) {
		this.outputFile = outputFile;
	}

	public void writeStart() throws Exception {

		outputFactory = XMLOutputFactory.newInstance();

		eventWriter = outputFactory.createXMLEventWriter(new FileOutputStream(outputFile));

		eventFactory = XMLEventFactory.newInstance();
		end = eventFactory.createDTD("\n");
		tab = eventFactory.createDTD("\t");

		startDocument = eventFactory.createStartDocument();
		eventWriter.add(startDocument);
	}
	
	public void writeEnd() throws XMLStreamException {
		eventWriter.add(eventFactory.createEndDocument());
		eventWriter.close();
	}

	public void createNode(String name, String value) throws XMLStreamException {

		if (value != null && value != "") {
			
			XMLEventFactory eventFactory = XMLEventFactory.newInstance();
	        XMLEvent end = eventFactory.createDTD("\n");
	        XMLEvent tab = eventFactory.createDTD("\t");
	        
	        StartElement sElement = eventFactory.createStartElement("", "", name);
	        eventWriter.add(tab);
	        eventWriter.add(sElement);
	        
	        Characters characters = eventFactory.createCharacters(value);
	        eventWriter.add(characters);
	        
	        EndElement eElement = eventFactory.createEndElement("", "", name);
	        eventWriter.add(eElement);
	        eventWriter.add(end);
		}
	}
	
	public void createStartElement(String tag) throws XMLStreamException {
		configStartElement = eventFactory.createStartElement("", "", tag);
		eventWriter.add(configStartElement);
		eventWriter.add(end);
	}
	
	public void createEndElement(String tag) throws XMLStreamException {
		eventWriter.add(eventFactory.createEndElement("", "", tag));
		eventWriter.add(end);
	}
	
	public void createTab() throws XMLStreamException {
		eventWriter.add(tab);
	}
}

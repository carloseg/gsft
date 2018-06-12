package gsft.model.builder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import gsft.model.VirtualMachine;

public class VirtualMachineBuilder {

	public static VirtualMachine process(String filename) throws ParserConfigurationException, SAXException, IOException {
		
		// creates a file from the filename
		File file = new File(filename);	
		// throws an exception if the file does not exist
		if (! file.exists()) {
			throw new FileNotFoundException(filename + "not found.");
		}
		
		// process the obtained file 
		return process(file);
		
	}
	
	public static VirtualMachine process(File file) throws ParserConfigurationException, SAXException, IOException {
	
		// create a SAX parser for the file
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();
		GSFTHandler gtfsHandler = new GSFTHandler();

		// process the file
		saxParser.parse(file, gtfsHandler);
		
		// obtain the resulting model
		return gtfsHandler.getVM();
		
	}
	
}

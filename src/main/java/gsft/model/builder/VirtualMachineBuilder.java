package gsft.model.builder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import gsft.model.VirtualMachine;

/**
 * Class that build an object structure that represents the data of a virtual machine.
 * 
 */
public class VirtualMachineBuilder {

	/**
	 * Returns a object structure, with a root Virtual Machine instance, that represents 
	 * the disks, images and snapshots of a VirtualBox virtual machine.
	 * @param filename		name of the .vbox file to process
	 * @return				Virtual machine object structure that represents the information 
	 * 						in the .vbox file
	 * @throws ParserConfigurationException	Error processing the XML file
	 * @throws SAXException					Error processing the elements (tags) in the XML file
	 * @throws IOException					Error opening or reading the XML file
	 */
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

	/**
	 * Returns a object structure, with a root Virtual Machine instance, that represents 
	 * the disks, images and snapshots of a VirtualBox virtual machine.
	 * @param file	.vbox File to process 
	 * @return				Virtual machine object structure that represents the information 
	 * 						in the .vbox file
	 * @throws ParserConfigurationException	Error processing the XML file
	 * @throws SAXException					Error processing the elements (tags) in the XML file
	 * @throws IOException					Error opening or reading the XML file
	 */
	public static VirtualMachine process(File file) throws ParserConfigurationException, SAXException, IOException {
	
		// create a SAX parser for the file
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();
		GSFTHandler gtfsHandler = new GSFTHandler();

		// process the file
		saxParser.parse(file, gtfsHandler);
		
		gtfsHandler.getVM().setFilename(file.getCanonicalPath());
		
		// obtain the resulting model
		return gtfsHandler.getVM();
		
	}
	
}

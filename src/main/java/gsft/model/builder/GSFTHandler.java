package gsft.model.builder;

import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import gsft.model.Disk;
import gsft.model.Image;
import gsft.model.Snapshot;
import gsft.model.VirtualMachine;

/**
 * This class is a JAX-SAX handler, used to obtain the information of
 * virtual machines, disks and snapshots from a .vbox XML file.
 *  
 */
public class GSFTHandler extends DefaultHandler {

	/**
	 * Variable that shows if the software has all the data it needs to construct
	 * the virtual machine with all its snapshots.
	 */
	private boolean 		machineReady 	= false;
	
	/**
	 * Virtual machine instance used during processing of the XML
	 */
	private VirtualMachine 	vm 				= null;
	
	private Stack<Image> 	imageStack		= new Stack<Image>();
	
	/**
	 * Disk instance used during processing of the XML
	 */
	private Disk			currentDisk		= null;
	
	/**
	 * Snapshot instance used during processing of the XML
	 */
	private Snapshot 		currentSnapshot = null;

	/**
	 * Snapshot instance created during the process to represent the 
	 * current state of the virtual machine (if the machine state was saved 
	 * instead of turned off) 
	 */
	private Snapshot		lastState		= null;
	
	private boolean 		inMediaRegistry = false;
	private boolean			inADVD			= false;
	
	// Constructors
	// ============
	
	/**
	 * Constructs a JAX-SAX Handler to process the elements in a XML document
	 */
	public GSFTHandler() {
	}

	/**
	 * Method invoked by the JAX-SAX parser each time it detects the beginning of 
	 * an XML element.
	 * 
	 * For processing .vbox files, this method considers the elements for "Machine",
	 * "HardDisk", "Image" and "Snapshot". 
	 * It uses the values of the attribute in these elements to create the corresponding 
	 * instances of the GSFT model.
	 * 
	 * @param uri			URI of the XML element detected by the parser
	 * @param localName		Name of the XML element
	 * @param qName			Qualified name of the detected XML element
	 * @param attributes	attributes of the XML element
	 */
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

		//If the handler reads the tag Machine, enters in this part of the code
		//"Machine" is a tag in the XML file.
		if (qName.equalsIgnoreCase("Machine")) {
			
			// Creates the virtual machine
			// uuid, name, OS, lsc, disk);
			vm = new VirtualMachine(	
						attributes.getValue("uuid"), // .substring(1, uuid.length() - 1),
						attributes.getValue("name"),
						attributes.getValue("OSType"),
						attributes.getValue("lastStateChange"));
			
			// create a Snapshot for current state (if applies)
			lastState = new Snapshot(
						"{current}",
						"{current}",
						attributes.getValue("lastStateChange"),
						attributes.getValue("stateFile"));
						
		}
		
		// If the handler reads the tag HardDisk, enters in this part of the code
		// "HardDisk" is a tag in the XML file.
		else if (qName.equalsIgnoreCase("HardDisk")) { 

			// create a disk image with the information
			Image image = new Image(
					attributes.getValue("uuid"),
					attributes.getValue("location"),
					attributes.getValue("format"),
					(attributes.getValue("type") == null)
					);
			
			// Is is not an snapshot ? 
			if (! image.isSnapshot()) {
				
				// create the corresponding disk
				Disk disk = new Disk(
						attributes.getValue("location"),
						attributes.getValue("format")
						);
				disk.setBaseImage(image);
				
				// add to the virtual machine
				vm.addDisk(disk);
				currentDisk = disk;
				
			}

//			Image lastImage = imageStack.size() == 0 ? null:  imageStack.peek();
//			System.out.println(image.getLocation()
//					+ " parent-> "
//					+ (lastImage == null ? "null" : lastImage.getLocation()));
			
			// set the disk for the image
			image.setDisk(currentDisk);
			// set the parent image
			image.setParent(imageStack.size() == 0 ? null:  imageStack.peek());
			// add the image to the virtual machine
			vm.addImage(image);
		
			// store last processed image
			imageStack.push(image);
			
		}
		
		// If the handler reads the tag Snapshot, enters in this part of the code
		// "Snapshot" is a tag in the XML file.
		else if (qName.equalsIgnoreCase("Snapshot")) {
			
			Snapshot snapshot = new Snapshot(
					attributes.getValue("uuid"),
					attributes.getValue("name"),
					attributes.getValue("timeStamp"),
					attributes.getValue("stateFile"));
			currentSnapshot = snapshot;
			vm.addSnapshot(snapshot);
		}
		
		else if (qName.equalsIgnoreCase("MediaRegistry")) {
			inMediaRegistry = true;
		}
		
		else if (qName.equalsIgnoreCase("AttachedDevice")) {
			if (attributes.getValue("type").equals("DVD")) {
				inADVD = true;
			}
		}
		
		else if (qName.equalsIgnoreCase("Image")) {

			// during the processing of the XML, 
			
			// if the parser is in media registry tag, 
			// the image is a DVD and there is not a current snapshot
			if (inMediaRegistry) {
				
				Image dvd = new Image(
						attributes.getValue("uuid"),
						attributes.getValue("location"),
						"DVD",
						false
						);
				vm.addDVD(dvd);
			}
			
			if (!inMediaRegistry) {
			
				// link detected image to the current snapshot
				String diskUUID = attributes.getValue("uuid");
				Image image = vm.getImage(diskUUID);
				
				// is the image of a disk ? (not a DVD) 
				if (image != null) {				
					if (currentSnapshot != null) {
						currentSnapshot.addImage(image);
					} else {
						lastState.addImage(image);				
					}
				}
			}
		}
	}

	/**
	 * Method invoked by the JAX-SAX parser each time it detects the end of 
	 * an XML element.
	 * 
	 * This method considers the end of the "Snapshot" elements to update the
	 * instance of the current snapshot during the processing of the XML.
	 * 
	 * @param uri			URI of the XML element detected by the parser
	 * @param localName		Name of the XML element
	 * @param qName			Qualified name of the detected XML element
	 */
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equalsIgnoreCase("Snapshot")) {
			if (currentSnapshot != null) {
					vm.setLastSnapShot(currentSnapshot);
			}
			currentSnapshot = null;
			
		} 
		
		else if (qName.equalsIgnoreCase("MediaRegistry")) {
			inMediaRegistry = false;
		}		
		
		else if (qName.equalsIgnoreCase("AttachedDevice")) {
			inADVD = false;
		}		
		
		else if (qName.equalsIgnoreCase("HardDisk")) {
			imageStack.pop();
		}
	}
	
	/**
	 * Method invoked by the JAX-SAX parser when it detects the end of the XML 
	 * document.
	 * 
	 * This method updates the snapshot that represents the current state of the
	 * virtual machine. In addition, it sets the "ready" flag of the virtual machine
	 * to indicate that the processing has ended. 
	 */
	@Override
	public void endDocument() throws SAXException {
		
		// add the last State as the last snapshot
		vm.addSnapshot(lastState);
		
		// flag the machine as ready
		machineReady = true;
	}
	
	/**
	 * Retrieves the virtual machine instance with the data obtained from the 
	 * XML document.
	 * @return	virtual machine instance with the data in the XML.
	 */
	public VirtualMachine getVM() {
		if (machineReady)
			return vm;
		throw new IllegalStateException("Trying to get a VM without parsing a file");
	}
	
}

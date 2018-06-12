package gsft.model.builder;

/**
 * This class allows the parser to get the data from a XML file. 
 * The class consist of three ArrayList. An Snapshots ArrayList, 
 * an HardDisks ArrayList, and an Hard Disks Temp ArrayList. 
 * 
 * The Snapshots ArrayList has all the snapshots of the virtual machine. 
 * The Hard Disks ArrayList has all the .VDI files of the snapshots. 
 * The Hard Disks Temp ArrayList has all the .VDI files, including the 
 * original Hard Disk of the virtual machine. In this ArrayList the .VDI files
 * are saved before the software saves them in the final ArrayList.
 * 
 * @author Carlos Eduardo Gomez Montoya
 * @author David Camilo Bonilla Verdugo
 * @author Harold Enrique Castro Barrera
 */

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import gsft.model.Disk;
import gsft.model.Snapshot;
import gsft.model.VirtualMachine;

public class GSFTHandler extends DefaultHandler {

	/**
	 * Variable that shows if the software has all the data it needs to construct
	 * the virtual machine with all its snapshots.
	 */
	private boolean machineReady = false;
	
	VirtualMachine 	vm;
	Disk			currentDisk;
	Snapshot 		currentSnapshot;
	
	String			lastSnapshotUUID;
	
	
	/**
	 * Constructor of the User Handler
	 * @param pNumSnapshots, pNumSnapshots != null, pNumSnapshots != 0
	 */
	public GSFTHandler() {
	}

	/**
	 * This method extracts all the information from the XML file
	 * and saves it in the different ArrayLists, metadata attributes
	 * and finally, constructs the Virtual Machine with all its attributes
	 * and data.
	 */
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

		//If the handler reads the tag Machine, enters in this part of the code
		//"Machine" is a tag in the XML file.
		if (qName.equalsIgnoreCase("Machine")) {
			
			//Creates the virtual machine
			// uuid, name, OS, lsc, disk);
			vm = new VirtualMachine(	
						attributes.getValue("uuid"), // .substring(1, uuid.length() - 1),
						attributes.getValue("name"),
						attributes.getValue("OSType"),
						attributes.getValue("lastStateChange"));

		}
		
		//If the handler reads the tag HardDisk, enters in this part of the code
		//"HardDisk" is a tag in the XML file.
		else if (qName.equalsIgnoreCase("HardDisk")) { 

			Disk disk = new Disk(
					attributes.getValue("uuid"),
					attributes.getValue("location"),
					attributes.getValue("format"),
					(attributes.getValue("type") == null)
					);
			vm.addDisk(disk);
			
			if (attributes.getValue("type") != null) 
				currentDisk = disk;
			
		}
		
		//If the handler reads the tag Snapshot, enters in this part of the code
		//"Snapshot" is a tag in the XML file.
		else if (qName.equalsIgnoreCase("Snapshot")) {
			
			Snapshot snapshot = new Snapshot(
					attributes.getValue("uuid"),
					attributes.getValue("name"),
					attributes.getValue("timeStamp"),
					attributes.getValue("stateFile"));
			currentSnapshot = snapshot;
			vm.addSnapshot(snapshot);
		}
		
		else if (qName.equalsIgnoreCase("Image")) {
			if (currentSnapshot != null) {
				String diskUUID = attributes.getValue("uuid");
				Disk disk = vm.getDisk(diskUUID);
				currentSnapshot.setDisk(disk);
				currentSnapshot.setBaseDisk(currentDisk);
			}
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equalsIgnoreCase("Snapshot")) {
			currentSnapshot = null;
		}
	}
	
	@Override
	public void endDocument() throws SAXException {
		
		for(Disk disk: vm.getDisks().values()) {
			boolean found = false;
			for(Snapshot snapshot: vm.getSnapshots()) {
				if (snapshot.getDisk() == disk) {
					found = true;
				}
			}
			if (!found) {
				vm.setDisk(disk);
			}
		}
		
		machineReady = true;
	}
	
	public VirtualMachine getVM() {
		if (machineReady)
			return vm;
		throw new IllegalStateException("Trying to get a VM without parsing a file");
	}
	
}

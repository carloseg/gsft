package gsft.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class that represents a Virtual Machine with all its snapshots 
 * 
 */
public class VirtualMachine {

	/**
	 * Metadata of the Virtual Machine
	 */
	public String uuid;
	public String name;
	public String OS;
	public String lastStateChange;

	HashMap<String, Disk> disks = new HashMap<String, Disk>();
	
	HashMap<String, Image> images = new HashMap<String, Image>();
	
	Snapshot	lastSnapShot;
	
	/**
	 * ArrayList of the snapshots the virtual machine has
	 */
	public ArrayList<Snapshot> snapshots = new ArrayList<Snapshot>();

	
	// Constructors
	// ============
	
	/**
	 * Constructs a Virtual Machine object
	 * 
	 * @param pUuid, pUuid != null, pUuid != ""
	 * @param pName, pName != null, pName != ""
	 * @param pOS, pOS != null, pOS != ""
	 * @param pLastStateChange pLastStateChange != null, pLastStateChange != ""
	 */
	public VirtualMachine(String pUuid, 
			String pName, String pOS,
			String pLastStateChange) {
		uuid = pUuid;
		name = pName;
		OS = pOS;
		lastStateChange = pLastStateChange;
	}
	
	
	// Attributes
	// ==========
	
	/**
	 * Allows to add a snapshot to the virtual machine
	 * @param snap	Snapshot to add to the virtual machine
	 */
	public void addSnapshot(Snapshot snap){
		snapshots.add(snap);		
	}

	/**
	 * Allows to delete a snapshot.
	 * @param snap	Snapshot to delete in the virtual machine
	 */
	public void deleteSnapshot(Snapshot snap){
		for (int i = 0; i<snapshots.size();i++)
		{
			if(snapshots.get(i)!=null){
				if(snapshots.get(i) == snap){
					snapshots.remove(i);
				}
			}
		}
	}
	
	/**
	 * Returns the UUID of the VM
	 * @return uuid	Unique id of the Virtual Machine
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * Sets the uuid of the machine
	 * @param uuid	Unique id of the Virtual machine
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	
	// Disks
	// =====
	
	/**
	 * Add a disk to the Virtual Machine
	 * @param disk	Disk to add to the Virtual Machine
	 */
	public void addDisk(Disk disk) {
		disks.put(disk.getUuid(), disk);
	}
	
	/**
	 * Finds a disk in the Virtual Machine by its UUID 
	 * @param uuid	Unique id of the disk to find
	 * @return		Disk of the virtual machine with the specified UUID
	 */
	public Disk getDisk(String uuid) {
		return disks.get(uuid);
	}

	/**
	 * Returns a map with the disks of the Virtual Machine
	 * @return	Map (UUID, Disk instance) with the disks of the Virtual Machine 
	 */
	public HashMap<String, Disk> getDisks() {
		return disks;
	}
	
	
	// Snapshots
	// =========
	
	/**
	 * Returns a list with the snapshots of the virtual machine
	 * @return	list with the snapshots of the virtual machine
	 */
	public ArrayList<Snapshot> getSnapshots() {
		return snapshots;
	}
	
	/**
	 * Retrieves the last snapshot of the virtual machine
	 * @return	last snapshot of the virtual machine
	 */
	public Snapshot getLastSnapShot() {
		return lastSnapShot;
	}
	
	/**
	 * Sets the last snapshot of the virtual machine
	 * @param lastSnapShot	last snapshot of the virtual machine
	 */
	public void setLastSnapShot(Snapshot lastSnapShot) {
		this.lastSnapShot = lastSnapShot;
	}

	
	// Images
	// ======
	
	/**
	 * Returns a map with the images of the virtual machine
	 * @return	Map (UUID, Image) with the data of the images of the virtual machine
	 */
	public HashMap<String, Image> getImages() {
		return images;
	}
	
	/**
	 * Add an Image to the Virtual Machine
	 * @param image	Image to add to the virtual machine
	 */
	public void addImage(Image image) {
		this.images.put(image.getUuid(), image);
	}
	
	/**
	 * Finds an image of the virtual machine by its UUID
	 * @param uuid	Unique id of the image to find
	 * @return		Image of the virtual machine with the specified UUID 
	 */
	public Image getImage(String uuid) {
		return this.images.get(uuid);
	}
	
	/**
	 * returns the name of the virtual machine
	 * @return name	name of the virtual machine
	 */
	public String getName() {
		return name;
	}

	/**
	 * sets the name of the virtual machine
	 * @param name	name of the virtual machine
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * returns the OS of the virtual machine
	 * @return OS
	 */
	public String getOS() {
		return OS;
	}

	/**
	 * sets the OS of the virtual machine
	 * @param oS	OS of the virtual machine
	 */
	public void setOS(String oS) {
		OS = oS;
	}

	/**
	 * returns the last state change of the virtual machine
	 * @return timestamp of the last change of the virtual machine
	 */
	public String getLastStateChange() {
		return lastStateChange;
	}

	/**
	 * sets the last State Change of the virtual machine
	 * @param lastStateChange timestamp of the last change of the virtual machine
	 */
	public void setLastStateChange(String lastStateChange) {
		this.lastStateChange = lastStateChange;
	}


	// Pretty print
	// ============
	
	/**
	 * returns a String with all the information of the virtual machine
	 * including each one of the Snapshots and its information
	 * @return String
	 */
	public String toString() {
		return "{ uuid: \"" + uuid + "\""
				+ ",\n\t name: \"" + name + "\""
				+ ",\n\t os: \"" + OS + "\""
				+ ",\n\t disks: " + disks.values()
				+ ",\n\t snapshots: " + snapshots
				+ "\n}";
	}
	
	
}

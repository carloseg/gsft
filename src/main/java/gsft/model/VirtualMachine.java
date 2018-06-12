package gsft.model;

/**
 * Class that represents a Virtual Machine with all its snapshots 
 * 
 * @author Carlos Eduardo Gomez Montoya
 * @author David Camilo Bonilla Verdugo
 * @author Harold Enrique Castro Barrera
 */
import java.util.ArrayList;
import java.util.HashMap;


public class VirtualMachine {

	/**
	 * Metadata of the Virtual Machine
	 */
	public String uuid;
	public String name;
	public String OS;
	public String lastStateChange;

	HashMap<String, Disk> disks = new HashMap<String, Disk>();
	
	Snapshot	lastSnapShot;
	Disk		disk;
	
	/**
	 * ArrayList of the snapshots the virtual machine has
	 */
	public ArrayList<Snapshot> snapshots = new ArrayList<Snapshot>();

	/**
	 * Constructor of the class.
	 * @param pUuid, pUuid != null, pUuid != ""
	 * @param pName, pName != null, pName != ""
	 * @param pOS, pOS != null, pOS != ""
	 * @param pLastStateChange pLastStateChange != null, pLastStateChange != ""
	 * @param pDisk, pDisk != null, pDisk != ""
	 */
	public VirtualMachine(String pUuid, 
			String pName, String pOS,
			String pLastStateChange) {
		uuid = pUuid;
		name = pName;
		OS = pOS;
		lastStateChange = pLastStateChange;
	}
	
	
	public void addDisk(Disk disk) {
		disks.put(disk.getUuid(), disk);
	}
	
	public Disk getDisk(String uuid) {
		return disks.get(uuid);
	}
	
	/**
	 * Allows to add a snapshot to the virtual machine
	 * @param snap
	 */
	public void addSnapshot(Snapshot snap){
		snapshots.add(snap);		
	}

	/**
	 * Allows to delete a snapshot.
	 * @param snap
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
	 * returns the UUID of the VM
	 * @return uuid
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * sets the uuid of the machine
	 * @param uuid
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Disk getDisk() {
		return disk;
	}
	
	public void setDisk(Disk disk) {
		this.disk = disk;
	}
	
	
	public HashMap<String, Disk> getDisks() {
		return disks;
	}
	
	public ArrayList<Snapshot> getSnapshots() {
		return snapshots;
	}
	
	/**
	 * sets the snapshots of the virtual machine
	 * @param pSnapshots
	 */
	public void setSnapshots(ArrayList<Snapshot> pSnapshots) {
		snapshots = pSnapshots;
	}
	
	public Snapshot getLastSnapShot() {
		return lastSnapShot;
	}
	
	public void setLastSnapShot(Snapshot lastSnapShot) {
		this.lastSnapShot = lastSnapShot;
	}

	/**
	 * returns the name of the snapshot
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * sets the name of the snapshot
	 * @param name
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
	 * @param oS
	 */
	public void setOS(String oS) {
		OS = oS;
	}

	/**
	 * returns the last state change of the virtual machine
	 * @return lastStateChange
	 */
	public String getLastStateChange() {
		return lastStateChange;
	}

	/**
	 * sets the last State Change of the virtual machine
	 * @param lastStateChange
	 */
	public void setLastStateChange(String lastStateChange) {
		this.lastStateChange = lastStateChange;
	}


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

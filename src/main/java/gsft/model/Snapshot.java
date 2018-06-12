package gsft.model;

/**
 * This class represents a snapshot
 * taken  from the virtual machine at some time.
 * 
 * The snapshot has a name, a timeStamp, an UUID and a pair.
 * 
 * @author Carlos Eduardo Gomez Montoya
 * @author David Camilo Bonilla Verdugo
 * @author Harold Enrique Castro Barrera
 *
 */
public class Snapshot {

	/**
	 * Attribute that describes the name of the snapshot
	 */
	private String name;
	
	/**
	 * Attribute that describes the time stamp of the snapshot
	 */
	private String timeStamp;
	
	/**
	 * Attribute that describes the uuid of the snapshot
	 */
	private String uuid;
	
	private String stateFile;

	/**
	 * Attribute that points to the disk used to store the snapshot
	 */
	Disk disk;
	Disk baseDisk;
	
	/**
	 * Constructor of the class Snapshot.
	 * 
	 * @param pUuid, pUuid != null, pUuid != ""
	 * @param pName, pName != null, pName != ""
	 * @param pTimeStamp, pTimeStamp != null, pTimeStamp != ""
	 */
	public Snapshot(String pUuid, String pName, String pTimeStamp, String stateFile) {
		name = pName;
		timeStamp = pTimeStamp;
		uuid = pUuid;
		this.stateFile = stateFile;
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
	 * @param name, name != null, name != ""
	 */
	public void setName(String name) {
		this.name = name;
	}

	public String getStateFile() {
		return stateFile;
	}
	
	public void setStateFile(String stateFile) {
		this.stateFile = stateFile;
	}
	
	/**
	 * returns the time stamp of the snapshot
	 * @return tiemeStamp
	 */
	public String getTimeStamp() {
		return timeStamp;
	}

	/**
	 * sets the time stamp of the snapshot
	 * @param timeStamp, timeStamp != null, timeStamp != ""
	 */
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	/**
	 * returns the UUID of the snapshot
	 * @return uuid
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * sets the UUID of the snapshot
	 * @param uuid, uuid != null, uuid != null
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
	
	public Disk getBaseDisk() {
		return baseDisk;
	}
	
	public void setBaseDisk(Disk baseDisk) {
		this.baseDisk = baseDisk;
	}
	
	/**
	 * returns a String with all the information of the snapshot
	 * @return String
	 */
	public String toString() {
		return "{ uuid : \"" + uuid + "\""
				+ ",\n\t\t name: " + name
				+ ",\n\t\t timestamp: \"" + timeStamp + "\""
				+ ",\n\t\t stateFile: \"" + stateFile + "\""
				+ ",\n\t\t disk.uuid: \"" + disk.getUuid() + "\""
				+ ",\n\t\t baseDisk.uuid: \"" + baseDisk.getUuid() + "\""
				+ "\n\t\t}";
	}
	
	
	
}

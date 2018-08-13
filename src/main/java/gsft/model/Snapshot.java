package gsft.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents a snapshot taken from the virtual machine at some time.
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
	 * Attribute that points to the disk image used to store the snapshot
	 */
	List<Image> images = new ArrayList<Image>();
	
	/**
	 * Disk which is the base of the snapshot
	 */
	Disk baseDisk;
	
	/**
	 * Constructor of the class Snapshot.
	 * 
	 * @param pUuid			Unique id of the snapshot. pUuid != null, pUuid != ""
	 * @param pName 		Name of the snapshot. pName != null, pName != ""
	 * @param pTimeStamp 	Timestamp of the snapshot. pTimeStamp != null, pTimeStamp != ""
	 * @param stateFile		filename with the state of the snapshot.
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

	public void addImage(Image image) {
		images.add(image);
	}
	
	public List<Image> getImages() {
		return images;
	}
	
	public void setImages(List<Image> images) {
		this.images = images;
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
				+ ",\n\t\t images: " + images
				+ "\n\t\t}";
	}
	
	
	
}

package gsft.model;

/**
 * Class that represents the image files used to store disks and snapshots.
 * 
 */
public class Image {

	/**
	 * Image unique identifier
	 */
	String	uuid;
	
	/**
	 * Location of the image
	 */
	String	location;
	
	/**
	 * File format of the image file
	 */
	String	format;
	
	/**
	 * Disk which the image is part of
	 */
	Disk 	disk;
	
	/**
	 * Parent image. 
	 * If the image is part of an snapshot, the image stores incremental changes
	 * to the parent image file.
	 */
	Image	parent = null;
	
	/**
	 * True if the image corresponds to a snapshot 
	 */
	boolean isSnapshot = false;

	// Constructors
	// ============
	
	/**
	 * Constructs an Image object.
	 * 
	 * @param uuid			identifier of the image
	 * @param location		location of the image file
	 * @param format		file format used for the image
	 * @param isSnapshot	true if the image corresponds to a snapshot
	 */
	public Image(String uuid, String location, String format, boolean isSnapshot) {
		super();
		this.uuid = uuid;
		this.location = location;
		this.format = format;
		this.isSnapshot = isSnapshot;
	}


	// Attributes
	// ==========
	
	/**
	 * Retrieves the Base name of the image
	 * @return name of the file used for the image (without directories)
	 */
	public String getBaseName() {

		// actualDisk format in the .vbox file: 
		//    Snapshots/DiskName.vdi

		int lastSeparator = this.location.lastIndexOf("/");
		lastSeparator = (lastSeparator == -1) ? 0 : lastSeparator+1;
		return this.location.substring(lastSeparator);
	}
	
	/**
	 * Retrieves the unique identifier of the Image file
	 * @return	Unique identifier of the image
	 */
	public String getUuid() {
		return uuid;
	}
	
	/**
	 * Sets the unique identifier for the image file
	 * @param uuid	Unique identifier of the image
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	/**
	 * Retrieves the location of the image 
	 * @return	location of the image
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * Sets the location of the image
	 * @param location	location of the image
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * File format of the image
	 * @return	format of the image, e.g. "VDI"
	 */
	public String getFormat() {
		return format;
	}

	/**
	 * Sets the file format of the image
	 * @param format	format of the image
	 */
	public void setFormat(String format) {
		this.format = format;
	}

	/**
	 * Returns true if the image corresponds to a snapshot
	 * @return	true if the image is part of a snapshot
	 */
	public boolean isSnapshot() {
		return isSnapshot;
	}

	/**
	 * Sets the flag that indicates if the image corresponds to a snapshot
	 * @param isSnapshot	true if the image is part of a snapshot
	 */
	public void setSnapshot(boolean isSnapshot) {
		this.isSnapshot = isSnapshot;
	}

	/**
	 * Retrieves the disk which the image is a part
	 * @return 	the disk of the image
	 */
	public Disk getDisk() {
		return disk;
	}
	
	/**
	 * Sets the disk which the image is a part
	 * @param disk	disk of the image
	 */
	public void setDisk(Disk disk) {
		this.disk = disk;
	}
	
	/**
	 * Retrieves the parent image.
	 * @return 	parent image.
	 */
	public Image getParent() {
		return parent;
	}
	
	/**
	 * Sets the parent image
	 * @param parent	parent image
	 */
	public void setParent(Image parent) {
		this.parent = parent;
	}
	
	// Pretty print
	// ============
	
	@Override
	public String toString() {
		return "{ uuid: \"" + uuid + "\""
				+ ",\n\t\t location: \"" + location + "\""
				+ ",\n\t\t format: \"" + format + "\""
				+ "\n\t\t}";
	}
	
}

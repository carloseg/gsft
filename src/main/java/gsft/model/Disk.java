package gsft.model;

import java.util.HashMap;

/**
 * Class that represents each disk of a Virtual Machine.
 */
public class Disk {

	/**
	 * Disk identifier
	 */
	String	uuid;
	
	/**
	 * File format used to store the disk data
	 */
	String	format;
	
	/**
	 * Base Image. First image for the disk
	 */
	Image baseImage;
	
	/**
	 * Images for this disk (including base and snapshot images)
	 */
	HashMap<String,Image> images = new HashMap<String, Image>();
	
	
	// Constructors
	// ============
	
	/**
	 * Constructs a Disk object.
	 * 
	 * @param uuid			Unique id of the disk.
	 * @param format		File Format of the disk
	 */
	public Disk(String uuid, String format) {
		super();
		this.uuid = uuid;
		this.format = format;
	}
	
	/**
	 * Retrieves the base name of the disk file
	 * @return name of the file used for the disk (without directories)
	 */
	public String getBaseName() {
		if (baseImage == null) 
			return "Error: base image not set";
		return this.getBaseImage().getBaseName();
	}
	

	// Attributes
	// ==========
	
	/**
	 * Retrieves the Unique id for the disk
	 * @return 	Unique id of the disk
	 */
	public String getUuid() {
		return uuid;
	}
	
	/**
	 * Sets the value of the Unique id for the disk
	 * @param uuid 	Unique id of the disk
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	/**
	 * Retrieves the file format used to store the disk. 
	 * Virtualbox typically uses the "VDI" format
	 * @return 	file format used to store the disk
	 */
	public String getFormat() {
		return format;
	}

	/**
	 * Sets the file format used to store the disk
	 * @param format file format used to store the disk
	 */
	public void setFormat(String format) {
		this.format = format;
	}

	/**
	 * Retrieves the base image of the disk. 
	 * Typically, the base image is the file used to create the virtual machine.
	 * In VirtualBox, all the snapshots stores incremental changes over the base image. 
	 * @return 	base image of the disk
	 */
	public Image getBaseImage() {
		return baseImage;
	}
	
	/**
	 * Sets the base image for the disk
	 * @param baseImage	base image of the disk
	 */
	public void setBaseImage(Image baseImage) {
		this.baseImage = baseImage;
	}
	
	/**
	 * Add an image to the disk. 
	 * This method is used to track all the image files (e.g. those used in snapshots) 
	 * related to each disk.  
	 * @param image	image file to add to the disk	
	 */
	public void addImages(Image image) {
		this.images.put(image.getUuid(), image);
	}
	
	/**
	 * Retrieves the image associated to a disk by its UUID.
	 * @param uuid	unique id of the image to retrieve
	 * @return	image associated to the disk with that UUID. Returns null if the disk is not found. 
	 */
	public Image getImage(String uuid) {
		return this.images.get(uuid);
	}
	
	// Pretty print
	// ============
	
	@Override
	public String toString() {
		return "{ uuid: \"" + uuid + "\""
				+ ",\n\t\t format: \"" + format + "\""
				+ "\n\t\t}";
	}
	
}

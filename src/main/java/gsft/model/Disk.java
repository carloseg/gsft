package gsft.model;

public class Disk {

	String	uuid;
	String	location;
	String	format;
	
	boolean isSnapshot = false;

	/**
	 * Constructor of the class.
	 * 
	 * @param uuid
	 * @param location
	 * @param format
	 * @param isSnapshot
	 */
	public Disk(String uuid, String location, String format, boolean isSnapshot) {
		super();
		this.uuid = uuid;
		this.location = location;
		this.format = format;
		this.isSnapshot = isSnapshot;
	}
	
	/**
	 * Constructor of the class.
	 * 
	 * @param uuid
	 * @param location
	 * @param format
	 */
	public Disk(String uuid, String location, String format) {
		super();
		this.uuid = uuid;
		this.location = location;
		this.format = format;
	}

	public String getBaseName() {

		/*					//actualDisk form: Snapshots/DiskName.vdi
		//			actualDisk.split("/") [0] = Snapshots
		//			actualDisk.split("/") [1] = DiskName.vdi
		String diskToAdd = (actualDisk.split("/")[1]);
		*/

		int lastSeparator = this.location.lastIndexOf("/");
		lastSeparator = (lastSeparator == -1) ? 0 : lastSeparator+1;
		return this.location.substring(lastSeparator);
	}
	

	// ---
	
	public String getUuid() {
		return uuid;
	}
	
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public boolean isSnapshot() {
		return isSnapshot;
	}

	public void setSnapshot(boolean isSnapshot) {
		this.isSnapshot = isSnapshot;
	}

	@Override
	public String toString() {
		return "{ uuid: \"" + uuid + "\""
				+ ",\n\t\t location: \"" + location + "\""
				+ ",\n\t\t format: \"" + format + "\""
				+ "\n\t\t}";
	}
	
}

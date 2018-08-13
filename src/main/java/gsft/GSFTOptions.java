package gsft;

import com.beust.jcommander.Parameter;

public class GSFTOptions {	

	@Parameter(names = {"-migrate"}, description = "Show only the files required to migrate")
	public boolean migrate; 
	
	@Parameter(names = {"-snapshot", "-s"}, description = "-snapshot <name>: Name of the snapshot to process.")
	public String snapshotName;	

	@Parameter(names = {"-file", "-f"}, description = "-file <filename>: Export to a file. Name of the file to produce")
	public String csvFileName;
	
	@Parameter(description = "<.vbox filename>")
	public String filename;
	
}

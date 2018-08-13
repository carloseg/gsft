package gsft;

import com.beust.jcommander.Parameter;

/**
 * Class that represents the command line options for the execution of the GSFT command.
 * 
 * It uses the JCommander library to obtain the values from the command line and to 
 * output help information to the users.
 *
 */
public class GSFTOptions {	

	/**
	 * True if the user wants the list of files required to migrate a virtual machine
	 */
	@Parameter(names = {"-migrate"}, description = "Show only the files required to migrate")
	public boolean migrate; 
	
	/**
	 * Name of the snapshot to process
	 */
	@Parameter(names = {"-snapshot", "-s"}, description = "-snapshot <name>: Name of the snapshot to process.")
	public String snapshotName;	

	/**
	 * Name of the file to produce
	 */
	@Parameter(names = {"-file", "-f"}, description = "-file <filename>: Export to a file. Name of the file to produce")
	public String outputFileName;
	
	/**
	 * Name of the .vbox file to process
	 */
	@Parameter(description = "<.vbox filename>")
	public String filename;
	
}

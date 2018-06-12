package gsft;

import com.beust.jcommander.Parameter;

public class GSFTOptions {	
	
	@Parameter(names = {"-csv"}, description = "<.csv filename>: Export to a CSV file. name of the .csv file to produce")
	public String csvFileName;
	
	@Parameter(description = "<.vbox filename>")
	public String filename;
	
}

package gsft;

import com.beust.jcommander.JCommander;

import gsft.model.VirtualMachine;
import gsft.model.builder.VirtualMachineBuilder;
import gsft.utils.CSVWriter;

public class GSFT {

	public static void main(String[] args) {
		
		GSFTOptions opts = new GSFTOptions();				
		JCommander commands = JCommander.newBuilder()
			.addObject(opts)
			.args(args)
			.build();

		VirtualMachine vm = null;
				
		// if the filename is omitted
		if (opts.filename == null) {
			System.err.println("You must provide a .vbox file to process");
			commands.setProgramName("GSFT");
			commands.usage();
			System.exit(0);
		}

		// process the .vbox file
		try {
			vm = VirtualMachineBuilder.process(opts.filename);
		
		} catch (Exception e) {
			System.err.println("Error processing " + opts.filename + " .vbox file : " + e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}			

		// if the CSV filename was provided
		if (opts.csvFileName != null) {
			try {
				CSVWriter.writeToCSVFile(vm, opts.csvFileName);
				System.out.println("File " + opts.csvFileName + " created");
				
			} catch (Exception e) {
				System.err.println("Error creating " + opts.csvFileName + " .csv file: " + e.getMessage());
				e.printStackTrace();
				System.exit(1);
			}
			
		
		} else {
			try {
				CSVWriter.writeToConsole(vm);
			} catch (Exception e) {
				System.err.println("Error producing output to console : " + e.getMessage());
				e.printStackTrace();
				System.exit(1);
			}
			
		}				
		
		
	}
	

}

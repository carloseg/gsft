package gsft;

import com.beust.jcommander.JCommander;

import gsft.model.VirtualMachine;
import gsft.model.builder.VirtualMachineBuilder;
import gsft.utils.ListAllWriter;
import gsft.utils.ListMigrateFilesWriter;

/**
 * Program that outputs the information of disks and snapshots of an specified virtual machine.
 * 
 * @author Carlos Eduardo Gomez Montoya
 * @author David Camilo Bonilla Verdugo
 * @author Jaime Chavarriaga
 * @author Harold Enrique Castro Barrera
 * @version 0.2-SNAPSHOT
 */
public class GSFT {

	/**
	 * Main method for the GSFT program.
	 * @param args	command line arguments
	 */
	public static void main(String[] args) {
		
		// 1. Obtain the parameters from the command line
		
		GSFTOptions opts = new GSFTOptions();				
		JCommander commands = JCommander.newBuilder()
			.addObject(opts)
			.args(args)
			.build();

		
		// 2. Process the vbox file
		
		VirtualMachine vm = null;
				
		// if the filename is omitted
		if (opts.filename == null) {
			System.err.println("You must provide a .vbox file to process");
			commands.setProgramName("GSFT");
			commands.usage();
			System.exit(0);
		}

		try {
			vm = VirtualMachineBuilder.process(opts.filename);
		
		} catch (Exception e) {
			System.err.println("Error processing " + opts.filename + " .vbox file : " + e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}			

		// 3. Produce the output
		
		// Must the program list all the files ?
		if (! opts.migrate) {
			
			try {
				ListAllWriter.write(vm, opts.csvFileName);
			} catch (Exception e) {
				
				// if the CSV filename was provided
				if (opts.csvFileName != null) {
					System.err.println("Error creating " + opts.csvFileName + " .csv file: " + e.getMessage());
				} else {
					System.err.println("Error producing output to console : " + e.getMessage());
				}
				e.printStackTrace();
				System.exit(1);
			}
							
		// Must the program list only the files to migrate ?
		} else {
			
			try {
				ListMigrateFilesWriter.write(vm, opts.snapshotName, opts.csvFileName);
			} catch (Exception e) {
				
				// if the CSV filename was provided
				if (opts.csvFileName != null) {
					System.err.println("Error creating " + opts.csvFileName + " .csv file: " + e.getMessage());
				} else {
					System.err.println("Error producing output to console : " + e.getMessage());
				}	
				e.printStackTrace();
				System.exit(1);
			}							
		}
		
		
	}
	

}

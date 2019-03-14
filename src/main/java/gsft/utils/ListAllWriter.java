package gsft.utils;

import java.io.IOException;
import java.io.PrintStream;

import gsft.model.Image;
import gsft.model.Snapshot;
import gsft.model.VirtualMachine;

/**
 * Class that writes the data of the hard disks and snapshots in a virtual 
 * machine, in CSV format, to a file or the console.
 */
public class ListAllWriter {
	
	/**
	 * Exports data of the disks and snapshots of a virtual machine. 
	 * It can produce output to a filename or to the console.
	 * 
	 * @param vm			Virtual Machine which disks and snapshots will be exported
	 * @param filename		Name of the output file. if filename == null, it produces the output to console
	 * @throws IOException	Error if the class cannot create the file or write the output
	 */
	public static void write(VirtualMachine vm, String filename) throws IOException {
		
		PrintStream pw; 
		
		// define the print stream for the output
		if (filename != null)
			pw = new PrintStream(filename);
		else
			pw = System.out;
		
		// write the result to the output
		writeToOutput(vm, pw);
		
		// if it is using a file, close the print stream
		if (filename != null)
			pw.close();
	}
	
	
	/**
	 * Internal method that produce the output
	 * @param vm Virtual Machine which data will be exported
	 * @param pw Print Stream to the output (it can be a file or the console)
	 * @throws IOException Error if the class cannot write the output
	 */
	private static void writeToOutput(VirtualMachine vm, PrintStream pw) throws IOException {
		pw.println("Timestamp, Snapshot, Disk, Memory State");
		
		// for all the snapshots
		for(Snapshot snapshot: vm.getSnapshots()) {
			
			// for all the images (disks) 
			for(Image image : snapshot.getImages()) {
				
				// output the information
				pw.println(
						snapshot.getTimeStamp()
						+ ", " + snapshot.getName()
						+ ", " + (image != null ? image.getLocation() : "(not found)") 
						+ ", " + (snapshot != null ? snapshot.getStateFile() : "(not found)")
						);					
			}
		}

	}
	
}

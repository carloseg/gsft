package gsft.utils;

import gsft.model.Image;
import gsft.model.Snapshot;
import gsft.model.VirtualMachine;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Class that writes the files required to migrate a virtual machine to a file or the console.
 */
public class ListFilesWithTypeWriter {
	
	/**
	 * Exports the list of files required to migrate a virtual machine to 
	 * another physical computer or virtual box installation.
	 * 
	 * @param vm			Virtual machine which data will be exporte
	 * @param snapshotname	Name of the snapshot to migrate. if snapshotname == null, it shows the
	 * 						list of files required to migrate all the snapshots.
	 * @param filename		Name of the output file. if filename == null, it produces the output to the console
	 * @throws IOException	Error if the class cannot create the file or write the output
	 */
	public static void write(VirtualMachine vm, String snapshotname, String filename) throws IOException {
		
		if (snapshotname == null)
			snapshotname = vm.getLastSnapShot().getName();
		
		PrintStream pw; 
		
		// define the print stream for the output
		if (filename != null)
			pw = new PrintStream(filename);
		else
			pw = System.out;
		
		// write the result to the output
		writeToOutput(vm, pw, snapshotname);
		
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
	private static void writeToOutput(VirtualMachine vm, PrintStream pw, String snapshotname) throws IOException {
		
		// print titles
		pw.println("Type, Filename, File Size");
		
		// print virtual machine file
		pw.print("vbox, ");
		pw.print(vm.getName() + ".vbox, ");
		pw.println(getFileSize(vm.getFilename()));
		
		Snapshot lastSnapshot = null;
		
		// no snapshot name ?
		if (snapshotname == null) {
		
			// for all the snapshots
			for(Snapshot snapshot: vm.getSnapshots()) {
				
				// ignore the "{current}" snapshot
				if (snapshot != vm.getLastSnapShot()) {		
					// for all the images (disks) 
					for(Image image : snapshot.getImages()) {
						pw.print("disk, ");
						pw.print(image.getLocation() + ", ");
						pw.println(getFileSize(image.getLocation()));
					}
					lastSnapshot = snapshot;
				}

			}

			// show the .sav file for the last snapshot
			pw.print("state, ");
			pw.print(lastSnapshot.getStateFile() + ", ");
			pw.println(getFileSize(lastSnapshot.getStateFile()));
			
			
		// is there a snapshot name ?
		} else {
			
			// determine the selected snapshot 
			Snapshot selectedSnapshot = null;
			for(Snapshot snapshot: vm.getSnapshots()) {
				if (snapshot.getName().equals(snapshotname)) {
					selectedSnapshot  = snapshot;
					break;
				}
			}
			
			// snapshot found ?
			if (selectedSnapshot != null) {
				// for each image
				for (Image image : selectedSnapshot.getImages()) {
					Image imageToDisplay = image;
					// display the image and all its parent images				
					do {
						pw.print("disk, ");
						pw.print(imageToDisplay.getLocation() + ", ");
						pw.println(getFileSize(imageToDisplay.getLocation()));

						imageToDisplay = imageToDisplay.getParent();
					} while (imageToDisplay != null);
					
				}
			}

			pw.print("state, ");
			pw.print(selectedSnapshot.getStateFile() + ", ");
			pw.println(getFileSize(selectedSnapshot.getStateFile()));
		}
		
	}
	
	private static String getFileSize(String filename) {
		File file = new File(filename);
		if (!file.exists()) {
			return "(not found)";
		} else {
			double bytes = file.length();
			double kilobytes = (bytes / 1024);
			if (kilobytes < 1024)
				return "" + kilobytes + " KB";	

			double megabytes = (kilobytes / 1024);
			if (megabytes < 1024)
				return "" + megabytes + " MB";	

			double gigabytes = (megabytes / 1024);
			return "" + gigabytes + " GB";
		}
	}
	
}

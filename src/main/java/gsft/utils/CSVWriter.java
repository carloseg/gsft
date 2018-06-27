package gsft.utils;

import gsft.model.Disk;
import gsft.model.Snapshot;
import gsft.model.VirtualMachine;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

public class CSVWriter {

	/**
	 * Method that creates the CSV file where the name of the snapshot
	 * and the file names are printed. 
	 * @param vm
	 * @param file, file != null, file != ""
	 */
	public static void writeToCSVFile(VirtualMachine vm, String filename) {
		try {

			PrintStream pw = new PrintStream(filename); 
			writeToOutput(vm, pw);
			pw.close();

			// new
			//add -migrate before .csv extension in the filename
			String migrateFileName=filename.substring(0,filename.length()-4);
			migrateFileName+="-migrate.csv";
			PrintStream pw2 = new PrintStream(migrateFileName); 
			writeToOutput2(vm, pw2);
			pw2.close();
	
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	

	public static void writeToConsole(VirtualMachine vm) throws IOException {
		writeToOutput(vm, System.out); 
	}

	private static void writeToOutput(VirtualMachine vm, PrintStream pw) throws IOException {
		pw.println("VM name, timestamp, Snapshot, Disk, Memory State");
		
		// shows all the snapshots
		for(Snapshot snapshot: vm.getSnapshots()) {
			pw.println(vm.getName() 
					+ ", " + snapshot.getTimeStamp()
					+ ", " + snapshot.getName()
					+ ", " + snapshot.getDisk().getLocation()
					+ ", " + snapshot.getStateFile()
					);	
		}
		
		// shows the last state
		for(Disk disk: vm.getDisks().values()) {
			if (! disk.isSnapshot()) {
				pw.println(vm.getName() 
						+ ", " + vm.lastStateChange
						+ ", {current}" 
						+ ", " + vm.getDisk().getLocation() 
						+ ", "
						);
			}
		}
	}

	private static void writeToOutput2(VirtualMachine vm, PrintStream pw) throws IOException {
		//pw.println("List of files to migrate the VM\n");
		
		ArrayList<Snapshot> s = vm.getSnapshots();
		
		pw.println(vm.getName() + ".vbox");
		
		for (int i=0; i<s.size(); i++) {
			pw.println(s.get(i).getDisk().getLocation());
		}
		pw.println(s.get(s.size()-1).getStateFile());
	}

	
	/**
	 * Method that creates and edits the CSV file with the results
	 * @param result, result > 0
	 */
	void createResultsFile(double result) {
/*
		final String NEXT_LINE = "\n";

		BufferedWriter out = null;
		try  
		{
		    FileWriter fw = new FileWriter("results.csv", true); 
		    out = new BufferedWriter(fw);
		    out.write(result+NEXT_LINE);
		}
		catch (IOException e)
		{
		    System.err.println("Error: " + e.getMessage());
		}
		finally
		{
		    if(out != null) {
		        try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		    }
		}
		*/
	}

	
}

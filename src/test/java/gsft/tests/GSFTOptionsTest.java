package gsft.tests;

import org.junit.Test;
import static org.junit.Assert.*;

import com.beust.jcommander.JCommander;

import gsft.GSFTOptions;

/**
 * Class that represents the command line options for the execution of the GSFT command.
 * 
 * It uses the JCommander library to obtain the values from the command line and to 
 * output help information to the users.
 *
 */
public class GSFTOptionsTest {

	@Test
	public void testWithoutParameters() {
		
		String args[]  = {};
	
		GSFTOptions opts = new GSFTOptions();				
		JCommander.newBuilder()
			.addObject(opts)
			.args(args)
			.build();
		
		assertTrue(opts.filename == null);
		assertTrue(opts.csvFileName == null);		
	}
	
	@Test
	public void testWithFilenameOnly() {
		
		String args[]  = { "example.vbox" };
	
		GSFTOptions opts = new GSFTOptions();				
		JCommander.newBuilder()
			.addObject(opts)
			.args(args)
			.build();
		
		assertTrue(opts.filename != null);
		assertTrue(opts.csvFileName == null);		
	}
	
	@Test
	public void testWithBothFileNames() {
		
		String args[]  = { "-csv", "example.csv", "example.vbox" };
	
		GSFTOptions opts = new GSFTOptions();				
		JCommander.newBuilder()
			.addObject(opts)
			.args(args)
			.build();
		
		assertTrue(opts.filename != null);
		assertTrue(opts.filename.equals("example.vbox"));
		
		assertTrue(opts.csvFileName != null);		
		assertTrue(opts.csvFileName.equals("example.csv"));
	}
	
}

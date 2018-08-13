package gsft.tests;

import org.junit.Test;
import static org.junit.Assert.*;

import com.beust.jcommander.JCommander;

import gsft.GSFTOptions;

/**
 * Tests for the management of command line options
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
		assertTrue(opts.outputFileName == null);
		assertTrue(opts.snapshotName == null);
		assertTrue(opts.migrate == false);
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
		assertTrue(opts.outputFileName == null);		
	}
	
	@Test
	public void testWithBothFileNames() {
		
		String args[]  = { "-file", "example.csv", "example.vbox" };
	
		GSFTOptions opts = new GSFTOptions();				
		JCommander.newBuilder()
			.addObject(opts)
			.args(args)
			.build();
		
		assertTrue(opts.filename != null);
		assertTrue(opts.filename.equals("example.vbox"));
		
		assertTrue(opts.outputFileName != null);		
		assertTrue(opts.outputFileName.equals("example.csv"));
	}
	
	@Test
	public void testWithSnapshotNames() {
		
		String args[]  = { "-migrate", "-snapshot", "snapshot1", "-file", "example.csv", "example.vbox" };
	
		GSFTOptions opts = new GSFTOptions();				
		JCommander.newBuilder()
			.addObject(opts)
			.args(args)
			.build();

		assertTrue(opts.migrate == true);

		assertTrue(opts.snapshotName != null);
		assertTrue(opts.snapshotName.equals("snapshot1"));
		
		assertTrue(opts.filename != null);
		assertTrue(opts.filename.equals("example.vbox"));
		
		assertTrue(opts.outputFileName != null);		
		assertTrue(opts.outputFileName.equals("example.csv"));
	}
	
	
}

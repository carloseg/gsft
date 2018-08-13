package gsft.tests;

import org.junit.Test;

import gsft.model.Image;
import gsft.model.Snapshot;
import gsft.model.VirtualMachine;
import gsft.model.builder.VirtualMachineBuilder;

/**
 * Test for checking the correct processing of .vbox files
 *
 */
public class ProcessVboxTest {

	/**
	 * Test a .vbox file with multiple disks
	 * @throws Exception
	 */
	@Test
	public void testWithMultipleDisks() throws Exception {
		
		String filename = ProcessVboxTest.class.getClassLoader()
								.getResource("tests/FreeDOS-1v1.vbox")
								.getFile();
		
		VirtualMachine vm = VirtualMachineBuilder.process(filename);
		System.out.println(vm.getLastStateChange());
		
		for (Snapshot snapshot: vm.getSnapshots()) {
			System.out.println(snapshot.getName());
			
			for (Image image: snapshot.getImages()) {
				System.out.println(image.getBaseName());
			}
		}
		
	}
	
}

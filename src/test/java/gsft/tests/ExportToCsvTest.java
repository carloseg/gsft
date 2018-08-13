package gsft.tests;

import java.io.File;

import org.junit.Test;
import static org.junit.Assert.*;

import gsft.model.VirtualMachine;
import gsft.model.builder.VirtualMachineBuilder;
import gsft.utils.ListAllWriter;

public class ExportToCsvTest {

	@Test
	public void testExampleVBox() {
		
		// 1. load a .vbox file from the /src/test/resources
		String filename = "example/example.vbox";
		ClassLoader classLoader  = getClass().getClassLoader();
		File inputFile = new File(classLoader.getResource(filename).getFile());
		
		// 2. process the file
		try {
			
			VirtualMachine vm = VirtualMachineBuilder.process(inputFile);			
			System.out.println(vm.getUuid());
			System.err.println(vm);
			
			// write to console
			ListAllWriter.write(vm, null);
		
		// 3. fail the test if there is an exception
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
	}

	@Test
	public void testExampleVBoxWithTwoDisks() {
		
		// 1. Load a .vbox file with two disks and multiple snapshots
		String filename = "tests/FreeDOS-1v1.vbox";
		ClassLoader classLoader  = getClass().getClassLoader();
		File inputFile = new File(classLoader.getResource(filename).getFile());
		
		// 2. Process the virtual machine 
		try {
			
			VirtualMachine vm = VirtualMachineBuilder.process(inputFile);			
			System.out.println(vm.getUuid());
			System.err.println(vm);
			
			// write to console
			ListAllWriter.write(vm, null);
			
		// 3. fail if there is an error
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
	}
	
}

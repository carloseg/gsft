package gsft.tests;

import java.io.File;

import org.junit.Test;
import static org.junit.Assert.*;

import gsft.model.VirtualMachine;
import gsft.model.builder.VirtualMachineBuilder;
import gsft.utils.CSVWriter;

public class ExportToCsvTest {

	@Test
	public void testExampleVBox() {
		
		String filename = "example/example.vbox";
		ClassLoader classLoader  = getClass().getClassLoader();
		
		File inputFile = new File(classLoader.getResource(filename).getFile());
		
		try {
			
			VirtualMachine vm = VirtualMachineBuilder.process(inputFile);			
			System.out.println(vm.getUuid());
			System.err.println(vm);
			
			CSVWriter.writeToConsole(vm);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail(e.getMessage());
		}
		
	}
	
}

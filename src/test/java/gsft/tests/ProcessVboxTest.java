package gsft.tests;

import org.junit.Test;
import static org.junit.Assert.*;

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
		
		// Get the FreeDOS-1v1.vbox file
		String filename = ProcessVboxTest.class.getClassLoader()
								.getResource("tests/FreeDOS-1v1.vbox")
								.getFile();
		
		// Process the file
		VirtualMachine vm = VirtualMachineBuilder.process(filename);
		
		// Check the obtained values
		assertTrue(vm.getLastStateChange() != null);
		assertFalse(vm.getLastStateChange().equals(""));
		
		// Check the number of disks
		assertTrue(vm.getDisks().size() == 2);
		
		// Check the number of snapshots
		assertTrue(vm.getSnapshots().size() == 5);
		
	}
	
	@Test
	public void testWithMultipleDisks2() throws Exception {
		
		// Get the FreeDOS-1v1.vbox file
		String filename = ProcessVboxTest.class.getClassLoader()
								.getResource("tests/FreeDOS-1v2.vbox")
								.getFile();
		
		// Process the file
		VirtualMachine vm = VirtualMachineBuilder.process(filename);
		
		// Print the obtained values
		System.out.println("Data for testWithMultipleDisks2()");
		System.out.println("last change : "+ vm.getLastStateChange());
		for (Snapshot snapshot: vm.getSnapshots()) {
			System.out.println(snapshot.getName());
			
			for (Image image: snapshot.getImages()) {
				System.out.println("\t" + image.getBaseName());
			}
		}		
		
		// Check the obtained values
		assertTrue(vm.getLastStateChange() != null);
		assertFalse(vm.getLastStateChange().equals(""));
		assertTrue(vm.getLastStateChange().equals("2018-06-12T20:19:20Z"));			// change if the vbox changes
		
		// Check the number of disks
		assertTrue(vm.getDisks().size() == 2);
		
		// Check the number of snapshots
		assertTrue(vm.getSnapshots().size() == 5);
		
		// Check the data in the snapshots
		assertTrue(vm.getSnapshots().get(0) != null);
		assertTrue(vm.getSnapshots().get(0).getName() != null);
		assertTrue(vm.getSnapshots().get(0).getName().equals("snapshot 1"));

		assertTrue(vm.getSnapshots().get(0).getImages().size() == 2);
		assertTrue(vm.getSnapshots().get(0).getImages().get(0).getBaseName() != null);
		assertTrue(vm.getSnapshots().get(0).getImages().get(0).getBaseName().equals("FreeDos.vdi"));
		assertTrue(vm.getSnapshots().get(0).getImages().get(1).getBaseName() != null);
		assertTrue(vm.getSnapshots().get(0).getImages().get(1).getBaseName().equals("FreeDOS-1.vdi"));
		
		assertTrue(vm.getSnapshots().get(1) != null);
		assertTrue(vm.getSnapshots().get(1).getName() != null);
		assertTrue(vm.getSnapshots().get(1).getName().equals("Snapshot 1a"));
		
		assertTrue(vm.getSnapshots().get(1).getImages().size() == 2);
		assertTrue(vm.getSnapshots().get(1).getImages().get(0).getBaseName() != null);
		assertTrue(vm.getSnapshots().get(1).getImages().get(0).getBaseName().equals("{df5537c3-e87b-4101-b525-f059f4ab899f}.vdi"));
		assertTrue(vm.getSnapshots().get(1).getImages().get(1).getBaseName() != null);
		assertTrue(vm.getSnapshots().get(1).getImages().get(1).getBaseName().equals("{581a971e-fbce-420b-904c-4942d61f9c40}.vdi"));
		
		assertTrue(vm.getSnapshots().get(4) != null);
		assertTrue(vm.getSnapshots().get(4).getName() != null);
		assertTrue(vm.getSnapshots().get(4).getName().equals("{current}"));
		
		assertTrue(vm.getSnapshots().get(4).getImages().size() == 2);
		assertTrue(vm.getSnapshots().get(4).getImages().get(0).getBaseName() != null);
		assertTrue(vm.getSnapshots().get(4).getImages().get(0).getBaseName().equals("{72ba94db-2c92-485f-8ae5-c09f1281aa1c}.vdi"));
		assertTrue(vm.getSnapshots().get(4).getImages().get(1).getBaseName() != null);
		assertTrue(vm.getSnapshots().get(4).getImages().get(1).getBaseName().equals("{239d24ca-b523-46c7-a21a-81a74bc3b0bb}.vdi"));
		
	}
	
	@Test
	public void testWithMultipleDisks3() throws Exception {
		
		// Get the FreeDOS-1v1.vbox file
		String filename = ProcessVboxTest.class.getClassLoader()
								.getResource("tests/FreeDOS-1v3.vbox")
								.getFile();
		
		// Process the file
		VirtualMachine vm = VirtualMachineBuilder.process(filename);
		
		// Print the obtained values
		System.out.println("Data for testWithMultipleDisks3()");
		System.out.println("last change : "+ vm.getLastStateChange());
		for (Snapshot snapshot: vm.getSnapshots()) {
			System.out.println(snapshot.getName());
			
			for (Image image: snapshot.getImages()) {
				System.out.println("\t" + image.getBaseName());
			}
		}

		// Check the obtained values
		assertTrue(vm.getLastStateChange() != null);
		assertFalse(vm.getLastStateChange().equals(""));
		assertTrue(vm.getLastStateChange().equals("2018-06-12T20:28:34Z"));			// change if the vbox changes
		
		// Check the number of disks
		assertTrue(vm.getDisks().size() == 2);
		
		// Check the number of snapshots
		assertTrue(vm.getSnapshots().size() == 6);
		
		// Check the data in the snapshots
		assertTrue(vm.getSnapshots().get(0) != null);
		assertTrue(vm.getSnapshots().get(0).getName() != null);
		assertTrue(vm.getSnapshots().get(0).getName().equals("snapshot 1"));

		assertTrue(vm.getSnapshots().get(0).getImages().size() == 2);
		assertTrue(vm.getSnapshots().get(0).getImages().get(0).getBaseName() != null);
		assertTrue(vm.getSnapshots().get(0).getImages().get(0).getBaseName().equals("FreeDos.vdi"));
		assertTrue(vm.getSnapshots().get(0).getImages().get(1).getBaseName() != null);
		assertTrue(vm.getSnapshots().get(0).getImages().get(1).getBaseName().equals("FreeDOS-1.vdi"));
		
		assertTrue(vm.getSnapshots().get(1) != null);
		assertTrue(vm.getSnapshots().get(1).getName() != null);
		assertTrue(vm.getSnapshots().get(1).getName().equals("Snapshot 1a"));
		
		assertTrue(vm.getSnapshots().get(1).getImages().size() == 2);
		assertTrue(vm.getSnapshots().get(1).getImages().get(0).getBaseName() != null);
		assertTrue(vm.getSnapshots().get(1).getImages().get(0).getBaseName().equals("{df5537c3-e87b-4101-b525-f059f4ab899f}.vdi"));
		assertTrue(vm.getSnapshots().get(1).getImages().get(1).getBaseName() != null);
		assertTrue(vm.getSnapshots().get(1).getImages().get(1).getBaseName().equals("{581a971e-fbce-420b-904c-4942d61f9c40}.vdi"));
		
		assertTrue(vm.getSnapshots().get(4) != null);
		assertTrue(vm.getSnapshots().get(4).getName() != null);
		assertTrue(vm.getSnapshots().get(4).getName().equals("snapshot 4"));
		
		assertTrue(vm.getSnapshots().get(4).getImages().size() == 2);
		assertTrue(vm.getSnapshots().get(4).getImages().get(0).getBaseName() != null);
		assertTrue(vm.getSnapshots().get(4).getImages().get(0).getBaseName().equals("{72ba94db-2c92-485f-8ae5-c09f1281aa1c}.vdi"));
		assertTrue(vm.getSnapshots().get(4).getImages().get(1).getBaseName() != null);
		assertTrue(vm.getSnapshots().get(4).getImages().get(1).getBaseName().equals("{239d24ca-b523-46c7-a21a-81a74bc3b0bb}.vdi"));
	
		assertTrue(vm.getSnapshots().get(5) != null);
		assertTrue(vm.getSnapshots().get(5).getName() != null);
		assertTrue(vm.getSnapshots().get(5).getName().equals("{current}"));
		
		assertTrue(vm.getSnapshots().get(5).getImages().size() == 2);
		assertTrue(vm.getSnapshots().get(5).getImages().get(0).getBaseName() != null);
		assertTrue(vm.getSnapshots().get(5).getImages().get(0).getBaseName().equals("{19994683-7194-45e9-803d-538e6c5cdd05}.vdi"));
		assertTrue(vm.getSnapshots().get(5).getImages().get(1).getBaseName() != null);
		assertTrue(vm.getSnapshots().get(5).getImages().get(1).getBaseName().equals("{70b8953e-8a31-4cc1-a9ac-6f51d6fdac1b}.vdi"));
		
	}
	
}

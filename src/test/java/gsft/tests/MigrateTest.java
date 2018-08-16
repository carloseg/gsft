package gsft.tests;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import gsft.GSFT;

/**
 * Test for checking the correct processing of .vbox files
 *
 */
public class MigrateTest {

	/**
	 * Test the process to obtain the list of files for migration. 
	 * This uses a .vbox file with a snapshot tree. It request the first snapshot.
	 * @throws Exception
	 */
	@Test
	public void testMigrateSnapshot() throws Exception {
		
		// Get the FreeDOS-1v3.vbox file
		String filename = MigrateTest.class.getClassLoader()
								.getResource("tests/FreeDOS-1v3.vbox")
								.getFile();

		// Get a temporary file
	    File temp = File.createTempFile("test1", ".tmp"); 
		
		// Execute the Migrate 
		GSFT.main(new String[] { "-migrate", "-snapshot", "snapshot 1", filename, "-file", temp.getPath() });
		
		// Reads the content
		List<String> content = readLines(temp);
		
		// the first snapshot has the .vbox, two .vdi files and a .sav file
		assertTrue(content.size() == 4);
		assertTrue(content.get(0).endsWith(".vbox"));
		assertTrue(content.get(1).endsWith(".vdi"));
		assertTrue(content.get(2).endsWith(".vdi"));
		assertTrue(content.get(3).endsWith(".sav"));
		
//		for (String line: content) {
//			System.out.println(line);
//		}
	}

	/**
	 * Test the process to obtain the list of files for migration. 
	 * This uses a .vbox file with a snapshot tree. It request the first snapshot or the first path.
	 * @throws Exception
	 */
	@Test
	public void testMigrateSnapshot2() throws Exception {
		
		// Get the FreeDOS-1v1.vbox file
		String filename = MigrateTest.class.getClassLoader()
								.getResource("tests/FreeDOS-1v3.vbox")
								.getFile();

		// Get a temporary file
	    File temp = File.createTempFile("test1", ".tmp"); 
		
		// Execute the Migrate 
		GSFT.main(new String[] { "-migrate", "-snapshot", "Snapshot 1a", filename, "-file", temp.getPath() });
		
		// Reads the content
		List<String> content = readLines(temp);
		
		// the first snapshot has the .vbox, four .vdi files and a .sav file
		assertTrue(content.size() == 6);
		assertTrue(content.get(0).endsWith(".vbox"));
		assertTrue(content.get(1).endsWith(".vdi"));
		assertTrue(content.get(2).endsWith(".vdi"));
		assertTrue(content.get(3).endsWith(".vdi"));
		assertTrue(content.get(4).endsWith(".vdi"));
		assertTrue(content.get(5).endsWith(".sav"));

	}

	/**
	 * Test the process to obtain the list of files for migration. 
	 * This uses a .vbox file with a snapshot tree. It request the second snapshot or the first path.
	 * @throws Exception
	 */
	@Test
	public void testMigrateSnapshot3() throws Exception {
		
		// Get the FreeDOS-1v1.vbox file
		String filename = MigrateTest.class.getClassLoader()
								.getResource("tests/FreeDOS-1v3.vbox")
								.getFile();
		
		// Get a temporary file
	    File temp = File.createTempFile("test1", ".tmp"); 
		
		// Execute the Migrate 
		GSFT.main(new String[] { "-migrate", "-snapshot", "snapshot 2", filename, "-file", temp.getPath() });
		
		// Reads the content
		List<String> content = readLines(temp);
		
		// the first snapshot has the .vbox, six .vdi files and a .sav file
		assertTrue(content.size() == 8);
		assertTrue(content.get(0).endsWith(".vbox"));
		assertTrue(content.get(1).endsWith(".vdi"));
		assertTrue(content.get(2).endsWith(".vdi"));
		assertTrue(content.get(3).endsWith(".vdi"));
		assertTrue(content.get(4).endsWith(".vdi"));
		assertTrue(content.get(5).endsWith(".vdi"));
		assertTrue(content.get(6).endsWith(".vdi"));
		assertTrue(content.get(7).endsWith(".sav"));

	}

	/**
	 * Test the process to obtain the list of files for migration. 
	 * This uses a .vbox file with a snapshot tree. It request the third snapshot or the first path.
	 * @throws Exception
	 */
	@Test
	public void testMigrateSnapshot4() throws Exception {
		
		// Get the FreeDOS-1v1.vbox file
		String filename = MigrateTest.class.getClassLoader()
								.getResource("tests/FreeDOS-1v3.vbox")
								.getFile();
		
		// Get a temporary file
	    File temp = File.createTempFile("test1", ".tmp"); 
		
		// Execute the Migrate 
		GSFT.main(new String[] { "-migrate", "-snapshot", "snapshot3", filename, "-file", temp.getPath() });
		
		// Reads the content
		List<String> content = readLines(temp);
		
		// the first snapshot has the .vbox, eight .vdi files and a .sav file
		assertTrue(content.size() == 10);
		assertTrue(content.get(0).endsWith(".vbox"));
		assertTrue(content.get(1).endsWith(".vdi"));
		assertTrue(content.get(2).endsWith(".vdi"));
		assertTrue(content.get(3).endsWith(".vdi"));
		assertTrue(content.get(4).endsWith(".vdi"));
		assertTrue(content.get(5).endsWith(".vdi"));
		assertTrue(content.get(6).endsWith(".vdi"));
		assertTrue(content.get(7).endsWith(".vdi"));
		assertTrue(content.get(8).endsWith(".vdi"));
		assertTrue(content.get(9).endsWith(".sav"));

	}
	
	/**
	 * Test the process to obtain the list of files for migration. 
	 * This uses a .vbox file with a snapshot tree. It request the second snapshot or the second path.
	 * @throws Exception
	 */
	@Test
	public void testMigrateSnapshot5() throws Exception {
		
		// Get the FreeDOS-1v1.vbox file
		String filename = MigrateTest.class.getClassLoader()
								.getResource("tests/FreeDOS-1v3.vbox")
								.getFile();
		
		// Get a temporary file
	    File temp = File.createTempFile("test1", ".tmp"); 		
		
		// Execute the Migrate 
		GSFT.main(new String[] { "-migrate", "-snapshot", "snapshot 4", filename, "-file", temp.getPath() });
		
		// Reads the content
		List<String> content = readLines(temp);
		
		// the first snapshot has the .vbox, two .vdi files and a .sav file
		assertTrue(content.size() == 6);
		assertTrue(content.get(0).endsWith(".vbox"));
		assertTrue(content.get(1).endsWith(".vdi"));
		assertTrue(content.get(2).endsWith(".vdi"));
		assertTrue(content.get(3).endsWith(".vdi"));
		assertTrue(content.get(4).endsWith(".vdi"));
		assertTrue(content.get(5).endsWith(".sav"));
	
	}

	/**
	 * Reads a file into a list of strings
	 * @param file 			File to read
	 * @return				array of Strings with the file content
	 * @throws Exception	Error reading the file
	 */
	private List<String> readLines(File file) throws Exception {
	      if (!file.exists()) {
	          return new ArrayList<String>();
	      }
	      BufferedReader reader = new BufferedReader(new FileReader(file));
	      List<String> results = new ArrayList<String>();
	      String line = reader.readLine();
	      while (line != null) {
	          results.add(line);
	          line = reader.readLine();
	      }
	      reader.close();
	      return results;
	  }
	
}

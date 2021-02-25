import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

import org.junit.Test;

public class Testing {

	@Test
	public void fileChecker() {

		File file = new File("\\SoftwareHasher\\src\\MainPanel.Java");// Default file loaded
		file.getAbsolutePath();
		System.out.println(file);
	}

	@Test
	public void fileChecker2() {
		JFileChooser dirFolder = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		dirFolder.setCurrentDirectory(new File(System.getProperty("user.home") + System.getProperty("Desktop")));
		dirFolder.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int newFactor = dirFolder.showOpenDialog(null);
		if (newFactor == JFileChooser.APPROVE_OPTION) {
			File fold;
			fold = dirFolder.getSelectedFile();
			File[] listOfFiles = fold.listFiles();
			String[] dirContent = new String[listOfFiles.length];
			int hash = fold.hashCode();

			for (int i = 0; i < listOfFiles.length; i++) { // counting how many files are in the directory to
															// display them all
				if (listOfFiles[i].isFile()) {
					System.out.println("Directory: " + listOfFiles[i].getParent());
					dirContent[i] = listOfFiles[i].getName();
					System.out.println("Filenames: " + (Arrays.toString(dirContent)));
					System.out.println("Hash: " + hash);
				}

			}
		}
	}
	@Test
	public void DataWriter() 
			throws Exception {
			    String testing = "Does this work?";
			    BufferedWriter database = new BufferedWriter(new FileWriter("src//writerTEST.txt"));
			    database.write(testing);
			    database.close();
			}

}

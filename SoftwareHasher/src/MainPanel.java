import java.awt.Dimension;//Imports used
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileSystemView;

@SuppressWarnings("serial")
public class MainPanel extends JPanel {

	private static JComboBox<String> hashAlgorithm;// Combo box for different algorithms
	private static String[] inputlist = { "Algorithm 1", "Algorithm 2", "Algorithm 3" };// Combo box list
	private static JCheckBox metaTick;// The checkbox that allows conversion of meta data
	private static JMenuBar menuBar;//The menubar where all the menuitems are stored
	private static JMenu m1, m2;//The two menus within the menuitems
	private static JMenuItem loadF, loadD, exit, about;//The menuitems within the menus
	public static JLabel hashLabel, dirLabel, fileLabel;//Labels used for displaying the data from the files and directory
	public static File file, fold;//Both of the files used to get the files and directories

	JMenuBar setupTaskBar() {// Where the taskbar is created

		menuBar = new JMenuBar();// Creates the initial base for the menu
		menuBar.setToolTipText("Indicates where all the submenus are added.");// Tooltips explaining what things do in the program
																				
		m1 = new JMenu("File");// Creating the menus within the menubar
		m1.setIcon(new ImageIcon("img/file.png"));// Displaying of the icons and their paths
		m1.setToolTipText("Opens the File menu.");// Tooltips explaining what things do in the program

		m2 = new JMenu("Help");// Creating the menus within the menubar
		m2.setIcon(new ImageIcon("img/help.png"));// Displaying of the icons and their paths
		m2.setToolTipText("Opens the Help menu.");// Tooltips explaining what things do in the program

		loadF = new JMenuItem("File");// Creating the menuitems 
		loadF.setMnemonic(KeyEvent.VK_L);// Declaring the Mnemonics
		loadF.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));// Selecting a mask and a key for it to react to																				
		loadF.setIcon(new ImageIcon("img/file.png"));// Displaying of the icons and their paths
		loadF.setToolTipText("Allows you to load a file.");// Tooltips explaining what things do in the program

		loadD = new JMenuItem("Directory");
		loadD.setMnemonic(KeyEvent.VK_F);// Declaring the Mnemonics
		loadD.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK));
		loadD.setIcon(new ImageIcon("img/file.png"));// Displaying of the icons and their paths
		loadD.setToolTipText("Allows you to load a directory.");// Tooltips explaining what things do in the program

		exit = new JMenuItem("Exit");
		exit.setMnemonic(KeyEvent.VK_E);// Declaring the Mnemonics
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
		exit.setIcon(new ImageIcon("img/exit.png"));// Displaying of the icons and their paths
		exit.setToolTipText("Allows you to exit the application.");// Tooltips explaining what things do in the program

		about = new JMenuItem("About");
		about.setMnemonic(KeyEvent.VK_A);
		about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
		about.setIcon(new ImageIcon("img/about.png"));// Displaying of the icons and their paths
		about.setToolTipText("Gives you a brief description of the application.");// Tooltips explaining what things do in the program

		dirLabel = new JLabel("Directory: ");// Label telling you what to do
		dirLabel.setToolTipText("The label showing the selected directory.");// Tooltips explaining what things do in the program

		fileLabel = new JLabel("Filename: ");// Label telling you what to do
		fileLabel.setToolTipText("The label showing the selected filenames.");// Tooltips explaining what things do in the program

		hashLabel = new JLabel("Hash: ");// Label telling you what to do
		hashLabel.setToolTipText("The label showing the generated hash.");// Tooltips explaining what things do in the program

		metaTick = new JCheckBox("Generate metadata");// Tickbox allowing you to generate meta-data
		metaTick.setToolTipText("Choose which way to convert.");// Tooltips explaining what things do in the program

		menuBar.add(m1);// Adds the menus and submenus to the main menubar
		menuBar.add(m2);
		m1.add(loadF);
		m1.add(loadD);
		m1.add(exit);
		m2.add(about);
		add(dirLabel);//Adds the labels to the panel
		add(fileLabel);
		add(hashLabel);
		add(metaTick);

		loadF.addActionListener(new ActionListener() { // File chooser for the single file
			public void actionPerformed(ActionEvent e) {

				MainPanel.fileLoader();//Calling the method to the menuitem 
				try {
					MainPanel.DataWriter();//Calling the method to the menuitem 
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			    MainPanel.fold = new File ("");//Clearing the file
			}
		});

		loadD.addActionListener(new ActionListener() { // File chooser for the directory
			public void actionPerformed(ActionEvent e) {

				MainPanel.folderLoader();//Calling the method to the menuitem 
				try {
					MainPanel.DataWriter();//Calling the method to the menuitem 
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				MainPanel.file = new File ("");//Clearing the file
				
		
			}
		});

		exit.addActionListener(new ActionListener() {// Allows the program to listen to the exit option
			public void actionPerformed(ActionEvent e) {
				System.exit(0);// Terminates the program when clicked
			}
		});

		about.addActionListener(new ActionListener() {// Allows the program to listen to the about option
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"The purpose of this application is to allow you to hash files and file directories." + "\n"// Displays message when pressed																				
								+ "Author name - Thomas Gale" + "\n" + "Copyright \u00a9 2019",
						"About", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		return menuBar;// Returns the menubar to its default state
	}

	MainPanel() {// Mainpanel is where everything you see on the screen is shown
		ActionListener listener = new AlgListener();// Listens to Action events

		hashAlgorithm = new JComboBox<String>(inputlist);// Converts the values when the options changed
		hashAlgorithm.setToolTipText("List of all conversions.");
		hashAlgorithm.addActionListener(listener);

		add(hashAlgorithm);

		hashAlgorithm.setMaximumSize( hashAlgorithm.getPreferredSize());
		setPreferredSize(new Dimension(800, 160));// Set the preferred size of the application

		JOptionPane.showMessageDialog(null,
				"Please select file in the menubar to either hash a single file or a directory.", "Hash File",
				JOptionPane.INFORMATION_MESSAGE);// prompt to hash a file when the program is ran
	}

	public static void fileLoader() {
		JFileChooser loadFile = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		loadFile.setCurrentDirectory(new File(System.getProperty("user.home") + System.getProperty("Desktop")));
		loadFile.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int newFactor = loadFile.showOpenDialog(null);
		if (newFactor == JFileChooser.APPROVE_OPTION) {
			file = loadFile.getSelectedFile();
			int hash = file.hashCode();

			JOptionPane.showMessageDialog(null, "New file loaded", "Hash File", // display when above code is processed to show the new file
					JOptionPane.INFORMATION_MESSAGE);

			dirLabel.setText("Directory: " + file.getParent());
			fileLabel.setText("Filename: " + file.getName());
			hashLabel.setText("Hash: " + hash);
		
			
			}if (newFactor == JFileChooser.CANCEL_OPTION) {
				JOptionPane.showMessageDialog(null, "File not changed", "Option Cancelled", // display when above code is processed to show the new file
						JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public static void folderLoader() {
		JFileChooser dirFolder = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		dirFolder.setCurrentDirectory(new File(System.getProperty("user.home") + System.getProperty("Desktop")));
		dirFolder.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int newFactor = dirFolder.showOpenDialog(null);
		if (newFactor == JFileChooser.APPROVE_OPTION) {
			fold = dirFolder.getSelectedFile();
			File[] listOfFiles = fold.listFiles();
			String[] dirContent = new String[listOfFiles.length];
			int hash = fold.hashCode();
		

			if (MainPanel.metaTick.isSelected() == false) { // if the checkbox is not ticked do below code

				for (int i = 0; i < listOfFiles.length; i++) { // counting how many files are in the directory to
																// display them all
					if (listOfFiles[i].isFile()) {
						dirLabel.setText("Directory: " + listOfFiles[i].getParent());
						dirContent[i] = listOfFiles[i].getName();
						fileLabel.setText("Filenames: " + (Arrays.toString(dirContent)));
						hashLabel.setText("Hash: " + hash);
					}
						
			}
				JOptionPane.showMessageDialog(null, "New file loaded", "Hash File", JOptionPane.INFORMATION_MESSAGE);	
				
			} else {
				Path fPath = Paths.get(fold.getAbsolutePath()); // else it will do this to generate the metadata and the
															// above code together
				BasicFileAttributes attr = null;
				try {
					attr = Files.readAttributes(fPath, BasicFileAttributes.class);
				} catch (IOException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Meta data could not be loaded", "Error", // display when above code is processed to show the new file
							JOptionPane.INFORMATION_MESSAGE);
				}

				for (int i = 0; i < listOfFiles.length; i++) {
					if (listOfFiles[i].isFile()) {
						dirLabel.setText("Directory: " + listOfFiles[i].getParent() + " (Creation time: " //Displaying the meta data
								+ attr.creationTime() + " Last accessed: " + attr.lastAccessTime() 
								+ " Last modified: " + attr.lastModifiedTime() + " Directory size: " + attr.size()
								+ ")");
						dirContent[i] = listOfFiles[i].getName();
						fileLabel.setText("Filenames: " + (Arrays.toString(dirContent)));
						hashLabel.setText("Hash: " + hash);
						
					}

				}
				JOptionPane.showMessageDialog(null, "New file loaded", "Hash File", JOptionPane.INFORMATION_MESSAGE);
			}
		}if (newFactor == JFileChooser.CANCEL_OPTION) {
			JOptionPane.showMessageDialog(null, "File not changed", "Option Cancelled", // display when above code is processed to show the new file
					JOptionPane.INFORMATION_MESSAGE);
	}

	}
	
	public static void DataWriter() throws Exception {//Writes the filepaths to a txt doc
			
			    String filePath = "" + file;//Declaring the variables
			    String folderPath = "" + fold;
			    BufferedWriter database;
				try {
					 
					database = new BufferedWriter(new FileWriter("src//R4Database.txt"));//Directory where the path names are written to
					
					 if(loadF.isSelected() == true);{
						 database.write(filePath);//Writes the selected file
						 database.newLine();//Add a new line 
					 } if(loadD.isSelected() == true);{
						 database.write(folderPath);//Writes the selected directory
						 database.newLine();//Add a new line
					 }
					 database.close();//Closes the reader
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
	
		
	public class AlgListener implements ActionListener {// Class where all the algorithm conversions happen

		public void actionPerformed(ActionEvent event) {

			// Setup the switch to select the algorithms

			switch (MainPanel.hashAlgorithm.getSelectedIndex()) {
			case 0: // Algorithm 1
				if (file.isFile()) {//If file is not empty then the algorithm will be used
					new algorithm1().produceFileHash(null);

				} else if (fold.isDirectory()) {//if fold is not empty then the algorithm will be used
					new algorithm1().produceDirHash(null);
				}
				if (metaTick.isSelected()) {//if the checkbox is ticked metadata will be generated from the directory
					new algorithm1().produceDirMetaHash(null);

				}
				break;

			case 1: // Algorithm 2
				if (file.isFile()) {//If file is not empty then the algorithm will be used
					new algorithm2().produceFileHash(null);

				} else if (fold.isDirectory()) {//if fold is not empty then the algorithm will be used
					new algorithm2().produceDirHash(null);
				}
				if (metaTick.isSelected()) {//if the checkbox is ticked metadata will be generated from the directory
					new algorithm2().produceDirMetaHash(null);

				}
				break;

			case 2: // Algorithm 3
				if (file.isFile()) {//If file is not empty then the algorithm will be used
					new algorithm3().produceFileHash(null);

				} else if (fold.isDirectory()) {//if fold is not empty then the algorithm will be used
					new algorithm3().produceDirHash(null);
				}
				if (metaTick.isSelected()) {//if the checkbox is ticked metadata will be generated from the directory
					new algorithm3().produceDirMetaHash(null);

				}
				break;

			}

		}
	}

}// End of the application

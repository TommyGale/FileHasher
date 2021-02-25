import java.io.File;//Imports used

public class algorithm2 implements hashGenerator {// Algorithm 2 class created from interface

	@Override
	public String produceFileHash(File file) {// Produces the hash for the single file
		int hash = MainPanel.file.hashCode() * 10;// Hashing algorithm
		String newHash = Integer.toBinaryString(hash);// Convert algorithm to Binary
		MainPanel.hashLabel.setText("Hash: " + newHash);// Adding the result to the label in the mainpanel
		return null;
	}

	@Override
	public String produceDirHash(File fold) {// Produces the hash for the directory
		int hash = MainPanel.fold.hashCode() * 20;// Hashing algorithm
		String newHash = Integer.toBinaryString(hash);// Convert algorithm to Binary
		MainPanel.hashLabel.setText("Hash: " + newHash);// Adding the result to the label in the mainpanel
		return null;
	}

	@Override
	public String produceDirMetaHash(File fold) {// Produces the hash for the meta data of the directory
		int hash = MainPanel.fold.hashCode() * 30;// Hashing algorithm
		String newHash = Integer.toBinaryString(hash);// Convert algorithm to Binary
		MainPanel.hashLabel.setText("Hash: " + newHash);// Adding the result to the label in the mainpanel
		return null;
	}

}// End of class

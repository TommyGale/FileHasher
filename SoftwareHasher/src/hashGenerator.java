import java.io.File;//Imports used

public interface hashGenerator {// Base interface for generating different hashing

	String produceFileHash(File file);// Produces the hash for the single file

	String produceDirHash(File file);// Produces the hash for the directory

	String produceDirMetaHash(File file);// Produces the hash for the meta data of the directory

}// End of the interface

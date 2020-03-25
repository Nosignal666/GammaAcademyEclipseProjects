package corsojava.storage;

import java.net.URL;
import corsojava.storage.StorageException;

public interface StorageInterface {
	public void initStorage(URL config) throws StorageException;
	public void writeData(DataInterface data) throws StorageException;
	public DataInterface readData(String index) throws StorageException;
}

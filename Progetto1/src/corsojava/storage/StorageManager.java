package corsojava.storage;

import java.net.URL;

public class StorageManager {
	private StorageInterface storage;

	public void init(String storageManager, URL config) throws StorageException{
		try{
			/* è un modo per creare dalla stringa "corsojava.filestorage.FileStorageManager" 
			 * un oggetto ti tipo FileStorageManager*/
			Class<?> clazz=Class.forName(storageManager); 
			storage=(StorageInterface)clazz.newInstance();
			storage.initStorage(config);
			
		}
		catch(Exception e){
			throw new StorageException("Init Error: "+ e.getMessage());
		}
	}
	public DataInterface readData(String index) throws StorageException{
		DataInterface data= null;
        try{
			data=storage.readData(index);
		}
		catch(StorageException e){
			throw e;
		}
		return data;
	}
	public void writeData(DataInterface data) throws StorageException{
        try{
			storage.writeData(data);
		}
		catch(StorageException e){
			throw e;
		}
		
	}

}

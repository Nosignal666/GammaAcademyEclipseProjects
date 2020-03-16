package storage;
import java.net.URL;

import strutturaDati.Azienda;

public interface StorageInterface {
	public void initStorage(URL config) throws StorageException;
	public void writeAzienda(Azienda az)throws StorageException;
	public Azienda readAzienda(String nome)throws StorageException;
	

}

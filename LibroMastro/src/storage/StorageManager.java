package storage;

import java.net.URL;

import strutturaDati.*;

public class StorageManager {
	private StorageInterface si;
	public void initStorage(String storage,URL config) throws StorageException{
		try {
			si=(StorageInterface)Class.forName(storage).newInstance();
			si.initStorage(config);
		} catch (Exception e) {
			throw new StorageException();
		}
	}
	public void writeAzienda(Azienda az) throws StorageException{
		si.writeAzienda(az);
	}
	public Azienda readAzienda(String nome) throws StorageException{
		return si.readAzienda(nome);
	}

}

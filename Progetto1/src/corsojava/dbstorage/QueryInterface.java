package corsojava.dbstorage;
import java.sql.ResultSet;

import corsojava.storage.DataInterface;
import corsojava.storage.StorageException;

public interface QueryInterface {
	public QuerySet update(DataInterface dataInter) throws QueryException;
	public QuerySet select(String index)throws QueryException;
	public String getPackageName();
	DataInterface buildObjectfrom(ResultSet rs,String index) throws StorageException;
}

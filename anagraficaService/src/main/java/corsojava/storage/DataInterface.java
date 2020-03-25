package corsojava.storage;

import corsojava.storage.IllegaIndexException;
public interface DataInterface {
	public void setDataElement(DataElement dataElement)throws IllegalArgumentException, IllegalAccessException;
	public DataElement getDataElement()throws IllegalArgumentException, IllegalAccessException;
	public String getIndex() throws IllegaIndexException;

}

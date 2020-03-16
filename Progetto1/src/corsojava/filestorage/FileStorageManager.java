package corsojava.filestorage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import corsojava.storage.DataElement;
import corsojava.storage.DataInterface;
import corsojava.storage.StorageException;
import corsojava.storage.StorageInterface;

public class FileStorageManager implements StorageInterface {
	private String path=null;

	@Override
	public void initStorage(URL config) throws StorageException {
		
		/* verificare se la directory esiste altirmenti crearla*/
		
		path=config.getPath();
		File dir=new File(path);
		try{
			if(!dir.exists()) dir.mkdir();
		}
		catch(Exception e){
			throw new StorageException("Init Error: "+e.getMessage());
		}
	}

	@Override
	public void writeData(DataInterface data) throws StorageException {
		FileOutputStream fis=null;
		OutputStreamWriter osw=null;
		BufferedWriter bw =null;
		try{
			
			String nomefile=data.getIndex();
			
			File file=new File(path+"\\"+nomefile.toLowerCase()+".txt");
			file.createNewFile();
			fis=new FileOutputStream(file);
			osw=new OutputStreamWriter(fis);
			bw =new BufferedWriter(osw);
			DataElement de=data.getDataElement();
			bw.write("nomeClasse="+de.getNomeclasse()+"\n");
			
			/** creare file nella directory path con nome l'index restituito da getIndex,
			 * e scandendo l'hashmap scrivere sul file le coppie chiave valore controllando
			 * che il valore sia diverso da null altrimenti nessuna scrittura 
			 */
			
			
			HashMap<String,String> hm=de.getData();
			Set<Entry<String,String>> set=hm.entrySet(); /* è l'insieme delle entries su cui si può ciclare */
			String key=null;
			String value=null;
			for(Entry<String, String> ent: set){
				key=ent.getKey();
				value=ent.getValue();
				if(!value.equals("") && value!=null) bw.write(key+"="+value+"\n");
			}
		   }  
		catch(Exception e){
			e.printStackTrace();
			throw new StorageException(e.getMessage());
		}
		finally{
			try{bw.close();	osw.close(); fis.close();}catch(Exception e){};
		}
	}

	@Override
	public DataInterface readData(String index) throws StorageException{
		// TODO Auto-generated method stub
		FileInputStream fis=null;
		InputStreamReader isr=null;
		BufferedReader br=null;
		String key=null;
		String value=null;
		String line=null;
		String nomeClasse=null;
		DataInterface dataInter=null;
		try{
			fis=new FileInputStream(path+"\\"+index+".txt");
			isr=new InputStreamReader(fis);
			br=new BufferedReader(isr);
			String[] nomeClasseNomePair=br.readLine().split("=");
			nomeClasse=nomeClasseNomePair[1];

			
			DataElement de=new DataElement();
			HashMap<String,String> hm=new HashMap<String,String>();
			de.setNomeclasse(nomeClasse);
			
		
			Class<?> clazz=Class.forName(nomeClasse);
			dataInter=(DataInterface)clazz.newInstance();
			
/* utilizza StrinTokenizer */
			while((line=br.readLine())!=null){
				String[] keyValuePair=line.split("=");
				key=keyValuePair[0];
				value=keyValuePair[1];
				hm.put(key, value);
			}
			
			de.setData(hm);
			dataInter.setDataElement(de);
		}
		catch(Exception e){
			throw new StorageException("Read Error: "+e.getMessage());
			
		}
		finally{
			try{br.close();isr.close();fis.close();}catch(Exception e){};
		}
		return dataInter;
	}
}

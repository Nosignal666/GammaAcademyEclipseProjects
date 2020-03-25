package storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.StringTokenizer;

import strutturaDati.Azienda;
import strutturaDati.Entry;

public class FileStorageManager implements StorageInterface {
	private String path;

	@Override
	public void initStorage(URL config) throws StorageException {
		path=config.getPath();
		File dir=new File(path);		
		if(!dir.exists()) dir.mkdir();
	}

	@Override
	public void writeAzienda(Azienda az) throws StorageException {
		if(az==null) throw new StorageException("Attempting to write null Azienda");
		String dirName=path+"\\"+az.getNome();
		File dir=new File(dirName);
		if(!dir.exists()) dir.mkdir();
		int count;
		File file=null;
		BufferedWriter bw=null;
		try {
			Field numeroFattura=Class.forName("strutturaDati.Entry").getDeclaredField("numeroFattura");
			numeroFattura.setAccessible(true);
			
			Field[] entryFields=Class.forName("strutturaDati.Entry").getDeclaredFields();
			for(Entry ent: az.entryList){
				count=dir.list().length;
				numeroFattura.set(ent, Integer.toString(count));
				file=new File(dirName+"\\"+Integer.toString(count)+".txt");
				bw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
				for(Field f:entryFields){
					f.setAccessible(true);
					bw.write(f.getName()+"="+f.get(ent)+"\n");
				}
				bw.close();
			}
		} catch (Exception e) {
			throw new StorageException("WRITE ERROR");
		}finally{
			try{
				bw.close();
			}catch(Exception e){}
		}
	}

	@Override
	public Azienda readAzienda(String nome) throws StorageException {
		Azienda az=new Azienda(nome);
		File dir=new File(path+"\\"+nome);
		if(!dir.exists()) throw new StorageException(nome+" not found");
		File[] files=dir.listFiles();
		Entry entry;
		ArrayList<Entry> entryList=new ArrayList<Entry>();
		BufferedReader br=null;
		try {
			for(File file:files){
				entry=new Entry();
				br=new BufferedReader(new InputStreamReader(new FileInputStream(file)));
				String line;
				while((line=br.readLine())!=null){
					StringTokenizer st2=new StringTokenizer(line,"=");
					Field field=Class.forName("strutturaDati.Entry").getDeclaredField(st2.nextToken());
					field.setAccessible(true);
					field.set(entry, st2.nextToken());
				}
				entryList.add(entry);
				br.close();
			}
			az.setEntryList(entryList);
			return az;
		} catch (Exception e) {
			e.printStackTrace();
			throw new StorageException("READ ERROR");
		}
	}

}

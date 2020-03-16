import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import corsojava.storage.StorageManager;

public class StorageConfigurator {
	
	private static StorageManager sm=null;
	
	public static StorageManager getStorageManager() throws ServletException{
		if(sm==null){
			sm=new StorageManager();
			
			FileInputStream fis;
			BufferedReader br;
			InputStreamReader isr;
			String line;
			HashMap<String,String> configData=new HashMap<String,String>();
			try {
				fis = new FileInputStream(new File("C:\\apache-tomcat-8.5.51\\webapps\\AnagraficaWeb\\config.txt"));
				isr=new InputStreamReader(fis);
				br=new BufferedReader(isr);
				
				while(!((line=br.readLine())==null)){
					if(line.startsWith("#")) continue;
					StringTokenizer st=new StringTokenizer(line,"=");
					configData.put(st.nextToken(), st.nextToken());
			    } 
				sm.init(configData.get("support"), new URL("file:\\"+configData.get("storageParameter")));
				br.close();
			}catch (Exception e) {
				throw new ServletException("CONFIG ERROR: "+e.getMessage());
			}
			
		}
		return sm;	
	}
}

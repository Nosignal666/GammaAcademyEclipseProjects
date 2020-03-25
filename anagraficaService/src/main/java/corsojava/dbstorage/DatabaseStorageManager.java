package corsojava.dbstorage;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.StringTokenizer;
import corsojava.storage.DataInterface;
import corsojava.storage.StorageException;
import corsojava.storage.StorageInterface;

public class DatabaseStorageManager {
	Connection con;
	QueryInterface qy;
	HashMap<String,String> configData=new HashMap<String,String>();
	
	public void initStorage() throws StorageException {
		try {
			/*
			InputStreamReader isr=new InputStreamReader(new FileInputStream(config.getPath()));
			BufferedReader br=new BufferedReader(isr);
			StringTokenizer st;
			String line;
			while(!((line=br.readLine())==null)){
				st=new StringTokenizer(line,"=");
				configData.put(st.nextToken(),st.nextToken());
			}
			br.close();
			isr.close();
		
			*/
			configData.put("path", "jdbc:postgresql://ec2-46-137-84-140.eu-west-1.compute.amazonaws.com:5432/dcu94ibsui6gmc");
			configData.put("username","aftfocnmvvhnis");
			configData.put("password","d7977e037e59b2477853fd2bdbcaafdd98f8df20a7d7168c41f3f8cb5011f997");
			configData.put("QueryInterface","QueryAnagrafica");
			
			Class.forName("org.postgresql.Driver");
			
			Class<?> clazz=Class.forName("corsojava.dbstorage."+configData.get("QueryInterface"));
			qy=(QueryInterface)clazz.newInstance();
			con=DriverManager.getConnection(configData.get("path"),configData.get("username"),configData.get("password"));
			con.close();

			
		} catch (ClassNotFoundException e) {
			throw new StorageException("driver or QueryInterface not found not found");
		} catch (Exception e) {
			e.printStackTrace();
			throw new StorageException("initialization failed, look config parameter!");
		}
	}

	
	public void writeData(DataInterface data) throws StorageException {
		try {
			con=DriverManager.getConnection(configData.get("path"),configData.get("username"),configData.get("password"));
			con.setAutoCommit(false);
			PreparedStatement ps;
			QuerySet qs=qy.update(data);
			for(QueryElement qe: qs){
				ps=con.prepareStatement(qe.getStatement());
				ps.execute();
				ps.close();
			}
			con.commit();
		} catch (Exception e) {
			try{con.rollback();}catch(Exception f){};
			throw new StorageException("Storage Error: "+e.getMessage());
		}finally{
			try{con.close();}catch(Exception e){};
		}
	}
	
	
	public DataInterface readData(String index) throws StorageException {
		DataInterface data=null;
		PreparedStatement ps=null;
		try {
			con=DriverManager.getConnection(configData.get("path"),configData.get("username"),configData.get("password"));
			QuerySet qs=qy.select(index);
			
			ps=con.prepareStatement(qs.get(0).getStatement());
			ps.execute();
			data= qy.buildObjectfrom(ps.getResultSet(), index);
		} catch (Exception e) {
			throw new StorageException("Storage Error: "+e.getMessage());
		}finally{
			try{ps.close();con.close();}catch(Exception e){};
		}
		return data;
	}
}

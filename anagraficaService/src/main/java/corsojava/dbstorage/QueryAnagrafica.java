package corsojava.dbstorage;


import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.Set;
import java.util.StringJoiner;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.Map.Entry;
import corsojava.storage.DataElement;
import corsojava.storage.DataInterface;
import corsojava.storage.StorageException;


public class QueryAnagrafica implements QueryInterface{
	private String dataInterface_id;
	private String credenziali_id;
	private String className=null;
	
	
	
	
	private String insertDataInterfaceStatement(DataInterface data) throws QueryException{
		String statement=null;
		dataInterface_id=UUID.randomUUID().toString();
		try{
			DataElement de=data.getDataElement();
			HashMap<String,String> hm=de.getData();
			String nomeClasse=de.getNomeclasse();
			String tabella=nomeClasse.substring(21);
			StringJoiner sj1=new StringJoiner(",",tabella+"(",")");
			StringJoiner sj2=new StringJoiner(","," values(",")");
			Set<Entry<String,String>> set=hm.entrySet();
			hm.remove("username");
			hm.remove("password");
			for(Entry<String,String> ent: set){
				sj1.add(ent.getKey());
				sj2.add("'"+ent.getValue()+"'");
			}
			sj1.add(tabella+"_id");
			sj2.add("'"+dataInterface_id+"'");
			statement="insert into "+sj1.toString()+sj2.toString();
		}catch(Exception e){
			throw new QueryException("Query Error: unable to get insert dataInterface stat");
		}
		return statement;
	}
	private String insertCredentialStatement(DataInterface data) throws QueryException{
		credenziali_id=UUID.randomUUID().toString();
		String statement=null;
		try{
			DataElement de=data.getDataElement();
			HashMap<String,String> hm=de.getData();
		    statement="insert into credenzialiutente(username,password,credenziali_id)"
		    		+ " values('"+hm.get("username")+"','"+hm.get("password")+"',"+"'"+credenziali_id+"')";
		}catch(Exception e){
			throw new QueryException("Query Error: unable to get insert credential stat");
		}
		return statement;
	}
	private String insertLinkStatement(DataInterface data) throws QueryException{
		String statement=null;
		try{
			DataElement de=data.getDataElement();
			String tabella=de.getNomeclasse().substring(21);
		    statement="insert into credenziali_"+tabella+"(credenziali_id,"+tabella+"_id)"+ "values('"+credenziali_id+"','"+dataInterface_id+"')";
		    
		}catch(Exception e){
			throw new QueryException("Query Error: unable to get insert link stat");
		}
		return statement;
	}
	private String searchDataStatement(String index) throws QueryException{
		String statement=null;
		StringTokenizer st=new StringTokenizer(index,"-");
		String tabella=st.nextToken();
		try{
			statement="select * from credenzialiutente,"+tabella+","+"credenziali_"+tabella+" where";
			switch(tabella){
			case "DatiStudente":
				statement+="(nome,cognome,matricola)="+"('"+st.nextToken()+"','"+st.nextToken()+"','"+st.nextToken()+"')"
						+ " and credenzialiutente.credenziali_id=credenziali_"+tabella+".credenziali_id  and "+tabella+"."
								+tabella+"_id=credenziali_"+tabella+"."+tabella+"_id";
				break;
			case "DatiUtente":
				statement+="(nome,cognome,materia)="+"('"+st.nextToken()+"','"+st.nextToken()+"','"+st.nextToken()+"')"
						+ " and credenzialiutente.credenziali_id=credenziali_"+tabella+".credenziali_id  and "+tabella+"."
						+tabella+"_id=credenziali_"+tabella+"."+tabella+"_id";
				
				break;
			case "DatiAzienda":
				statement+="(nome,PIva)="+"('"+st.nextToken()+"','"+st.nextToken()+"')"
						+ " and credenzialiutente.credenziali_id=credenziali_"+tabella+".credenziali_id  and "+tabella+"."
						+tabella+"_id=credenziali_"+tabella+"."+tabella+"_id";
				break;
			}
		}catch(Exception e){
			throw new QueryException("Query Error: bad index ");
		}
		return statement;
	}
	
	
	public String getClassName(){
		return className;
	}
	@Override
	public QuerySet update(DataInterface dataInter) throws QueryException {
		
		QuerySet qs=new QuerySet();
		qs.add(new QueryElement(insertDataInterfaceStatement(dataInter)));
		qs.add(new QueryElement(insertCredentialStatement(dataInter)));
		qs.add(new QueryElement(insertLinkStatement(dataInter)));
		return qs;
	}
	@Override
	public QuerySet select(String index) throws QueryException {
		QuerySet qs=new QuerySet();
		qs.add(new QueryElement(searchDataStatement(index)));
		
		return qs;
	}
	
	@Override
	public String getPackageName(){
		return "corsojava.anagrafica";
	}
	@Override
	public DataInterface buildObjectfrom(ResultSet rs, String index) throws StorageException {
		DataInterface data;
		try{
			StringTokenizer st=new StringTokenizer(index,"-");
			data=(DataInterface)Class.forName("corsojava.anagrafica."+st.nextToken()).newInstance();
			
			ResultSetMetaData rsm=rs.getMetaData();
			
			DataElement de=new DataElement();
			HashMap<String,String> hm=new HashMap<String,String>();
			
			if(!rs.next()) throw new StorageException("Storage Error: entry not found");
			for(int i=1; i<rsm.getColumnCount(); i++){
				hm.put(rsm.getColumnName(i), rs.getString(i));
			}
			de.setData(hm);
			data.setDataElement(de);
		} catch (Exception e) {
			throw new StorageException("Storage Error: "+e.getMessage());
		}
		return data;
		}
		
	
	
	
}
	

	

	

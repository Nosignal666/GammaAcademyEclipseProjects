package corsojava.dbstorage;

public class QueryElement {
	String statement;
	public QueryElement(String statement){
		this.statement=statement;
	}

	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}

}

package corsojava.dbstorage;

public class QueryException extends Exception{

	private static final long serialVersionUID = 7632621215905136146L;

	public QueryException() {
		super();
	}

	public QueryException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

	public QueryException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public QueryException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public QueryException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}

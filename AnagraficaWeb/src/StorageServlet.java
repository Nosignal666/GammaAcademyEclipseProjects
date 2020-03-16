

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import corsojava.storage.DataElement;
import corsojava.storage.DataInterface;
import corsojava.storage.StorageException;
import corsojava.storage.StorageManager;

/**
 * Servlet implementation class StorageServlet
 */
@WebServlet("/StorageServlet") //si può modificare qui l'url per la chimat della servlet
public class StorageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	StorageManager sm;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StorageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    



	@Override
	public void init() throws ServletException {
		super.init();
		sm=StorageConfigurator.getStorageManager();
		
	}





	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		String index=request.getParameter("index");
		try {
			DataInterface di=sm.readData(index);
			ObjectMapper mapper=new ObjectMapper();
			String jsonString=mapper.writeValueAsString(di);
			PrintWriter out=response.getWriter();
			out.println(jsonString);
			out.flush();
		} catch (StorageException e) {
			e.printStackTrace();
			throw new ServletException("ERROR: "+ e.getClass().getName()+": "+e.getMessage());
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException{
		// TODO Auto-generated method stub
		try {
			DataElement de=new DataElement();
			
			
			HashMap<String,String> hm=new HashMap<String,String>();
			Enumeration<String> parameters=request.getParameterNames();
			while(parameters.hasMoreElements()){
				String key=parameters.nextElement();
				String value=request.getParameter(key);
				hm.put(key, value);
			}
			
			DataInterface di=(DataInterface)Class.forName(request.getParameter("type")).newInstance();
			de.setData(hm);
			di.setDataElement(de);
			sm.writeData(di);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException("ERROR: "+ e.getClass().getName()+": "+e.getMessage());
		}
		
	}

}

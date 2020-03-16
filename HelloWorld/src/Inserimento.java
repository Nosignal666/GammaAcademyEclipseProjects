

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class Inserimento
 */
@WebServlet("/Inserimento")
public class Inserimento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Inserimento() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		Enumeration<String> parameterName=request.getParameterNames();
		FileWriter fw=new FileWriter("C:\\Users\\Gamma.Academy\\Desktop\\datiInseriti.txt",true);
		PrintWriter tomyfile=new PrintWriter(fw);
		while(parameterName.hasMoreElements()){
			String name=(String) parameterName.nextElement();
			String value=request.getParameter(name);
			if(name.compareTo("submit")!=0){
				tomyfile.println(name+"="+value);
				}
		}
		fw.close();
		tomyfile.close();
		response.getWriter().println("<html><body>Grazie e arrivederci</body></html>");
		response.getWriter().close();
	}

}

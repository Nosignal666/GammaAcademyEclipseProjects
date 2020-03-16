

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Session1
 */
@WebServlet("/Session1")
public class Session1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Session1() {
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
		doGet(request, response);
	}
	
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setContentType("text/html");
		HttpSession session=request.getSession(true);
		PrintWriter out=response.getWriter();
		if(session.isNew()){
			out.println("<html><body>Tempo di creazione: "+session.getCreationTime());
			session.setAttribute("accessi", new Integer(1));
		}
		else{
			int accessi=((Integer)session.getAttribute("accessi")).intValue();
			accessi++;
			session.setAttribute("accessi", new Integer(accessi));
			out.println("<br>numero di accessi: "+accessi);
		}
		out.println("</body></html>");
		out.close();
	}

}

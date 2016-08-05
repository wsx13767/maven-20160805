

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.LoginCheckDAO;
import dao.RegisterDAO;
import dao.UserDataDAO;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 response.setContentType("text/plain");
		 response.setCharacterEncoding("UTF-8");
		 request.setCharacterEncoding("UTF-8");
		 String account = String.valueOf(request.getParameter("account"));
		 String password = String.valueOf(request.getParameter("password"));
		 String name = String.valueOf(request.getParameter("name"));
		 String phone = String.valueOf(request.getParameter("phone"));
		 String email = String.valueOf(request.getParameter("email"));
		 
			  
		 
		 try {
				
				boolean n =  new RegisterDAO().Register(account,password,name,phone,email);
		    	
		    	PrintWriter out = response.getWriter();
			    out.print(n);
				out.flush();
				out.close();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		 
		 

	}

}

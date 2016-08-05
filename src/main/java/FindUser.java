

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import model.UserData;

import dao.JsonDAO;
import dao.UserDataDAO;

/**
 * Servlet implementation class FindUser
 */
@WebServlet("/FindUser")
public class FindUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindUser() {
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
	
				try {
					UserDataDAO alluser =new UserDataDAO();
					JsonDAO userdatajson = new JsonDAO();
					List< UserData > list = alluser.findAll();
					String a = userdatajson.getUSERDATAJSON(list);
					response.setContentType("text/plain");
					response.setCharacterEncoding("UTF-8");
					PrintWriter out = response.getWriter();
					out.write(a);
					out.flush();
					out.close();
					
					
				} catch (Exception e) {
					// TODO Auto-generated catch block 
					e.printStackTrace();
					
				}
	}

}

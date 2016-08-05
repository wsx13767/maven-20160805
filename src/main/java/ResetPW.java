

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UserData;





import dao.RegisterDAO;
import dao.UserDataDAO;


/**
 * Servlet implementation class FindUser
 */
@WebServlet("/ResetPW")
public class ResetPW extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResetPW() {
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
		String checkCode = String.valueOf(request.getParameter("checkcode"));
		String password = String.valueOf(request.getParameter("password"));
		String account = String.valueOf(request.getParameter("account"));
		
		
		
		try {
			GenerateLink check = new GenerateLink();
			String syscheckcode = check.generateCheckcode(account);
			
			if(syscheckcode.equals(checkCode)){
				UserDataDAO user = new UserDataDAO();
				int user_id =(int) user.findByAccount(account).getUserId();
				 user = new UserDataDAO();
				user.updatePassword(user_id, password);
				PrintWriter out = response.getWriter();
			    out.print(true);
				out.flush();
				out.close();
				
				
			}
			else{
				
				PrintWriter out = response.getWriter();
			    out.print(false);
				out.flush();
				out.close();
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

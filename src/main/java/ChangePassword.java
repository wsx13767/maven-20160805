

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDataDAO;
import model.UserData;

/**
 * Servlet implementation class accountInfo
 */
@WebServlet("/accountInfo")
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePassword() {
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
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("utf-8");
		//Create Object
		UserData nowUser = null;
		UserDataDAO dao = null;
		
		//get the newest user_data
		try {
			dao = new UserDataDAO();
			nowUser = new UserDataDAO().findByAccount(request.getParameter("userAccount"));
		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		
		//set variable
		int user_id = (int) nowUser.getUserId();
		String oPwd = request.getParameter("Opwd");
		String nPwd = request.getParameter("Npwd");
		String cPwd = request.getParameter("Cpwd");
		
		//test empty
		if(!oPwd.isEmpty() && !nPwd.isEmpty() && !cPwd.isEmpty()){
			//test password condition
			if(oPwd.equals(nowUser.getPassword()) && nPwd.length()>5 && nPwd.length()<22 && cPwd.equals(nPwd)){
				//update password
				try {
					dao.updatePassword(user_id, nPwd);
					//success message
					request.setAttribute("rs", "0");
					request.getRequestDispatcher("./changeRequest.jsp").forward(request, response);
					//---------------
				} catch (Exception e) {
					e.printStackTrace();
					//db out message
					request.setAttribute("rs", "1");
					request.getRequestDispatcher("./changeRequest.jsp").forward(request, response);
					//-----------
				}
				return;
			}
			//test out
			request.setAttribute("rs", "2");
			request.getRequestDispatcher("./changeRequest.jsp").forward(request, response);
			//--------
			return;
		}
		//null message
		request.setAttribute("rs", "3");
		request.getRequestDispatcher("./changeRequest.jsp").forward(request, response);
		//----------------
	}

}

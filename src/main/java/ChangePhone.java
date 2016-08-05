

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
public class ChangePhone extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePhone() {
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
		String nPh = request.getParameter("Nph");
		
		//test empty
		if(!nPh.isEmpty()){
			if(nPh.length()<11){
				//update phone
				try {
					dao.updatePhone(user_id, nPh);
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
			//test out message
			request.setAttribute("rs", "2");
			request.getRequestDispatcher("./changeRequest.jsp").forward(request, response);
			//---------------
		}
		//null message
		request.setAttribute("rs", "3");
		request.getRequestDispatcher("./changeRequest.jsp").forward(request, response);
		//----------------
	}

}

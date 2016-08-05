

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
@WebServlet("/ForgetPW")
public class ForgetPW extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForgetPW() {
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
		String email = String.valueOf(request.getParameter("useremail"));
		
		try {
			
			// email檢查 如果email已存在回傳F email不存在回傳T
			boolean a = new RegisterDAO().RegisterEmailCheck(email);
			if(a){
				PrintWriter out = response.getWriter();
			    out.print(a);
				out.flush();
				out.close();
				
				
			}
			else{
				UserDataDAO user = new UserDataDAO();
				String account = user.findByEmail(email).getAccount();
				user = new UserDataDAO();
				long userid = user.findByEmail(email).getUserId();
				user = new UserDataDAO();
				String userName = user.findByEmail(email).getName();
				//產生LINK
				GenerateLink link = new GenerateLink();
				String resetlink = link.generateResetPwdLink(userid, account);
				
				//寄EMAIL
				ForgetPwMail send = new ForgetPwMail();
				send.setMessage(userName, resetlink);
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

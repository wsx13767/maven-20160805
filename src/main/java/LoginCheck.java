

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UserData;

import dao.LoginCheckDAO;
import dao.UserDataDAO;


/**
 * Servlet implementation class LoginCheck
 */
@WebServlet("/LoginCheck")
public class LoginCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginCheck() {
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
		    String account = String.valueOf(request.getParameter("userID"));
		    String passwd = String.valueOf(request.getParameter("passWD"));
		    UserData userdata = null;
		    
		    
		 
		    // 判斷帳號密碼是否為NULL
		    if(account==null || passwd == null)
		    {
		    	response.sendRedirect("index.jsp");
		    	return;
		    }
		    		    
		    //查詢DB是否有帳號密碼 回傳 T or F
		    try {
		    	
		    	boolean n =  new LoginCheckDAO().FindIDPW(account, passwd);
		    	if(n){
		    		userdata = new UserDataDAO().findByAccount(account);
		    		request.getSession().setAttribute("login", account);
		    		
		    	}
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

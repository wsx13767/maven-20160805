

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.JoinOrderDAO;

/**
 * Servlet implementation class accountInfo
 */
@WebServlet("/orderSearchMoreInfo")
public class ChangeGet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeGet() {
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
		JoinOrderDAO joinOrderDAO = null;
		System.out.println("test");
		String join_log = request.getParameter("getNO");
		
		try {
			joinOrderDAO = new JoinOrderDAO();
			joinOrderDAO.updateGet(join_log);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		request.getRequestDispatcher("/updateGetSuccess.jsp").forward(request, response);
	}

}

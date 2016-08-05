

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BuildOrderDAO;

/**
 * Servlet implementation class accountInfo
 */
@WebServlet("/orderManagenmentMore")
public class ChangeState extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeState() {
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
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		BuildOrderDAO buildOrderDAO = null;
		String order_number = request.getParameter("searchOrder");
		
		try {
			buildOrderDAO = new BuildOrderDAO();
			buildOrderDAO.updateStateToDead(order_number);
			out.println("<html><head><meta charset=\"UTF-8\"></head>");
			out.println("<body><script type=\"text/javascript\">"
					+"alert('截止成功');"
					+"window.location = './orderManagementMore.jsp?searchOrder="+order_number+"';"
					+"</script></body></html>");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

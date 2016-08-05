import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CountJoinItem;


import dao.CountJoinItemDAO;
import dao.JsonDAO;

/**
 * Servlet implementation class MyServPushJsp
 */
@WebServlet("/SendJoinOrderItemCount")
public class SendJoinOrderItemCountAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendJoinOrderItemCountAjax() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding( "UTF-8" );
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		String order=request.getParameter("order");
		String sql="SELECT ORDER_NUMBER, SUM(COUNT)\n"+
							"FROM JOIN_ORDER\n"+
							"WHERE ORDER_NUMBER="+order+"\n"+
							"GROUP BY ORDER_NUMBER\n"+
							"ORDER BY TO_NUMBER(ORDER_NUMBER)\n" ;
		try {
			CountJoinItemDAO cjid = new CountJoinItemDAO();
			JsonDAO jdao=new JsonDAO();
			List<CountJoinItem> list = cjid.findAll(sql);
			if(list.isEmpty())
				response.getWriter().write("NoData");
			else
				response.getWriter().write(jdao.getCountJoinItem(list));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

}

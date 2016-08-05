import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.JoinOrder;


import dao.JsonDAO;
import dao.jQcloudDAO;

/**
 * Servlet implementation class MyServPushJsp
 */
@WebServlet("/jQcloudAjax")
public class jQcloudAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	public jQcloudAjax() {
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
		String sql="SELECT  JOIN_ORDER.ITEM_NUMBER,JOIN_ORDER.COUNT,ITEM_INFORMATION.NAME,ITEM_INFORMATION.ORDER_NUMBER " +
							"FROM JOIN_ORDER,ITEM_INFORMATION,BUILD_ORDER " +
							"WHERE JOIN_ORDER.ITEM_NUMBER=ITEM_INFORMATION.ITEM_NUMBER "+
							"AND ITEM_INFORMATION.ORDER_NUMBER=BUILD_ORDER.ORDER_NUMBER "+
							"AND BUILD_ORDER.BUILD_STATE='live' ";
		try {
			jQcloudDAO jod = new jQcloudDAO();
			JsonDAO jdao=new JsonDAO();
			List<JoinOrder> list = jod.findAll(sql);
				response.getWriter().write(jdao.getJoinOrderJSON(list));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}

}

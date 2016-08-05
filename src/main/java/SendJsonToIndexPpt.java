import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.PptItem;


import dao.PptItemDAO;
import dao.JsonDAO;

/**
 * Servlet implementation class MyServPushJsp
 */
@WebServlet("/SendJsonToIndexPpt")
public class SendJsonToIndexPpt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendJsonToIndexPpt() {
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
		String sql="SELECT BUILD_ORDER.ORDER_NUMBER,ITEM_INFORMATION,PICTURE "+
							"FROM BUILD_ORDER,(SELECT COUNT(ORDER_NUMBER) A,ORDER_NUMBER B "+
																        "FROM (SELECT DISTINCT JOIN_TIME,ORDER_NUMBER "+ 
																              			"FROM JOIN_ORDER) "+
																        "GROUP BY ORDER_NUMBER "+
																        "ORDER BY JOIN_TIME DESC) C "+
							"WHERE BUILD_ORDER.ORDER_NUMBER=C.B "+
							"AND BUILD_ORDER.BUILD_STATE='live' "+
							"AND DEADLINE-SYSDATE>0 "+
							"ORDER BY C.A DESC,BUILD_ORDER.ORDER_NUMBER";
		try {
			PptItemDAO jod = new PptItemDAO();
			JsonDAO jdao=new JsonDAO();
			List<PptItem> list = jod.findAll(sql);
			if(list.isEmpty())
				response.getWriter().write("NoData");
			else
				response.getWriter().write(jdao.getPptItem(list));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}

}

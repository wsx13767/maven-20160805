import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.JoinOrder;


import dao.JoinOrderItemDAO;
import dao.JsonDAO;
import dao.OrderInformationDAO;

/**
 * Servlet implementation class MyServPushJsp
 */
@WebServlet("/OrderInformation")
public class OrderInformation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderInformation() {
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
		String sql="SELECT BUILD_ORDER.ORDER_NUMBER,TO_CHAR(BUILD_ORDER.DEADLINE,'yyyy-mm-dd hh24:mi:ss'),TO_CHAR(BUILD_ORDER.BUILD_TIME,'yyyy-mm-dd hh24:mi:ss'),BUILD_ORDER.REMARK,ITEM_INFORMATION.MONEY,BUILD_ORDER.ITEM_INFORMATION,BUILD_ORDER.REMARK,BUILD_ORDER.PICTURE,ITEM_INFORMATION.NAME,ITEM_INFORMATION.MAX,USER_DATA.ACCOUNT,ITEM_INFORMATION.MIN,ITEM_INFORMATION.BIG_PRICE,ITEM_INFORMATION.MID_PRICE,ITEM_INFORMATION.SMALL_PRICE,ITEM_INFORMATION.SUGER,ITEM_INFORMATION.ITEM_NUMBER,ITEM_INFORMATION.ICE,ITEM_INFORMATION.\"SIZE\"\n"+
							"FROM  ITEM_INFORMATION,BUILD_ORDER,USER_DATA\n"+
							"WHERE ITEM_INFORMATION.ORDER_NUMBER=BUILD_ORDER.ORDER_NUMBER\n"+
							"AND ITEM_INFORMATION.USER_ID=BUILD_ORDER.USER_ID\n"+
							"AND BUILD_ORDER.USER_ID=USER_DATA.USER_ID\n"+
							"AND ITEM_INFORMATION.ORDER_NUMBER="+order+"\n"+
							"ORDER BY TO_NUMBER(BUILD_ORDER.ORDER_NUMBER),ITEM_INFORMATION.MONEY\n" ;
		try {
			OrderInformationDAO oid = new OrderInformationDAO();
			JsonDAO jdao=new JsonDAO();
			List<JoinOrder> list = oid.findAll(sql);
			if(list.isEmpty())
				response.getWriter().write("NoData");
			else
				response.getWriter().write(jdao.getJoinOrderJSON(list));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}

}

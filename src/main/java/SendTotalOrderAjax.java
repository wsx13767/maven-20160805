import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import model.TotalOrderCount;

import dao.JsonDAO;
import dao.TotalOrderCountDAO;

/**
 * Servlet implementation class MyServPushJsp
 */
@WebServlet("/SendTotalOrderAjax")
public class SendTotalOrderAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendTotalOrderAjax() {
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
		String category=request.getParameter("category");
		if(category.equals("DRINK")){
			category="AND ITEM_INFORMATION.MONEY IS NULL ";
		}else if(category.equals("FOOD")){
			category="AND ITEM_INFORMATION.MONEY IS NOT NULL ";
		}else{
			category="";
		}	
		String sql="SELECT COUNT(DISTINCT BUILD_ORDER.ORDER_NUMBER) "+
							"FROM BUILD_ORDER,ITEM_INFORMATION "+
							"WHERE BUILD_ORDER.ORDER_NUMBER=ITEM_INFORMATION.ORDER_NUMBER "+
							"AND BUILD_ORDER.USER_ID=ITEM_INFORMATION.USER_ID  "+
							category+
							"AND DEADLINE-SYSDATE>0 "+
							"AND BUILD_STATE='live'";
		try {
			TotalOrderCountDAO tocd = new TotalOrderCountDAO();
			JsonDAO jdao=new JsonDAO();
			List<TotalOrderCount> list = tocd.findAll(sql);
			if(list.isEmpty())
				response.getWriter().write("NoData");
			else
				response.getWriter().write(jdao.getTotalOrderCount(list));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}

}

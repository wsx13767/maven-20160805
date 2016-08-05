import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.IndexItem;


import dao.IndexItemDAO;
import dao.JsonDAO;

/**
 * Servlet implementation class MyServPushJsp
 */
@WebServlet("/SendIndexItem")
public class SendIndexItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendIndexItem() {
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
		String row=request.getParameter("row");
		int rowInt=Integer.valueOf(row);
		//System.out.println(row);
		String sql="SELECT ORDER_NUMBER,ITEM_INFORMATION,REMARK,PICTURE "+
							"FROM BUILD_ORDER "+
							"WHERE BUILD_STATE='live' "+
							"AND DEADLINE-SYSDATE>0 "+
							"AND ROWNUM <"+(rowInt+2)+" "+
							"MINUS "+
							"SELECT ORDER_NUMBER,ITEM_INFORMATION,REMARK,PICTURE "+
							"FROM BUILD_ORDER "+
							"WHERE BUILD_STATE='live' "+
							"AND DEADLINE-SYSDATE>0 "+
							"AND ROWNUM <"+rowInt+" "+
							"ORDER BY ORDER_NUMBER ";
		
		try {
			IndexItemDAO jod = new IndexItemDAO();
			JsonDAO jdao=new JsonDAO();
			List<IndexItem> list = jod.findAll(sql);
			if(list.isEmpty())
				response.getWriter().write("NoData");
			else
				response.getWriter().write(jdao.getIndexItem(list));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}

}

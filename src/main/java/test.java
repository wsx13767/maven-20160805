import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.InsertJoinOrderDAO;

import model.InsertJoinOrder;

/**
 * Servlet implementation class test
 */
@WebServlet("/test")
public class test extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public test() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
				request.setCharacterEncoding("UTF-8");
				response.setCharacterEncoding("utf-8");
				response.setContentType("text/html;charset=utf-8");//各種設定編碼避免錯誤
				List<InsertJoinOrder> list = new ArrayList<InsertJoinOrder>();	
				String orderNumber="";
				String userId="";
				String userName="";
				int totalMoney=0;
				orderNumber=request.getParameter("order_number");
				userId=request.getParameter("user_id");
				userName=request.getParameter("user_name");
				totalMoney=Integer.parseInt(request.getParameter("j_total_money").trim());
				int i = 0;
				String k="";
				while (request.getParameter("j_item_" + i) != null) {
					
					InsertJoinOrder ijo=new InsertJoinOrder();
					
					// 商品名稱
					ijo.setJItem(request.getParameter("j_item_" + i));//
				
					
					// 商品數量
					ijo.setJItemQuan(request.getParameter("j_item_quan_"+i));
			
					// 商品價格
					ijo.setJPrice(request.getParameter("j_price_" + i));
							
					// 單項總價
					ijo.setJTotalPrice(request.getParameter("j_total_price_" + i));
					
					//項目編號
					ijo.setJIndex(request.getParameter("j_index_" + i));
					
					//商品編號
					ijo.setItemNumber(request.getParameter("j_itemNumber_" + i));
					
					//大小
					ijo.setJSize(request.getParameter("j_chose_" + i));
					
					//甜度
					ijo.setJSuger(request.getParameter("j_chose_suger_" + i));
					
					//冰塊
					ijo.setJIce(request.getParameter("j_chose_ice_" + i));

					try {
						list.add(ijo);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//System.out.println("testi= "+i);
					//System.out.println(totalMoney);
					i++;
				}
				try {
					InsertJoinOrderDAO ijod=new InsertJoinOrderDAO();
					k=ijod.insert(list, orderNumber, userId, totalMoney);	
					JoinOrderJavaMail jojm=new JoinOrderJavaMail();
					jojm.setMessage(list,totalMoney,orderNumber,userName);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				response.getWriter().write(k);
	}

}

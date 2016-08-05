
import model.BuildOrder;
import model.UserData;
import dao.BuildItemDAO;
import dao.UserDataDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;





/**
 * Servlet implementation class buildOrderServlet
 */
@WebServlet("/buildOrderServlet")
public class buildOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String BuildOrder = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public buildOrderServlet() {
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
		response.setContentType("text/json; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
				
		
		
		//抓取食品訂單內容宣告
		String title = request.getParameter("food_title_name");
		String deadline = request.getParameter("food_deadline_name")+" "+request.getParameter("food_deadline_name2");
		String itemNeedCounter = request.getParameter("food_itemNeedCounter_name");
		String itemInformation = request.getParameter("food_itemInformation_name");
		String itemName = request.getParameter("food_itemName_name1");
		String money = request.getParameter("food_money_name1");
		String counter = request.getParameter("food_counter_name");
		String picture = request.getParameter("food_picture_name");
		String userID = request.getParameter("userID");
		
		//新增食品
		String itemName2 = request.getParameter("food_itemName_name2");
		String money2 = request.getParameter("food_money_name2");
		String counter2 = request.getParameter("food_counter_name2");
		String itemName3 = request.getParameter("food_itemName_name3");
		String money3 = request.getParameter("food_money_name3");
		String counter3 = request.getParameter("food_counter_name3");
		String itemName4 = request.getParameter("food_itemName_name4");
		String money4 = request.getParameter("food_money_name4");
		String counter4 = request.getParameter("food_counter_name4");
		//截止時間格式
		SimpleDateFormat sdf01 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date = null;
		
		//系統時間格式
		Calendar cl=Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
		String sysdate = sdf.format(cl.getTime());

		
		int n=0;
		BuildItemDAO buildItemDAO = null;
		//只有1樣商品
		if(itemName2 == null  &&  itemName3 == null && itemName4 == null){
			try{

				n = new BuildItemDAO().BuildOrder(title, itemInformation,deadline,itemName,itemNeedCounter,money,counter,userID,picture);
				request.getRequestDispatcher("./buildPass.jsp").forward(request,response);
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//兩樣商品
		}else if(itemName3 == null && itemName4 ==null){
			try{
				n = new BuildItemDAO().BuildOrder2(title, itemInformation,deadline,itemName,itemNeedCounter,money,counter,userID,itemName2,money2,picture);
				request.getRequestDispatcher("./buildPass.jsp").forward(request,response);
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//三樣商品
		}else if(itemName4 == null){
			try{
				n = new BuildItemDAO().BuildOrder3(title, itemInformation,deadline,itemName,itemNeedCounter,money,counter,userID,itemName2,money2,itemName3,money3,picture);
				request.getRequestDispatcher("./buildPass.jsp").forward(request,response);
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//四樣商品
		}else{
			try{
			n = new BuildItemDAO().BuildOrder4(title, itemInformation,deadline,itemName,itemNeedCounter,money,counter,userID,itemName2,money2,itemName3,money3,itemName4,money4,picture);
			request.getRequestDispatcher("./buildPass.jsp").forward(request,response);
			}catch(Exception e){
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		
		
		
		
		
		
		
	}
		
}
		
		
		
		
		
	
		

	

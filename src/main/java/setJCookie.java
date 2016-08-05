import java.io.IOException;

import java.io.UnsupportedEncodingException;

import java.net.URLEncoder;

import java.util.ArrayList;

import java.util.List;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.JsonDAO;

import model.JOrderCookies;



/**
 * Servlet implementation class setJCookie
 */
@WebServlet("/setJCookie")
public class setJCookie extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JOrderCookies JOrderCookies=new JOrderCookies();
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public setJCookie() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @throws UnsupportedEncodingException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */	
	
	
	/*public static String mapToJSON(Map map) {
		return JSONObject.fromObject(map).toString(); map轉換json
	}*/
	
	
	private void setCookie(String json, HttpServletResponse response,String orderNumber,String userId) {
		Cookie jsonCookie = new Cookie("joinOrder_jsonCookie_"+orderNumber+"_"+userId.trim(), json);// 索引值,資料內容
		//System.out.println("joinOrder_jsonCookie_"+orderNumber+"_"+userId);
		response.addCookie(jsonCookie);// 新增一個COOKIE
		jsonCookie.setMaxAge( 60 * 60*24);// COOKIE存活時間 
		//System.out.println(jsonCookie.getValue());
	}
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");//各種設定編碼避免錯誤

		//@SuppressWarnings("rawtypes")
		/*Map map = new HashMap();
		map=request.getParameterMap();*/
		String orderNumber="";
		String userId="";
		orderNumber=request.getParameter("order_number");
		userId=request.getParameter("user_id");
		List<JOrderCookies> list = new ArrayList<JOrderCookies>();	
		int i = 0;
		while (request.getParameter("j_item_" + i) != null) {
			//map.put("j_item" , URLEncoder.encode(request.getParameter("j_item_" + i),"UTF-8"));用map不好存取,失敗了...
			
			JOrderCookies JOrderCookies=new JOrderCookies();
			
			// 商品名稱
			JOrderCookies.setJItem(URLEncoder.encode(request.getParameter("j_item_" + i),"UTF-8"));//
			//URLEncoder.encode(需要throws UnsupportedEncodingException )-->cookie輸入中文要先換編碼然後再用decode解碼
			//.....用URLEncoder把中文換掉JQUERY COOKIE套件竟然自動把他換回中文.......
			
			// 商品數量
			JOrderCookies.setJItemQuan(request.getParameter("j_item_quan_"+i));
	
			// 商品價格
			JOrderCookies.setJPrice(request.getParameter("j_price_" + i));
					
			// 單項總價
			JOrderCookies.setJTotalPrice(request.getParameter("j_total_price_" + i));
			
			//項目編號
			JOrderCookies.setJIndex(request.getParameter("j_index_" + i));
			
			//商品編號
			JOrderCookies.setItemNumber(request.getParameter("j_itemNumber_" + i));
			
			//大小
			JOrderCookies.setJSize(request.getParameter("j_chose_" + i));
			
			//甜度
			JOrderCookies.setJSuger(request.getParameter("j_chose_suger_" + i));
			
			//冰塊
			JOrderCookies.setJIce(request.getParameter("j_chose_ice_" + i));

			try {
				list.add(JOrderCookies);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			i++;
		}
		/*--將list轉json--*/
		JsonDAO jd=new JsonDAO();	
		String json=jd.getJOrderCookies(list);
		//System.out.print(json);檢查有沒有東西
		
		/*--將json設為cookie--*/
		setCookie(json,response,orderNumber,userId);
		//System.out.println(list.get(1).getJIce());
		//轉至確認頁面
		 ServletContext sc = getServletContext();   
		 RequestDispatcher rd = null;   
		 rd = sc.getRequestDispatcher("/confirmJOder.jsp"); //定向的頁面   
		rd.forward(request, response);   
	}
}

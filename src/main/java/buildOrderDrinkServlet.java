

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

import com.objectdb.o.VAR;

import dao.BuildItemDAO;

/**
 * Servlet implementation class buildOrderDrinkServlet
 */
@WebServlet("/buildOrderDrinkServlet")
public class buildOrderDrinkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public buildOrderDrinkServlet() {
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

		//抓取飲料內容宣告
		String title = request.getParameter("drink_title_name");
		String deadline = request.getParameter("drink_deadline_name")+" "+request.getParameter("drink_deadline_name2");
		String itemNeedCounter = request.getParameter("drink_itemNeedcounter_name");
		String itemInformation = request.getParameter("drink_itemInformation_name");
		String itemName = request.getParameter("drink_itemName_name1");
		String bigMoney = request.getParameter("drink_bigMoney_name1");
		String midMoney = request.getParameter("drink_midMoney_name1");
		String smallMoney = request.getParameter("drink_smallMoney_name1");
		String counter = request.getParameter("drink_counter_name");
		String userID = request.getParameter("userID");
		String picture = request.getParameter("myPhoto");
		String[] ice =request.getParameterValues("ice1");
		String[] sugar =request.getParameterValues("sugar1");
		String[] size =request.getParameterValues("size1");
		int i=0;
		//冰塊字串宣告
		String iceString = null;
		if(ice.length==1){
			 iceString = ice[0];	
		}else if(ice.length==2){
			 iceString = ice[0]+"/"+ice[1];
		}else if(ice.length==3){
			 iceString = ice[0]+"/"+ice[1]+"/"+ice[2];
		}else if(ice.length==4){
			 iceString = ice[0]+"/"+ice[1]+"/"+ice[2]+"/"+ice[3];
		}else if(ice.length==5){
			 iceString = ice[0]+"/"+ice[1]+"/"+ice[2]+"/"+ice[3]+"/"+ice[4];
		}
		//甜度字串宣告
		String sugarString = null;
		if(sugar.length==1){
			sugarString = sugar[0];	
		}else if(sugar.length==2){
			sugarString = sugar[0]+"/"+sugar[1];
		}else if(sugar.length==3){
			sugarString = sugar[0]+"/"+sugar[1]+"/"+sugar[2];
		}else if(sugar.length==4){
			sugarString = sugar[0]+"/"+sugar[1]+"/"+sugar[2]+"/"+sugar[3];
		}else if(sugar.length==5){
			sugarString = sugar[0]+"/"+sugar[1]+"/"+sugar[2]+"/"+sugar[3]+"/"+sugar[4];
		}
		//大小字串宣告
		String sizeString = null;
		if(size.length==1){
			sizeString = size[0];
		}else if(size.length==2){
			sizeString = size[0]+"/"+size[1];
		}else if(size.length==3){
			sizeString = size[0]+"/"+size[1]+"/"+size[2];
		}
//		out.println("冰塊"+iceString);
//		out.println("甜度"+sugarString);
//		out.println("大小"+sizeString);
		
		
		
		//新增飲品2
		String itemName2 = request.getParameter("drink_itemName_name2");
		String bigMoney2 = request.getParameter("drink_bigMoney_name2");
		String midMoney2 = request.getParameter("drink_midMoney_name2");
		String smallMoney2 = request.getParameter("drink_smallMoney_name2");
		String counter2 = request.getParameter("drink_counter_name2");
		String[] ice2 =request.getParameterValues("ice2");
		String[] sugar2 =request.getParameterValues("sugar2");
		String[] size2 =request.getParameterValues("size2");
		//冰塊字串宣告
		String iceString2 = null;
		if(ice2 !=null){
			if(ice2.length==1){
				 iceString2 = ice2[0];	
			}else if(ice2.length==2){
				 iceString2 = ice2[0]+"/"+ice2[1];
			}else if(ice2.length==3){
				 iceString2 = ice2[0]+"/"+ice2[1]+"/"+ice2[2];
			}else if(ice2.length==4){
				 iceString2 = ice2[0]+"/"+ice2[1]+"/"+ice2[2]+"/"+ice2[3];
			}else if(ice2.length==5){
				 iceString2 = ice2[0]+"/"+ice2[1]+"/"+ice2[2]+"/"+ice2[3]+"/"+ice2[4];
			}
		}
//		//甜度字串宣告
		String sugarString2 = null;
		if(sugar2 !=null){
			if(sugar2.length==1){
				sugarString2 = sugar2[0];	
			}else if(sugar2.length==2){
				sugarString2 = sugar2[0]+"/"+sugar2[1];
			}else if(sugar2.length==3){
				sugarString2 = sugar2[0]+"/"+sugar2[1]+"/"+sugar2[2];
			}else if(sugar2.length==4){
				sugarString2 = sugar2[0]+"/"+sugar2[1]+"/"+sugar2[2]+"/"+sugar2[3];
			}else if(sugar2.length==5){
				sugarString2 = sugar2[0]+"/"+sugar2[1]+"/"+sugar2[2]+"/"+sugar2[3]+"/"+sugar2[4];
			}
		}
		//大小字串宣告
		String sizeString2 = null;
		if(size2 !=null){
			if(size2.length==1){
				sizeString2 = size2[0];
			}else if(size2.length==2){
				sizeString2 = size2[0]+"/"+size2[1];
			}else if(size2.length==3){
				sizeString2 = size2[0]+"/"+size2[1]+"/"+size2[2];
			}
		}
//		out.println("冰塊"+iceString2);
//		out.println("甜度"+sugarString2);
//		out.println("大小"+sizeString2);	
		
		
		
		
		//新增飲品3
		String itemName3 = request.getParameter("drink_itemName_name3");
		String bigMoney3 = request.getParameter("drink_bigMoney_name3");
		String midMoney3 = request.getParameter("drink_midMoney_name3");
		String smallMoney3 = request.getParameter("drink_smallMoney_name3");
		String counter3 = request.getParameter("drink_counter_name3");
		String[] ice3 =request.getParameterValues("ice3");
		String[] sugar3 =request.getParameterValues("sugar3");
		String[] size3 =request.getParameterValues("size3");
		//冰塊字串宣告
		String iceString3 = null;
		if(ice3 !=null){
			if(ice3.length==1){
				 iceString3 = ice3[0];	
			}else if(ice3.length==2){
				 iceString3 = ice3[0]+"/"+ice3[1];
			}else if(ice3.length==3){
				 iceString3 = ice3[0]+"/"+ice3[1]+"/"+ice3[2];
			}else if(ice3.length==4){
				 iceString3 = ice3[0]+"/"+ice3[1]+"/"+ice3[2]+"/"+ice3[3];
			}else if(ice3.length==5){
				 iceString3 = ice3[0]+"/"+ice3[1]+"/"+ice3[2]+"/"+ice3[3]+"/"+ice3[4];
			}
		}
		//甜度字串宣告
		String sugarString3 = null;
		if(sugar3 !=null){
			if(sugar3.length==1){
				sugarString3 = sugar3[0];	
			}else if(sugar3.length==2){
				sugarString3 = sugar3[0]+"/"+sugar3[1];
			}else if(sugar3.length==3){
				sugarString3 = sugar3[0]+"/"+sugar3[1]+"/"+sugar3[2];
			}else if(sugar3.length==4){
				sugarString3 = sugar3[0]+"/"+sugar3[1]+"/"+sugar3[2]+"/"+sugar3[3];
			}else if(sugar3.length==5){
				sugarString3 = sugar3[0]+"/"+sugar3[1]+"/"+sugar3[2]+"/"+sugar3[3]+"/"+sugar3[4];
			}
		}
		//大小字串宣告
		String sizeString3 = null;
		if(size3 !=null){
			if(size3.length==1){
				sizeString3 = size3[0];
			}else if(size3.length==2){
				sizeString3 = size3[0]+"/"+size3[1];
			}else if(size3.length==3){
				sizeString3 = size3[0]+"/"+size3[1]+"/"+size3[2];
			}
		}
//		out.println("冰塊"+iceString3);
//		out.println("甜度"+sugarString3);
//		out.println("大小"+sizeString3);	
		
		
		
		
		
		
		//新增飲品4
		String itemName4 = request.getParameter("drink_itemName_name4");
		String bigMoney4 = request.getParameter("drink_bigMoney_name4");
		String midMoney4 = request.getParameter("drink_midMoney_name4");
		String smallMoney4 = request.getParameter("drink_smallMoney_name4");
		String counter4 = request.getParameter("drink_counter_name4");
		String[] ice4 =request.getParameterValues("ice3");
		String[] sugar4 =request.getParameterValues("sugar3");
		String[] size4 =request.getParameterValues("size3");
		//冰塊字串宣告
		String iceString4 = null;
		if(ice4 !=null){
			if(ice4.length==1){
				 iceString4 = ice4[0];	
			}else if(ice4.length==2){
				 iceString4 = ice4[0]+"/"+ice4[1];
			}else if(ice4.length==3){
				 iceString4 = ice4[0]+"/"+ice4[1]+"/"+ice4[2];
			}else if(ice4.length==4){
				 iceString4 = ice4[0]+"/"+ice4[1]+"/"+ice4[2]+"/"+ice4[3];
			}else if(ice4.length==5){
				 iceString4 = ice4[0]+"/"+ice4[1]+"/"+ice4[2]+"/"+ice4[3]+"/"+ice4[4];
			}
		}
		//甜度字串宣告
		String sugarString4 = null;
		if(sugar4 != null){
			if(sugar4.length==1){
				sugarString4 = sugar4[0];	
			}else if(sugar4.length==2){
				sugarString4 = sugar4[0]+"/"+sugar4[1];
			}else if(sugar4.length==3){
				sugarString4 = sugar4[0]+"/"+sugar4[1]+"/"+sugar4[2];
			}else if(sugar4.length==4){
				sugarString4 = sugar4[0]+"/"+sugar4[1]+"/"+sugar4[2]+"/"+sugar4[3];
			}else if(sugar4.length==5){
				sugarString4 = sugar4[0]+"/"+sugar4[1]+"/"+sugar4[2]+"/"+sugar4[3]+"/"+sugar4[4];
			}
		}
		//大小字串宣告
		String sizeString4 = null;
		if(size4 != null){
			if(size4.length==1){
				sizeString4 = size4[0];
			}else if(size4.length==2){
				sizeString4 = size4[0]+"/"+size4[1];
			}else if(size4.length==3){
				sizeString4 = size4[0]+"/"+size4[1]+"/"+size4[2];
			}
		}
		
//		out.println("冰塊"+iceString4);
//		out.println("甜度"+sugarString4);
//		out.println("大小"+sizeString4);	
		
		
		int n=0;
		BuildItemDAO buildItemDAO = null;
		//一樣商品
		if(itemName2 ==null && itemName3 == null && itemName4 ==null){
			try{   
			n = new BuildItemDAO().BuildOrderDrink(itemInformation,title,deadline,userID,itemName,itemNeedCounter,bigMoney,midMoney,smallMoney,counter,iceString,sugarString,sizeString,picture);
				request.getRequestDispatcher("./buildPass.jsp").forward(request,response);
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
		//兩樣商品
		}else if(itemName3 == null && itemName4 ==null){
			try{       
			n = new BuildItemDAO().BuildOrderDrink2(itemInformation,title,deadline,userID,itemName,itemNeedCounter,bigMoney,midMoney,smallMoney,counter,iceString,sugarString,sizeString,itemName2,bigMoney2,midMoney2,smallMoney2,iceString2,sugarString2,sizeString2,picture);
				request.getRequestDispatcher("./buildPass.jsp").forward(request,response);
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//三樣商品
		}else if(itemName4 == null){
			try{            
			n = new BuildItemDAO().BuildOrderDrink3(itemInformation,title,deadline,userID,itemName,itemNeedCounter,bigMoney,midMoney,smallMoney,counter,iceString,sugarString,sizeString,itemName2,bigMoney2,midMoney2,smallMoney2,iceString2,sugarString2,sizeString2,itemName3,bigMoney3,midMoney3,smallMoney3,iceString3,sugarString3,sizeString3,picture);
				request.getRequestDispatcher("./buildPass.jsp").forward(request,response);
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//四樣商品
		}else{
			try{              
			n = new BuildItemDAO().BuildOrderDrink4(itemInformation,title,deadline,userID,itemName,itemNeedCounter,bigMoney,midMoney,smallMoney,counter,iceString,sugarString,sizeString,itemName2,bigMoney2,midMoney2,smallMoney2,iceString2,sugarString2,sizeString2,itemName3,bigMoney3,midMoney3,smallMoney3,iceString3,sugarString3,sizeString3,itemName4,bigMoney4,midMoney4,smallMoney4,iceString4,sugarString4,sizeString4,picture);
				request.getRequestDispatcher("./buildPass.jsp").forward(request,response);
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
}

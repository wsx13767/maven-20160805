

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import model.UserData;

import dao.CashDAO;
import dao.JsonDAO;
import dao.UserDataDAO;
import dao.WriteCashLogDAO;

/**
 * Servlet implementation class FindUser
 */
@WebServlet("/Cash")
public class Cash extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Cash() {
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
		
		request.setCharacterEncoding("utf-8");
		String[] user =String.valueOf(request.getParameter("usr")).split(":");
		int money =Integer.valueOf(request.getParameter("money"));
		
		
		try {
			UserDataDAO userinfo =new UserDataDAO();
     		long userid= userinfo.findByAccount(user[1]).getUserId();
     		CashDAO cash =new CashDAO();
     		int nowmoney =cash.getnowmoney(user[1]);
     		int newmoney = nowmoney + money;
     		
			if(newmoney<10000){
			//寫入LOG
     		     		 
     		WriteCashLogDAO writeCashLog = new WriteCashLogDAO();
     		int cashlogresult = writeCashLog.cashlog(userid, money) ;
     		
     		//儲值
     		
     		     		
     		boolean cashresult = cash.cash(user[1],newmoney);
     		
     		PrintWriter out = response.getWriter();
		    out.print(cashresult);
			out.flush();
			out.close();
			}
     		else {
     			
     		PrintWriter out = response.getWriter();
		    out.print(false);
			out.flush();
			out.close();}
     	
     					
		} catch (Exception e) {
			// TODO Auto-generated catch block 
			e.printStackTrace();
			
		}
	}

}

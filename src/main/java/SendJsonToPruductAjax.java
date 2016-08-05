import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.JoinOrder;
import dao.JsonDAO;
import dao.ProductDAO;

/**
 * Servlet implementation class SendJsonToJoinOrderAjax
 */
@WebServlet("/SendJsonToPruductAjax")
public class SendJsonToPruductAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendJsonToPruductAjax() {
        super();
        // TODO Auto-generated constructor stub
    }

    
    public String getSqlA(String sort,String sqlOrderBy,String category,int start,int limit){
    	String sqlA="SELECT BUILD_ORDER.ORDER_NUMBER,BUILD_ORDER.DEADLINE,BUILD_ORDER.PICTURE,ITEM_INFORMATION.MONEY,BUILD_ORDER.ITEM_INFORMATION,ITEM_INFORMATION.NAME,ITEM_INFORMATION.MAX,USER_DATA.ACCOUNT,JOIN_ORDER.COUNT,BUILD_ORDER.BUILD_TIME,ITEM_INFORMATION.BIG_PRICE,ITEM_INFORMATION.MID_PRICE,ITEM_INFORMATION.SMALL_PRICE "+
							   "FROM ITEM_INFORMATION,BUILD_ORDER,USER_DATA,JOIN_ORDER,(SELECT ORDER_NUMBER "+
																																			                        "FROM (SELECT DISTINCT BUILD_ORDER.ORDER_NUMBER "+
																																			                            "FROM ITEM_INFORMATION,BUILD_ORDER,USER_DATA,JOIN_ORDER "+
																																			                            "WHERE ITEM_INFORMATION.ORDER_NUMBER=BUILD_ORDER.ORDER_NUMBER "+
																																			                            "AND ITEM_INFORMATION.USER_ID=BUILD_ORDER.USER_ID "+
																																			                            "AND BUILD_ORDER.USER_ID=USER_DATA.USER_ID "+
																																			                            "AND ITEM_INFORMATION.ITEM_NUMBER=JOIN_ORDER.ITEM_NUMBER(+) "+
																																			                            "AND DEADLINE-SYSDATE>0 "+
																																			                            "AND BUILD_ORDER.BUILD_STATE='live' "+
																																			                            category+
																																						    			sqlOrderBy +")"+
																																			                        "WHERE ROWNUM<="+(start+limit)+" "+
																																			                        "MINUS "+
																																			                        "SELECT ORDER_NUMBER "+
																																			                        "FROM (SELECT DISTINCT BUILD_ORDER.ORDER_NUMBER "+
																																					                               "FROM ITEM_INFORMATION,BUILD_ORDER,USER_DATA,JOIN_ORDER "+
																																					                               "WHERE ITEM_INFORMATION.ORDER_NUMBER=BUILD_ORDER.ORDER_NUMBER "+
																																					                               "AND ITEM_INFORMATION.USER_ID=BUILD_ORDER.USER_ID "+
																																					                               "AND BUILD_ORDER.USER_ID=USER_DATA.USER_ID "+
																																					                               "AND ITEM_INFORMATION.ITEM_NUMBER=JOIN_ORDER.ITEM_NUMBER(+) "+
																																					                               "AND DEADLINE-SYSDATE>0 "+
																																					                               "AND BUILD_ORDER.BUILD_STATE='live' "+
																																					                               category+
																																									    		   sqlOrderBy +")"+
																																			                        "WHERE ROWNUM<="+start+") A "+
							"WHERE ITEM_INFORMATION.ORDER_NUMBER=BUILD_ORDER.ORDER_NUMBER "+
							"AND ITEM_INFORMATION.USER_ID=BUILD_ORDER.USER_ID "+
							"AND BUILD_ORDER.USER_ID=USER_DATA.USER_ID "+
							"AND DEADLINE-SYSDATE>0 "+
							"AND BUILD_ORDER.BUILD_STATE='live' "+
							"AND ITEM_INFORMATION.ITEM_NUMBER=JOIN_ORDER.ITEM_NUMBER(+)"+
							"AND BUILD_ORDER.ORDER_NUMBER=A.ORDER_NUMBER "+
							category+
				    		sqlOrderBy;
				return sqlA;
			}
    
    public String getSqlC(String sort,String sqlOrderBy,String category,String sqlView,int start,int limit){
		String sqlC="SELECT BUILD_ORDER.ORDER_NUMBER,BUILD_ORDER.DEADLINE,BUILD_ORDER.PICTURE,ITEM_INFORMATION.MONEY,BUILD_ORDER.ITEM_INFORMATION,ITEM_INFORMATION.NAME,ITEM_INFORMATION.MAX,USER_DATA.ACCOUNT,JOIN_ORDER.COUNT,BUILD_ORDER.BUILD_TIME,ITEM_INFORMATION.BIG_PRICE,ITEM_INFORMATION.MID_PRICE,ITEM_INFORMATION.SMALL_PRICE "+
								"FROM ITEM_INFORMATION,BUILD_ORDER,USER_DATA,JOIN_ORDER,(SELECT ORDER_NUMBER  "+
																																				                    "FROM "+sqlView+" "+
																																				                    "WHERE ROWNUM<"+(start+limit)+" "+
																																				                    "MINUS "+
																																		                            "SELECT ORDER_NUMBER  "+
																																		                            "FROM "+sqlView+" "+
																																				                    "WHERE ROWNUM<"+start+") A "+
								"WHERE ITEM_INFORMATION.ORDER_NUMBER=BUILD_ORDER.ORDER_NUMBER "+
								"AND ITEM_INFORMATION.USER_ID=BUILD_ORDER.USER_ID "+
								"AND BUILD_ORDER.USER_ID=USER_DATA.USER_ID "+
								"AND DEADLINE-SYSDATE>0 "+
								"AND BUILD_ORDER.BUILD_STATE='live' "+
								"AND ITEM_INFORMATION.ITEM_NUMBER=JOIN_ORDER.ITEM_NUMBER(+) "+
								"AND BUILD_ORDER.ORDER_NUMBER=A.ORDER_NUMBER "+
								 category+
								sqlOrderBy;
								return sqlC;
    }
    
    public String getSqlB(String sort,String sqlOrderBy,String category,int start,int limit){
    	String sqlB="SELECT BUILD_ORDER.ORDER_NUMBER,BUILD_ORDER.DEADLINE,BUILD_ORDER.PICTURE,ITEM_INFORMATION.MONEY,BUILD_ORDER.ITEM_INFORMATION,ITEM_INFORMATION.NAME,ITEM_INFORMATION.MAX,USER_DATA.ACCOUNT,JOIN_ORDER.COUNT,BUILD_ORDER.BUILD_TIME,ITEM_INFORMATION.BIG_PRICE,ITEM_INFORMATION.MID_PRICE,ITEM_INFORMATION.SMALL_PRICE  "+
    			"FROM ITEM_INFORMATION,BUILD_ORDER,USER_DATA,JOIN_ORDER,(SELECT ORDER_NUMBER "+
																															                        "FROM (SELECT BUILD_ORDER.ORDER_NUMBER "+
																																	                              "FROM JOIN_ORDER,BUILD_ORDER,ITEM_INFORMATION "+
																																	                              "WHERE JOIN_ORDER.ORDER_NUMBER(+)=BUILD_ORDER.ORDER_NUMBER "+
																																	                              "AND ITEM_INFORMATION.USER_ID=BUILD_ORDER.USER_ID "+
																																	                              "AND ITEM_INFORMATION.ORDER_NUMBER=BUILD_ORDER.ORDER_NUMBER "+
																																	                              "AND DEADLINE-SYSDATE>0 "+
																																	                              "AND BUILD_ORDER.BUILD_STATE='live' "+
																																	                              category+
																																	                              "GROUP BY BUILD_ORDER.ORDER_NUMBER "+
																																	                              "ORDER BY SUM(NVL(JOIN_ORDER.COUNT,0)) "+sort+") "+
																															                        "WHERE ROWNUM<="+(start+limit)+" "+
																															                        "MINUS "+
																																			                        "SELECT ORDER_NUMBER "+
																																			                        "FROM (SELECT BUILD_ORDER.ORDER_NUMBER "+
																																					                              "FROM JOIN_ORDER,BUILD_ORDER,ITEM_INFORMATION "+
																																					                              "WHERE JOIN_ORDER.ORDER_NUMBER(+)=BUILD_ORDER.ORDER_NUMBER "+
																																					                              "AND ITEM_INFORMATION.USER_ID=BUILD_ORDER.USER_ID "+
																																					                              "AND ITEM_INFORMATION.ORDER_NUMBER=BUILD_ORDER.ORDER_NUMBER "+
																																					                              "AND DEADLINE-SYSDATE>0 "+
																																					                              "AND BUILD_ORDER.BUILD_STATE='live' "+
																																					                              category+
																																					                              "GROUP BY BUILD_ORDER.ORDER_NUMBER "+
																																					                              "ORDER BY SUM(NVL(JOIN_ORDER.COUNT,0)) "+sort+") "+
																															                        "WHERE ROWNUM<="+start+") A  "+
				"WHERE ITEM_INFORMATION.ORDER_NUMBER=BUILD_ORDER.ORDER_NUMBER  "+
				"AND ITEM_INFORMATION.USER_ID=BUILD_ORDER.USER_ID  "+
				"AND BUILD_ORDER.USER_ID=USER_DATA.USER_ID  "+
				"AND ITEM_INFORMATION.ITEM_NUMBER=JOIN_ORDER.ITEM_NUMBER(+)  "+
				"AND BUILD_ORDER.ORDER_NUMBER = A.ORDER_NUMBER  "+
				"AND DEADLINE-SYSDATE>0 "+
				"AND BUILD_ORDER.BUILD_STATE='live' "+
				category;
				return sqlB;
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
		
		String sortBy=request.getParameter("sortBy");
		String sort=request.getParameter("sort");
		String category=request.getParameter("category");
		int limit=Integer.parseInt(request.getParameter("limit"));
		int start=Integer.parseInt(request.getParameter("start"));
		
		String sqlOrderBy="";
		String sql="";
		String sqlView="";
		String orderM="";
		
		if(sort.equals("ASC")){
			orderM="ORDER BY GREATEST(NVL(ITEM_INFORMATION.MONEY, 0),NVL(ITEM_INFORMATION.BIG_PRICE, 0),NVL(ITEM_INFORMATION.MID_PRICE, 0),NVL(ITEM_INFORMATION.SMALL_PRICE, 0)) DESC,TO_NUMBER(BUILD_ORDER.ORDER_NUMBER) ";
			if(category.equals("DRINK")){
				category="AND ITEM_INFORMATION.MONEY IS NULL ";
				sqlView="GREATEST_DRINK_MONEY_VIEW";
			}else if(category.equals("FOOD")){
				category="AND ITEM_INFORMATION.MONEY IS NOT NULL ";
				sqlView="GREATEST_FOOD_MONEY_VIEW";
			}else{
				category="";
				sqlView="GREATEST_MONEY_VIEW";
			}	
		}else if(sort.equals("DESC")){
			orderM="ORDER BY LEAST(NVL(ITEM_INFORMATION.MONEY, 9999),NVL(ITEM_INFORMATION.BIG_PRICE, 9999),NVL(ITEM_INFORMATION.MID_PRICE, 9999),NVL(ITEM_INFORMATION.SMALL_PRICE, 9999)),TO_NUMBER(BUILD_ORDER.ORDER_NUMBER) ";
			if(category.equals("DRINK")){
				category="AND ITEM_INFORMATION.MONEY IS NULL ";
				sqlView="LEAST_DRINK_MONEY_VIEW";
			}else if(category.equals("FOOD")){
				category="AND ITEM_INFORMATION.MONEY IS NOT NULL ";
				sqlView="LEAST_FOOD_MONEY_VIEW";
			}else{
				category="";
				sqlView="LEAST_MONEY_VIEW";
			}	
		}else{
			orderM="ORDER BY GREATEST(NVL(ITEM_INFORMATION.MONEY, 0),NVL(ITEM_INFORMATION.BIG_PRICE, 0),NVL(ITEM_INFORMATION.MID_PRICE, 0),NVL(ITEM_INFORMATION.SMALL_PRICE, 0)) DESC,TO_NUMBER(BUILD_ORDER.ORDER_NUMBER) ";
			if(category.equals("DRINK")){
				category="AND ITEM_INFORMATION.MONEY IS NULL ";
				sqlView="GREATEST_DRINK_MONEY_VIEW";
			}else if(category.equals("FOOD")){
				category="AND ITEM_INFORMATION.MONEY IS NOT NULL ";
				sqlView="GREATEST_FOOD_MONEY_VIEW";
			}else{
				category="";
				sqlView="GREATEST_MONEY_VIEW";
			}	
		}
		
		if(sortBy.equals("COUNT")){
			sqlOrderBy="ORDER BY  TO_NUMBER(BUILD_ORDER.ORDER_NUMBER) "+sort+",ITEM_INFORMATION.MONEY";
			sql=getSqlB(sort,sqlOrderBy,category,start,limit);
		}else if(sortBy.equals("MONEY")){	
			sqlOrderBy="ORDER BY GREATEST(NVL(ITEM_INFORMATION.MONEY, 0),NVL(ITEM_INFORMATION.BIG_PRICE, 0),NVL(ITEM_INFORMATION.MID_PRICE, 0),NVL(ITEM_INFORMATION.SMALL_PRICE, 0)) "+sort+",TO_NUMBER(BUILD_ORDER.ORDER_NUMBER)";
			sql=getSqlC(sort,orderM,category,sqlView,start,limit);
		}else if(sortBy.equals("DATE")){
			sqlOrderBy="ORDER BY TO_NUMBER(BUILD_ORDER.ORDER_NUMBER) "+sort;
			sql=getSqlA(sort,sqlOrderBy,category,start,limit);
		}else{
			sqlOrderBy="ORDER BY TO_NUMBER(BUILD_ORDER.ORDER_NUMBER) "+sort;
			sql=getSqlA(sortBy,sqlOrderBy,category,start,limit);
		}

		try {
			//JoinOrderDAO jod = new JoinOrderDAO();
			ProductDAO jod = new ProductDAO();
			JsonDAO jdao=new JsonDAO();
			List<JoinOrder> list = jod.findAll(sql);
			response.getWriter().write(jdao.getJoinOrderJSON(list)); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

}

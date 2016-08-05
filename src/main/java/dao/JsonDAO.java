package dao;

import java.util.List;

import model.*;

import com.google.gson.Gson;

public class JsonDAO {
	public String getJoinOrderJSON ( List< JoinOrder > list )  { 
	    Gson gson =  new  Gson (); 
	    String json=gson.toJson(list);
	    return json;
	}
	public String getItemInformationJSON ( List< ItemInformation > list )  { 
	    Gson gson =  new  Gson (); 
	    String json=gson.toJson(list);
	    return json;
	}
	public String getBUILDORDERJSON ( List< BuildOrder > list )  { 
	    Gson gson =  new  Gson (); 
	    String json=gson.toJson(list);
	    return json;
	}
	public String getCASHLOGJSON ( List< CashLog > list )  { 
	    Gson gson =  new  Gson (); 
	    String json=gson.toJson(list);
	    return json;
	}
	public String getUSERDATAJSON ( List< UserData > list )  { 
	    Gson gson =  new  Gson (); 
	    String json=gson.toJson(list);
	    return json;
	}
	public String getJOrderCookies ( List< JOrderCookies > list )  { 
	    Gson gson =  new  Gson (); 
	    String json=gson.toJson(list);
	    return json;
	}
	public String getCountJoinItem ( List< CountJoinItem > list )  { 
	    Gson gson =  new  Gson (); 
	    String json=gson.toJson(list);
	    return json;
	}
	public String getPptItem ( List< PptItem > list )  { 
	    Gson gson =  new  Gson (); 
	    String json=gson.toJson(list);
	    return json;
	}
	public String getIndexItem ( List< IndexItem > list )  { 
	    Gson gson =  new  Gson (); 
	    String json=gson.toJson(list);
	    return json;
	}
	public String getTotalOrderCount ( List< TotalOrderCount > list )  { 
	    Gson gson =  new  Gson (); 
	    String json=gson.toJson(list);
	    return json;
	}
}

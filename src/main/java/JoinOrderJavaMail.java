import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import model.InsertJoinOrder;

public class JoinOrderJavaMail {

	public int setMessage(List <InsertJoinOrder> list,int totalMoney,String orderNumber,String userName){
		String title="<div style='font-size: 18px;margin-bottom: 50px;'><p>尊貴的"+userName+"先生/小姐,感謝您的訂購,底下是您此次訂單的交易明細!</p></div>";
		String table="<div><h1 style=\"color:red;text-align:center;\">交易明細</h1><hr style='width:40%;border:1px solid#CCC;'>"+
								"<table style='margin: auto;margin-top: 20px;margin-bottom: 35px;border-collapse: separate;border-spacing: 40px 5px;font-size: 20px;text-align:center'>";
		String tableEnd="</div></table>";
		String totalMoneyString="<div style=\"text-align:center;font-size:20px;margin-bottom: 20px;\"><span>總金額:"+totalMoney+"元<span></div>";
		String footer="<div><p>爽爽訂團購網再次感謝您的購買,若有任何疑問可以至底下網址(連絡我們),客服人員將立即為您服務。<p><div>"+
									"<br style='height:0px'><p style='margin-top:-20px;'>爽爽訂首頁:<a href='http://localhost:8088/G/index.jsp'>http://localhost:8088/G/index.jsp</a><p>"+
									"<br style='height:0px'><p style='margin-top:-20px;'>連絡我們:<a href='http://localhost:8088/G/contact.jsp'>http://localhost:8088/G/contact.jsp</a><p>"+
									"<div><p style='margin-top: 30px;margin-bottom: -30px;'>爽爽訂團購網 敬上</p><br><p style='margin-top:-30px;'>注意︰本郵件是由爽爽訂系統自動產生與發送，請勿直接回覆。</p></div>";
		if(list.get(0).getJIce().trim().equals("null")){
			String food="<tr><td></td><td style='width: 20%;'></td><td>單價</td><td>數量</td><td>價格</td></tr>";
			String tbody="";
				for(int i=0;i<list.size();i++){
					tbody=tbody+"<tr><td>"+(i+1)+".</td><td>"+list.get(i).getJItem()+"</td><td>"+list.get(i).getJPrice()+"</td><td>"+list.get(i).getJItemQuan()+"份</td><td>"+list.get(i).getJTotalPrice()+"元</td></tr>";
				}
			sendEmail(title+table+food+tbody+tableEnd+totalMoneyString+footer,orderNumber);
			}
		else{
			String drink="<tr><td></td><td style='width: 20%;'></td><td>L</td><td>M</td><td>S</td><td>冰塊</td><td>甜度</td><td>數量</td><td>價格</td></tr>";
			String tbody="";
			String ice="";
			String suger="";
			for(int i=0;i<list.size();i++){
				if(list.get(i).getJIce().equals("10")){suger="全糖";}else if(list.get(i).getJIce().equals("7")){suger="7分";}else if(list.get(i).getJIce().equals("5")){suger="半糖";}else if(list.get(i).getJIce().equals("3")){suger="微糖";}else{suger="無糖";}
				if(list.get(i).getJSuger().equals("10")){ice="正常";}else if(list.get(i).getJSuger().equals("5")){ice="少冰";}else{ice="去冰";}
				if(list.get(i).getJSize().trim().equals("b")){
					tbody=tbody+"<tr><td>"+(i+1)+".</td><td>"+list.get(i).getJItem()+"</td><td>"+list.get(i).getJPrice()+"</td><td></td><td></td><td>"+ice+"</td><td>"+suger+"</td><td>"+list.get(i).getJItemQuan()+"杯</td><td>"+list.get(i).getJTotalPrice()+"元</td></tr>";
				}
				if(list.get(i).getJSize().trim().equals("m")){
					tbody=tbody+"<tr><td>"+(i+1)+".</td><td>"+list.get(i).getJItem()+"</td><td></td><td>"+list.get(i).getJPrice()+"</td><td></td><td>"+ice+"</td><td>"+suger+"</td><td>"+list.get(i).getJItemQuan()+"杯</td><td>"+list.get(i).getJTotalPrice()+"元</td></tr>";
				}
				if(list.get(i).getJSize().trim().equals("s")){
					tbody=tbody+"<tr><td>"+(i+1)+".</td><td>"+list.get(i).getJItem()+"</td><td></td><td></td><td>"+list.get(i).getJPrice()+"</td><td>"+ice+"</td><td>"+suger+"</td><td>"+list.get(i).getJItemQuan()+"杯</td><td>"+list.get(i).getJTotalPrice()+"元</td></tr>";
				}
			}
			sendEmail(title+table+drink+tbody+tableEnd+totalMoneyString+footer,orderNumber);
		}
		return 6;	
	}
	
 public void sendEmail(String messageHtml,String orderNumber) {
  String host = "smtp.gmail.com";
  int port = 587;
  final String username = "025yensid@gmail.com";
  final String password = "disney520";//your password

  Properties props = new Properties();
  props.put("mail.smtp.host", host);
  props.put("mail.smtp.auth", "true");
  props.put("mail.smtp.starttls.enable", "true");
  props.put("mail.smtp.port", port);
  
  Session session = Session.getInstance(props, new Authenticator() {
	   protected PasswordAuthentication getPasswordAuthentication() {
		   return new PasswordAuthentication(username, password);
	   }
  });

  try {

   Message message = new MimeMessage(session);
   message.setFrom(new InternetAddress("SSD@gmail.com"));
   message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("markhuang1993@gmail.com"));
   message.setSubject("交易明細");
   /*message.setContent("<h1 style=\"color:red;text-align:center;\">This is a test</h1><hr style='width:40%;border:1px solid#CCC;'>"+
		   									"<table style='margin: auto;margin-top: 20px;margin-bottom: 150px;border-collapse: separate;border-spacing: 40px 5px;font-size: 28px;text-align:center'>"+
		   								   "<tr><td></td><td></td><td>L</td><td>M</td><td>S</td></tr>"+
		   								   "<tr><td>1.</td><td>珍珠奶茶</td><td>75元</td><td></td><td></td></tr>"+
		   								   "<tr><td>2.</td><td>綠茶</td><td></td><td></td><td>27元</td></tr></table>" ,"text/html;charset=utf-8");*/
   message.setContent(messageHtml ,"text/html;charset=utf-8");
   
   Transport transport = session.getTransport("smtp");
   transport.connect(host, port, username, password);

   Transport.send(message);

   System.out.println("寄送email結束.");
  } catch (MessagingException e) {
   throw new RuntimeException(e);
  }
 }
}
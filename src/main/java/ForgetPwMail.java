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

public class ForgetPwMail {

	public int setMessage(String userName,String resetlink){
		String title="<div style='font-size: 18px;margin-bottom: 50px;'><p>尊貴的"+userName+"先生/小姐,底下是您密碼重設連結!</p></div>";
		String link = "密碼重設連結"+resetlink;
		String footer="<div><p>若有任何疑問可以至底下網址(連絡我們),客服人員將立即為您服務。<p><div>"+
									"<br style='height:0px'><p style='margin-top:-20px;'>爽爽訂首頁:<a href='http://localhost:8088/G/index.jsp'>http://localhost:8088/G/index.jsp</a><p>"+
									"<br style='height:0px'><p style='margin-top:-20px;'>連絡我們:<a href='http://localhost:8088/G/contact.jsp'>http://localhost:8088/G/contact.jsp</a><p>"+
									"<div><p style='margin-top: 30px;margin-bottom: -30px;'>爽爽訂團購網 敬上</p><br><p style='margin-top:-30px;'>注意︰本郵件是由爽爽訂系統自動產生與發送，請勿直接回覆。</p></div>";
		sendEmail(title+link+footer);
		return 6;	
	}
	
 public void sendEmail(String messageHtml) {
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
   message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("025yensid@gmail.com"));
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
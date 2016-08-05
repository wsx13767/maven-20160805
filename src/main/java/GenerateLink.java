import java.security.MessageDigest;  
import java.security.NoSuchAlgorithmException;  
  
import javax.servlet.ServletRequest;  

import dao.UserDataDAO;

import model.UserData;
  

public class GenerateLink {
	private static final String CHECK_CODE = "checkCode";  
    
    /** 
     * 生成重設密碼的鏈接 
     */  
    public static String generateResetPwdLink(long userid ,String account) {  
        return "http://localhost/G/resetPassword.jsp?userid="  + 
                userid + "&" + CHECK_CODE + "=" + generateCheckcode(account);  
    }  
      
    /** 
     * 生成驗證帳戶的MD5校驗碼 
     * @param user  要激活的帳戶 
     * @return 將用戶名和密碼組合後，通過md5加密後的16進制格式的字符串 
     */  
    public static String generateCheckcode(String account) {  
         return md5(account);  
    }  
      
    /** 
     * 驗證校驗碼是否和注冊時發送的驗證碼一致 
     * @param user 要激活的帳戶 
     * @param checkcode 注冊時發送的校驗碼 
     * @return 如果一致返回true，否則返回false 
     */  
   
    
    //MD5
  
    private static String md5(String string) {  
        MessageDigest md = null;  
        try {  
            md = MessageDigest.getInstance("md5");  
            md.update(string.getBytes());  
            byte[] md5Bytes = md.digest();  
            return bytes2Hex(md5Bytes);  
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
        }  
          
        return null;  
    }  
      
    private static String bytes2Hex(byte[] byteArray)  
    {  
        StringBuffer strBuf = new StringBuffer();  
        for (int i = 0; i < byteArray.length; i++)  
        {  
            if(byteArray[i] >= 0 && byteArray[i] < 16)  
            {  
                strBuf.append("0");  
            }  
            strBuf.append(Integer.toHexString(byteArray[i] & 0xFF));  
        }  
        return strBuf.toString();  
    }  
}

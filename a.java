import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.lang.Byte;

import javax.crypto.Cipher;

public class a {
	
	
	 public static String getPubKeyContentString(String cerPath) {

		    // 读取本机存放的PKCS8证书文件
	        String currentPath = cerPath;
	        try {
	        	//读取pem证书
	            BufferedReader br = new BufferedReader(new FileReader(currentPath));

	            StringBuffer publickey = new StringBuffer();
	            String line;

	            while (null != (line = br.readLine())) {   //读取文件的一行数据
	                if ((line.contains("BEGIN PUBLIC KEY") || line.contains("END PUBLIC KEY"))) //一行的数据如果包含BEGIN PUBLIC KEY就(continue;)不append
	                    continue;
	                publickey.append(line);
	            }
	            return publickey.toString();
	        }catch (Exception e){
	            e.printStackTrace();
	        }
	        return null;
	    }
	 
	 public static String PubKeyEncry() {   //使用公钥加密
		 try {
			 
		        String brs = getPubKeyContentString("C:\\Users\\Administrator\\Desktop\\rsa_public_key.pem");
		        
		        byte[] keyBytes;
			    keyBytes = Base64.getDecoder().decode(brs);
             
				//System.out.println(keyBytes.toString());

			    
		        KeyFactory kf = KeyFactory.getInstance("RSA");
		        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		        PublicKey publicKey = kf.generatePublic(keySpec);


		        
		        String toSign="xiaobenTwo吴章本";
		       

		        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWITHSHA-1ANDMGF1PADDING");
		        
		        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
	            byte[] test = cipher.doFinal(toSign.getBytes("utf8"));    //gbk就可以在控制台显示中文
	            String base64encodedString =   Base64.getEncoder().encodeToString(test);
				
	            System.out.println("加密数据长度为：" + base64encodedString.length());
	            
             
	           /* File file = new File("E:\\web\\php\\public\\ceshi6.txt");
	            FileOutputStream fileOutputStreamnew = new FileOutputStream(file); 
	            fileOutputStreamnew.write(base64encodedString.getBytes());
	            fileOutputStreamnew.close();*/
	            
	            return base64encodedString;
		        
		   }catch (Exception e) {
		            return e.getMessage();
		        }
		 
	 }
	 
	 public static String getPrivateKeyContentString(String cerPath) {

		   // 读取本机存放的PKCS8证书文件
	        String currentPath = cerPath;
	        try {
	        	//读取pem证书
	            BufferedReader br = new BufferedReader(new FileReader(currentPath));

	            StringBuffer publickey = new StringBuffer();
	            String line;

	            while (null != (line = br.readLine())) {
	                if ((line.contains("BEGIN PRIVATE KEY") || line.contains("END PRIVATE KEY")))
	                    continue;
	                publickey.append(line);
	            }
	            return publickey.toString();
	        }catch (Exception e){
	            e.printStackTrace();
	        }
	        return null;
	    }
	 
	 public static String PrivateKeyDecrypt(String toSign) {    //使用私钥解密
		 try {
			 
			 
		        String brs = getPrivateKeyContentString("C:\\Users\\Administrator\\Desktop\\pkcs8.pem");
		        //System.out.println(brs);   
		        byte[] keyBytes;
			    keyBytes = Base64.getDecoder().decode(brs);
             
				

				
			    
		        KeyFactory kf = KeyFactory.getInstance("RSA");
		        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
		        PrivateKey publicKey = kf.generatePrivate(keySpec);

             
				//System.out.println(publicKey);
		        
		        byte[] keyBytess;
			    keyBytess = Base64.getDecoder().decode(toSign);

		        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWITHSHA-1ANDMGF1PADDING");
		        
		        cipher.init(Cipher.DECRYPT_MODE, publicKey);
	            byte[] test = cipher.doFinal(keyBytess);
	           // String base64encodedString =   Base64.getEncoder().encodeToString(test);
				
				String str = new String (test);;
				System.out.println("解密数据长度为：" + str.length());
	            
               return str;
	           /* File file = new File("C:\\Users\\Administrator\\Desktop\\ceshi6.txt");
	            FileOutputStream fileOutputStreamnew = new FileOutputStream(file); 
	            fileOutputStreamnew.write(test);
	            fileOutputStreamnew.close();*/
		        
		   }catch (Exception e) {
		           return e.getMessage();
		        }
	 }
    
	public static String exec(){
		try {
		 Runtime.getRuntime().exec("openssl pkcs8 -topk8 -inform PEM -in C:\\Users\\Administrator\\Desktop\\rsa_private_key.pem -outform pem -nocrypt -out C:\\Users\\Administrator\\Desktop\\pkcs8.pem");
		 return "ok";
		}catch(Exception e){
			return e.getMessage();
		}
	}
	
	public static void main(String[] args) {
		//System.out.println(exec());
		String PubKeyEncryData = PubKeyEncry();
		String PrivateKeyDecryptData = PrivateKeyDecrypt(PubKeyEncryData);
	    System.out.println(PrivateKeyDecryptData);
	}
	

}

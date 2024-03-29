package util;


import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.sound.sampled.AudioFormat.Encoding;

import sun.nio.cs.ext.GBK;

public class Decrypt {
   public static final String ALGORITHM_DES = "DES/CBC/PKCS5Padding";
	    public static String decrypt(String key,byte[] data) {
	    	
	        try {
                    DESKeySpec dks = new DESKeySpec(key.getBytes());
                    SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
	            Key secretKey = keyFactory.generateSecret(dks);
	            Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
	            IvParameterSpec iv = new IvParameterSpec("12345678".getBytes());
	            AlgorithmParameterSpec paramSpec = iv;
	            cipher.init(Cipher.DECRYPT_MODE, secretKey, paramSpec);
	            return new String(cipher.doFinal(data));
	        } catch (Exception e){
	    		e.printStackTrace();
	    		return "<?xml version=\"1.0\" encoding=\"GBK\" standalone=\"no\"?><root><respMsg>wrong</respMsg></root>";
	        }
	    }
               /**
	     * DES算法，加密
	     *
	     * @param data 待加密字符串
	     * @param key  加密私钥，长度不能够小于8位
	     * @return 加密后的字节数组，一般结合Base64编码使用
	     * @throws InvalidAlgorithmParameterException 
	     * @throws Exception 
	     */
	    public static byte[] encrypt(String key,String data) {
	    	if(data == null)
	    		return new byte[0];
	    	try{
		    	DESKeySpec dks = new DESKeySpec(key.getBytes());	    	
		    	SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		        //key的长度不能够小于8位字节
		        Key secretKey = keyFactory.generateSecret(dks);
		        Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
		        IvParameterSpec iv = new IvParameterSpec("12345678".getBytes());
		        AlgorithmParameterSpec paramSpec = iv;
		        cipher.init(Cipher.ENCRYPT_MODE, secretKey,paramSpec);           
		        byte[] bytes = cipher.doFinal(data.getBytes());            
		        return bytes;
	    	}catch(Exception e){
	    		e.printStackTrace();
	    		return new byte[0];
	    	}
	    }
}

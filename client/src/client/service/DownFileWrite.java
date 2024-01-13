/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.service;


import client.util.DES;
import client.util.Decrypt;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ytxlo
 */
public class DownFileWrite {
    public void write(String path,String src,String key){
//        String re = new String(Decrypt.encrypt("1413201160", str));
//        String st = Decrypt.decrypt("1413201160", re.getBytes());
//        System.out.println(src);
     
//        System.out.println("¼ÓÃÜ"+key);
        try {
            File f = new File(path);
            
            if (!f.getParentFile().exists()) {  
                    f.getParentFile().mkdirs();  
            }
            if(!f.exists()){
               f.createNewFile();
            }  
            try {
//            DES des = new DES("1413201160");
            
            byte[] message =Decrypt.encrypt(key, src);
            InputStream is = new ByteArrayInputStream(message);
            OutputStream out = new FileOutputStream(new File(path)); 
            byte[] buffer = new byte[1024]; 
            int r; 
            while ((r = is.read(buffer)) > 0) { 
                out.write(buffer, 0, r); 
            } 
            is.close(); 
            out.close();
        } catch (Exception ex) {
            Logger.getLogger(DownFileWrite.class.getName()).log(Level.SEVERE, null, ex);
        }
        } catch (IOException ex) {
            Logger.getLogger(DownFileWrite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

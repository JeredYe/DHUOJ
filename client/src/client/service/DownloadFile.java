/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 *
 * @author ytxlo
 */
public class DownloadFile {
        private List<String> remoteFilePath;
        private List<String> localFilePath;
    public DownloadFile(List<String> remoteFilePath, List<String> localFilePath) throws Exception{
        this.remoteFilePath = remoteFilePath;
        this.localFilePath = localFilePath;
        download();
    }
    private void download() throws Exception{
        for(int i=0;i<remoteFilePath.size();i++){
            URL urlfile = null;
            HttpURLConnection httpUrl = null;
            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;
            //File f = new File(localFilePath.get(i));
            File f = new File(localFilePath.get(i));  

            try {  
                ////System.out.println(f.exists());
                if (f.exists()) {  
                    continue;  
                }  
                if (!f.getParentFile().exists()) {  
                    f.getParentFile().mkdirs();  
                }  
                f.createNewFile();  
            } catch (Exception e) {  
                throw e;  
            }
            try
            {
                urlfile = new URL(remoteFilePath.get(i));
                httpUrl = (HttpURLConnection)urlfile.openConnection();
                httpUrl.connect();       
                bis = new BufferedInputStream(httpUrl.getInputStream());
                bos = new BufferedOutputStream(new FileOutputStream(f));
                int len = 2048;
                byte[] b = new byte[len];
                while ((len = bis.read(b)) != -1)
                {
                    bos.write(b, 0, len);  
                }
                bos.flush();
                bis.close();
                httpUrl.disconnect();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    if (bis!=null) bis.close();
                    if (bos!=null) bos.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}

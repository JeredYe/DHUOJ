/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientupdater;

//import client.view.frame.LoginFrame;
import common.Config;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import org.apache.commons.codec.binary.Base64;
import log.Log;

/**
 *
 * @author HASEE
 */
public class ClientUpdater {

    private Map<String, String> localmap = new HashMap<>();
    private Map<String, String> servermap = new HashMap<>();
    private Map<String, String> maptodownload = new HashMap<>();
    private String server_libpoint = "";
    private String local_libpoint = "";
    private int net_status_SocketError = 0;//连接错误计数
    private int net_status_ConnectTimeOut = 0;//连接超时计数
    private boolean slp = false;
    private boolean llp = false;

    /**
     * 开始更新，使用前请先调用checkupdate
     *
     * @param ipandport
     * @return
     */
    public boolean startUpdate(String ipandport) {
        try {
            boolean flag = false;//返回标记
            //如果config中顺利读取URLlip和URLport，开始检查更新           
            boolean continueFlag = true;
            UpdaterFrame uf = new UpdaterFrame(localmap, servermap, maptodownload, ipandport, server_libpoint, local_libpoint);
            uf.setVisible(true);//激活，不知为何，需要此部触发内部工作
            uf.setVisible(false);
            while (continueFlag) {
                System.out.println("updateframe" + continueFlag);
                System.out.println(uf.isworking);
                while (uf.isworking) {
                    System.out.println(uf.isworking);
                    Thread.sleep(500);
                };
                //uf.setVisible(false);

                if (uf.status == 1) {
                    continueFlag = false;
                    uf.setVisible(false);
                    JOptionPane.showMessageDialog(null, "更新成功，请重启客户端！");
                    flag = true;
                    //LoginFrame.main(null);
                } else if (uf.status == -1) {
                    uf.setVisible(true);
                    Object[] options = {"确认继续", "取消", "重试"};
                    int result = JOptionPane.showOptionDialog(null, "更新检测失败，是否继续使用旧版本？", "确认", JOptionPane.YES_NO_CANCEL_OPTION,
                            JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                    if (result == 0) {
                        continueFlag = false;
                        uf.setVisible(false);
                        flag = true;
                        // LoginFrame.main(null);
                    } else if (result == 1) {
                        continueFlag = false;
                        uf.dispose();
                        flag = false;
                    } else if (result == 2) {
                        uf.dispose();

                    }
                }
            }
            return flag;
        } catch (Exception e) {
            Log.writeExceptionLog(e.getClass()+e.getMessage());
             System.err.println(e.getMessage());
            return false;
        }
    }

    /**
     * 该方法用于检查是否更新
     *
     * @param ipandport 服务器ip地址加端口高.exam："47.95.22.126:8080"
     * @return int 1需要更新，0无需更新,-1检查更新中失败
     */
    public int CheckWhetherNeedUpdate(String ipandport) {

        try {
            //0.获取本地自身的所有列表文件
            File dir = new File("./");
            //获取本地md5列表
            File localMD5 = new File("lmd5.nax");
            if (!localMD5.exists())//本地不存在md5文件，则从头开始获取本地所有文件的md5值，保存至localmap
            {
                List<Integer> paras = new LinkedList<Integer>();
                paras.add(0);
                paras.add(0);
                int file_num = GetFileNums(dir);
                GetMD5(dir, localmap, 0, file_num, paras);
                SaveMd5("lmd5.nax", localmap);//初次执行扫描后保存md5文件,避免抛错导致执行动作无效，获取md5还是比较耗时间的；之后会对改写的文件的md5进行文件的覆写。
            } else {
                GetMD5("lmd5.nax", localmap);//本地已有md5文件，则直接获取本地的md5文件
            }            //1.获取服务端的文件列表
//            for(String s:localmap.keySet()){
//                System.out.println("map:    "+s+"----"+localmap.get(s));
//            }
            File locationFile = new File("lmd5.nax");
            FileReader fr = new FileReader(locationFile);
            BufferedReader br = new BufferedReader(fr);
            String location = br.readLine();
            br.close();
            fr.close();
            //计数重试3次抛错，循环体内重试2次，最后一次在体外抛错以便直接进入总catch中进行控制
            boolean got = false;
            while (net_status_SocketError < 2 && net_status_ConnectTimeOut < 2) {
                try {
                    System.out.println("下载文件前"+System.currentTimeMillis());
                    HttpGetFile(ipandport, "smd5.nax", "MD5s\\" + location + ".nax");//约定的固定md5文件列表,
                    got = true;
                    break;//无抛错直接退出循环
                } catch (Exception inner_E1) {
                    System.out.println(inner_E1.getMessage() + ".....InnerHTTPGetFile....md5.nax");
                     Log.writeExceptionLog(inner_E1.getClass()+inner_E1.getMessage());
                    if (inner_E1.getClass() == SocketTimeoutException.class) {
                        net_status_ConnectTimeOut++;
                    } else {
                        net_status_SocketError++;
                    }
                }
            }
            if (!got) {
                HttpGetFile(ipandport, "smd5.nax", "MD5s\\" + location + ".nax");
            }
            System.out.println("下载文件后"+System.currentTimeMillis());
            // SetWorkingProgress(0);
            //2.对比文件列表并决定下载覆盖那些文件
            File md5 = new File("smd5.nax");
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(md5));
            servermap.putAll((HashMap<String, String>) ois.readObject());
//            for(String s:servermap.keySet()){
//                System.out.println("smap:    "+s+"----"+servermap.get(s));
//            }
            ois.close();
            System.out.println("Server map--->" + servermap.size());
            //对比操作，以服务段为基准，允许增量
            GetLibPoint(servermap, localmap);//获取lib文件分割点，因为lib文件夹是必定存在的，所以通过lib区分并获取至最外层的相对目录
            // TextAreaMessage.setText("");
            //选取需要下载的文件列表
            System.out.println("对比前"+System.currentTimeMillis());
            SelectFilesToDownload(maptodownload, servermap, localmap);
            System.out.println("对比后"+System.currentTimeMillis());
            //判断是否需要更新
//            for(String s:maptodownload.keySet()){
//                System.out.println("smap:    "+s+"----"+maptodownload.get(s));
//            }
            if (maptodownload.isEmpty()) {
                 md5.delete();
                return 0;
            } else {
                return 1;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
             System.err.println(ex.getMessage());
             Log.writeExceptionLog(ex.getClass()+ex.getMessage());
            return -1;
        }
    }

    private void GetMD5(String filepathString, Map<String, String> localmap) throws IOException, ClassNotFoundException, Exception {
        File lmd5 = new File(filepathString);
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(lmd5));
        localmap.putAll((HashMap<String, String>) ois.readObject());
        ois.close();
        System.out.println("Local map--->" + localmap.size());
    }

    private void GetMD5(File dir, Map<String, String> localmap, int total_progress, int file_num, List<Integer> paras) throws IOException, NoSuchAlgorithmException, Exception {
        //System.out.println(dir.getAbsolutePath());
        if (dir.isDirectory()) {
            String[] tmps = dir.list();
            for (int i = 0; i < tmps.length; i++) {
                if(tmps[i].equals("MinGW")|| tmps[i].equals("jre")||tmps[i].equals("java")||tmps[i].equals("jdk")||tmps[i].equals("config")) continue;
                GetMD5(new File(dir, tmps[i]), localmap, total_progress, file_num, paras);
            }
        } else {
            MessageDigest md5 = MessageDigest.getInstance("md5");
            java.io.FileInputStream fi = new java.io.FileInputStream(dir);
//		java.nio.MappedByteBuffer byteBuffer = fi.getChannel().map(java.nio.channels.FileChannel.MapMode.READ_ONLY, 0, dir.length());
//		md5.update(byteBuffer);  
//	        java.math.BigInteger bi = new java.math.BigInteger(1, md5.digest());  
//	        String value = bi.toString(16);
//	        localmap.put(dir.getAbsolutePath(), value);
//              byteBuffer.clear();
//              FileInputStream fi = new FileInputStream(file);
            //更改为此方法以使文件资源能够释放，上述方式会使文件始终处在使用状态
            DigestInputStream di = new DigestInputStream(fi, md5);
            byte[] buffer = new byte[1024];
            while (di.read(buffer) > 0);
            md5 = di.getMessageDigest();
            BigInteger bi = new BigInteger(1, md5.digest());
            String value = bi.toString(16);
            localmap.put(dir.getAbsolutePath(), value);
            di.close();
            fi.close();
            int counter = paras.get(0);
            int has_num = paras.get(1);
            counter++;
            int k = (int) (1.0 * total_progress / file_num * counter - has_num);
            if (k >= 1) {
                //SetWorkingProgress(k);
                has_num += k;
            }
            paras.set(0, counter);
            paras.set(1, has_num);
        }
    }

    private void HttpGetFile(String ipandport, String saveFilePath, String requestNameString) throws Exception {
        //http://localhost:8080/oj/UpLoadFileAction!UpLoad.action
        String encfilename = new String(Base64.encodeBase64(requestNameString.getBytes("utf-8")));
        String versionId = Config.getValue("VersionId");
        URL url = new URL("http://" + ipandport + "/oj/SoftwareVersionMAction!DownLoad.action?versionId=" + versionId);
        URLConnection u = url.openConnection();
        HttpURLConnection connection = (HttpURLConnection) u;
        connection.setReadTimeout(5000);
        connection.connect();
        BufferedInputStream bin = new BufferedInputStream(connection.getInputStream());
        byte[] buff = new byte[1024];
        File file = new File(saveFilePath);
        //无目录则创建新文件夹
//        if(saveFilePath.contains("\\.\\")){
//            String[] spts = saveFilePath.split("\\\\");
//            String dirPath = saveFilePath.split("\\\\"+spts[spts.length-1])[0];
//            File dir = new File(dirPath);
//            if(!dir.exists())
//                dir.mkdirs();
//        }
        if (file.exists()) {
            file.delete();
            file.createNewFile();
        } else {
            if (file.getParentFile() != null && !file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            file.createNewFile();
        }
        FileOutputStream fos = new FileOutputStream(file);
        int size;
        while ((size = bin.read(buff)) != -1) {
            fos.write(buff, 0, size);
        }
        fos.flush();
        fos.close();
        bin.close();
        connection.disconnect();
    }

    private void SelectFilesToDownload(Map<String, String> maptodownload, Map<String, String> servermap, Map<String, String> localmap) throws Exception {
        //双循环遍历匹配
        Iterator iter_server = servermap.entrySet().iterator();
        int k = 0;
        while (iter_server.hasNext()) {
            Map.Entry entry_server = (Map.Entry) iter_server.next();
            String key_server = (String) entry_server.getKey();
            String val_server = (String) entry_server.getValue();
            String filename_server = "+";
            try {
                filename_server = key_server.split(server_libpoint.replaceAll("\\\\", "\\\\\\\\"))[1];
            } catch (Exception e) {
                continue;
            }
            Iterator iter_local = localmap.entrySet().iterator();
            boolean has = false;//记录本地是否存在该文件
            while (iter_local.hasNext()) {
                Map.Entry entry_local = (Map.Entry) iter_local.next();
                String key_local = (String) entry_local.getKey();
                String val_local = (String) entry_local.getValue();
                String filename_local = "-";
                try {
                    filename_local = key_local.split(local_libpoint.replaceAll("\\\\", "\\\\\\\\"))[1];
                } catch (Exception e) {
                    continue;
                }
                if (filename_local.equals(filename_server)) {
                    has = true;
                    if (!val_local.equals(val_server)) {
                        //记录同文件非同内容
                        AddToDownload(maptodownload, key_server, key_local);
                        entry_local.setValue(val_server);
                    }
                }
            }
            if (!has) {
                //记录没有的内容
                AddToDownload(maptodownload, key_server, "_");
                String targ = key_server.split(server_libpoint.replaceAll("\\\\", "\\\\\\\\"))[1];
                String saveLocation = local_libpoint + targ;
                localmap.put(saveLocation, val_server);
            }
        }
        System.out.println("对比后1--"+System.currentTimeMillis());
    }

    private void AddToDownload(Map<String, String> maptodownload, String key_server, String key_local) {
        maptodownload.put(key_server, key_local);
    }

    private void GetLibPoint(Map<String, String> servermap, Map<String, String> localmap) {
        Iterator iter = localmap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            String key = (String) entry.getKey();
            String val = (String) entry.getValue();
            //查找local_lib目录根
            if (key.contains("\\config\\Config.properties")) {
                local_libpoint = key.split("\\\\config\\\\Config.properties")[0];
                break;
            }
        }
        iter = servermap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            String key = (String) entry.getKey();
            String val = (String) entry.getValue();
            //查找local_lib目录根
            if (key.contains("\\config\\Config.properties")) {
                server_libpoint = key.split("\\\\config\\\\Config.properties")[0];
                break;
            }
        }
    }

    private void SaveMd5(String lmd5nax, Map<String, String> localmap) throws IOException {
        File lmd5 = new File(lmd5nax);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(lmd5));
        oos.writeObject(localmap);
        oos.close();
    }

    private int GetFileNums(File dir) {
        if (dir.isDirectory()) {
            String[] tmps = dir.list();
            int file_num = 0;
            for (int i = 0; i < tmps.length; i++) {
                file_num += GetFileNums(new File(dir, tmps[i]));
            }
            return file_num;
        } else {
            return 1;
        }
    }

}

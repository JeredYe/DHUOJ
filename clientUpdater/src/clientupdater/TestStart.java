/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientupdater;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 * 此入口为需要单独测试程序时入口
 *
 * @author 10102
 */
public class TestStart {

    public static void main(String[] args) {
        
        String ipandport ="";
        try {
            //读取Properties文件。
            //在此读配置文件是为了单独测试，需要项目引用时请从引用项目中读取
            Properties property = new Properties();
            FileInputStream fis = new FileInputStream("config/Config.properties");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader brt = new BufferedReader(isr);
            property.load(brt);
            brt.close();
            isr.close();
            fis.close();
            if (property.containsKey("URLip") && property.containsKey("URLport")) {
                
                ipandport = property.getProperty("URLip") + ":" + property.getProperty("URLport");
                //check是否需要更新
                ClientUpdater clientUpdater =new ClientUpdater();
                int isneed = clientUpdater.CheckWhetherNeedUpdate(ipandport);
                //需要则更新
                if (isneed==1) {
                   clientUpdater.startUpdate(ipandport);
                } else  {
                    JOptionPane.showMessageDialog(null, "没有需要的更新");
                }
            }else {
                JOptionPane.showMessageDialog(null, "未检测到配置文件，请确保本程序是从官网下载的版本。\n如果是，请联系教师或管理员。");
               return;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.getStackTrace();
        }

    }
}

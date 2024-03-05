/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import util.Decrypt;
import edu.dhu.ws.OJWS;
import edu.dhu.ws.OJWS_Service;
import static gui.Control.getDistributorField;
import static gui.Control.getJudgeInfoEditorPane;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.xml.namespace.QName;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
/**
 *
 * @author ytxlo
 */
@DubboService
public class Webservice implements java.rmi.Remote{
    private OJWS_Service webs;  
    private OJWS servicePort;
    public static boolean ENABLE_DUBBO=false;
    public static boolean existDubbo=false;
    //@DubboReference(url = "http://106.15.36.190:3000/edu.dhu.ws.OJWS")
    //http://106.15.36.190:3000/edu.dhu.ws.OJWS
    //dubbo://219.228.76.122:80/edu.dhu.ws.OJWS
    //https://219.228.76.122:80/edu.dhu.ws.OJWS
    //dubbo://10.10.10.1:20880/edu.dhu.ws.OJWS
    
    //@DubboReference(url = "dubbo://10.10.10.1:20880/edu.dhu.ws.OJWS")
    //@Resource
    public OJWS dubboPort;
    public static OJWS initDubboPort(String url) {
        // 应用配置
        ApplicationConfig application = new ApplicationConfig();
        application.setName("consumer");

        // 引用远程服务
        ReferenceConfig<OJWS> reference = new ReferenceConfig<>();
        reference.setApplication(application);
        reference.setInterface(OJWS.class);
        reference.setUrl(url); // 设置远程服务的 URL

        // 初始化
        return reference.get();
    }
    private void setDubbo(){
        
         String url = "dubbo://localhost:8080/edu.dhu.ws.OJWS";
         JTextField ip=getDistributorField(0);
         JTextField port=getDistributorField(1);
         if(ip!=null)//测试时获取不到控件
            url=String.format("dubbo://%s:%s/edu.dhu.ws.OJWS",ip.getText(),port.getText());
        try
        {
            JEditorPane infoPane=getJudgeInfoEditorPane(0);
            if(!existDubbo&&infoPane!=null)
                infoPane.setText(infoPane.getText()+LocalTime.now().toString()+"正在请求Dubbo服务...\n");
            //String url = "dubbo://219.228.76.122:20880/edu.dhu.ws.OJWS";//todo:需要改成用户自己输入
            dubboPort = initDubboPort(url);
        }
        catch(Exception e){
                 JEditorPane infoPane=getJudgeInfoEditorPane(1);
                infoPane.setText(infoPane.getText()+LocalTime.now().toString()+e.getMessage()+"\n");
            e.printStackTrace();
        }
        if(dubboPort!=null){
             JEditorPane infoPane=getJudgeInfoEditorPane(0);
             if(!existDubbo&&infoPane!=null)
                infoPane.setText(infoPane.getText()+LocalTime.now().toString()+"\nDubbo服务连接成功！URL:"+url+"\n");
             existDubbo=true;
            servicePort=dubboPort;
        }else{
            existDubbo=false;
            JEditorPane infoPane=getJudgeInfoEditorPane(1);
            infoPane.setText(infoPane.getText()+LocalTime.now().toString()+"\nDubbo服务连接失败！URL:"+url+"\n");
        }
            
        //如果能使用dubbo服务就使用，不能的话就用原来的
        
    }

    public Webservice(){
        try {
            DisableHostnameVerifier.disable();
        } catch (Exception ex) {
            Logger.getLogger(Webservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(!ENABLE_DUBBO){
            try{
                
                    webs = new OJWS_Service();
                    servicePort = webs.getOJWSImplPort();
                }
            catch(Exception e){
               e.printStackTrace();
            }
        }
        else
            setDubbo();
    }
    public Webservice(URL url,QName qname)throws java.rmi.RemoteException, MalformedURLException{
        url=new URL("https",url.getHost(),url.getPort(),url.getFile());
        try {
            DisableHostnameVerifier.disable();
        } catch (Exception ex) {
            Logger.getLogger(Webservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(!ENABLE_DUBBO){
            try{
                webs = new OJWS_Service(url,qname);
                servicePort = webs.getOJWSImplPort();

            }
            catch(Exception e){
                JEditorPane infoPane=getJudgeInfoEditorPane(1);
                infoPane.setText(infoPane.getText()+LocalTime.now().toString()+e.getMessage()+"\n");
            }
        }
        else
            setDubbo();
    }
    public String getSolutions(int arg0)throws java.rmi.RemoteException{
         String s =this.servicePort.wsGetSolutions("judge","judge123",arg0);
        return s;
    }
    public String getProblem(int arg)throws java.rmi.RemoteException{
//        servicePort.wsGetProblem(arg0, arg1, arg, arg)
        byte[] soucre = this.servicePort.wsGetProblem4Judge("felix", "felix", arg);
        String result = Decrypt.decrypt("felix10000", soucre);
        //String prob = result.replaceFirst("GBK", "UTF-8");
        return result;
    }
    public String updateResult(String arg)throws java.rmi.RemoteException{
         byte[] data=Decrypt.encrypt("judge123", arg);
        String s = servicePort.wsUpdateResult("judge","judge123",data);
        return s;
    }
    public static void main(String[] args) {
        Webservice ws=new Webservice();
        
        try {
            System.out.println(ws.getProblem(75));
        } catch (RemoteException ex) {
            Logger.getLogger(Webservice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

//////P.S.部署到服务器时要注意账号密码修改
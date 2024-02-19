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
import java.net.URL;
import javax.annotation.Resource;
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
    //@DubboReference(url = "http://106.15.36.190:3000/edu.dhu.ws.OJWS")
    //http://106.15.36.190:3000/edu.dhu.ws.OJWS
    //dubbo://219.228.76.122:80/edu.dhu.ws.OJWS
    //https://219.228.76.122:80/edu.dhu.ws.OJWS
    //dubbo://10.10.10.1:20880/edu.dhu.ws.OJWS
    
    //@DubboReference(url = "dubbo://10.10.10.1:20880/edu.dhu.ws.OJWS")
    //@Resource
    public OJWS dubboPort;
    public static OJWS initDubboPort(String url) {
        // Ӧ������
        ApplicationConfig application = new ApplicationConfig();
        application.setName("consumer");

        // ����Զ�̷���
        ReferenceConfig<OJWS> reference = new ReferenceConfig<>();
        reference.setApplication(application);
        reference.setInterface(OJWS.class);
        reference.setUrl(url); // ����Զ�̷���� URL

        // ��ʼ��
        return reference.get();
    }


    public Webservice(){
        webs = new OJWS_Service();
        try{
            servicePort = webs.getOJWSImplPort();

        }
        catch(Exception e){}
        try
        {
            String url = "dubbo://192.168.0.101:20880/edu.dhu.ws.OJWS";//todo:��Ҫ�ĳ��û��Լ�����
            dubboPort = initDubboPort(url);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        if(dubboPort!=null){
            
            servicePort=dubboPort;
        }
            
        //�����ʹ��dubbo�����ʹ�ã����ܵĻ�����ԭ����
        
    }
    public Webservice(URL url,QName qname)throws java.rmi.RemoteException{
        webs = new OJWS_Service(url,qname);
        servicePort = webs.getOJWSImplPort();
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
        System.out.println(ws.dubboPort.test("a"));
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import util.Decrypt;
import edu.dhu.ws.OJWS;
import edu.dhu.ws.OJWS_Service;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import javax.xml.namespace.QName;

/**
 *
 * @author ytxlo
 */
public class Webservice {
    private OJWS_Service webs;  
    private OJWS servicePort;
    public Webservice(){
        webs = new OJWS_Service();
        servicePort = webs.getOJWSImplPort();
    }
    public Webservice(URL url,QName qname){
        webs = new OJWS_Service(url,qname);
        servicePort = webs.getOJWSImplPort();
    }
    public String getSolutions(int arg0){
         String s =this.servicePort.wsGetSolutions("judge","judge123",arg0);
        return s;
    }
    public String getProblem(int arg){
//        servicePort.wsGetProblem(arg0, arg1, arg, arg)
        byte[] soucre = this.servicePort.wsGetProblem4Judge("felix", "felix", arg);
        String result = Decrypt.decrypt("felix10000", soucre);
        //String prob = result.replaceFirst("GBK", "UTF-8");
        return result;
    }
    public String updateResult(String arg){
         byte[] data=Decrypt.encrypt("judge123", arg);
        String s = servicePort.wsUpdateResult("judge","judge123",data);
        return s;
    }
}

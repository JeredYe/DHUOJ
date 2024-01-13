/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.service.web;

import edu.dhu.ws.OJWS;
import edu.dhu.ws.OJWS_Service;
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
    public String login(String username,String password){
        String re = servicePort.wsLogin(username, password);
        return re;
    }
    public String getExamList(String username,String password){
        String re = servicePort.wsGetExamList(username, password);
        return re;
    }
    public String getExamProblems(String username,String password,int examid){
        String re = servicePort.wsGetExamProblems(username, password, examid);
        return re;
    }
    public byte[] getProblem(String username,String password,int examId,int problemId){
        byte[] re = servicePort.wsGetProblem(username, password,examId, problemId);
        return re;
    }
    public String getExamDetil(String username,String password,int examid){
        String re = servicePort.wsGetExamDetail(username, password, examid);
        return re;
    }
    public String getExamById(String username,String password,int examid){
        String re = servicePort.wsGetExamById(username, password, examid);
        return re;
    }
    public String getExamProblemStatus(String username,String password,int examid,int problemid){
        String re = servicePort.wsGetExamProblemStatus(username, password, examid, problemid);
        return re;
    }
    public String submitThisProblem(String username,String password,String problemXml){
        String re = servicePort.wsSubmitThisProblem(username, password, problemXml);
        return re;
    }
    public String viewWrongCase(String username,String password,int examid,int problemid,int wrongnum,boolean bool){
        String re = servicePort.wsViewWrongCase(username, password, examid, problemid, wrongnum, bool);
        return re;
    }
    public String submitCode(String username,String password,String codeXml){
        String re = servicePort.wsSubmitCode(username, password, codeXml);
        return re;
    }
    public String submittedCode(String username,String password,int problemid){
        String re = servicePort.wsSubmittedCode(username, password, problemid);
        return re;
    }
    public String isPermit(String username,String password,int examId,String uuid){
        String re = servicePort.wsIsPermit(username, password, examId, uuid);
        return re;
    }
}

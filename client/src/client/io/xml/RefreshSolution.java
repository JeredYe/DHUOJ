/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.io.xml;

import client.util.Control;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author 毛泉
 */
public class RefreshSolution {
    private Document document;


    public void init() {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            this.document = db.newDocument();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }
    public String getStatus(String fileName) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(fileName);
            Element root = document.getDocumentElement();
            
             NodeList Status = root.getElementsByTagName("status");
             String status = Status.item(0).getTextContent();
             
             return status;
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String();
    }
    
    public String isFinished(String fileName) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(fileName);
            Element root = document.getDocumentElement();
            
             NodeList Status = root.getElementsByTagName("finished");
             String status = Status.item(0).getTextContent();
             
             return status;
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String();
    }
    public String getSourceCode(String fileName){
         try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(fileName);
            Element root = document.getDocumentElement();
             NodeList Code = root.getElementsByTagName("code");
             String code = Code.item(0).getTextContent();
             return code;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String();
    }
    
    public String getSolutionId(String fileName){
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(fileName);
            Element root = document.getDocumentElement();
            NodeList rspMsg = root.getElementsByTagName("solutionId");
            String serverId  = rspMsg.item(0).getTextContent();
            return serverId;
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String();
    }
    
    
    public String getScore(String fileName) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(fileName);
            Element root = document.getDocumentElement();
            
             NodeList Score = root.getElementsByTagName("score");
             String score = Score.item(0).getTextContent();
             
             return score;
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String();
    }
    
    public String getSubmitTimes(String fileName){
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(fileName);
            Element root = document.getDocumentElement();
            NodeList rspMsg = root.getElementsByTagName("submit");
            String submitTime  = rspMsg.item(0).getTextContent();
           
            return submitTime;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String();
    }
    
    public String getRemark(String fileName) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(fileName);
            Element root = document.getDocumentElement();
            
            NodeList Remark = root.getElementsByTagName("remark");
            String remark = Remark.item(0).getTextContent();
            return remark;   
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String();
    }
    
     public String getCorrectIds(String fileName) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(fileName);
            Element root = document.getDocumentElement();
            
            NodeList CorrectCaseIds = root.getElementsByTagName("correctCaseIds");
            String correctCaseIds = CorrectCaseIds.item(0).getTextContent();
            return correctCaseIds;   
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String();
    }
   
    public List<WrongCase> getWrongCases(String fileName){
           try{
             DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
             DocumentBuilder db = dbf.newDocumentBuilder();
             Document document = db.parse(fileName);
             Element root = document.getDocumentElement();
             
             List<WrongCase> ans =  new ArrayList<WrongCase>();
             NodeList wrong = root.getElementsByTagName("Case");
             int size = wrong.getLength();
             for(int j=0;j<size;j++){
                 String ID = wrong.item(j).getChildNodes().item(1).getTextContent();
                 String OUTPUT = wrong.item(j).getChildNodes().item(3).getTextContent();
                 //String STATUS = wrong.item(j).getChildNodes().item(5).getTextContent();
                 String STATUS = "";
                 WrongCase tp = new WrongCase(ID,OUTPUT,STATUS);
                 ans.add(tp);
             }
             return ans;
         }catch(FileNotFoundException e){
             e.printStackTrace();
         }catch(ParserConfigurationException e){
             e.printStackTrace();
         }catch(SAXException e){
             e.printStackTrace();
         }catch(IOException e){
             e.printStackTrace();
         }
         return new ArrayList<WrongCase>();
    }
    
    public Boolean isNew(String fileName,String problemId){
            String serverId  = getSolutionId(fileName);
            String routing = "./xml/"+Control.getPath()+"/"+Control.getExamId()+"-"+problemId+".xml";
            //不存在就当作是新的
            if(!fileExists(routing)) return true;
            else{
                String fileId = getFileSolutionId(routing);
                if (compare(fileId,serverId)) {
                    return true;
                } else {
                    return false;
                }
            }
    }
    
    public Boolean compare(String inFile,String inServer){
        int fileId = Integer.parseInt(inFile);
        int ServerId = Integer.parseInt(inServer);
        if(fileId<ServerId) return true;
        return false; //服务器比文件新 true 否则false
    }
    
    private Boolean fileExists(String fileName){
        File tmp = new File(fileName);
        SolutionCode sf = new SolutionCode();
        sf.init();
        if(tmp.exists() && !sf.isEmpty(fileName)){
            return true;
        }
        return false;
    }
    
    private String getFileSolutionId(String fileName){
         SolutionCode tmp = new SolutionCode();
         tmp.init();
         return tmp.getId(fileName);
    }
    
    public String hasSubmitted(String fileName){
        try{
             DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
             DocumentBuilder db = dbf.newDocumentBuilder();
             Document document = db.parse(fileName);
             Element root = document.getDocumentElement();
             
             NodeList rspMsg = root.getElementsByTagName("rspMsg");
             if(rspMsg.getLength() == 0) return "false";
             String status  = rspMsg.item(0).getTextContent();
             
             if(status.equals("Success")){
                 return "true";
             }
             return status;
         }catch(FileNotFoundException e){
             e.printStackTrace();
         }catch(ParserConfigurationException e){
             e.printStackTrace();
         }catch(SAXException e){
             e.printStackTrace();
         }catch(IOException e){
             e.printStackTrace();
         }
         return "false";
    }
    
//    public static void main(String[] args){
//        RefreshSolution a = new RefreshSolution();
//        a.init();
//        a.getWrongCases("./xml/a.xml");
//    }
}

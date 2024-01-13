/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.io.xml;

import client.util.Control;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import main.Answer;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author 毛泉
 */
public class SubmitCode {

    private Document document;
    String codes;

    public void init() {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            this.document = db.newDocument();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
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

    public String getSolutionId(String fileName) {
        try{
             DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
             DocumentBuilder db = dbf.newDocumentBuilder();
             Document document = db.parse(fileName);
             Element root = document.getDocumentElement();
             
             
             NodeList Id = root.getElementsByTagName("solutionId");
             String Ids = Id.item(0).getTextContent();
             
             return Ids;
         }catch(FileNotFoundException e){
             e.printStackTrace();
         }catch(ParserConfigurationException e){
             e.printStackTrace();
         }catch(SAXException e){
             e.printStackTrace();
         }catch(IOException e){
             e.printStackTrace();
         }
         return new String();
    }
    
    public String getSubmitTime(String fileName){
        try{
             DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
             DocumentBuilder db = dbf.newDocumentBuilder();
             Document document = db.parse(fileName);
             Element root = document.getDocumentElement();
             
             NodeList rspMsg = root.getElementsByTagName("time");
             String time  = rspMsg.item(0).getTextContent();
            
            return time;
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
    
    public String isSucceed(String fileName){
           try{
             DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
             DocumentBuilder db = dbf.newDocumentBuilder();
             Document document = db.parse(fileName);
             Element root = document.getDocumentElement();
             
             NodeList rspMsg = root.getElementsByTagName("rspMsg");
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

    public String SubmitCode(String examId,String problemId,String language,String sourceCode,Answer answer){
        Element root = this.document.createElement("root");
        this.document.appendChild(root);
        Element solution = this.document.createElement("solution");
        
        Element examid = this.document.createElement("examId");
        examid.appendChild(this.document.createTextNode(examId));
        solution.appendChild(examid);
        
        Element problemid = this.document.createElement("problemId");
        problemid.appendChild(this.document.createTextNode(problemId));
        solution.appendChild(problemid);
        
        Element lang = this.document.createElement("language");
        lang.appendChild(this.document.createTextNode(language));
        solution.appendChild(lang);
        
        Element sourcecode = this.document.createElement("sourceCode");
        sourcecode.appendChild(this.document.createTextNode(sourceCode));
        solution.appendChild(sourcecode);
        
        Element sta = this.document.createElement("status");
        sta.appendChild(this.document.createTextNode(answer.getStatus()));
        solution.appendChild(sta);
        
        Element correct = this.document.createElement("correctCaseIds");
        correct.appendChild(this.document.createTextNode(answer.getCorrectCaseIds()));
        solution.appendChild(correct);
        
        Element remark = this.document.createElement("remark");
        remark.appendChild(this.document.createTextNode(answer.getRemark()));
        solution.appendChild(remark);
        
        root.appendChild(solution);
        
        Element WrongCase = this.document.createElement("wrongCases");
        int casesNum = answer.getStatusOfTestCase().length;
            for (int j = 0; j < casesNum; j++) {
                if (!answer.getStatusOfTestCase()[j].equals("AC")) {
                    Element Case = document.createElement("case");

                    Element idOfWrong = document.createElement("caseId");
                    idOfWrong.appendChild(this.document.createTextNode(answer.getTestCaseId()[j]));
                    Case.appendChild(idOfWrong);

                    Element userOutput = document.createElement("output");
                    userOutput.appendChild(this.document.createTextNode(answer.getUsersOutput()[j]));
                    Case.appendChild(userOutput);

                    WrongCase.appendChild(Case);
                }
            }
        root.appendChild(WrongCase);
        
        String fileName = "./xml/"+Control.getPath()+"/tmp.xml";
        File tmpfile = new File(fileName);
        if(tmpfile.exists()) tmpfile.delete();
        
        TransformerFactory tf = TransformerFactory.newInstance();
        try {
            Transformer transformer = tf.newTransformer();
            DOMSource source = new DOMSource(document);
            transformer.setOutputProperty(OutputKeys.ENCODING, "GBK");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            PrintWriter pw = new PrintWriter(new FileOutputStream(fileName));
            StreamResult result = new StreamResult(pw);
            transformer.transform(source, result);
            //System.out.println("成功生成xml");
            String ans = readToString(fileName);
            tmpfile.delete();
            ans=ans.replace("&#", "&amp;#");
            return ans;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        return new String();
    }

    public String readToString(String fileName) {
		String encoding = "GBK";
		File file = new File(fileName);
		Long filelength = file.length();
		byte[] filecontent = new byte[filelength.intValue()];
		try {
			FileInputStream in = new FileInputStream(file);
			in.read(filecontent);
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			return new String(filecontent, encoding);
		} catch (UnsupportedEncodingException e) {
			System.err.println("The OS does not support " + encoding);
			e.printStackTrace();
			return null;
		}
	}
    
    

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.io.xml;

import client.model.StudentExamDetail;
import client.util.Control;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author ytxlo
 */
public class StudentExamDetail_io {
    String str = "./xml/"+Control.getPath()+"/studentExamDetaillist.xml";  

    public StudentExamDetail_io(){
        super();
    }
    public StudentExamDetail_io(String examId){
        str ="./xml/"+Control.getPath()+"/studentExamDetail_"+examId+".dat";  
    }
    public void add(StudentExamDetail s){  
        try {  
            Document document = XmlUtils.getDocument(str,Control.getKey());  
            
            Element studentExamDetaillist = document.getDocumentElement();          
            Element studentExamDetail = document.createElement("problem");
            
            //Element id = document.createElement("id");         
            Element solutionId = document.createElement("solutionId");             
            Element problemId  = document.createElement("problemId");
            Element status = document.createElement("status");
            Element hintCases = document.createElement("hintCases");
            Element score = document.createElement("score");
            Element elapsedTime = document.createElement("elapsedTime");
            Element finished = document.createElement("finished");
            
            solutionId.setTextContent(s.getSolutionId());
            problemId.setTextContent(s.getProblemId());
            status.setTextContent(s.getStatus());
            hintCases.setTextContent(s.getHintCases());
            score.setTextContent(s.getScore());
            elapsedTime.setTextContent(s.getElapsedTime());
            finished.setTextContent(s.getFinished());
 
            studentExamDetail.appendChild(solutionId); 
            studentExamDetail.appendChild(problemId);  
            studentExamDetail.appendChild(status);  
            studentExamDetail.appendChild(hintCases);  
            studentExamDetail.appendChild(score);  
            studentExamDetail.appendChild(elapsedTime);  
            studentExamDetail.appendChild(finished);  
            
            studentExamDetaillist.appendChild(studentExamDetail);  
            XmlUtils.DocumentToString(document, str,"GBK",Control.getKey()); 
              

        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
          
    }  
    public List<StudentExamDetail> getstudentExamDetaillist(){
        try {  
            List<StudentExamDetail> l = new ArrayList();
            StringBuffer src = new StringBuffer(str);
            src.delete(src.length()-3, src.length());
            src.append("dat");
            File f = new File(src.toString());
            if(!f.exists()){
                return l;
            }
            Document document = XmlUtils.getDocument(str,Control.getKey()); 
            NodeList list = document.getElementsByTagName("problem");  
            int a = list.getLength();
            for(int i=0;i<list.getLength();i++){  

                Element ele = (Element)list.item(i); 
                StudentExamDetail s = new StudentExamDetail();
                s.setProblemId(ele.getElementsByTagName("problemId").item(0).getTextContent());
                s.setStatus(ele.getElementsByTagName("status").item(0).getTextContent());
                s.setHintCases(ele.getElementsByTagName("hintCases").item(0).getTextContent());
                s.setScore(ele.getElementsByTagName("score").item(0).getTextContent());
                s.setElapsedTime(ele.getElementsByTagName("elapsedTime").item(0).getTextContent());
                s.setFinished(ele.getElementsByTagName("finished").item(0).getTextContent());
                s.setSolutionId(ele.getElementsByTagName("solutionId").item(0).getTextContent());
                l.add(s);
            }  
            return l;  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
    }
    public void changeStatus(String problemId,String status,String finished,String SolutionId){
        try {
            Document document = XmlUtils.getDocument(str,Control.getKey());
            NodeList list = document.getElementsByTagName("problem");
            for(int i=0;i<list.getLength();i++){
                Element ele = (Element)list.item(i);
                if (ele.getElementsByTagName("problemId").item(0).getTextContent().equals(problemId)){
                    ele.getElementsByTagName("status").item(0).setTextContent(status);
                    ele.getElementsByTagName("finished").item(0).setTextContent(finished);
                    ele.getElementsByTagName("solutionId").item(0).setTextContent(SolutionId);
                }
            }            
            XmlUtils.DocumentToString(document, str,"GBK",Control.getKey()); 
            
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        } 
    }
}

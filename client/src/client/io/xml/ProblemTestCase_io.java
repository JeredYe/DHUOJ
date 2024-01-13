/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.io.xml;

import client.model.ProblemTestCase;
import client.util.Control;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author ytxlo
 */
public class ProblemTestCase_io {
    String str = null; 
    String problemId;
    
    public ProblemTestCase_io(){
    }
    public ProblemTestCase_io(String problemId){
        str = "./xml/"+Control.getPath()+"/problem_"+Control.getExamId()+"-"+problemId+".dat";
        this.problemId = problemId;
    }
//    public void add(ProblemTestCase p){  
//        try {  
//
//            Document document = XmlUtils.getDocument(str,Control.getKey());  
//            Element problemTestCaselist = document.getDocumentElement();          
//            Element problemTestCase = document.createElement("problemTestCase");
//            
//            Element id = document.createElement("id");         
//            Element problemId = document.createElement("problemId");             
//            Element input  = document.createElement("input");
//            Element output = document.createElement("output");
//            
//            id.setTextContent(p.getId());
//            problemId.setTextContent(p.getProblemId());  
//            input.setTextContent(p.getInput());
//            output.setTextContent(p.getOutput());
//            
//            problemTestCase.appendChild(id);  
//            problemTestCase.appendChild(problemId);
//            problemTestCase.appendChild(input);
//            problemTestCase.appendChild(output);
//            
//            problemTestCaselist.appendChild(problemTestCase);  
//
//            XmlUtils.write2Xml(document, str);  
//
//        } catch (Exception e) {  
//            throw new RuntimeException(e);  
//        }  
//          
//    }  
     public List<ProblemTestCase> getProblemTestCaselist(){
        try {  
            List<ProblemTestCase> l = new ArrayList();
            Document document = XmlUtils.getDocument(str,Control.getKey());  
            NodeList list = document.getElementsByTagName("Case");  
            for(int i=0;i<list.getLength();i++){  
                Element ele = (Element)list.item(i);  
                ProblemTestCase p = new ProblemTestCase();
                p.setId(ele.getElementsByTagName("id").item(0).getTextContent());
                p.setProblemId(problemId);
                p.setInput(ele.getElementsByTagName("input").item(0).getTextContent());
                p.setOutput(ele.getElementsByTagName("output").item(0).getTextContent());
                
                l.add(p);
            }  
            return l;  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.io.xml;

import client.model.Solution;
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
public class Solution_io {
    String str = "./xml/"+Control.getPath()+"/SolutionWrongCase.xml";  
  
    public List<Solution> getSolutionlist(){
        try {  
            List<Solution> l = new ArrayList();
            Document document = XmlUtils.getDocument(str,Control.getKey());  

            NodeList list = document.getElementsByTagName("solution");  

            for(int i=0;i<list.getLength();i++){  
                Element ele = (Element)list.item(i);  

                Solution s = new Solution();
                //ele.getElementsByTagName("id").item(0).getTextContent();
                s.setExamId(ele.getElementsByTagName("examId").item(0).getTextContent());
                s.setProblemId(ele.getElementsByTagName("problemId").item(0).getTextContent());
                s.setLanguage(ele.getElementsByTagName("language").item(0).getTextContent());
                
                s.setSourceCode(ele.getElementsByTagName("sourceCode").item(0).getTextContent());
                s.setStatus(ele.getElementsByTagName("status").item(0).getTextContent());   
                s.setCorrectCaseIds(ele.getElementsByTagName("correctCaseIds").item(0).getTextContent());
                
                s.setRemark(ele.getElementsByTagName("remark").item(0).getTextContent());
                
                l.add(s);
            }  
            return l;  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.io.xml;

import client.model.Solution;
import client.model.WrongCase;
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
public class WrongCase_io {
    String str = "src/xml/"+Control.getPath()+"/SolutionWrongCase.dat";  
  
    public List<WrongCase> getWrongCaselist(){
        try {  
            List<WrongCase> l = new ArrayList();
            Document document = XmlUtils.getDocument(str,Control.getKey());  

            NodeList list = document.getElementsByTagName("wrongCases");  

            for(int i=0;i<list.getLength();i++){  
                Element ele = (Element)list.item(i);  

                WrongCase s = new WrongCase();
                //ele.getElementsByTagName("id").item(0).getTextContent();
                s.setCaseId(ele.getElementsByTagName("caseId").item(0).getTextContent());
                s.setOutput(ele.getElementsByTagName("output").item(0).getTextContent());
                
                l.add(s);
            }  
            return l;  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
    }
}

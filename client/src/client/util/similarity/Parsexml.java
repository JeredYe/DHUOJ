/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.util.similarity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
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
 * @author ëȪ
 */
public class Parsexml {
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

    public List<submittedCode> getCode(String fileName) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(fileName);
            Element root = document.getDocumentElement();
            
             NodeList rspMsg = root.getElementsByTagName("rspMsg");
             String msg = rspMsg.item(0).getTextContent();
             List<submittedCode> submitted = new ArrayList<submittedCode>();
             
             if(msg.equals("Success")){
                 NodeList records = root.getElementsByTagName("record");
                 int size = records.getLength();
                 for(int i = 0;i<size;i++){
                     String id = records.item(i).getChildNodes().item(1).getTextContent();
                     String solutionId = records.item(i).getChildNodes().item(3).getTextContent();
                     String code = records.item(i).getChildNodes().item(5).getTextContent();
                     
                     submittedCode node = new submittedCode(id,solutionId,code);
                     submitted.add(node);
                 }
             }else{
                 submittedCode node = new submittedCode("-1","-1","-1");
                 submitted.add(node);
             }
             
             return submitted;
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<submittedCode>();
    }
}

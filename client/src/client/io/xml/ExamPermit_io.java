/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.io.xml;
import client.model.*;
import client.util.Control;
import java.io.File;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
/**
 *
 * @author ytxlo
 */
public class ExamPermit_io {
    
    public ExamPermit getExamPermitFromString(String str){
        ExamPermit examPermit = null;
        try {
            examPermit = new ExamPermit();
            Document doc = XmlUtils.StringToDocument(str);
            Element root = (Element)doc.getElementsByTagName("root").item(0);
            if (root.getElementsByTagName("rspMsg").getLength()!=0) examPermit.setRspMsg(root.getElementsByTagName("rspMsg").item(0).getTextContent());
            if (root.getElementsByTagName("time").getLength()!=0) examPermit.setTime(root.getElementsByTagName("time").item(0).getTextContent());
            if (root.getElementsByTagName("id").getLength()!=0) examPermit.setId(root.getElementsByTagName("id").item(0).getTextContent());
            if (root.getElementsByTagName("uuid").getLength()!=0) examPermit.setUuid(root.getElementsByTagName("uuid").item(0).getTextContent());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return examPermit;
    }
    public ExamPermit getExamPermitFromLocal(int examId){
        String path = "./xml/"+Control.getPath()+"/exampermit_"+examId+".dat";
        try {
            File f = new File(path);
            if(f.exists()){
                ExamPermit examPermit = new ExamPermit();
                Document doc = XmlUtils.getDocument(path, Control.getKey());
                Element root = (Element)doc.getElementsByTagName("root").item(0);
                if (root.getElementsByTagName("rspMsg").getLength()!=0) examPermit.setRspMsg(root.getElementsByTagName("rspMsg").item(0).getTextContent());
                if (root.getElementsByTagName("time").getLength()!=0) examPermit.setTime(root.getElementsByTagName("time").item(0).getTextContent());
                if (root.getElementsByTagName("id").getLength()!=0) examPermit.setId(root.getElementsByTagName("id").item(0).getTextContent());
                if (root.getElementsByTagName("uuid").getLength()!=0) examPermit.setUuid(root.getElementsByTagName("uuid").item(0).getTextContent());
                return examPermit;
            }else{
                return null;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
}

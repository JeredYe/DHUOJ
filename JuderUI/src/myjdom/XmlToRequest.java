/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myjdom;

import myjdom.model.Request;
import org.w3c.dom.Element;

/**
 *
 * @author ytxlo
 */
public class XmlToRequest extends XmlToBase implements XmlConvert<Request>{

    @Override
    public Request convertXML() throws Exception {
        Request req = new Request();
        Element root = doc.getDocumentElement();
        req.setRspMsg(root.getElementsByTagName("rspMsg").item(0).getTextContent());
        req.setTime(root.getElementsByTagName("time").item(0).getTextContent());
        return req;
    }
    
}

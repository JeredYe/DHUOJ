/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myjdom;

import java.util.ArrayList;
import java.util.List;
import myjdom.model.Solution;
import myjdom.model.Solutions;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author ytxlo
 */
public class XmlToSolution extends XmlToBase implements XmlConvert<Solutions> {

    @Override
    public Solutions convertXML() throws Exception {
        Solutions solutions = new Solutions();
        List<Solution> solutionList = new ArrayList<>();
        Element rootElement = doc.getDocumentElement();
        solutions.setRspMsg(rootElement.getElementsByTagName("rspMsg").item(0).getTextContent());
        solutions.setTime(rootElement.getElementsByTagName("time").item(0).getTextContent());
//        Node node = doc.getDocumentElement().getElementsByTagName("solution").item(0);
//        NodeList list = node.getChildNodes();

        NodeList list=doc.getDocumentElement().getElementsByTagName("solution");
        for (int i = 0; i < list.getLength(); i++) {

            Element e = (Element)list.item(i);
            Solution s = new Solution();
            if (e.getElementsByTagName("solutionId").item(0) != null) {
                s.setSolutionId(e.getElementsByTagName("solutionId").item(0).getTextContent());
            }
            if (e.getElementsByTagName("problemId").item(0) != null) {
                s.setProblemId(e.getElementsByTagName("problemId").item(0).getTextContent());
            }
            if (e.getElementsByTagName("submitTime").item(0) != null) {
                s.setSubmitTime(e.getElementsByTagName("submitTime").item(0).getTextContent());
            }
            if (e.getElementsByTagName("language").item(0) != null) {
                s.setLangeuage(e.getElementsByTagName("language").item(0).getTextContent());
            }
            if (e.getElementsByTagName("code").item(0) != null) {
                s.setCode(e.getElementsByTagName("code").item(0).getTextContent());
            }
            solutionList.add(s);
        }
        if (solutionList.size() == 1 && solutionList.get(0).getProblemId() == null) {
            solutionList.remove(0);
        }
        solutions.setSolution(solutionList);
        return solutions;
    }
}

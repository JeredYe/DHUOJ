/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.io.xml;

import client.model.Exam;
import client.model.User;
import client.util.Control;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author ytxlo
 */
public class User_io {
    public User getUser(){
        try {  
            User user= new User();
            Document document = XmlUtils.getDocument("./xml/user.dat",Control.getPublicKey());
            Element ele = (Element)document.getElementsByTagName("root").item(0);  
            user.setRspMsg(ele.getElementsByTagName("rspMsg").item(0).getTextContent());
            if (ele.getElementsByTagName("id").getLength() != 0) user.setId(ele.getElementsByTagName("id").item(0).getTextContent());
            if (ele.getElementsByTagName("banji").getLength() != 0) user.setBanji(ele.getElementsByTagName("banji").item(0).getTextContent());
            if (ele.getElementsByTagName("chineseName").getLength() != 0) user.setChineseName(ele.getElementsByTagName("chineseName").item(0).getTextContent());
            if (ele.getElementsByTagName("email").getLength() != 0) user.setEmail(ele.getElementsByTagName("email").item(0).getTextContent());
            if (ele.getElementsByTagName("ip").getLength() != 0) user.setIp(ele.getElementsByTagName("ip").item(0).getTextContent());
            if (ele.getElementsByTagName("password").getLength() != 0) user.setPassword(ele.getElementsByTagName("password").item(0).getTextContent());
            if (ele.getElementsByTagName("studentNo").getLength() != 0) user.setStudentNo(ele.getElementsByTagName("studentNo").item(0).getTextContent());
            if (ele.getElementsByTagName("username").getLength() != 0) user.setUserName(ele.getElementsByTagName("username").item(0).getTextContent());
            if (ele.getElementsByTagName("nickName").getLength() != 0) user.setNickName(ele.getElementsByTagName("nickName").item(0).getTextContent());
            if (ele.getElementsByTagName("createDate").getLength() != 0) user.setCreateDate(ele.getElementsByTagName("createDate").item(0).getTextContent());

            return user;
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }
    }
    public User getUser(String str){
        try {  
            User user= new User();
            Document document = XmlUtils.StringToDocument(str);
            Element ele = (Element)document.getElementsByTagName("root").item(0);  
            user.setRspMsg(ele.getElementsByTagName("rspMsg").item(0).getTextContent());
            if (ele.getElementsByTagName("id").getLength() != 0) user.setId(ele.getElementsByTagName("id").item(0).getTextContent());
            if (ele.getElementsByTagName("banji").getLength() != 0) user.setBanji(ele.getElementsByTagName("banji").item(0).getTextContent());
            if (ele.getElementsByTagName("chineseName").getLength() != 0) user.setChineseName(ele.getElementsByTagName("chineseName").item(0).getTextContent());
            if (ele.getElementsByTagName("email").getLength() != 0) user.setEmail(ele.getElementsByTagName("email").item(0).getTextContent());
            if (ele.getElementsByTagName("ip").getLength() != 0) user.setIp(ele.getElementsByTagName("ip").item(0).getTextContent());
            if (ele.getElementsByTagName("password").getLength() != 0) user.setPassword(ele.getElementsByTagName("password").item(0).getTextContent());
            if (ele.getElementsByTagName("studentNo").getLength() != 0) user.setStudentNo(ele.getElementsByTagName("studentNo").item(0).getTextContent());
            if (ele.getElementsByTagName("username").getLength() != 0) user.setUserName(ele.getElementsByTagName("username").item(0).getTextContent());
            if (ele.getElementsByTagName("nickName").getLength() != 0) user.setNickName(ele.getElementsByTagName("nickName").item(0).getTextContent());
            if (ele.getElementsByTagName("createDate").getLength() != 0) user.setCreateDate(ele.getElementsByTagName("createDate").item(0).getTextContent());

            return user;
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }
    }
    public List<User> getUserList(){
        List<User> userList = new ArrayList<>();
        try {  
            Document document = XmlUtils.getDocument("./xml/user.dat",Control.getPublicKey());
            Element root = (Element)document.getElementsByTagName("root").item(0);  
            for(int i=0;i<root.getElementsByTagName("user").getLength();i++){
                User user = new User();
                Element ele = (Element)root.getElementsByTagName("user").item(i);
                user.setRspMsg(ele.getElementsByTagName("rspMsg").item(0).getTextContent());
                if (ele.getElementsByTagName("id").getLength() != 0) user.setId(ele.getElementsByTagName("id").item(0).getTextContent());
                if (ele.getElementsByTagName("banji").getLength() != 0) user.setBanji(ele.getElementsByTagName("banji").item(0).getTextContent());
                if (ele.getElementsByTagName("chineseName").getLength() != 0) user.setChineseName(ele.getElementsByTagName("chineseName").item(0).getTextContent());
                if (ele.getElementsByTagName("email").getLength() != 0) user.setEmail(ele.getElementsByTagName("email").item(0).getTextContent());
                if (ele.getElementsByTagName("ip").getLength() != 0) user.setIp(ele.getElementsByTagName("ip").item(0).getTextContent());
                if (ele.getElementsByTagName("password").getLength() != 0) user.setPassword(ele.getElementsByTagName("password").item(0).getTextContent());
                if (ele.getElementsByTagName("studentNo").getLength() != 0) user.setStudentNo(ele.getElementsByTagName("studentNo").item(0).getTextContent());
                if (ele.getElementsByTagName("username").getLength() != 0) user.setUserName(ele.getElementsByTagName("username").item(0).getTextContent());
                if (ele.getElementsByTagName("nickName").getLength() != 0) user.setNickName(ele.getElementsByTagName("nickName").item(0).getTextContent());
                if (ele.getElementsByTagName("createDate").getLength() != 0) user.setCreateDate(ele.getElementsByTagName("createDate").item(0).getTextContent());
                userList.add(user);
            }
        } catch (ParserConfigurationException | SAXException | IOException | DOMException e) {  
            e.printStackTrace();
        }
        return userList;
    }
    public void writeUserList(List<User> userList) throws Exception{
        try {  
            Document document = XmlUtils.createNewDocument();
            Element root = document.createElement("root");  
            document.appendChild(root);
            for(User user:userList){
                Element userele = document.createElement("user");
                
                Element rspMsg = document.createElement("rspMsg");
                Element id = document.createElement("id");
                Element banji = document.createElement("banji");
                Element chineseName = document.createElement("chineseName");
                Element email = document.createElement("email");
                Element ip = document.createElement("ip");
                Element password = document.createElement("password");
                Element studentNo = document.createElement("studentNo");
                Element username = document.createElement("username");
                Element nickName = document.createElement("nickName");
                Element createDate = document.createElement("createDate");
                
                rspMsg.setTextContent(user.getRspMsg());
                id.setTextContent(user.getId());
                banji.setTextContent(user.getBanji());
                chineseName.setTextContent(user.getChineseName());
                email.setTextContent(user.getEmail());
                ip.setTextContent(user.getIp());
                password.setTextContent(user.getPassword());
                studentNo.setTextContent(user.getStudentNo());
                username.setTextContent(user.getUserName());
                nickName.setTextContent(user.getNickName());
                createDate.setTextContent(user.getCreateDate());

                userele.appendChild(rspMsg);
                userele.appendChild(id);
                userele.appendChild(banji);
                userele.appendChild(chineseName);
                userele.appendChild(email);
                userele.appendChild(ip);
                userele.appendChild(password);
                userele.appendChild(studentNo);
                userele.appendChild(username);
                userele.appendChild(nickName);
                userele.appendChild(createDate);
                
                root.appendChild(userele);
            }
            XmlUtils.DocumentToString(document, "./xml/user.dat", "GBK", Control.getPublicKey());
        } catch (ParserConfigurationException | SAXException | IOException | DOMException e) {  
            e.printStackTrace();
        }
    }
//    public void addUser(User user){
//            Document document = XmlUtils.getDocument("./xml/user.xml","GBK");
//            Element ele = (Element)document.getElementsByTagName("root").item(0);  
//            for(int i=0;;i++)ele.getElementsByTagName("user");
//            user.setRspMsg(ele.getElementsByTagName("rspMsg").item(0).getTextContent());
//    }
}

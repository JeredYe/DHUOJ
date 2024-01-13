/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.model;

/**
 *
 * @author ytxlo
 */
public class User {
    private String rspMsg;
    private String id;
    private String banji;
    private String chineseName;
    private String email;
    private String ip;
    private String password;
    private String studentNo;
    private String userName;
    private String nickName;
    private String createDate;
    public User(){
        super();
    }
    public User(String rspMsg,String id,String banji,String chineseName,String email,String ip,String password,String studentNo,String userName,String nickName,String createDate){
        super();
        this.rspMsg = rspMsg;
        this.id = id;
        this.banji = banji;
        this.chineseName = chineseName;
        this.email = email;
        this.ip = ip;
        this.password = password;
        this.studentNo = studentNo;
        this.userName = userName;
        this.nickName = nickName;
        this.createDate = createDate;
    }
    public String getRspMsg(){
        return this.rspMsg;
    }
    public void setRspMsg(String str){
        this.rspMsg = str;
    }
    public String getId(){
        return this.id;
    }
    public void setId(String str){
        this.id = str;
    }
    public String getBanji(){
        return this.banji;
    }
    public void setBanji(String str){
        this.banji = str;
    }
    public String getChineseName(){
        return this.chineseName;
    }
    public void setChineseName(String str){
        this.chineseName = str;
    }
    public String getEmail(){
        return this.email;
    }
    public void setEmail(String str){
        this.email = str;
    }
    public String getIp(){
        return this.ip;
    }
    public void setIp(String str){
        this.ip = str;
    }
    public String getPassword(){
        return this.password;
    }
    public void setPassword(String str){
        this.password = str;
    }
    public String getStudentNo(){
        return this.studentNo;
    }
    public void setStudentNo(String str){
        this.studentNo = str;
    }
    public String getUserName(){
        return this.userName;
    }
    public void setUserName(String str){
        this.userName = str;
    }
    public String getNickName(){
        return this.nickName;
    }
    public void setNickName(String str){
        this.nickName = str;
    }
    public String getCreateDate(){
        return this.createDate;
    }
    public void setCreateDate(String str){
        this.createDate = str;
    }
    
}

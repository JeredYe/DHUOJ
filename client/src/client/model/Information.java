/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.model;

/**
 *
 * @author ëȪ
 */
public  class Information {
    static String ip;
    static String username;
    static String passwd;

    public Information(String ip, String username, String passwd) {
        this.ip = ip;
        this.username = username;
        this.passwd = passwd;
    }

    public Information() {
    }
    
    
    
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
    
    
}

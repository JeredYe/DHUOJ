/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.io.xml;

/**
 *
 * @author ëȪ
 */
public class WrongCase {
    String id;
    String output;
    String status;

    public WrongCase(String id, String output) {
        this.id = id;
        this.output = output;
    }
    
    public WrongCase(String id, String output,String status) {
        this.id = id;
        this.output = output;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
   
    
}

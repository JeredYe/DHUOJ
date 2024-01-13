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
public class WrongCase {
    private String caseId;
    private String output;
    public WrongCase(){
        super();
    }
    public WrongCase(String caseId,String output){
        super();
        this.caseId = caseId;
        this.output = output;
    }
    public String getCaseId(){
        return this.caseId;
    }
    public void setCaseId(String str){
        this.caseId = str;
    }
    
    public String getOutput(){
        return this.output;
    }
    public void setOutput(String str){
        this.output = str;
    }
}

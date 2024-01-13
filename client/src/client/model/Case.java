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
public class Case {
    private String id;
    private String input;
    private String output;
    public Case(){
        super();
    }
    public Case(String id,String input,String output){
        super();
        this.id = id;
        this.input = input;
        this.output = output;
    }
    public String getId(){
        return this.id;
    }
    public void setId(String str){
        this.id = str;
    }
    public String getInput(){
        return this.input;
    }
    public void setInput(String str){
        this.input = str;
    }
    public String getOutput(){
        return this.output;
    }
    public void setOutput(String str){
        this.output = str;
    }
}

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
public class ProblemTestCase {
    private String id;
    private String problemId;
    private String input;
    private String output;
    public ProblemTestCase(){
        super();
    }
    public ProblemTestCase(String id, String problemId,String input,String output){
        super();
        this.id = id;
        this.problemId = problemId;
        this.input = input;
        this.output = output;
    }
    
    public String getId(){
        return id;
    }
    public void setId(String str){
        this.id = str;
    }
    public String getProblemId(){
        return problemId;
    }
    public void setProblemId(String str){
        this.problemId = str;
    }
    public String getInput(){
        return input;
    }
    public void setInput(String str){
        this.input = str;
    }
    public String getOutput(){
        return output;
    }
    public void setOutput(String str){
        this.output = str;
    }
}

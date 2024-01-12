/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.oj_beans;

/**
 *
 * @author Administrator
 */
public class ProblemTestCaseBean {
    Integer id;
    Integer problemId;
    String input;
    String output="";

//    public ProblemTestCaseBean(String input, String output) {
//        this.id = 0;
//        this.problemId = 0;
//        this.input = input;
//        this.output = output;
//    }
    
  
    
    
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProblemId() {
        return problemId;
    }

    public void setProblemId(Integer problemId) {
        this.problemId = problemId;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }
    
    
   
    
}

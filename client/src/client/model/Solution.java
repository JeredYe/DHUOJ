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
public class Solution {
    private String examId;
    private String problemId;
    private String language;
    
    private String sourceCode;
    private String status; 
    private String correctCaseIds;
    
    private String remark;
    public Solution(){
        super();
    }
    public Solution(String examId,String problemId,String language,String sourceCode,String status,String correctCaseIds,String remark){
        super();
        this.examId = examId;
        this.problemId = problemId;
        this.language = language;
        
        this.sourceCode = sourceCode;
        this.status = status;
        this.correctCaseIds = correctCaseIds;

        this.remark = remark;
        
    }

      
    public String getExamId(){
        return examId;
    }
    public void setExamId(String str){
        this.examId = str;
    }
    
    public String getProblemId(){
        return problemId;
    }
    public void setProblemId(String str){
        this.problemId = str;
    }
     
    public String getLanguage(){
        return language;
    }
    public void setLanguage(String str){
        this.language = str;
    }
    
    public String getSourceCode(){
        return sourceCode;
    }
    public void setSourceCode(String str){
        this.sourceCode = str;
    }

    public String getStatus(){
        return status;
    }
    public void setStatus(String str){
        this.status = str;
    }
    
    public String getCorrectCaseIds(){
        return correctCaseIds;
    }
    public void setCorrectCaseIds(String str){
        this.correctCaseIds = str;
    }
   
    public String getRemark(){
        return remark;
    }
    public void setRemark(String str){
        this.remark = str;
    }
    
}

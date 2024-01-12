/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.List;
import persistence.oj_beans.ProblemTestCaseBean;

/**
 *
 * @author 毛泉
 */
public class Answer {
    private String language;
    private String solutionId;
    private String problemId;
    String[] testCaseId;
    String[] usersOutput;
    String[] statusOfTestCase;
    String status;
    String remark;
    String correctCaseIds;
    private String sourceCode;
    int[] result ;
    String[] output;
    String[] remarks;
    private List<ProblemTestCaseBean> testCaseBeanList;
    public Answer(String solutionId,String problemId,String language,String[] testCaseId, String[] usersOutput, String[] statusOfTestCase, String status, String remark,String correctCaseIds,List<ProblemTestCaseBean> testCaseBeanList,String sourceCode) {
        this.solutionId = solutionId;
        this.problemId = problemId;
        this.language = language;
        this.testCaseId = testCaseId;
        this.usersOutput = usersOutput;
        this.statusOfTestCase = statusOfTestCase;
        this.status = status;
        this.remark = remark;
        this.correctCaseIds = correctCaseIds;
        this.testCaseBeanList = testCaseBeanList;
        this.sourceCode = sourceCode;
    }
    public Answer(String[] testCaseId, String[] usersOutput, String[] statusOfTestCase, String status, String remark,String correctCaseIds) {
        this.testCaseId = testCaseId;
        this.usersOutput = usersOutput;
        this.statusOfTestCase = statusOfTestCase;
        this.status = status;
        this.remark = remark;
        this.correctCaseIds = correctCaseIds;
    }

    public Answer(int[] result,String[] output,String[] remarks,String remark,String correctCaseIds){
        this.result = result;
        this.output = output;
        this.remarks = remarks;
        this.remark = remark;
        this.correctCaseIds = correctCaseIds;
    }
    
    
    
    public String[] getTestCaseId() {
        return testCaseId;
    }

    public void setTestCaseId(String[] testCaseId) {
        this.testCaseId = testCaseId;
    }

    public String[] getUsersOutput() {
        return usersOutput;
    }

    public void setUsersOutput(String[] usersOutput) {
        this.usersOutput = usersOutput;
    }

    public String[] getStatusOfTestCase() {
        return statusOfTestCase;
    }

    public void setStatusOfTestCase(String[] statusOfTestCase) {
        this.statusOfTestCase = statusOfTestCase;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCorrectCaseIds() {
        return correctCaseIds;
    }

    public void setCorrectCaseIds(String correctCaseIds) {
        this.correctCaseIds = correctCaseIds;
    }

    public int[] getResult() {
        return result;
    }

    public void setResult(int[] result) {
        this.result = result;
    }

    public String[] getOutput() {
        return output;
    }

    public void setOutput(String[] output) {
        this.output = output;
    }

    public String[] getRemarks() {
        return remarks;
    }

    public void setRemarks(String[] remarks) {
        this.remarks = remarks;
    }

    /**
     * @return the solutionId
     */
    public String getSolutionId() {
        return solutionId;
    }

    /**
     * @param solutionId the solutionId to set
     */
    public void setSolutionId(String solutionId) {
        this.solutionId = solutionId;
    }

    /**
     * @return the problemId
     */
    public String getProblemId() {
        return problemId;
    }

    /**
     * @param problemId the problemId to set
     */
    public void setProblemId(String problemId) {
        this.problemId = problemId;
    }

    /**
     * @return the testCaseBeanList
     */
    public List<ProblemTestCaseBean> getTestCaseBeanList() {
        return testCaseBeanList;
    }

    /**
     * @param testCaseBeanList the testCaseBeanList to set
     */
    public void setTestCaseBeanList(List<ProblemTestCaseBean> testCaseBeanList) {
        this.testCaseBeanList = testCaseBeanList;
    }

    /**
     * @return the sourceCode
     */
    public String getSourceCode() {
        return sourceCode;
    }

    /**
     * @param sourceCode the sourceCode to set
     */
    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    /**
     * @return the language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * @param language the language to set
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    
    
}
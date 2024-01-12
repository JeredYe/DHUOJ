/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myjdom.model;

import java.util.List;
import persistence.oj_beans.ProblemTestCaseBean;

/**
 *
 * @author ytxlo
 */
public class ResultBean {
    private String language;
    private String sourceCode;
    private String solutionId;
    private String problemId;
    private String status;
    private String correctCaseIds;
    private String remark;
    private List<ProblemTestCaseBean> wrongCase;

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
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the correctCaseIds
     */
    public String getCorrectCaseIds() {
        return correctCaseIds;
    }

    /**
     * @param correctCaseIds the correctCaseIds to set
     */
    public void setCorrectCaseIds(String correctCaseIds) {
        this.correctCaseIds = correctCaseIds;
    }

    /**
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark the remark to set
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return the wrongCase
     */
    public List<ProblemTestCaseBean> getWrongCase() {
        return wrongCase;
    }

    /**
     * @param wrongCase the wrongCase to set
     */
    public void setWrongCase(List<ProblemTestCaseBean> wrongCase) {
        this.wrongCase = wrongCase;
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
}
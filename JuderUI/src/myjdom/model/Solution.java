/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myjdom.model;

/**
 *
 * @author ytxlo
 */
public class Solution {
    private String solutionId;
    private String problemId;
    private String submitTime;
    private String langeuage;
    private String code;

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
     * @return the submitTime
     */
    public String getSubmitTime() {
        return submitTime;
    }

    /**
     * @param submitTime the submitTime to set
     */
    public void setSubmitTime(String submitTime) {
        this.submitTime = submitTime;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the langeuage
     */
    public String getLangeuage() {
        return langeuage;
    }

    /**
     * @param langeuage the langeuage to set
     */
    public void setLangeuage(String langeuage) {
        this.langeuage = langeuage;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myjdom.model;

import persistence.oj_beans.ProblemTestCaseBean;
import java.util.List;

/**
 *
 * @author ytxlo
 */
public class ProblemBean {
    private List<ProblemTestCaseBean> testCaseBeanList;
    private Float timeOut;

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
     * @return the timeOut
     */
    public Float getTimeOut() {
        return timeOut;
    }

    /**
     * @param timeOut the timeOut to set
     */
    public void setTimeOut(Float timeOut) {
        this.timeOut = timeOut;
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyCache;

import java.util.List;
import persistence.oj_beans.ProblemBean;
import persistence.oj_beans.ProblemTestCaseBean;


/**
 *
 * @author Administrator
 */
public class Problem {

    Integer problemId;
    ProblemBean pbean;
    List<ProblemTestCaseBean> testcases;
    long entryTime;

    public Integer getProblemId() {
        return problemId;
    }

    public void setProblemId(Integer problemId) {
        this.problemId = problemId;
    }

    public ProblemBean getPbean() {
        return pbean;
    }

    public void setPbean(ProblemBean pbean) {
        this.pbean = pbean;
    }

    public List<ProblemTestCaseBean> getTestcases() {
        return testcases;
    }

    public void setTestcases(List<ProblemTestCaseBean> testcases) {
        this.testcases = testcases;
    }

    public long getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(long entryTime) {
        this.entryTime = entryTime;
    }

   
}

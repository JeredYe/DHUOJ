/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.util.similarity;

/**
 *
 * @author ëȪ
 */
public class submittedCode {
     String id;
     String solutionId;
     String ProcessedCode1;

    public submittedCode(String id, String solutionId, String ProcessedCode1) {
        this.id = id;
        this.solutionId = solutionId;
        this.ProcessedCode1 = ProcessedCode1;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSolutionId() {
        return solutionId;
    }

    public void setSolutionId(String solutionId) {
        this.solutionId = solutionId;
    }

    public String getProcessedCode1() {
        return ProcessedCode1;
    }

    public void setProcessedCode1(String ProcessedCode1) {
        this.ProcessedCode1 = ProcessedCode1;
    }
     
     
}

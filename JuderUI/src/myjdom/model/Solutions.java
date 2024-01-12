/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myjdom.model;

import java.util.List;

/**
 *
 * @author ytxlo
 */
public class Solutions {
    private String rspMsg;
    private String time;
    private List<Solution> solution;

    /**
     * @return the rspMsg
     */
    public String getRspMsg() {
        return rspMsg;
    }

    /**
     * @param rspMsg the rspMsg to set
     */
    public void setRspMsg(String rspMsg) {
        this.rspMsg = rspMsg;
    }

    /**
     * @return the time
     */
    public String getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * @return the solution
     */
    public List<Solution> getSolution() {
        return solution;
    }

    /**
     * @param solution the solution to set
     */
    public void setSolution(List<Solution> solution) {
        this.solution = solution;
    }
 
}

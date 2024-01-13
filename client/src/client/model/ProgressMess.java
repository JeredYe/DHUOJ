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
public class ProgressMess {
    private int progress;
    private String message;
    public ProgressMess(){}
    public void setProgress(int num){
        this.progress = num;
    }
    public void setMessage(String str){
        this.message = str;
    }
    public int getProgress(){
        return this.progress;
    }
    public String getMessage(){
        return this.message;
    }
}

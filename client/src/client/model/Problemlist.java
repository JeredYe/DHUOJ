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
public class Problemlist {
    private String problemId;
    private String examId;
    private String title;
    private String difficulty;
    private String score;
    private String status;
    private String updateTime;
    public Problemlist(){
        super();
    }
    public Problemlist(String problemId,String examId,String title,String difficulty,String score,String status,String updateTime){
        this.problemId = problemId;
        this.examId = examId;
        this.title = title;
        this.difficulty = difficulty;
        this.score = score;
        this.status = status;
        this.updateTime = updateTime;
    }
    public String getProblemId(){
        return this.problemId;
    }
    public String getExamId(){
        return this.examId;
    }
    public String getTitle(){
        return this.title;
    }
    public String getDifficulty(){
        return this.difficulty;
    }
    public String getScore(){
        return this.score;
    }
    public String getStatus(){
        return this.status;
    }
    public void setTile(String str){
        this.title = str;
    }
     public void setScore(String str){
        this.score = str;
    }
    public void setDifficulty(String str){
        this.difficulty = str;
    }
    public void setStatus(String str){
        this.status = str;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
    
}

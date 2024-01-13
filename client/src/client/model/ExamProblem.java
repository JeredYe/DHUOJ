/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.model;

/**
 *
 * @author ytxlo
 * fixed by sea 题目列表的每个item的部分内容 需要与StudentExamDetail的status合成Problemlist对象
 */
public class ExamProblem {
    
    private String problemId;
    private String score;
    private String displaySequence;
    private String title;
    private String difficulty;
    private String updateTime;
    private String bestBefore;
    private String scoreCoef;
    public ExamProblem(){
        super();
    }

    public ExamProblem(String problemId, String score, String displaySequence, String title, String difficulty,String updateTime) {
        this.problemId = problemId;
        this.score = score;
        this.displaySequence = displaySequence;
        this.title = title;
        this.difficulty = difficulty;
        this.updateTime = updateTime;
    }
    
    public String getProblemId(){
        return this.problemId;
    }
    public void setProblemId(String str){
        this.problemId = str;
    }
    public String getScore(){
        return this.score;
    }
    public void setScore(String str){
        this.score = str;
    }
    public String getDisplaySequence(){
        return this.displaySequence;
    }
    public void setDisplaySequence(String str){
        this.displaySequence = str;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
    
    public String getBestBefore() {
        return bestBefore;
    }

    public void setBestBefore(String bestBefore) {
        this.bestBefore = bestBefore;
    }

    public String getScoreCoef() {
        return scoreCoef;
    }

    public void setScoreCoef(String scoreCoef) {
        this.scoreCoef = scoreCoef;
    }
}

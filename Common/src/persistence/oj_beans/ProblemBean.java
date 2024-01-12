/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.oj_beans;

/**
 *
 * @author Administrator
 */
public class ProblemBean {
    //key
   Integer id;
   String title;
   String description;
   Float memory_limit;
   Float time_limit;
   String inputRequirement;
   String outputRequirement;
   String sample_input;
   String sample_output;
   String author;
   String difficulty;
   String sourceCode;
   String srcCodeLanguage;
   String scoreGrade;
   //not null
   Integer chapterId=0;
   Integer checkSimilarity;
   Float similarityThreshold;
   Integer solved;
   Integer submit;
   Float ratio;
   //not null
   Integer teacherId=0;
   String source;

   public float timer=600;//s
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getMemory_limit() {
        return memory_limit;
    }

    public void setMemory_limit(Float memory_limit) {
        this.memory_limit = memory_limit;
    }

    public Float getTime_limit() {
        return time_limit;
    }

    public void setTime_limit(Float time_limit) {
        this.time_limit = time_limit;
    }

    public String getInputRequirement() {
        return inputRequirement;
    }

    public void setInputRequirement(String inputRequirement) {
        this.inputRequirement = inputRequirement;
    }

    public String getOutputRequirement() {
        return outputRequirement;
    }

    public void setOutputRequirement(String outputRequirement) {
        this.outputRequirement = outputRequirement;
    }

    public String getSample_input() {
        return sample_input;
    }

    public void setSample_input(String sample_input) {
        this.sample_input = sample_input;
    }

    public String getSample_output() {
        return sample_output;
    }

    public void setSample_output(String sample_output) {
        this.sample_output = sample_output;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    public String getSrcCodeLanguage() {
        return srcCodeLanguage;
    }

    public void setSrcCodeLanguage(String srcCodeLanguage) {
        this.srcCodeLanguage = srcCodeLanguage;
    }

    public String getScoreGrade() {
        return scoreGrade;
    }

    public void setScoreGrade(String scoreGrade) {
        this.scoreGrade = scoreGrade;
    }

    public Integer getChapterId() {
        return chapterId;
    }

    public void setChapterId(Integer chapterId) {
        this.chapterId = chapterId;
    }

    public Integer getCheckSimilarity() {
        return checkSimilarity;
    }

    public void setCheckSimilarity(Integer checkSimilarity) {
        this.checkSimilarity = checkSimilarity;
    }

    public Float getSimilarityThreshold() {
        return similarityThreshold;
    }

    public void setSimilarityThreshold(Float similarityThreshold) {
        this.similarityThreshold = similarityThreshold;
    }

    public Integer getSolved() {
        return solved;
    }

    public void setSolved(Integer solved) {
        this.solved = solved;
    }

    public Integer getSubmit() {
        return submit;
    }

    public void setSubmit(Integer submit) {
        this.submit = submit;
    }

    public Float getRatio() {
        return ratio;
    }

    public void setRatio(Float ratio) {
        this.ratio = ratio;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    
}

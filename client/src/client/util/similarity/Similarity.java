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
public class Similarity {
    String isCopied;
    String maxnum;
    String maxId;

    public Similarity(String isCopied, String maxnum, String maxId) {
        this.isCopied = isCopied;
        this.maxnum = maxnum;
        this.maxId = maxId;
    }

    public String getIsCopied() {
        return isCopied;
    }

    public void setIsCopied(String isCopied) {
        this.isCopied = isCopied;
    }

    public String getMaxnum() {
        return maxnum;
    }

    public void setMaxnum(String maxnum) {
        this.maxnum = maxnum;
    }

    public String getMaxId() {
        return maxId;
    }

    public void setMaxId(String maxId) {
        this.maxId = maxId;
    }
}

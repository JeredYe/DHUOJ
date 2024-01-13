/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.service;

import client.util.Control;
import client.model.Exam;
import client.model.User;
import client.view.frame.ProgressBarFrame;
import javax.swing.JOptionPane;
import client.io.xml.Exam_io;
import client.io.xml.Read_xml;
import client.io.xml.User_io;

/**
 *
 * @author ytxlo
 */
public class DoBackground {
    private Exam oldexam;
    private Exam newexam;
    public DoBackground(){
        super();
    }
    public void compareUpdateTime(){
        User user = Control.getUser();
        String username = user.getUserName();
        String passwd = user.getPassword();
        Read_xml.XMLTobean();
        if (Control.getMainFrame()==null){
            return;
        }
        this.oldexam = Control.getMainFrame().getExam();
        int examId = Integer.parseInt(oldexam.getId());
        Exam_io exio = new Exam_io();
        try {
            String str = Control.getWebsService().getExamById(username, passwd, examId);
//            Object[] obj =client.invoke("WS_GetExamById",username,passwd,examId);
            this.newexam = exio.getexamString(str);
            if (this.newexam.getUpdateTime().equals("")){
                this.newexam.setUpdateTime("2000-01-01 00:00:00");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //System.out.println(this.oldexam.getUpdateTime());
        if (!this.newexam.getUpdateTime().equals(this.oldexam.getUpdateTime())){
            exio.updateExam(newexam.getId(),newexam);
            int r =JOptionPane .showConfirmDialog(null,"考试已经更新，是否立即更新？", "提示", JOptionPane.YES_NO_OPTION);
            if (r==JOptionPane.YES_OPTION){
                new ProgressBarFrame(newexam,DownSwingWorker.PROBLEMLIST); 
            }     
        }
        
        
    }
}

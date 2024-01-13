/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.io.xml;

import client.model.Exam;

/**
 *
 * @author ytxlo
 */
public class Change_io {
    public static void changeExamStatus(String Id,String status){
        Exam_io eio = new Exam_io();
        ////System.out.println(status);
        eio.changeStatus(Id, status);
    }
    public static void  UpdateExam(String Id,Exam ex){
        Exam_io eio = new Exam_io();
        ////System.out.println(status);
        eio.updateExam(Id, ex);
    }
}

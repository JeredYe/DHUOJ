/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.view.other;

import client.service.GetServerTime;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JLabel;

import java.util.Date;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import client.io.xml.Change_io;
import client.model.Exam;
import client.service.DownSwingWorker;
import client.view.frame.ExamFrame;
import client.view.frame.ProgressBarFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
import javax.swing.Timer;

/**
 *
 * @author ytxlo
 */
public class TimeLabel extends JLabel{
    private JButton jb;
    private JLabel jl;
    private String problemId;
    private static Date time;
    private Date startDate;
    private Date endDate;
    private ProgressBarFrame pbf;
    private JProgressBar jpb;
    public TimeLabel(String startTime,String endtime,JButton jbb,String Id){
        this.jb = jbb;
        
        this.problemId = Id;

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            startDate = sdf.parse(startTime);
            endDate = sdf.parse(endtime);  
        } catch (ParseException ex) {
            Logger.getLogger(TimeLabel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        long start = GetServerTime.getNowTime().getTime();
        //end 计算结束时间;
        final Timer timer=new Timer(1000,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long show=startDate.getTime()-GetServerTime.getNowTime().getTime();
                if(show>3600000){
                    setText("未开始");
                }
                else if(show<0){
                    setText("进行中");
                    jb.setEnabled(true);
                    //Change_io.changeExamStatus(n, "进行中");
                    if(endDate.getTime()-GetServerTime.getNowTime().getTime()<=0){
                        setText("已结束");
                        jb.setEnabled(true);
                    }
                    
                }
                else if((show/1000)==0){
                    setText("考试开始");
                    jb.setEnabled(true);
                    //jb.doClick();
                    Change_io.changeExamStatus(problemId, "进行中");
                }
                else{
                    //long h=show/1000/60/60;//时
                    long m=show/1000/60%60;//分
                    long s=show/1000%60;//秒
                    setText(+m+":"+s);
                    jb.setEnabled(false);
                }
            }
        });
        timer.start();
    }
    
     public TimeLabel(Exam exam,ExamFrame ef,String startTime,String endtime,JLabel jlb,String Id,JLabel sjl,JProgressBar progressBar,ProgressBarFrame barFrame,String status,JButton jb){
        this.jl = jlb;
        this.problemId = Id;
        this.pbf = barFrame;
        this.jpb = progressBar;
        
        DownSwingWorker sw = new DownSwingWorker(sjl, jpb, exam, barFrame,status);
        sw.execute();
        
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            startDate = sdf.parse(startTime);
            endDate = sdf.parse(endtime);  
        } catch (ParseException ex) {
            Logger.getLogger(TimeLabel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        long start = GetServerTime.getNowTime().getTime();
        //end 计算结束时间;
        final Timer timer=new Timer(1000,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long show=startDate.getTime()-GetServerTime.getNowTime().getTime();
                if(show>3600000){
                    setText("未开始");
                }
                else if(show<0){
                    setText("进行中");
                    jb.setEnabled(true);
                    //Change_io.changeExamStatus(n, "进行中");
                    if(endDate.getTime()-GetServerTime.getNowTime().getTime()<=0){
                        setText("已结束");
                        jb.setEnabled(true);
                    }
                    
                }
                else if((show/1000)==0){
                    setText("考试开始");
                    jb.setEnabled(true);
                    //jb.doClick();
                    Change_io.changeExamStatus(problemId, "进行中");
                }
                else{
                    //long h=show/1000/60/60;//时
                    long m=show/1000/60%60;//分
                    long s=show/1000%60;//秒
                    setText(+m+":"+s);
                    jb.setEnabled(false);
                }
            }
        });
        timer.start();
    }
     
//     public TimeLabel(String startTime,String endtime,JLabel jlb,String Id,JButton jb){
//        this.jl = jlb;
//        this.problemId = Id;
//        
//        try {
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            startDate = sdf.parse(startTime);
//            endDate = sdf.parse(endtime);  
//        } catch (ParseException ex) {
//            Logger.getLogger(TimeLabel.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        long start = GetServerTime.getNowTime().getTime();
//        //end 计算结束时间;
//        final Timer timer=new Timer();
//        //延迟0毫秒（即立即执行）开始，每隔1000毫秒执行一次
//        timer.schedule(new TimerTask()
//        {
//            public void run()
//            {
//                //show是剩余时间，即要显示的时间
//                
//                long show=startDate.getTime()-GetServerTime.getNowTime().getTime();
//                ////System.out.println(show);
//                if(show < 0 )
//                {
//                    jl.setText("倒计时结束！");
//                    jl.setEnabled(false);
//                    jb.setEnabled(true);
//                }
//                else
//                {
//                    long m=show/1000/60%60;//分
//                    long s=show/1000%60;//秒
//                    jl.setText("倒计时:"+m+":"+s);
//                    jl.setEnabled(false);
//                }
//            }
//        },0,1000);
//        //计时结束时候，停止全部timer计时计划任务
//        
//        
//        
//    }
    public static void setNowTime(String str){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            time = sdf.parse(str);
            
        } catch (ParseException ex) {
            Logger.getLogger(TimeLabel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

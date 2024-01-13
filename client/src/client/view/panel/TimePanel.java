package client.view.panel;

import client.service.GetServerTime;
import client.view.frame.LoginFrame;
import javax.swing.*;
import java.util.*;
import java.util.Timer;
import java.util.TimerTask;
import java.text.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TimePanel extends JPanel{
	private JLabel JL_time;
	private JLabel JL_display;
	private JLabel JLabel1;
	private JLabel JLabel2;
	private JLabel JLabel3;
	private String DEFAULT_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	private String time;
        private Date endDate;
	private int ONE_SECOND = 1000;
	public TimePanel(String endtime){
            this.JLabel1 = new JLabel("");
            this.JLabel2 = new JLabel("");
            this.JLabel3 = new JLabel("");
            this.JL_time = new JLabel();
            this.JL_display = new JLabel();
            SimpleDateFormat dateFormatter = new SimpleDateFormat(DEFAULT_TIME_FORMAT);
            try {
                this.endDate = dateFormatter.parse(endtime);
            } catch (ParseException ex) {
                Logger.getLogger(TimePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(LoginFrame.getLogin()==true){
                configTimeArea();
            }
            else{
                this.JL_time.setText("");
            }
            //this.setLayout(new java.awt.GridLayout(1, 3));

            this.JL_display.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

            this.add(this.JL_display);
            //this.add(this.JLabel1);
            //this.add(this.JLabel2);
            //this.add(this.JLabel3);
	}
	private void configTimeArea(){
		Timer Ter = new Timer();
		Ter.scheduleAtFixedRate(new JLabelTimerTask(),new Date(), ONE_SECOND);
	}
	protected class JLabelTimerTask extends TimerTask{
            
            public void run() {
                Date nowDate = GetServerTime.getNowTime();
                if (endDate.getTime()-nowDate.getTime()<=0){
                    time = "本场考试已经结束";
                }
                else{
                    long day = (endDate.getTime()-nowDate.getTime())/(1000 * 86400);
                    long hour = ((endDate.getTime()-nowDate.getTime())-day*(1000 * 86400))/(1000*3600);
                    long m=((endDate.getTime()-nowDate.getTime())-day*(1000 * 86400))/1000/60%60;//分
                    long s=((endDate.getTime()-nowDate.getTime())-day*(1000 * 86400))/1000%60;//秒
                    time = "距结束："+day+"天  "+hour+":"+m+":"+s;
                }
                JL_display.setText(time);
            }
	}
}

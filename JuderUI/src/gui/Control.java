/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import common.Config;
import data.JudgeFromQueue;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

import data.MainForNet;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.text.Element;
import javax.xml.namespace.QName;
import myjdom.model.Solution;
import web.Webservice;

/**
 *
 * @author Administrator
 */
public class Control {

    private static String url = null;
    private static QName qname = null;
    private static MainFrame mainFrame = null;
    private static MainForNet mainNet = null;
    private static JudgeFromQueue judgeFromQueue = null;
    public static int runflag = 0; //线程运行状态。0,代表线程关闭状态,1,线程运行状态。
    private static Webservice webService = null;
    public static Queue<Solution> queue = null;
    public static boolean[] threadCountsManager; //线程数目管理
    public static boolean[] threadState;//线程状态

    public static MainFrame getMainFrame() {
        return mainFrame;
    }

    public static void setMainFrame(MainFrame mainFrame) {
        Control.mainFrame = mainFrame;
    }

    //定义线程状态开始
    public static void setRunStatus() {
        runflag = 1;
    }

    //初始化数组状态
    public static void setStateArray() {

        //false 开始关闭线程循环
        threadCountsManager = new boolean[5];
        threadCountsManager[0] = false;
        threadCountsManager[1] = false;
        threadCountsManager[2] = false;
        threadCountsManager[3] = false;
        threadCountsManager[4] = false;

        //false 代表线程关闭成功
        threadState = new boolean[5];
        threadState[0] = false;
        threadState[1] = false;
        threadState[2] = false;
        threadState[3] = false;
        threadState[4] = false;
    }

    //线程实际管理
    public static boolean managerThreadCounts(String s) {
        for (int i = 1; i <= 4; i++) {
            int a = Integer.parseInt(s);
            //原来停止的现在要启动
            if (i <= a) {
                    //启动线程
                    if (threadCountsManager[i] == false) {
                        threadCountsManager[i] = true;
                        threadState[i] = true;
                        mainFrame.threadManagerTabb.setTitleAt(i, "线程正在运行");
                        judgeFromQueue = new JudgeFromQueue(i);
                        judgeFromQueue.start();
                    }                
            } else //原来运行的现在要停止
            if (threadCountsManager[i] == true) {
                threadCountsManager[i] = false;
                mainFrame.threadManagerTabb.setTitleAt(i, "线程正在停止");
                mainFrame.button_StartThread.setEnabled(false);
            }
        }
        return true;
    }

    //操作mainframe
    public static void setTabbStopTitle(int n) {
        mainFrame.threadManagerTabb.setTitleAt(n, "线程[未运行]");
    }

    //设置停止文本
    public static void setStoptxt() {
        runflag = 0;  //设置线程状态关闭
//        mainFrame.buttonCompilersConfig.setEnabled(true);//恢复编译器配置按钮
//        mainFrame.buttonCompilersConfig1.setEnabled(true);
         mainFrame.jButton2.setEnabled(true);//设置编辑配置文件
        mainFrame.jLabel14.setText("已关闭 ");//更新UI状态
        
    }

    public static void setStartThreadButtontEnable() {
        mainFrame.button_StartThread.setEnabled(true);
    }

    public static void setGuiQueueSize(String s) {
        mainFrame.jLabel2.setText(s);
    }

    //
    public static JEditorPane getJudgeInfoEditorPane(int a) {
        switch (a) {
            case 1:
                return mainFrame.textJudgeInfo1;
            default:
                return mainFrame.textJudgeInfo0;
        }
    }

    public static JEditorPane getExceptionEditorPane(int a) {
        switch (a) {
            case 1:
                return mainFrame.textExceptionInfo1;
            default:
                return mainFrame.textExceptionInfo0;
        }
    }

    public static boolean stopJudgerForNet() {
        int j = 1;
        try {
            mainFrame.buttonStop.setEnabled(false);
            mainFrame.jLabel14.setText(" - -");
            // String s=mainFrame.jComboBox1.getSelectedItem().toString();
            int counts = 1;
            threadCountsManager[0] = false; //关闭获取test线程

            //关闭裁判线程
            for (; j <= 1; j++) {
                if (threadCountsManager[j] == true) {
                    threadCountsManager[j] = false;
                    mainFrame.threadManagerTabb.setTitleAt(j, "线程正在停止");
                }
            }
            return true;
        } catch (Exception e) {
            Control.addExceptionInfo(j, e.toString());
            return false;
        }
    }

    @SuppressWarnings("empty-statement")
    public static boolean startJudgerForNet(String ip, int port) {

        queue = new LinkedList<>();

        // mainFrame.jLabel2.setText(""+queue.size());
        // mainNet = new MainForNet(ip, port);
        mainNet = new MainForNet();//获取问题到队列
        threadCountsManager[0] = true;
        mainNet.start()
;
        String a = mainFrame.jComboBox1.getSelectedItem().toString();
        return managerThreadCounts(a);

    }

    public static void addJudgeInfo(int a, String info) {
        //信息框中信息超过2000行自动清洗屏幕
        Element map = getJudgeInfoEditorPane(a).getDocument().getDefaultRootElement();
        if (map.getElementCount() == 2000) {
            clearInfo(a);
        }
        int pos = 1;//光标位置标记
        String preInfo = getJudgeInfoEditorPane(a).getText();
        preInfo = getDetailTime() + info + "\n" + preInfo;
        //使焦点一直确定在第一行的最新信息     
        getJudgeInfoEditorPane(a).setText(preInfo);
        getJudgeInfoEditorPane(a).setSelectionStart(pos);
        getJudgeInfoEditorPane(a).setSelectionEnd(pos);
    }

    public static void clearInfo(int a) {
            getJudgeInfoEditorPane(a).setText("");
            getExceptionEditorPane(a).setText("");
    }

    public static void addExceptionInfo(int a, String info) {
        //信息框中信息超过2000行自动清洗屏幕
        Element map = getExceptionEditorPane(a).getDocument().getDefaultRootElement();
        if (map.getElementCount() == 2000) {
            clearInfo(a);
        }
        int pos = 1;//光标位置标记
        String preInfo = getExceptionEditorPane(a).getText();
        preInfo = getDetailTime() + preInfo + "\n" + info;
        getExceptionEditorPane(a).setText(preInfo);
        //使焦点确定在第一行的最新信息
        getExceptionEditorPane(a).setSelectionStart(pos);
        getExceptionEditorPane(a).setSelectionEnd(pos);
    }

    public static String getChooseDirectory() {
        return getChooseDirectory("a");
    }

    public static String getChooseDirectory(String lan) {

        try {
            String dirName;
            while (true) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                fileChooser.showDialog(new JLabel(), "保存");
                File file = fileChooser.getSelectedFile();
                dirName = file.toString();
                if (lan.equals("c") && dirName.contains(" ")) {
                    JOptionPane.showConfirmDialog(fileChooser,
                            "MinGW璺緞涓嶅緱鍖呭惈绌烘牸锛岃閲嶆柊閫夋嫨", "娴嬭瘯缁撴灉", //ToDo
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.INFORMATION_MESSAGE
                    );
                } else {
                    break;
                }
            }
            return dirName;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

    }

    private static String getDetailTime() {
        Calendar c = Calendar.getInstance();
        String hour = String.valueOf(c.get(Calendar.HOUR_OF_DAY));
        if (hour.length() < 2) {
            hour = "0" + hour;
        }
        String minute = String.valueOf(c.get(Calendar.MINUTE));
        if (minute.length() < 2) {
            minute = "0" + minute;
        }
        String second = String.valueOf(c.get(Calendar.SECOND));
        if (second.length() < 2) {
            second = "0" + second;
        }
        String year = String.valueOf(c.get(Calendar.YEAR));
        String month = String.valueOf(c.get(Calendar.MONTH) + 1);
        String day = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
        String logtime = year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
        return logtime;
    }

    /**
     * @return the webService
     */
    public static Webservice getWebService() {
        return webService;
    }

    /**
     * @param aWebService the webService to set
     */
    public static void setWebService(Webservice aWebService) {
        webService = aWebService;
    }

    /**
     * @return the url
     */
    public static String getUrl() {
        return url;
    }

    /**
     * @param aUrl the url to set
     */
    public static void setUrl(String aUrl) {
        url = aUrl;
    }

    /**
     * @return the qname
     */
    public static QName getQname() {
        return qname;
    }

    /**
     * @param aQname the qname to set
     */
    public static void setQname(QName aQname) {
        qname = aQname;
    }

}

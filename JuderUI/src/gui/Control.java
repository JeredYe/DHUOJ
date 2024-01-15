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
    public static int runflag = 0; //�߳�����״̬��0,�����̹߳ر�״̬,1,�߳�����״̬��
    private static Webservice webService = null;
    public static Queue<Solution> queue = null;
    public static boolean[] threadCountsManager; //�߳���Ŀ����
    public static boolean[] threadState;//�߳�״̬

    public static MainFrame getMainFrame() {
        return mainFrame;
    }

    public static void setMainFrame(MainFrame mainFrame) {
        Control.mainFrame = mainFrame;
    }

    //�����߳�״̬��ʼ
    public static void setRunStatus() {
        runflag = 1;
    }

    //��ʼ������״̬
    public static void setStateArray() {

        //false ��ʼ�ر��߳�ѭ��
        threadCountsManager = new boolean[5];
        threadCountsManager[0] = false;
        threadCountsManager[1] = false;
        threadCountsManager[2] = false;
        threadCountsManager[3] = false;
        threadCountsManager[4] = false;

        //false �����̹߳رճɹ�
        threadState = new boolean[5];
        threadState[0] = false;
        threadState[1] = false;
        threadState[2] = false;
        threadState[3] = false;
        threadState[4] = false;
    }

    //�߳�ʵ�ʹ���
    public static boolean managerThreadCounts(String s) {
        for (int i = 1; i <= 4; i++) {
            int a = Integer.parseInt(s);
            //ԭ��ֹͣ������Ҫ����
            if (i <= a) {
                    //�����߳�
                    if (threadCountsManager[i] == false) {
                        threadCountsManager[i] = true;
                        threadState[i] = true;
                        mainFrame.threadManagerTabb.setTitleAt(i, "�߳���������");
                        judgeFromQueue = new JudgeFromQueue(i);
                        judgeFromQueue.start();
                    }                
            } else //ԭ�����е�����Ҫֹͣ
            if (threadCountsManager[i] == true) {
                threadCountsManager[i] = false;
                mainFrame.threadManagerTabb.setTitleAt(i, "�߳�����ֹͣ");
                mainFrame.button_StartThread.setEnabled(false);
            }
        }
        return true;
    }

    //����mainframe
    public static void setTabbStopTitle(int n) {
        mainFrame.threadManagerTabb.setTitleAt(n, "�߳�[δ����]");
    }

    //����ֹͣ�ı�
    public static void setStoptxt() {
        runflag = 0;  //�����߳�״̬�ر�
//        mainFrame.buttonCompilersConfig.setEnabled(true);//�ָ����������ð�ť
//        mainFrame.buttonCompilersConfig1.setEnabled(true);
         mainFrame.jButton2.setEnabled(true);//���ñ༭�����ļ�
        mainFrame.jLabel14.setText("�ѹر� ");//����UI״̬
        
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
            threadCountsManager[0] = false; //�رջ�ȡtest�߳�

            //�رղ����߳�
            for (; j <= 1; j++) {
                if (threadCountsManager[j] == true) {
                    threadCountsManager[j] = false;
                    mainFrame.threadManagerTabb.setTitleAt(j, "�߳�����ֹͣ");
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
        mainNet = new MainForNet();//��ȡ���⵽����
        threadCountsManager[0] = true;
        mainNet.start()
;
        String a = mainFrame.jComboBox1.getSelectedItem().toString();
        return managerThreadCounts(a);

    }

    public static void addJudgeInfo(int a, String info) {
        //��Ϣ������Ϣ����2000���Զ���ϴ��Ļ
        Element map = getJudgeInfoEditorPane(a).getDocument().getDefaultRootElement();
        if (map.getElementCount() == 2000) {
            clearInfo(a);
        }
        int pos = 1;//���λ�ñ��
        String preInfo = getJudgeInfoEditorPane(a).getText();
        preInfo = getDetailTime() + info + "\n" + preInfo;
        //ʹ����һֱȷ���ڵ�һ�е�������Ϣ     
        getJudgeInfoEditorPane(a).setText(preInfo);
        getJudgeInfoEditorPane(a).setSelectionStart(pos);
        getJudgeInfoEditorPane(a).setSelectionEnd(pos);
    }

    public static void clearInfo(int a) {
            getJudgeInfoEditorPane(a).setText("");
            getExceptionEditorPane(a).setText("");
    }

    public static void addExceptionInfo(int a, String info) {
        //��Ϣ������Ϣ����2000���Զ���ϴ��Ļ
        Element map = getExceptionEditorPane(a).getDocument().getDefaultRootElement();
        if (map.getElementCount() == 2000) {
            clearInfo(a);
        }
        int pos = 1;//���λ�ñ��
        String preInfo = getExceptionEditorPane(a).getText();
        preInfo = getDetailTime() + preInfo + "\n" + info;
        getExceptionEditorPane(a).setText(preInfo);
        //ʹ����ȷ���ڵ�һ�е�������Ϣ
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
                fileChooser.showDialog(new JLabel(), "����");
                File file = fileChooser.getSelectedFile();
                dirName = file.toString();
                if (lan.equals("c") && dirName.contains(" ")) {
                    JOptionPane.showConfirmDialog(fileChooser,
                            "MinGW路径不得包含空格，请重新选择", "测试结果", //ToDo
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

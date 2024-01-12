/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import cache.ProblemsCachManager;
import gui.Control;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import main.Answer;
import myjdom.AnswerToXml;
import myjdom.XmlToProblemBean;
import myjdom.XmlToRequest;
import myjdom.model.ProblemBean;
import myjdom.model.Request;
import myjdom.model.ResultBean;
import myjdom.model.Solution;
import myjdom.model.Solutions;
import persistence.oj_beans.ProblemTestCaseBean;
import swingworker.MySwingWorker;
import log.Log;

/**
 *
 * @author 10102
 */
public class JudgeFromQueue extends Thread {

    private boolean stop = false;
    public Queue<Solution> queue = null;
    private Solutions solutions;
    private List<ProblemTestCaseBean> testCaseBeans = null;
    private Map<String, ProblemBean> problemBeanMap = null;
    private Lock lock;
    private int threadNo;

    public JudgeFromQueue(int threadNo) {
        this.threadNo = threadNo;
    }

    @Override
    public void run() {
        while (Control.threadCountsManager[threadNo] || !Control.queue.isEmpty() && Control.threadCountsManager[0] == false) {
            try {
                if (Control.queue.isEmpty()) {
                    Thread.sleep(1000);
                    continue;
                }
                Solution s = null;
                synchronized (Control.queue) {
                    if (!Control.queue.isEmpty()) {
                        System.out.println("poll����");
                        s = Control.queue.poll();
                        System.out.println("poll���");
                        //Swing�����̰߳�ȫ�ģ�invoke�����������ض�run�������첽ִ��
                        EventQueue.invokeLater(() -> {
                            Control.setGuiQueueSize("" + Control.queue.size());
                        });
                    }
                }
                if (s != null) {
                    String problemIdString = s.getProblemId();
                    System.out.println("��ȡpromblem");
                    getWebServiceProblems(problemIdString);
                     System.out.println("��ȡpromblem���");
                    System.out.println("��ʼ����");
                    Judge(s);
                     System.out.println("�������");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                EventQueue.invokeLater(() -> {
                    Control.addExceptionInfo(threadNo, ex.toString());
                });
            }
        }
        Control.threadState[threadNo] = false;
        EventQueue.invokeLater(() -> {
            Control.setTabbStopTitle(threadNo);
            Control.addJudgeInfo(threadNo, "�߳�" + threadNo + "stop success\n");
        });
        //�жϸ�ֹͣ���߳��Ѿ�ֹͣ
        int i = 1;
        boolean runFlag = false;
        synchronized (Control.threadCountsManager) {
            for (; i <= 4; i++) {
                if (Control.threadCountsManager[i] == true) {
                    runFlag = true;
                }
                if (Control.threadCountsManager[i] != Control.threadState[i]) {
                    break;
                }
            }
            if (i > 4 && runFlag == true) {
                //�жϸ�ֹͣ���߳��Ѿ�ֹͣ���һ����߳�������
                EventQueue.invokeLater(() -> {
                    Control.setStartThreadButtontEnable();
                });
            }
        }

        //�ж��Ƿ���ȫֹͣ
        int j = 0;
        synchronized (Control.threadCountsManager) {
            for (; j <= 4; j++) {
                if (Control.threadCountsManager[j] == true || Control.threadState[j] == true) {
                    break;
                }
            }
            if (j > 4) {
                EventQueue.invokeLater(() -> {
                    Control.setStoptxt();
                });
            }
        }
    }

    public void shutdown() {
        this.stop = true;
    }

    //�Ӷ����л�ȡ���벢����
    public void Judge(Solution s) throws Exception {
        String solutionId = s.getSolutionId();
        String problemId = s.getProblemId();
        String language = s.getLangeuage();
        String sourceCode = s.getCode();
        Float timeOut = problemBeanMap.get(problemId).getTimeOut();
        List<ProblemTestCaseBean> testCaseBeanList = problemBeanMap.get(problemId).getTestCaseBeanList();
        MySwingWorker myswingworker = new MySwingWorker(threadNo, solutionId, problemId, language, sourceCode, timeOut, testCaseBeanList, this::changeMessage, this::submitAnswer);
        myswingworker.execute();
        myswingworker.get();
//                      int i =1/0;
    }

    private void getWebServiceProblems(String problemId) throws Exception {
        problemBeanMap = new HashMap<>();
        ProblemBean problemBean = new ProblemBean();
        XmlToProblemBean xtp = new XmlToProblemBean();
        ProblemsCachManager problemsCachManager = ProblemsCachManager
                .getInstance();
        String problem = (String) problemsCachManager
                .getObject("problemId" + problemId);
        if (problem == null) {
            problem = Control.getWebService().getProblem(Integer.parseInt(problemId));
            problemsCachManager.putObject("problemId" + problemId,
                    problem);
        }
//                System.out.println(Integer.parseInt(problemId));

//                System.out.println(xml);
        xtp.readXmlString(problem);
        problemBean = xtp.convertXML();
        Control.addJudgeInfo(threadNo, "get problemId: " + problemId);
        problemBeanMap.put(problemId, problemBean);
    }

    //
    public void changeMessage(String message) {
        Control.addJudgeInfo(threadNo, message);
        Log.writeInfo(threadNo + ":" + message);
    }
    //

    public void submitAnswer(Answer answer) {
        System.out.println("�ύ���");
        String xml = null;
        try {
            ResultBean rb = new ResultBean();
            rb.setSolutionId(answer.getSolutionId());
            rb.setProblemId(answer.getProblemId());
            rb.setStatus(answer.getStatus());
            rb.setCorrectCaseIds(answer.getCorrectCaseIds());
            rb.setRemark(answer.getRemark());
            rb.setLanguage(answer.getLanguage());
            rb.setSourceCode(answer.getSourceCode());
            List<ProblemTestCaseBean> wrongCase = new ArrayList<>();
            List<String> testCaseList = Arrays.asList(answer.getTestCaseId());
            List<String> correctList = Arrays.asList(answer.getCorrectCaseIds().split(","));
            for (int i = 0; i < testCaseList.size(); i++) {
                if (!correctList.contains(testCaseList.get(i))) {
                    ProblemTestCaseBean testCase = new ProblemTestCaseBean();
                    testCase.setId(Integer.parseInt(testCaseList.get(i)));
                    testCase.setOutput(answer.getUsersOutput()[i]);
                    wrongCase.add(testCase);
                }
            }
            rb.setWrongCase(wrongCase);
            AnswerToXml rtx = new AnswerToXml(rb);
            xml = rtx.convertXML();
            xml=xml.replace("&#", "&amp;#");
            //System.out.println(xml);
            String request = Control.getWebService().updateResult(xml);
            //System.out.println(request);
            XmlToRequest xtr = new XmlToRequest();
            xtr.readXmlString(request);
            Request req = xtr.convertXML();
            System.out.println("�ύ���");
            Control.addJudgeInfo(threadNo, "server result:" + req.getRspMsg());
//            Control.addJudgeInfo("ok");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

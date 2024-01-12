/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import gui.Control;
import java.awt.EventQueue;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import myjdom.XmlToSolution;
import myjdom.model.Solution;
import myjdom.model.Solutions;
import myjdom.model.ProblemBean;
import persistence.oj_beans.ProblemTestCaseBean;
import web.Webservice;

/**
 *
 * @author Administrator
 */
public class MainForNet extends Thread {

    private List<ProblemTestCaseBean> testCaseBeans = null;
    private Map<String, ProblemBean> problemBeanMap = null;
//    private ProblemBean problemBean = null;
//    private static String serverURL;
//    private static IDistributorServer server;
    private int previousSId = -1;
    public Solutions solutions;
    private Lock lock;
//    private String ip = "127.0.0.1";
//    private int port = 5000;

    public MainForNet() {

    }

    @Override
    public void run() {
        while (Control.threadCountsManager[0]) {
            try {
                if (Control.queue.size() > 2) {
                    Thread.sleep(500);
                    continue;
                }
                System.out.println("��ʼ��ȡsolution");
                List<Solution> list = getWebServiceSolutions();
                System.out.println("��ȡslolutin����"+list.size());
//          getServerObject();  
                if (list.size() == 0) {
                    Thread.sleep(3000);
                } else {
                    System.out.println("solutionװ�ض���");
                    listToQueue(list);
                    System.out.println("solutionװ�ض������");
                    //Swing�����̰߳�ȫ�ģ�invoke�����������ض�run�������첽ִ��
                    EventQueue.invokeLater(() -> {
                        Control.setGuiQueueSize("" + Control.queue.size());
                    });
                }
//            judge();
            } catch (Exception ex) {
                ex.printStackTrace();
                EventQueue.invokeLater(() -> {
                    Control.addExceptionInfo(0, ex.toString());
                });
            }
        }
        Control.threadState[0] = false;

        //�ж��Ƿ���ȫֹͣ
        int i = 0;
        synchronized (Control.threadCountsManager) {
            for (; i <= 4; i++) {
                if (Control.threadCountsManager[i] == true || Control.threadState[i] == true) {
                    break;
                }
            }
            if (i > 4) {
                EventQueue.invokeLater(() -> {
                    Control.setStoptxt();
                });

            }
        }
    }

    private List<Solution> getWebServiceSolutions() throws Exception {
        Control.setWebService(new Webservice(new URL(Control.getUrl()), Control.getQname()));
        String xml = Control.getWebService().getSolutions(5);
        XmlToSolution xts = new XmlToSolution();
        xts.readXmlString(xml);

        this.solutions = xts.convertXML();
//        Control.addJudgeInfo("  get "+solutions.getSolution().size()+" solutioins ");
        if (solutions.getSolution().size() != 0) {
            //System.out.println("get " + solutions.getSolution().size() + " solution");
            Control.addJudgeInfo(0, "get " + solutions.getSolution().size() + " solution");
        } else {
            Thread.sleep(1000);
        }
        return solutions.getSolution();
    }
    //��list��Ԫ�ذ�list��˳��Ž�����

    public void listToQueue(List<Solution> s) {
        synchronized (Control.queue) {
            Control.queue.addAll(s);
        }
    }

    public void getSolutionFromWeb() throws Exception {

        List<Solution> s;
        s = this.getWebServiceSolutions();
        this.listToQueue(s);

    }
}

//    private void getServerObject() throws Exception{
//        String serverURL = "jdbc://"+ip+":" + port + "/" + "gddoj";
//        try {
//            server = (IDistributorServer) Naming.lookup(serverURL);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//     private void getTestCaseBeans(SolutionBean sbean){
//        Problem p = ProblemCacheManager.getOneProblem(sbean.getProblemId());
//        ProblemBean problemBean = p.getPbean();
//        testCaseBeans = p.getTestcases();
//        
//     }
//    public void changeMessage(String message){
//        Control.addJudgeInfo(message);
//    }
//    public void submitAnswer(Answer answer){
//        String xml = null;
//        try {
//            ResultBean rb = new ResultBean();
//            rb.setSolutionId(answer.getSolutionId());
//            rb.setProblemId(answer.getProblemId());
//            rb.setStatus(answer.getStatus());
//            rb.setCorrectCaseIds(answer.getCorrectCaseIds());
//            rb.setRemark(answer.getRemark());
//            rb.setLanguage(answer.getLanguage());
//            rb.setSourceCode(answer.getSourceCode());
//            List<ProblemTestCaseBean> wrongCase = new ArrayList<>();
//            List<String> testCaseList = Arrays.asList(answer.getTestCaseId());
//            List<String> correctList = Arrays.asList(answer.getCorrectCaseIds().split(","));
//            for(int i=0;i<testCaseList.size();i++){
//                if(!correctList.contains(testCaseList.get(i))){
//                    ProblemTestCaseBean testCase = new ProblemTestCaseBean();
//                    testCase.setId(Integer.parseInt(testCaseList.get(i)));
//                    testCase.setOutput(answer.getUsersOutput()[i]);
//                    wrongCase.add(testCase);
//                }
//            }
//            rb.setWrongCase(wrongCase);
//            AnswerToXml rtx = new AnswerToXml(rb);
//            xml = rtx.convertXML();
//            System.out.println(xml);
//            String request = Control.getWebService().updateResult(xml);
//            System.out.println(request);
//            XmlToRequest xtr = new XmlToRequest();
//            xtr.readXmlString(request);
//            Request req = xtr.convertXML();
//            Control.addJudgeInfo("server result:"+req.getRspMsg());
////            Control.addJudgeInfo("ok");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        
//    }
//    public void judge() {
//        while (true) {
//             Integer sid = null;
//            try {
//               
//                List<Solution> list = solutions.getSolution();
//                for(Solution s:list){
//                    String problemId = s.getProblemId();
//                    
//                    String language = s.getLangeuage();
//                    String sourceCode = s.getCode();
//                    
//   
//                }
//                sid = server.getOneSolutionId();
//                
//                new Process().Judge(langauge, sourceCode, timeOut, testCaseBeans);
//                List<SolutionBean> slist=SolutionDAO.findMore("id=", sid, 1);
//                if (slist.size() != 0) {
//                    SolutionBean sbean = slist.get(0);
//                    if ((sbean.getId() < previousSId) || !(sbean.getStatus().equals(Const.STATUS[Const.QUEUE]))) {
//                        Control.addExceptionInfo("previous SolutionId=" + previousSId + "---this SolutionId" + sid);
//                        Control.addExceptionInfo("thisSId < previousSId? " + (sbean.getId() < previousSId));
//                        Control.addExceptionInfo("sbean.status=" + sbean.getStatus());
//                        Control.addExceptionInfo("this status!=QUEUE? " + (!(sbean.getStatus().equals(Const.STATUS[Const.QUEUE]))));
//                        continue;
//                    }
//                    previousSId = sbean.getId();
//                    getTestCaseBeans(sbean);
//                    new Process().Judge(sbean,testCaseBeans,problemBean);
//                } else {
//                    TimeTool.sleep(2000);
//                }
//                if (this.stop) {
//                    break;
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//                Control.addExceptionInfo("SID="+sid+" "+e.toString());
//            }
//        }
//    }


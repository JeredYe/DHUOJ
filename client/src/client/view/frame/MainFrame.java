package client.view.frame;

import client.model.Exam;
import client.model.ExamProblem;
import client.model.Problem;
import client.model.Problemlist;
import client.model.StudentExamDetail;
import client.view.other.ColorChange;
import client.service.CompareStatus;
import client.view.panel.CodePanel;
import client.util.Control;
import client.service.DoBackground;
import client.service.DownSwingWorker;
import client.util.Config;
import client.view.panel.AnswerTablePanel;
import client.view.panel.PaperPanel;
import client.view.panel.RankPanel;
import client.view.panel.TimePanel;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import client.io.xml.Compute_xml;
import client.io.xml.ExamProblem_io;
import client.io.xml.Problem_io;
import client.io.xml.SolutionCode;
import client.io.xml.StudentExamDetail_io;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import log.Log;
import share.gui.NewCompileSetting;

public class MainFrame extends JFrame implements ColorChange {

    private String examId;
    private String endtime;
    private JTabbedPane TP_Main;

    private JButton JB_return;
    private JButton UpdateProblem;
    private JButton UpdateStatus;
    private JButton UpdateExam;
    private JButton UpdateNowProblem;

    private JPanel JPT_Work;
    private JPanel JPT_Result;
    private JPanel JPT_Submit;
    private RankPanel JPT_Rank;
    private JPanel JP_Paper;
    private JPanel JP_Code;

    private JSplitPane JSP_PC;
    private JLabel jLabel1;
    private JToolBar jToolBarAll;
    private JToolBar jToolBarProblem;
    private PaperPanel problem;
    private CodePanel codepanel;
    private JScrollPane JSP_Pane;
    private TimePanel TP_time;

    private JTable JT_problem;
    private List<Problemlist> problemlist;
    private List<Problem> probleml;
    private int now_problem_number;
    private AnswerTablePanel answer;
    private Exam exam;
    private String NowProblem = null;

    private JMenuBar menubar;
    private JMenu freshMenu;
    private JMenu systemMenu;
    private JMenu backMenu;
    private JPanel MenuPanelRight;
    private JMenuBar MenuPanel;
    private JPanel titlePanel;
    private JLabel title;
    private JPanel NorthPanel;
    private JLabel jl_south;

//    private JMenu JM_CompileInfo;
//    private JMenuItem cCPlus_CompileInfo;
//    private JMenuItem java_CompileInfo;
    private JMenuItem JM_return;
    private JMenuItem JM_UpdateProblem;
    private JMenuItem JM_UpdateStatus;
    private JMenuItem JM_UpdateExam;
    private JMenuItem JM_Update;
    private JMenuItem JM_Exit;
    private JLabel jl;
    private List<StudentExamDetail> oldStatus;
    private JPanel bottom;
    private JProgressBar jpb;
    private JLabel jpb_message;
    private Map<String, ExamProblem> examProblemMap;

    public MainFrame(Exam exam, CompareStatus com) {
//        this.probleml = pl;
        Control.setExamId(exam.getId());
        Control.setExam(exam);
        this.exam = exam;
        String[] arrayStr = new String[]{};
        arrayStr = exam.getLanguage().split(",");
        List<String> languages = java.util.Arrays.asList(arrayStr);
        Control.setLanguages(languages);
        this.examId = exam.getId();
        this.endtime = exam.getEndtime();
        this.problem = new PaperPanel();
        this.answer = new AnswerTablePanel();
        this.codepanel = new CodePanel(answer);
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.JP_Code.add(this.problem, "Center");
        this.JPT_Result.add(this.answer);
        this.JPT_Submit.add(this.codepanel);
        this.getPorblemStandard(exam.getId());
        Control.setMainFrame(this);
        Control.setCode(this.codepanel);
        Control.setAns(this.answer);

        if (com != null) {
            com.doPrompt();
        }
//            //this.JTP_Code.setTabComponentAt(i, new ButtonTabComponent(this.JTP_Code));
        
        if(!problemlist.isEmpty()){ 
            this.JT_problem.setRowSelectionInterval(0, 0);
            chooseProblem();
        }
    }

    public Exam getExam() {
        return this.exam;
    }

    private void initComponents() {
        this.jpb_message = new JLabel();
        Control.setJpb_message(this.jpb_message);
        this.bottom = new JPanel();
        this.jpb = new JProgressBar();
        Control.setJpb(this.jpb);
        this.TP_Main = new JTabbedPane();
        this.JPT_Result = new JPanel();
        this.JPT_Submit = new JPanel();
        
//        this.cCPlus_CompileInfo = new JMenuItem("C/C++������");
//        this.java_CompileInfo = new JMenuItem("Java������");
        
        this.JB_return = new JButton();
        //this.JCB_Model = new JComboBox();
        this.UpdateProblem = new JButton();
        this.UpdateStatus = new JButton();
        this.UpdateExam = new JButton();
        this.UpdateNowProblem = new JButton();
        this.JPT_Work = new JPanel();

        this.JP_Paper = new JPanel();
        this.JP_Code = new JPanel();
        this.JSP_PC = new JSplitPane();

        this.jLabel1 = new JLabel();
        this.jToolBarAll = new JToolBar();
        this.jToolBarProblem = new JToolBar();
        this.TP_time = new TimePanel(endtime);
        this.menubar = new JMenuBar();

        this.freshMenu = new JMenu("ͬ��");
        this.systemMenu = new JMenu("ϵͳ");
//        this.JM_CompileInfo = new JMenu("����������");

        this.JM_return = new JMenuItem("���ؿ����б�");
        this.JM_UpdateProblem = new JMenuItem("����ȫ����Ŀ");
        this.JM_UpdateStatus = new JMenuItem("������Ŀ״̬");
        this.JM_UpdateExam = new JMenuItem("���¿�����Ϣ");
        this.JM_Exit = new JMenuItem("�˳�");
        this.JM_Update = new JMenuItem("");
        this.jl = new JLabel("                  ");
        this.MenuPanelRight = new JPanel();
        this.MenuPanel = new JMenuBar();
        this.add(this.bottom, BorderLayout.SOUTH);
        this.bottom.setLayout(new FlowLayout(FlowLayout.RIGHT));
        this.bottom.add(this.jpb_message);
        this.bottom.add(this.jpb);
        this.MenuPanel.setLayout(new BorderLayout());
        this.MenuPanelRight.setLayout(new FlowLayout(FlowLayout.RIGHT));
        this.titlePanel = new JPanel();
        this.NorthPanel = new JPanel();
        this.jl_south = new JLabel();
        this.NorthPanel.setLayout(new BorderLayout());
        this.titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.title = new JLabel(exam.getName());
        this.title.setFont(new java.awt.Font("Dialog", 1, 20));
        this.titlePanel.add(this.title);
        this.TP_time.setPreferredSize(new Dimension(150, 20));
        this.jl_south.setPreferredSize(new Dimension(150, 20));
        this.NorthPanel.add(this.titlePanel, BorderLayout.CENTER);
        this.NorthPanel.add(this.TP_time, BorderLayout.EAST);
        this.NorthPanel.add(this.jl_south, BorderLayout.WEST);

//        JM_CompileInfo.add(cCPlus_CompileInfo);
//        cCPlus_CompileInfo.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                NewCompileSetting config = new NewCompileSetting("c", MainFrame.this, true);
//                config.setVisible(true);
//            }
//        });
//        JM_CompileInfo.add(java_CompileInfo);
//        java_CompileInfo.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                NewCompileSetting config = new NewCompileSetting("java", MainFrame.this, true);
//                config.setVisible(true);
//            }
//        });
        
        freshMenu.add(JM_UpdateProblem);
        freshMenu.add(JM_UpdateStatus);
        freshMenu.add(JM_UpdateExam);
        //freshMenu.add(JM_return);
       // systemMenu.add(JM_CompileInfo);
        
        systemMenu.add(JM_Exit);
        menubar.setLayout(new FlowLayout(FlowLayout.LEFT));
        MenuPanel.add(this.menubar, BorderLayout.WEST);
        MenuPanel.add(this.MenuPanelRight, BorderLayout.EAST);
        menubar.add(systemMenu);
        menubar.add(freshMenu);
        //menubar.add(this.jl);
        //MenuPanelRight.add(this.TP_time);
        MenuPanelRight.add(this.JB_return);

        oldStatus = new StudentExamDetail_io(examId).getstudentExamDetaillist();

        this.setVisible(true);
        //setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("�ͻ���");
//        if (LoginFrame.getLogin() == true) {
//            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
//        } else {
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                //super.windowClosing(e);  
                //���붯��  
                dispose();
                System.exit(0);
            }
        });
        // }

        this.JSP_PC.setDividerLocation(320);
        this.JSP_PC.setDividerSize(10);
        this.JSP_PC.setOneTouchExpandable(true);
        this.UpdateExam.setText("���¿�����Ϣ");
        this.JM_UpdateExam.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Control.setPrompt(true);
                    new ProgressBarFrame(exam,DownSwingWorker.EXAM);
                    new DoBackground().compareUpdateTime();
                    setProblemlist(true);
                    setRank();
                } catch (Exception ex) {
                    System.err.println(ex.getMessage());
                    Log.writeExceptionLog(ex.getMessage());
                }
            }
        });
        this.UpdateStatus.setText("����״̬");
        this.JM_UpdateStatus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Control.setPrompt(true);
                    List<StudentExamDetail> oldStatus = new StudentExamDetail_io(examId).getstudentExamDetaillist();
                    new ProgressBarFrame(exam, DownSwingWorker.STATUS);
                    List<StudentExamDetail> newStatus = new StudentExamDetail_io(examId).getstudentExamDetaillist();
                    CompareStatus comp = new CompareStatus();
                    comp.compare(oldStatus, newStatus, problemlist, examId);
                    setProblemlist(true);
                    comp.doPrompt();
                } catch (Exception ex) {
                    Log.writeExceptionLog(ex.getMessage());
                    //System.err.println(ex.getMessage());
                }
            }
        });
        this.UpdateProblem.setText("������Ŀ");
        this.JM_UpdateProblem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Control.setPrompt(true);
                    new ProgressBarFrame(exam, DownSwingWorker.PROBLEM);
                    setProblemlist(true);
                    setRank();
                } catch (Exception ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        if (LoginFrame.getLogin() == false) {
            this.JM_UpdateExam.setEnabled(false);
            this.JM_UpdateProblem.setEnabled(false);
            this.JM_UpdateStatus.setEnabled(false);
        }

//        this.JM_CompileInfo.addActionListener(new java.awt.event.ActionListener(){
//            public void actionPerformed(ActionEvent evt) {
//                      // NewCompileSetting config = new NewCompileSetting(MainFrame.this, true);
//                      // config.setVisible(true);
//            }
//        });
        
        this.JB_return.setText("���ؿ����б�");
        this.JB_return.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                JB_ReturnActionPerformed(evt);
            }
        });
        this.JM_Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                System.exit(1);
            }
        });
        this.jToolBarAll.setFloatable(false);
        this.setJMenuBar(MenuPanel);
        getContentPane().add(this.NorthPanel, "North");
        this.JPT_Work.setLayout(new java.awt.BorderLayout());
        if ((LoginFrame.getLogin() == false)) {
            this.UpdateNowProblem.setEnabled(false);
        }
        this.UpdateNowProblem.setText("���±�������");
        this.UpdateNowProblem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (NowProblem == null) {
                    JOptionPane.showMessageDialog(null, "��ѡ����Ŀ��");
                } else {
                    Control.setPrompt(true);
                    new ProgressBarFrame(exam, NowProblem, DownSwingWorker.NOWPROBLEM);
                }

            }
        });
        this.JP_Paper.setLayout(new GridLayout(10, 1));
        this.JP_Code.setLayout(new java.awt.BorderLayout());
        this.jToolBarProblem.setFloatable(false);
        this.JP_Code.add(this.jToolBarProblem, BorderLayout.NORTH);
        this.jToolBarProblem.add(this.UpdateNowProblem);
        this.JT_problem = new JTable();
        setProblemlist(true);
        this.JSP_Pane = new JScrollPane(this.JT_problem);
        this.JPT_Work.add(this.JP_Code, "Center");

        this.TP_Main.addTab("��Ŀ����", JPT_Work);

        this.JPT_Submit.setLayout(new java.awt.BorderLayout());

        this.TP_Main.addTab("�����ύ", JPT_Submit);

        this.JPT_Result.setLayout(new java.awt.BorderLayout());

        this.TP_Main.addTab("�ύ���", JPT_Result);
        if (LoginFrame.getLogin() == true) {
            JPT_Rank = new RankPanel();
            TP_Main.addTab("�ɼ�����", JPT_Rank); //TODO �ɼ�����ҳ��ʱ����         
            setRank();
        }
        this.TP_Main.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (((JTabbedPane) e.getSource()).getSelectedIndex() == 3) {
                    JPT_Rank.updateWeb();
                }
            }
        });

        this.JSP_PC.setLeftComponent(this.JSP_Pane);
        this.JSP_PC.setRightComponent(this.TP_Main);
        getContentPane().add(this.JSP_PC, "Center");

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width - 850) / 2, (screenSize.height - 650) / 2, 850, 650);

    }

    private void setRank() {
        String ip = Config.getURLip();
        String port = Config.getURLport();
        String url = ip + ":" + port;
        if(problemlist.size()>10){
            JPT_Rank.changeHTML("http://" + url + "/oj/user/examScore3.jsp?examId=" + examId + "&fromclient=true&userId="+Control.getUser().getId());
        } else {
            JPT_Rank.changeHTML("http://" + url + "/oj/user/examScore2.jsp?examId=" + examId + "&fromclient=true");
        }
    }

    private void JB_ReturnActionPerformed(ActionEvent evt) {
        dispose();
        new ExamFrame().setVisible(true);
    }

    public void newDialog(JDialog dialog, JPanel p, String title) {
        dialog.setAlwaysOnTop(true);
        dialog.setTitle(title);
        dialog.add(p);
        dialog.pack();
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        java.awt.Dimension dialogSize = dialog.getSize();
        dialog.setLocation((screenSize.width - dialogSize.width) / 2, (screenSize.height - dialogSize.height) / 2);
        dialog.setVisible(true);
    }

    public void collection(int row) {
        this.JT_problem.setRowSelectionInterval(row, row);
    }

    //TODO need to change
    public void setProblemlist(boolean bo) {
        String[] Names = {"���", "����", "�Ѷ�", "����", "״̬"};
        if (bo == true) {
            problemlist = Compute_xml.getProblemlist(examId);
        }
        //probleml = Read_xml.getProblemList(examId);
        DefaultTableModel defaultModel = new DefaultTableModel(Names, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;//����true��ʾ�ܱ༭��false��ʾ���ܱ༭
            }
        };
        List<StudentExamDetail> sedlist = new StudentExamDetail_io(examId).getstudentExamDetaillist();
        HashMap<String, StudentExamDetail> sedhash = new HashMap<String, StudentExamDetail>();
        for (int i = 0; i < sedlist.size(); i++) {
            sedhash.put(sedlist.get(i).getProblemId(), sedlist.get(i));
        }
        for (int i = 0; i < problemlist.size(); i++) {
            SolutionCode sc = new SolutionCode();
            sc.init();
            boolean isFinish = false;
            boolean isCopy = false;
            String f = new String("isnotFinished");
            String c = new String("isnotCopied");
            String backFile = "./xml/"+Control.getPath()+"/"+Control.getExamId()+"-"+problemlist.get(i).getProblemId()+".xml";
            File tmpFile = new File(backFile);
            if (tmpFile.exists() && sc.isFinished(backFile)) {
                isFinish = true;
            }
            if (tmpFile.exists() && sc.isCopied(backFile).equals("true") && !sc.isFinished(backFile)) {
                isCopy = true;
            }
            StudentExamDetail sed = null;
            if (sedhash.containsKey(problemlist.get(i).getProblemId())) {
                sed = sedhash.get(problemlist.get(i).getProblemId());
            }
            if (sed != null) {
                if (sed.getFinished().equals("true")) {
                    isFinish = true;
                }
            }
            Problemlist pl = problemlist.get(i);
            this.ControlColor(pl, isFinish, isCopy);

            int cnt = i + 1;
            String[] r = new String[5];
            r[0] = String.valueOf(cnt);
            r[1] = problemlist.get(i).getTitle();
            r[2] = problemlist.get(i).getDifficulty();
            r[3] = problemlist.get(i).getScore();
            r[4] = problemlist.get(i).getStatus();

            defaultModel.addRow(r);

        }
        this.JT_problem.setModel(defaultModel);

        this.JT_problem.getColumnModel().getColumn(0).setPreferredWidth(50);
        this.JT_problem.getColumnModel().getColumn(1).setPreferredWidth(150);
        this.JT_problem.getColumnModel().getColumn(2).setPreferredWidth(50);
        this.JT_problem.getColumnModel().getColumn(3).setPreferredWidth(50);
        this.JT_problem.getColumnModel().getColumn(4).setPreferredWidth(50);
        this.JT_problem.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//        this.JT_problem.setRowSelectionInterval(0, 0);
        this.JT_problem.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                chooseProblem();
            }
        });
        this.revalidate();

    }

    public Problem getInformation() {
        int row = JT_problem.getSelectedRow();
        Problem pro = new Problem_io(problemlist.get(row).getProblemId()).getproblem();
        return pro;
    }

    public Problem getInformation(String prono) {
        Problem prod = new Problem_io(prono).getproblem();
        return prod;
    }

    public void chooseProblem() {
        int row = JT_problem.getSelectedRow();
        this.NowProblem = problemlist.get(row).getProblemId();
        Problem_io problem_io = new Problem_io(problemlist.get(row).getProblemId());
        ExamProblem_io examproblem_io = new ExamProblem_io(problemlist.get(row).getExamId());
        if (new File(problem_io.getPath()).exists()) {
            Problem prod = problem_io.getproblem();
            ExamProblem epro = null;
            try{epro = examproblem_io.getProblemList().get(row);}
            catch (Exception e){}
            prod.setDeadline(endtime);
            if(prod == null||prod.getUpdateTime().compareTo(problemlist.get(row).getUpdateTime())<0){
                Control.setPrompt(false);
                new ProgressBarFrame(exam, NowProblem, DownSwingWorker.NOWPROBLEM);
            }else if (codepanel.setSample(prod, Integer.parseInt(examId), exam.getSubmitOnlyAC(), row)) {
                problem.setPaper(prod, row,epro);
                now_problem_number = row;
                String routing = "./xml/"+Control.getPath()+"/"+Control.getExamId()+"-"+String.valueOf(this.NowProblem)+".xml";
                SolutionCode tmp = new SolutionCode();
                tmp.init();
                String s = tmp.getSubmitTimes(routing);
                if(!"".equals(problemlist.get(row).getStatus())&&"0".equals(tmp.getSubmitTimes(routing))){
                    answer.refreshResult(this.NowProblem);
                }
            }
        } else {
            Control.setPrompt(true);
            new ProgressBarFrame(exam, NowProblem, DownSwingWorker.NOWPROBLEM);
        }
//        if (prod == null) {
//            
//        } else 
        collection(row);
    }

    public int chooseRow() {
        int row = JT_problem.getSelectedRow();
        return row;
    }

    @Override
    public void change(Problemlist pl, String color) {
        StringBuffer sb1 = new StringBuffer("<html><font color=");
        sb1.append("\"").append(color).append("\">").append(pl.getStatus()).append("</font></html>");
        pl.setStatus(sb1.toString());
        StringBuffer sb2 = new StringBuffer("<html><font color=");
        sb2.append("\"").append(color).append("\">").append(pl.getTitle()).append("</font></html>");
        pl.setTile(sb2.toString());
        StringBuffer sb3 = new StringBuffer("<html><font color=");
        sb3.append("\"").append(color).append("\">").append(pl.getScore()).append("</font></html>");
        pl.setScore(sb3.toString());
        StringBuffer sb4 = new StringBuffer("<html><font color=");
        sb4.append("\"").append(color).append("\">").append(pl.getDifficulty()).append("</font></html>");
        pl.setDifficulty(sb4.toString());

    }

    public void getPorblemStandard(String exam_id) {
        ExamProblem_io io = new ExamProblem_io(exam_id);
        try{
        List<ExamProblem> list = io.getProblemList();
        this.examProblemMap = new HashMap<String, ExamProblem>();
        for (ExamProblem examProblem : list) {
            examProblemMap.put(examProblem.getProblemId(), examProblem);
        }
        }catch(Exception e){
            //����
        }
    }

    public void ControlColor(Problemlist pl, boolean isFinish, boolean isCopy) {
        if (isFinish) {
            change(pl, ColorChange.GRAY);
        } else if (isCopy && !isFinish) {
            change(pl, ColorChange.RED);
        }
    }
}

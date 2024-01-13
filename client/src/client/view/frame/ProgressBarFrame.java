/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.view.frame;

import client.io.xml.StudentExamDetail_io;
import client.model.Exam;
import client.service.DownSwingWorker;
import client.util.Control;
import client.view.other.TimeLabel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.swing.*;

/**
 *
 * @author ytxlo
 */
public class ProgressBarFrame extends JFrame {

    private JLabel jl;
    private JLabel jltitle;
    private JProgressBar progressBar; //������
    private Exam exam;
    private ExamFrame ef;
    private String status;
    
    private JButton JB_Start;
     private JButton JB_contest;
     private JLabel progressbar;//����ʱ
     private JPanel JP_Top;
     private JPanel JP_Panel;
     private JPanel JP_Bottom;
     private JPanel JP_Bottom_Left;
     private JPanel JP_Bottom_Center;
     private JPanel JP_Bottom_Right;
     private JLabel jLabelTitle;
     private JLabel jLabelEmpty;
     private JTextArea JT_Content;
     
     public ProgressBarFrame(Exam exam,ExamFrame ef,String status,String init)
     {
        this.exam = exam;
        this.ef = ef;
        this.status = status;
        InitComponents();
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds((screenSize.width - 500) / 2, (screenSize.height - 467) / 2,500,467);
        this.progressbar = new TimeLabel(exam,ef,exam.getStarttime(),exam.getEndtime(),this.progressbar,exam.getId(),this.jl,this.progressBar,this,status,JB_Start);
     }
     
    public ProgressBarFrame(Exam exam,String NowProblem,String status){
        this.status = status;
        init();
        DownSwingWorker sw = new DownSwingWorker(jl, progressBar,exam, NowProblem, this, status);
        sw.execute();
    }
    public ProgressBarFrame(Exam exam,String status){
        this.exam = exam;
        this.status = status;
        init();
        DownSwingWorker sw = new DownSwingWorker(jl, progressBar, exam, this,status);
        sw.execute();
    }
    public ProgressBarFrame(Exam exam,ExamFrame ef,String status){ 
        this.exam = exam;
        this.ef = ef;
        this.status = status;
        init();
        DownSwingWorker sw = new DownSwingWorker(jl, progressBar, exam, ef, this,status);
        sw.execute();
    } 
    private void init(){
        this.progressBar=new JProgressBar(); 
        this.jl = new JLabel("��������");
        this.jltitle = new JLabel("�������ر�����������");
        this.jl.setPreferredSize(new Dimension(400,50));
        this.jltitle.setPreferredSize(new Dimension(400,50));
        this.progressBar.setStringPainted(true); 
        this.progressBar.setPreferredSize(new Dimension(400, 50));
        this.add(this.jltitle,BorderLayout.NORTH);
        this.add(progressBar, BorderLayout.CENTER);//�ڲ�ָ�����ֹ�����������£�Ĭ��ʹ��BorderLayout�� ����ʹ�ò��ֹ�����������ȷ˵��setLayout(new BorderLayout())  
        this.add(jl,BorderLayout.SOUTH);
        this.setTitle("����"); 
        this.pack(); 
        this.setSize(400,200);
        this.setLocationRelativeTo(null);
        this.setVisible(true); 
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);      
    }
    
    private void InitComponents()
    {
          this.jl = new JLabel("��������");
          jLabelTitle = new JLabel();
          jLabelEmpty = new JLabel();
          JT_Content = new JTextArea();
          JP_Top = new JPanel();
          JP_Panel = new JPanel();
          JP_Bottom = new JPanel();
          JP_Bottom_Left = new JPanel();
          JP_Bottom_Center = new JPanel();
          JP_Bottom_Right = new JPanel();
          this.progressbar = new JLabel();
          JB_Start = new JButton();
          this.JB_contest = new JButton();
          this.progressBar=new JProgressBar(); 
          
          JP_Panel.setLayout(new java.awt.GridLayout(1, 1));
          JP_Top.setLayout(new java.awt.GridLayout(1, 1));
          jLabelEmpty.setText("");
          jLabelTitle.setText("���Խ���");
          jLabelTitle.setHorizontalAlignment(JLabel.CENTER);
          jLabelTitle.setFont(new Font(null, Font.PLAIN, 25));
          JT_Content.setText( ""+"\n"
                             +"      �������ƣ�"+exam.getName()+"\n"
                             +"      �������ݣ�"+exam.getDescription()+"\n"
                             +"      ��ʦID��"+exam.getTeacherId()+"\n"
                             +"      ����ţ�"+exam.getProblemNum());
          JT_Content.setFont(new Font(null, Font.PLAIN, 18));
          JT_Content.setLineWrap(true);
          JT_Content.setWrapStyleWord(true);
          JP_Bottom.setLayout(new java.awt.GridLayout(1, 3));
          JP_Bottom_Left.setLayout(new java.awt.GridLayout(3, 1));
          JP_Bottom_Center.setLayout(new java.awt.GridLayout(3, 1));
          JP_Bottom_Right.setLayout(new java.awt.GridLayout(3, 1));
          JB_Start.setText("��ʼ����");
          JB_Start.setEnabled(false);
          JT_Content.setEnabled(false);
          
          JP_Top.add(jLabelTitle);
          JP_Panel.add(JT_Content);
          JP_Bottom_Left.add(jLabelEmpty);
          JP_Bottom_Left.add(jLabelEmpty);
          JP_Bottom_Left.add(this.jl);
          JP_Bottom_Center.add(this.progressbar);
          JP_Bottom_Center.add(JB_Start);
          JP_Bottom_Center.add(progressBar);
          JP_Bottom_Right.add(jLabelEmpty);
          JP_Bottom_Right.add(jLabelEmpty);
          JP_Bottom_Right.add(jLabelEmpty);
          JP_Bottom.add(JP_Bottom_Left, java.awt.BorderLayout.WEST);
          JP_Bottom.add(JP_Bottom_Center, java.awt.BorderLayout.CENTER);
          JP_Bottom.add(JP_Bottom_Right, java.awt.BorderLayout.EAST);
          progressbar.setHorizontalAlignment(JLabel.CENTER);
          progressbar.setForeground(Color.red);
          progressbar.setBackground(Color.red);
          this.progressBar.setStringPainted(true); 
          this.progressBar.setPreferredSize(new Dimension(80, 20));
          progressBar.setVisible(false);
          this.jl.setVisible(false);
          getContentPane().add(JP_Top, java.awt.BorderLayout.NORTH);
          getContentPane().add(JP_Panel, java.awt.BorderLayout.CENTER);
          getContentPane().add(JP_Bottom, java.awt.BorderLayout.SOUTH);
          setResizable(false);
          JB_Start.addKeyListener(new KeyAdapter() {
             public void keyPressed(KeyEvent e){
                if(e.getKeyCode()==KeyEvent.VK_ENTER)
                    JB_Start.doClick();
            }
        });
        JB_Start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_StartActionPerformed();
            }
        });
        //this.progressbar = new TimeLabel(exam.getStarttime(),exam.getEndtime(),this.progressbar,exam.getId(),JB_Start); 
    }
    private void JB_StartActionPerformed()
    {
        File f = new File("./xml/"+Control.getPath()+"/examproblems_"+exam.getId()+".dat");
            if(!f.exists()){
                JOptionPane.showMessageDialog(null, "��Ŀδ���سɹ���");
            }
            else{
                new MainFrame(exam,null);
                dispose();
            }
    }
}

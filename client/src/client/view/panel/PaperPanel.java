package client.view.panel;

import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
import client.model.Problem;
import client.model.ExamProblem;
import client.util.CreateProblemHtml;
import java.awt.BorderLayout;
import java.io.File;
import javax.swing.*;

public class PaperPanel extends JPanel{

	private JEditorPane JEP_Problem;
	private JScrollPane jScrollPane1;
	private int papernum;
        final JWebBrowser webBrowser;
	public PaperPanel(){
            this.webBrowser = new JWebBrowser();
            initComponents();
	}
        public void initComponents(){
                webBrowser.setBarsVisible(false);
                webBrowser.setButtonBarVisible(false);
                webBrowser.setStatusBarVisible(false);
		this.setLayout(new BorderLayout());
		this.add(webBrowser,BorderLayout.CENTER);
	}
	public void setPaper(Problem pro,int row ,ExamProblem epro) {
		try {
                    pro.setBestBefore(epro.getBestBefore());
                    pro.setScoreCoef(epro.getScoreCoef());
                    String result = this.prpblemView(pro,row);
                    CreateProblemHtml cph = new CreateProblemHtml(result);
                    File file = new File("./problem.html");  
                    String path = file.getAbsolutePath();   
                    webBrowser.navigate(path);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	

        private String prpblemView(Problem pro,int row){
            
            String p1 = "<center><h1 style=\"font-family:verdana;color:blue\">"+String.valueOf(row+1)+"."+pro.getTitle()+"</h1></center>";
//            String p2 = "<center><p>晚于"+pro.getBestBefore()+"提交成绩乘以系数"+pro.getScoreCoef()+"%，截止时间:"+pro.getDeadline()+"</p></center>";
//            if (pro.getBestBefore().equals("")||pro.getScoreCoef().equals("")||pro.getDeadline().equals("")){
                String p2="";
//            }
            String p3 = "<center><p>"+"&nbsp&nbsp时间限制："+pro.getTime_limit()+"&nbsp&nbsp章节:"+pro.getChapterName()+"</p></center>";
            String p4= "<center><p>晚于："+pro.getBestBefore()+"&nbsp&nbsp后提交分数乘系数"+pro.getScoreCoef()+"%</p></center>";
            String p5 = "<center><p>截止时间："+pro.getDeadline()+"</p></center>";
            String str1 = "<h2 style=\"font-family:verdana;color:blue\">问题描述</h2>"+pro.getDescription();
            String str2 = "<h2 style=\"font-family:verdana;color:blue\">输入说明</h2>"+pro.getInputRequirement();
            String str3 = "<h2 style=\"font-family:verdana;color:blue\">输出说明</h2>"+pro.getOutputRequirement();
            String temp = new String(pro.getSample_input());
            int rowN = countN(temp);
            String str4 = "<h2 style=\"font-family:verdana;color:blue\">输入范例</h2>"+"<textarea wrap=\"off\" style=\"font-family:'宋体';width:100%\" rows=\""+String.valueOf(rowN+1)+"\">"+pro.getSample_input()+"</textarea>";
            temp = new String(pro.getSample_output());
            rowN = countN(temp);
            String str5 = "<h2 style=\"font-family:verdana;color:blue\">输出范例</h2>"+"<textarea wrap=\"off\" style=\"font-family:'宋体';width:100%\" rows=\""+String.valueOf(rowN+1)+"\">"+pro.getSample_output()+"</textarea>";
            String result;
            if(pro.getBestBefore().equals("")!=true) result = p1 + p2 + p3 + p4 + p5 + str1 + str2 + str3 + str4 + str5;
            else result = p1 + p2 + p3 + p5 + str1 + str2 + str3 + str4 + str5;
            return result;
        }
        private int countN(String str){
            int count = 1;
            for(int i = 0;i<str.length();i++){
                if (str.charAt(i)=='\n'){
                    count++;
                }
            }
            if (count>20){
                count = 20;
                return count;
            }
            else{
                return count;
            }
        }
}

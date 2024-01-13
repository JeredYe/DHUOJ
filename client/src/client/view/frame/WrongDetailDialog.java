/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.view.frame;

import static client.view.panel.CodePanel.asciiToString;
import static client.view.panel.CodePanel.stringToAscii;
import main.Answer;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author 毛泉
 */
public class WrongDetailDialog extends JFrame {

    JLabel wrongID;
    JLabel stdIn;
    JLabel codeWay;
    JLabel stdOut;
    JLabel yourout;
    JButton OK;
    JScrollPane JSP_StdIn;
    JScrollPane JSP_StdOut;
    JScrollPane JSP_YourOut;
    JTextArea JEP_StdIn;
    JTextArea JEP_StdOut;
    JTextArea JEP_YourOut;
    JComboBox JC_codeway;
    JPanel stdout_top;
    JPanel your_top;
    JPanel info;
    JPanel input;
    JPanel code;
    JPanel putout;
    JPanel youranswer;
    JPanel middle;
    String codeway;
    ButtonGroup codway;

    public WrongDetailDialog(JFrame Owner, Answer answer, int wrongnum, String[] testCaseIn, String[] testCaseOut, String testCaseId) {
        this.wrongID = new JLabel("测试用例ID：" + testCaseId);
        this.stdIn = new JLabel("测试用例输入：");
        this.codeWay = new JLabel("编码：");
        this.stdOut = new JLabel("测试用例正确输出：      ");
        this.yourout = new JLabel("你的用例错误输出：");
        this.JSP_StdIn = new JScrollPane();
        this.JSP_StdOut = new JScrollPane();
        this.JSP_YourOut = new JScrollPane();
        this.JEP_StdIn = new JTextArea(6, 20);
        this.JEP_StdOut = new JTextArea(6, 20);
        this.JEP_YourOut = new JTextArea(6, 20);
        this.JC_codeway = new JComboBox();
        this.codeway = new String();
        this.OK = new JButton();
        this.info = new JPanel();
        this.input = new JPanel();
        this.code = new JPanel();
        this.putout = new JPanel();
        this.youranswer = new JPanel();
        this.codway = new ButtonGroup();
        this.middle = new JPanel();
        this.stdout_top = new JPanel();
        this.your_top = new JPanel();
        this.middle.setLayout(new GridLayout(2, 2));
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds((screenSize.width - 700) / 2, (screenSize.height - 500) / 2, 700, 500);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        this.setLayout(new BorderLayout());

        JSP_StdIn.setViewportView(JEP_StdIn);
        JSP_StdOut.setViewportView(JEP_StdOut);
        JSP_YourOut.setViewportView(JEP_YourOut);

        setTitle("查看测试用例");
        String[] way = {"ASCII码", "文本"};

        this.JC_codeway.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"文本", "ASCII码"}));
        this.JC_codeway.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                WrongDetailDialog.this.JC_codewayActionPerformed(evt);
            }
        });

        info.setLayout(new FlowLayout(FlowLayout.CENTER));
        info.add(wrongID);

        input.setLayout(new BorderLayout());
        input.add(stdIn, BorderLayout.NORTH);
        input.add(JSP_StdIn, BorderLayout.CENTER);

        //code.add(codeWay);
        //code.add(JC_codeway);
        putout.setLayout(new BorderLayout());
        stdout_top.setLayout(new FlowLayout(FlowLayout.LEFT));
        stdout_top.setPreferredSize(new Dimension(350, 50));
        stdout_top.add(stdOut);
        code.add(codeWay);
        addRadioButton();
        stdout_top.add(code);
        putout.add(stdout_top, BorderLayout.NORTH);
        putout.add(JSP_StdOut, BorderLayout.CENTER);

        youranswer.setLayout(new BorderLayout());
        your_top.setLayout(new FlowLayout(FlowLayout.LEFT));
        your_top.setPreferredSize(new Dimension(350, 50));
        your_top.add(yourout);
        youranswer.add(your_top, BorderLayout.NORTH);
        youranswer.add(JSP_YourOut, BorderLayout.CENTER);

        add(info, BorderLayout.NORTH);
        this.middle.add(input);
        this.middle.add(new JPanel());
        this.middle.add(putout);
        this.middle.add(youranswer);
        add(this.middle, BorderLayout.CENTER);

        JEP_StdIn.setText(testCaseIn[wrongnum]);
        JEP_StdOut.setText(testCaseOut[wrongnum]);

        String output = answer.getUsersOutput()[wrongnum];
        StringBuffer replaceResult = new StringBuffer();
        int index = output.indexOf("&#");  //output:"abc&#0;def"
        while (index >= 0) {
            replaceResult.append(output.substring(0, index));//replaceResult:"abc"
            output = output.substring(index + 2);//output:"0;def"

            int indexColon = output.indexOf(";");
            if (indexColon < 0) {
                replaceResult.append("&#");
                break;
            }
            String num = output.substring(0, indexColon);//num:0
            if (num.length() > 3) {
                replaceResult.append("&#" + num + ";");
            } else {
                int number = 0;
                try {
                    number = Integer.parseInt(num);
                    replaceResult.append(String.valueOf((char) (number)));
                } catch (Exception e) {
                    replaceResult.append("&#" + num + ";");
                }
            }

            output = output.substring(indexColon + 1);//output:"def"
            index = output.indexOf("&#");
        }
        replaceResult.append(output);
        //replaceResult.trimToSize(64000);
        output = replaceResult.toString();

        JEP_YourOut.setText(output);
    }

    private void addRadioButton() {
        JRadioButton buttona = new JRadioButton("ASCII码", false);
        JRadioButton buttont = new JRadioButton("文本", true);
        buttona.setActionCommand("ASCII码");
        buttont.setActionCommand("文本");
        codway.add(buttona);
        code.add(buttona);
        codway.add(buttont);
        code.add(buttont);

        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String recent = new String(WrongDetailDialog.this.codeway);
                String eActionCommand = e.getActionCommand();
                //System.out.printf("e.getActionCommand() is %s\n",eActionCommand);
                WrongDetailDialog.this.codeway = eActionCommand;
                if (!(eActionCommand.equals(recent))) {
                    if (eActionCommand.equals("ASCII码")) {
                        WrongDetailDialog.this.JEP_StdOut.setText(stringToAscii(WrongDetailDialog.this.JEP_StdOut.getText()));
                        WrongDetailDialog.this.JEP_YourOut.setText(stringToAscii(WrongDetailDialog.this.JEP_YourOut.getText()));
                    } else {
                        WrongDetailDialog.this.JEP_StdOut.setText(asciiToString(WrongDetailDialog.this.JEP_StdOut.getText()));
                        WrongDetailDialog.this.JEP_YourOut.setText(asciiToString(WrongDetailDialog.this.JEP_YourOut.getText()));
                    }
                }
            }
        };

        buttona.addActionListener(actionListener);
        buttont.addActionListener(actionListener);
    }

    private void JC_codewayActionPerformed(ActionEvent evt) {
        String recent = new String(WrongDetailDialog.this.codeway);
        this.codeway = this.JC_codeway.getSelectedItem().toString();
        //System.out.println(this.codeway);
        if (!(this.codeway.equals(recent))) {
            if (this.codeway.equals("ASCII码")) {
                this.JEP_StdOut.setText(stringToAscii(this.JEP_StdOut.getText()));
                this.JEP_YourOut.setText(stringToAscii(this.JEP_YourOut.getText()));
                recent = "文本";
            } else {
                this.JEP_StdOut.setText(asciiToString(this.JEP_StdOut.getText()));
                this.JEP_YourOut.setText(asciiToString(this.JEP_YourOut.getText()));
                recent = "ASCII码";
            }
        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.view.other;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

/**
 *
 * @author Sxz 2018 4 21 RSyntax库的文本编辑区域
 */
public class CodeTextAreaPanel extends JEditorPane {
       RSyntaxTextArea codeArea = new RSyntaxTextArea(20, 60);

   public    CodeTextAreaPanel(JScrollPane JSP_Code){
        setLayout(new BorderLayout());
        codeArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        codeArea.setCodeFoldingEnabled(true);
        codeArea.setFont(new Font(null, Font.PLAIN, 20));
        RTextScrollPane codePanel = new RTextScrollPane(codeArea);
        codePanel.setBorder(BorderFactory.createEmptyBorder());
        this.add(codePanel);
       }
}

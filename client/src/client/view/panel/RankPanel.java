/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.view.panel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javax.swing.*;

/**
 *
 * @author ytxlo 
 * fix by sea 使用javaFX的WebView代替JWebBrowser
 */
public class RankPanel extends JFXPanel {

    private static WebView webview;
    private JPanel jp;
    private JButton reflash;

    public RankPanel() {
        this.jp = new JPanel();
        this.reflash = new JButton("刷新");
        init();
    }

    public void init() {
        this.setLayout(new BorderLayout());
        //webBrowser.navigate("http://www.baidu.com");
        this.jp.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.jp.add(reflash);
        this.reflash.addActionListener((ActionEvent e) -> {
            updateWeb();
        });
        this.add(jp, BorderLayout.NORTH);
    }

    public void changeHTML(String url) {
        Platform.setImplicitExit(false);
        Platform.runLater(() -> {
            webview = new WebView();
            webview.setMaxSize(10000, 10000);
            WebEngine we = webview.getEngine();
            setScene(new Scene(webview));
            we.load(url);
        });
    }
    public void updateWeb(){
        Platform.runLater(() -> {
                webview.getEngine().reload();
            });
    }
}

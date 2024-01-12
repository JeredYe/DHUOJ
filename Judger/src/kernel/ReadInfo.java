/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kernel;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import resultData.JudgerInfo;

/**
 *
 * @author sxz
 */
public class ReadInfo extends Thread {

    private OutputStream os;
    private String input;
//    private boolean isStop=false;
    public ReadInfo(OutputStream os, String input) {
        
        this.os = os;
        this.input = input;
    }

    @Override
    public void run() {
//        while(!isStop)
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                os));
            bw.write(input);
            bw.flush();
            os.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
//    public void shutDown(){
//     this.isStop=true;
//    }
}

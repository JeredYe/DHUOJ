/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tool;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author Administrator
 */
public class ThreadTool {
    /**
     * ������
     *
     * @param PID ����ID]
     * * @param processName ������
     * @return �ҵ�����true,û�ҵ�����false
     */
    public static boolean findProcess(long PID) {
      
        BufferedReader bufferedReader = null;
        
        //���ݽ���ID���ҽ���
        if (PID > 0) {
            try {
                Process proc = Runtime.getRuntime().exec("tasklist /fi \"PID eq " + PID + " \"");
                //System.err.println("tasklist /fi \"PID eq " + PID + " \"");
                bufferedReader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
                String line = null;

                while ((line = bufferedReader.readLine()) != null) {
                    //System.err.println(line);
                    if (line.contains(".exe")) {
                        return true;
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                return false;
            } finally {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (Exception ex) {
                    }
                }
            }
        }        
        return false;
    }
    
    public static boolean findProcess(String processName) {
        BufferedReader bufferedReader = null;
          //���ݽ�����
        if (processName.trim().length() > 0) {
            try {
                Process proc = Runtime.getRuntime().exec("tasklist /fi \"IMAGENAME eq " + processName+"\"");
                bufferedReader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    if (line.contains(processName)) {
                        return true;
                    }
                }
                return false;
            } catch (Exception ex) {
                ex.printStackTrace();
                return false;
            } finally {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (Exception ex) {
                    }
                }
            }
        }
        return false;
    }
}

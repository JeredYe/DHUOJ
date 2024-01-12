/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kernel;

import MyCache.Shared;
import resultData.RunInfo;
import resultData.CompileInfo;
import common.Config;
import common.Const;
//import gui.Control;
import java.io.InputStream;
import java.io.OutputStream;
import log.Log;
import resultData.Result;
import tool.ThreadTool;
import com.sun.jna.Library;
import com.sun.jna.Native;
import java.lang.reflect.Field;
import resultData.JudgerInfo;

/**
 *
 * @author Administrator
 */
public class ExeCommand {

    //��������ӿڼ��ر������kernal32   
    public interface Kernel32 extends Library {

        public static Kernel32 INSTANCE = (Kernel32) Native.loadLibrary("kernel32", Kernel32.class);

        public long GetProcessId(Long hProcess);
    }

    //��ȡProcess��ӦID
    private long getProcessID(Process process) {

        Field field = null;
        try {
            field = process.getClass().getDeclaredField("handle");
            field.setAccessible(true);
            long PID = Kernel32.INSTANCE.GetProcessId((Long) field.get(process));
            return PID;
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchFieldException | SecurityException ex) {
            Log.writeExceptionLog(ex.getMessage());
            ex.printStackTrace();
            return -1;
        }
    }

    public int exeCompile(String compileCommand,String env) {
        if (Config.DEBUG >= 2) {
            System.out.println("compileCommand:" + compileCommand);
        }
        int result = -1;
        Result.status =0;
        CompileInfo.init();

            //���볬��������Ϊ��ʱ
            result = getJurgeResult(compileCommand,env, "", 10);
            CompileInfo.exitValue = JudgerInfo.exitValue;
            CompileInfo.info = JudgerInfo.info;
            CompileInfo.errorInfo = JudgerInfo.errorInfo;
            CompileInfo.isKilled = JudgerInfo.isKilled;

           if (Result.status == Const.SE)
            {
                CompileInfo.remark = JudgerInfo.remark;
            }
            else if (CompileInfo.isKilled == 1) {
                Result.status = Const.CE;
                CompileInfo.remark = "���볬ʱ,�����ԣ�" + CompileInfo.errorInfo;
            } else if (result != 0) {
                Result.status = Const.CE;
                CompileInfo.remark = CompileInfo.errorInfo;
            } else {
                CompileInfo.remark = "";
            }
            if (Config.DEBUG >= 2) {
                System.out.println("compile info:" + CompileInfo.info);
                System.out.println("compile errinfo:" + CompileInfo.errorInfo);
            }
            return result;

       

    }

    public int exeLink(String linkCommand,String env) {
        if (Config.DEBUG >= 2) {
            System.out.println("linkCommand:" + linkCommand);
        }
        int result = -1;
        Result.status =0;
            result = getJurgeResult(linkCommand,env, "", 0);

            CompileInfo.exitValue = result;
            CompileInfo.info += "\n" + JudgerInfo.info;
            CompileInfo.errorInfo += "\n" + JudgerInfo.errorInfo;

            if (Result.status == Const.SE)
            {
                CompileInfo.remark = JudgerInfo.remark;
            }
            else if (result != 0) {
                Result.status = Const.CE;
                CompileInfo.remark = CompileInfo.errorInfo;
            } else {
                CompileInfo.remark = "";
            }
            if (Config.DEBUG >= 2) {
                System.out.println("link info:" + CompileInfo.info);
                System.out.println("link errinfo:" + CompileInfo.errorInfo);
            }
            return result;

       

    }

    public int exeRun(String runCommand,String env, String input, long timeLimit) {
//        if (Config.DEBUG >= 2) {
//            System.out.println("runCommand" + runCommand);
//        }
        int result = -1;
        Result.status = 0;
        RunInfo.init();
        result = getJurgeResult(runCommand, env, input, timeLimit);
        
        RunInfo.exitValue = JudgerInfo.exitValue;
        RunInfo.info = JudgerInfo.info;
        RunInfo.errorInfo = JudgerInfo.errorInfo;
        RunInfo.isKilled = JudgerInfo.isKilled;

        if (Config.DEBUG >= 2) {
            System.out.println("run info:" + RunInfo.info);
            System.out.println("run errinfo:" + RunInfo.errorInfo);
        }
        boolean flag = false;
        if (flag = ThreadTool.findProcess("WerFault.exe")) {
            try {
                Runtime.getRuntime().exec("taskkill /f /t /im WerFault.exe").waitFor();
            }catch(Exception e) {
                 Log.writeExceptionLog("RunCommand line:1:" + e.getMessage() + "\n" + e.getStackTrace());
            }
        }
        if (Result.status == Const.SE)
        {
            RunInfo.remark = JudgerInfo.remark;
        }
        else if (flag) {
            RunInfo.remark = "����ʱ����" + RunInfo.errorInfo;
            Result.status = Const.RE;
        } else if (RunInfo.isKilled == 1) {
            Result.status = Const.TLE;
            RunInfo.remark = "���г�ʱ" + RunInfo.errorInfo;
        } else if (result != 0) {
            Result.status = Const.RE;
            RunInfo.remark = "����ʱ���󣺳���ֵ��Ϊ��" + RunInfo.errorInfo;
        }
        if (result == 0) {
            RunInfo.remark = "";
        }
        return result;

    }

    public int getJurgeResult(String command,String env, String input, long timeLimit) {
        int result = -1;

        Process p = null;
        InputStream in = null;
        InputStream err = null;
        OutputStream os = null;
        ReadInfo infoRead=null;
        JudgerInfo.init();
        try {
            p = Runtime.getRuntime().exec(command, new String[]{env});
            //��ȡ���̸�ID
            long PID = getProcessID(p);
            Shared.PID = PID;

            in = p.getInputStream();
            err = p.getErrorStream();
            if (input.equals("") == false) // �������Ϊ���򲻴򿪳���������
            {
               
                os = p.getOutputStream();
                infoRead =new ReadInfo(os, input);
                infoRead.start();
//                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
//                        os));
//                bw.write(input);
//                bw.flush();
//                os.close();
            }
            WriteInfo infoWrite = new WriteInfo(in);
            WriteInfo errorInfoWrite = new WriteInfo(err);
            infoWrite.start();
            errorInfoWrite.start();

            if (timeLimit == 0) {
                JudgerInfo.isRun = p.waitFor();
                result = p.exitValue();
            } else {
                LimitTime limit = new LimitTime();
                limit.setProcess(p);
                limit.setTimeLimit(timeLimit);
                Thread thread = new Thread(limit);
                thread.start();
                JudgerInfo.isRun = p.waitFor();
                result = p.exitValue();
            }
            //ȷ��������ȫ��ɱ��
            if (Shared.PID != -1 && ThreadTool.findProcess(Shared.PID)) {
                Runtime.getRuntime().exec("taskkill /f /t /PID " + Shared.PID).waitFor();   
            }
            Shared.PID = -1;
            infoWrite.join();
            errorInfoWrite.join();
            JudgerInfo.exitValue = result;
            JudgerInfo.info = infoWrite.returnInfo();
            JudgerInfo.errorInfo = errorInfoWrite.returnInfo();

            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
            Result.status = Const.SE;
            JudgerInfo.remark = "ϵͳ����,������";
            Log.writeExceptionLog("compileCommand line:3:" + ex.getMessage() + "\n" + ex.getStackTrace());
            return -1;
        } finally {
            try {
                //ȷ��������ȫ��ɱ��
                if (Shared.PID != -1 && ThreadTool.findProcess(Shared.PID)) {
                    Runtime.getRuntime().exec("taskkill /f /t /PID " + Shared.PID).waitFor(); 
                    Shared.PID=-1;
                }
                if (in != null) {
                    in.close();
                }
                if (err != null) {
                    err.close();
                }
                if (os != null) {
                    os.close();
                }
            } catch (Exception e) {
                Log.writeExceptionLog(e.getMessage());
                e.printStackTrace();
            }
        }
    }
}

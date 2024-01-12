package log;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Calendar;

public class Log {

    private static String Logdir = System.getProperty("user.dir") + File.separator + "log";
    private static String time = null; // ʱ�䣬�������ַ���
    private static File exceptionLog = null; // ������Ϣ
    private static File infoLog = null; // �ճ���Ϣ
    private static RandomAccessFile excWriter = null; // �����ļ���д��
    private static RandomAccessFile infoWriter = null; //


    public static void init() {
        File dir = new File(Logdir + "\\exception");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        dir = new File(Logdir + "\\info");
        if (!dir.exists()) {
            dir.mkdirs();
        }

    }

    private static void checkIsNewDay() {
        if (time == null || getNowTime().equals(time) == false) {
            createNewLog();
        }
    }

    private static void createNewLog() {
        if (excWriter != null && infoWriter != null) {
            try {
                excWriter.close();
                infoWriter.close();
            } catch (IOException e) {

                e.printStackTrace();
            }

        }
        time = getNowTime();
        String fileName = time + ".log";

        //
        exceptionLog = new File(Logdir + "\\exception\\" + fileName);
        infoLog = new File(Logdir + "\\info\\" + fileName);
        //
        try {
            excWriter = new RandomAccessFile(exceptionLog, "rw");
            infoWriter = new RandomAccessFile(infoLog, "rw");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void writeExceptionLog(String msg) {
        init();//确保路径完整
        checkIsNewDay();
        long size = exceptionLog.length();
        if (size >= 5 * 1024 * 1024 && excWriter != null) //
        {
            try {
                if (excWriter != null) {
                    excWriter.close();
                    excWriter = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (size < 5 * 1024 * 1024 && excWriter != null) {
            String logtime = getDetailTime();
            msg = logtime + " " + msg + "\r\n";
            try {
                excWriter.seek(size);
                excWriter.write(msg.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public static void writeInfo(String msg) {
        init();//确保路径完整
        checkIsNewDay();
        long size = infoLog.length();
        if (size >= 5 * 1024 * 1024 && infoWriter != null) {
            try {
                if (infoWriter != null) {
                    infoWriter.close();
                    infoWriter = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (size < 5 * 1024 * 1024 && infoWriter != null) {

            String logtime = getDetailTime();
            msg = logtime + " " + msg + "\r\n";
            try {
                infoWriter.seek(size);
                infoWriter.write(msg.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    //����������
    private static String getNowTime() {
        Calendar c = Calendar.getInstance();
        String year = String.valueOf(c.get(Calendar.YEAR));
        String month = String.valueOf(c.get(Calendar.MONTH) + 1);
        if (month.length() < 2) {
            month = "0" + month;
        }
        String day = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
        if (day.length() < 2) {
            day = "0" + day;
        }
        String today = year + month + day;
        return today;

    }

    //����ʱ����
    private static String getDetailTime() {
        Calendar c = Calendar.getInstance();
        String hour = String.valueOf(c.get(Calendar.HOUR_OF_DAY));
        if (hour.length() < 2) {
            hour = "0" + hour;
        }
        String minute = String.valueOf(c.get(Calendar.MINUTE));
        if (minute.length() < 2) {
            minute = "0" + minute;
        }
        String second = String.valueOf(c.get(Calendar.SECOND));
        if (second.length() < 2) {
            second = "0" + second;
        }
        String logtime = hour + ":" + minute + ":" + second;
        return logtime;
    }

}

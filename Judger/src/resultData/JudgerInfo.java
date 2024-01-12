/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resultData;

/**
 *
 * @author sxz
 */
public class JudgerInfo {

   public static String info;
    public static String errorInfo;
    public static int exitValue;
    public static long useTime;
    public static String remark;
    public static int isRun = 1;  
    public static int isKilled = 0;

    public static void init() {
        info = "";
        errorInfo = "";
        exitValue = 1;
        useTime = 0;
        remark = "";
        isKilled = 0;
        isRun = 1;
    }

}

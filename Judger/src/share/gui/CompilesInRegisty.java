/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package share.gui;

import com.ice.jni.registry.Registry;
import com.ice.jni.registry.RegistryKey;
import com.ice.jni.registry.NoSuchValueException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 *
 * @author sxz
 */
public class CompilesInRegisty {

    private final  String compilePath32 = "SOFTWARE\\Wow6432Node\\Microsoft\\Windows\\CurrentVersion\\Uninstall";//32位路径
    private final  String compilePath64 = "SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Uninstall";//64位路径
    private final  String compilePathCurrentUser = "SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Uninstall";//currentuser路径   
    private final String exceptionString ="：当前注册表路径异常";
    //从注册表中查找jdk的路径
    public  List<Compiles> getCompilePath(String complieName) {
        List<Compiles> result = new ArrayList<>();
        //查找32位
        try{
        RegistryKey software32 = Registry.HKEY_LOCAL_MACHINE.openSubKey(compilePath32);
        getInStalled(software32, result, complieName);
        }catch(Exception e){
        log.Log.writeExceptionLog(e.getMessage()+exceptionString+compilePath32);
        //处理异常但是忽略继续检查其他路径
        }
          
        //查找64位
        try {
        RegistryKey software64 = Registry.HKEY_LOCAL_MACHINE.openSubKey(compilePath64);
        getInStalled(software64, result, complieName);
        } catch (Exception e) {
         log.Log.writeExceptionLog(e.getMessage()+exceptionString+compilePath64);
        //处理异常但是忽略继续检查其他路径
        }
        
        //查找currentuser位
        try {
        RegistryKey softwareCurrrentUser = Registry.HKEY_CURRENT_USER.openSubKey(compilePathCurrentUser);
        getInStalled(softwareCurrrentUser, result, complieName);
        } catch (Exception e) {
            log.Log.writeExceptionLog(e.getMessage()+exceptionString+compilePathCurrentUser);
        }    
        return result;
    }

    //具体查找编译器具体内容
    private  void getInStalled(RegistryKey software, List<Compiles> result, String complieName) throws Exception {
        String pathString;
        String versionString;
        RegistryKey subKey=null;
        Enumeration<String> enumeration64 = software.keyElements();
        for (; enumeration64.hasMoreElements();) {
            try {
                //打开每一个加密和不加密文件名对象，查找DisplayName
                subKey = software.openSubKey(enumeration64.nextElement());
                String nameString = subKey.getStringValue("DisplayName");

                //根据DisplayName过滤jdk
                if (nameString.contains(complieName)) {
                    try {
                        pathString = subKey.getStringValue("InstallLocation");
                    } catch (NoSuchValueException e) { //找不到“install”找uninstall
                        String s = subKey.getStringValue("UninstallString");
                        String[] tmp = s.split("\\\\");
                        StringBuilder sb = new StringBuilder();
                        for (int j = 0; j < tmp.length - 1; j++) {
                            sb.append(tmp[j]).append("\\");
                        }
                        pathString = sb.toString();
                    }
                    versionString = subKey.getStringValue("DisplayVersion");
                    Compiles compiles = new Compiles();
                    compiles.setCompileNameString(nameString);
                    compiles.setVersionString(versionString);
                    compiles.setPathString(pathString);
                    result.add(compiles);
                }
                subKey.closeKey();               
            } 
            catch (Exception ex) {                 //找不到的key忽略
            }
        }
    }
}

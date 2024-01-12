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

    private final  String compilePath32 = "SOFTWARE\\Wow6432Node\\Microsoft\\Windows\\CurrentVersion\\Uninstall";//32λ·��
    private final  String compilePath64 = "SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Uninstall";//64λ·��
    private final  String compilePathCurrentUser = "SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Uninstall";//currentuser·��   
    private final String exceptionString ="����ǰע���·���쳣";
    //��ע����в���jdk��·��
    public  List<Compiles> getCompilePath(String complieName) {
        List<Compiles> result = new ArrayList<>();
        //����32λ
        try{
        RegistryKey software32 = Registry.HKEY_LOCAL_MACHINE.openSubKey(compilePath32);
        getInStalled(software32, result, complieName);
        }catch(Exception e){
        log.Log.writeExceptionLog(e.getMessage()+exceptionString+compilePath32);
        //�����쳣���Ǻ��Լ����������·��
        }
          
        //����64λ
        try {
        RegistryKey software64 = Registry.HKEY_LOCAL_MACHINE.openSubKey(compilePath64);
        getInStalled(software64, result, complieName);
        } catch (Exception e) {
         log.Log.writeExceptionLog(e.getMessage()+exceptionString+compilePath64);
        //�����쳣���Ǻ��Լ����������·��
        }
        
        //����currentuserλ
        try {
        RegistryKey softwareCurrrentUser = Registry.HKEY_CURRENT_USER.openSubKey(compilePathCurrentUser);
        getInStalled(softwareCurrrentUser, result, complieName);
        } catch (Exception e) {
            log.Log.writeExceptionLog(e.getMessage()+exceptionString+compilePathCurrentUser);
        }    
        return result;
    }

    //������ұ�������������
    private  void getInStalled(RegistryKey software, List<Compiles> result, String complieName) throws Exception {
        String pathString;
        String versionString;
        RegistryKey subKey=null;
        Enumeration<String> enumeration64 = software.keyElements();
        for (; enumeration64.hasMoreElements();) {
            try {
                //��ÿһ�����ܺͲ������ļ������󣬲���DisplayName
                subKey = software.openSubKey(enumeration64.nextElement());
                String nameString = subKey.getStringValue("DisplayName");

                //����DisplayName����jdk
                if (nameString.contains(complieName)) {
                    try {
                        pathString = subKey.getStringValue("InstallLocation");
                    } catch (NoSuchValueException e) { //�Ҳ�����install����uninstall
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
            catch (Exception ex) {                 //�Ҳ�����key����
            }
        }
    }
}

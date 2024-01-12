package common;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.HashMap;
import javax.swing.JOptionPane;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;

/**
 *
 * @author Jared Ye
 */
public abstract class LangSelector {

    private static String ConfigPath="";
    private static Document Data;
    public static boolean Loaded=false;
    private static XPath xpath = XPathFactory.newInstance().newXPath();
    static {
        try {
            ConfigPath=findConfigFilePath();
            Data = init(ConfigPath);
            Loaded=true;
        } catch (Exception e) {
            //初始化的时候没有读到config.xml
        }
    }
    private static String findConfigFilePath() {
        String currentPath = System.getProperty("user.dir");
        String configFile = "config.xml";

        // Try to find config.xml in the current directory
        File currentDirConfigFile = new File(currentPath, configFile);
        if (currentDirConfigFile.exists()) {
            return currentDirConfigFile.getAbsolutePath();
        }

        // If not found, recursively search in parent directories
        return findConfigInParentDirectory(new File(currentPath), configFile);
    }

    private static String findConfigInParentDirectory(File directory, String configFile) {
        File configFileInParent = new File(directory, configFile);
        if (configFileInParent.exists()) {
            return configFileInParent.getAbsolutePath();
        }

        // If not found and not the root directory, recursively search in parent
        File parentDirectory = directory.getParentFile();
        if (parentDirectory != null) {
            return findConfigInParentDirectory(parentDirectory, configFile);
        }

        // If reached the root directory and still not found, return null
        return null;
    }
    //自动递归向上级获取config.xml的路径
    public static String getConfigPath(){
        return ConfigPath;
    }
    
    public static String getDefaultCompilerName(String languageName){
        //if(languageName.charAt(0)>='a')languageName
        try{
            String exp="/languages/language"+ "[@id='" +languageName+ "']"+"/compiler[1]/@name";
            return (String) xpath.evaluate(exp,Data);
        } catch (XPathExpressionException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    //当没有指定具体的编译器时，自动获取一个默认的编译器。



    public static Document init(String ConfigPath) throws Exception {
        // 创建Document对象
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setValidating(false);
        DocumentBuilder db = dbf.newDocumentBuilder();
        // 创建XPath对象
        LangSelector.ConfigPath =ConfigPath;
        //JOptionPane.showMessageDialog(null,"当前工作目录:"+ConfigPath);
//        LangSelector.ConfigPath=new File(LangSelector.ConfigPath).getAbsolutePath();
        return db.parse(LangSelector.ConfigPath);
    }


    public static String getCompilerPath(String languageName,String compilerName) {
        if(compilerName==null)compilerName=getDefaultCompilerName(languageName);
        try{
            String exp="/languages/language"+ "[@id='" +languageName+ "']"+"//compiler[@name='"+compilerName+"']//path/text()";
            return (String) xpath.evaluate(exp,Data);
        } catch (XPathExpressionException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    public static String getCompileCommand(String languageName,String compilerName){
        if(compilerName==null)compilerName=getDefaultCompilerName(languageName);
        try{
            String exp="/languages/language"+ "[@id='" +languageName+ "']"+"//compiler[@name='"+compilerName+"']//compileCmd/text()";
            return (String) xpath.evaluate(exp,Data);
        } catch (XPathExpressionException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    public static String getLinkCommand(String languageName,String compilerName){
        if(compilerName==null)compilerName=getDefaultCompilerName(languageName);
        try{
            String exp="/languages/language"+ "[@id='" +languageName+ "']"+"//compiler[@name='"+compilerName+"']//linkCmd/text()";
            return (String) xpath.evaluate(exp,Data);
        } catch (XPathExpressionException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    public static String getRunCommand(String languageName,String compilerName){
        if(compilerName==null)compilerName=getDefaultCompilerName(languageName);
        try{
            String exp="/languages/language"+ "[@id='" +languageName+ "']"+"//compiler[@name='"+compilerName+"']//runCmd/text()";
            return (String) xpath.evaluate(exp,Data);
        } catch (XPathExpressionException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    public enum PlaceHolder{

        SourceFile("$sourceFile$"),
        ObjFile("$objFile$"),
        ExeFile("$exeFile$"),
        CompilerPath("$compilerPath$");
        private final String strPlaceHolder;
        private PlaceHolder(String str){
            strPlaceHolder=str;
        }
        public String getStr(){
            return strPlaceHolder;
        }
    }
    public static  String matchPlaceHolder(String src, HashMap<String,String>map){
        for (java.util.Map.Entry<String, String> Entry : map.entrySet()) {
            src = src.replace(Entry.getKey(), Entry.getValue());
            System.out.println(Entry.getKey() + "已替换：" + Entry.getValue());
        }
        return src;
    }


}

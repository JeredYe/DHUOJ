/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kernel;

import MyCache.Shared;
import common.Config;
import resultData.RunInfo;
import common.Const;
import common.LangSelector;
//import gui.Control;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import log.Log;
import resultData.CompileInfo;
import resultData.Result;
import tool.ThreadTool;
import log.Log;

/**
 *
 * @author Administrator
 */
public class Judger {

    String sourceFile;
    ExeCommand exe;
    Boolean isFound;
    String mainClassName; //
    String sourceDir;
    
    public Judger() {
        Config.freshConfig();
        sourceFile = "";
        exe = new ExeCommand();
        isFound = true;
        //checkCompiler();
    }
    
    public Boolean compileFound(){
        return isFound;
    }

//    private void checkCompiler() {
//        //for c
//        File file1 = new File(Config.getCompilerDir("c") + File.separator + "gcc.exe");
//        File file2 = new File(Config.getCompilerDir("c") + File.separator + "gcc.exe");
//        if (!file1.exists() || !file2.exists()) {
//            System.out.println("编译器未找到");
//            isFound = false;
//        }
//    }
    
    public Boolean checkForCompiler(){
        
        //File file1 = new File(Config.getCompilerDir("c",null) + File.separator + "gcc.exe");
        //File file2 = new File(Config.getCompilerDir("c",null) + File.separator + "gcc.exe");
        //if (!file1.exists() || !file2.exists()) {
           // System.out.println("编译器未找到");
          //  isFound = false;
         //   return false;
        //}
        isFound = true;
        return true;
    }

    private void saveSourceCodeFile(String language, String sourceCode) {
        File dir = new File(Config.getSourcePath());
        if (!dir.exists()) {
            dir.mkdirs();
        }
        dir = new File(Config.getTargetPath());
        if (!dir.exists()) {
            dir.mkdirs();
        }
        language = language.toLowerCase();
        try {
            //String s= Config.getTargetPath();
            sourceDir =Config.getSourcePath()+File.separator+"output" ;
            sourceFile =sourceDir+ File.separator + "Main"
                    + Const.getLatterSuffix(language);
            
            if(language.equals("java")){
                mainClassName=findMainClassName(sourceCode);
                sourceFile = sourceDir+ File.separator + findMainClassName(sourceCode) //TODO
                    + Const.getLatterSuffix(language);
                //System.out.println(sourceFile);
            }
            File fl =new File(sourceDir);
            if(!fl.exists()) {
                 fl.mkdirs();
            }          
            BufferedWriter out = new BufferedWriter(new FileWriter(sourceFile));
            out.write(sourceCode);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.writeExceptionLog(e.getMessage());         
        }

    }

    private String linkCommand(String language,String compiler) {
        if(compiler==null){
            compiler=LangSelector.getDefaultCompilerName(language);
        }
        //String language = "c";
        HashMap<String,String>map=new HashMap<>();
        map.put(LangSelector.PlaceHolder.CompilerPath.getStr(),Config.getCompilerDir(language,compiler));
        map.put(LangSelector.PlaceHolder.SourceFile.getStr(),sourceFile);
        if(!compiler.toLowerCase().equals("msvc"))
            map.put(LangSelector.PlaceHolder.ObjFile.getStr(),Config.getTargetPath()+ File.separator+"output"+ File.separator + "Main.o");
        else
            map.put(LangSelector.PlaceHolder.ObjFile.getStr(),Config.getTargetPath()+ File.separator+"output"+ File.separator + "Main.obj");
        map.put(LangSelector.PlaceHolder.ExeFile.getStr(),Config.getTargetPath()+ File.separator+"output"+ File.separator + "Main.exe");
        String linkCommand = LangSelector.matchPlaceHolder(LangSelector.getCompileCommand("C++",compiler), map);
        //String linkCommand = Config.getCompilerDir(language) + File.separator + "g++ " +"\""+ Config.getTargetPath() +File.separator+"output"+File.separator+  "Main"+".o"+"\"" + " -o " +"\""+ Config.getTargetPath()+File.separator+"output"+File.separator + "Main"+".exe"+"\"\n";      
        return linkCommand;
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////TODO
//mingw32-g++.exe -Wall -g  -c E:\Downloads\aaa\aa.cpp -o obj\Debug\aa.o
//mingw32-g++.exe  -o bin\Debug\aaa.exe obj\Debug\aa.o   
    private String compileCommand(String language,String compiler) {////////////TODO
        String compileCommand = "";
        if(compiler==null){
            compiler=LangSelector.getDefaultCompilerName(language);
        }
        language = language.toLowerCase();//todo
        if (language.equals("c")) {
            
            compileCommand += "\"" + Config.getCompilerDir(language,compiler) + File.separator + "gcc\" -c " + "\""+sourceFile +"\""+ " -o " +"\""+Config.getTargetPath()+ File.separator+"output"+ File.separator + "Main"+".o"+"\"\n";
        } else if (language.equals("java")) {
            compileCommand += "\"" + Config.getCompilerDir(language,compiler) + File.separator + "javac\" " + sourceFile; //todo文件路径
        } else if (language.equals("cpp")||language.equals("c++")) {
            //compileCommand +=Config.getCompilerDir(language) + File.separator +LangSelector.getCompileCommand("C++",null);
            
            HashMap<String,String>map=new HashMap<>();
            map.put(LangSelector.PlaceHolder.CompilerPath.getStr(),Config.getCompilerDir(language,compiler));
            map.put(LangSelector.PlaceHolder.SourceFile.getStr(),sourceFile);
            if(!compiler.toLowerCase().equals("msvc"))
                map.put(LangSelector.PlaceHolder.ObjFile.getStr(),Config.getTargetPath()+ File.separator+"output"+ File.separator + "Main.o");
            else
                map.put(LangSelector.PlaceHolder.ObjFile.getStr(),Config.getTargetPath()+ File.separator+"output"+ File.separator + "Main.obj");
            map.put(LangSelector.PlaceHolder.ExeFile.getStr(),Config.getTargetPath()+ File.separator+"output"+ File.separator + "Main.exe");
            compileCommand = LangSelector.matchPlaceHolder(LangSelector.getCompileCommand("C++",compiler), map);
            //compileCommand += "\"" + Config.getCompilerDir(language) + File.separator + "g++\" -Wall -g -c -std=c++1y "//todo:C++14
            //        + "\""+sourceFile+"\"" + " -o " + "\""+Config.getTargetPath()+ File.separator+"output"+ File.separator + "Main"+".o"+"\"\n";
        } else {
            CompileInfo.info = "this programing language is not support!!!";
        }
        return compileCommand;
    }

    private String runCommand(String language,String compiler) {
        String runCommand = "";
        if (language.equals("c")) {
            runCommand +="\""+ Config.getTargetPath()+ File.separator+"output"+File.separator + "Main"+"\"";
            
        } else if (language.equals("java")) {
        runCommand += Config.getCompilerDir(language,compiler) + File.separator + "java"+ " -cp " + Config.getSourcePath()+ File.separator+"output"+File.separator+ " "+mainClassName;  // TODO 文件路径 start            
        //System.err.println(runCommand);
        } else if (language.equals("cpp")||language.equals("c++")) {
            runCommand += "\""+ Config.getTargetPath()+ File.separator+"output"+File.separator  + "Main"+"\"";
        }
//        System.out.println(runCommand);
        return runCommand;
    }

    public int compile(String sourceCode, String language,String compiler) {
        int result = -1;
        //检查语言是否在范围内
        language = language.toLowerCase();
        if (language.equals("c") || language.equals("cpp")|| language.equals("c++") || language.equals("java")) {
            //ok
        } else {
            Result.status = Const.CE;
//            CompileInfo.remark = "语言种类不符合要求";
            return result;
        }
        try {
            if (Shared.PID!=-1&&ThreadTool.findProcess(Shared.PID)) {  //tore0
                Runtime.getRuntime().exec("taskkill /f /t /PID "+Shared.PID).waitFor();
               // System.out.println("进程已杀死");    
//                System.out.println("杀进程"+"taskkill /f /t /PID "+Shared.PID);
            }
        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }

        saveSourceCodeFile(language, sourceCode);

        int repeatTime = 3;
        String compileCom = compileCommand(language,compiler);
        for (int i = 0; i < repeatTime; i++) {
            result = exe.exeCompile(compileCom,"Path="+Config.getCompilerDir(language,compiler));
            if (result == 0) {
                if (language.equals("c") || language.equals("cpp")||language.equals("c++")) {
                    result = exe.exeLink(linkCommand(language,compiler),"Path="+Config.getCompilerDir(language,compiler));
                    if(result==0){
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        return result;
    }

    public int run(String language,String compiler, String input, int timeLimit) {
        language = language.toLowerCase();
        return exe.exeRun(runCommand(language,compiler), "Path="+Config.getCompilerDir(language,compiler), input, timeLimit);
    }

    public boolean check(String stdAns) {
        String output= preProcess(RunInfo.info);
        String stdans = preProcess(stdAns);

        int status = Const.WA;
        if (output.equals(stdans)) {
            status = Const.AC;
        } else {
            String output1 = removeSP(output);
            String ans1 = removeSP(stdans);
            if (output1.equals(ans1)) {
                status = Const.PE;
            }

        }
        Result.status = status;
        return (status == Const.AC);
    }

    private String removeSP(String s) {
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < s.length();i++) {
            if (s.charAt(i) != '\n' && s.charAt(i) != '\t' && s.charAt(i) != '\r' && s.charAt(i) != '\f' && s.charAt(i)!=' '&& s.charAt(i)!='\000') {
                temp.append(s.charAt(i));
            }
        }
     //System.out.println((int)temp.charAt(temp.length()-1));
        return temp.toString();
    }
    
     public static String replaceBlank(String str) {
        String dest = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        char chars[]=dest.toCharArray();
        return dest;
    }

    private String preProcess(String s) {
        StringBuilder temp = new StringBuilder();
        int len = s.length();
        //将\r\n替换为\n
        s = s.replace("\r\n", "\n");
//        for (int i = 0; i < len;) {
//            if ((i < len - 1) && s.charAt(i + 1) == '\n' && s.charAt(i) == '\r') {
//                temp.append('\n');
//                i = i + 2;
//            } else {
//                temp.append(s.charAt(i));
//                i = i + 1;
//            }
//        }
       char[] chars=s.toCharArray();
       int lastIndex=chars.length-1;
       //去除末尾的\n
       for(int i=chars.length-1;i>=0;i--){
           if(chars[i]=='\n'){
               lastIndex=i-1;
           }else{
               break;
           }
       }
        return new String(chars,0,lastIndex+1);
    }

     /*
    找到一个java文件中包含的含有main方法的类的名字
    */
    static String findMainClassName(String code){
        //1.首先寻找一个java文件中公共类
        int index=code.indexOf("public class");
        if(index==-1){
            //2.查找含有main方法的类
            int startIndex=code.indexOf("public static void main");
           if(startIndex==-1){
               return null;
           }
           startIndex=code.lastIndexOf("class",startIndex);
            startIndex+=(new String("class")).length();
            int endIndex=code.indexOf('{',startIndex);
            String mainClassName=code.substring(startIndex,endIndex);
            return mainClassName;
        }
            
        index+=(new String("public class")).length();
        int index1=code.indexOf('{',index);
        String mainClassName=code.substring(index, index1);
        mainClassName=mainClassName.trim();
        
        return mainClassName;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class FileFinder {
    
    public static File findFile(String fileName){
        return new File("config/"+fileName);
    }
    
    public static boolean isExistFile(String fileName){
        File file = new File(fileName);
        if(file.exists())
            return true;
        else
            return false;
    }
    
    public static List<String> getSubs(String dirName){
        List<String> strs = new LinkedList<>();
        File file = new File(dirName);
        if(file.exists()){
            File[] files = file.listFiles();
            for(int i = 0; i < files.length; i++){
                strs.add(files[i].getName());
            }
        }
        return strs;
    }
    
}

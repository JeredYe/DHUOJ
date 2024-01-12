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
        private static String findFilePath(String fileName) {
        String currentPath = System.getProperty("user.dir");
        String configFile = fileName;

        // Try to find config.xml in the current directory
        File currentDirConfigFile = new File(currentPath, configFile);
        System.out.print(currentDirConfigFile.getAbsoluteFile());
        if (currentDirConfigFile.exists()) {
            return currentDirConfigFile.getAbsolutePath();
        }

        // If not found, recursively search in parent directories
        return findInParentDirectory(new File(currentPath), configFile);
    }

    private static String findInParentDirectory(File directory, String configFile) {
        File configFileInParent = new File(directory, configFile);
        if (configFileInParent.exists()) {
            return configFileInParent.getAbsolutePath();
        }

        // If not found and not the root directory, recursively search in parent
        File parentDirectory = directory.getParentFile();
        if (parentDirectory != null) {
            return findInParentDirectory(parentDirectory, configFile);
        }

        // If reached the root directory and still not found, return null
        return null;
    }
    public static File findFile(String fileName){
        return new File(findFilePath(fileName));
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

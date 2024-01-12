/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import static common.LangSelector.*;
import java.util.HashMap;

/**
 *
 * @author tange
 */
public class LangSelectorTest {
    public static void main(String[] args) {

        String compileCmd=getCompileCommand("C++","MinGW");
        HashMap<String,String> map=new HashMap<String,String>(){
            {
                put(LangSelector.PlaceHolder.SourceFile.getStr(), "TestSourceFile");
                put(LangSelector.PlaceHolder.ObjFile.getStr(), "TestObjFile");
            }
        };
        compileCmd=matchPlaceHolder(compileCmd,map);
        System.out.println(compileCmd);
        System.out.println(getDefaultCompilerName("C++"));
    }
}

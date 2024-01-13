/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.util;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author 毛泉
 */
public class Tips {
    Map<String,String> tips = new HashMap<String,String>();
    public Tips(){
        tips.put("AC","AC：运行结果正确");
        tips.put("WA","WA：测试数据运行错误。注意：有两种情况可能导致自己运行程序时正确但OJ系统给出错误结果：\n"
            + "1、如果在程序中使用了fflush函数清空输入，可能导致WA。\n"
            + "2、如果是输入字符串，OJ系统的测试数据最后一行后面不一定有换行符，但我们在键盘上输入时每一行后面都必然有换行符。所以，要当心这个区别。");
        tips.put("TLE","TLE：程序运行超时。可能的原因：\n"
                + "1、程序中存在死循环\n"
                + " 2、算法需要改进");
        tips.put("PE","PE：测试数据结果正确，但空格、换行、tab等字符不正确。");
        tips.put("RE","RE：程序运行时错误。可能的原因：\n"
            + "1、main函数return的不是0或者忘记写return 0语句；\n"
            + " 2、除0或指针错误等原因造成程序崩溃。");
        tips.put("CE","CE：编译错误");
        tips.put("OLE","OLE：程序运行输出内容超过了允许的最大长度");
        tips.put("SE","SE：系统错误，请重试");
}
    public final String getTips(String status){
        return (String) tips.get(status);
    }        
}

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
 * @author ëȪ
 */
public class Tips {
    Map<String,String> tips = new HashMap<String,String>();
    public Tips(){
        tips.put("AC","AC�����н����ȷ");
        tips.put("WA","WA�������������д���ע�⣺������������ܵ����Լ����г���ʱ��ȷ��OJϵͳ������������\n"
            + "1������ڳ�����ʹ����fflush����������룬���ܵ���WA��\n"
            + "2������������ַ�����OJϵͳ�Ĳ����������һ�к��治һ���л��з����������ڼ���������ʱÿһ�к��涼��Ȼ�л��з������ԣ�Ҫ�����������");
        tips.put("TLE","TLE���������г�ʱ�����ܵ�ԭ��\n"
                + "1�������д�����ѭ��\n"
                + " 2���㷨��Ҫ�Ľ�");
        tips.put("PE","PE���������ݽ����ȷ�����ո񡢻��С�tab���ַ�����ȷ��");
        tips.put("RE","RE����������ʱ���󡣿��ܵ�ԭ��\n"
            + "1��main����return�Ĳ���0��������дreturn 0��䣻\n"
            + " 2����0��ָ������ԭ����ɳ��������");
        tips.put("CE","CE���������");
        tips.put("OLE","OLE����������������ݳ������������󳤶�");
        tips.put("SE","SE��ϵͳ����������");
}
    public final String getTips(String status){
        return (String) tips.get(status);
    }        
}

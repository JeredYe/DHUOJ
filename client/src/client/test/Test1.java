/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.test;

import client.util.Decrypt;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import javax.crypto.CipherInputStream;
import org.junit.Test;

/**
 *
 * @author ytxlo
 */
public class Test1 {

    public static void main(String args[]){
        new Test1().test();
    }
    public void test(){
        try {
            byte[] message= Decrypt.encrypt("1413201160", str);
            InputStream is = new ByteArrayInputStream(message);
            OutputStream out = new FileOutputStream(new File("./test.dat")); 
            byte[] buffer = new byte[1024]; 
            int r; 
            while ((r = is.read(buffer)) > 0) { 
                out.write(buffer, 0, r); 
            } 
            is.close(); 
            out.close(); 
        } catch (Exception e) {
            e.printStackTrace();
        }
        File f = new File("./test.dat");  
  
        ByteArrayOutputStream bos = new ByteArrayOutputStream((int)f.length());  
        BufferedInputStream in = null;  
        try{  
            in = new BufferedInputStream(new FileInputStream(f));  
            int buf_size = 1024;  
            byte[] buffer = new byte[buf_size];  
            int len = 0;  
            while(-1 != (len = in.read(buffer,0,buf_size))){  
                bos.write(buffer,0,len);  
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        String result = Decrypt.decrypt("1413201160", bos.toByteArray());
        //System.out.println(result);
    }
        String str = new String("<?xml version=\"1.0\" encoding=\"GB2312\" standalone=\"no\"?>\n" +
"<code>\n" +
"<finished>true</finished>\n" +
"<submit>\n" +
"<sourceCode>#include &lt;stdio.h&gt;\n" +
"#include &lt;string.h&gt;\n" +
"\n" +
"typedef struct Stu {\n" +
"	int num;\n" +
"	char name[20];\n" +
"	char sex[20];\n" +
"	int age;\n" +
"	int score;\n" +
"}Stu;\n" +
"\n" +
"int main() {\n" +
"	int N, i, j;\n" +
"	Stu s[10];\n" +
"	int tempA;\n" +
"	char tempB[20];\n" +
"	scanf(\"%d\", &amp;N);\n" +
"	for(i = 0; i &lt; N; ++i) {\n" +
"		scanf(\"%d %s %s %d %d\", &amp;s[i].num, s[i].name, s[i].sex, &amp;s[i].age, &amp;s[i].score);\n" +
"	}\n" +
"	for(i = 0; i &lt; N - 1; ++i) {\n" +
"		for(j = i + 1; j &lt; N; ++j) {\n" +
"			if(s[i].score &gt; s[j].score) {\n" +
"				tempA = s[i].num;\n" +
"				s[i].num = s[j].num;\n" +
"				s[j].num = tempA;\n" +
"\n" +
"				strcpy(tempB, s[i].name);\n" +
"				strcpy(s[i].name, s[j].name);\n" +
"				strcpy(s[j].name, tempB);\n" +
"\n" +
"				strcpy(tempB, s[i].sex);\n" +
"				strcpy(s[i].sex, s[j].sex);\n" +
"				strcpy(s[j].sex, tempB);\n" +
"\n" +
"				tempA = s[i].age;\n" +
"				s[i].age = s[j].age;\n" +
"				s[j].age = tempA;\n" +
"\n" +
"				tempA = s[i].score;\n" +
"				s[i].score = s[j].score;\n" +
"				s[j].score = tempA;\n" +
"			}\n" +
"		}\n" +
"	}\n" +
"	for(i = 0; i &lt; N; ++i) {\n" +
"		printf(\"%d %s %s %d %d\\n\", s[i].num, s[i].name, s[i].sex, s[i].age, s[i].score);\n" +
"	}\n" +
"	return 0;\n" +
"}</sourceCode>\n" +
"<id>68887</id>\n" +
"<time>2017-04-19 10:38:08</time>\n" +
"<status>AC</status>\n" +
"<correctCaseIds>1447,1448,1449,1450,1451,</correctCaseIds>\n" +
"<wrongCaseIds/>\n" +
"<score>0.98</score>\n" +
"<remark>AC：运行结果正确</remark>\n" +
"<similarity/>\n" +
"<submits>2</submits>\n" +
"</submit>\n" +
"</code>");
}

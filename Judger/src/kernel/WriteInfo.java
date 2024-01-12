package kernel;

import MyCache.Shared;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class WriteInfo extends Thread {

    private InputStream is;
    private String info;
    public int exitFlag = 0;
    Thread thread = new Thread(this);

    public WriteInfo(InputStream is) {
        this.is = is;

    }

    @Override
    public void run() {

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        char[] s = new char[512];
        //int c=-1;
        int len = -1;

        StringBuffer buffer = new StringBuffer();
        try {
            while (((len = reader.read(s, 0, 512)) != -1)) {
                if (buffer.length() < 2*Shared.maxOutputLength) { //����\r�Ĵ��ڣ�ʹ��������󣬵������ܳ���������maxoutputlength
                    buffer.append(s, 0, len);
                } 
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
//        if(Config.DEBUG){
//            System.out.println("output length:"+buffer.length());
//        }
        info = buffer.toString().replace("\r\n", "\n");
        if (info.length() > Shared.maxOutputLength) {
           info = info.substring(0, Shared.maxOutputLength) + "(���ض�,���и������)";
        }

        try {
            if (is != null) {
                is.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String returnInfo() {
        return this.info;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;
import javax.net.ssl.*;
import java.security.cert.X509Certificate;
/**
 *
 * @author tange
 */
public class DisableHostnameVerifier {
    public static void disable() throws Exception {
        // ���� SSLContext
        SSLContext sslContext = SSLContext.getInstance("TLS");
        
        // ����һ�� TrustManager��ʵ�� X509TrustManager �ӿڲ��������ⱸ�����Ƽ��
        X509TrustManager trustManager = new X509TrustManager() {
            public void checkClientTrusted(X509Certificate[] chain, String authType) {}
            public void checkServerTrusted(X509Certificate[] chain, String authType) {}
            public X509Certificate[] getAcceptedIssuers() { return new X509Certificate[0]; }
        };

        // ��ʼ�� SSLContext�����Զ���� TrustManager ��ӵ� TrustManager ������
        sslContext.init(null, new TrustManager[] { trustManager }, null);
        
        // ��ȡ SSLContext �е� SSLSocketFactory��������ΪĬ�ϵ� SSLSocketFactory
        HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
        
        // �������ⱸ�����Ƽ��
        HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> true);
    }
}
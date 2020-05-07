package com.example.laba2;

import com.example.laba2.model.Civilization;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;


// Класс для проверки наличия картинки
public class Network {

    public static ExecutorService executorService = Executors.newFixedThreadPool(4);

    public List<Civilization> getList(List<Civilization> list) throws Exception {
        List<Civilization> passed = new ArrayList<>();
        for (Civilization some : list ){
            if(buildConnection(some.getGraphic())){
                passed.add(some);
            }
        }
        return passed;
    }

    private boolean buildConnection(final String url) throws Exception {

        final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {

            @Override
            public void checkClientTrusted(java.security.cert.X509Certificate[] x509Certificates, String s) {

            }

            @Override
            public void checkServerTrusted(java.security.cert.X509Certificate[] x509Certificates, String s) {

            }

            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }

        }};

        final SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
        HostnameVerifier allHostsValid = (hostname, session) -> true;

        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

        final URL _url = new URL(url);
        final HttpURLConnection connection = (HttpURLConnection) _url.openConnection();
        connection.setRequestMethod("GET");
        connection.setReadTimeout(15 * 1000);
        connection.setConnectTimeout(15 * 1000);
        connection.connect();

        if (connection.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND){
            return false;
        }

        return true;

    }

}

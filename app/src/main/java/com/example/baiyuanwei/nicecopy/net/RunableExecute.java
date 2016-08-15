package com.example.baiyuanwei.nicecopy.net;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by baiyuanwei on 16/8/14.
 */
public class RunableExecute implements Runnable {

    private String urlStr;
    private String method;
    private byte[] data;

    public RunableExecute(String url, String method, byte[] data) {
        this.urlStr = url;
        this.method = method;
        this.data = data;
    }

    @Override
    public void run() {


        HttpURLConnection urlConnection;

        try {
            URL url = new URL(urlStr);

            urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            urlConnection.setRequestMethod(method);
            urlConnection.setConnectTimeout(8000);
            urlConnection.setReadTimeout(8000);
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setRequestProperty("Content-type", "application/json");

            OutputStream outputStream = urlConnection.getOutputStream();
            outputStream.write(data);
            int response = urlConnection.getResponseCode();
            if (response == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = urlConnection.getInputStream();

            }



        }catch (Exception e){
            e.printStackTrace();
        }


    }
}

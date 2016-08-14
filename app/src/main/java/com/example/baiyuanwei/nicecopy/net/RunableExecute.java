package com.example.baiyuanwei.nicecopy.net;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * Created by baiyuanwei on 16/8/14.
 */
public class RunableExecute implements Runnable {

    private String urlStr;
    private String method;
    private Map<String, String> params;

    public RunableExecute(String url, String method, Map<String, String> params) {
        this.urlStr = url;
        this.method = method;
        this.params = params;
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



        }catch (Exception e){
            e.printStackTrace();
        }


    }
}

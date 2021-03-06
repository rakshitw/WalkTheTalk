package com.xrci.standup;


import android.os.StrictMode;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PostData {
    String tag = this.getClass().getName();

    public static int RESULT_OK = 200;
    public static String INVALID_RESPONSE = "invalidResponse";
    public static String INVALID_PAYLOAD = "invalidPayload";
    public static String EXCEPTION = "exception";

    public static String postContent(String path, String data) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();
            HttpPost httpost = new HttpPost(path);
            /*
            // convert parameters into JSON object
			JSONObject holder = getJsonObjectFromMap(params);
			JSONObject holder=new JSONObject(data);
			*/
            //Passes the results to a string builder/entity
            StringEntity se = new StringEntity(data);
//            Logger.appendLog(data, true);
            //Sets the post request as the resulting string
            httpost.setEntity(se);
            //Sets a request header
            httpost.setHeader("Accept", "application/json");
            httpost.setHeader("Content-type", "application/json");
            //Handles what is returned from the page
            HttpResponse reply = httpclient.execute(httpost);
            //Thread.sleep(7000);
            Log.i("status code", "status code is " + reply.getStatusLine().getStatusCode());

            if (reply.getStatusLine().getStatusCode() != RESULT_OK) {
                Log.i("check", "status code is " + reply.getStatusLine().getStatusCode());
                return INVALID_RESPONSE;
            } else {
                BufferedReader BuffRead = new BufferedReader(new InputStreamReader(reply.getEntity().getContent(), "UTF-8"));
                String response = BuffRead.readLine();
                JSONObject responseJSON = new JSONObject(response);
                if (responseJSON.has("Result")) {
                    String responseResult = responseJSON.get("Result").toString();

                    if (responseResult.equals("error")) {
                        Log.i("check", "postActivity in Post Data " + responseJSON.toString());
                        return INVALID_PAYLOAD;
                    } else {
                        Log.i("check", "I am here");
                        return response;
                    }

                } else {
                    Log.i("check", "result field missing  and status code not 200");
                    return response;
                }
            }
        } catch (Exception e) {
            Log.e("PostData", "Error in posting data: " + e.getMessage());

//            Logger.appendLog("Exception in posting data:" + e.getMessage(), true);
            //AppLog.logger(" Error in posting for " +path +" with data:" + data+" :"+ e.getMessage());
            e.printStackTrace();
            return EXCEPTION;
        }

    }

    public static String postMultipart(String path, MultipartEntity multipartEntity) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();
            HttpPost httpost = new HttpPost(path);
            httpost.setEntity(multipartEntity);
            //Sets a request header
//            httpost.setHeader("Accept", "application/json");
//            httpost.setHeader("Content-type", "application/json");
            //Handles what is returned from the page
            HttpResponse reply = httpclient.execute(httpost);
            //Thread.sleep(7000);
            Log.i("status code", "status code in multipart is " + reply.getStatusLine().getStatusCode());

            if (reply.getStatusLine().getStatusCode() != RESULT_OK) {
                Log.i("check", "status code in multipart is " + reply.getStatusLine().getStatusCode());
                return INVALID_RESPONSE;
            } else {

                BufferedReader BuffRead = new BufferedReader(new InputStreamReader(reply.getEntity().getContent(), "UTF-8"));
                String response;
                StringBuilder s = new StringBuilder();

                while ((response = BuffRead.readLine()) != null) {
                    s = s.append(response);
                }
                response = s.toString();
                Log.i("check", "result field missing  and status code not 200");
                return response;
            }
        } catch (Exception e) {
            Log.e("PostData", "Error in posting data: " + e.getMessage());

//            Logger.appendLog("Exception in posting data:" + e.getMessage(), true);
            //AppLog.logger(" Error in posting for " +path +" with data:" + data+" :"+ e.getMessage());
            e.printStackTrace();
            return EXCEPTION;
        }

    }


}

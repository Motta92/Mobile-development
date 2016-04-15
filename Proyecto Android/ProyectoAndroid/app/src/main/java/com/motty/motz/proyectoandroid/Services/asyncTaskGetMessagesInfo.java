package com.motty.motz.proyectoandroid.Services;

import android.os.AsyncTask;
import android.util.Log;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Carlos on 3/29/2016.
 */
public class asyncTaskGetMessagesInfo extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... params) {
        // The connection URL
        //final String url = "http://10.0.2.2:8090/rest/messages/"+params[0]+"/"+params[1];
        final String url = "http://172.17.69.220:8090/rest/messages/"+params[0]+"/"+params[1];

        // Create a new RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        // Add the String message converter
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

        // Make the HTTP GET request, marshaling the response to a String
        String result = restTemplate.getForObject(url, String.class, "Android");

        return result;

    }
}
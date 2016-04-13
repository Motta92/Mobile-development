package com.motty.motz.proyectoandroid.Services;

import android.os.AsyncTask;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Carlos on 4/12/2016.
 */
public class asyncTaskGetFilesInfo extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... params) {
        // The connection URL
        String url = "http://10.0.2.2:8090/rest/files/"+params[0];

        // Create a new RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        // Add the String message converter
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

        // Make the HTTP GET request, marshaling the response to a String
        String result = restTemplate.getForObject(url, String.class, "Android");

        return result;
    }
}

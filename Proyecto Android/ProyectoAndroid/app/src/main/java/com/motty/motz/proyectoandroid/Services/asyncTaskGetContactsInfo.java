package com.motty.motz.proyectoandroid.Services;


import android.os.AsyncTask;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Carlos on 3/27/2016.
 */
public class asyncTaskGetContactsInfo extends AsyncTask<Void, Void, String> {
    @Override
    protected String doInBackground(Void... params) {
        // The connection URL
        //final String url = "http://10.0.2.2:8090/rest/contacts/3";
        final String url = "http://172.17.69.220:8090/rest/contacts/3";

        // Create a new RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        // Add the String message converter
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

        // Make the HTTP GET request, marshaling the response to a String
        String result = restTemplate.getForObject(url, String.class, "Android");

        return result;
    }
}

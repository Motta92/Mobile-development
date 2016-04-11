package com.motty.motz.proyectoandroid.Services;

import android.os.AsyncTask;
import android.util.Log;

import com.motty.motz.proyectoandroid.TemplateClasses.messageTemplateClass;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Carlos on 4/10/2016.
 */
public class asyncTaskSendMessageInfo extends AsyncTask<String, Void, Void> {

    @Override
    protected Void doInBackground(String... params) {
        // The connection URL
        String url = "http://10.0.2.2:8090/rest/messages";

        // Create a new RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        // Add the String message converter
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

        // Create template class
        messageTemplateClass postMessage = new messageTemplateClass();

        // Set post values
        postMessage.setFrom(Integer.parseInt(params[0]));
        postMessage.setTo(Integer.parseInt(params[1]));
        postMessage.setText(params[2]);

        // Make the HTTP GET request, marshaling the response to a String
        messageTemplateClass result = restTemplate.postForObject(url, postMessage, messageTemplateClass.class);

        return null;

    }
}

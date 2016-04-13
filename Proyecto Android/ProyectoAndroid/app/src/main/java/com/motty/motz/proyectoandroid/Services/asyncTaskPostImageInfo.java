package com.motty.motz.proyectoandroid.Services;

import android.os.AsyncTask;

import com.motty.motz.proyectoandroid.TemplateClasses.messageTemplateClass;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Carlos on 4/12/2016.
 */
public class asyncTaskPostImageInfo extends AsyncTask<String, Void, Void> {
    @Override
    protected Void doInBackground(String... params) {
        // The connection URL
        String url = "http://10.0.2.2:8090/rest/files/"+params[0]+"/"+params[1];

        // Create a new RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        // Add the String message converter
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

        MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

        map.add("file",new FileSystemResource(params[2]));

        // Make the HTTP GET request, marshaling the response to a String
        String result = restTemplate.postForObject(url,map,String.class);


        return null;
    }
}

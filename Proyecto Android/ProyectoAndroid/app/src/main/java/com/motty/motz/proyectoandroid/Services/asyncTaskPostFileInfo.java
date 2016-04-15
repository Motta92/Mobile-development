package com.motty.motz.proyectoandroid.Services;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import com.motty.motz.proyectoandroid.Activities.files;
import com.motty.motz.proyectoandroid.TemplateClasses.messageTemplateClass;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

/**
 * Created by Carlos on 4/12/2016.
 */
public class asyncTaskPostFileInfo extends AsyncTask<String, Void, Void> {

    private MultiValueMap<String, Object> formData;

    @Override
    protected Void doInBackground(String... params) {
        /*
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
        */


        FileSystemResource resource = new FileSystemResource(params[2]);
        //Resource resource = new ClassPathResource(params[2]);
        formData = new LinkedMultiValueMap<String, Object>();
        formData.add("file", resource);

        final String url = "http://10.0.2.2:8090/rest/files/"+params[0]+"/"+params[1];

        HttpHeaders requestHeaders = new HttpHeaders();

        requestHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<MultiValueMap<String, Object>>(formData, requestHeaders);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        Log.d("MotzPostLog", response.getBody());


        return null;
    }

}

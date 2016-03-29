package com.motty.motz.proyectoandroid;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class contacts extends ListActivity {

    String result;
    List<postClass> contactsList;
    ArrayList<String> obtainedInfo = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        asyncTaskGetServiceInfo myAsyncTask = new asyncTaskGetServiceInfo();

        try {
            result = myAsyncTask.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        ObjectMapper mapper = new ObjectMapper();

        try {
            contactsList = mapper.readValue(result, mapper.getTypeFactory().constructCollectionType(List.class, postClass.class));
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*
        for(int i=0; i<contactsList.size(); ++i){
            obtainedInfo.add(contactsList.get(i).getNombre());
        }
        */

        customArrayAdapter activityAdapter = new customArrayAdapter(this,R.layout.contacts_custom_layout,contactsList);
        setListAdapter(activityAdapter);


    }




}

package com.motty.motz.proyectoandroid;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class messages extends ListActivity {
    String result;
    List<messageTemplateClass> messageList;
    ArrayList<String> chatMessages = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

        Integer idMessage = getIntent().getIntExtra("id",0);

        asyncTaskGetMessagesInfo myAsyncTask = new asyncTaskGetMessagesInfo();

        try {
            result = myAsyncTask.execute(idMessage.toString(), "3").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        ObjectMapper mapper = new ObjectMapper();

        try {
            messageList = mapper.readValue(result, mapper.getTypeFactory().constructCollectionType(List.class, messageTemplateClass.class));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(int i=0; i<messageList.size(); ++i){
            chatMessages.add(messageList.get(i).getText());
        }

        ArrayAdapter<String> activityAdapter = new ArrayAdapter<String>(this,R.layout.messages_custom_layout,chatMessages);
        setListAdapter(activityAdapter);
    }

}

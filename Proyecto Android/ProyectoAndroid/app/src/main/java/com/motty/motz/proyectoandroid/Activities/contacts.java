package com.motty.motz.proyectoandroid.Activities;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.motty.motz.proyectoandroid.CustomAdapters.customArrayAdapterContacts;
import com.motty.motz.proyectoandroid.R;
import com.motty.motz.proyectoandroid.Services.asyncTaskGetContactsInfo;
import com.motty.motz.proyectoandroid.TemplateClasses.contactTemplateClass;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class contacts extends ListActivity{

    String result;
    List<contactTemplateClass> contactsList;
    ArrayList<String> obtainedInfo = new ArrayList<String>();
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        lv = getListView();

        asyncTaskGetContactsInfo myAsyncTask = new asyncTaskGetContactsInfo();

        try {
            result = myAsyncTask.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        ObjectMapper mapper = new ObjectMapper();

        try {
            contactsList = mapper.readValue(result, mapper.getTypeFactory().constructCollectionType(List.class, contactTemplateClass.class));
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*
        for(int i=0; i<contactsList.size(); ++i){
            obtainedInfo.add(contactsList.get(i).getNombre());
        }
        */


        customArrayAdapterContacts activityAdapter = new customArrayAdapterContacts(this,R.layout.contacts_custom_layout,contactsList);
        setListAdapter(activityAdapter);


    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
    /*
        Toast toast = Toast.makeText(getApplicationContext(),
                "Item " + position,
                Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();

        Intent i = new Intent(contacts.this,messages.class);
        i.putExtra("id", contactsList.get(position).getUserId());
        this.startActivity(i);
    */
    }
}

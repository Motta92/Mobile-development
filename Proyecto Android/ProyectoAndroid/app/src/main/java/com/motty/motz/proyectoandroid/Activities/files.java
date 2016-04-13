package com.motty.motz.proyectoandroid.Activities;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.motty.motz.proyectoandroid.CustomAdapters.customArrayAdapterContacts;
import com.motty.motz.proyectoandroid.CustomAdapters.customArrayAdapterFiles;
import com.motty.motz.proyectoandroid.R;
import com.motty.motz.proyectoandroid.Services.asyncTaskGetMessagesInfo;
import com.motty.motz.proyectoandroid.Services.asyncTaskGetSharedFilesInfo;
import com.motty.motz.proyectoandroid.Services.asyncTaskPostImageInfo;
import com.motty.motz.proyectoandroid.TemplateClasses.contactTemplateClass;
import com.motty.motz.proyectoandroid.TemplateClasses.fileTemplateClass;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class files extends ListActivity {

    public static final int RESULT_LOAD_IMAGE = 0;
    String result;
    List<fileTemplateClass> filesList;
    ArrayList<String> obtainedInfo = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_files);

        Integer idMessage = getIntent().getIntExtra("id", 0);
        String userName = getIntent().getStringExtra("name");

        TextView name = (TextView) findViewById(R.id.userNameFiles);
        name.setText(userName);

        asyncTaskGetSharedFilesInfo myAsyncTask = new asyncTaskGetSharedFilesInfo();

        try {
            result = myAsyncTask.execute(idMessage.toString(), "3").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        ObjectMapper mapper = new ObjectMapper();

        try {
            filesList = mapper.readValue(result, mapper.getTypeFactory().constructCollectionType(List.class, fileTemplateClass.class));
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*
        for(int i=0; i<contactsList.size(); ++i){
            obtainedInfo.add(contactsList.get(i).getNombre());
        }
        */

        customArrayAdapterFiles activityAdapter = new customArrayAdapterFiles(this,R.layout.files_custom_layout,filesList);
        setListAdapter(activityAdapter);
    }

    public void sendFile(View view) {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        this.startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
    }

    public void filterByDate(View view) {
    }

    public void filterByName(View view) {
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Get image from image gallery
        if(resultCode == RESULT_OK && requestCode == RESULT_LOAD_IMAGE){
            // Get the address of the image
            Uri imageURI = data.getData();
            InputStream inputStream;

            try {
                inputStream = getContentResolver().openInputStream(imageURI);
                Log.d("Motz1", inputStream.toString());
                Log.d("Motz2", imageURI.toString());
                //Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            //Uri bitmapToFile = new Uri(imageURI);

            //File newFile = new File(tempUri);

            Integer idMessage = getIntent().getIntExtra("id", 0);

            asyncTaskPostImageInfo myAsyncTask = new asyncTaskPostImageInfo();

            try {
                myAsyncTask.execute(idMessage.toString(), "3", imageURI.toString()).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            // Create an image file name
            //String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        }
    }
}

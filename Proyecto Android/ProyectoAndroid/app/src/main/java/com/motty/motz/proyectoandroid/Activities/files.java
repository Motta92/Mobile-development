package com.motty.motz.proyectoandroid.Activities;

import android.app.DownloadManager;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.motty.motz.proyectoandroid.CustomAdapters.customArrayAdapterFiles;
import com.motty.motz.proyectoandroid.R;
import com.motty.motz.proyectoandroid.Services.asyncTaskGetSharedFilesInfo;
import com.motty.motz.proyectoandroid.Services.asyncTaskPostFileInfo;
import com.motty.motz.proyectoandroid.TemplateClasses.fileTemplateClass;

import java.io.ByteArrayOutputStream;
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
    public static final int RESULT_LOAD_FILE = 0;
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

        if(filesList != null){
            for(int i=0; i<filesList.size(); ++i){
                obtainedInfo.add(filesList.get(i).getName());
            }
        }



        customArrayAdapterFiles activityAdapter = new customArrayAdapterFiles(this,R.layout.files_custom_layout,filesList);
        setListAdapter(activityAdapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        fileTemplateClass row = filesList.get(position);
        /*
        Toast toast = Toast.makeText(v.getContext(),
                "File " + position,
                Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
        */

        // Use Download Manager o AsyncTask

        DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);

        String url = "http://10.0.2.2:8090/rest/files/"+ filesList.get(position).getId().toString();

        Uri uri = Uri.parse(url);

        DownloadManager.Request request = new DownloadManager.Request(uri);

        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        request.setTitle("File Download.");
        request.setDescription("File is downloading...");

        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, row.getName());

        Long reference = manager.enqueue(request);
    }

    public void sendFile2(View view){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("file/*");
        startActivityForResult(intent, RESULT_LOAD_FILE);
    }

    //@Override
    protected void onActivityResult2(int requestCode, int resultCode, Intent intent) {
        if (requestCode == RESULT_LOAD_FILE)
        {
            if (resultCode == RESULT_OK)
            {
                Uri uri = intent.getData();
                String type = intent.getType();
                Log.i("LoadImageTagMotz", "Pick completed: " + uri + " " + type);
                if (uri != null)
                {
                    String path = uri.toString();
                    if (path.toLowerCase().startsWith("file://"))
                    {
                        // Selected file/directory path is below

                        asyncTaskPostFileInfo myAsyncTask = new asyncTaskPostFileInfo();

                        Integer idMessage = getIntent().getIntExtra("id", 0);

                        try {
                            myAsyncTask.execute(idMessage.toString(), "3", (new File(URI.create(path))).getAbsolutePath()).get();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
            else Log.i("LoadImageTagMotz2","Back from pick with cancel status");
        }
    }

    public void sendFile(View view) {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        this.startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
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
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                Uri tmp = getImageUri(this, bitmap);
                File myFile = new File(getRealPathFromURI(tmp));

                Integer idMessage = getIntent().getIntExtra("id", 0);
                asyncTaskPostFileInfo myAsyncTask = new asyncTaskPostFileInfo();

                myAsyncTask.execute(idMessage.toString(), "3", myFile.getAbsolutePath()).get();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            // Create an image file name
            //String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            finish();
            startActivity(getIntent());
        }
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }
}

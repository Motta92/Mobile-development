package com.motty.motz.proyectoandroid.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.motty.motz.proyectoandroid.TemplateClasses.messageTemplateClass;

import java.util.ArrayList;


public class MessagesDatabase extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "motz.db";
    private static final String MESSAGES_TABLE = "messagesDB";

    private static final String ID_COLUMN = "ID";
    private static final String FROM_COLUMN = "FROM_COL";
    private static final String TO_COLUMN = "TO_COL";
    private static final String MESSAGE_COLUMN = "MESSAGE";
    private static final String DATE_COLUMN = "DATE_COL";

    public MessagesDatabase(Context context, SQLiteDatabase.CursorFactory factory){
        super(context,DATABASE_NAME,factory,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String TABLE_CREATION_QUERY = "CREATE TABLE " + MESSAGES_TABLE + " (" + ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                FROM_COLUMN + " INTEGER, " + TO_COLUMN + " INTEGER, " + MESSAGE_COLUMN + " VARCHAR(100)," + DATE_COLUMN + " VARCHAR(25))";
        db.execSQL(TABLE_CREATION_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String TABLE_WARRANTY = "DROP TABLE IF EXISTS";
        db.execSQL(TABLE_WARRANTY + MESSAGES_TABLE);
        onCreate(db);
    }

    public void addMessage(messageTemplateClass newMessage){
        ContentValues content = new ContentValues();

        content.put(FROM_COLUMN,newMessage.getFrom());
        content.put(TO_COLUMN,newMessage.getTo());
        content.put(MESSAGE_COLUMN,newMessage.getText());
        content.put(DATE_COLUMN,newMessage.getDate());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(MESSAGES_TABLE,null,content);
        db.close();
    }

    public void getMessages(ArrayList<messageTemplateClass> messages, Integer from, Integer to){
        String GET_MESSAGE_QUERY = "SELECT * FROM " + MESSAGES_TABLE + " WHERE " + FROM_COLUMN
                + " = ? and " + TO_COLUMN + " = ?";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(GET_MESSAGE_QUERY,new String[]{from.toString(), to.toString()});



        if(cursor.moveToFirst()){
            messages.add(new messageTemplateClass(Integer.parseInt(cursor.getString(1)), Integer.parseInt(cursor.getString(2)), cursor.getString(3), cursor.getString(4)));
            while(cursor.moveToNext()){
                messages.add(new messageTemplateClass(Integer.parseInt(cursor.getString(1)), Integer.parseInt(cursor.getString(2)), cursor.getString(3), cursor.getString(4)));
            }
        }

        cursor.close();
        db.close();
    }
}

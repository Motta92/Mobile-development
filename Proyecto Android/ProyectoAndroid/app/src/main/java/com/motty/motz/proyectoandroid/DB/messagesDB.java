package com.motty.motz.proyectoandroid.DB;

import android.util.Log;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.motty.motz.proyectoandroid.TemplateClasses.messageTemplateClass;

import java.security.MessageDigest;

import java.util.Date;
import java.util.List;

/**
 * Created by Carlos on 4/10/2016.
 */

@Table(name = "MessagesDb")
public class messagesDB extends Model {

    @Column(name = "fromCol")
    public Integer fromCol;

    @Column(name = "toCol")
    public Integer toCol;

    @Column(name = "msg")
    public String msg;


    public messagesDB() {
        super();
    }

    public static List<messagesDB> getMessagesFromTo(Integer from, Integer to){
        Log.d("MotzQuery", "(fromCol" + " = " + from + " and " + "toCol" + " = " + to + ") or " + "(fromCol" + " = " + to + " and " + "toCol" + " = " + from + ")");
        return new Select()
                .from(messagesDB.class)
                .where("(fromCol" + " = " + from + " and " + "toCol" + " = " + to + ") or " + "(fromCol" + " = " + to + " and " + "toCol" + " = " + from + ")")
                        //.where(from + " = ?", from.toString())
                .execute();
    }

    public static void deleteAll() {
        new Delete().from(messagesDB.class).execute();
    }


    public static void insertIntoDB(List<messageTemplateClass> messageList){
        ActiveAndroid.beginTransaction();
        try {
            for (int i = 0; i < messageList.size(); ++i) {
                messagesDB msgs = new messagesDB();
                msgs.fromCol = messageList.get(i).getFrom();
                msgs.toCol = messageList.get(i).getTo();
                msgs.msg = messageList.get(i).getText();
                //msgs.date = messageList.get(i).getDate();
                msgs.save();
                //chatMessages.add(messageList.get(i).getText());

                ActiveAndroid.setTransactionSuccessful();
            }
        }
        finally {
            ActiveAndroid.endTransaction();
        }

    }

}

package com.motty.motz.proyectoandroid.CustomAdapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.motty.motz.proyectoandroid.DB.messagesDB;
import com.motty.motz.proyectoandroid.R;
import com.motty.motz.proyectoandroid.TemplateClasses.messageTemplateClass;

import java.util.List;

/**
 * Created by Carlos on 3/29/2016.
 */
public class customArrayAdapterMessages extends ArrayAdapter<messagesDB> {
    public customArrayAdapterMessages(Context context, int resource, List<messagesDB> objects) {
        super(context, resource, objects);
    }

    private class ViewHolder{
        TextView messageText;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder viewHolder = null;

        messagesDB row = getItem(position);

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if(convertView == null){
            convertView = inflater.inflate(R.layout.messages_custom_layout, null);
            viewHolder = new ViewHolder();
            viewHolder.messageText = (TextView) convertView.findViewById(R.id.messageText);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.messageText.setText(row.msg);

        return convertView;
    }
}

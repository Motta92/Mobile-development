package com.motty.motz.proyectoandroid.CustomAdapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.motty.motz.proyectoandroid.R;
import com.motty.motz.proyectoandroid.TemplateClasses.fileTemplateClass;

import java.util.List;


/**
 * Created by Carlos on 4/12/2016.
 */
public class customArrayAdapterFiles extends ArrayAdapter<fileTemplateClass> {


    public customArrayAdapterFiles(Context context, int resource, List<fileTemplateClass> objects) {
        super(context, resource, objects);
    }

    private class ViewHolder{
        TextView fileTag;
    }

    public View getView(final int position, View convertView, ViewGroup parent){
        ViewHolder viewHolder = null;

        fileTemplateClass row = getItem(position);

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if(convertView == null){
            convertView = inflater.inflate(R.layout.files_custom_layout, null);
            viewHolder = new ViewHolder();
            viewHolder.fileTag = (TextView) convertView.findViewById(R.id.fileDescription);

            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.fileTag.setText(row.getDate());

        return convertView;
    }

}

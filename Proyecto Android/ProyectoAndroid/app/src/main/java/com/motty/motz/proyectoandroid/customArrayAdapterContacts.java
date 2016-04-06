package com.motty.motz.proyectoandroid;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Carlos on 3/29/2016.
 */
public class customArrayAdapterContacts extends ArrayAdapter<postClass>{
    public customArrayAdapterContacts(Context context, int resource, List<postClass> objects) {
        super(context, resource, objects);
    }

    private class ViewHolder{
        TextView userName;
        TextView userId;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder viewHolder = null;

        postClass row = getItem(position);

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if(convertView == null){
            convertView = inflater.inflate(R.layout.contacts_custom_layout, null);
            viewHolder = new ViewHolder();
            viewHolder.userName = (TextView) convertView.findViewById(R.id.userName);
            viewHolder.userId = (TextView) convertView.findViewById(R.id.userId);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.userName.setText(row.getNombre());
        viewHolder.userId.setText(row.getUserName());

        return convertView;
    }
}

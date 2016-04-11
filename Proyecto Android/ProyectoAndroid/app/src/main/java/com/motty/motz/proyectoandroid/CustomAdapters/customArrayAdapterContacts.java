package com.motty.motz.proyectoandroid.CustomAdapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.motty.motz.proyectoandroid.R;
import com.motty.motz.proyectoandroid.TemplateClasses.contactTemplateClass;
import com.motty.motz.proyectoandroid.Activities.files;
import com.motty.motz.proyectoandroid.Activities.messages;

import java.util.List;

/**
 * Created by Carlos on 3/29/2016.
 */
public class customArrayAdapterContacts extends ArrayAdapter<contactTemplateClass>{
    public customArrayAdapterContacts(Context context, int resource, List<contactTemplateClass> objects) {
        super(context, resource, objects);
    }

    private class ViewHolder{
        TextView userName;
        TextView userId;
        Button messagesButton;
        Button filesButton;
    }

    public View getView(final int position, View convertView, ViewGroup parent){
        ViewHolder viewHolder = null;

        contactTemplateClass row = getItem(position);

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if(convertView == null){
            convertView = inflater.inflate(R.layout.contacts_custom_layout, null);
            viewHolder = new ViewHolder();
            viewHolder.userName = (TextView) convertView.findViewById(R.id.userName);
            viewHolder.userId = (TextView) convertView.findViewById(R.id.userId);
            viewHolder.messagesButton = (Button) convertView.findViewById(R.id.toMessages);
            viewHolder.filesButton = (Button) convertView.findViewById(R.id.toFiles);

            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.userName.setText(row.getNombre());
        viewHolder.userId.setText(row.getUserName());


        viewHolder.messagesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // DO what you want to recieve on btn click there.

                contactTemplateClass row = getItem(position);
                Toast toast = Toast.makeText(v.getContext(),
                        "Item " + position,
                        Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();

                Intent i = new Intent(v.getContext(),messages.class);
                i.putExtra("id", row.getUserId());
                i.putExtra("name",row.getNombre());
                v.getContext().startActivity(i);

            }
        });

        viewHolder.filesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // DO what you want to recieve on btn click there.

                contactTemplateClass row = getItem(position);
                Toast toast = Toast.makeText(v.getContext(),
                        "Item " + position,
                        Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();

                Intent i = new Intent(v.getContext(),files.class);
                i.putExtra("id", row.getUserId());
                i.putExtra("name",row.getNombre());
                v.getContext().startActivity(i);
            }
        });

        return convertView;
    }
}

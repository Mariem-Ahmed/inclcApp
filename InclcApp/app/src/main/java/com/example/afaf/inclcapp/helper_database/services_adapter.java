package com.example.afaf.inclcapp.helper_database;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.afaf.inclcapp.R;

import java.util.List;

/**
 * Created by enterprise on 25/04/17.
 */

public class services_adapter extends ArrayAdapter<services_model> {
    protected Context mContext;
    protected List<services_model> mServices;


    public services_adapter(Context context, List<services_model> services, services_helper db) {
        super(context, R.layout.services_row, services);
        mServices = services;
        mContext = context;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.services_row, null);
            holder = new ViewHolder();
            holder.servicename = (TextView) convertView.findViewById(R.id.Name);
            holder.unitno = (TextView) convertView.findViewById(R.id.sessionno);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final services_model sModel = mServices.get(position);

        holder.servicename.setText(sModel.getServiceName());


        holder.unitno.setText(sModel.getUnitnum());

        holder.action = (LinearLayout) convertView.findViewById(R.id.linearAction);

        return convertView;

    }

    private static class ViewHolder {
        TextView servicename;
        TextView unitno;
        LinearLayout action;
    }


}


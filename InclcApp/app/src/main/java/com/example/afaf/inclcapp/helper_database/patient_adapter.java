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
import java.util.StringTokenizer;

/**
 * Created by enterprise on 23/04/17.
 */

public class patient_adapter extends ArrayAdapter<patient_model> {
    protected Context mContext;
    protected List<patient_model> mPatients;


    public patient_adapter(Context context, List<patient_model> patients, patient_helper db) {
        super(context, R.layout.patient_row, patients);
        mPatients = patients;
        mContext = context;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.patient_row, null);
            holder = new ViewHolder();
            holder.custname = (TextView) convertView.findViewById(R.id.Name);
            holder.sqe = (TextView) convertView.findViewById(R.id.seqq);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final patient_model patientModel = mPatients.get(position);
        String s = null, ss;
        StringTokenizer st = new StringTokenizer(patientModel.getPatientName(), "-");

        s = st.nextToken();

        holder.custname.setText(s);


        holder.sqe.setText(patientModel.getSqe());

        holder.action = (LinearLayout) convertView.findViewById(R.id.linearAction);

        return convertView;

    }

    private static class ViewHolder {
        TextView custname;
        TextView sqe;
        LinearLayout action;
    }


}

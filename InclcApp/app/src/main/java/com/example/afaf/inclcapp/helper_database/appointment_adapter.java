package com.example.afaf.inclcapp.helper_database;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.afaf.inclcapp.MainActivity;
import com.example.afaf.inclcapp.R;
import com.example.afaf.inclcapp.appointment_activity;

import org.json.JSONException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by enterprise on 11/04/17.
 */

public class appointment_adapter extends ArrayAdapter<appointment_Model> {
    protected Context mContext;
    protected List<appointment_Model> mAppointments;


    public appointment_adapter(Context context, List<appointment_Model> appointments, appointment_helper db) {
        super(context, R.layout.appoint_row, appointments);
        mAppointments = appointments;
        mContext = context;

    }

    public static String getDate(long milliSeconds, String dateFormat) {
        // Create a DateFormatter object for displaying date in specified format.
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.appoint_row, null);
            holder = new ViewHolder();
            holder.clinicname = (TextView) convertView.findViewById(R.id.Name);
            holder.date = (TextView) convertView.findViewById(R.id.Date);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final appointment_Model appModel = mAppointments.get(position);

        holder.clinicname.setText(appModel.getClinic());
        SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd");
        Date d = null;
        try {
            d = output.parse(appModel.getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long millisecond = d.getTime();

        String currentDate = getDate(millisecond, "yyyy-MM-dd");

        holder.date.setText(currentDate);

        holder.action = (LinearLayout) convertView.findViewById(R.id.linearAction);

        holder.action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity.fabflag = false;

                String appDate = appModel.getDate();
                String appDoctor = appModel.getDoctor();
                String appClinic = appModel.getClinic();
                String appStartTime = appModel.getStartTime();
                String appEndTime = appModel.getEndTime();
                String appNumOfCust = appModel.getNumOfCustomer();
                String appNumOfServedCust = appModel.getNumOfServedCustomer();
                String appStatus = appModel.getAppointmentStatus();

                Intent i = new Intent(getContext(), appointment_activity.class);

                i.putExtra("id", appModel.getId() + "");
                i.putExtra("appDate", appDate);
                i.putExtra("appDoctor", appDoctor);
                i.putExtra("appClinic", appClinic);
                i.putExtra("appStartTime", appStartTime);
                i.putExtra("appEndTime", appEndTime);
                i.putExtra("appNumOfCust", appNumOfCust);
                i.putExtra("appNumOfServedCust", appNumOfServedCust);
                i.putExtra("appStatus", appStatus);
                i.putExtra("appId", appModel.getAppointmentId());



                getContext().startActivity(i);

            }
        });



        return convertView;

    }

    private static class ViewHolder {
        TextView clinicname;
        TextView date;
        LinearLayout action;
    }
}

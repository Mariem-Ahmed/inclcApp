package com.example.afaf.inclcapp.helper_database;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.afaf.inclcapp.R;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * Created by enterprise on 22/05/17.
 */

public class credit_adapter extends ArrayAdapter<credit_model> {
    protected Context mContext;
    protected List<credit_model> mcredit;
    SharedPreferences sharedpreferences;
    String uRl = "";

    public credit_adapter(Context context, List<credit_model> credits, credit_helper db) {
        super(context, R.layout.credit_row, credits);
        mcredit = credits;
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.credit_row, null);
            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.Name);
            holder.date = (TextView) convertView.findViewById(R.id.date);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final credit_model pModel = mcredit.get(position);

        holder.name.setText(pModel.getcAmount());

        SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd");
        Date d = null;
        try {
            d = output.parse(pModel.getcDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long millisecond = d.getTime();

        String currentDate = getDate(millisecond, "yyyy-MM-dd");

        holder.date.setText(currentDate);


        holder.action = (LinearLayout) convertView.findViewById(R.id.linearAction);

        return convertView;

    }

    private static class ViewHolder {
        TextView name;
        TextView date;
        LinearLayout action;
    }


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}

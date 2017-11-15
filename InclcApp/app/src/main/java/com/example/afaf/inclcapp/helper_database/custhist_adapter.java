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

import org.json.JSONException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * Created by enterprise on 25/04/17.
 */

public class custhist_adapter extends ArrayAdapter<custhist_model> {
    protected Context mContext;
    protected List<custhist_model> mhist;
    SharedPreferences sharedpreferences;
    String uRl = "";
    public static final String MyPREFERENCES = "MyPrefs";

    public custhist_adapter(Context context, List<custhist_model> custhis, custhist_helper db) {
        super(context, R.layout.custhist_row, custhis);
        mhist = custhis;
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.custhist_row, null);
            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.Name);
            holder.notes = (TextView) convertView.findViewById(R.id.notes);
            holder.date = (TextView) convertView.findViewById(R.id.date);
            holder.image = (ImageView) convertView.findViewById(R.id.photo);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final custhist_model pModel = mhist.get(position);

        holder.name.setText(pModel.getServicename());

        holder.notes.setText(pModel.getNotes());

        SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd");
        Date d = null;
        try {
            d = output.parse(pModel.getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long millisecond = d.getTime();

        String currentDate = getDate(millisecond, "yyyy-MM-dd");

        holder.date.setText(currentDate);
        sharedpreferences = getContext().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        uRl = sharedpreferences.getString("URL", null);

        // put image to imageview
//            for (int i=0; i<mhist.size(); i++) {
//                custhist_model m = null;
//                custhist_helper dp= new custhist_helper(getContext());
//                try {
//                   m = dp.readCustHist(i+1);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
            //   if (!pModel.getPhoto().equals("")) {

                    Picasso.with(getContext())
                            .load(uRl + "/ws/com.opentus.inshape.clinic.getphoto?custhistid=" + pModel.getCusthistId())
                            .resize(0, 450)
                            .into(holder.image);
              //  }

   // }

        holder.action = (LinearLayout) convertView.findViewById(R.id.linearAction);

        return convertView;

    }

    private static class ViewHolder {
        TextView name;
        TextView notes;
        TextView date;
        ImageView image;
        LinearLayout action;
    }


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}

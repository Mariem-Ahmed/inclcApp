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
 * Created by enterprise on 24/04/17.
 */

public class product_adapter extends ArrayAdapter<product_model> {
    protected Context mContext;
    protected List<product_model> mProducts;


    public product_adapter(Context context, List<product_model> products, product_helper db) {
        super(context, R.layout.product_row, products);
        mProducts = products;
        mContext = context;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.product_row, null);
            holder = new ViewHolder();
            holder.prodname = (TextView) convertView.findViewById(R.id.Name);
            holder.unitno = (TextView) convertView.findViewById(R.id.unitno);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final product_model pModel = mProducts.get(position);

        holder.prodname.setText(pModel.getProductName());


        holder.unitno.setText(pModel.getUnitno());

        holder.action = (LinearLayout) convertView.findViewById(R.id.linearAction);

        return convertView;

    }

    private static class ViewHolder {
        TextView prodname;
        TextView unitno;
        LinearLayout action;
    }


}

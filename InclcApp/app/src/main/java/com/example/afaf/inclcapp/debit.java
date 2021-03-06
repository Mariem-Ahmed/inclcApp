package com.example.afaf.inclcapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v4.app.ListFragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;


import com.example.afaf.inclcapp.helper_database.debit_model;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.List;

public class debit extends ListFragment {
    // login
    public static final String LoginPREFERENCES = "LoginPrefs";
    public static final String MyPREFERENCES = "MyPrefs";
    String service = "";


    SharedPreferences sharedpreferencesLogin;
    String uname = "";
    String passw = "";
    String userId = "";
    String BP = "";
    SharedPreferences sharedpreferences;
    String uRl = "";

    SwipeRefreshLayout swipeContainer;

    List<debit_model> list;
    debit_helper db;

    String textName = "";
    String dDate = "";
    String dAmount = "";
    String dAppointmentID = "";
    String dAppointmentName = "";
    String dProductID = "";
    String dProductName = "";
    String dNetPrice = "";
    String dUnitNo = "";
    String dId = "";

    public debit() {
    }

    @SuppressLint("ValidFragment")
    public debit(String s, Object o) {
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.debit, container, false);

        return v;
    }

    // ---------------------------------------------------------------------------------------
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        db= new debit_helper(getActivity());

        sharedpreferences = getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        uRl = sharedpreferences.getString("URL", null);

        sharedpreferencesLogin = getActivity().getSharedPreferences(LoginPREFERENCES, Context.MODE_PRIVATE);
        uname = sharedpreferencesLogin.getString("uName", null);
        passw = sharedpreferencesLogin.getString("passWord", null);
        userId = sharedpreferencesLogin.getString("userId", null);
        BP = sharedpreferencesLogin.getString("BP", null);

        if (isNetworkAvailable() == true) {
            AsyncCall task = new AsyncCall();
            task.execute();


            list = db.getAllDebits();
            debit_adapter adapter = new debit_adapter(getListView().getContext(), list, db);
            setListAdapter(adapter);


        } else {
            list = db.getAllDebits();
            debit_adapter adapter = new debit_adapter(getListView().getContext(), list, db);
            setListAdapter(adapter);

        }
        // Lookup the swipe container view
        swipeContainer = (SwipeRefreshLayout) getActivity().findViewById(R.id.swipeContainerdebit);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                /// web service action ////
                if (isNetworkAvailable() == true) {
                    AsyncCall task = new AsyncCall();
                    task.execute();


                    list = db.getAllDebits();
                    debit_adapter adapter = new debit_adapter(getListView().getContext(), list, db);
                    setListAdapter(adapter);

                } else {
                    // Configure the refreshing colors

                    list = db.getAllDebits();
                    debit_adapter adapter = new debit_adapter(getListView().getContext(), list, db);
                    setListAdapter(adapter);


                    Toast.makeText(getActivity(), "Could not update debits", Toast.LENGTH_LONG).show();

                }

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeContainer.setRefreshing(false);
                    }
                }, 2000);

            }
        });


        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
    }

    // get doctor credit
    public HttpURLConnection createGetConnection(String wsPart, String method) throws Exception {
        Authenticator.setDefault(new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(uname, passw.toCharArray());
            }
        });

        final URL url = new URL(uRl + wsPart);
        final HttpURLConnection hc = (HttpURLConnection) url.openConnection();
        hc.setRequestMethod(method);
        hc.setAllowUserInteraction(false);
        hc.setDefaultUseCaches(false);
        hc.setDoOutput(true);
        hc.setDoInput(true);
        hc.setInstanceFollowRedirects(true);
        hc.setUseCaches(false);
        hc.setRequestProperty("Content-Type", "text/xml");
        return hc;
    }


    public String doTestGetRequest(String wsPart) {
        try {


            final HttpURLConnection hc = createGetConnection(wsPart, "GET");
            hc.connect();
            //  final SAXReader sr = new SAXReader();
            final InputStream is = hc.getInputStream();
            final StringBuilder sb = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            converter1(sb.toString());
            // -Ahmed Ali Eldeeb----------------------------------------------------------------------------------------

        } catch (Exception e) {

        }

        return null;
    }

    public void converter1(String content) throws XmlPullParserException, IOException {
        db.onUpgrade(db.getWritableDatabase(), 1, 2);
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        factory.setNamespaceAware(true);
        XmlPullParser xpp = factory.newPullParser();
        // Log.d("aaa",content);

        xpp.setInput(new StringReader(content));
        int eventType = xpp.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {

            if (eventType == XmlPullParser.START_TAG) {
                textName = xpp.getName();

                if (textName.startsWith("product")) {

                    if (xpp.getAttributeCount() != 1) {
                        dProductName = xpp.getAttributeValue(2);
                        dProductID = xpp.getAttributeValue(0);

                    } else {
                        dProductName = "";
                        dProductID = "";
                    }

                }
                if (textName.startsWith("appointment")) {

                    if (xpp.getAttributeCount() != 1) {
                        dAppointmentName = xpp.getAttributeValue(2);
                        dAppointmentID = xpp.getAttributeValue(0);

                    } else {
                        dAppointmentName = "";
                        dAppointmentID = "";

                    }


                }


            } else if (eventType == XmlPullParser.TEXT) {

                if (textName.equals("id") && !xpp.getText().startsWith("\n")) {

                    if (!xpp.getText().startsWith("\n")) {
                        dId = xpp.getText();
                    } else {
                        dId = "";
                    }
                }

                if (textName.equals("date") && !xpp.getText().startsWith("\n")) {
                    //  xx[i] = xpp.getText();

                    if (!xpp.getText().startsWith("\n")) {
                        dDate = xpp.getText();
                    } else {
                        dDate = "";
                    }

                }

                if (textName.equals("netUnitPrice") && !xpp.getText().startsWith("\n")) {
                    //  xx[i] = xpp.getText();

                    if (!xpp.getText().startsWith("\n")) {
                        dNetPrice = xpp.getText();
                    } else {
                        dNetPrice = "";
                    }

                }

                if (textName.equals("amount") && !xpp.getText().startsWith("\n")) {
                    //  xx[i] = xpp.getText()

                    if (!xpp.getText().startsWith("\n")) {
                        dAmount = xpp.getText();
                    } else {
                        dAmount = "";
                    }
                }
                if (textName.equals("unitNo") && !xpp.getText().startsWith("\n")) {
                    //  xx[i] = xpp.getText()

                    if (!xpp.getText().startsWith("\n")) {
                        dUnitNo = xpp.getText();
                    } else {
                        dUnitNo = "";
                    }
                    db.createDebit(dDate, dAmount, dAppointmentID, dAppointmentName, dProductID, dProductName, dNetPrice,dUnitNo,dId );

                    textName = "";

                }

            }
            eventType = xpp.next();
        }

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    // get product
    private class AsyncCall extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {

            doTestGetRequest("/ws/com.opentus.inshape.clinic.getdebit?docid=" + BP);

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {


        }

        @Override
        protected void onPreExecute() {


        }

        @Override
        protected void onProgressUpdate(Void... values) {

        }
    }

}

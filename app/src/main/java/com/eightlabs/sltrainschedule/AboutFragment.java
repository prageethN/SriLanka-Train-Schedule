package com.eightlabs.sltrainschedule;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.eightlabs.sltrainschedule.util.AppGlobalData;
import com.eightlabs.sltrainschedule.util.IntentCaller;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class AboutFragment extends Fragment {

    View rootView;

    TextView lnkFeedback,lnkRateApp,txtAppDescription;

    IntentCaller intentCaller;

    public AboutFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_about, container, false);

        initializeObjects();
        initializeUIElements();

        return rootView;
    }

    void initializeObjects(){

        intentCaller = new IntentCaller();
    }
    void initializeUIElements(){

        lnkFeedback = (TextView)rootView.findViewById(R.id.lnkFeedback);
        lnkFeedback.setText(Html.fromHtml(getString(R.string.lnk_feedback)));
        Linkify.addLinks(lnkFeedback, Linkify.ALL);
        lnkFeedback.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                intentCaller.sendEmail(getActivity(), new String [] {AppGlobalData.STR_SUPPORT_EMAIL},"","",AppGlobalData.STR_EMAIL_CHOOSER_HEADER);
            }

        });

        lnkRateApp = (TextView)rootView.findViewById(R.id.lnkRateApp);
        lnkRateApp.setText(Html.fromHtml(getString(R.string.lnk_rate_app)));
        Linkify.addLinks(lnkRateApp, Linkify.ALL);
        lnkRateApp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                intentCaller.openMarketPlace(getActivity(), "market://details?id=" + getActivity().getPackageName());
            }

        });

        txtAppDescription = (TextView)rootView.findViewById(R.id.txtAppDescription);
        txtAppDescription.setText(readTextFile());

     }
    public  String readTextFile()
    {
        InputStream inputStream = getActivity().getResources().openRawResource(R.raw.app_description);

        InputStreamReader inputReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputReader);
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        try
        {
            while (( line = bufferedReader.readLine()) != null)
            {
                stringBuilder.append(line);
                stringBuilder.append('\n');
            }
        }
        catch (IOException e)
        {
            return null;
        }
        return stringBuilder.toString();
    }
}

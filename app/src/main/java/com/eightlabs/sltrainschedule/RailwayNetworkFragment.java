package com.eightlabs.sltrainschedule;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

public class RailwayNetworkFragment extends Fragment {

    View rootView;

    WebView wvRailwayNetwork;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		rootView = inflater.inflate(R.layout.fragment_railway_network, container, false);

        initializeUIElements();

		return rootView;
	}

    void initializeUIElements(){

        wvRailwayNetwork=(WebView)rootView.findViewById(R.id.wvRailwayNetwork);
        wvRailwayNetwork.loadUrl("file:///android_asset/railway_network_map.htm");
    }
}

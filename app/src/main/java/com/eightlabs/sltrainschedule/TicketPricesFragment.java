package com.eightlabs.sltrainschedule;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TicketPricesFragment extends Fragment {
	
	public TicketPricesFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_ticket_prices, container, false);
         
        return rootView;
    }
}

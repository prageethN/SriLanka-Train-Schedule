package com.eightlabs.sltrainschedule;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class SearchFragment extends Fragment {

    private View rootView;

    private EditText txtSearchDate;
    private EditText txtStartTime;
    private EditText txtEndTime;

    private RadioButton rbFullSchedule,rbNextSchedule;

    private CheckBox cbTimeFilter;


    Date startTime = new Date();
    Date endTime = new Date();

    Calendar searchDate = Calendar.getInstance();

    public SearchFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_search, container, false);

        initializeUIElements();

        return rootView;
    }

    void initializeUIElements() {

        // ========================================================================================= Date Picker
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                searchDate.set(Calendar.YEAR, year);
                searchDate.set(Calendar.MONTH, monthOfYear);
                searchDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                setSearchDate(1);
            }

        };


        txtSearchDate = (EditText) rootView.findViewById(R.id.txtSearchDate);
        txtSearchDate.setEnabled(false);
        txtSearchDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), date, searchDate
                        .get(Calendar.YEAR), searchDate.get(Calendar.MONTH),
                        searchDate.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.setTitle("Select Date");
                datePickerDialog.show();
            }
        });
        setSearchDate(0);

        // ========================================================================================= Time Pickers
        final TimePickerDialog.OnTimeSetListener timeStart = new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                // TODO Auto-generated method stub
                startTime.setHours(hourOfDay);
                startTime.setMinutes(minute);
                setStartTime(1);
            }
        };

        txtStartTime = (EditText) rootView.findViewById(R.id.txtStartTime);
        txtStartTime.setEnabled(false);
        txtStartTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),timeStart,startTime.getHours(),startTime.getMinutes(),false);
                timePickerDialog.setTitle("Select Time");
                timePickerDialog.show();
            }
        });
        setStartTime(0);
        // -----------------------------------------------------------------------------------------
        final TimePickerDialog.OnTimeSetListener timeEnd = new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                // TODO Auto-generated method stub
                endTime.setHours(hourOfDay);
                endTime.setMinutes(minute);
                setEndTime(1);
            }
        };
        txtEndTime = (EditText) rootView.findViewById(R.id.txtEndTime);
        txtEndTime.setEnabled(false);
        txtEndTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),timeEnd,endTime.getHours(),endTime.getMinutes(),false);
                timePickerDialog.setTitle("Select Time");
                timePickerDialog.show();

            }
        });
        setEndTime(0);

        // ========================================================================================= Radio Button

        rbFullSchedule = (RadioButton)rootView.findViewById(R.id.rbFullSchedule);
        rbFullSchedule.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                boolean checked = ((RadioButton) v).isChecked();
                if (checked){
                    setSearchDate(0);
                    setEndTime(0);
                    setStartTime(2);

                    cbTimeFilter.setChecked(false);
                    cbTimeFilter.setEnabled(true);
                    txtSearchDate.setEnabled(true);
                    txtStartTime.setEnabled(true);
                    txtEndTime.setEnabled(true);
                }

            }
        });
        // -----------------------------------------------------------------------------------------
        rbNextSchedule = (RadioButton)rootView.findViewById(R.id.rbNextSchedule);
        rbNextSchedule.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                boolean checked = ((RadioButton) v).isChecked();
                if(checked) {
                    setSearchDate(0);
                    setStartTime(0);

                    cbTimeFilter.setChecked(true);
                    cbTimeFilter.setEnabled(false);
                    txtSearchDate.setEnabled(false);
                    txtStartTime.setEnabled(false);
                    txtEndTime.setEnabled(false);
                }
            }
        });

        // ========================================================================================= Check Box

        cbTimeFilter = (CheckBox)rootView.findViewById(R.id.cbTimeFilter);
        cbTimeFilter.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

            }
        });

    }

    private void setSearchDate (int option) {

        String myFormat = "dd, MMMM, yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        switch ( option)
        {
            case 0:
                Date date = new Date();
                txtSearchDate.setText(sdf.format(date));
                break;
            case 1:
                txtSearchDate.setText(sdf.format(searchDate.getTime()));
        }

    }
    private void setStartTime(int option) {

        String myFormat = "hh:mm aa"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        switch ( option)
        {
            case 0:
                Date date = new Date();
                txtStartTime.setText(sdf.format(date));
                break;
            case 1:
                txtStartTime.setText(sdf.format(startTime));
                cbTimeFilter.setChecked(true);
                break;
            case 2:
                startTime.setHours(0);
                startTime.setMinutes(0);
                txtStartTime.setText(sdf.format(startTime));
                break;
        }

    }

    private void setEndTime(int option) {

        String myFormat = "hh:mm aa"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        switch ( option)
        {
            case 0:
                endTime.setHours(23);
                endTime.setMinutes(59);
                txtEndTime.setText(sdf.format(endTime.getTime()));
                break;
            case 1:
                txtEndTime.setText(sdf.format(endTime.getTime()));
                cbTimeFilter.setChecked(true);
        }

    }

}

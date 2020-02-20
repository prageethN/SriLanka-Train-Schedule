package com.eightlabs.sltrainschedule.util;


import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

public class IntentCaller {

    public void sendEmail(Activity activity, String [] emailAddress,String subject,String text,String chooserHeader){
        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL, emailAddress);
        email.putExtra(Intent.EXTRA_SUBJECT, subject);
        email.putExtra(Intent.EXTRA_TEXT, text);
        activity.startActivity(Intent.createChooser(email, chooserHeader));
    }

    public void openMarketPlace(Activity activity,String link){
        Uri uri = Uri.parse(link);
        Intent marketPlace = new Intent(Intent.ACTION_VIEW, uri);
        try {
            activity.startActivity(marketPlace);
        }
        catch (ActivityNotFoundException e) {
            Toast.makeText(activity, "You don't have any app that can open this link", Toast.LENGTH_SHORT).show();
        }
        }
    }


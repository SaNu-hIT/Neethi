package com.example.work.cartapp.Extras.SessionManager;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by intellyelabs on 17/04/17.
 */

public class SessionManager {

    SharedPreferences preferences;
    Context context;
    SharedPreferences.Editor editor;
    public static final String PREFF_NAME = "NEETHI";
    int PRIVATE_MODE = 0;

    public SessionManager(Context context) {
        this.context = context;
        if (context != null) {
            preferences = context.getSharedPreferences(PREFF_NAME, PRIVATE_MODE);
            editor = preferences.edit();
        }


    }

    public void SaveCredentials(String username, String password) {
        editor.putString("USERNAME", username);
        editor.putString("PASSWORD", password);
        editor.commit();
    }

    public void SaveValuesInitial(Long Shop_Id, String Shop_Name, String GSTIN) {
        editor.putLong("Shop_Id", Shop_Id);
        editor.putString("Shop_Name", Shop_Name);
        editor.putString("GSTIN", GSTIN);
        editor.commit();
    }

    public String getUsername() {
        String user = preferences.getString("USERNAME", "1");
        return user;
    }

    public String getPassword() {
        String pass = preferences.getString("PASSWORD", "1");
        return pass;
    }


    public void SaveFireBasetoken(String save) {
        editor.putString("FIREBASE_TOKEN", save);
        editor.commit();

    }

    public String GetFireBasetoken() {
        String ss = preferences.getString("FIREBASE_TOKEN", "");
        return ss;

    }

    public Long getUserId() {
        Long ss = preferences.getLong("Shop_Id", 0);
        return ss;

    }

//    public void SaveUseId(String save) {
//        editor.putString("Shop_Id", save);
//        editor.commit();
//
//    }
    public String getCompany() {
        String Shop_Name = preferences.getString("Shop_Name", "");
        return Shop_Name;

    }

    public String getRole() {
        String ss = preferences.getString("ROLE", "");
        return ss;

    }

    public void SaveRole(String save) {
        editor.putString("ROLE", save);
        editor.commit();

    }

    public String getPId() {
        String ss = preferences.getString("PID", "");
        return ss;

    }

    public void setPId(String save) {
        editor.putString("PID", save);
        editor.commit();

    }


    public void ClearAll() {
        editor.clear();
        editor.commit();
    }


}



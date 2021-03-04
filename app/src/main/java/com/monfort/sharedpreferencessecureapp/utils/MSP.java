package com.monfort.sharedpreferencessecureapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;

public class MSP {

    public enum KEY {
        USER_RATE,
        FIRST_OPEN,
        VOLUME_ON,
        USER_NAME
    }

    public interface KEYS {
        final String USER_NAME = "USER_NAME";
        final String VOLUME_ON = "VOLUME_ON";
        final String FIRST_OPEN = "FIRST_OPEN";
        final String USER_RATE = "USER_RATE";
    }



    private static MSP instance;
    private SharedPreferences prefs;

    public SharedPreferences getPrefs() {
        return prefs;
    }

    public static MSP getInstance() {
        return instance;
    }

    private MSP(Context context, boolean isEncrypted) {


        try {
            if (!isEncrypted) {
                prefs = context.getApplicationContext().getSharedPreferences("APP_SP", Context.MODE_PRIVATE);
            } else {
                String masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC);
                prefs = EncryptedSharedPreferences.create(
                        "APP_SP",
                        masterKeyAlias,
                        context,
                        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
                );
            }
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * init the library
     * @param context of the application -try to init with Application context
     * @param isEncrypted true for encrypted file, default = false
     */
    public static void initHelper(Context context, boolean isEncrypted) {
        if (instance == null) {
            context = context.getApplicationContext();
            instance = new MSP(context, isEncrypted);
        }
    }

    /**
     * init the library, default Encrypted = false
     * @param context of the application -try to init with Application context
     */
    public static void initHelper(Context context) {
        initHelper(context, false);
    }

    public void putString(String KEY, String value) {
        prefs.edit().putString(KEY, value).apply();
    }

    public String getString(String KEY, String defValue) {
        return prefs.getString(KEY, defValue);
    }

    public void putDouble(String KEY, double defValue) {
        putString(KEY, String.valueOf(defValue));
    }

    public double getDouble(String KEY, double defValue) {
        return Double.parseDouble(getString(KEY, String.valueOf(defValue)));
    }


    public void putInt(KEY key, int value) {
        prefs.edit().putInt(key.name(), value).apply();
    }

    public int getInt(KEY key, int defValue) {
        return prefs.getInt(key.name(), defValue);
    }


    public <T> void putArray(String KEY, ArrayList<T> array) {
        String json = new Gson().toJson(array);
        prefs.edit().putString(KEY, json).apply();
    }

    public <T> ArrayList<T> getArray(String KEY, TypeToken typeToken) {
        // type token == new TypeToken<ArrayList<YOUR_CLASS>>() {}
        ArrayList<T> arr = null;
        try {
            arr = new Gson().fromJson(prefs.getString(KEY, ""), typeToken.getType());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return arr;
    }

    public <S, T> void putMap(String KEY, HashMap<S, T> map) {
        String json = new Gson().toJson(map);
        prefs.edit().putString(KEY, json).apply();
    }

    public <S, T> HashMap<S, T> getMap(String KEY, TypeToken typeToken) {
        // getMap(MySharedPreferencesV4.KEYS.SP_PLAYLISTS, new TypeToken<HashMap<String, Playlist>>() {});
        // type token == new TypeToken<ArrayList<YOUR_CLASS>>() {}
        HashMap<S, T> map = null;
        try {
            map = new Gson().fromJson(prefs.getString(KEY, ""), typeToken.getType());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return map;
    }
}

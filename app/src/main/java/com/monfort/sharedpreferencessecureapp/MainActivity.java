package com.monfort.sharedpreferencessecureapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.gson.reflect.TypeToken;
import com.monfort.sharedpreferencessecureapp.utils.MSP;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MSP.getInstance().putString("NAME1", "Ariel");
        String n1 = MSP.getInstance().getString("NAME2", "NA");
        Log.d("pttt", "n1 = " + n1);


        MSP.getInstance().putDouble("Age", 14.5);
        double d1 = MSP.getInstance().getDouble("Age", 0.0);
        Log.d("pttt", "d1 = " + d1);


        MSP.getInstance().putString(MSP.KEYS.USER_NAME, "Annabelle");
        MSP.getInstance().putString("sdfsdfdsfdsf", "Annabelle");
        Log.d("pttt", MSP.getInstance().getString(MSP.KEYS.USER_NAME, "NA"));

        MSP.getInstance().putInt(MSP.KEY.VOLUME_ON, 10);
        MSP.getInstance().getInt(MSP.KEY.VOLUME_ON, 0);


        ArrayList<Car> cars = new ArrayList<>();
        cars.add(new Car()
                .setModel("Mercedes")
                .setEngine(2500)
        );
        cars.add(new Car()
                .setModel("Mitsubishi")
                .setEngine(1400)
        );
        MSP.getInstance().putArray("CARS", cars);

        TypeToken typeToken = new TypeToken<ArrayList<Car>>() {};
        ArrayList<Car> cars2 = MSP.getInstance().getArray("CARS", typeToken);
        for (Car car : cars2) {
            Log.d("pttt", car.getModel() + "  -  " + car.getEngine());
        }

        MSP.getInstance().putMap("USERS", new HashMap<>());
        MSP.getInstance().getMap("USERS", new TypeToken<HashMap<String, Car>>() {});

    }
}
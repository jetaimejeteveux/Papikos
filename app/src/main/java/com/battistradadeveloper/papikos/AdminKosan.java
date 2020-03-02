package com.battistradadeveloper.papikos;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class AdminKosan extends AppCompatActivity {

        List<Kos> employeeList;
        SQLiteDatabase mDatabase;
        ListView listViewEmployees;
        KosAdapter adapter;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_admin_kosan);

            listViewEmployees = findViewById(R.id.listViewKos);
            employeeList = new ArrayList<>();

            //membuka database
            mDatabase = openOrCreateDatabase(MainActivity.DATABASE_NAME, MODE_PRIVATE, null);

            //method untuk mengoutput data
            showEmployeesFromDatabase();
        }

        private void showEmployeesFromDatabase() {

            //we used rawQuery(sql, selectionargs) for fetching all the employees
            Cursor cursor = mDatabase.rawQuery("SELECT * FROM employees", null);

            //
            if (cursor.moveToFirst()) {
                //looping through all the records
                do {
                    //pushing each record in the employee list
                    employeeList.add(new Kos(
                            cursor.getInt(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3),
                            cursor.getString(4),
                            cursor.getString(5),
                            cursor.getString(6),
                            cursor.getDouble(7)
                    ));
                } while (cursor.moveToNext());
            }
            //closing the cursor
            cursor.close();

            //creating the adapter object
            adapter = new KosAdapter(this, R.layout.itemview, employeeList);

            //adding the adapter to listview
            listViewEmployees.setAdapter(adapter);
        }

    }
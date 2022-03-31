package com.team6.quickcashteam6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Map;

public class EmployeePageActivity extends AppCompatActivity {

    ArrayList<IDPairs> allIDs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_page);
        Button viewJobsButton = findViewById(R.id.viewJobsButton);

        Intent intent = getIntent();
        MainActivity.employeeID = intent.getStringExtra("ID");
        if (MainActivity.employeeID.charAt(0) == '-') {
            MainActivity.employeeKey = MainActivity.employeeID;
        }
        else {
            findEmployeeKey();
        }

        viewJobsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent allJobsPage = new Intent(EmployeePageActivity.this, AllJobsActivity.class);
                startActivity(allJobsPage);
            }
        });
    }

    public void findEmployeeKey() {
        FirebaseDatabase firebase = FirebaseDatabase.getInstance();

        DatabaseReference ref = firebase.getReference().child("IDs");

        allIDs= new ArrayList<>();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                allIDs= collectPairs((Map<String,Object>) snapshot.getValue());
                for (IDPairs pair:allIDs){
                    if (pair.getUserID().equals(MainActivity.employeeID)){
                        MainActivity.employeeKey=pair.getDatabaseKey();
                        break;
                    }
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public ArrayList<IDPairs> collectPairs(Map<String,Object> data){
        ArrayList<IDPairs> IDs= new ArrayList<>();
        for (Map.Entry<String,Object> map:data.entrySet()){
            Map idMap=(Map) map.getValue();
            IDPairs pairs= new IDPairs((String) idMap.get("userID"),(String) idMap.get("databaseKey"));
            IDs.add(pairs);
        }
        return IDs;
    }
}

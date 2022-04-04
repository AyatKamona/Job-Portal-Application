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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public class EmployeePageActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    ArrayList<IDPairs> allIDs;
    String keyToSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_page);

        Button viewJobsButton = findViewById(R.id.viewJobsButton);

        Button currentJobButton = findViewById(R.id.currentJobButton);
        currentJobButton.setOnClickListener(this::onCurrentJobButtonClick);

        Intent intent = getIntent();
        MainActivity.employeeID = intent.getStringExtra("ID");

        if (MainActivity.employeeID.charAt(0) == '-') {
            MainActivity.employeeKey = MainActivity.employeeID;
            keyToSend = MainActivity.employeeID;
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

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();

        Button logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Intent reLogin = new Intent(EmployeePageActivity.this, MainActivity.class);
                startActivity(reLogin);
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

                allIDs= collectPairs((Map<String,Object>) Objects.requireNonNull(snapshot.getValue()));
                for (IDPairs pair:allIDs){
                    if (pair.getUserID().equals(MainActivity.employeeID)){
                        MainActivity.employeeKey=pair.getDatabaseKey();
                        keyToSend = pair.getDatabaseKey();

                        DatabaseReference getJobIDRef= FirebaseDatabase.getInstance().getReference().child("Employee").child(MainActivity.employeeKey);
                        getJobIDRef.addValueEventListener(new ValueEventListener() {

                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                Employee employee = snapshot.getValue(Employee.class);

                            //    assert employee != null;
                                if (employee.getCurrentJobID() != null) {
                                    MainActivity.currentJobID = employee.getCurrentJobID();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }

                        });

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

    public void onCurrentJobButtonClick(View view){

        if (MainActivity.currentJobID != null) {
            Intent currentJobPage = new Intent(EmployeePageActivity.this, currentJobActivity.class);
            currentJobPage.putExtra("employee", keyToSend);
            startActivity(currentJobPage);
        }

        else {
            Toast.makeText(EmployeePageActivity.this, "You do not have a current job.", Toast.LENGTH_SHORT).show();
        }
    }
}

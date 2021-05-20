package com.example.surfbuddy.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.surfbuddy.Adapter.AdapterMain;
import com.example.surfbuddy.R;
import com.example.surfbuddy.repository.UserRepository;
import com.firebase.ui.auth.AuthUI;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements AdapterMain.OnNoteListener {

    RecyclerView mainRecyclerView;

    String s1[], s2[];
    int images[] = {R.drawable.julo, R.drawable.kubaprofil, R.drawable.maksprofil, R.drawable.michalangeloprofil, R.drawable.raphaelprofil, R.drawable.donatello, R.drawable.leonardoprofil, R.drawable.rick, R.drawable.morty, R.drawable.ashwins};

    UserRepository userRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainRecyclerView = findViewById(R.id.mainRecyclerView);

        s1 = getResources().getStringArray(R.array.instructor_Names);
        s2 = getResources().getStringArray(R.array.surf_Types);

        AdapterMain adapterMain = new AdapterMain(this, s1, s2, images, this);
        mainRecyclerView.setAdapter(adapterMain);
        mainRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.home:
                        return true;
                    case R.id.Weather:
                        startActivity(new Intent(getApplicationContext(), WeatherActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.logout:
                        userRepository.signOut();
                }
                return false;
            }
        });

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null)
            Toast.makeText(this, "Welcome " + user.getDisplayName(), Toast.LENGTH_SHORT).show();
        else
            startLoginActivity();


    }

    private void startLoginActivity() {
        startActivity(new Intent(this, SignInActivity.class));
        finish();
    }


    @Override
    public void onNoteClick(int position) {
        Intent intent = new Intent(this, Portfolio.class);
        startActivity(intent);
    }
}
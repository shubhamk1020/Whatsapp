package com.shubhamk.myapplicationWhatsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shubhamk.myapplicationWhatsapp.Adapter.FragmentsAdapter;
import com.shubhamk.myapplicationWhatsapp.databinding.ActivityMainBinding;

import java.security.acl.Group;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding binding;
    FirebaseAuth auth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth = FirebaseAuth.getInstance();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");
        myRef.setValue("Hello world");


        binding.viewPager.setAdapter(new FragmentsAdapter(getSupportFragmentManager()));
        binding.tabLayout.setupWithViewPager(binding.viewPager);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String value = snapshot.getValue(String.class);
                Toast.makeText(MainActivity.this, "" +value, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){

            case R.id.newgroup:

                Intent intent = new Intent(MainActivity.this, GroupChatActivity.class);
                startActivity(intent);

                break;
            case R.id.broadcast:
                break;
            case R.id.liked:
                break;
            case R.id.stared:
                break;
            case R.id.payment:
                break;
            case R.id.Setting:
                Intent setting = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(setting);
                break;
            case R.id.logout:

                auth.signOut();
                Intent i = new Intent(MainActivity.this, SigninActivity.class);
                startActivity(i);

                break;
        }

        return true;
    }
}
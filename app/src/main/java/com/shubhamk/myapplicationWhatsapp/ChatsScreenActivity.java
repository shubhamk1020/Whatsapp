package com.shubhamk.myapplicationWhatsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.shubhamk.myapplicationWhatsapp.databinding.ActivityChatsScreenBinding;
import com.shubhamk.myapplicationWhatsapp.databinding.ActivityMainBinding;
import com.squareup.picasso.Picasso;

public class ChatsScreenActivity extends AppCompatActivity {

    ActivityChatsScreenBinding binding;
    FirebaseAuth auth;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatsScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth = FirebaseAuth.getInstance();
        getSupportActionBar().hide();


        database = FirebaseDatabase.getInstance();

        String senderid = auth.getUid();
        String receverid = getIntent().getStringExtra("userId");
        String profilePic = getIntent().getStringExtra("profilePic");
        String userName = getIntent().getStringExtra("userName");

        binding.usertxt.setText(userName);
        Picasso.get().load(profilePic).placeholder(R.drawable.whatsapp).into(binding.profileImage);

        // for Back button
        binding.backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ChatsScreenActivity.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}
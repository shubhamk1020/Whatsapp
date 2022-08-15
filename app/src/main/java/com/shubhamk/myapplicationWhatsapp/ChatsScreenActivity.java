package com.shubhamk.myapplicationWhatsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shubhamk.myapplicationWhatsapp.Adapter.ChatAdapter;
import com.shubhamk.myapplicationWhatsapp.Model.MessageModel;
import com.shubhamk.myapplicationWhatsapp.databinding.ActivityChatsScreenBinding;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.Date;


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

        final String senderId = auth.getUid();
        String receiverId = getIntent().getStringExtra("userId");
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

        ArrayList<MessageModel> messageModels = new ArrayList<>();

        final ChatAdapter chatAdapter = new ChatAdapter(messageModels ,this);
        binding.chatsRecyclerview.setAdapter(chatAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.chatsRecyclerview.setLayoutManager(layoutManager);


        final String senderRoom = senderId + receiverId;
        final String receiverRoom = receiverId + senderId;

        database.getReference().child("chats")
                .child(senderRoom)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        messageModels.clear();
                        for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                            MessageModel model = dataSnapshot.getValue(MessageModel.class);

                            model.setMessageId(snapshot.getKey());

                            messageModels.add(model);
                        }

                        chatAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        binding.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String message = binding.etMessage.getText().toString();
                final MessageModel model = new MessageModel(senderId , message);

                model.setTimestamp(new Date().getTime());
                binding.etMessage.setText("");

                database.getReference().child("chats")
                        .child(senderRoom)
                        .push().setValue(model)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                database.getReference().child("chats").child(receiverRoom)
                                        .push().setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {

                                    }
                                });
                            }
                        });
            }
        });

    }

}

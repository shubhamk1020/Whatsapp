package com.shubhamk.myapplicationWhatsapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shubhamk.myapplicationWhatsapp.ChatsScreenActivity;
import com.shubhamk.myapplicationWhatsapp.Model.Users;
import com.shubhamk.myapplicationWhatsapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {

    ArrayList<Users> list;
    Context context;

    //constructor


    public UsersAdapter(ArrayList<Users> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sampe_show_user,parent,false);
             return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Users users = list.get(position);
        Picasso.get().load(users.getProfilepic()).placeholder(R.drawable.meta).into(holder.img);
        holder.txtUser.setText(users.getUserName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ChatsScreenActivity.class);
                i.putExtra("userId",users.getUserId());
                i.putExtra("profilePic",users.getProfilepic());
                i.putExtra("userName",users.getUserName());
                context.startActivity(i);

            }
        });
        
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView txtUser, txtlast;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.profile_image);
            txtUser = itemView.findViewById(R.id.usertxt);
            txtlast = itemView.findViewById(R.id.lastmsg);


        }
    }

}

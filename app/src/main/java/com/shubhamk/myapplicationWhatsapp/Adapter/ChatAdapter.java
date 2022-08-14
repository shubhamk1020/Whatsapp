package com.shubhamk.myapplicationWhatsapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.shubhamk.myapplicationWhatsapp.Model.MessageModel;
import com.shubhamk.myapplicationWhatsapp.R;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter {

    ArrayList<MessageModel> messageModels;
    Context context;

    public ChatAdapter(ArrayList<MessageModel> messageModels, Context context) {
        this.messageModels = messageModels;
        this.context = context;
    }

    int SENDER_VIEW_TYPE = 1;
    int RECEIVER_VIEW_TYPE = 2;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       if(viewType == SENDER_VIEW_TYPE){
           View view = LayoutInflater.from(context).inflate(R.layout.sample_sender,parent,false);


           return new SenderViewHolder(view);
       }
        else
       {
           View view = LayoutInflater.from(context).inflate(R.layout.samle_receiver,parent,false);
           return new ReceiverViewHolder(view);
       }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        MessageModel messageModel = messageModels.get(position);
        if(holder.getClass() == SenderViewHolder.class){
            ((SenderViewHolder)holder).SenderMsg.setText(messageModel.getMessage());
        }
        else{
            ((ReceiverViewHolder)holder).receiverMsgtxt.setText(messageModel.getMessage());
        }

    }

    @Override
    public int getItemViewType(int position) {
        if(messageModels.get(position).getuId().equals(FirebaseAuth.getInstance().getUid()))
        {
            return SENDER_VIEW_TYPE;
        }
        else{
            return  RECEIVER_VIEW_TYPE;
        }

    }

    @Override
    public int getItemCount() {
        return messageModels.size();
    }

    public class ReceiverViewHolder extends RecyclerView.ViewHolder{
        TextView receiverMsgtxt, reciverTime;
        public ReceiverViewHolder(@NonNull View itemView) {
            super(itemView);
            receiverMsgtxt = itemView.findViewById(R.id.receivertxt);
            reciverTime = itemView.findViewById(R.id.receivertime);
        }
    }
    public class SenderViewHolder extends RecyclerView.ViewHolder{
        TextView SenderMsg, SenderTime;
        public SenderViewHolder(@NonNull View itemView) {
            super(itemView);
            SenderMsg = itemView.findViewById(R.id.sendetxt);
            SenderTime = itemView.findViewById(R.id.sentdertime);
        }
    }
}

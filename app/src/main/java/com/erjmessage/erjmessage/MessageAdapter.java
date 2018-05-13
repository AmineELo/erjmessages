package com.erjmessage.erjmessage;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {

    private List<Message> messageList;
    private Context context;

    public MessageAdapter(List<Message> messageList, Context context) {
        this.messageList = messageList;
        this.context = context;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_item, parent, false);
        return new MessageViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {

        Message message = messageList.get(position);

        //split the full name by the middle space
        String fullName[] = message.getSender().split(" ");
        String firstChar = fullName[0].substring(0, 1) + fullName[1].substring(0, 1);


        //create an image drawable with the first char of the sender's name
        TextDrawable textDrawable = TextDrawable.builder().buildRound(firstChar, Color.GRAY);

        holder.senderPicture.setImageDrawable(textDrawable);
        holder.messageSender.setText(message.getSender());
        holder.messageSubject.setText(message.getMessageSubject());
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    class MessageViewHolder extends RecyclerView.ViewHolder{

        private ImageView senderPicture;
        private TextView messageSubject, messageSender;

        private MessageViewHolder(View itemView) {
            super(itemView);

            senderPicture = itemView.findViewById(R.id.sender_picture);
            messageSubject = itemView.findViewById(R.id.message_subject);
            messageSender = itemView.findViewById(R.id.sender_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Message message = messageList.get(getAdapterPosition());
                    Intent intent = new Intent(context, MessageActivity.class);

                    intent.putExtra("sender", message.getSender());
                    intent.putExtra("receiver", message.getReceiver());
                    intent.putExtra("message_subject", message.getMessageSubject());
                    intent.putExtra("message_body", message.getMessageBody());
                    intent.putExtra("message_timestamp", message.getMessageTimeStamp());

                    context.startActivity(intent);
                }
            });
        }
    }
}

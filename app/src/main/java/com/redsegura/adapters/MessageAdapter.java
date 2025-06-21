package com.redsegura.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.redsegura.models.Message;
import com.redsegura.redseguraapp.R;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    private List<Message> messageList;

    public MessageAdapter(List<Message> messages) {
        this.messageList = messages;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView sender, content;

        public ViewHolder(View v) {
            super(v);
            sender = v.findViewById(R.id.tv_sender);
            content = v.findViewById(R.id.tv_message);
        }
    }

    @Override
    public MessageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Message m = messageList.get(position);
        holder.sender.setText(m.getSender());
        holder.content.setText(m.getContent());
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }
}

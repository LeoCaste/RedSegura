package com.redsegura.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.redsegura.models.Message;
import com.redsegura.redseguraapp.R;
import java.util.List;

public class CommunityAdapter extends RecyclerView.Adapter<CommunityAdapter.ViewHolder> {

    private List<Message> messageList;

    public CommunityAdapter(List<Message> messages) {
        this.messageList = messages;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView user, message;

        public ViewHolder(View v) {
            super(v);
            user = v.findViewById(R.id.tv_user);
            message = v.findViewById(R.id.tv_message);
        }
    }

    @Override
    public CommunityAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_community_message, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Message m = messageList.get(position);
        holder.user.setText(m.getSender());
        holder.message.setText(m.getContent());
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }
}

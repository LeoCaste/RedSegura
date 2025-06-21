package com.redsegura.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.BaseAdapter;
import com.redsegura.R;
import com.redsegura.models.MessageModel;
import java.util.List;

public class ChatAdapter extends BaseAdapter {

    private Context context;
    private List<MessageModel> messages;

    public ChatAdapter(Context context, List<MessageModel> messages) {
        this.context = context;
        this.messages = messages;
    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Object getItem(int position) {
        return messages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MessageModel message = messages.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_message, parent, false);
        }

        TextView senderText = convertView.findViewById(R.id.tv_sender);
        TextView messageText = convertView.findViewById(R.id.tv_message);

        if (message.isFromAI()) {
            senderText.setText("IA:");
        } else {
            senderText.setText(message.getSender() + ":");
        }

        messageText.setText(message.getContent());

        return convertView;
    }
}

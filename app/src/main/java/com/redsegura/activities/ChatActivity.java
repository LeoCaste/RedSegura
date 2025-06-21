package com.redsegura.activities;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.redsegura.R;
import com.redsegura.adapters.ChatAdapter;
import com.redsegura.models.MessageModel;
import com.redsegura.services.ChatService;
import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private ListView chatListView;
    private EditText inputMessage;
    private ImageButton sendButton;
    private ChatAdapter chatAdapter;
    private List<MessageModel> messageList = new ArrayList<>();
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        chatListView = findViewById(R.id.chatListView);
        inputMessage = findViewById(R.id.editTextMessage);
        sendButton = findViewById(R.id.buttonSend);

        chatAdapter = new ChatAdapter(this, messageList);
        chatListView.setAdapter(chatAdapter);

        username = getIntent().getStringExtra("username");

        sendButton.setOnClickListener(v -> {
            String msgText = inputMessage.getText().toString().trim();
            if (!msgText.isEmpty()) {
                ChatService.sendMessage(username, msgText, success -> {
                    if (success) {
                        messageList.add(new MessageModel(username, msgText, System.currentTimeMillis()));
                        chatAdapter.notifyDataSetChanged();
                        inputMessage.setText("");
                    }
                });
            }
        });

        // Aquí puedes implementar el polling para recibir mensajes
    }
}

package com.redsegura.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.redsegura.R;
import com.redsegura.adapters.ChatAdapter;
import com.redsegura.models.MessageModel;
import com.redsegura.services.ChatService;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private ListView chatListView;
    private EditText inputMessage;
    private ImageButton sendButton;
    private ChatAdapter chatAdapter;
    private final List<MessageModel> messageList = new ArrayList<>();
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
                        runOnUiThread(() -> {
                            messageList.add(new MessageModel(username, msgText, System.currentTimeMillis(), false));
                            chatAdapter.notifyDataSetChanged();
                            inputMessage.setText("");
                        });
                    }
                });
            }
        });

        startMessagePolling();
    }

    private void startMessagePolling() {
        new Thread(() -> {
            while (true) {
                try {
                    ChatService.getMessages(username, response -> {
                        if (response != null) {
                            runOnUiThread(() -> {
                                messageList.clear();
                                for (int i = 0; i < response.length(); i++) {
                                    try {
                                        JSONObject msg = response.getJSONObject(i);
                                        String sender = msg.getString("sender");
                                        String content = msg.getString("message");
                                        long timestamp = System.currentTimeMillis(); // Ajusta si el backend devuelve timestamp real
                                        boolean fromAI = msg.optBoolean("fromAI", false);
                                        messageList.add(new MessageModel(sender, content, timestamp, fromAI));
                                    } catch (Exception e) {
                                        Log.e("ChatPolling", "Error al parsear mensaje", e);
                                    }
                                }
                                chatAdapter.notifyDataSetChanged();
                            });
                        }
                    });

                    Thread.sleep(10000); // Esperar 10 segundos
                } catch (Exception e) {
                    Log.e("ChatPolling", "Error en polling", e);
                }
            }
        }).start();
    }
}

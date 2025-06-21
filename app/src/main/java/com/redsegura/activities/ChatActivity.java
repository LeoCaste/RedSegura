package com.redsegura.activities;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.redsegura.R;
import com.redsegura.adapters.ChatAdapter;
import com.redsegura.models.MessageModel;
import com.redsegura.services.ChatService;

import org.json.JSONArray;
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

    private final Handler handler = new Handler(Looper.getMainLooper());
    private final Runnable pollingRunnable = new Runnable() {
        @Override
        public void run() {
            ChatService.getMessages(username, jsonArray -> {
                if (jsonArray != null) {
                    messageList.clear();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject obj = jsonArray.optJSONObject(i);
                        if (obj != null) {
                            String sender = obj.optString("sender");
                            String receiver = obj.optString("receiver");
                            String content = obj.optString("message");
                            long timestamp = Long.parseLong(obj.optString("timestamp"));

                            messageList.add(new MessageModel(sender, receiver, content, timestamp, false));
                        }
                    }
                    runOnUiThread(() -> chatAdapter.notifyDataSetChanged());
                }
            });

            handler.postDelayed(this, 5000); // Repite cada 5 segundos
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        chatListView = findViewById(R.id.chatListView);
        inputMessage = findViewById(R.id.editTextMessage);
        sendButton = findViewById(R.id.buttonSend);

        username = getIntent().getStringExtra("username");

        chatAdapter = new ChatAdapter(this, messageList);
        chatListView.setAdapter(chatAdapter);

        sendButton.setOnClickListener(v -> {
            String msgText = inputMessage.getText().toString().trim();
            if (!msgText.isEmpty()) {
                ChatService.sendMessage(username, msgText, success -> {
                    if (success) {
                        messageList.add(new MessageModel(username, "destinatario", msgText, System.currentTimeMillis(), false));
                        chatAdapter.notifyDataSetChanged();
                        inputMessage.setText("");
                    } else {
                        runOnUiThread(() ->
                                Toast.makeText(ChatActivity.this, "Error al enviar", Toast.LENGTH_SHORT).show());
                    }
                });
            }
        });

        // Iniciar polling de mensajes
        handler.post(pollingRunnable);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(pollingRunnable);
    }
}

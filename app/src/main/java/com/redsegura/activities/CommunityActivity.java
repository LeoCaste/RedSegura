package com.redsegura.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.redsegura.R;

public class CommunityActivity extends AppCompatActivity {

    private ListView communityListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);

        communityListView = findViewById(R.id.listViewCommunities); // ✅

        // Ejemplo de comunidades locales (esto puede venir del backend)
        String[] communities = {"Barrio Norte", "Villa Esperanza", "Temuco Centro", "Labranza Sur"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                communities
        );

        communityListView.setAdapter(adapter);

        communityListView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedCommunity = communities[position];
            Intent chatIntent = new Intent(this, ChatActivity.class);
            chatIntent.putExtra("username", selectedCommunity);
            startActivity(chatIntent);
        });
    }
}

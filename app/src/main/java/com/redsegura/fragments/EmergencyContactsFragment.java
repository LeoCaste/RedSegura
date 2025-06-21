package com.redsegura.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.redsegura.R;
import com.redsegura.adapters.EmergencyContactAdapter;
import com.redsegura.models.Contact;
import java.util.ArrayList;
import java.util.List;

public class EmergencyContactsFragment extends Fragment {

    private RecyclerView recyclerView;
    private EmergencyContactAdapter adapter;
    private List<Contact> contactList;

    public EmergencyContactsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_emergency_contacts, container, false);

        recyclerView = view.findViewById(R.id.emergency_contacts_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        contactList = new ArrayList<>();
        adapter = new EmergencyContactAdapter(contactList);
        recyclerView.setAdapter(adapter);

        Button addContactBtn = view.findViewById(R.id.add_emergency_contact_button);
        addContactBtn.setOnClickListener(v -> {
            // Aquí iría la lógica para agregar nuevo contacto (e.g., abrir diálogo)
        });

        // TODO: Cargar contactos reales desde base de datos o almacenamiento local

        return view;
    }
}

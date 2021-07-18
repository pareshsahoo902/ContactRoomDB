package com.demoapp.mycontacts;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.demoapp.mycontacts.Adapter.ContactAdapter;
import com.demoapp.mycontacts.Database.TableContact.Contact;
import com.demoapp.mycontacts.Database.TableContact.ContactViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fabContact;
    ContactViewModel viewModel;
    RecyclerView contactRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fabContact = findViewById(R.id.addContact);
        contactRecycler = findViewById(R.id.contactRecycler);
        contactRecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        contactRecycler.setHasFixedSize(true);


        fabContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ContactActivity.class).putExtra("TYPE",0));
            }
        });


        viewModel = ViewModelProviders.of(this).get(ContactViewModel.class);

        final ContactAdapter adapter = new ContactAdapter(MainActivity.this,viewModel);
        contactRecycler.setAdapter(adapter);


        viewModel.getAllContacts().observe(this, new Observer<List<Contact>>() {
            @Override
            public void onChanged( List<Contact> contacts) {
                adapter.updateList(contacts);
            }
        });


    }
}
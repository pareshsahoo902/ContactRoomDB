package com.demoapp.mycontacts;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.demoapp.mycontacts.Database.TableContact.Contact;
import com.demoapp.mycontacts.Database.TableContact.ContactViewModel;

public class ContactActivity extends AppCompatActivity {
    TextView headerText;
    int type;
    ImageView close, done;
    ContactViewModel viewModel;
    EditText name, contact, email;
    Contact contactEdit;
    Button deleteBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        type = getIntent().getIntExtra("TYPE", 0);

        contactEdit = (Contact) getIntent().getSerializableExtra("CONTACT");


        headerText = findViewById(R.id.textheader);
        close = findViewById(R.id.close);
        done = findViewById(R.id.done);
        name = findViewById(R.id.nameText);
        contact = findViewById(R.id.contactText);
        deleteBtn = findViewById(R.id.deleteBtn);
        email = findViewById(R.id.emailText);
        email.setText("none");
        if (type == 0) {
            headerText.setText("Add Contact");
        } else if (type == 1) {
            headerText.setText("Edit Contact");
            deleteBtn.setVisibility(View.VISIBLE);
        } else {
            headerText.setText("Contact");
        }
        if (contactEdit != null) {
            name.setText(contactEdit.getName());
            contact.setText(contactEdit.getContact());
            email.setText(contactEdit.getEmail());
        }

        viewModel = ViewModelProviders.of(this).get(ContactViewModel.class);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.delete(contactEdit);
                finish();
            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validateFeilds();

            }
        });

    }

    private void validateFeilds() {

        if (TextUtils.isEmpty(name.getText().toString())) {
            Toast.makeText(this, "Enter a Name to save!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(contact.getText().toString())) {
            Toast.makeText(this, "Enter a Phone number to save!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!TextUtils.isDigitsOnly(contact.getText().toString())) {
            Toast.makeText(this, "Enter Valid Number", Toast.LENGTH_SHORT).show();
            return;
        }


        if (contactEdit != null) {
            contactEdit.setName(name.getText().toString());
            contactEdit.setContact(contact.getText().toString());
            contactEdit.setEmail(email.getText().toString());
            viewModel.update(contactEdit);
        } else {
            Contact contactObj = new Contact(name.getText().toString(), email.getText().toString(), contact.getText().toString().trim());

            viewModel.insert(contactObj);
        }


        Toast.makeText(this, "Contact Saved!", Toast.LENGTH_SHORT).show();
        finish();
    }
}
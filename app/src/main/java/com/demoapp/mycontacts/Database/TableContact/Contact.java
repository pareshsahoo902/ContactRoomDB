package com.demoapp.mycontacts.Database.TableContact;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "contact_table")
public class Contact implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;


    private String name , email , contact;

    public Contact(String name, String email, String contact) {
        this.name = name;
        this.email = email;
        this.contact = contact;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getContact() {
        return contact;
    }
}

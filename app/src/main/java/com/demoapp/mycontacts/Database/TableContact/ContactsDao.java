package com.demoapp.mycontacts.Database.TableContact;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.net.ContentHandler;
import java.util.List;

@Dao
public interface ContactsDao {

    @Query("SELECT * FROM contact_table")
    LiveData<List<Contact>> getAllContacts();

    @Insert
    void insert(Contact contact);

    @Update
    void update(Contact contact);

    @Delete
    void delete(Contact contact);


}

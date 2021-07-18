package com.demoapp.mycontacts.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.demoapp.mycontacts.Database.TableContact.Contact;
import com.demoapp.mycontacts.Database.TableContact.ContactsDao;

@Database(entities = {Contact.class}, version = 1)
public abstract class MyDatabase extends RoomDatabase {

    private static MyDatabase INSTANCE;

    public abstract ContactsDao contactsDao();


    public static MyDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), MyDatabase.class, "my_contacts")
                    .build();
        }
        return INSTANCE;
    }

}

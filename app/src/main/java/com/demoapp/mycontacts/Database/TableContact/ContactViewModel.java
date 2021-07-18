package com.demoapp.mycontacts.Database.TableContact;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class ContactViewModel extends AndroidViewModel {

    private LiveData<List<Contact>> contactLists;
    private ContactsRepository contactsRepository;

    public ContactViewModel(Application application) {
        super(application);
        contactsRepository = new ContactsRepository(application);
        contactLists = contactsRepository.getAllContacts();

    }

    public void insert(Contact contact) {
        contactsRepository.insert(contact);
    }

    public void update(Contact contact) {
        contactsRepository.update(contact);
    }

    public void delete(Contact contact) {
        contactsRepository.delete(contact);
    }

    public LiveData<List<Contact>> getAllContacts() {
        return contactLists;
    }


}

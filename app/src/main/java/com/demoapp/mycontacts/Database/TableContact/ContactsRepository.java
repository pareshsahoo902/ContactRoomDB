package com.demoapp.mycontacts.Database.TableContact;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.demoapp.mycontacts.Database.MyDatabase;

import java.util.List;

public class ContactsRepository {

    private ContactsDao contactsDao;

    private LiveData<List<Contact>> contactLists;

    public ContactsRepository(Application application) {
        MyDatabase myDb = MyDatabase.getDatabase(application);
        contactsDao = myDb.contactsDao();
        contactLists = contactsDao.getAllContacts();
    }


    public void insert(Contact contact) {
        new InsertContact(contactsDao).execute(contact);
    }

    public void update(Contact contact) {
        new UpdateContact(contactsDao).execute(contact);
    }

    public void delete(Contact contact) {
        new DeleteContact(contactsDao).execute(contact);
    }

    public LiveData<List<Contact>> getAllContacts() {
        return contactLists;
    }


    //ASync


    public static class InsertContact extends AsyncTask<Contact, Void, Void> {
        private ContactsDao dao;

        public InsertContact(ContactsDao contactsDao) {
            this.dao = contactsDao;
        }

        @Override
        protected Void doInBackground(Contact... contacts) {
            dao.insert(contacts[0]);
            return null;
        }
    }


    public static class UpdateContact extends AsyncTask<Contact, Void, Void> {
        private ContactsDao dao;

        public UpdateContact(ContactsDao contactsDao) {
            this.dao = contactsDao;
        }

        @Override
        protected Void doInBackground(Contact... contacts) {
            dao.update(contacts[0]);
            return null;
        }
    }

    public static class DeleteContact extends AsyncTask<Contact, Void, Void> {
        private ContactsDao dao;

        public DeleteContact(ContactsDao contactsDao) {
            this.dao = contactsDao;
        }

        @Override
        protected Void doInBackground(Contact... contacts) {
            dao.delete(contacts[0]);
            return null;
        }
    }

}

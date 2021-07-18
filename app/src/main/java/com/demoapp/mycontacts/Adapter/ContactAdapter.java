package com.demoapp.mycontacts.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.amulyakhare.textdrawable.TextDrawable;
import com.demoapp.mycontacts.ContactActivity;
import com.demoapp.mycontacts.Database.TableContact.Contact;
import com.demoapp.mycontacts.Database.TableContact.ContactViewModel;
import com.demoapp.mycontacts.MainActivity;
import com.demoapp.mycontacts.R;

import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private List<Contact> contactList = new ArrayList<>();
    private Context mCOntext;

    private ContactViewModel viewModel;

    public ContactAdapter(Context mCOntext,ContactViewModel viewModel) {

        this.mCOntext = mCOntext;
        this.viewModel  = viewModel;
    }

    public void updateList(List<Contact > contacts){
        this.contactList = contacts;
        notifyDataSetChanged();
    }

    @NonNull

    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        return new ContactViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull  ContactViewHolder holder, int position) {

        holder.name.setText(contactList.get(position).getName());
        TextDrawable drawable = TextDrawable.builder()
                .beginConfig()
                .textColor(mCOntext.getResources().getColor(R.color.black))
                .withBorder(1)
                .endConfig()
                .buildRoundRect(contactList.get(position).getName().substring(0,2).toUpperCase(),mCOntext.getResources().getColor(R.color.white),12);

        holder.image.setImageDrawable(drawable);

        Bundle bundle =  new Bundle();
        bundle.putSerializable("CONTACT",contactList.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext().startActivity(new Intent(mCOntext, ContactActivity.class).putExtra("TYPE",1).putExtras(bundle));

            }
        });

        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                viewModel.delete(contactList.get(position));
            }
        });




    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder{

        public TextView name;
        public ImageView image , share;

        public ContactViewHolder(@NonNull  View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            share = itemView.findViewById(R.id.share);
            image = itemView.findViewById(R.id.image);
        }
    }
}

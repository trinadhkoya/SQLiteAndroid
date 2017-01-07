package com.numetriclabz.dbsqlite;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private List<PhoneBook> contactList;
    private ListView listView;
    private TextView name, number, type;
    private Button add;
    private PhonebookAdapter adapter;
    PhoneBookHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.contactlist);

        db = new PhoneBookHandler(this);

        contactList = db.getAllContacts();

        adapter = new PhonebookAdapter(this, contactList);
        listView.setAdapter(adapter);
    }

    public void onClick(View view){

        name = (EditText) findViewById(R.id.name);
        number = (EditText) findViewById(R.id.number);
        type = (EditText) findViewById(R.id.type);

        String cName = name.getText().toString();
        String num = number.getText().toString();
        String cType = type.getText().toString();

        int id = db.addContact(new PhoneBook(cName, num, cType));
        contactList.add(new PhoneBook(id, cName, num, cType));
        adapter.notifyDataSetChanged();
    }
}
